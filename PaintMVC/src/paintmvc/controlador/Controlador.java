package paintmvc.controlador;

import paintmvc.figuras.Figura;
import paintmvc.modelo.Modelo;
import paintmvc.svg.GeneradorSVG;
import paintmvc.vista.VistaPrincipal;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controlador {

    private Modelo modelo;
    private VistaPrincipal vista;

    private int x1;
    private int y1;
    private boolean esperandoSegundoClick = false;
    private int orden = 0;

    public Controlador(Modelo modelo, VistaPrincipal vista) {
        this.modelo = modelo;
        this.vista = vista;

        prepararEventos();
    }

    private void prepararEventos() {

        vista.btnColorLinea.addActionListener(e -> {
            Color color = JColorChooser.showDialog(vista, "Elige color de línea", Color.BLACK);

            if (color != null) {
                vista.colorLinea = color;
            }
        });

        vista.btnColorRelleno.addActionListener(e -> {
            Color color = JColorChooser.showDialog(vista, "Elige color de relleno", Color.YELLOW);

            if (color != null) {
                vista.colorRelleno = color;
            }
        });

        vista.btnGuardar.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(vista, "Escribe un nombre para el dibujo:");

            if (nombre != null && !nombre.isEmpty()) {
                modelo.guardarDibujo(nombre, vista.lienzo.getFiguras());
                JOptionPane.showMessageDialog(vista, "Dibujo guardado.");
            }
        });

        vista.btnCargar.addActionListener(e -> {
            vista.lienzo.setFiguras(modelo.cargarUltimoDibujo());
            JOptionPane.showMessageDialog(vista, "Último dibujo cargado.");
        });

        vista.btnBorrar.addActionListener(e -> {
            vista.lienzo.limpiar();
        });

        vista.btnSVG.addActionListener(e -> {
            GeneradorSVG.generar(
                    vista.lienzo.getFiguras(),
                    "C:\\Users\\izqui\\Documents\\NetBeansProjects\\PaintMVC\\dibujo.svg"
            );

            JOptionPane.showMessageDialog(
                    vista,
                    "Archivo SVG generado en C:\\Users\\izqui\\Documents\\NetBeansProjects\\PaintMVC\\dibujo.svg"
            );
        });

        vista.lienzo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clickEnLienzo(e);
            }
        });
    }

    private void clickEnLienzo(MouseEvent e) {
        String tipo = vista.comboFigura.getSelectedItem().toString();

        if (tipo.equals("Punto")) {
            Figura figura = new Figura(
                    "Punto",
                    e.getX(),
                    e.getY(),
                    e.getX(),
                    e.getY(),
                    vista.sliderLados.getValue(),
                    vista.colorLinea,
                    vista.colorRelleno,
                    vista.checkRelleno.isSelected(),
                    orden
            );

            orden++;
            vista.lienzo.agregarFigura(figura);
            return;
        }

        if (!esperandoSegundoClick) {
            x1 = e.getX();
            y1 = e.getY();
            esperandoSegundoClick = true;
        } else {
            Figura figura = new Figura(
                    tipo,
                    x1,
                    y1,
                    e.getX(),
                    e.getY(),
                    vista.sliderLados.getValue(),
                    vista.colorLinea,
                    vista.colorRelleno,
                    vista.checkRelleno.isSelected(),
                    orden
            );

            orden++;
            vista.lienzo.agregarFigura(figura);
            esperandoSegundoClick = false;
        }
    }
}