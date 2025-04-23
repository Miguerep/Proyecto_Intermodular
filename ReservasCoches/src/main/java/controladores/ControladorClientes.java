/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Cliente;

/**
 *
 * @author Portatil
 */
//public class ControladorClientes{
//      public Object[][] obtenerClientes() {
//
//        Object[][] tabla = null;
//
//        ResultSet rs;
//
//        String sql = "SELECT * FROM clientes";
//
//        int numRegistros;
//
//        int contador = 0;
//
//        try {
//
//            rs = .executeQuery(sql);
//
//            //vamos al último registro y obtenemos su posición para saber cuantos registros hay
//
//            rs.last();
//
//            numRegistros = rs.getRow();
//
//
//
//            // damos tamaño a la matriz de objetos para almacenar lo leído de la BD
//
//            System.out.println("registros obtenidos:" + numRegistros);
//
//            tabla = new Object[numRegistros][4];
//
//
//
//            // recolocamos el puntero para recorrer el resultado
//
//            rs.beforeFirst();
//
//            System.out.println("Lista de deportistas:");
//
//            while (rs.next()) {
//
//                // preparamos los datos
//
//                nomYApe = rs.getString("nomYApe");
//
//                deporte = rs.getString("deporte");
//
//                añoNac = rs.getInt("añoNac");
//
//                altura = rs.getFloat("altura");
//
//                //mostramos los datos por el terminal para hacer seguimiento de la ejecución
//
//                System.out.println("Deportista: " + nomYApe + "\t Deporte: " + deporte + "\t Año nacimiento: " + añoNac + "\t Altura: " + altura);
//
//                // guardamos los datos en la tabla a devolver
//
//                tabla[contador][0] = nomYApe;
//
//                tabla[contador][1] = deporte;
//
//                tabla[contador][2] = añoNac;
//
//                tabla[contador][3] = altura;
//
//                // avanzamos posición en el array
//
//                contador++;
//
//            }
//
//        } catch (SQLException ex) {
//
//            Logger.getLogger(ControladorBDOUsuarios.class.getName()).log(Level.SEVERE, null, ex);
//
//        }
//
//        return tabla;
//
//    }
//}