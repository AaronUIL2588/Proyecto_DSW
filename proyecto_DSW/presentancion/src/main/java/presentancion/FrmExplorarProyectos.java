

package presentancion;
import entidades.Proyecto;
import servicios.ProyectoServicio;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author rober
 */
public class FrmExplorarProyectos extends JFrame {

     private final ProyectoServicio servicio;

    private JComboBox<String> cmbCategoria;
    private JTable tablaProyectos;
    private DefaultTableModel modeloTabla;
    private List<Proyecto> proyectosMostrados;

    private final String[] categorias = {
        "Todas",
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

    public FrmExplorarProyectos() {

        servicio = new ProyectoServicio();
        proyectosMostrados = new ArrayList<>();

        setTitle("Explorar proyectos");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        iniciarComponentes();
        cargarProyectos();
    }

    private void iniciarComponentes() {

        setLayout(new BorderLayout(10, 10));

        // Panel superior
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel lblTitulo = new JLabel("Proyectos disponibles:");
        cmbCategoria = new JComboBox<>(categorias);

        JButton btnFiltrar = new JButton("Filtrar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnDetalles = new JButton("Ver detalles");

        panelSuperior.add(lblTitulo);
        panelSuperior.add(cmbCategoria);
        panelSuperior.add(btnFiltrar);
        panelSuperior.add(btnActualizar);
        panelSuperior.add(btnDetalles);

        add(panelSuperior, BorderLayout.NORTH);

        // Tabla de proyectos
        modeloTabla = new DefaultTableModel(
            new Object[]{"ID", "Título", "Categoría",
                "Freelancers", "Fecha de creación"}, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaProyectos = new JTable(modeloTabla);
        tablaProyectos.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scroll = new JScrollPane(tablaProyectos);
        add(scroll, BorderLayout.CENTER);

        // Eventos
        btnFiltrar.addActionListener(e -> filtrarProyectos());

        btnActualizar.addActionListener(e -> cargarProyectos());

        btnDetalles.addActionListener(e -> mostrarDetalles());

        // Consultar los detalles con doble clic
        tablaProyectos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    mostrarDetalles();
                }
            }
        });
    }

    private void cargarProyectos() {

        try {
            List<Proyecto> proyectos =
                    servicio.listarProyectosActivos();

            mostrarEnTabla(proyectos);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Error al cargar los proyectos: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void filtrarProyectos() {

        String categoria =
                cmbCategoria.getSelectedItem().toString();

        try {

            if (categoria.equals("Todas")) {
                cargarProyectos();
            } else {
                List<Proyecto> proyectos =
                        servicio.listarPorCategoria(categoria);

                mostrarEnTabla(proyectos);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Error al filtrar proyectos: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void mostrarEnTabla(List<Proyecto> proyectos) {

        modeloTabla.setRowCount(0);

        proyectosMostrados = new ArrayList<>(proyectos);

        for (Proyecto proyecto : proyectos) {

            modeloTabla.addRow(new Object[]{
                proyecto.getId(),
                proyecto.getTitulo(),
                proyecto.getCategoria(),
                proyecto.getCantidadFreelancer(),
                proyecto.getFechaCreacion()
            });
        }
    }

    private void mostrarDetalles() {

        int fila = tablaProyectos.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                this,
                "Selecciona un proyecto primero."
            );
            return;
        }

        Long id = Long.valueOf(
            modeloTabla.getValueAt(fila, 0).toString()
        );

        try {
            Proyecto proyecto = servicio.obtenerProyecto(id);

            String detalles =
                    "Título: " + proyecto.getTitulo()
                    + "\n\nCategoría: " + proyecto.getCategoria()
                    + "\n\nDescripción:\n" + proyecto.getDescripcion()
                    + "\n\nFreelancers necesarios: "
                    + proyecto.getCantidadFreelancer()
                    + "\n\nContacto: " + proyecto.getContacto()
                    + "\n\nFecha de publicación: "
                    + proyecto.getFechaCreacion();

            JOptionPane.showMessageDialog(
                this,
                detalles,
                "Detalles del proyecto",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "No se pudieron consultar los detalles: " + e.getMessage()
            );
        }
    }
}
