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
     * Este método es usado en una expresión lambda.
     * 
     * @param accion Objeto representativo de la acción que se realizará, en este caso, a la base de datos, usando EntityManager
     */
    protected void ejecutar(Consumer<EntityManager> accion) {
        // Se crea el EntityManager usando la clase Conexion
        EntityManager em = Conexion.crearEntityManager();
        try {
            // Se realizan las operaciones comúnes (incluyendo el inicio y fin
            // de la transacción)
            em.getTransaction().begin();
            // Se usa el método accept de la interfaz Consumer, el cual permite
            // ejecutar la operación recibida en el parametro del mismo
            accion.accept(em);
            // Se finaliza la transacción
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            // Se manejan las excepciones, haciendo uso del método rollback en
            // la transacción, permitiendo volver al estado normal de la base
            // de datos, antes de realizar cambios
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            // Independientemente del resultado de la operación, al final del
            // método, se cierra el EntityManager para liberar los recursos utilizados
            em.close();
        }
    }

    /**
     * Método utilizado para realizar consultas a la base de datos y devolver
     * los resultados de las mismas. Similar al método ejecutar(), este método
     * utiliza la interfaz funcional Function, la cual recibe un objeto
     * (EntityManager o T, génerico) para realizar una operación y devolver
     * un resultado. Este método se utiliza en una expresión lambda.
     * 
     * @param <T> Tipo de dato génerico obtenido como resultado, dependiendo de la consulta realizada.
     * @param consulta Objeto que representa la consulta que se realizara a la base de datos
     * @return Objeto del resultado de la consulta realizada
     */
    protected <T> T consultar(Function<EntityManager, T> consulta) {
        // Se crea el EntityManager usando la clase Conexion
        EntityManager em = Conexion.crearEntityManager();
        try {
            // Se utiliza el método apply de la interfaz Function, el cual 
            // ejecuta la operación recibida (en este caso, una consulta) y
            // devuelve su resultado
            return consulta.apply(em);
        } finally {
            // Independientemente del resultado de la operación, al final del
            // método, se cierra el EntityManager para liberar los recursos utilizados
            em.close();
        }
    }
}
