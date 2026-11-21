package interfaces;

import entidades.Hito;
import enumeradores.EstadoHito;
import java.util.List;

/**
 * Operaciones de base de datos para la entidad Hito.
 *
 * @author Felix_isq
 */
public interface IHitoRepositorio extends IRepositorioBase<Hito> {

    List<Hito> listarPorProyecto(Long idProyecto);

    void actualizarEstado(Long id, EstadoHito estado);
}