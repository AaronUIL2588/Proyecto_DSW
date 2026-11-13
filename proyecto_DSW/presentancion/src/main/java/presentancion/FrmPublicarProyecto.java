
package presentancion;
import entidades.Proyecto;
import entidades.Usuario;
import servicios.ProyectoServicio;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import javax.swing.*;

/**
 *
 * @author rober
 */
public class FrmPublicarProyecto extends JFrame {
    private final ProyectoServicio servicio;
    private final Usuario usuarioActual;

    private JTextField txtTitulo;
    private JTextArea txtDescripcion;
    private JComboBox<String> cmbCategoria;
    private JSpinner spnFreelancers;
    private JTextField txtContacto;

    private final String[] categorias = {
        "Mantenimiento",
        "Diseño Digital",
        "Limpieza",
        "Marketing/Publicidad",
        "Contenido Multimedia",
        "Educación",
        "Eventos",
        "Cuidado",
        "Desarrollo de software",
        "Transporte y logística"
    };

    public FrmPublicarProyecto(Usuario usuario) {

        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException(
                "Se necesita un usuario registrado para publicar."
            );
        }

        usuarioActual = usuario;
        servicio = new ProyectoServicio();

        setTitle("Publicar proyecto");
        setSize(550, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        iniciarComponentes();
    }

    private void iniciarComponentes() {

        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(
            new GridLayout(5, 2, 10, 15)
        );

        // Título
        panelFormulario.add(new JLabel("Título del proyecto:"));

        txtTitulo = new JTextField();
        panelFormulario.add(txtTitulo);

        // Categoría
        panelFormulario.add(new JLabel("Categoría:"));

        cmbCategoria = new JComboBox<>(categorias);
        panelFormulario.add(cmbCategoria);

        // Cantidad de freelancers
        panelFormulario.add(new JLabel("Freelancers necesarios:"));

        spnFreelancers = new JSpinner(
            new SpinnerNumberModel(1, 1, 100, 1)
        );
        panelFormulario.add(spnFreelancers);

        // Info del Contacto
        panelFormulario.add(new JLabel("Información de contacto:"));

        txtContacto = new JTextField();
        panelFormulario.add(txtContacto);

        // Descripción
        panelFormulario.add(new JLabel("Descripción:"));

        txtDescripcion = new JTextArea(5, 20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);

        JScrollPane scrollDescripcion =
                new JScrollPane(txtDescripcion);

        panelFormulario.add(scrollDescripcion);

        add(panelFormulario, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();

        JButton btnPublicar = new JButton("Publicar proyecto");
        JButton btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnPublicar);
        panelBotones.add(btnCancelar);

        add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        btnPublicar.addActionListener(e -> publicarProyecto());

        btnCancelar.addActionListener(e -> dispose());
    }

    private void publicarProyecto() {

        try {

            // Obtener los datos del formulario
            String titulo = txtTitulo.getText().trim();

            String descripcion =
                    txtDescripcion.getText().trim();

            String categoria =
                    cmbCategoria.getSelectedItem().toString();

            int cantidadFreelancer =
                    (Integer) spnFreelancers.getValue();

            String contacto =
                    txtContacto.getText().trim();

            // Validar espacios
            if (titulo.isEmpty()
                    || descripcion.isEmpty()
                    || contacto.isEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Completa todos los espacios."
                );

                return;
            }

            // Crear el proyecto
            Proyecto proyecto = new Proyecto();

            proyecto.setTitulo(titulo);
            proyecto.setDescripcion(descripcion);
            proyecto.setCategoria(categoria);
            proyecto.setCantidadFreelancer(cantidadFreelancer);
            proyecto.setContacto(contacto);
            proyecto.setFechaCreacion(LocalDate.now());
            proyecto.setUsuario(usuarioActual);

            // Guardar mediante el servicio
            servicio.crearProyecto(proyecto);

            JOptionPane.showMessageDialog(
                this,
                "¡Tu proyecto se publicó correctamente!",
                "Proyecto publicado",
                JOptionPane.INFORMATION_MESSAGE
            );

            limpiarFormulario();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "No se pudo publicar el proyecto: "
                + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limpiarFormulario() {

        txtTitulo.setText("");
        txtDescripcion.setText("");
        cmbCategoria.setSelectedIndex(0);
        spnFreelancers.setValue(1);
        txtContacto.setText("");
    }
}
