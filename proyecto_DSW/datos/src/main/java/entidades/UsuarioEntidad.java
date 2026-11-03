package entidades;

import enumeradores.EstadoVerificacion;
import java.io.Serializable;
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
public class UsuarioEntidad implements Serializable {
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
    
    @Column(name = "numero_cedula", nullable = false)
    private String numeroCedulaProfesional;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_verificacion", nullable = false)
    private EstadoVerificacion estadoVerificacion;
    
    @OneToMany(mappedBy = "usuario")
    private PostulacionEntidad postulacion;
    
    @OneToMany(mappedBy = "usuario")
    private AportacionEntidad aportacion;
    
    @OneToMany(mappedBy = "usuario")
    private ProyectoEntidad proyecto;

    public UsuarioEntidad() {}

    public UsuarioEntidad(Long id, String nombres, String apellidoPaterno, String apellidoMaterno, String nombreUsuario, String correo, String contrasenia, String telefono, String numeroCedulaProfesional, EstadoVerificacion estadoVerificacion) {
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

    public UsuarioEntidad(String nombres, String apellidoPaterno, String apellidoMaterno, String nombreUsuario, String correo, String contrasenia, String telefono, String numeroCedulaProfesional, EstadoVerificacion estadoVerificacion) {
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

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNombres() {return nombres;}
    public void setNombres(String nombres) {this.nombres = nombres;}

    public String getApellidoPaterno() {return apellidoPaterno;}
    public void setApellidoPaterno(String apellidoPaterno) {this.apellidoPaterno = apellidoPaterno;}

    public String getApellidoMaterno() {return apellidoMaterno;}
    public void setApellidoMaterno(String apellidoMaterno) {this.apellidoMaterno = apellidoMaterno;}

    public String getNombreUsuario() {return nombreUsuario;}
    public void setNombreUsuario(String nombreUsuario) {this.nombreUsuario = nombreUsuario;}

    public String getCorreo() {return correo;}
    public void setCorreo(String correo) {this.correo = correo;}

    public String getContrasenia() {return contrasenia;}
    public void setContrasenia(String contrasenia) {this.contrasenia = contrasenia;}

    public String getTelefono() {return telefono;}
    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getNumeroCedulaProfesional() {return numeroCedulaProfesional;}
    public void setNumeroCedulaProfesional(String numeroCedulaProfesional) {this.numeroCedulaProfesional = numeroCedulaProfesional;}

    public EstadoVerificacion getEstadoVerificacion() {return estadoVerificacion;}
    public void setEstadoVerificacion(EstadoVerificacion estadoVerificacion) {this.estadoVerificacion = estadoVerificacion;}

    public PostulacionEntidad getPostulacion() {return postulacion;}
    public void setPostulacion(PostulacionEntidad postulacion) {this.postulacion = postulacion;}

    public AportacionEntidad getAportacion() {return aportacion;}
    public void setAportacion(AportacionEntidad aportacion) {this.aportacion = aportacion;}

    public ProyectoEntidad getProyecto() {return proyecto;}
    public void setProyecto(ProyectoEntidad proyecto) {this.proyecto = proyecto;}
}