package repositorios;

import interfaces.IAportacionRepositorio;
import entidades.Aportacion;
import java.util.List;

/**
 * Clase utilizada para operaciones hechas a la clase/entidad Aportacion.
 * Hereda los métodos de la clase RepositorioBase, e implementa su propia
 * interfaz con los métodos necesarios para esta misma clase.
 *
 * @author Felix_isq
 */
public class AportacionRepositorio extends RepositorioBase implements IAportacionRepositorio {

    /**
     * Método para guardar un objeto de la entidad Aportacion en la base de datos.
     * 
     * @param aportacion Objeto del tipo Aportacion a guardar en la base de datos
     */
    @Override
    public void guardar(Aportacion aportacion) {
        // Utiliza el método heredado ejecutar(), con la operación persist(),
        // recibiendo el objeto aportacion para insertarlo
        ejecutar(em -> em.persist(aportacion));
    }

    /**
     * Método para buscar una aportación, utilizando su ID, en la base de datos.
     * 
     * @param id Valor numerico que representa el ID de la aportación a buscar
     * @return Objeto del tipo Aportacion (o null) como resultado de la busqueda
     */
    @Override
    public Aportacion buscarPorId(Long id) {
        // Utiliza el método heredado consultar(), con la operación find(),
        // recibiendo la clase del objeto que regresará (donde, en caso de no
        // encontrar coincidencias, devolverá null) y el ID que lo identifica
        return consultar(em -> em.find(Aportacion.class, id));
    }

    /**
     * Método para obtener la lista de aportaciones de un proyecto específico,
     * utilizando el ID del proyecto, desde la base de datos.
     * 
     * @param idProyecto Valor numerico que representa el ID del proyecto
     * @return Lista de objetos del tipo Aportacion
     */
    @Override
    public List<Aportacion> listarPorProyecto(Long idProyecto) {
        // Utiliza consultar(), con la operación createQuery(), creando la
        // sentencia específica para la busqueda de las aportaciones de los
        // proyectos. Obtiene objetos de la clase Aportacion, asigna el id
        // del parametro a la sentencia y devuelve los resultados en una lista.
        return consultar(em -> em.createQuery(
                "SELECT a FROM Aportacion a WHERE a.proyecto.id = :idProyecto", Aportacion.class)
                .setParameter("idProyecto", idProyecto)
                .getResultList());
    }

    /**
     * Método para obtener la lista de aportaciones de un usuario específico,
     * utilizando el ID del usuario, desde la base de datos.
     * 
     * @param idUsuario valor numerico que representa el ID del usuario
     * @return Lista de objetos del tipo Aportacion
     */
    @Override
    public List<Aportacion> listarPorUsuario(Long idUsuario) {
        // Utiliza consultar(), con la operación createQuery(). Obtiene objetos
        // de la clase Aportacion, asigna el id del parametro a la sentencia
        // y devuelve los resultados en una lista.
        return consultar(em -> em.createQuery(
                "SELECT a FROM Aportacion a WHERE a.usuario.id = :idUsuario", Aportacion.class)
                .setParameter("idUsuario", idUsuario)
                .getResultList());
    }

    /**
     * Método para calcular el monto total recaudado de las aportaciones
     * dirigidas a un proyecto específico. Utiliza el ID del proyecto.
     * 
     * @param idProyecto Valor numerico que representa el ID del proyecto
     * @return Valor del total recaudado, por medio de aportaciones, del proyecto específico. Devuelve 0.0, si el resultado es nulo.
     */
    @Override
    public double obtenerTotalRecaudado(Long idProyecto) {
        // Utiliza consultar(), con la operación createQuery(). Obtiene el
        // resultado de la sumatoria de las aportaciones (del tipo Double),
        // asigna el id del parametro a la sentencia y devuelve el resultado.
        Double total = consultar(em -> em.createQuery(
                "SELECT SUM(a.monto) FROM Aportacion a WHERE a.proyecto.id = :idProyecto", Double.class)
                .setParameter("idProyecto", idProyecto)
                .getSingleResult());
        // Verifica si el resultado es nulo. En caso de serlo, devuelve 0.0
        // Caso contrario, devuelve el valor del resultado.
        return total == null ? 0.0 : total;
    }
}