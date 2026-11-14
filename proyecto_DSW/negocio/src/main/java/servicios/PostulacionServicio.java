/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import entidades.Postulacion;
import entidades.Proyecto;
import entidades.Usuario;
import enumeradores.EstadoPostulacion;
import enumeradores.EstadoProyecto;
import enumeradores.EstadoVerificacion;
import java.time.LocalDate;
import java.util.List;
import interfaces.IPostulacionRepositorio;
import interfaces.IProyectoRepositorio;
import interfaces.IUsuarioRepositorio;
import repositorios.PostulacionRepositorio;
import repositorios.ProyectoRepositorio;
import repositorios.UsuarioRepositorio;

/**
 * Reglas de negocio de las postulaciones de freelancers a los proyectos.
 *
 * @author Felix_isq
 */
public class PostulacionServicio {

    private final IPostulacionRepositorio postulacionRepositorio;
    private final IProyectoRepositorio proyectoRepositorio;
    private final IUsuarioRepositorio usuarioRepositorio;

    public PostulacionServicio() {
        this(new PostulacionRepositorio(), new ProyectoRepositorio(), new UsuarioRepositorio());
    }

    public PostulacionServicio(IPostulacionRepositorio postulacionRepositorio,
            IProyectoRepositorio proyectoRepositorio,
            IUsuarioRepositorio usuarioRepositorio) {
        this.postulacionRepositorio = postulacionRepositorio;
        this.proyectoRepositorio = proyectoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    /**
     * Envía una postulación. Solo pueden postularse los freelancers
     * verificados, a proyectos activos de otra persona y una sola vez por
     * proyecto. La postulación queda PENDIENTE con la fecha de hoy.
     */
    public Postulacion enviarPostulacion(Postulacion postulacion) {
        Validaciones.exigirNoNulo(postulacion, "La postulación");
        Validaciones.exigirTexto(postulacion.getMensaje(), "El mensaje");
        if (postulacion.getUsuario() == null || postulacion.getUsuario().getId() == null) {
            throw new NegocioException("La postulación debe tener un usuario");
        }
        if (postulacion.getProyecto() == null || postulacion.getProyecto().getId() == null) {
            throw new NegocioException("La postulación debe tener un proyecto");
        }
        Usuario usuario = usuarioRepositorio.buscarPorId(postulacion.getUsuario().getId());
        if (usuario == null) {
            throw new NegocioException("El usuario no existe");
        }
        if (usuario.getEstadoVerificacion() != EstadoVerificacion.VERIFICADO) {
            throw new NegocioException("Solo los freelancers verificados pueden postularse");
        }
        Proyecto proyecto = proyectoRepositorio.buscarPorId(postulacion.getProyecto().getId());
        if (proyecto == null) {
            throw new NegocioException("El proyecto no existe");
        }
        if (proyecto.getEstado() != EstadoProyecto.ACTIVO) {
            throw new NegocioException("Solo se puede postular a proyectos activos");
        }
        if (proyecto.getUsuario().getId().equals(usuario.getId())) {
            throw new NegocioException("No puedes postularte a tu propio proyecto");
        }
        if (postulacionRepositorio.existePostulacion(usuario.getId(), proyecto.getId())) {
            throw new NegocioException("Ya te postulaste a este proyecto");
        }
        postulacion.setUsuario(usuario);
        postulacion.setProyecto(proyecto);
        postulacion.setEstadoPostulacion(EstadoPostulacion.PENDIENTE);
        postulacion.setFecha(LocalDate.now());
        postulacionRepositorio.guardar(postulacion);
        return postulacion;
    }

    public List<Postulacion> listarPorProyecto(Long idProyecto) {
        Validaciones.exigirNoNulo(idProyecto, "El id del proyecto");
        return postulacionRepositorio.listarPorProyecto(idProyecto);
    }

    public List<Postulacion> listarPorUsuario(Long idUsuario) {
        Validaciones.exigirNoNulo(idUsuario, "El id del usuario");
        return postulacionRepositorio.listarPorUsuario(idUsuario);
    }

    /**
     * El dueño elige a un freelancer. Si con esa elección ya se completa la
     * cantidad de freelancers que pidió el proyecto, las demás postulaciones
     * pendientes pasan a RECHAZADA (dejan de verse).
     */
    public void seleccionarFreelancer(Long idPostulacion) {
        Postulacion postulacion = obtenerPostulacion(idPostulacion);
        if (postulacion.getEstadoPostulacion() != EstadoPostulacion.PENDIENTE) {
            throw new NegocioException("Solo se puede seleccionar una postulación pendiente");
        }
        Proyecto proyecto = postulacion.getProyecto();
        if (proyecto.getEstado() != EstadoProyecto.ACTIVO) {
            throw new NegocioException("El proyecto ya no está activo");
        }
        List<Postulacion> todas = postulacionRepositorio.listarPorProyecto(proyecto.getId());
        long aceptadas = todas.stream()
                .filter(p -> p.getEstadoPostulacion() == EstadoPostulacion.ACEPTADA)
                .count();
        if (aceptadas >= proyecto.getCantidadFreelancer()) {
            throw new NegocioException("El proyecto ya tiene todos los freelancers que necesita");
        }
        postulacionRepositorio.actualizarEstado(idPostulacion, EstadoPostulacion.ACEPTADA);
        if (aceptadas + 1 >= proyecto.getCantidadFreelancer()) {
            for (Postulacion otra : todas) {
                if (otra.getEstadoPostulacion() == EstadoPostulacion.PENDIENTE
                        && !otra.getId().equals(idPostulacion)) {
                    postulacionRepositorio.actualizarEstado(otra.getId(), EstadoPostulacion.RECHAZADA);
                }
            }
        }
    }

    /**
     * El dueño deshace la elección de un freelancer. La postulación vuelve a
     * PENDIENTE y, como se libera un lugar, las que se habían rechazado también
     * vuelven a PENDIENTE.
     */
    public void cancelarSeleccion(Long idPostulacion) {
        Postulacion postulacion = obtenerPostulacion(idPostulacion);
        if (postulacion.getEstadoPostulacion() != EstadoPostulacion.ACEPTADA) {
            throw new NegocioException("Solo se puede cancelar la selección de una postulación aceptada");
        }
        postulacionRepositorio.actualizarEstado(idPostulacion, EstadoPostulacion.PENDIENTE);
        for (Postulacion otra : postulacionRepositorio.listarPorProyecto(postulacion.getProyecto().getId())) {
            if (otra.getEstadoPostulacion() == EstadoPostulacion.RECHAZADA) {
                postulacionRepositorio.actualizarEstado(otra.getId(), EstadoPostulacion.PENDIENTE);
            }
        }
    }

    private Postulacion obtenerPostulacion(Long idPostulacion) {
        Validaciones.exigirNoNulo(idPostulacion, "El id de la postulación");
        Postulacion postulacion = postulacionRepositorio.buscarPorId(idPostulacion);
        if (postulacion == null) {
            throw new NegocioException("La postulación no existe");
        }
        return postulacion;
    }
}
