/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import interfaces.IPostulacionRepositorio;
import entidades.Postulacion;
import enumeradores.EstadoPostulacion;
import java.util.List;

/**
 * Implementación con JPA de IPostulacionRepositorio.
 *
 * @author Felix_isq
 */
public class PostulacionRepositorio extends RepositorioBase implements IPostulacionRepositorio {

    @Override
    public void guardar(Postulacion postulacion) {
        ejecutar(em -> em.persist(postulacion));
    }

    @Override
    public Postulacion buscarPorId(Long id) {
        return consultar(em -> em.find(Postulacion.class, id));
    }

    @Override
    public List<Postulacion> listarPorProyecto(Long idProyecto) {
        return consultar(em -> em.createQuery(
                "SELECT p FROM Postulacion p WHERE p.proyecto.id = :idProyecto", Postulacion.class)
                .setParameter("idProyecto", idProyecto)
                .getResultList());
    }

    @Override
    public List<Postulacion> listarPorUsuario(Long idUsuario) {
        return consultar(em -> em.createQuery(
                "SELECT p FROM Postulacion p WHERE p.usuario.id = :idUsuario", Postulacion.class)
                .setParameter("idUsuario", idUsuario)
                .getResultList());
    }

    @Override
    public void actualizarEstado(Long id, EstadoPostulacion estado) {
        ejecutar(em -> {
            Postulacion postulacion = em.find(Postulacion.class, id);
            if (postulacion != null) {
                postulacion.setEstadoPostulacion(estado);
            }
        });
    }

    @Override
    public boolean existePostulacion(Long idUsuario, Long idProyecto) {
        Long cantidad = consultar(em -> em.createQuery(
                "SELECT COUNT(p) FROM Postulacion p WHERE p.usuario.id = :idUsuario AND p.proyecto.id = :idProyecto",
                Long.class)
                .setParameter("idUsuario", idUsuario)
                .setParameter("idProyecto", idProyecto)
                .getSingleResult());
        return cantidad > 0;
    }
}
