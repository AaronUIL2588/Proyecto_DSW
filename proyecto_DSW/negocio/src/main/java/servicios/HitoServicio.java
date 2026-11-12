/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import entidades.Hito;
import entidades.Proyecto;
import enumeradores.EstadoHito;
import enumeradores.EstadoProyecto;
import java.util.List;
import repositorios.HitoRepositorio;
import repositorios.IHitoRepositorio;
import repositorios.IProyectoRepositorio;
import repositorios.ProyectoRepositorio;

/**
 * Reglas de negocio de los hitos. Un hito pasa por PENDIENTE (por hacer),
 * COMPLETADO (el freelancer lo marcó como terminado) y APROBADO (el dueño lo
 * aceptó). Si el dueño lo rechaza, vuelve a PENDIENTE.
 *
 * @author Felix_isq
 */
public class HitoServicio {

    private final IHitoRepositorio hitoRepositorio;
    private final IProyectoRepositorio proyectoRepositorio;

    public HitoServicio() {
        this(new HitoRepositorio(), new ProyectoRepositorio());
    }

    public HitoServicio(IHitoRepositorio hitoRepositorio, IProyectoRepositorio proyectoRepositorio) {
        this.hitoRepositorio = hitoRepositorio;
        this.proyectoRepositorio = proyectoRepositorio;
    }

    /**
     * Agrega un hito (queda PENDIENTE) a un proyecto activo que ya existe.
     */
    public Hito crearHito(Hito hito) {
        Validaciones.exigirNoNulo(hito, "El hito");
        Validaciones.exigirTexto(hito.getNombre(), "El nombre del hito");
        Validaciones.exigirTexto(hito.getDescripcion(), "La descripción del hito");
        if (hito.getProyecto() == null || hito.getProyecto().getId() == null) {
            throw new NegocioException("El hito debe pertenecer a un proyecto");
        }
        Proyecto proyecto = proyectoRepositorio.buscarPorId(hito.getProyecto().getId());
        if (proyecto == null) {
            throw new NegocioException("El proyecto no existe");
        }
        if (proyecto.getEstado() != EstadoProyecto.ACTIVO) {
            throw new NegocioException("Solo se pueden agregar hitos a proyectos activos");
        }
        hito.setProyecto(proyecto);
        hito.setEstado(EstadoHito.PENDIENTE);
        if (hito.getNotas() == null) {
            hito.setNotas("");
        }
        hitoRepositorio.guardar(hito);
        return hito;
    }

    public List<Hito> listarPorProyecto(Long idProyecto) {
        Validaciones.exigirNoNulo(idProyecto, "El id del proyecto");
        return hitoRepositorio.listarPorProyecto(idProyecto);
    }

    /**
     * El freelancer marca un hito pendiente como terminado, para que el dueño
     * lo apruebe.
     */
    public void marcarParaAprobacion(Long idHito) {
        Hito hito = obtenerHitoDeProyectoActivo(idHito);
        if (hito.getEstado() != EstadoHito.PENDIENTE) {
            throw new NegocioException("Solo un hito pendiente puede marcarse como terminado");
        }
        hitoRepositorio.actualizarEstado(idHito, EstadoHito.COMPLETADO);
    }

    /**
     * El dueño aprueba un hito que el freelancer marcó como terminado.
     */
    public void aprobarHito(Long idHito) {
        Hito hito = obtenerHitoDeProyectoActivo(idHito);
        if (hito.getEstado() != EstadoHito.COMPLETADO) {
            throw new NegocioException("Solo se puede aprobar un hito marcado como terminado");
        }
        hitoRepositorio.actualizarEstado(idHito, EstadoHito.APROBADO);
    }

    /**
     * El dueño rechaza un hito marcado como terminado: vuelve a PENDIENTE.
     */
    public void rechazarHito(Long idHito) {
        Hito hito = obtenerHitoDeProyectoActivo(idHito);
        if (hito.getEstado() != EstadoHito.COMPLETADO) {
            throw new NegocioException("Solo se puede rechazar un hito marcado como terminado");
        }
        hitoRepositorio.actualizarEstado(idHito, EstadoHito.PENDIENTE);
    }

    private Hito obtenerHitoDeProyectoActivo(Long idHito) {
        Validaciones.exigirNoNulo(idHito, "El id del hito");
        Hito hito = hitoRepositorio.buscarPorId(idHito);
        if (hito == null) {
            throw new NegocioException("El hito no existe");
        }
        if (hito.getProyecto().getEstado() != EstadoProyecto.ACTIVO) {
            throw new NegocioException("El proyecto ya no está activo");
        }
        return hito;
    }
}
