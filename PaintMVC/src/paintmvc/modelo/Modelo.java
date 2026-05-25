package paintmvc.modelo;

import paintmvc.figuras.Figura;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Modelo {

    private Connection conexion;

    public Modelo() {
        conectar();
        crearTablas();
    }

    private void conectar() {
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:Paint.db");
            System.out.println("Base de datos conectada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
            e.printStackTrace();
        }
    }

    private void crearTablas() {
        String tablaDibujos =
                "CREATE TABLE IF NOT EXISTS dibujos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT" +
                ");";

        String tablaFiguras =
                "CREATE TABLE IF NOT EXISTS figuras (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dibujo_id INTEGER," +
                "tipo TEXT," +
                "x1 INTEGER," +
                "y1 INTEGER," +
                "x2 INTEGER," +
                "y2 INTEGER," +
                "lados INTEGER," +
                "color_linea INTEGER," +
                "color_relleno INTEGER," +
                "relleno INTEGER," +
                "orden_figura INTEGER," +
                "FOREIGN KEY(dibujo_id) REFERENCES dibujos(id)" +
                ");";

        try {
            Statement st = conexion.createStatement();
            st.execute(tablaDibujos);
            st.execute(tablaFiguras);
            System.out.println("Tablas creadas correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear tablas.");
            e.printStackTrace();
        }
    }

    public void guardarDibujo(String nombre, ArrayList<Figura> figuras) {
        try {
            String sqlDibujo = "INSERT INTO dibujos(nombre) VALUES(?)";

            PreparedStatement ps = conexion.prepareStatement(sqlDibujo, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nombre);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            int dibujoId = -1;

            if (rs.next()) {
                dibujoId = rs.getInt(1);
            }

            String sqlFigura =
                    "INSERT INTO figuras " +
                    "(dibujo_id, tipo, x1, y1, x2, y2, lados, color_linea, color_relleno, relleno, orden_figura) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement psFigura = conexion.prepareStatement(sqlFigura);

            for (Figura f : figuras) {
                psFigura.setInt(1, dibujoId);
                psFigura.setString(2, f.tipo);
                psFigura.setInt(3, f.x1);
                psFigura.setInt(4, f.y1);
                psFigura.setInt(5, f.x2);
                psFigura.setInt(6, f.y2);
                psFigura.setInt(7, f.lados);
                psFigura.setInt(8, f.colorLinea.getRGB());
                psFigura.setInt(9, f.colorRelleno.getRGB());

                if (f.relleno) {
                    psFigura.setInt(10, 1);
                } else {
                    psFigura.setInt(10, 0);
                }

                psFigura.setInt(11, f.orden);

                psFigura.executeUpdate();
            }

            System.out.println("Dibujo guardado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al guardar dibujo.");
            e.printStackTrace();
        }
    }

    public ArrayList<Figura> cargarUltimoDibujo() {
        ArrayList<Figura> figuras = new ArrayList<>();

        String sql =
                "SELECT * FROM figuras " +
                "WHERE dibujo_id = (SELECT MAX(id) FROM dibujos) " +
                "ORDER BY orden_figura ASC";

        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Figura f = new Figura(
                        rs.getString("tipo"),
                        rs.getInt("x1"),
                        rs.getInt("y1"),
                        rs.getInt("x2"),
                        rs.getInt("y2"),
                        rs.getInt("lados"),
                        new Color(rs.getInt("color_linea")),
                        new Color(rs.getInt("color_relleno")),
                        rs.getInt("relleno") == 1,
                        rs.getInt("orden_figura")
                );

                figuras.add(f);
            }

            System.out.println("Dibujo cargado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al cargar dibujo.");
            e.printStackTrace();
        }

        return figuras;
    }
}