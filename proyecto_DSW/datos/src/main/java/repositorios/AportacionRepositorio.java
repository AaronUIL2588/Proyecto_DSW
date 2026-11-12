/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import entidades.Aportacion;
import java.util.List;

/**
 * Implementación con JPA de IAportacionRepositorio.
 *
 * @author Felix_isq
 */
public class AportacionRepositorio extends RepositorioBase implements IAportacionRepositorio {

    @Override
    public void guardar(Aportacion aportacion) {
        ejecutar(em -> em.persist(aportacion));
    }

    @Override
    public Aportacion buscarPorId(Long id) {
        return consultar(em -> em.find(Aportacion.class, id));
    }

    @Override
    public List<Aportacion> listarPorProyecto(Long idProyecto) {
        return consultar(em -> em.createQuery(
                "SELECT a FROM Aportacion a WHERE a.proyecto.id = :idProyecto", Aportacion.class)
                .setParameter("idProyecto", idProyecto)
                .getResultList());
    }

    @Override
    public List<Aportacion> listarPorUsuario(Long idUsuario) {
        return consultar(em -> em.createQuery(
                "SELECT a FROM Aportacion a WHERE a.usuario.id = :idUsuario", Aportacion.class)
                .setParameter("idUsuario", idUsuario)
                .getResultList());
    }

    @Override
    public double obtenerTotalRecaudado(Long idProyecto) {
        Double total = consultar(em -> em.createQuery(
                "SELECT SUM(a.monto) FROM Aportacion a WHERE a.proyecto.id = :idProyecto", Double.class)
                .setParameter("idProyecto", idProyecto)
                .getSingleResult());
        return total == null ? 0.0 : total;
    }
}
