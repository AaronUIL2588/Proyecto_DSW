
package pruebas;

import APIs.apimarket.ApiMarketValidadorCedula;
import Builders.UsuarioBuilder;
import entidades.Usuario;
import interfaces.ValidadorCedula;
import repositorios.RepositorioBase;
import repositorios.UsuarioRepositorio;
import servicios.CedulaProfesionalServicio;
import servicios.UsuarioServicio;

public class PruebaValidacionCedula {

    public static void main(String[] args) {

        // 1. Configurar el validador externo.
        ValidadorCedula validador =
                new ApiMarketValidadorCedula();

        CedulaProfesionalServicio cedulaServicio =
                new CedulaProfesionalServicio(validador);

        // 2. Configurar UsuarioServicio.
        UsuarioRepositorio usuarioRepositorio =
                new UsuarioRepositorio();

        UsuarioServicio usuarioServicio =
                new UsuarioServicio(
                        usuarioRepositorio,
                        cedulaServicio
                );

        try {
            // 3. Crear un usuario de prueba.
            String sufijo = String.valueOf(
                    System.currentTimeMillis()
            );

            Usuario usuario = new UsuarioBuilder()
                    .nombres("Luis")
                    .apellidoPaterno("Perez")
                    .apellidoMaterno("Lopez")
                    .nombreUsuario("freelancer" + sufijo)
                    .correo("freelancer" + sufijo + "@correo.com")
                    .contrasenia("1234")
                    .telefono("6441234567")
                    .build();

            usuario = usuarioServicio.registrarUsuario(usuario);

            System.out.println(
                    "Usuario registrado. ID: " + usuario.getId()
            );

            // 4. Solicitar verificación.
            boolean resultado =
                    usuarioServicio.solicitarVerificacionFreelancer(
                            usuario.getId(),
                            "2547854"
                    );

            System.out.println(
                    "Resultado de verificación: " + resultado
            );

            // 5. Consultar el perfil actualizado.
            Usuario actualizado =
                    usuarioServicio.obtenerPerfil(usuario.getId());

            System.out.println(
                    "Número de cédula: "
                    + actualizado.getNumeroCedulaProfesional()
            );

            System.out.println(
                    "Estado de verificación: "
                    + actualizado.getEstadoVerificacion()
            );

        } catch (Exception e) {

            System.out.println(
                    "Ocurrió un error durante la prueba:"
            );

            e.printStackTrace();

        } finally {
            RepositorioBase.cerrar();
        }
    }
}