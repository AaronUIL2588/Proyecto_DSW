/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Usuario;
import java.util.List;

/**
 * Operaciones de base de datos para la entidad Usuario.
 *
 * @author Felix_isq
 */
public interface IUsuarioRepositorio {

    void guardar(Usuario usuario);

    Usuario buscarPorId(Long id);

    Usuario buscarPorCorreo(String correo);

    List<Usuario> listarUsuarios();

    void actualizar(Usuario usuario);

    void eliminar(Long id);
}
