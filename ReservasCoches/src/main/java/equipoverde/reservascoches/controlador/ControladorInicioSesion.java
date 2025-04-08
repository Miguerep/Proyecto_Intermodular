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

/**
 *
 * @author Portatil
 */
public class ControladorInicioSesion {

    String nombreBDO = "AD_Ejemplo1.odb";
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

}
