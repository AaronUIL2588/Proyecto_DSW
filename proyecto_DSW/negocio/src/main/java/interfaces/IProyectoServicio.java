/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Proyecto;
import java.util.List;

/**
 * Interfaz utilizada para la clase ProyectoServicio
 *
 * @author Felix_isq
 */
public interface IProyectoServicio {

    Proyecto crearProyecto(Proyecto proyecto);

    Proyecto obtenerProyecto(Long idProyecto);

    List<Proyecto> listarProyectosActivos();

    List<Proyecto> listarPorCategoria(String categoria);

    List<Proyecto> listarProyectosDeUsuario(Long idUsuario);

    void cancelarProyecto(Long idProyecto);

    boolean finalizarProyecto(Long idProyecto);

    void reactivarProyecto(Long idProyecto);

    double obtenerTotalRecaudado(Long idProyecto);

}
