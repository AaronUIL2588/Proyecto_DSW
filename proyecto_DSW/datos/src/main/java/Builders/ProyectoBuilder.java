/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Builders;

import entidades.Hito;
import entidades.Proyecto;
import entidades.Usuario;
import enumeradores.EstadoProyecto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Builder de la entidad Proyecto.
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

    public ProyectoBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ProyectoBuilder titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public ProyectoBuilder descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public ProyectoBuilder categoria(String categoria) {
        this.categoria = categoria;
        return this;
    }

    public ProyectoBuilder cantidadFreelancer(int cantidadFreelancer) {
        this.cantidadFreelancer = cantidadFreelancer;
        return this;
    }

    public ProyectoBuilder estado(EstadoProyecto estado) {
        this.estado = estado;
        return this;
    }

    public ProyectoBuilder fechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public ProyectoBuilder contacto(String contacto) {
        this.contacto = contacto;
        return this;
    }

    public ProyectoBuilder usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public ProyectoBuilder hito(Hito hito) {
        this.hitos.add(hito);
        return this;
    }

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
            proyecto.agregarHito(hito);
        }
        return proyecto;
    }
}
