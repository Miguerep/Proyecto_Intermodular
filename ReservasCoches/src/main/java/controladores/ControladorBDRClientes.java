/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelos.Cliente;

/**
 *
 * @author Portatil
 */
public class ControladorBDRClientes {

    String nombreBDR = "Clientes.odb";
    String rutaBase = "db/Clientes";
    String rutaBDR = rutaBase + nombreBDR;
    String jpql;
    Connection con;
    Statement sent;

    public ControladorBDRClientes() {
        conectarBDR();
    }

    public boolean conectarBDR() {
        boolean creaConexion = false;
        try {
            //Se conecta con la base de datos, con la url, el usuario y la contraseña que hemos indicado antes.
            con = DriverManager.getConnection(rutaBDR);
            System.out.println("Conexión establecida con " + rutaBDR);
            creaConexion = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql;

        try {
            sent = con.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS `Clientes`(\n"
                    + "    `id_cliente` INT NOT NULL,\n"
                    + "    `nombre` VARCHAR(40) NOT NULL,\n"
                    + "    `apellidos` VARCHAR(50),\n"
                    + "    `telefono` VARCHAR(10) NOT NULL UNIQUE,,\n"
                    + "    `correo` VARCHAR(40) UNIQUE,\n"
                    + "    PRIMARY KEY (`id_cliente`)\n"
                    + "  );";
            sent.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Ha ocurrido algun error");
        }

        return creaConexion;
    }
    public void cerrarConexionBD() {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algun error");
        }
    }
    public Object[][] objetenerTodo() {
        Object[][] tabla = null;
        ResultSet rs;
        String sql = "Select  * FROM clientes";
        int numRegistros;
        int contador = 0;
        int idCliente;
        String nombre, apellidos, telefono,correo;
        
        try {
            
            sent = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = sent.executeQuery(sql);

            //Vamos al ultimo registro y obtenemos su posicion para saber cuantos registros hay.
            rs.last();
            numRegistros = rs.getRow();

            //Damos tamaño a la matriz de objetos para almacenar lo leido de la BD.
            System.out.println("registros obtenidos: " + numRegistros);
            tabla = new Object[numRegistros][5];
            
            //Recolocamos el puntero para recorrer el resultado.
            rs.beforeFirst();
            System.out.println("Lista de ciudades");
            while (rs.next()) {
                //Preparamos los datos
                idCliente = rs.getInt("idCliente");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                telefono = rs.getString("telefono");
                correo = rs.getString("correo");
                
                //Mostramos los datos por terminal para hacer seguimiento de la ejecucion.
                System.out.println("ID Cliente: " + idCliente + "\t Nombre: " + nombre + "\t Apellidos: " + apellidos + "\t Telefono: " + telefono + "\t Correo: " + correo);
                //Guardamos los datos en la tabla a devolver.
                tabla[contador][0] = idCliente;
                tabla[contador][1] = nombre;
                tabla[contador][2] = apellidos;
                tabla[contador][3] = telefono;
                tabla[contador][4] = correo;
                
                //Avanzamos posicion en el array.
                contador++;
            } 
        }
        catch (SQLException e) {
                System.out.println(e.getMessage());
        }
        return tabla;
    }
}
