/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorios;

import Builders.AportacionBuilder;
import Builders.HitoBuilder;
import Builders.PostulacionBuilder;
import Builders.ProyectoBuilder;
import Builders.UsuarioBuilder;
import entidades.Aportacion;
import entidades.Postulacion;
import entidades.Proyecto;
import entidades.Usuario;
import enumeradores.EstadoHito;
import enumeradores.EstadoPostulacion;
import enumeradores.EstadoProyecto;
import enumeradores.EstadoVerificacion;

/**
 * Prueba manual de los repositorios (sin pantallas). Crea datos con los
 * builders, los guarda y los consulta. Requiere MySQL encendido; las tablas se
 * crean solas al ejecutar (persistence.xml).
 *
 * @author Felix_isq
 */
public class PruebaRepositorios {

    public static void main(String[] args) {
        UsuarioRepositorio usuarios = new UsuarioRepositorio();
        ProyectoRepositorio proyectos = new ProyectoRepositorio();
        HitoRepositorio hitos = new HitoRepositorio();
        PostulacionRepositorio postulaciones = new PostulacionRepositorio();
        AportacionRepositorio aportaciones = new AportacionRepositorio();

        try {
            // 1. Guardar usuarios (en Usuario la cédula y la verificación son obligatorias por ahora)
            Usuario dueno = new UsuarioBuilder()
                    .nombres("Ana").apellidoPaterno("Perez").apellidoMaterno("Lopez")
                    .nombreUsuario("ana").correo("ana@correo.com").contrasenia("1234")
                    .telefono("6441234567").numeroCedulaProfesional("SIN CEDULA")
                    .estadoVerificacion(EstadoVerificacion.PENDIENTE)
                    .build();
            Usuario freelancer = new UsuarioBuilder()
                    .nombres("Beto").apellidoPaterno("Ruiz").apellidoMaterno("Soto")
                    .nombreUsuario("beto").correo("beto@correo.com").contrasenia("1234")
                    .telefono("6449876543").numeroCedulaProfesional("12345678")
                    .estadoVerificacion(EstadoVerificacion.VERIFICADO)
                    .build();
            usuarios.guardar(dueno);
            usuarios.guardar(freelancer);
            System.out.println("1. Usuarios guardados con id " + dueno.getId() + " y " + freelancer.getId());

            Usuario encontrado = usuarios.buscarPorCorreo("beto@correo.com");
            System.out.println("2. buscarPorCorreo: " + (encontrado == null ? "NO encontrado" : encontrado.getNombres()));

            // 2. Guardar un proyecto con dos hitos (los hitos se guardan junto con el proyecto)
            Proyecto proyecto = new ProyectoBuilder()
                    .titulo("Logo para cafeteria").descripcion("Diseno de logotipo")
                    .categoria("DISENO_DIGITAL").cantidadFreelancer(1).contacto("ana@correo.com")
                    .usuario(dueno)
                    .hito(new HitoBuilder().nombre("Boceto").descripcion("Tres propuestas").notas("-").build())
                    .hito(new HitoBuilder().nombre("Entrega final").descripcion("Archivos finales").notas("-").build())
                    .build();
            proyectos.guardar(proyecto);
            System.out.println("3. Proyecto guardado con id " + proyecto.getId());
            System.out.println("4. Proyectos de Ana: " + proyectos.listarPorUsuario(dueno.getId()).size()
                    + " | hitos del proyecto: " + hitos.listarPorProyecto(proyecto.getId()).size());

            // 3. Postulacion
            Postulacion postulacion = new PostulacionBuilder()
                    .mensaje("Tengo experiencia en logos").usuario(freelancer).proyecto(proyecto).build();
            postulaciones.guardar(postulacion);
            System.out.println("5. existePostulacion: " + postulaciones.existePostulacion(freelancer.getId(), proyecto.getId()));
            //postulaciones.actualizarEstado(postulacion.getId(), EstadoPostulacion.ACEPTADA);
            System.out.println("6. Estado de la postulacion: " + postulaciones.buscarPorId(postulacion.getId()).getEstadoPostulacion());

            // 4. Aportacion y total recaudado
            Aportacion aportacion = new AportacionBuilder()
                    .monto(100).comisionPlataforma(5).usuario(freelancer).proyecto(proyecto).build();
            aportaciones.guardar(aportacion);
            System.out.println("7. Total recaudado: " + aportaciones.obtenerTotalRecaudado(proyecto.getId()));

            // 5. Actualizar estados
            Long idHito = hitos.listarPorProyecto(proyecto.getId()).get(0).getId();
            //hitos.actualizarEstado(idHito, EstadoHito.COMPLETADO);
            //proyectos.actualizarEstado(proyecto.getId(), EstadoProyecto.FINALIZADO);
            System.out.println("8. Hito: " + hitos.buscarPorId(idHito).getEstado()
                    + " | Proyecto: " + proyectos.buscarPorId(proyecto.getId()).getEstado());
        } finally {
            RepositorioBase.cerrar();
        }
    }
}
