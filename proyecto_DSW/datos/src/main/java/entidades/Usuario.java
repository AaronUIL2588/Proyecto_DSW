package entidades;

import enumeradores.EstadoVerificacion;
import java.io.Serializable;
import java.util.ArrayList;
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

    @Column(name = "numero_cedula")
    private String numeroCedulaProfesional;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_verificacion")
    private EstadoVerificacion estadoVerificacion;

    /**
     * Postulaciones que este usuario ha enviado (como freelancer).
     */
    @OneToMany(mappedBy = "usuario")
    private List<Postulacion> postulacion = new ArrayList<>();

    /**
     * Aportaciones que este usuario ha hecho a proyectos.
     */
    @OneToMany(mappedBy = "usuario")
    private List<Aportacion> aportacion = new ArrayList<>();

    /**
     * Proyectos que este usuario ha publicado (como cliente / dueño).
     */
    @OneToMany(mappedBy = "usuario")
    private List<Proyecto> proyecto = new ArrayList<>();

    public Usuario() {
    }

    /**
     * Agrega un proyecto publicado por este usuario y deja ambos lados
     * sincronizados.
     */
    public void agregarProyecto(Proyecto p) {
        if (!proyecto.contains(p)) {
            proyecto.add(p);
        }
        p.setUsuario(this);
    }

    /**
     * Agrega una postulación enviada por este usuario y deja ambos lados
     * sincronizados.
     */
    public void agregarPostulacion(Postulacion p) {
        if (!postulacion.contains(p)) {
            postulacion.add(p);
        }
        p.setUsuario(this);
    }

    /**
     * Agrega una aportación hecha por este usuario y deja ambos lados
     * sincronizados.
     */
    public void agregarAportacion(Aportacion a) {
        if (!aportacion.contains(a)) {
            aportacion.add(a);
        }
        a.setUsuario(this);
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
