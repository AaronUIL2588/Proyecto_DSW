package interfaces;

import entidades.Aportacion;
import java.util.List;

/**
 * Interfaz utilizada para la clase AportacionServicio
 *
 * @author aron
 */
public interface IAportacionServicio {
    double calcularComision(double monto);

    Aportacion donar(Long idUsuario, Long idProyecto, double monto);

    List<Aportacion> listarPorProyecto(Long idProyecto);

    List<Aportacion> listarPorUsuario(Long idUsuario);
}
