package servicios;

import interfaces.ValidadorCedula;

/**
 * Clase específica para consultar y validar la cédula profesional para el programa
 *
 * @author aron
 */
public class CedulaProfesionalServicio {
    private final ValidadorCedula validador;

    public CedulaProfesionalServicio(ValidadorCedula validador) {
        this.validador = validador;
    }

    public boolean validarCedula(String numero) throws Exception {
        return validador.validarCedula(numero);
    }
}