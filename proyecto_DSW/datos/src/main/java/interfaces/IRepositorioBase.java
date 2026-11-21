package interfaces;

/**
 * Operaciones comunes de base de datos que tienen todos los repositorios.
 * Es genérica: T es la entidad con la que trabaja cada repositorio
 * (Usuario, Proyecto, Hito, Aportacion o Postulacion).
 *
 * Las interfaces de cada repositorio heredan de esta, así estos métodos
 * se declaran una sola vez.
 *
 * @param <T> Entidad con la que trabaja el repositorio
 * @author Felix_isq
 */
public interface IRepositorioBase<T> {

    void guardar(T entidad);

    T buscarPorId(Long id);

    void actualizar(T entidad);

    void eliminar(Long id);
}