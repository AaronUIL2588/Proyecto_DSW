package repositorios;

import interfaces.IProyectoRepositorio;
import entidades.Proyecto;
import enumeradores.EstadoProyecto;
import java.util.List;

/**
 * Clase utilizada para operaciones hechas a la clase/entidad Proyecto.
 * Hereda los métodos de la clase RepositorioBase, e implementa su propia
 * interfaz con los métodos necesarios para esta misma clase.
 *
 * @author Felix_isq
 */
public class ProyectoRepositorio extends RepositorioBase implements IProyectoRepositorio {

    /**
     * Método para guardar un objeto de la entidad Proyecto en la base de datos.
     * 
     * @param proyecto Objeto del tipo Proyecto a guardar en la base de datos 
     */
    @Override
    public void guardar(Proyecto proyecto) {
        // Utiliza el método heredado ejecutar(), con la operación persist(),
        // recibiendo el objeto proyecto para insertarlo
        ejecutar(em -> em.persist(proyecto));
    }

    /**
     * Método para buscar un proyecto, utilizando su ID, en la base de datos.
     * 
     * @param id Valor numerico que representa el ID del proyecto a buscar
     * @return Objeto del tipo Proyecto (o null) como resultado de la busqueda
     */
    @Override
    public Proyecto buscarPorId(Long id) {
        // Utiliza el método heredado consultar(), con la operación find(),
        // recibiendo la clase del objeto que regresará (donde, en caso de no
        // encontrar coincidencias, devolverá null) y el ID que lo identifica
        return consultar(em -> em.find(Proyecto.class, id));
    }

    /**
     * Método para obtener la lista completa de los proyectos almacenados en
     * la base de datos.
     * 
     * @return Lista de objetos del tipo Proyecto
     */
    @Override
    public List<Proyecto> listarProyectos() {
        // Utiliza consultar(), con la operación createQuery(). Obtiene objetos
        // de la clase Proyecto y devuelve los resultados en una lista.
        return consultar(em -> em.createQuery("SELECT p FROM Proyecto p", Proyecto.class).getResultList());
    }

    /**
     * Método para obtener la lista de proyectos de un usuario específico,
     * utilizando el ID del usuario, desde la base de datos.
     * 
     * @param idUsuario valor numerico que representa el ID del usuario
     * @return Lista de objetos del tipo Proyecto
     */
    @Override
    public List<Proyecto> listarPorUsuario(Long idUsuario) {
        // Utiliza consultar(), con la operación createQuery(). Obtiene objetos
        // de la clase Proyecto, asigna el id del parametro a la sentencia
        // y devuelve los resultados en una lista.
        return consultar(em -> em.createQuery(
                "SELECT p FROM Proyecto p WHERE p.usuario.id = :idUsuario", Proyecto.class)
                .setParameter("idUsuario", idUsuario)
                .getResultList());
    }

    /**
     * Método para obtener la lista de proyectos de una categoria específica,
     * utilizando el nombre de la categoria, desde la base de datos.
     * 
     * @param categoria valor textual que representa el nombre de la categoria
     * @return Lista de objetos del tipo Proyecto
     */
    @Override
    public List<Proyecto> buscarPorCategoria(String categoria) {
        // Utiliza consultar(), con la operación createQuery(). Obtiene objetos
        // de la clase Proyecto, asigna el valor del parametro a la sentencia
        // y devuelve los resultados en una lista.
        return consultar(em -> em.createQuery(
                "SELECT p FROM Proyecto p WHERE p.categoria = :categoria", Proyecto.class)
                .setParameter("categoria", categoria)
                .getResultList());
    }

    /**
     * Método para actualizar el estado del proyecto. Permite actualizar
     * el estado, desde ACTIVO, hasta FINALIZADO y CANCELADO. Utiliza el
     * ID del proyecto para realizar esta actualizacion
     * 
     * @param id valor numerico que representa el ID del proyecto
     * @param estado valor del tipo EstadoProyecto (enumerado) que representa el estado del proyecto a actualizar
     */
    @Override
    public void actualizarEstado(Long id, EstadoProyecto estado) {
        // Usa ejecutar(), buscando el proyecto, usando su ID, y actualizando
        // su estado (no se actualiza nada en caso que la busqueda resulte nula)
        ejecutar(em -> {
            Proyecto proyecto = em.find(Proyecto.class, id);
            if (proyecto != null) {
                proyecto.setEstado(estado);
            }
        });
    }
}
