
package paintmvc_;

import paintmvc.controlador.Controlador;
import paintmvc.modelo.Modelo;
import paintmvc.vista.VistaPrincipal;

public class Main {
     public static void main(String[] args) {
        Modelo modelo = new Modelo();
        VistaPrincipal vista = new VistaPrincipal();
        Controlador controlador = new Controlador(modelo, vista);

        vista.setVisible(true);
    }
}
