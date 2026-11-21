package repositorios;

import interfaces.IUsuarioRepositorio;
import entidades.Usuario;
import java.util.List;

/**
 * Implementación con JPA de IUsuarioRepositorio.
 *
 * @author Felix_isq
 */
public class UsuarioRepositorio extends RepositorioBase<Usuario> implements IUsuarioRepositorio {

    public UsuarioRepositorio() {
        super(Usuario.class);
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {
        return consultar(em -> {
            List<Usuario> resultado = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class)
                    .setParameter("correo", correo)
                    .getResultList();
            return resultado.isEmpty() ? null : resultado.get(0);
        });
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return consultar(em -> em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList());
    }

    
}