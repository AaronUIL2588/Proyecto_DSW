package enumeradores;

/**
 * Clase de tipo Enumeration para el estado de la postulación del usuario
 * (Postulado a un proyecto como freelancer)
 *
 * Cada estado tiene un valor numérico (el que se guarda en la base de datos)
 * y un texto (el que se muestra en pantalla).
 *
 * @author aron
 */
public enum EstadoPostulacion {

    PENDIENTE("PENDIENTE", 1),
    ACEPTADA("ACEPTADA", 2),
    RECHAZADA("RECHAZADA", 3);

    private final String estado;
    private final int valor;

    private EstadoPostulacion(String estado, int valor) {
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