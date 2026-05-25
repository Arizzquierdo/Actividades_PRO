package paintmvc.vista;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import java.awt.BorderLayout;
import java.awt.Color;

public class VistaPrincipal extends JFrame {

    public JComboBox<String> comboFigura;
    public JButton btnColorLinea;
    public JButton btnColorRelleno;
    public JButton btnGuardar;
    public JButton btnCargar;
    public JButton btnBorrar;
    public JButton btnSVG;
    public JCheckBox checkRelleno;
    public JSlider sliderLados;
    public Lienzo lienzo;

    public Color colorLinea = Color.BLACK;
    public Color colorRelleno = Color.YELLOW;

    public VistaPrincipal() {
        setTitle("Paint MVC");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lienzo = new Lienzo();

        comboFigura = new JComboBox<>();
        comboFigura.addItem("Punto");
        comboFigura.addItem("Recta");
        comboFigura.addItem("Circunferencia");
        comboFigura.addItem("Poligono regular");

        btnColorLinea = new JButton("Color línea");
        btnColorRelleno = new JButton("Color relleno");
        btnGuardar = new JButton("Guardar");
        btnCargar = new JButton("Cargar");
        btnBorrar = new JButton("Borrar");
        btnSVG = new JButton("Generar SVG");

        checkRelleno = new JCheckBox("Relleno");

        sliderLados = new JSlider(3, 12, 5);
        sliderLados.setMajorTickSpacing(1);
        sliderLados.setPaintTicks(true);
        sliderLados.setPaintLabels(true);

        JPanel panelSuperior = new JPanel();

        panelSuperior.add(new JLabel("Figura:"));
        panelSuperior.add(comboFigura);

        panelSuperior.add(new JLabel("Lados:"));
        panelSuperior.add(sliderLados);

        panelSuperior.add(checkRelleno);
        panelSuperior.add(btnColorLinea);
        panelSuperior.add(btnColorRelleno);
        panelSuperior.add(btnGuardar);
        panelSuperior.add(btnCargar);
        panelSuperior.add(btnBorrar);
        panelSuperior.add(btnSVG);

        add(panelSuperior, BorderLayout.NORTH);
        add(lienzo, BorderLayout.CENTER);
    }
}