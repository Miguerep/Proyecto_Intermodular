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
    String usuario = "root";
    String clave = "";
    String url = "jdbc:mysql://192.168.0.30/proyecto_final";
    String jpql;
    Connection con;
    Statement sent;

    public ControladorBDRClientes() {
        conectarBDR();
    }

    public void conectarBDR() {
        /**
         * Conectar con la base de datos
         */
        try {
            // conexión con la BD
            con = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión establecida con " + url);

            
        } catch (SQLException e) {
            // Información del Error
            System.err.println("SQL Error mensaje: " + e.getMessage());
            System.err.println("SQL Estado: " + e.getSQLState());
            System.err.println("SQL código específico: " + e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        String sql;
    }
    public void cerrarConexionBD() {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algun error");
        }
    }
    public Object[][] obtenerTodo() {
        Object[][] tabla = null;
        ResultSet rs;
        String sql = "Select * FROM clientes";
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
                idCliente = rs.getInt("id_cliente");
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
            sql = "DELETE FROM clientes";
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
            sql = "INSERT INTO clientes (id_cliente, nombre, apellidos, telefono, correo) VALUES ('1', 'Jose', 'Rivera Martinez', '658524103', 'jose@gmail.com');";
            resultado = sent.executeUpdate(sql);
            
            sent = con.createStatement();
            sql2 = "INSERT INTO clientes (id_cliente, nombre, apellidos, telefono, correo) VALUES ('2', 'Marcos', 'Gonzalez Garcia', '692410729', 'marcos@gmail.com');";
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
            sql = "INSERT INTO clientes (id_cliente, nombre, apellidos, telefono, correo) VALUES ('" + idCliente + "', '" + nombre + "', '" + apellidos + "', '" + telefono + "', '" + correo + "')";
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
    public boolean borrar(String nombre) {
        String sql;
        boolean correcto = false;
        int resultado;
        try {
            sent = con.createStatement();
            sql = "DELETE FROM clientes WHERE nombre = " + "'" + nombre + "'";
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
            sql = "DELETE FROM clientes ";
            resultado = sent.executeUpdate(sql);
            if (resultado >=0) {
                correcto = true;
            }
            System.out.println("Se han borrado todos los clientes.");
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
                sql = "INSERT INTO clientes (id_cliente, nombre, apellidos, telefono, correo) VALUES ('" + tabla[contador][0] + "', '" + tabla[contador][1] + "', '" + tabla[contador][2] + "', '" + tabla[contador][3] + "', '" + tabla[contador][4] + "')";
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
