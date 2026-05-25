
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Controlador {

    private Vista vista;
    private Modelo modelo;
    private GeneradorSopa generador;

    public Controlador(Vista vista, Modelo modelo, GeneradorSopa generador) {
        this.vista = vista;
        this.modelo = modelo;
        this.generador = generador;

        iniciarEventos();
        consultarPalabras();
    }

    private void iniciarEventos() {
        vista.btnAnadir.addActionListener(e -> anadirPalabra());
        vista.btnEliminar.addActionListener(e -> eliminarPalabra());
        vista.btnConsultar.addActionListener(e -> consultarPalabras());
        vista.btnGenerar.addActionListener(e -> generarSopa());
    }

    private void anadirPalabra() {
        String palabra = vista.txtPalabra.getText().trim().toUpperCase();

        if (palabra.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Introduce una palabra.");
            return;
        }

        if (palabra.contains(" ")) {
            JOptionPane.showMessageDialog(vista, "La palabra no puede contener espacios.");
            return;
        }

        if (palabra.length() > 10) {
            JOptionPane.showMessageDialog(vista, "La palabra no puede tener más de 10 letras.");
            return;
        }

        modelo.insertarPalabra(palabra);

        vista.txtPalabra.setText("");
        consultarPalabras();
    }

    private void eliminarPalabra() {
        String palabra = vista.txtPalabra.getText().trim().toUpperCase();

        if (palabra.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Introduce una palabra para eliminar.");
            return;
        }

        modelo.eliminarPalabra(palabra);

        vista.txtPalabra.setText("");
        consultarPalabras();
    }

    private void consultarPalabras() {
        ArrayList<String> palabras = modelo.obtenerPalabras();

        String texto = "";

        for (String palabra : palabras) {
            texto += palabra + "\n";
        }

        vista.areaPalabras.setText(texto);
    }

    private void generarSopa() {
        ArrayList<String> palabras = modelo.obtenerPalabras();

        if (palabras.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "No hay palabras en la base de datos.");
            return;
        }

        String sopa = generador.generarSopa(palabras);

        vista.areaSopa.setText(sopa);
    }
}