package repositorios;

import interfaces.IHitoRepositorio;
import entidades.Hito;
import enumeradores.EstadoHito;
import java.util.List;

/**
 * Implementación con JPA de IHitoRepositorio.
 *
 * @author Felix_isq
 */
public class HitoRepositorio extends RepositorioBase implements IHitoRepositorio {

    @Override
    public void guardar(Hito hito) {
        ejecutar(em -> em.persist(hito));
    }

    @Override
    public Hito buscarPorId(Long id) {
        return consultar(em -> em.find(Hito.class, id));
    }

    @Override
    public List<Hito> listarPorProyecto(Long idProyecto) {
        return consultar(em -> em.createQuery(
                "SELECT h FROM Hito h WHERE h.proyecto.id = :idProyecto", Hito.class)
                .setParameter("idProyecto", idProyecto)
                .getResultList());
    }

    @Override
    public void actualizar(Hito hito) {
        ejecutar(em -> em.merge(hito));
    }

    @Override
    public void actualizarEstado(Long id, EstadoHito estado) {
        ejecutar(em -> {
            Hito hito = em.find(Hito.class, id);
            if (hito != null) {
                hito.setEstado(estado);
            }
        });
    }
}
