package interfaces;

import entidades.Aportacion;
import java.util.List;

/**
 * Operaciones de base de datos para la entidad Aportacion.
 *
 * @author Felix_isq
 */
public interface IAportacionRepositorio extends IRepositorioBase<Aportacion> {

    List<Aportacion> listarPorProyecto(Long idProyecto);

    List<Aportacion> listarPorUsuario(Long idUsuario);

    double obtenerTotalRecaudado(Long idProyecto);
}