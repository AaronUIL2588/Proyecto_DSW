/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Aportacion;
import java.util.List;

/**
 * Operaciones de base de datos para la entidad Aportacion.
 *
 * @author Felix_isq
 */
public interface IAportacionRepositorio {

    void guardar(Aportacion aportacion);

    Aportacion buscarPorId(Long id);

    List<Aportacion> listarPorProyecto(Long idProyecto);

    List<Aportacion> listarPorUsuario(Long idUsuario);

    double obtenerTotalRecaudado(Long idProyecto);

}
