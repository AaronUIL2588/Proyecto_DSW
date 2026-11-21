package Builders;

import entidades.Hito;
import entidades.Proyecto;
import entidades.Usuario;
import enumeradores.EstadoProyecto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Builder para la entidad Proyecto. Facilita la creación de objetos del
 * tipo Proyecto, permitiendo asignar sus atributos de forma ordenada.
 *
 * @author Felix_isq
 */
public class ProyectoBuilder {

    private Long id;
    private String titulo;
    private String descripcion;
    private String categoria;
    private int cantidadFreelancer;
    private EstadoProyecto estado = EstadoProyecto.ACTIVO;
    private LocalDate fechaCreacion = LocalDate.now();
    private String contacto;
    private Usuario usuario;
    private final List<Hito> hitos = new ArrayList<>();
    
    /**
     * Método para asignar el atributo de las notas al objeto del tipo Proyecto.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param id Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public ProyectoBuilder id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Método para asignar el atributo del ID al objeto del tipo Proyecto.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param titulo Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public ProyectoBuilder titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    /**
     * Método para asignar el atributo de la descripcion al objeto del tipo
     * Proyecto. Al regresar la instancia de este mismo Builder, se puede
     * encadenar con varios métodos de manera consecutiva.
     * 
     * @param descripcion Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public ProyectoBuilder descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    /**
     * Método para asignar el atributo de la categoria al objeto del tipo
     * Proyecto. Al regresar la instancia de este mismo Builder, se puede
     * encadenar con varios métodos de manera consecutiva.
     * 
     * @param categoria Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public ProyectoBuilder categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    /**
     * Método para asignar el atributo de la cantidad de freelancers al objeto
     * del tipo Proyecto. Al regresar la instancia de este mismo Builder, se
     * puede encadenar con varios métodos de manera consecutiva.
     * 
     * @param cantidadFreelancer Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public ProyectoBuilder cantidadFreelancer(int cantidadFreelancer) {
        this.cantidadFreelancer = cantidadFreelancer;
        return this;
    }

    /**
     * Método para asignar el atributo del estado al objeto del tipo Proyecto.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param estado Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public ProyectoBuilder estado(EstadoProyecto estado) {
        this.estado = estado;
        return this;
    }

    /**
     * Método para asignar el atributo de la fecha de creacion al objeto del
     * tipo Proyecto. Al regresar la instancia de este mismo Builder, se puede
     * encadenar con varios métodos de manera consecutiva.
     * 
     * @param fechaCreacion Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public ProyectoBuilder fechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    /**
     * Método para asignar el atributo de contacto al objeto del tipo Proyecto.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param contacto Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public ProyectoBuilder contacto(String contacto) {
        this.contacto = contacto;
        return this;
    }

    /**
     * Método para asignar el atributo del usuario al objeto del tipo Proyecto.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param usuario Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public ProyectoBuilder usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    /**
     * Método para agregar el atributo del hito a la lista dal objeto del
     * tipo Proyecto. Al regresar la instancia de este mismo Builder, se puede
     * encadenar con varios métodos de manera consecutiva.
     * 
     * @param hito Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public ProyectoBuilder hito(Hito hito) {
        this.hitos.add(hito);
        return this;
    }

    /**
     * Método para construir y devolver el objeto Proyecto con los valores definidos
     * anteriormente. Crea una instancia nueva de la clase Proyecto, le asigna los
     * valores guardados en esta misma clase y devuelve el objeto completamente
     * construido.
     * 
     * @return Objeto completo y construido del tipo Proyecto
     */
    public Proyecto build() {
        Proyecto proyecto = new Proyecto();
        proyecto.setId(id);
        proyecto.setTitulo(titulo);
        proyecto.setDescripcion(descripcion);
        proyecto.setCategoria(categoria);
        proyecto.setCantidadFreelancer(cantidadFreelancer);
        proyecto.setEstado(estado);
        proyecto.setFechaCreacion(fechaCreacion);
        proyecto.setContacto(contacto);
        proyecto.setUsuario(usuario);
        for (Hito hito : hitos) {
            hito.setProyecto(proyecto);
            proyecto.getListaHitos().add(hito);
        }
        return proyecto;
    }
}
