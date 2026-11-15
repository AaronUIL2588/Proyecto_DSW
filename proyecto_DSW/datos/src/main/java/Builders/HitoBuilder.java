package Builders;

import entidades.Hito;
import entidades.Proyecto;
import enumeradores.EstadoHito;

/**
 * Clase Builder para la entidad Hito. Facilita la creación de objetos del
 * tipo Hito, permitiendo asignar sus atributos de forma ordenada.
 *
 * @author Felix_isq
 */
public class HitoBuilder {

    private Long id;
    private String nombre;
    private String descripcion;
    private EstadoHito estado = EstadoHito.PENDIENTE;
    private String notas;
    private Proyecto proyecto;

    /**
     * Método para asignar el atributo del ID al objeto del tipo Hito. Al
     * regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param id Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public HitoBuilder id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Método para asignar el atributo del nombre al objeto del tipo Hito. Al
     * regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param nombre Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public HitoBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    /**
     * Método para asignar el atributo de la descripción al objeto del
     * tipo Hito. Al regresar la instancia de este mismo Builder, se puede
     * encadenar con varios métodos de manera consecutiva.
     * 
     * @param descripcion Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public HitoBuilder descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    /**
     * Método para asignar el atributo del estado al objeto del tipo Hito.
     * Al regresar la instancia de este mismo Builder, se puede encadenar
     * con varios métodos de manera consecutiva.
     * 
     * @param estado Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public HitoBuilder estado(EstadoHito estado) {
        this.estado = estado;
        return this;
    }

    /**
     * Método para asignar el atributo de las notas al objeto del tipo Hito.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param notas Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public HitoBuilder notas(String notas) {
        this.notas = notas;
        return this;
    }

    /**
     * Método para asignar el atributo del proyecto al objeto del tipo Hito.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param proyecto Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public HitoBuilder proyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        return this;
    }

    /**
     * Método para construir y devolver el objeto Hito con los valores definidos
     * anteriormente. Crea una instancia nueva de la clase Hito, le asigna los
     * valores guardados en esta misma clase y devuelve el objeto completamente
     * construido.
     * 
     * @return Objeto completo y construido del tipo Hito
     */
    public Hito build() {
        Hito hito = new Hito();
        hito.setId(id);
        hito.setNombre(nombre);
        hito.setDescripcion(descripcion);
        hito.setEstado(estado);
        hito.setNotas(notas);
        hito.setProyecto(proyecto);
        return hito;
    }
}
