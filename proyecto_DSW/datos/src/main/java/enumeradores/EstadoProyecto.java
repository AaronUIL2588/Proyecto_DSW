package enumeradores;

/**
 * Clase de tipo Enumeration para el estado del proyecto
 *
 * @author aron
 */
public enum EstadoProyecto {

    ACTIVO("ACTIVO", 1), 
    FINALIZADO("FINALIZADO", 2), 
    CANCELADO("CANCELADO", 3);

    private final String estado;
    private final int valor;

    private EstadoProyecto(String estado, int valor) {
        this.estado = estado;
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public int getValor() {
        return valor;
    }
}
