package convertidores;

import enumeradores.EstadoHito;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Convierte EstadoHito al número que se guarda en la base de datos (getValor)
 * y, al leer, convierte ese número de regreso al enumerador.
 *
 * Así, si mañana se cambia el nombre de un estado, los registros ya
 * guardados no se pierden porque el número sigue siendo el mismo.
 *
 * @author Felix_isq
 */
@Converter
public class EstadoHitoConvertidor implements AttributeConverter<EstadoHito, Integer> {

    /**
     * Java -> base de datos: guarda el valor numérico del estado.
     */
    @Override
    public Integer convertToDatabaseColumn(EstadoHito estado) {
        return estado == null ? null : estado.getValor();
    }

    /**
     * Base de datos -> Java: busca el estado que tiene ese valor.
     */
    @Override
    public EstadoHito convertToEntityAttribute(Integer valor) {
        if (valor == null) {
            return null;
        }
        for (EstadoHito estado : EstadoHito.values()) {
            if (estado.getValor() == valor) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Valor de EstadoHito desconocido: " + valor);
    }
}