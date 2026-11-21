package interfaces;

import entidades.Postulacion;
import enumeradores.EstadoPostulacion;
import java.util.List;

/**
 * Operaciones de base de datos para la entidad Postulacion.
 *
 * @author Felix_isq
 */
public interface IPostulacionRepositorio extends IRepositorioBase<Postulacion> {

    List<Postulacion> listarPorProyecto(Long idProyecto);

    List<Postulacion> listarPorUsuario(Long idUsuario);

    void actualizarEstado(Long id, EstadoPostulacion estado);

    boolean existePostulacion(Long idUsuario, Long idProyecto);
}