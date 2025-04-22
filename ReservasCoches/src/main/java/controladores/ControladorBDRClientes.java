/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import javax.persistence.Query;
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
            System.out.println("Lista de clientes");
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
    
    public boolean vaciar() {
        boolean correcto = false;
        String sql;
        int resultado;
        try {
            sent =con.createStatement();
            sql = "DELETE FROM Clientes";
            resultado = sent.executeUpdate(sql);
            if (resultado >= 0) {
                correcto = true;
            }
            System.out.println("Se han eliminado " + resultado + " clientes.");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algun error.");
        }
        return correcto;
    }
    
    public boolean añadirEjemplos() {

        String sql, sql2;
        boolean correcto = false;
        int resultado;

        try {
            //Inserto dos ejemplos de ciudades.
            sent = con.createStatement();
            sql = "INSERT INTO Clientes (id_Cliente, nombre, apellidos, telefono, correo) VALUES ('0', Jose', 'Rivera Martinez', '658524103', 'jose@gmail.com');";
            resultado = sent.executeUpdate(sql);
            
            sent = con.createStatement();
            sql2 = "INSERT INTO Clientes (id_Cliente, nombre, apellidos, telefono, correo) VALUES ('1', Marcos', 'Gonzalez Garcia', '692410729', 'marcos@gmail.com');";
            resultado = sent.executeUpdate(sql2);
            
            if (resultado >= 0) {
                correcto = true;
            }
            System.out.println("Se ha insertado el cliente");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algun error");
        }
        return correcto;
    }
    
    
    public boolean añadir(int idCliente, String nombre, String apellidos, String telefono, String correo){
        String sql;
        boolean correcto = false;
        int resultado;
        try {
            sent = con.createStatement();
            sql = "INSERT INTO Clientes (idCliente, nombre, apellidos, telefono, correo) VALUES ('" + idCliente + "', '" + nombre + "', '" + apellidos + "', '" + telefono + "', '" + correo + "')";
            resultado = sent.executeUpdate(sql);
            if (resultado >= 0) {
                correcto = true;
            }
            System.out.println("Se ha insertado el cliente");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algun error");
        }
        return correcto;
    }
    public boolean borrarPorNombre(String nombre) {
        String sql;
        boolean correcto = false;
        int resultado;
        try {
            sent = con.createStatement();
            sql = "DELETE FROM Clientes WHERE nombre = " + "'" + nombre + "'";
            resultado = sent.executeUpdate(sql);
            if (resultado == 1) {
                correcto = true;
            }
            System.out.println("Se ha borrado el cliente");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algun error");
        }
        return correcto;
    }
    public boolean borrarTodo() {
        String sql;
        boolean correcto = false;
        int resultado;
        try {
            sent = con.createStatement();
            sql = "DELETE FROM Clientes ";
            resultado = sent.executeUpdate(sql);
            if (resultado == 1) {
                correcto = true;
            }
            System.out.println("Se han borrado todos los clientes");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algun error");
        }
        return correcto;
    }
    public Object[][] cargarArchivoXML() {
        FileInputStream fis;
        XMLDecoder xmld;
        Object[][] tabla = null;
        try {
            fis = new FileInputStream("listadoClientes.xml");
            xmld = new XMLDecoder(fis);
            tabla = (Object[][]) xmld.readObject();
            String sql;
            int contador = 0;
            
            for (Object[] objects : tabla) {
                sql = "INSERT INTO Clientes (idCliente, nombre, apellidos, telefono, correo) VALUES ('" + tabla[contador][0] + "', '" + tabla[contador][1] + "', '" + tabla[contador][2] + "', '" + tabla[contador][3] + "', '" + tabla[contador][4] + "')";
                sent.executeUpdate(sql);
                contador++;
            }
            xmld.close();
        } catch (Exception e) {
            System.err.println("\tERROR en la lectura de datos del archivo: " + "listadoClientes.xml");
        }
        return tabla;
    }
    public void guardarArchivoXML(Object[][] datos) {
        FileOutputStream fos;
        XMLEncoder xmle;

        try {
            fos = new FileOutputStream("listadoClientes.xml");
            xmle = new XMLEncoder(new BufferedOutputStream(fos));
            xmle.writeObject(datos);
            xmle.close();
        } catch (Exception e) {
            System.err.println("\tERROR en la escritura de datos del archivo: " + "listadoClientes.xml");
        }
    }
}
