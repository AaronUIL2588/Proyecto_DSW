/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Punto único de conexión a la base de datos. Crea una sola vez la fábrica de
 * JPA (unidad de persistencia "ConexionJPA", definida en persistence.xml) y de
 * ahí salen todos los EntityManager que usan los repositorios.
 *
 * @author Felix_isq
 */
public final class Conexion {

    private static final EntityManagerFactory FABRICA
            = Persistence.createEntityManagerFactory("ConexionJPA");

    private Conexion() {
    }

    /**
     * Crea un EntityManager nuevo. Quien lo pide es responsable de cerrarlo.
     */
    public static EntityManager crearEntityManager() {
        return FABRICA.createEntityManager();
    }

    /**
     * Cierra la conexión. Llamar al terminar el programa.
     */
    public static void cerrar() {
        if (FABRICA.isOpen()) {
            FABRICA.close();
        }
    }
}
