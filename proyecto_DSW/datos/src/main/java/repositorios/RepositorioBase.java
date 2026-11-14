package repositorios;

import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManager;

/**
 * Clase abstracta utilizada en los repositorios del programa. Trabaja con
 * la clase Conexion para hacer uso de los EntityManager.
 *
 * @author Felix_isq
 */
public abstract class RepositorioBase {
    /**
     * Método utilizado para realizar operaciones que modifiquen la información
     * de la base de datos. Utiliza la interfaz funcional Consumer, la cual
     * recibe un objeto del tipo T (generico), en este caso, EntityManager,
     * para realizar operaciones sin necesidad de devolver algún resultado.
     * 
     * @param accion 
     */
    protected void ejecutar(Consumer<EntityManager> accion) {
        EntityManager em = Conexion.crearEntityManager();
        try {
            em.getTransaction().begin();
            accion.accept(em);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Ejecuta una consulta (solo lectura) y regresa su resultado.
     */
    protected <T> T consultar(Function<EntityManager, T> consulta) {
        EntityManager em = Conexion.crearEntityManager();
        try {
            return consulta.apply(em);
        } finally {
            em.close();
        }
    }
}
