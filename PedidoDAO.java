import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PedidoDAO {


    public List<Pedido> leerTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT id, cliente, direccion, estado, repartidor_id FROM pedidos";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setId(rs.getInt("id"));
                p.setCliente(rs.getString("cliente"));
                p.setDireccion(rs.getString("direccion"));
                p.setEstado(rs.getString("estado"));
                p.setRepartidorId(rs.getInt("repartidor_id"));
                lista.add(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar pedidos: " + e.getMessage());
        }
        return lista;
    }


    public boolean insertar(Pedido pedido) {
        String sql = "INSERT INTO pedidos (cliente, direccion, estado, repartidor_id) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, pedido.getCliente());
            ps.setString(2, pedido.getDireccion());
            ps.setString(3, pedido.getEstado());
            ps.setInt(4, pedido.getRepartidorId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar pedido: " + e.getMessage());
            return false;
        }
    }


    public boolean actualizar(Pedido pedido) {
        String sql = "UPDATE pedidos SET cliente = ?, direccion = ?, estado = ?, repartidor_id = ? WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, pedido.getCliente());
            ps.setString(2, pedido.getDireccion());
            ps.setString(3, pedido.getEstado());
            ps.setInt(4, pedido.getRepartidorId());
            ps.setInt(5, pedido.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar pedido: " + e.getMessage());
            return false;
        }
    }


    public boolean eliminar(int id) {
        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar pedido: " + e.getMessage());
            return false;
        }
    }
}