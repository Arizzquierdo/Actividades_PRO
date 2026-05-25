
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Modelo {

    private final String url = "jdbc:mariadb://localhost:3306/sopa_letras";
    private final String usuario = "root";
    private final String password = "";

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(url, usuario, password);
    }

    public void insertarPalabra(String palabra) {
        String sql = "INSERT INTO palabras (palabra) VALUES (?)";

        try (Connection conexion = conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, palabra.toUpperCase());
            ps.executeUpdate();

            System.out.println("Palabra insertada correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al insertar palabra: " + e.getMessage());
        }
    }

    public void eliminarPalabra(String palabra) {
        String sql = "DELETE FROM palabras WHERE palabra = ?";

        try (Connection conexion = conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, palabra.toUpperCase());
            int filas = ps.executeUpdate();

            if (filas > 0) {
                System.out.println("Palabra eliminada correctamente.");
            } else {
                System.out.println("La palabra no existe en la base de datos.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar palabra: " + e.getMessage());
        }
    }

    public ArrayList<String> obtenerPalabras() {
        ArrayList<String> listaPalabras = new ArrayList<>();

        String sql = "SELECT palabra FROM palabras";

        try (Connection conexion = conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                listaPalabras.add(rs.getString("palabra"));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener palabras: " + e.getMessage());
        }

        return listaPalabras;
    }
}