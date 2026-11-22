package enumeradores;

/**
 * Clase de tipo Enumeration para el estado de la verificación del usuario
 * (Postulado a Freelancer)
 *
 * @author aron
 */
public enum EstadoVerificacion {
     PENDIENTE("PENDIENTE", 1), 
     VERIFICADO("VERIFICADO", 2), 
     RECHAZADO("RECHAZADO", 3);

    private final String estado;
    private final int valor;

    private EstadoVerificacion(String estado, int valor) {
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