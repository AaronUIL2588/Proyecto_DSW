/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Usuario;

/**
 * Interfaz utilizada para la clase UsuarioServicio
 *
 * @author Felix_isq
 */
public interface IUsuarioServicio {

     Usuario registrarUsuario(Usuario usuario);

    Usuario iniciarSesion(String correo, String contrasenia);

    Usuario obtenerPerfil(Long idUsuario);

    void actualizarPerfil(Usuario usuario);

    boolean verificarCedula(String numero);

    boolean solicitarVerificacionFreelancer(Long idUsuario, String numeroCedula);

}
