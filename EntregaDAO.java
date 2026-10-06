import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EntregaDAO {


    public List<Entrega> leerTodos() {
        List<Entrega> lista = new ArrayList<>();
        String sql = "SELECT id, pedido_id, fecha_entrega, observaciones FROM entregas";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Entrega e = new Entrega();
                e.setId(rs.getInt("id"));
                e.setPedidoId(rs.getInt("pedido_id"));
                e.setFechaEntrega(rs.getString("fecha_entrega"));
                e.setObservaciones(rs.getString("observaciones"));
                lista.add(e);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar entregas: " + e.getMessage());
        }
        return lista;
    }


    public boolean insertar(Entrega entrega) {
        String sql = "INSERT INTO entregas (pedido_id, fecha_entrega, observaciones) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, entrega.getPedidoId());
            ps.setString(2, entrega.getFechaEntrega());
            ps.setString(3, entrega.getObservaciones());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar entrega: " + e.getMessage());
            return false;
        }
    }
}
