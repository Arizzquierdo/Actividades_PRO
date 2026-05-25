package paintmvc.vista;

import paintmvc.figuras.Figura;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.ArrayList;

public class Lienzo extends JPanel {

    private ArrayList<Figura> figuras = new ArrayList<>();

    public Lienzo() {
        setBackground(java.awt.Color.WHITE);
    }

    public void agregarFigura(Figura figura) {
        figuras.add(figura);
        repaint();
    }

    public ArrayList<Figura> getFiguras() {
        return figuras;
    }

    public void setFiguras(ArrayList<Figura> figuras) {
        this.figuras = figuras;
        repaint();
    }

    public void limpiar() {
        figuras.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Figura f : figuras) {
            f.dibujar(g);
        }
    }
}