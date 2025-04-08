/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package equipoverde.reservascoches.controlador;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import modelos.UsuariosAplicacion;

/**
 *
 * @author Portatil
 */
public class ControladorInicioSesion {

    String nombreBDO = "Usuarios.odb";
    String rutaBase = "$objectdb/db/";
    String rutaBDO = rutaBase + nombreBDO;
    String jpql;

    // Conexión a la BDO ---------------------------------------------------
    public void ConectarDB() {
        EntityManagerFactory emf;
        emf = Persistence.createEntityManagerFactory(rutaBDO);
        EntityManager em;
        em = emf.createEntityManager();
    }
    public void CerrarConexion(EntityManager em, EntityManagerFactory emf){
            em.close();
            emf.close();
    }
    
    public void CrearUsuarios(EntityManager em) {
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
    

}
