package convertidores;

import enumeradores.EstadoVerificacion;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Convierte EstadoVerificacion al número que se guarda en la base de datos
 * (getValor) y, al leer, convierte ese número de regreso al enumerador.
 *
 * Así, si mañana se cambia el nombre de un estado, los registros ya
 * guardados no se pierden porque el número sigue siendo el mismo.
 * Puede ser null, porque un usuario que no es freelancer no tiene estado
 * de verificación.
 *
 * @author Felix_isq
 */
@Converter
public class EstadoVerificacionConvertidor implements AttributeConverter<EstadoVerificacion, Integer> {

    /**
     * Java -> base de datos: guarda el valor numérico del estado.
     */
    @Override
    public Integer convertToDatabaseColumn(EstadoVerificacion estado) {
        return estado == null ? null : estado.getValor();
    }

    /**
     * Base de datos -> Java: busca el estado que tiene ese valor.
     */
    @Override
    public EstadoVerificacion convertToEntityAttribute(Integer valor) {
        if (valor == null) {
            return null;
        }
        for (EstadoVerificacion estado : EstadoVerificacion.values()) {
            if (estado.getValor() == valor) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Valor de EstadoVerificacion desconocido: " + valor);
    }
}