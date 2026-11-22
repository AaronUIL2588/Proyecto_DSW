package repositorios;

import interfaces.IHitoRepositorio;
import entidades.Hito;
import java.util.List;

/**
 * Implementación con JPA de IHitoRepositorio.
 *
 * @author Felix_isq
 */
public class HitoRepositorio extends RepositorioBase<Hito> implements IHitoRepositorio {

    public HitoRepositorio() {
        super(Hito.class);
    }

    @Override
    public List<Hito> listarPorProyecto(Long idProyecto) {
        return consultar(em -> em.createQuery(
                "SELECT h FROM Hito h WHERE h.proyecto.id = :idProyecto", Hito.class)
                .setParameter("idProyecto", idProyecto)
                .getResultList());
    }

    
}