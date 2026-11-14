/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Proyecto;
import enumeradores.EstadoProyecto;
import java.util.List;

/**
 * Operaciones de base de datos para la entidad Proyecto. Al guardar un proyecto
 * se guardan también sus hitos.
 *
 * @author Felix_isq
 */
public interface IProyectoRepositorio {

    void guardar(Proyecto proyecto);

    Proyecto buscarPorId(Long id);

    List<Proyecto> listarProyectos();

    List<Proyecto> listarPorUsuario(Long idUsuario);

    List<Proyecto> buscarPorCategoria(String categoria);

    void actualizarEstado(Long id, EstadoProyecto estado);

}
