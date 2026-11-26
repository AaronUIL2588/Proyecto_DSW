
package presentancion;
import entidades.Usuario;
import servicios.UsuarioServicio;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author rober
 */
public class FrmMenuPrincipal extends JFrame{
    private final Usuario usuario;

    public FrmMenuPrincipal(Usuario usuario) {

        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Debes iniciar sesión.");
        }

        this.usuario = usuario;

        setTitle("Plataforma de proyectos - Menú principal");
        setSize(480, 470);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        iniciarComponentes();
    }

    private void iniciarComponentes() {

        setLayout(new BorderLayout(12, 12));

        JLabel bienvenida = new JLabel(
                "Bienvenido, " + usuario.getNombreUsuario(),
                SwingConstants.CENTER
        );

        bienvenida.setBorder(
                BorderFactory.createEmptyBorder(18, 5, 10, 5)
        );

        add(bienvenida, BorderLayout.NORTH);

        JPanel opciones = new JPanel(new GridLayout(5, 1, 10, 10));

        JButton btnExplorar = new JButton("Explorar proyectos");
        JButton btnPublicar = new JButton("Publicar proyecto");
        JButton btnPostular = new JButton("Enviar postulación");
        JButton btnAportar = new JButton("Realizar aportación");
        JButton btnGestionar = new JButton("Gestionar postulaciones");

        opciones.add(btnExplorar);
        opciones.add(btnPublicar);
        opciones.add(btnPostular);
        opciones.add(btnAportar);
        opciones.add(btnGestionar);

        opciones.setBorder(
                BorderFactory.createEmptyBorder(10, 35, 20, 35)
        );

        add(opciones, BorderLayout.CENTER);

        btnExplorar.addActionListener(e ->
                new FrmExplorarProyectos().setVisible(true)
        );

        btnPublicar.addActionListener(e ->
                new FrmPublicarProyecto(usuario).setVisible(true)
        );

        btnPostular.addActionListener(e ->
                new FrmEnviarPostulacion(usuario).setVisible(true)
        );

        btnAportar.addActionListener(e ->
                new FrmRealizarAportacion(usuario).setVisible(true)
        );

        btnGestionar.addActionListener(e ->
                new FrmGestionarPostulaciones(usuario).setVisible(true)
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> iniciarSesion());
    }

    private static void iniciarSesion() {

        JTextField txtCorreo = new JTextField();
        JPasswordField txtContrasenia = new JPasswordField();

        JPanel panel = new JPanel(new GridLayout(2, 2, 8, 8));

        panel.add(new JLabel("Correo:"));
        panel.add(txtCorreo);

        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContrasenia);

        int opcion = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Iniciar sesión",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcion != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            Usuario usuario = new UsuarioServicio().iniciarSesion(
                    txtCorreo.getText().trim(),
                    new String(txtContrasenia.getPassword())
            );

            new FrmMenuPrincipal(usuario).setVisible(true);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "No se pudo iniciar sesión: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            iniciarSesion();
        }
    }    
}
