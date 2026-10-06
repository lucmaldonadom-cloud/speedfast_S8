import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FrmEntregas extends JFrame {
    private JTable tablaEntregas;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> cmbPedidos;
    private JTextField txtFecha, txtObservaciones;
    private EntregaDAO entregaDAO;
    private PedidoDAO pedidoDAO;
    private List<Pedido> listaPedidos;

    public FrmEntregas() {
        entregaDAO = new EntregaDAO();
        pedidoDAO = new PedidoDAO();

        setTitle("SpeedFast - Gestión de Entregas");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE ENTREGAS");
        lblTitulo.setBounds(230, 20, 200, 25);
        add(lblTitulo);

        JLabel lblPedido = new JLabel("ID Pedido:");
        lblPedido.setBounds(30, 70, 100, 25);
        add(lblPedido);

        cmbPedidos = new JComboBox<>();
        cmbPedidos.setBounds(140, 70, 200, 25);
        add(cmbPedidos);
        cargarPedidosCombo();

        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        lblFecha.setBounds(30, 110, 110, 25);
        add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(140, 110, 200, 25);
        add(txtFecha);

        JLabel lblObs = new JLabel("Observaciones:");
        lblObs.setBounds(30, 150, 100, 25);
        add(lblObs);

        txtObservaciones = new JTextField();
        txtObservaciones.setBounds(140, 150, 200, 25);
        add(txtObservaciones);

        JButton btnGuardar = new JButton("Registrar Entrega");
        btnGuardar.setBounds(380, 70, 210, 35);
        add(btnGuardar);

        modeloTabla = new DefaultTableModel(new String[]{"ID Entrega", "ID Pedido", "Fecha", "Observaciones"}, 0);
        tablaEntregas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaEntregas);
        scrollPane.setBounds(30, 210, 560, 180);
        add(scrollPane);

        cargarTabla();


        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbPedidos.getSelectedIndex() >= 0 && !txtFecha.getText().trim().isEmpty()) {
                    int index = cmbPedidos.getSelectedIndex();
                    Pedido pedidoSeleccionado = listaPedidos.get(index);

                    Entrega entrega = new Entrega();
                    entrega.setPedidoId(pedidoSeleccionado.getId());
                    entrega.setFechaEntrega(txtFecha.getText().trim());
                    entrega.setObservaciones(txtObservaciones.getText().trim());

                    if (entregaDAO.insertar(entrega)) {
                        JOptionPane.showMessageDialog(null, "Entrega registrada con éxito.");
                        txtFecha.setText("");
                        txtObservaciones.setText("");
                        cargarTabla();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione un pedido y complete la fecha.");
                }
            }
        });
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Entrega> lista = entregaDAO.leerTodos();
        for (Entrega ent : lista) {
            modeloTabla.addRow(new Object[]{ent.getId(), ent.getPedidoId(), ent.getFechaEntrega(), ent.getObservaciones()});
        }
    }

    private void cargarPedidosCombo() {
        listaPedidos = pedidoDAO.leerTodos();
        cmbPedidos.removeAllItems();
        for (Pedido p : listaPedidos) {
            cmbPedidos.addItem("ID: " + p.getId() + " - " + p.getCliente());
        }
    }
}
