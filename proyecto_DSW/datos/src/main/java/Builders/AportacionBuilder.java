package Builders;

import entidades.Aportacion;
import entidades.Proyecto;
import entidades.Usuario;
import java.time.LocalDate;

/**
 * Clase Builder para la entidad Aportacion. Facilita la creación de objetos
 * del tipo Aportacion, permitiendo asignar sus atributos de forma ordenada.
 *
 * @author Felix_isq
 */
public class AportacionBuilder {

    private Long id;
    private double monto;
    private double comisionPlataforma;
    private LocalDate fecha = LocalDate.now();
    private Usuario usuario;
    private Proyecto proyecto;

    /**
     * Método para asignar el atributo del ID al objeto del tipo Aportacion.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param id Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public AportacionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Método para asignar el atributo del monto al objeto del tipo Aportacion.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param monto Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public AportacionBuilder monto(double monto) {
        this.monto = monto;
        return this;
    }

    /**
     * Método para asignar el atributo de la comision de la plataforma al 
     * objeto del tipo Aportacion. Al regresar la instancia de este mismo
     * Builder, se puede encadenar con varios métodos de manera consecutiva.
     * 
     * @param comisionPlataforma Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public AportacionBuilder comisionPlataforma(double comisionPlataforma) {
        this.comisionPlataforma = comisionPlataforma;
        return this;
    }

    /**
     * Método para asignar el atributo de la fecha al objeto del tipo Aportacion.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param fecha Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public AportacionBuilder fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    /**
     * Método para asignar el atributo del usuario al objeto del tipo Aportacion.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param usuario Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public AportacionBuilder usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    /**
     * Método para asignar el atributo del proyecto al objeto del tipo Aportacion.
     * Al regresar la instancia de este mismo Builder, se puede encadenar con
     * varios métodos de manera consecutiva.
     * 
     * @param proyecto Valor recibido para asignarlo como atributo
     * @return Instancia de esta misma clase Builder, que permite encadenar más métodos
     */
    public AportacionBuilder proyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        return this;
    }

    /**
     * Método para construir y devolver el objeto Aportacion con los valores
     * definidos anteriormente. Crea una instancia nueva de la clase Aportacion,
     * le asigna los valores guardados en esta misma clase y devuelve el objeto
     * completamente construido.
     * 
     * @return Objeto completo y construido del tipo Aportacion
     */
    public Aportacion build() {
        Aportacion aportacion = new Aportacion();
        aportacion.setId(id);
        aportacion.setMonto(monto);
        aportacion.setComisionPlataforma(comisionPlataforma);
        aportacion.setFecha(fecha);
        aportacion.setUsuario(usuario);
        aportacion.setProyecto(proyecto);
        return aportacion;
    }
}
