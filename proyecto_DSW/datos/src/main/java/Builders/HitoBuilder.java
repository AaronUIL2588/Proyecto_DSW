/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Builders;

import entidades.Hito;
import entidades.Proyecto;
import enumeradores.EstadoHito;

/**
 * Builder de la entidad Hito.
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

    public HitoBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public HitoBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public HitoBuilder descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public HitoBuilder estado(EstadoHito estado) {
        this.estado = estado;
        return this;
    }

    public HitoBuilder notas(String notas) {
        this.notas = notas;
        return this;
    }

    public HitoBuilder proyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        return this;
    }

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
