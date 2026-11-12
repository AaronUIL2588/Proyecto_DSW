/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

/**
 * Validaciones simples que repiten varios servicios.
 *
 * @author Felix_isq
 */
final class Validaciones {

    private Validaciones() {
    }

    static void exigirNoNulo(Object valor, String nombre) {
        if (valor == null) {
            throw new NegocioException(nombre + " es obligatorio");
        }
    }

    static void exigirTexto(String valor, String nombre) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new NegocioException(nombre + " es obligatorio");
        }
    }
}
