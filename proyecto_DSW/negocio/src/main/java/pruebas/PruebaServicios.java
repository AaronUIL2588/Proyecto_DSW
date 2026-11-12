/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas;

import Builders.HitoBuilder;
import Builders.PostulacionBuilder;
import Builders.ProyectoBuilder;
import Builders.UsuarioBuilder;
import entidades.Hito;
import entidades.Postulacion;
import entidades.Proyecto;
import entidades.Usuario;
import java.util.List;
import repositorios.Conexion;
import servicios.AportacionServicio;
import servicios.HitoServicio;
import servicios.NegocioException;
import servicios.PostulacionServicio;
import servicios.ProyectoServicio;
import servicios.UsuarioServicio;

/**
 * Prueba manual de la capa de negocio. Necesita MySQL encendido. Ejecutar esta
 * clase (Run File) y revisar la consola.
 *
 * @author Felix_isq
 */
public class PruebaServicios {

    public static void main(String[] args) {
        UsuarioServicio usuarios = new UsuarioServicio();
        ProyectoServicio proyectos = new ProyectoServicio();
        AportacionServicio aportaciones = new AportacionServicio();
        PostulacionServicio postulaciones = new PostulacionServicio();
        HitoServicio hitos = new HitoServicio();

        try {
            String sufijo = String.valueOf(System.currentTimeMillis() % 100000);

            // 1. Registro
            Usuario dueno = usuarios.registrarUsuario(nuevoUsuario("Ana", "ana" + sufijo));
            Usuario freelancer = usuarios.registrarUsuario(nuevoUsuario("Luis", "luis" + sufijo));
            Usuario donador = usuarios.registrarUsuario(nuevoUsuario("Marta", "marta" + sufijo));
            System.out.println("1. Registrados: " + dueno.getId() + ", " + freelancer.getId() + ", " + donador.getId());

            try {
                usuarios.registrarUsuario(nuevoUsuario("Ana", "ana" + sufijo));
            } catch (NegocioException e) {
                System.out.println("   Correo repetido rechazado: " + e.getMessage());
            }

            // 2. Verificación del freelancer
            System.out.println("2. Cédula inválida: " + usuarios.solicitarVerificacionFreelancer(freelancer.getId(), "123"));
            System.out.println("   Cédula válida: " + usuarios.solicitarVerificacionFreelancer(freelancer.getId(), "12345678"));

            // 3. Proyecto con hitos
            Proyecto proyecto = new ProyectoBuilder()
                    .titulo("Sitio web para la escuela").descripcion("Página informativa")
                    .categoria("Tecnología").cantidadFreelancer(1).contacto("ana@correo.com")
                    .usuario(dueno)
                    .hito(new HitoBuilder().nombre("Diseño").descripcion("Maqueta").notas("").build())
                    .hito(new HitoBuilder().nombre("Desarrollo").descripcion("Programar").notas("").build())
                    .build();
            proyectos.crearProyecto(proyecto);
            System.out.println("3. Proyecto creado id=" + proyecto.getId() + " estado=" + proyecto.getEstado());
            System.out.println("   Activos en catálogo: " + proyectos.listarProyectosActivos().size());

            // 4. Aportaciones
            aportaciones.donar(donador.getId(), proyecto.getId(), 1000);
            System.out.println("4. Total recaudado: " + proyectos.obtenerTotalRecaudado(proyecto.getId())
                    + " (comisión de 1000 = " + aportaciones.calcularComision(1000) + ")");
            try {
                aportaciones.donar(dueno.getId(), proyecto.getId(), 500);
            } catch (NegocioException e) {
                System.out.println("   Aportar al propio proyecto rechazado: " + e.getMessage());
            }

            // 5. Postulación y selección
            Postulacion p = new PostulacionBuilder().mensaje("Me interesa").usuario(freelancer).proyecto(proyecto).build();
            postulaciones.enviarPostulacion(p);
            try {
                postulaciones.enviarPostulacion(new PostulacionBuilder().mensaje("otra vez").usuario(freelancer).proyecto(proyecto).build());
            } catch (NegocioException e) {
                System.out.println("5. Postulación repetida rechazada: " + e.getMessage());
            }
            postulaciones.seleccionarFreelancer(p.getId());
            System.out.println("   Postulación aceptada.");

            // 6. Hitos
            List<Hito> lista = hitos.listarPorProyecto(proyecto.getId());
            System.out.println("6. Finalizar con hitos pendientes: " + proyectos.finalizarProyecto(proyecto.getId()));
            for (Hito h : lista) {
                hitos.marcarParaAprobacion(h.getId());
                hitos.aprobarHito(h.getId());
            }
            System.out.println("   Finalizar con todos aprobados: " + proyectos.finalizarProyecto(proyecto.getId()));
            System.out.println("   Estado final: " + proyectos.obtenerProyecto(proyecto.getId()).getEstado());
        } catch (NegocioException e) {
            System.out.println("ERROR DE NEGOCIO: " + e.getMessage());
        } finally {
            Conexion.cerrar();
        }
    }

    private static Usuario nuevoUsuario(String nombre, String nombreUsuario) {
        return new UsuarioBuilder().nombres(nombre).apellidoPaterno("Perez").apellidoMaterno("Lopez")
                .nombreUsuario(nombreUsuario).correo(nombreUsuario + "@correo.com")
                .contrasenia("1234").telefono("6441234567").build();
    }
}
