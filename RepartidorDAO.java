import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class RepartidorDAO {


    public List<Repartidor> leerTodos() {
        List<Repartidor> lista = new ArrayList<>();
        String sql = "SELECT id, nombre FROM repartidores";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Repartidor r = new Repartidor();
                r.setId(rs.getInt("id"));
                r.setNombre(rs.getString("nombre"));
                lista.add(r);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar repartidores: " + e.getMessage());
        }
        return lista;
    }


    public boolean insertar(Repartidor repartidor) {
        String sql = "INSERT INTO repartidores (nombre) VALUES (?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, repartidor.getNombre());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar repartidor: " + e.getMessage());
            return false;
        }
    }
}
