package interfaces;

/**
 * Interfaz funcional para utilizar con el sistema externo que valida cédulas.
 *
 * @author aron
 */
public interface ValidadorCedula {
    boolean validarCedula(String numero) throws Exception;
}