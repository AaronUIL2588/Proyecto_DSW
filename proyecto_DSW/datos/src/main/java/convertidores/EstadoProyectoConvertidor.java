package convertidores;

import enumeradores.EstadoProyecto;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Convierte EstadoProyecto al número que se guarda en la base de datos
 * (getValor) y, al leer, convierte ese número de regreso al enumerador.
 *
 * Así, si mañana se cambia el nombre de un estado, los registros ya
 * guardados no se pierden porque el número sigue siendo el mismo.
 *
 * @author Felix_isq
 */
@Converter
public class EstadoProyectoConvertidor implements AttributeConverter<EstadoProyecto, Integer> {

    /**
     * Java -> base de datos: guarda el valor numérico del estado.
     */
    @Override
    public Integer convertToDatabaseColumn(EstadoProyecto estado) {
        return estado == null ? null : estado.getValor();
    }

    /**
     * Base de datos -> Java: busca el estado que tiene ese valor.
     */
    @Override
    public EstadoProyecto convertToEntityAttribute(Integer valor) {
        if (valor == null) {
            return null;
        }
        for (EstadoProyecto estado : EstadoProyecto.values()) {
            if (estado.getValor() == valor) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Valor de EstadoProyecto desconocido: " + valor);
    }
}