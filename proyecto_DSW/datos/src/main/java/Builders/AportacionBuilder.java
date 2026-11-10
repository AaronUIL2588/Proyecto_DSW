/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Builders;

import entidades.Aportacion;
import entidades.Proyecto;
import entidades.Usuario;
import java.time.LocalDate;

/**
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

    public AportacionBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public AportacionBuilder monto(double monto) {
        this.monto = monto;
        return this;
    }

    public AportacionBuilder comisionPlataforma(double comisionPlataforma) {
        this.comisionPlataforma = comisionPlataforma;
        return this;
    }

    public AportacionBuilder fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public AportacionBuilder usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public AportacionBuilder proyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        return this;
    }

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
