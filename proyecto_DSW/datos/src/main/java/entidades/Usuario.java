package entidades;

import enumeradores.EstadoVerificacion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase de la entidad Usuario
 *
 * @author aron
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;

    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    @Column(name = "telefono", nullable = false)
    private String telefono;

//    @Column(name = "numero_cedula", nullable = false)
//    private String numeroCedulaProfesional;
//    
//    @Enumerated(EnumType.STRING)
//    @Column(name = "estado_verificacion", nullable = false)
//    private EstadoVerificacion estadoVerificacion;
    @Column(name = "numero_cedula")
    private String numeroCedulaProfesional;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_verificacion")
    private EstadoVerificacion estadoVerificacion;
    
    @OneToMany(mappedBy = "usuario")
    private List<Postulacion> postulacion;

    @OneToMany(mappedBy = "usuario")
    private List<Aportacion> aportacion;

    @OneToMany(mappedBy = "usuario")
    private List<Proyecto> proyecto;

    public Usuario() {
    }

    public Usuario(Long id, String nombres, String apellidoPaterno, String apellidoMaterno, String nombreUsuario, String correo, String contrasenia, String telefono, String numeroCedulaProfesional, EstadoVerificacion estadoVerificacion) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.numeroCedulaProfesional = numeroCedulaProfesional;
        this.estadoVerificacion = estadoVerificacion;
    }

    public Usuario(String nombres, String apellidoPaterno, String apellidoMaterno, String nombreUsuario, String correo, String contrasenia, String telefono, String numeroCedulaProfesional, EstadoVerificacion estadoVerificacion) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.numeroCedulaProfesional = numeroCedulaProfesional;
        this.estadoVerificacion = estadoVerificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNumeroCedulaProfesional() {
        return numeroCedulaProfesional;
    }

    public void setNumeroCedulaProfesional(String numeroCedulaProfesional) {
        this.numeroCedulaProfesional = numeroCedulaProfesional;
    }

    public EstadoVerificacion getEstadoVerificacion() {
        return estadoVerificacion;
    }

    public void setEstadoVerificacion(EstadoVerificacion estadoVerificacion) {
        this.estadoVerificacion = estadoVerificacion;
    }

    public List<Postulacion> getPostulacion() {
        return postulacion;
    }

    public void setPostulacion(List<Postulacion> postulacion) {
        this.postulacion = postulacion;
    }

    public List<Aportacion> getAportacion() {
        return aportacion;
    }

    public void setAportacion(List<Aportacion> aportacion) {
        this.aportacion = aportacion;
    }

    public List<Proyecto> getProyecto() {
        return proyecto;
    }

    public void setProyecto(List<Proyecto> proyecto) {
        this.proyecto = proyecto;
    }
}
