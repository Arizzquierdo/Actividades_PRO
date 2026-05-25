

public class Main {

    public static void main(String[] args) {

        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        GeneradorSopa generador = new GeneradorSopa();

        Controlador controlador = new Controlador(vista, modelo, generador);

        vista.setVisible(true);
        vista.setLocationRelativeTo(null);
    }
}