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
    String usuario = "usuario";
    String clave = "96WFjTsdglPkS!R(";
    String url = "jdbc:mysql://192.168.0.30/coches";
    
    
    public  ControladorReservas(){
    conectarBD();
    }

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
        String sql;
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
        String sql = "SELECT * FROM reservas";
        int numRegistros;
        int contador = 0;
        String estado;
        int id_reserva, id_cliente, id_empleado, id_servicio , fecha_hora;

        try {

            rs = sentencia.executeQuery(sql);
            //vamos al último registro y obtenemos su posición para saber cuantos registros ha
            rs.last();
            numRegistros = rs.getRow();
            // damos tamaño a la matriz de objetos para almacenar lo leído de la BD
            System.out.println("registros obtenidos:" + numRegistros);
            tabla = new Object[numRegistros][6];
            // recolocamos el puntero para recorrer el resultado

            rs.beforeFirst();

            System.out.println("Lista de reservas:");

            while (rs.next()) {

                // preparamos los datos

                id_reserva = rs.getInt("id_reserva");
                id_cliente = rs.getInt("id_cliente");
                id_empleado = rs.getInt("id_empleado");
                id_servicio = rs.getInt("id_servicio");
                estado = rs.getString("estado");
                fecha_hora = rs.getInt("fecha_hora");

                // guardamos los datos en la tabla a devolver
                tabla[contador][0] = id_reserva;
                tabla[contador][1] = id_cliente;
                tabla[contador][2] = id_empleado;
                tabla[contador][3] = id_servicio;
                tabla[contador][4] = estado;
                tabla[contador][5] = fecha_hora;

               
                contador++;

            }

        } catch (SQLException ex) {
            System.out.println("Ha ocurrido algun error");
        }
        return tabla;

    }

    public boolean borrarPorID(int id) {
        String sql;
        boolean correcto = false;
        int resultado;
        try {
            sentencia = con.createStatement();
            sql = "DELETE FROM reserva WHERE id_reserva = " + "'" + id + "'";
            resultado = sentencia.executeUpdate(sql);
            if (resultado == 1) {
                correcto = true;
            }
            System.out.println("Se ha borrado la reserva");
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
            sentencia = con.createStatement();
            sql = "DELETE FROM reservas ";
            resultado = sentencia.executeUpdate(sql);
            if (resultado == 1) {
                correcto = true;
            }
            System.out.println("Se han borrado todas las reservas");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algun error");
        }
        return correcto;
    }

    public boolean añadirEjemplos() {

        String sql, sql2;
        boolean correcto = false;
        int resultado;

        try {
            //Inserto dos ejemplos de reservas.
            sentencia = con.createStatement();
            sql = "INSERT INTO reservas (`id_reserva`, `estado`, `fecha_hora`, `id_cliente`,`id_empleado`,`id_servicio`) VALUES (1, 1, '2025-04-22 15:30:00', 2, 3, 4) ;";
            resultado = sentencia.executeUpdate(sql);
            
            sentencia = con.createStatement();
            sql2 = "INSERT INTO ciudades (nombre, pais, codigoPostal, poblacion, superficieKM2, comunidadAutonoma, Patron) VALUES ('Barcelona', 'España', '18001', '1680782', '101.35', 'Cataluña', 'Santa Eulalia');";
            resultado = sentencia.executeUpdate(sql2);
            
            if (resultado >= 0) {
                correcto = true;
            }
            System.out.println("Se ha insertado la reserva");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algun error");
        }
        return correcto;
    }
}
