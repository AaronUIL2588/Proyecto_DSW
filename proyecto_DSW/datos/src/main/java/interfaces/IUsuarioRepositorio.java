package interfaces;

import entidades.Usuario;
import java.util.List;

/**
 * Operaciones de base de datos para la entidad Usuario.
 *
 * @author Felix_isq
 */
public interface IUsuarioRepositorio extends IRepositorioBase<Usuario> {

    Usuario buscarPorCorreo(String correo);

    List<Usuario> listarUsuarios();
}