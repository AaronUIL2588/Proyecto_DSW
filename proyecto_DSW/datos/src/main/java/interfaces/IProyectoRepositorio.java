package interfaces;

import entidades.Proyecto;
import java.util.List;

/**
 * Operaciones de base de datos para la entidad Proyecto. Al guardar un proyecto
 * se guardan también sus hitos.
 *
 * @author Felix_isq
 */
public interface IProyectoRepositorio extends IRepositorioBase<Proyecto> {

    List<Proyecto> listarProyectos();

    List<Proyecto> listarPorUsuario(Long idUsuario);

    List<Proyecto> buscarPorCategoria(String categoria);

    
}