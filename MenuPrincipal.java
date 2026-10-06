import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("SpeedFast - Sistema de Gestión");
        setSize(400, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("SPEEDFAST - MENÚ PRINCIPAL");
        lblTitulo.setBounds(90, 20, 230, 30);
        add(lblTitulo);

        JButton btnPedidos = new JButton("Gestionar Pedidos");
        btnPedidos.setBounds(90, 80, 210, 40);
        add(btnPedidos);

        JButton btnRepartidores = new JButton("Gestionar Repartidores");
        btnRepartidores.setBounds(90, 140, 210, 40);
        add(btnRepartidores);

        JButton btnEntregas = new JButton("Gestionar Entregas");
        btnEntregas.setBounds(90, 200, 210, 40);
        add(btnEntregas);

        btnPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmPedidos().setVisible(true);
            }
        });

        btnRepartidores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmRepartidores().setVisible(true);
            }
        });

        btnEntregas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FrmEntregas().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }
}
