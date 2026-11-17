/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Postulacion;
import java.util.List;

/**
 * Interfaz utilizada para la clase PostulacionServicio
 *
 * @author Felix_isq
 */
public interface IPostulacionServicio {

    Postulacion enviarPostulacion(Postulacion postulacion);

    List<Postulacion> listarPorProyecto(Long idProyecto);

    List<Postulacion> listarPorUsuario(Long idUsuario);

    void seleccionarFreelancer(Long idPostulacion);

    void cancelarSeleccion(Long idPostulacion);
}
