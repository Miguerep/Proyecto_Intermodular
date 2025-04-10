/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

/**
 *
 * @author Portatil
 */
public class ControladorReservas {

    Connection con;
    Statement sentencia;
    String sql;
    String usuario = "prueba";
    String clave = "1234";
    String url = "jdbc:mysql://192.168.0.177/coches";

    public void conectarBD() {
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
    }
    /**
     * Desconectar de la base de datos
     */

    public void desconectarBD() {
        // cierre de la conexión
        try {
        con.close();
            
        } catch (SQLException e) {
            // Información del Error
            System.err.println("SQL Error mensaje: " + e.getMessage());
            System.err.println("SQL Estado: " + e.getSQLState());
            System.err.println("SQL código específico: " + e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

 public Object[][] obtenerTodo() {

        Object[][] tabla = null;

        ResultSet rs;

        String sql = "SELECT * FROM deportistas";

        int numRegistros;

        int contador = 0;

        String nomYApe, deporte;

        int añoNac;

        float altura;



        try {

            rs = sentencia.executeQuery(sql);

            //vamos al último registro y obtenemos su posición para saber cuantos registros hay

            rs.last();

            numRegistros = rs.getRow();



            // damos tamaño a la matriz de objetos para almacenar lo leído de la BD

            System.out.println("registros obtenidos:" + numRegistros);

            tabla = new Object[numRegistros][4];



            // recolocamos el puntero para recorrer el resultado

            rs.beforeFirst();

            System.out.println("Lista de deportistas:");

            while (rs.next()) {

                // preparamos los datos

                nomYApe = rs.getString("nomYApe");

                deporte = rs.getString("deporte");

                añoNac = rs.getInt("añoNac");

                altura = rs.getFloat("altura");

                //mostramos los datos por el terminal para hacer seguimiento de la ejecución

                System.out.println("Deportista: " + nomYApe + "\t Deporte: " + deporte + "\t Año nacimiento: " + añoNac + "\t Altura: " + altura);

                // guardamos los datos en la tabla a devolver

                tabla[contador][0] = nomYApe;

                tabla[contador][1] = deporte;

                tabla[contador][2] = añoNac;

                tabla[contador][3] = altura;

                // avanzamos posición en el array

                contador++;

            }

        } catch (SQLException ex) {

            Logger.getLogger(ControladorReservas.class.getName()).log(Level.SEVERE, null, ex);

        }
        return tabla;

    }

    public void borrarPorNombre(String nomArchivo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void vaciar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void exportarEjemplos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
