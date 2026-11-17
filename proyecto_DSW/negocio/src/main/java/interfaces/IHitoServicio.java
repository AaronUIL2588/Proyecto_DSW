/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Hito;
import java.util.List;

/**
 * Interfaz utilizada para la clase HitoServicio
 *
 * @author Felix_isq
 */
public interface IHitoServicio {

    Hito crearHito(Hito hito);

    List<Hito> listarPorProyecto(Long idProyecto);

    void marcarParaAprobacion(Long idHito);

    void aprobarHito(Long idHito);

    void rechazarHito(Long idHito);
}
