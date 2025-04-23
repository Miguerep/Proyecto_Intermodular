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
    String rutaBase = "$db/Usuarios";
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
 // Cerrar conexion a la BDO ---------------------------------------------------
    public void cerrarConexion() {
        em.close();
        emf.close();
    }

    public void crearUsuariosEjemplo() {
        try {
            // inicio transacción bloque hacer datos persistentes -> uno por cada commit()
            em.getTransaction().begin();
            UsuariosAplicacion u1 = new UsuariosAplicacion("Pedro", "1234", true);
            UsuariosAplicacion u2 = new UsuariosAplicacion("Admin", "1234", true);
            UsuariosAplicacion u3 = new UsuariosAplicacion("Jesus", "1234", false);

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

    public Object[][] obtenerTodoInicioSesion() {
        Object[][] tabla = null;

        try {
            Query query = em.createQuery("SELECT u FROM UsuariosAplicacion u");
            List<UsuariosAplicacion> listaUsuarios = query.getResultList();

            tabla = new Object[listaUsuarios.size()][3];

            for (int i = 0; i < listaUsuarios.size(); i++) {
                UsuariosAplicacion u = listaUsuarios.get(i);
                tabla[i][0] = u.getNombre();
                tabla[i][1] = u.getContraseña();
                tabla[i][2] = u.isEsAdmin();
            }

        } catch (Exception e) {
            //Falta el logger
            System.out.println("Error");
        }
        return tabla;
    }
}
