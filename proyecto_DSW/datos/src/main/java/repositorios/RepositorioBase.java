package repositorios;

import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase abstracta utilizada en los repositorios del programa. Crea una sola
 * vez la fábrica de JPA (unidad de persistencia "ConexionJPA", definida en
 * persistence.xml) y la usa para crear los EntityManager que necesitan los
 * métodos ejecutar() y consultar().
 *
 * Es genérica (T es la entidad con la que trabaja el repositorio) para tener
 * en un solo lugar las operaciones guardar() y buscarPorId(), que son iguales
 * en todos los repositorios.
 *
 * @param <T> Entidad con la que trabaja el repositorio (Usuario, Proyecto...)
 * @author Felix_isq
 */
public abstract class RepositorioBase<T> {

    // Fábrica de EntityManager, creada una sola vez para todos los repositorios
    // (final: su valor no puede cambiar; static: es compartida por todas las
    // clases que heredan de RepositorioBase)
    private static final EntityManagerFactory FABRICA
            = Persistence.createEntityManagerFactory("ConexionJPA");

    // Clase de la entidad (por ejemplo Usuario.class); la necesita em.find()
    private final Class<T> clase;

    /**
     * Constructor utilizado por los repositorios hijos para indicar con qué
     * entidad trabajan.
     *
     * @param clase Clase de la entidad, por ejemplo Usuario.class
     */
    protected RepositorioBase(Class<T> clase) {
        this.clase = clase;
    }

    /**
     * Método para cerrar la fábrica, utilizado al cerrar el programa.
     */
    public static void cerrar() {
        if (FABRICA.isOpen()) {
            FABRICA.close();
        }
    }

    /**
     * Guarda una entidad en la base de datos. Es igual para todos los
     * repositorios, por eso está aquí y no se repite en cada uno.
     *
     * @param entidad Objeto a guardar en la base de datos
     */
    public void guardar(T entidad) {
        ejecutar(em -> em.persist(entidad));
    }

    /**
     * Busca una entidad por su id.
     *
     * @param id Identificador de la entidad
     * @return La entidad encontrada, o null si no existe
     */
    public T buscarPorId(Long id) {
        return consultar(em -> em.find(clase, id));
    }

    /**
     * Método utilizado para realizar operaciones que modifiquen la información
     * de la base de datos. Utiliza la interfaz funcional Consumer, la cual
     * recibe un objeto del tipo EntityManager para realizar operaciones sin
     * necesidad de devolver algún resultado. Este método es usado en una
     * expresión lambda.
     *
     * @param accion Acción que se realizará en la base de datos, usando EntityManager
     */
    protected void ejecutar(Consumer<EntityManager> accion) {
        // Se crea el EntityManager usando la fábrica
        EntityManager em = FABRICA.createEntityManager();
        try {
            // Se inicia la transacción
            em.getTransaction().begin();
            // accept() ejecuta la operación recibida en el parámetro
            accion.accept(em);
            // Se finaliza la transacción
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            // Si algo falla, rollback deshace los cambios de la transacción
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            // Siempre se cierra el EntityManager para liberar recursos
            em.close();
        }
    }

    /**
     * Método utilizado para realizar consultas a la base de datos y devolver
     * sus resultados. Similar a ejecutar(), pero utiliza la interfaz funcional
     * Function, que recibe un EntityManager y devuelve un resultado. Este
     * método se utiliza en una expresión lambda.
     *
     * @param <R> Tipo del resultado obtenido, dependiendo de la consulta realizada
     * @param consulta Consulta que se realizará a la base de datos
     * @return Resultado de la consulta realizada
     */
    protected <R> R consultar(Function<EntityManager, R> consulta) {
        // Se crea el EntityManager usando la fábrica
        EntityManager em = FABRICA.createEntityManager();
        try {
            // apply() ejecuta la consulta recibida y devuelve su resultado
            return consulta.apply(em);
        } finally {
            // Siempre se cierra el EntityManager para liberar recursos
            em.close();
        }
    }
}