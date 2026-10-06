import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmRepartidores extends JFrame {
    private JTable tablaRepartidores;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre;
    private RepartidorDAO repartidorDAO;

    public FrmRepartidores() {
        repartidorDAO = new RepartidorDAO();

        setTitle("SpeedFast - Gestión de Repartidores");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE REPARTIDORES");
        lblTitulo.setBounds(150, 20, 220, 25);
        add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 70, 70, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 70, 200, 25);
        add(txtNombre);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(320, 70, 120, 25);
        add(btnGuardar);

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
        tablaRepartidores = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaRepartidores);
        scrollPane.setBounds(30, 120, 410, 210);
        add(scrollPane);

        // Cargar los datos existentes al abrir
        cargarTabla();

        // Acción del botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText().trim();
                if (!nombre.isEmpty()) {
                    Repartidor r = new Repartidor();
                    r.setNombre(nombre);
                    if (repartidorDAO.insertar(r)) {
                        JOptionPane.showMessageDialog(null, "Repartidor guardado con éxito.");
                        txtNombre.setText("");
                        cargarTabla();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre.");
                }
            }
        });
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Repartidor> lista = repartidorDAO.leerTodos();
        for (Repartidor r : lista) {
            modeloTabla.addRow(new Object[]{r.getId(), r.getNombre()});
        }
    }
}
