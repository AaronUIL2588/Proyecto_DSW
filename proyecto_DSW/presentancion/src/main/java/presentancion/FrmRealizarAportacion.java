
package presentancion;

import entidades.Proyecto;
import entidades.Usuario;
import servicios.AportacionServicio;
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
public class FrmRealizarAportacion extends JFrame {
    private final Usuario usuario;
    private final ProyectoServicio proyectoServicio;
    private final AportacionServicio aportacionServicio;

    private JComboBox<String> cmbProyectos;
    private JTextField txtMonto;
    private JLabel lblComision;

    private List<Proyecto> proyectos;

    public FrmRealizarAportacion(Usuario usuario) {

        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Se necesita un usuario registrado.");
        }

        this.usuario = usuario;
        proyectoServicio = new ProyectoServicio();
        aportacionServicio = new AportacionServicio();
        proyectos = new ArrayList<>();

        setTitle("Realizar aportación");
        setSize(550, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        iniciarComponentes();
        cargarProyectos();
    }

    private void iniciarComponentes() {

        setLayout(new BorderLayout(10, 10));

        JPanel formulario = new JPanel(new GridLayout(3, 2, 10, 15));

        formulario.add(new JLabel("Proyecto:"));

        cmbProyectos = new JComboBox<>();
        formulario.add(cmbProyectos);

        formulario.add(new JLabel("Monto a aportar ($):"));

        txtMonto = new JTextField();
        formulario.add(txtMonto);

        formulario.add(new JLabel("Comisión de plataforma (5%):"));

        lblComision = new JLabel("$0.00");
        formulario.add(lblComision);

        add(formulario, BorderLayout.CENTER);

        JPanel botones = new JPanel();

        JButton btnCalcular = new JButton("Calcular comisión");
        JButton btnAportar = new JButton("Confirmar aportación");

        botones.add(btnCalcular);
        botones.add(btnAportar);

        add(botones, BorderLayout.SOUTH);

        btnCalcular.addActionListener(e -> calcularComision());
        btnAportar.addActionListener(e -> realizarAportacion());
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
                    "Error al cargar proyectos: " + e.getMessage()
            );
        }
    }

    private Double leerMonto() {

        try {

            double monto = Double.parseDouble(txtMonto.getText().trim());

            if (monto <= 0) {
                throw new NumberFormatException();
            }

            return monto;

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingresa un monto numérico mayor a cero."
            );

            return null;
        }
    }

    private void calcularComision() {

        Double monto = leerMonto();

        if (monto == null) {
            return;
        }

        double comision = aportacionServicio.calcularComision(monto);

        lblComision.setText(String.format("$%.2f", comision));
    }

    private void realizarAportacion() {

        int indice = cmbProyectos.getSelectedIndex();

        if (indice < 0 || indice >= proyectos.size()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No hay un proyecto disponible."
            );

            return;
        }

        Double monto = leerMonto();

        if (monto == null) {
            return;
        }

        double comision = aportacionServicio.calcularComision(monto);

        int opcion = JOptionPane.showConfirmDialog(
                this,
                String.format(
                        "Aportación: $%.2f%nComisión (5%%): $%.2f%n¿Deseas continuar?",
                        monto,
                        comision
                ),
                "Confirmar aportación",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            aportacionServicio.donar(
                    usuario.getId(),
                    proyectos.get(indice).getId(),
                    monto
            );

            JOptionPane.showMessageDialog(
                    this,
                    "La aportación se registró correctamente."
            );

            txtMonto.setText("");
            lblComision.setText("$0.00");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo realizar la aportación: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
