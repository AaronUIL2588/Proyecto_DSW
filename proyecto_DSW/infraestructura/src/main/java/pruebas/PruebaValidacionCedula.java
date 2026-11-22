
package pruebas;

import APIs.apimarket.ApiMarketValidadorCedula;
import interfaces.ValidadorCedula;
import servicios.CedulaProfesionalServicio;

public class PruebaValidacionCedula {

    public static void main(String[] args) {

        ValidadorCedula validador =
                new ApiMarketValidadorCedula();

        CedulaProfesionalServicio servicio =
                new CedulaProfesionalServicio(validador);

        try {

            String numeroCedula = "2547854";

            boolean resultado =
                    servicio.validarCedula(numeroCedula);

            if (resultado) {
                System.out.println(
                        "La cédula profesional existe."
                );
            } else {
                System.out.println(
                        "La cédula profesional no fue encontrada."
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Ocurrió un error al validar la cédula:"
            );

            e.printStackTrace();
        }
    }
}