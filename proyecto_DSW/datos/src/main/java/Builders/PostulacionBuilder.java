package Builders;

import entidades.Postulacion;
import entidades.Proyecto;
import entidades.Usuario;
import enumeradores.EstadoPostulacion;
import java.time.LocalDate;

/**
 * Clase Builder para la entidad Postulacion. Facilita la creación de objetos
 * del tipo Postulacion, permitiendo asignar sus atributos de forma ordenada.
 * 
 * @author Felix_isq
 */
public class PostulacionBuilder {

    private Long id;
    private String mensaje;
    private LocalDate fecha = LocalDate.now();
    private EstadoPostulacion estadoPostulacion = EstadoPostulacion.PENDIENTE;
    private Usuario usuario;
    private Proyecto proyecto;

    /**
     * Método para asignar el atributo del ID al objeto del tipo Postulacion.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param id Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public PostulacionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Método para asignar el atributo del mensaje al objeto del tipo
     * Postulacion. Al regresar la instancia de este mismo Builder, se puede
     * encadenar con varios métodos de manera consecutiva.
     * 
     * @param mensaje Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public PostulacionBuilder mensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    /**
     * Método para asignar el atributo de la fecha al objeto del tipo
     * Postulacion. Al regresar la instancia de este mismo Builder, se puede
     * encadenar con varios métodos de manera consecutiva.
     * 
     * @param fecha Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public PostulacionBuilder fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    /**
     * Método para asignar el atributo del estado de la postulacion al objeto
     * del tipo Postulacion. Al regresar la instancia de este mismo Builder,
     * se puede encadenar con varios métodos de manera consecutiva.
     * 
     * @param estadoPostulacion Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public PostulacionBuilder estadoPostulacion(EstadoPostulacion estadoPostulacion) {
        this.estadoPostulacion = estadoPostulacion;
        return this;
    }

    /**
     * Método para asignar el atributo del usuario al objeto del tipo
     * Postulacion. Al regresar la instancia de este mismo Builder, se puede
     * encadenar con varios métodos de manera consecutiva.
     * 
     * @param usuario Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public PostulacionBuilder usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    /**
     * Método para asignar el atributo del proyecto al objeto del tipo
     * Postulacion. Al regresar la instancia de este mismo Builder, se puede
     * encadenar con varios métodos de manera consecutiva.
     * 
     * @param proyecto Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public PostulacionBuilder proyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        return this;
    }

    /**
     * Método para construir y devolver el objeto Postulacion con los valores
     * definidos anteriormente. Crea una instancia nueva de la clase
     * Postulacion, le asigna los valores guardados en esta misma clase y
     * devuelve el objeto completamente construido.
     * 
     * @return Objeto completo y construido del tipo Postulacion
     */
    public Postulacion build() {
        Postulacion postulacion = new Postulacion();
        postulacion.setId(id);
        postulacion.setMensaje(mensaje);
        postulacion.setFecha(fecha);
        postulacion.setEstadoPostulacion(estadoPostulacion);
        postulacion.setUsuario(usuario);
        postulacion.setProyecto(proyecto);
        return postulacion;
    }
}
