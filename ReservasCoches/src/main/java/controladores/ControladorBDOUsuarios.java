/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

    // Conexión a la BDO ---------------------------------------------------
    public void conectarBDO() {
        try {
        emf = Persistence.createEntityManagerFactory(rutaBDO);
        em = emf.createEntityManager();
        } catch (Exception e) {
            System.err.println("\tError al ejecutar la conexión .");
        }
    }
    public void cerrarConexion(){
            em.close();
            emf.close();
    }
    
    public void crearUsuariosEjemplo() {
        try {
            // inicio transacción bloque hacer datos persistentes -> uno por cada commit()
            em.getTransaction().begin();
            UsuariosAplicacion u1 = new UsuariosAplicacion("Juan", "1234");
            UsuariosAplicacion u2 = new UsuariosAplicacion("Admin", "1234");
            UsuariosAplicacion u3 = new UsuariosAplicacion("Pedro", "1234");
       
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
     * @param nombre
     * @return 
     */
    
    public String obtenerClave(String nombre){
        UsuariosAplicacion u;
        String respuesta = "";
         u = em.find(UsuariosAplicacion.class, nombre);
         if (u!=null) {
            respuesta = u.getNombre();
        }
        return respuesta;
    }
    

}
