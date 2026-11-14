/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import Builders.AportacionBuilder;
import entidades.Aportacion;
import entidades.Proyecto;
import entidades.Usuario;
import enumeradores.EstadoProyecto;
import java.util.List;
import repositorios.AportacionRepositorio;
import interfaces.IAportacionRepositorio;
import interfaces.IProyectoRepositorio;
import interfaces.IUsuarioRepositorio;
import repositorios.ProyectoRepositorio;
import repositorios.UsuarioRepositorio;

/**
 * Reglas de negocio de las aportaciones (donaciones) a los proyectos.
 *
 * @author Felix_isq
 */
public class AportacionServicio {

    /**
     * Comisión de la plataforma sobre cada aportación: 5 %.
     */
    public static final double PORCENTAJE_COMISION = 0.05;

    private final IAportacionRepositorio aportacionRepositorio;
    private final IProyectoRepositorio proyectoRepositorio;
    private final IUsuarioRepositorio usuarioRepositorio;

    public AportacionServicio() {
        this(new AportacionRepositorio(), new ProyectoRepositorio(), new UsuarioRepositorio());
    }

    public AportacionServicio(IAportacionRepositorio aportacionRepositorio,
            IProyectoRepositorio proyectoRepositorio,
            IUsuarioRepositorio usuarioRepositorio) {
        this.aportacionRepositorio = aportacionRepositorio;
        this.proyectoRepositorio = proyectoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    /**
     * Comisión (5 %) de un monto, redondeada a centavos. Sirve para mostrarla
     * antes de confirmar.
     */
    public double calcularComision(double monto) {
        return Math.round(monto * PORCENTAJE_COMISION * 100.0) / 100.0;
    }

    /**
     * Registra la aportación de un usuario a un proyecto de otra persona. El
     * proyecto debe estar activo. Aquí es donde se haría el cobro con el
     * sistema bancario antes de guardar.
     */
    public Aportacion donar(Long idUsuario, Long idProyecto, double monto) {
        Validaciones.exigirNoNulo(idUsuario, "El id del usuario");
        Validaciones.exigirNoNulo(idProyecto, "El id del proyecto");
        if (monto <= 0) {
            throw new NegocioException("El monto debe ser mayor a cero");
        }
        Usuario usuario = usuarioRepositorio.buscarPorId(idUsuario);
        if (usuario == null) {
            throw new NegocioException("El usuario no existe");
        }
        Proyecto proyecto = proyectoRepositorio.buscarPorId(idProyecto);
        if (proyecto == null) {
            throw new NegocioException("El proyecto no existe");
        }
        if (proyecto.getEstado() != EstadoProyecto.ACTIVO) {
            throw new NegocioException("Solo se puede aportar a proyectos activos");
        }
        if (proyecto.getUsuario().getId().equals(idUsuario)) {
            throw new NegocioException("No puedes aportar a tu propio proyecto");
        }
        Aportacion aportacion = new AportacionBuilder()
                .monto(monto)
                .comisionPlataforma(calcularComision(monto))
                .usuario(usuario)
                .proyecto(proyecto)
                .build();
        aportacionRepositorio.guardar(aportacion);
        return aportacion;
    }

    public List<Aportacion> listarPorProyecto(Long idProyecto) {
        Validaciones.exigirNoNulo(idProyecto, "El id del proyecto");
        return aportacionRepositorio.listarPorProyecto(idProyecto);
    }

    public List<Aportacion> listarPorUsuario(Long idUsuario) {
        Validaciones.exigirNoNulo(idUsuario, "El id del usuario");
        return aportacionRepositorio.listarPorUsuario(idUsuario);
    }
}
