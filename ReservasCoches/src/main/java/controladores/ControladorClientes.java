/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.util.TreeSet;
import modelos.Cliente;

/**
 *
 * @author Portatil
 */
public class ControladorClientes {
    TreeSet<Cliente> listadoClientes = new TreeSet();

    /**
     * Exportar ejemplos para la tabla.
     *
     * @return
     */
//    public boolean exportarEjemplos() {
////        listadoClientes.add ("jose ramon", "albacete", "2006", 1.6f);
//        añadir("Lionel Messi", "Fútbol", "1987", 1.70f);
//        añadir("Michael Jordan", "Baloncesto", "1963", 1.98f);
//        añadir("Simone Biles", "Gimnasia", "1997", 1.42f);
//        añadir("Usain Bolt", "Atletismo", "1986", 1.95f);
//
//        return false;
//
//    }

    /**
     * Vaciar completamente
     *
     */
    public void vaciar() {
        listadoClientes.clear(); // Elimina todos los elementos de la colección
    }

    /**
     * Añadir un nuevo deportista
     *
     * @param id_usuario
     * @param nombre
     * @param apellidos
     * @param telefono
     * @param correo
     * @param contraseña
     * @return
     */
   public boolean añadir(int id_usuario, String nombre, String apellidos, String telefono, String correo, String contraseña) {
    if (listadoClientes == null) {
        listadoClientes = new TreeSet<>();
    }
    try {
        Cliente cliente1 = new Cliente(0,"Jose", "Martinez", "698712512", "josem@gmail.com","contraseña");
        return listadoClientes.add(cliente1); 
    } catch (NumberFormatException e) {
        System.err.println();
        return false; 
    }
}

    /**
     * Borrar por nombre
     *
     * @param nombre
     * @return
     */
    public boolean borrarPorNombre(String nombre) {
        if (listadoClientes == null || listadoClientes.isEmpty()) {
            return false;//Si esta vacío no borra nada.
        }
        // Buscar el deportista
        for (Cliente cl : listadoClientes) {
            if (cl.getNombre().equalsIgnoreCase(nombre)) {
//                return vaciar(nombre);
            }
        }

        return false;
    }

    /**
     * Convertir de objeto a matriz
     *
     * @return
     */
    public Object[][] convertirAMatrizObject() {

        // creo matriz con tantas filas como deportistas hay en el treeSet y 
        // columnas 4, pues son 4 datos a almacenar.
        Object[][] matrizObj = new Object[listadoClientes.size()][6];

        int id = 0;

        for (Cliente cl : this.listadoClientes) {

            matrizObj[id][0] = cl.getId_usuario();

            matrizObj[id][1] = cl.getNombre();

            matrizObj[id][2] = cl.getApellidos()+ ""; // como cadena

            matrizObj[id][3] = cl.getTelefono()+ ""; // como cadena
            
            matrizObj[id][4] = cl.getCorreo()+ "";
            
            matrizObj[id][5] = cl.getContraseña()+ "";

            id++;

        }
        return matrizObj;

    }
}
