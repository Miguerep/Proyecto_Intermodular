/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelos.UsuariosAplicacion;

/**
 *
 * @author Portatil
 */
public class ControladorBDOUsuarios {

    String nombreBDO = "Usuarios.odb";
    String rutaBase = "db/Usuarios";
    String rutaBDO = rutaBase + nombreBDO;
    String jpql;
    EntityManager em;
    EntityManagerFactory emf;

    public ControladorBDOUsuarios() {
        conectarBDO();
    }

    // Conexión a la BDO ---------------------------------------------------
    public void conectarBDO() {
        try {
            emf = Persistence.createEntityManagerFactory(rutaBDO);
            em = emf.createEntityManager();
        } catch (Exception e) {
            System.err.println("\tError al ejecutar la conexión .");
        }
    }

    public void cerrarConexion() {
        em.close();
        emf.close();
    }

    public void crearUsuariosEjemplo() {
        try {
            // inicio transacción bloque hacer datos persistentes -> uno por cada commit()
            em.getTransaction().begin();
            UsuariosAplicacion u1 = new UsuariosAplicacion("Pedro", "1234", "Admin");
            UsuariosAplicacion u2 = new UsuariosAplicacion("Admin", "1234", "Admin");
            UsuariosAplicacion u3 = new UsuariosAplicacion("Jesus", "1234", "");

            // indicación de hacer persistentes los objetos vehículo
            em.persist(u1);
            em.persist(u2);
            em.persist(u3);

            // confirmacion de la persistencia
            em.getTransaction().commit();

        } catch (Exception e) {
            System.err.println("\tError al ejecutar la transacción.");
        }

    }

    /**
     * Devuelve vacio si no esta el usuario como si el valor es vacio
     *
     * @param nombre
     * @return
     */
    public String obtenerClave(String nombre) {
        UsuariosAplicacion u;
        String respuesta = "";
        u = em.find(UsuariosAplicacion.class, nombre);
        if (u != null) {
            respuesta = u.getContraseña();
        }
        return respuesta;
    }

    public String obtenerUsuario(String nombre) {
        UsuariosAplicacion u;
        String respuesta = "";
        u = em.find(UsuariosAplicacion.class, nombre);
        if (u != null) {
            respuesta = u.getNombre();
        }
        return respuesta;
    }

    public boolean comprobarAdmin(String nombre, String contraseña) {
        boolean respuesta = false;
        Object[][] usuarios = obtenerTodoInicioSesion();

        for (Object[] fila : usuarios) {
            String nombreUsuario = (String) fila[0];
            String contraseñaUsuario = (String) fila[1];
            String permisosUsuario = (String) fila[2];

            if (nombre.equalsIgnoreCase(nombreUsuario) && contraseña.equals(contraseñaUsuario)) {
                if (permisosUsuario.equals("Admin")) {
                    respuesta = true;
                }

            }

        }
        return respuesta;
    }

    public Object[][] obtenerTodoInicioSesion() {
        Object[][] tabla = null;
        int numRegistros;
        int contador = 0;
        String nombre, contraseña, permisos;

        Query query1 = em.createQuery("SELECT u FROM Usuario u");
        try {
            List<UsuariosAplicacion> listaUsuarios = query1.getResultList();
            numRegistros = listaUsuarios.size();

            tabla = new Object[numRegistros][3];

//            System.out.println("Lista usuarios");
            for (UsuariosAplicacion usu : listaUsuarios) {
//                System.out.println(usu);

                nombre = usu.getNombre();
                contraseña = usu.getContraseña();
                permisos = usu.getEsAdmin();

                tabla[contador][0] = nombre;
                tabla[contador][1] = contraseña;
                tabla[contador][2] = permisos;

                //avanzamos posicion en el array
                contador++;
            }

        } catch (Exception e) {
            //Falta el logger
            System.out.println("Error");
        }
        return tabla;
    }
}
