package entidades;

import enumeradores.EstadoPostulacion;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Clase de la entidad Postulacion
 *
 * @author aron
 */
@Entity
public class Postulacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id_postulacion")
    private Long id;
    
    @Column(name = "mensaje", nullable = false)
    private String mensaje;
    
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_verificacion", nullable = false)
    private EstadoPostulacion estadoPostulacion;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;

    public Postulacion() {}

    public Postulacion(Long id, String mensaje, LocalDate fecha, EstadoPostulacion estadoPostulacion) {
        this.id = id;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.estadoPostulacion = estadoPostulacion;
    }

    public Postulacion(String mensaje, LocalDate fecha, EstadoPostulacion estadoPostulacion) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.estadoPostulacion = estadoPostulacion;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getMensaje() {return mensaje;}
    public void setMensaje(String mensaje) {this.mensaje = mensaje;}

    public LocalDate getFecha() {return fecha;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

    public EstadoPostulacion getEstadoPostulacion() {return estadoPostulacion;}
    public void setEstadoPostulacion(EstadoPostulacion estadoPostulacion) {this.estadoPostulacion = estadoPostulacion;}

    public Usuario getUsuario() {return usuario;}
    public void setUsuario(Usuario usuario) {this.usuario = usuario;}

    public Proyecto getProyecto() {return proyecto;}
    public void setProyecto(Proyecto proyecto) {this.proyecto = proyecto;}
}