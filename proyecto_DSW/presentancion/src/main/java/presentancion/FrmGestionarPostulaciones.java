
package presentancion;

import entidades.Postulacion;
import entidades.Proyecto;
import entidades.Usuario;
import servicios.PostulacionServicio;
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
public class FrmGestionarPostulaciones extends JFrame {
    private final Usuario usuario;
    private final ProyectoServicio proyectoServicio;
    private final PostulacionServicio postulacionServicio;

    private JComboBox<String> cmbProyectos;
    private JTable tabla;
    private DefaultTableModel modelo;

    private List<Proyecto> proyectos;
    private List<Postulacion> postulaciones;

    public FrmGestionarPostulaciones(Usuario usuario) {

        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Se necesita un usuario registrado.");
        }

        this.usuario = usuario;
        proyectoServicio = new ProyectoServicio();
        postulacionServicio = new PostulacionServicio();

        proyectos = new ArrayList<>();
        postulaciones = new ArrayList<>();

        setTitle("Gestionar postulaciones");
        setSize(850, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        iniciarComponentes();
        cargarProyectos();
    }

    private void iniciarComponentes() {

        setLayout(new BorderLayout(10, 10));

        JPanel superior = new JPanel(new FlowLayout(FlowLayout.LEFT));

        superior.add(new JLabel("Mis proyectos:"));

        cmbProyectos = new JComboBox<>();
        cmbProyectos.addActionListener(e -> cargarPostulaciones());

        superior.add(cmbProyectos);

        JButton btnActualizar = new JButton("Actualizar");
        superior.add(btnActualizar);

        add(superior, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
                new Object[]{
                    "ID",
                    "Freelancer",
                    "Mensaje",
                    "Fecha",
                    "Estado"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel inferior = new JPanel();

        JButton btnAceptar = new JButton("Aceptar freelancer");
        JButton btnActualizarTabla = new JButton("Actualizar lista");

        inferior.add(btnAceptar);
        inferior.add(btnActualizarTabla);

        add(inferior, BorderLayout.SOUTH);

        btnAceptar.addActionListener(e -> aceptarFreelancer());

        btnActualizar.addActionListener(e -> cargarProyectos());

        btnActualizarTabla.addActionListener(e -> cargarPostulaciones());
    }

    private void cargarProyectos() {

        try {

            cmbProyectos.removeAllItems();
            proyectos.clear();

            proyectos = proyectoServicio.listarProyectosDeUsuario(
                    usuario.getId()
            );

            for (Proyecto proyecto : proyectos) {

                cmbProyectos.addItem(
                        proyecto.getId() + " - " + proyecto.getTitulo()
                );
            }

            if (proyectos.isEmpty()) {
                modelo.setRowCount(0);

                JOptionPane.showMessageDialog(
                        this,
                        "Todavía no has publicado proyectos."
                );
            } else {
                cargarPostulaciones();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar tus proyectos: " + e.getMessage()
            );
        }
    }

    private void cargarPostulaciones() {

        int indice = cmbProyectos.getSelectedIndex();

        modelo.setRowCount(0);
        postulaciones.clear();

        if (indice < 0 || indice >= proyectos.size()) {
            return;
        }

        try {

            Proyecto proyecto = proyectos.get(indice);

            postulaciones = postulacionServicio.listarPorProyecto(
                    proyecto.getId()
            );

            for (Postulacion p : postulaciones) {

                String nombreFreelancer =
                        p.getUsuario().getNombreUsuario();

                modelo.addRow(new Object[]{
                    p.getId(),
                    nombreFreelancer,
                    p.getMensaje(),
                    p.getFecha(),
                    p.getEstadoPostulacion()
                });
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al consultar postulaciones: " + e.getMessage()
            );
        }
    }

    private void aceptarFreelancer() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona una postulación."
            );
            return;
        }

        Long idPostulacion = postulaciones.get(fila).getId();

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Deseas aceptar a este freelancer?",
                "Confirmar selección",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            postulacionServicio.seleccionarFreelancer(idPostulacion);

            JOptionPane.showMessageDialog(
                    this,
                    "El freelancer fue aceptado correctamente."
            );

            cargarPostulaciones();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo aceptar al freelancer: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
