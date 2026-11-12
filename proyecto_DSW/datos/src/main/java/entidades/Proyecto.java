package entidades;

import enumeradores.EstadoPostulacion;
import enumeradores.EstadoProyecto;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase de la entidad Proyecto
 *
 * @author aron
 */
@Entity
@Table(name = "proyecto")
public class Proyecto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id_proyecto")
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "cantidad_freelancer", nullable = false)
    private int cantidadFreelancer;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoProyecto estado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "contacto", nullable = false)
    private String contacto;

    /**
     * Dueño (cliente) que publicó el proyecto.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "proyecto")
    private List<Postulacion> listaPostulaciones = new ArrayList<>();

    /**
     * Los hitos solo existen dentro de un proyecto: se guardan y borran con él.
     */
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hito> listaHitos = new ArrayList<>();

    @OneToMany(mappedBy = "proyecto")
    private List<Aportacion> listaAportaciones = new ArrayList<>();

    public Proyecto() {
    }

    /**
     * Agrega un hito al proyecto y deja ambos lados de la relación
     * sincronizados.
     */
    public void agregarHito(Hito hito) {
        if (!listaHitos.contains(hito)) {
            listaHitos.add(hito);
        }
        hito.setProyecto(this);
    }

    /**
     * Quita un hito del proyecto (orphanRemoval lo borra de la base de datos).
     */
    public void quitarHito(Hito hito) {
        listaHitos.remove(hito);
        hito.setProyecto(null);
    }

    /**
     * Agrega una postulación recibida y deja ambos lados sincronizados.
     */
    public void agregarPostulacion(Postulacion postulacion) {
        if (!listaPostulaciones.contains(postulacion)) {
            listaPostulaciones.add(postulacion);
        }
        postulacion.setProyecto(this);
    }

    /**
     * Agrega una aportación recibida y deja ambos lados sincronizados.
     */
    public void agregarAportacion(Aportacion aportacion) {
        if (!listaAportaciones.contains(aportacion)) {
            listaAportaciones.add(aportacion);
        }
        aportacion.setProyecto(this);
    }

    /**
     * Freelancers asignados al proyecto: los usuarios cuya postulación fue
     * aceptada. No es una columna; se calcula a partir de las postulaciones.
     */
    public List<Usuario> obtenerFreelancersAsignados() {
        List<Usuario> freelancers = new ArrayList<>();
        for (Postulacion p : listaPostulaciones) {
            if (p.getEstadoPostulacion() == EstadoPostulacion.ACEPTADA) {
                freelancers.add(p.getUsuario());
            }
        }
        return freelancers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCantidadFreelancer() {
        return cantidadFreelancer;
    }

    public void setCantidadFreelancer(int cantidadFreelancer) {
        this.cantidadFreelancer = cantidadFreelancer;
    }

    public EstadoProyecto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProyecto estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Postulacion> getListaPostulaciones() {
        return listaPostulaciones;
    }

    public void setListaPostulaciones(List<Postulacion> listaPostulaciones) {
        this.listaPostulaciones = listaPostulaciones;
    }

    public List<Hito> getListaHitos() {
        return listaHitos;
    }

    public void setListaHitos(List<Hito> listaHitos) {
        this.listaHitos = listaHitos;
    }

    public List<Aportacion> getListaAportaciones() {
        return listaAportaciones;
    }

    public void setListaAportaciones(List<Aportacion> listaAportaciones) {
        this.listaAportaciones = listaAportaciones;
    }
}
