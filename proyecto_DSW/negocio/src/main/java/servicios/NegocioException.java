/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

/**
 * Error de regla de negocio (por ejemplo "Ya existe un usuario con ese
 * correo"). El mensaje está pensado para mostrarse tal cual al usuario en la
 * pantalla.
 *
 * @author Felix_isq
 */
public class NegocioException extends RuntimeException {

    public NegocioException(String mensaje) {
        super(mensaje);
    }
}
