package repositorios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase que sirve como punto único de conexión a la base de datos. Crea una
 * sola vez la fábrica de JPA (unidad de persistencia "ConexionJPA", definida
 * en persistence.xml) y hace uso de la misma para crear el EntityManager
 * necesario para las clases de repositorios.
 *
 * @author Felix_isq
 */
public final class Conexion {

    // Fabrica creada e inicializada para utilizarse en la creación del EntityManager
    // (final permite que su valor no pueda modificarse, static que pueda
    //  usarse sin crear un objeto nuevo)
    private static final EntityManagerFactory FABRICA
            = Persistence.createEntityManagerFactory("ConexionJPA");

    /**
     * Método para crear un objeto del tipo EntityManager, usado en los
     * repositorios para las operaciones necesarias. Hace uso de la fabrica ya
     * creada de la clase.
     * 
     * @return objeto del tipo EntityManager, usado para las operaciones de los repositorios.
     */
    public static EntityManager crearEntityManager() {
        return FABRICA.createEntityManager();
    }

    /**
     * Método para cerrar la fabrica, utilizado al cerrar el programa.
     */
    public static void cerrar() {
        if (FABRICA.isOpen()) {
            FABRICA.close();
        }
    }
}