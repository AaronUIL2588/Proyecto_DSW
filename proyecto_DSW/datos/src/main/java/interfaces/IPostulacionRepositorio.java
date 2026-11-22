package interfaces;

import entidades.Postulacion;
import java.util.List;

/**
 * Operaciones de base de datos para la entidad Postulacion.
 *
 * @author Felix_isq
 */
public interface IPostulacionRepositorio extends IRepositorioBase<Postulacion> {

    List<Postulacion> listarPorProyecto(Long idProyecto);

    List<Postulacion> listarPorUsuario(Long idUsuario);

    boolean existePostulacion(Long idUsuario, Long idProyecto);
}