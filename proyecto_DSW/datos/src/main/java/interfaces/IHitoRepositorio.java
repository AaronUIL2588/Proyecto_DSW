/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Hito;
import enumeradores.EstadoHito;
import java.util.List;

/**
 * Operaciones de base de datos para la entidad Hito.
 *
 * @author Felix_isq
 */
public interface IHitoRepositorio {

    void guardar(Hito hito);

    Hito buscarPorId(Long id);

    List<Hito> listarPorProyecto(Long idProyecto);

    void actualizar(Hito hito);

    void actualizarEstado(Long id, EstadoHito estado);

}
