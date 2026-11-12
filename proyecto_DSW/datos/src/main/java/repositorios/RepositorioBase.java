/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManager;

/**
 * Código común de los repositorios: abrir el EntityManager, manejar la
 * transacción y cerrarlo siempre. Así cada repositorio solo escribe lo que lo
 * hace distinto.
 *
 * @author Felix_isq
 */
public abstract class RepositorioBase {

    /**
     * Ejecuta una operación que modifica datos dentro de una transacción (con
     * rollback si falla).
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
