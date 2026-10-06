import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FrmPedidos extends JFrame {
    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private JTextField txtId, txtCliente, txtDireccion, txtEstado;
    private JComboBox<Repartidor> cmbRepartidor;
    private PedidoDAO pedidoDAO;
    private RepartidorDAO repartidorDAO;

    public FrmPedidos() {
        pedidoDAO = new PedidoDAO();
        repartidorDAO = new RepartidorDAO();

        setTitle("SpeedFast - Gestión de Pedidos");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE PEDIDOS");
        lblTitulo.setBounds(280, 20, 200, 25);
        add(lblTitulo);


        txtId = new JTextField();

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(30, 70, 80, 25);
        add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(120, 70, 180, 25);
        add(txtCliente);

        JLabel lblDireccion = new JLabel("Dirección:");
        lblDireccion.setBounds(30, 110, 80, 25);
        add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(120, 110, 180, 25);
        add(txtDireccion);

        JLabel lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(30, 150, 80, 25);
        add(lblEstado);

        txtEstado = new JTextField();
        txtEstado.setBounds(120, 150, 180, 25);
        add(txtEstado);

        JLabel lblRepartidor = new JLabel("Repartidor:");
        lblRepartidor.setBounds(30, 190, 80, 25);
        add(lblRepartidor);

        cmbRepartidor = new JComboBox<>();
        cmbRepartidor.setBounds(120, 190, 180, 25);
        add(cmbRepartidor);
        cargarRepartidoresCombo();


        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(330, 70, 120, 30);
        add(btnGuardar);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(330, 110, 120, 30);
        add(btnActualizar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(330, 150, 120, 30);
        add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(330, 190, 120, 30);
        add(btnLimpiar);


        modeloTabla = new DefaultTableModel(new String[]{"ID", "Cliente", "Dirección", "Estado", "ID Repartidor"}, 0);
        tablaPedidos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPedidos);
        scrollPane.setBounds(30, 240, 680, 190);
        add(scrollPane);

        cargarTabla();


        tablaPedidos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaPedidos.getSelectedRow();
                if (fila >= 0) {
                    txtId.setText(modeloTabla.getValueAt(fila, 0).toString());
                    txtCliente.setText(modeloTabla.getValueAt(fila, 1).toString());
                    txtDireccion.setText(modeloTabla.getValueAt(fila, 2).toString());
                    txtEstado.setText(modeloTabla.getValueAt(fila, 3).toString());

                    int repartidorId = Integer.parseInt(modeloTabla.getValueAt(fila, 4).toString());
                    for (int i = 0; i < cmbRepartidor.getItemCount(); i++) {
                        Repartidor r = cmbRepartidor.getItemAt(i);
                        if (r.getId() == repartidorId) {
                            cmbRepartidor.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    Pedido p = new Pedido();
                    p.setCliente(txtCliente.getText().trim());
                    p.setDireccion(txtDireccion.getText().trim());
                    p.setEstado(txtEstado.getText().trim());
                    Repartidor rSeleccionado = (Repartidor) cmbRepartidor.getSelectedItem();
                    p.setRepartidorId(rSeleccionado.getId());

                    if (pedidoDAO.insertar(p)) {
                        JOptionPane.showMessageDialog(null, "Pedido registrado con éxito.");
                        limpiarCampos();
                        cargarTabla();
                    }
                }
            }
        });


        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtId.getText().isEmpty() && validarCampos()) {
                    Pedido p = new Pedido();
                    p.setId(Integer.parseInt(txtId.getText()));
                    p.setCliente(txtCliente.getText().trim());
                    p.setDireccion(txtDireccion.getText().trim());
                    p.setEstado(txtEstado.getText().trim());
                    Repartidor rSeleccionado = (Repartidor) cmbRepartidor.getSelectedItem();
                    p.setRepartidorId(rSeleccionado.getId());

                    if (pedidoDAO.actualizar(p)) {
                        JOptionPane.showMessageDialog(null, "Pedido actualizado con éxito.");
                        limpiarCampos();
                        cargarTabla();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un pedido de la tabla para actualizar.");
                }
            }
        });


        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtId.getText().isEmpty()) {
                    int id = Integer.parseInt(txtId.getText());
                    int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este pedido?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        if (pedidoDAO.eliminar(id)) {
                            JOptionPane.showMessageDialog(null, "Pedido eliminado.");
                            limpiarCampos();
                            cargarTabla();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un pedido de la tabla para eliminar.");
                }
            }
        });


        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        List<Pedido> lista = pedidoDAO.leerTodos();
        for (Pedido p : lista) {
            modeloTabla.addRow(new Object[]{p.getId(), p.getCliente(), p.getDireccion(), p.getEstado(), p.getRepartidorId()});
        }
    }

    private void cargarRepartidoresCombo() {
        List<Repartidor> lista = repartidorDAO.leerTodos();
        for (Repartidor r : lista) {
            cmbRepartidor.addItem(r);
        }
    }

    private boolean validarCampos() {
        if (txtCliente.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty() || txtEstado.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos de texto.");
            return false;
        }
        if (cmbRepartidor.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un repartidor.");
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtCliente.setText("");
        txtDireccion.setText("");
        txtEstado.setText("");
        if (cmbRepartidor.getItemCount() > 0) {
            cmbRepartidor.setSelectedIndex(0);
        }
        tablaPedidos.clearSelection();
    }
}
