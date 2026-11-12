/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import entidades.Usuario;
import java.util.List;

/**
 * Implementación con JPA de IUsuarioRepositorio.
 *
 * @author Felix_isq
 */
public class UsuarioRepositorio extends RepositorioBase implements IUsuarioRepositorio {

    @Override
    public void guardar(Usuario usuario) {
        ejecutar(em -> em.persist(usuario));
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return consultar(em -> em.find(Usuario.class, id));
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

    @Override
    public void actualizar(Usuario usuario) {
        ejecutar(em -> em.merge(usuario));
    }

    @Override
    public void eliminar(Long id) {
        ejecutar(em -> {
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.remove(usuario);
            }
        });
    }
}
