package repositorios;

import interfaces.IPostulacionRepositorio;
import entidades.Postulacion;
import java.util.List;

/**
 * Clase utilizada para operaciones hechas a la clase/entidad Postulacion.
 * Hereda los métodos de la clase RepositorioBase, e implementa su propia
 * interfaz con los métodos necesarios para esta misma clase.
 *
 * @author Felix_isq
 */
public class PostulacionRepositorio extends RepositorioBase<Postulacion> implements IPostulacionRepositorio {

    public PostulacionRepositorio() {
        super(Postulacion.class);
    }

    /**
     * Método para obtener la lista de postulaciones de un proyecto específico,
     * utilizando el ID del proyecto, desde la base de datos.
     * 
     * @param idProyecto Valor numerico que representa el ID del proyecto
     * @return Lista de objetos del tipo Postulacion
     */
    @Override
    public List<Postulacion> listarPorProyecto(Long idProyecto) {
        // Utiliza consultar(), con la operación createQuery(), creando la
        // sentencia específica para la busqueda de las postulaciones de los
        // proyectos. Obtiene objetos de la clase Postulacion, asigna el id
        // del parametro a la sentencia y devuelve los resultados en una lista.
        return consultar(em -> em.createQuery(
                "SELECT p FROM Postulacion p WHERE p.proyecto.id = :idProyecto", Postulacion.class)
                .setParameter("idProyecto", idProyecto)
                .getResultList());
    }

    /**
     * Método para obtener la lista de postulaciones de un usuario específico,
     * utilizando el ID del usuario, desde la base de datos.
     * 
     * @param idUsuario valor numerico que representa el ID del usuario
     * @return Lista de objetos del tipo Postulacion
     */
    @Override
    public List<Postulacion> listarPorUsuario(Long idUsuario) {
        // Utiliza consultar(), con la operación createQuery(). Obtiene objetos
        // de la clase Postulacion, asigna el id del parametro a la sentencia
        // y devuelve los resultados en una lista.
        return consultar(em -> em.createQuery(
                "SELECT p FROM Postulacion p WHERE p.usuario.id = :idUsuario", Postulacion.class)
                .setParameter("idUsuario", idUsuario)
                .getResultList());
    }

    

    /**
     * Método utilizado para validar si la postulacion existe, usando los
     * IDs del usuario y el proyecto relacionado con la postulacion. Permite
     * limitar a los usuarios, donde solo pueden postularse al mismo proyecto
     * una sola vez.
     * 
     * @param idUsuario valor numerico que representa el ID del usuario a comprobar
     * @param idProyecto valor numerico que representa el ID del proyecto relacionado con la postulacion
     * @return valor booleano que define si el usuario del parametro ya cuenta con una postulacion en el proyecto del parametro
     */
    @Override
    public boolean existePostulacion(Long idUsuario, Long idProyecto) {
        // Utiliza consultar(), con la operación createQuery(). Obtiene el
        // resultado del conteo de las postulaciones (del tipo Long),
        // relacionadas con el usuario y el proyecto definido como parametro,
        // asigna los ids del parametro a la sentencia y devuelve el resultado.
        Long cantidad = consultar(em -> em.createQuery(
                "SELECT COUNT(p) FROM Postulacion p WHERE p.usuario.id = :idUsuario AND p.proyecto.id = :idProyecto",
                Long.class)
                .setParameter("idUsuario", idUsuario)
                .setParameter("idProyecto", idProyecto)
                .getSingleResult());
        
        // Devuelve un valor booleano, true si la cantidad de postulaciones es
        // mayor que 0, false en caso contrario.
        return cantidad > 0;
    }
}