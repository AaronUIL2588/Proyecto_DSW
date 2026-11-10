/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Builders;

import entidades.Postulacion;
import entidades.Proyecto;
import entidades.Usuario;
import enumeradores.EstadoPostulacion;
import java.time.LocalDate;

/**
 * Builder de la entidad Postulacion.
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

    public PostulacionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public PostulacionBuilder mensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public PostulacionBuilder fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public PostulacionBuilder estadoPostulacion(EstadoPostulacion estadoPostulacion) {
        this.estadoPostulacion = estadoPostulacion;
        return this;
    }

    public PostulacionBuilder usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public PostulacionBuilder proyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        return this;
    }

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
