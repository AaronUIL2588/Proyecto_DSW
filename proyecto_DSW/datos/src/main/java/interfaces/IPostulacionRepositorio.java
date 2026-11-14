/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Postulacion;
import enumeradores.EstadoPostulacion;
import java.util.List;

/**
 * Operaciones de base de datos para la entidad Postulacion.
 *
 * @author Felix_isq
 */
public interface IPostulacionRepositorio {

    void guardar(Postulacion postulacion);

    Postulacion buscarPorId(Long id);

    List<Postulacion> listarPorProyecto(Long idProyecto);

    List<Postulacion> listarPorUsuario(Long idUsuario);

    void actualizarEstado(Long id, EstadoPostulacion estado);

    /**
     * Indica si el usuario ya se postuló a ese proyecto.
     */
    boolean existePostulacion(Long idUsuario, Long idProyecto);

}
