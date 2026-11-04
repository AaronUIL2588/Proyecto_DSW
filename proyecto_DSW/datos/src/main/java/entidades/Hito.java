package entidades;

import enumeradores.EstadoHito;
import java.io.Serializable;
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
 * Clase de la entidad Hito
 *
 * @author aron
 */
@Entity
public class Hito implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id_hito")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoHito estado;
    
    @Column(name = "notas", nullable = false)
    private String notas;
    
    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;

    public Hito() {}

    public Hito(Long id, String nombre, String descripcion, EstadoHito estado, String notas, Proyecto proyecto) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.notas = notas;
        this.proyecto = proyecto;
    }

    public Hito(String nombre, String descripcion, EstadoHito estado, String notas, Proyecto proyecto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.notas = notas;
        this.proyecto = proyecto;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public EstadoHito getEstado() {return estado;}
    public void setEstado(EstadoHito estado) {this.estado = estado;}

    public String getNotas() {return notas;}
    public void setNotas(String notas) {this.notas = notas;}

    public Proyecto getProyecto() {return proyecto;}
    public void setProyecto(Proyecto proyecto) {this.proyecto = proyecto;}
}