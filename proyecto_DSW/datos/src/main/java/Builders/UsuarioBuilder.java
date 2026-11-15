package Builders;

import entidades.Usuario;
import enumeradores.EstadoVerificacion;

/**
 * Clase Builder para la entidad Usuario. Facilita la creación de objetos del
 * tipo Usuario, permitiendo asignar sus atributos de forma ordenada.
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

    /**
     * Método para asignar el atributo del ID al objeto del tipo Usuario. Al
     * regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param id Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public UsuarioBuilder id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Método para asignar el atributo de los nombres al objeto del tipo
     * Usuario. Al regresar la instancia de este mismo Builder, se puede
     * encadenar con varios métodos de manera consecutiva.
     * 
     * @param nombres Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public UsuarioBuilder nombres(String nombres) {
        this.nombres = nombres;
        return this;
    }

    /**
     * Método para asignar el atributo del apellido paterno al objeto del tipo
     * Usuario. Al regresar la instancia de este mismo Builder, se puede
     * encadenar con varios métodos de manera consecutiva.
     * 
     * @param apellidoPaterno Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public UsuarioBuilder apellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
        return this;
    }

    /**
     * Método para asignar el atributo del apellido materno al objeto del tipo
     * Usuario. Al regresar la instancia de este mismo Builder, se puede
     * encadenar con varios métodos de manera consecutiva.
     * 
     * @param apellidoMaterno Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public UsuarioBuilder apellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
        return this;
    }

    /**
     * Método para asignar el atributo del nombre de usuario al objeto del tipo
     * Usuario. Al regresar la instancia de este mismo Builder, se puede
     * encadenar con varios métodos de manera consecutiva.
     * 
     * @param nombreUsuario Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public UsuarioBuilder nombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        return this;
    }

    /**
     * Método para asignar el atributo del correo al objeto del tipo Usuario.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param correo Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public UsuarioBuilder correo(String correo) {
        this.correo = correo;
        return this;
    }

    /**
     * Método para asignar el atributo de la contraseña al objeto del tipo
     * Usuario. Al regresar la instancia de este mismo Builder, se puede
     * encadenar con varios métodos de manera consecutiva.
     * 
     * @param contrasenia Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public UsuarioBuilder contrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
        return this;
    }

    /**
     * Método para asignar el atributo del telefono al objeto del tipo Usuario.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param telefono Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public UsuarioBuilder telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    /**
     * Método para asignar el atributo del número de cedula profesional al
     * objeto del tipo Usuario. Al regresar la instancia de este mismo Builder,
     * se puede encadenar con varios métodos de manera consecutiva.
     * 
     * @param numeroCedulaProfesional Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public UsuarioBuilder numeroCedulaProfesional(String numeroCedulaProfesional) {
        this.numeroCedulaProfesional = numeroCedulaProfesional;
        return this;
    }

    /**
     * Método para asignar el atributo del estado de verificacion para la
     * postulación como freelancer al objeto del tipo Usuario. Al regresar la
     * instancia de este mismo Builder, se puede encadenar con varios métodos
     * de manera consecutiva.
     * 
     * @param estadoVerificacion Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public UsuarioBuilder estadoVerificacion(EstadoVerificacion estadoVerificacion) {
        this.estadoVerificacion = estadoVerificacion;
        return this;
    }

    /**
     * Método para construir y devolver el objeto Usuario con los valores definidos
     * anteriormente. Crea una instancia nueva de la clase Usuario, le asigna los
     * valores guardados en esta misma clase y devuelve el objeto completamente
     * construido.
     * 
     * @return Objeto completo y construido del tipo Usuario
     */
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
