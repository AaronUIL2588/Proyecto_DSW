/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Builders;

import entidades.Usuario;
import enumeradores.EstadoVerificacion;

/**
 * Builder de la entidad Usuario. Sustituye a los constructores con muchos
 * parámetros: se encadenan solo los datos que se necesiten y al final se llama
 * a build().
 *
 * @author Felix_isq
 */
public class UsuarioBuilder {

    private Long id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreUsuario;
    private String correo;
    private String contrasenia;
    private String telefono;
    private String numeroCedulaProfesional;
    private EstadoVerificacion estadoVerificacion;

    public UsuarioBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder nombres(String nombres) {
        this.nombres = nombres;
        return this;
    }

    public UsuarioBuilder apellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
        return this;
    }

    public UsuarioBuilder apellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
        return this;
    }

    public UsuarioBuilder nombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        return this;
    }

    public UsuarioBuilder correo(String correo) {
        this.correo = correo;
        return this;
    }

    public UsuarioBuilder contrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
        return this;
    }

    public UsuarioBuilder telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public UsuarioBuilder numeroCedulaProfesional(String numeroCedulaProfesional) {
        this.numeroCedulaProfesional = numeroCedulaProfesional;
        return this;
    }

    public UsuarioBuilder estadoVerificacion(EstadoVerificacion estadoVerificacion) {
        this.estadoVerificacion = estadoVerificacion;
        return this;
    }

    public Usuario build() {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombres(nombres);
        usuario.setApellidoPaterno(apellidoPaterno);
        usuario.setApellidoMaterno(apellidoMaterno);
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setCorreo(correo);
        usuario.setContrasenia(contrasenia);
        usuario.setTelefono(telefono);
        usuario.setNumeroCedulaProfesional(numeroCedulaProfesional);
        usuario.setEstadoVerificacion(estadoVerificacion);
        return usuario;
    }
}
