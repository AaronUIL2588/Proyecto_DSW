package APIs.apimarket;

import interfaces.ValidadorCedula;

/**
 * Clase que implementa la interfaz de Negocio para usarse con API Market
 * 
 * @author aron
 */
public class ApiMarketValidadorCedula implements ValidadorCedula {

    private final CedulaProfesionalAPI api;

    public ApiMarketValidadorCedula() {
        this.api = new CedulaProfesionalAPI();
    }

    @Override
    public boolean validarCedula(String numero) throws Exception {

        if (numero == null || !numero.matches("\\d+")) {
            throw new IllegalArgumentException(
                "El número de cédula debe contener solamente dígitos."
            );
        }

        if (numero.length() > 7) {
            throw new IllegalArgumentException(
                "El número de cédula no debe superar los siete dígitos."
            );
        }

        String cedulaNormalizada = "0".repeat(7 - numero.length()) + numero;

        RespuestaCedulaDTO respuesta =
                api.consultarCedula(cedulaNormalizada);

        if (respuesta == null ||
            !Boolean.TRUE.equals(respuesta.getSuccess())) {

            throw new IllegalStateException(
                "No fue posible validar la cédula profesional."
            );
        }

        if (respuesta.getData() == null) {
            throw new IllegalStateException(
                "La API devolvió una respuesta sin información de registros."
            );
        }

        for (CedulaRegistroDTO registro : respuesta.getData()) {

            if (registro != null &&
                cedulaNormalizada.equals(registro.getIdCedula())) {
                return true;
            }
        }

        return false;
    }
}