package interfaces;

import entidades.Hito;
import java.util.List;

/**
 * Operaciones de base de datos para la entidad Hito.
 *
 * @author Felix_isq
 */
public interface IHitoRepositorio extends IRepositorioBase<Hito> {

    List<Hito> listarPorProyecto(Long idProyecto);

}