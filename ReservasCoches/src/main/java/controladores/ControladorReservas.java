/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

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
    String url = "jdbc:mysql://192.168.0.30/proyecto_final";

    public ControladorReservas() {
        leerConexion();
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
        int id_reserva, id_cliente, id_empleado, id_servicio;
        Timestamp fecha_hora;

        try {
            sentencia = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
                estado = rs.getString("estado");
                fecha_hora = rs.getTimestamp("fecha_hora");
                id_cliente = rs.getInt("id_cliente");
                id_empleado = rs.getInt("id_empleado");
                id_servicio = rs.getInt("id_servicio");

                // guardamos los datos en la tabla a devolver
                tabla[contador][0] = id_reserva;
                tabla[contador][1] = estado;
                tabla[contador][2] = fecha_hora;
                tabla[contador][3] = id_cliente;
                tabla[contador][4] = id_empleado;
                tabla[contador][5] = id_servicio;

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
            sql = "DELETE FROM reservas WHERE id_reserva = " + "'" + id + "'";
            resultado = sentencia.executeUpdate(sql);
            if (resultado == 1) {
                correcto = true;
            }
            System.out.println("Se ha borrado la reserva");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algún error: " + e.getMessage());
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
            System.out.println("Ha ocurrido algun error" + e.getMessage());
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
            sql = "INSERT INTO reservas (`id_reserva`, `estado`, `fecha_hora`, `id_cliente`,`id_empleado`,`id_servicio`) VALUES (1, 'Sin Completar', CURRENT_TIMESTAMP, 1, 1, 1);";
            resultado = sentencia.executeUpdate(sql);

            sentencia = con.createStatement();
            sql2 = "INSERT INTO reservas (`id_reserva`, `estado`, `fecha_hora`, `id_cliente`,`id_empleado`,`id_servicio`) VALUES (2, 'Hecho', CURRENT_TIMESTAMP, 1, 1, 1) ;";
            resultado = sentencia.executeUpdate(sql2);

            if (resultado >= 0) {
                correcto = true;
            }
            System.out.println("Se ha insertado el servicio");
        } catch (SQLException e) {
            System.err.println("Ha ocurrido algun error" + e.getMessage());
        }
        return correcto;
    }

    public boolean añadir(int id_reserva, int id_cliente, int id_empleado, int id_servicio, String estado) {
        String sql;
        boolean correcto = false;
        int resultado;

        try {
            sentencia = con.createStatement();
            sql = "INSERT INTO Reservas (id_reserva, estado, fecha_hora, id_cliente, id_empleado, id_servicio) "
                    + "VALUES (" + id_reserva + ", '" + estado + "', CURRENT_TIMESTAMP, " + id_cliente + ", " + id_empleado + ", " + id_servicio + ")";

            resultado = sentencia.executeUpdate(sql);

            if (resultado > 0) {
                correcto = true;
            }

            System.out.println("Se ha insertado la reserva");
        } catch (SQLException e) {
            System.out.println("Ha ocurrido algún error: " + e.getMessage());
        }

        return correcto;
    }

    public Object[][] cargarArchivoXML() {
        FileInputStream fis;
        XMLDecoder xmld;
        Object[][] tabla = null;
        try {
            fis = new FileInputStream("listadoReservas.xml");
            xmld = new XMLDecoder(fis);
            tabla = (Object[][]) xmld.readObject();
            String sql;
            int contador = 0;

            for (Object[] objects : tabla) {
                Timestamp timestampActual = new Timestamp(System.currentTimeMillis());
                sql = "INSERT INTO reservas (`id_reserva`, `estado`, `fecha_hora`, `id_cliente`,`id_empleado`,`id_servicio`) VALUES ('"
                        + tabla[contador][0] + "', '"
                        + tabla[contador][1] + "', '"
                        + timestampActual.toString() + "', '"
                        + tabla[contador][3] + "', '"
                        + tabla[contador][4] + "', '"
                        + tabla[contador][5] + "')";
                sentencia.executeUpdate(sql);
                contador++;
            }

            xmld.close();
        } catch (FileNotFoundException | SQLException e) {
            System.err.println("\tERROR en la lectura de datos del archivo: " + "listadoReservas.xml " + e.getMessage());
        }
        return tabla;
    }

    public void guardarArchivoXML(Object[][] datos) {
        FileOutputStream fos;
        XMLEncoder xmle;
        try {
            fos = new FileOutputStream("listadoReservas.xml");
            xmle = new XMLEncoder(new BufferedOutputStream(fos));
            xmle.writeObject(datos);
            xmle.close();
            System.out.println("guardado correctamente");
        } catch (FileNotFoundException e) {
            System.err.println("\tERROR en la escritura de datos del archivo: " + "listadoReservas.xml" + e.getMessage());
        }
    }

    private void leerConexion() {

        String cadena, nombreFich = "c:\\Documentos\\Conexión.txt";
        final char SEPARADOR = ';';
        String arrayCadenas[];

        System.out.println("\nLEYENDO CONTENIDO DEL ARCHIVO '" + nombreFich + "':\n");
        try (BufferedReader fichBuf = new BufferedReader(new FileReader(nombreFich))) {
            cadena = fichBuf.readLine();
            while (cadena != null) {
                System.out.println(cadena);
                arrayCadenas = cadena.split("" + SEPARADOR);
                System.out.println("usuario: " + arrayCadenas[0] + " clave: " + arrayCadenas[1] + " url: " + arrayCadenas[2]);
                usuario = arrayCadenas[0];
                clave = arrayCadenas[1];
                url = arrayCadenas[2];
                cadena = fichBuf.readLine();
            }
            // se cierra el archivo
            fichBuf.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("");
    }
}
