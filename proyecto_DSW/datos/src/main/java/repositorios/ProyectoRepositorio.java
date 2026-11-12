/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import entidades.Proyecto;
import enumeradores.EstadoProyecto;
import java.util.List;

/**
 *
 * @author Felix_isq
 */
public class ProyectoRepositorio extends RepositorioBase implements IProyectoRepositorio {

    @Override
    public void guardar(Proyecto proyecto) {
        ejecutar(em -> em.persist(proyecto));
    }

    @Override
    public Proyecto buscarPorId(Long id) {
        return consultar(em -> em.find(Proyecto.class, id));
    }

    @Override
    public List<Proyecto> listarProyectos() {
        return consultar(em -> em.createQuery("SELECT p FROM Proyecto p", Proyecto.class).getResultList());
    }

    @Override
    public List<Proyecto> listarPorUsuario(Long idUsuario) {
        return consultar(em -> em.createQuery(
                "SELECT p FROM Proyecto p WHERE p.usuario.id = :idUsuario", Proyecto.class)
                .setParameter("idUsuario", idUsuario)
                .getResultList());
    }

    @Override
    public List<Proyecto> buscarPorCategoria(String categoria) {
        return consultar(em -> em.createQuery(
                "SELECT p FROM Proyecto p WHERE p.categoria = :categoria", Proyecto.class)
                .setParameter("categoria", categoria)
                .getResultList());
    }

    @Override
    public void actualizarEstado(Long id, EstadoProyecto estado) {
        ejecutar(em -> {
            Proyecto proyecto = em.find(Proyecto.class, id);
            if (proyecto != null) {
                proyecto.setEstado(estado);
            }
        });
    }
}
