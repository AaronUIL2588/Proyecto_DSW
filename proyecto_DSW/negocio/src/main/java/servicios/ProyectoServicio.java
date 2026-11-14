/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import entidades.Hito;
import entidades.Proyecto;
import enumeradores.EstadoHito;
import enumeradores.EstadoProyecto;
import java.time.LocalDate;
import java.util.List;
import repositorios.AportacionRepositorio;
import repositorios.HitoRepositorio;
import interfaces.IAportacionRepositorio;
import interfaces.IHitoRepositorio;
import interfaces.IProyectoRepositorio;
import repositorios.ProyectoRepositorio;

/**
 * Reglas de negocio de los proyectos: publicar, consultar el catálogo,
 * cancelar, finalizar y reactivar.
 *
 * @author Felix_isq
 */
public class ProyectoServicio {

    private final IProyectoRepositorio proyectoRepositorio;
    private final IHitoRepositorio hitoRepositorio;
    private final IAportacionRepositorio aportacionRepositorio;

    public ProyectoServicio() {
        this(new ProyectoRepositorio(), new HitoRepositorio(), new AportacionRepositorio());
    }

    public ProyectoServicio(IProyectoRepositorio proyectoRepositorio,
            IHitoRepositorio hitoRepositorio,
            IAportacionRepositorio aportacionRepositorio) {
        this.proyectoRepositorio = proyectoRepositorio;
        this.hitoRepositorio = hitoRepositorio;
        this.aportacionRepositorio = aportacionRepositorio;
    }

    /**
     * Publica un proyecto nuevo (queda ACTIVO) junto con sus hitos (quedan
     * PENDIENTES). Valida los datos obligatorios.
     */
    public Proyecto crearProyecto(Proyecto proyecto) {
        Validaciones.exigirNoNulo(proyecto, "El proyecto");
        Validaciones.exigirTexto(proyecto.getTitulo(), "El título");
        Validaciones.exigirTexto(proyecto.getDescripcion(), "La descripción");
        Validaciones.exigirTexto(proyecto.getCategoria(), "La categoría");
        Validaciones.exigirTexto(proyecto.getContacto(), "El contacto");
        if (proyecto.getCantidadFreelancer() < 1) {
            throw new NegocioException("El proyecto necesita al menos un freelancer");
        }
        if (proyecto.getUsuario() == null || proyecto.getUsuario().getId() == null) {
            throw new NegocioException("El proyecto debe tener un dueño");
        }
        for (Hito hito : proyecto.getListaHitos()) {
            Validaciones.exigirTexto(hito.getNombre(), "El nombre del hito");
            Validaciones.exigirTexto(hito.getDescripcion(), "La descripción del hito");
            hito.setEstado(EstadoHito.PENDIENTE);
            if (hito.getNotas() == null) {
                hito.setNotas("");
            }
        }
        proyecto.setEstado(EstadoProyecto.ACTIVO);
        if (proyecto.getFechaCreacion() == null) {
            proyecto.setFechaCreacion(LocalDate.now());
        }
        proyectoRepositorio.guardar(proyecto);
        return proyecto;
    }

    public Proyecto obtenerProyecto(Long idProyecto) {
        Validaciones.exigirNoNulo(idProyecto, "El id del proyecto");
        Proyecto proyecto = proyectoRepositorio.buscarPorId(idProyecto);
        if (proyecto == null) {
            throw new NegocioException("El proyecto no existe");
        }
        return proyecto;
    }

    /**
     * Catálogo de Explorar: solo los proyectos activos.
     */
    public List<Proyecto> listarProyectosActivos() {
        return proyectoRepositorio.listarProyectos().stream()
                .filter(p -> p.getEstado() == EstadoProyecto.ACTIVO)
                .toList();
    }

    /**
     * Catálogo de Explorar filtrado por categoría: solo los proyectos activos.
     */
    public List<Proyecto> listarPorCategoria(String categoria) {
        Validaciones.exigirTexto(categoria, "La categoría");
        return proyectoRepositorio.buscarPorCategoria(categoria).stream()
                .filter(p -> p.getEstado() == EstadoProyecto.ACTIVO)
                .toList();
    }

    /**
     * Proyectos publicados por un usuario, en cualquier estado ("Mis
     * proyectos").
     */
    public List<Proyecto> listarProyectosDeUsuario(Long idUsuario) {
        Validaciones.exigirNoNulo(idUsuario, "El id del usuario");
        return proyectoRepositorio.listarPorUsuario(idUsuario);
    }

    /**
     * Cancela un proyecto activo. Aquí es donde se haría el reembolso, con el
     * sistema bancario, del dinero que no se haya pagado en hitos aprobados.
     */
    public void cancelarProyecto(Long idProyecto) {
        Proyecto proyecto = obtenerProyecto(idProyecto);
        if (proyecto.getEstado() != EstadoProyecto.ACTIVO) {
            throw new NegocioException("Solo se puede cancelar un proyecto activo");
        }
        proyectoRepositorio.actualizarEstado(idProyecto, EstadoProyecto.CANCELADO);
    }

    /**
     * Finaliza un proyecto activo. Regresa true si se finalizó, o false si
     * todavía tiene hitos sin aprobar (o no tiene hitos).
     */
    public boolean finalizarProyecto(Long idProyecto) {
        Proyecto proyecto = obtenerProyecto(idProyecto);
        if (proyecto.getEstado() != EstadoProyecto.ACTIVO) {
            throw new NegocioException("Solo se puede finalizar un proyecto activo");
        }
        List<Hito> hitos = hitoRepositorio.listarPorProyecto(idProyecto);
        if (hitos.isEmpty()) {
            return false;
        }
        for (Hito hito : hitos) {
            if (hito.getEstado() != EstadoHito.APROBADO) {
                return false;
            }
        }
        proyectoRepositorio.actualizarEstado(idProyecto, EstadoProyecto.FINALIZADO);
        return true;
    }

    /**
     * Vuelve a activar un proyecto que estaba finalizado o cancelado.
     */
    public void reactivarProyecto(Long idProyecto) {
        Proyecto proyecto = obtenerProyecto(idProyecto);
        if (proyecto.getEstado() == EstadoProyecto.ACTIVO) {
            throw new NegocioException("El proyecto ya está activo");
        }
        proyectoRepositorio.actualizarEstado(idProyecto, EstadoProyecto.ACTIVO);
    }

    /**
     * Suma de las aportaciones del proyecto (el total no se guarda, se
     * calcula).
     */
    public double obtenerTotalRecaudado(Long idProyecto) {
        obtenerProyecto(idProyecto);
        return aportacionRepositorio.obtenerTotalRecaudado(idProyecto);
    }
}
