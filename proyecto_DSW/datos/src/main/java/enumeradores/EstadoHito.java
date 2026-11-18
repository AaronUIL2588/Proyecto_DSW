package enumeradores;

/**
 * Clase de tipo Enumeration para el estado del hito del proyecto
 *
 * @author aron
 */
public enum EstadoHito {
    
    PENDIENTE("PENDIENTE", 1),
    COMPLETADO("COMPLETADO", 2),
    APROBADO("APROBADO", 3);
    
    private String estado;
    private int valor;
    
    private EstadoHito(String estado, int valor) {
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
