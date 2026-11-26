
package presentancion;

import entidades.Postulacion;
import entidades.Proyecto;
import entidades.Usuario;
import servicios.PostulacionServicio;
import servicios.ProyectoServicio;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author rober
 */
public class FrmEnviarPostulacion extends JFrame {
    private final Usuario usuario;
    private final ProyectoServicio proyectoServicio;
    private final PostulacionServicio postulacionServicio;

    private JComboBox<String> cmbProyectos;
    private JTextArea txtMensaje;
    private List<Proyecto> proyectos;

    public FrmEnviarPostulacion(Usuario usuario) {

        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Se necesita un usuario registrado.");
        }

        this.usuario = usuario;
        proyectoServicio = new ProyectoServicio();
        postulacionServicio = new PostulacionServicio();
        proyectos = new ArrayList<>();

        setTitle("Enviar postulación");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        iniciarComponentes();
        cargarProyectos();
    }

    private void iniciarComponentes() {

        setLayout(new BorderLayout(10, 10));

        JPanel formulario = new JPanel(new GridLayout(2, 1, 10, 10));

        formulario.add(new JLabel("Selecciona el proyecto:"));

        cmbProyectos = new JComboBox<>();
        formulario.add(cmbProyectos);

        add(formulario, BorderLayout.NORTH);

        JPanel panelMensaje = new JPanel(new BorderLayout(5, 5));

        panelMensaje.add(
                new JLabel("Mensaje de presentación:"),
                BorderLayout.NORTH
        );

        txtMensaje = new JTextArea(8, 30);
        txtMensaje.setLineWrap(true);
        txtMensaje.setWrapStyleWord(true);

        panelMensaje.add(
                new JScrollPane(txtMensaje),
                BorderLayout.CENTER
        );

        add(panelMensaje, BorderLayout.CENTER);

        JPanel botones = new JPanel();

        JButton btnEnviar = new JButton("Enviar postulación");
        JButton btnCancelar = new JButton("Cancelar");

        botones.add(btnEnviar);
        botones.add(btnCancelar);

        add(botones, BorderLayout.SOUTH);

        btnEnviar.addActionListener(e -> enviarPostulacion());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void cargarProyectos() {

        try {

            cmbProyectos.removeAllItems();
            proyectos.clear();

            for (Proyecto proyecto : proyectoServicio.listarProyectosActivos()) {

                if (!proyecto.getUsuario().getId().equals(usuario.getId())) {

                    proyectos.add(proyecto);

                    cmbProyectos.addItem(
                            proyecto.getId() + " - " + proyecto.getTitulo()
                    );
                }
            }

            if (proyectos.isEmpty()) {
                cmbProyectos.addItem("No hay proyectos disponibles");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar los proyectos: " + e.getMessage()
            );
        }
    }

    private void enviarPostulacion() {

        int indice = cmbProyectos.getSelectedIndex();

        if (indice < 0 || indice >= proyectos.size()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay un proyecto seleccionado."
            );
            return;
        }

        String mensaje = txtMensaje.getText().trim();

        if (mensaje.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Escribe un mensaje de presentación."
            );
            return;
        }

        try {

            Postulacion postulacion = new Postulacion();

            postulacion.setMensaje(mensaje);
            postulacion.setUsuario(usuario);
            postulacion.setProyecto(proyectos.get(indice));

            postulacionServicio.enviarPostulacion(postulacion);

            JOptionPane.showMessageDialog(
                    this,
                    "Tu postulación se envió correctamente."
            );

            txtMensaje.setText("");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo enviar la postulación: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
