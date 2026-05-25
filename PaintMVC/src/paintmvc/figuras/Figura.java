package paintmvc.figuras;

import java.awt.Color;
import java.awt.Graphics;

public class Figura {

    public String tipo;
    public int x1, y1, x2, y2;
    public int lados;
    public Color colorLinea;
    public Color colorRelleno;
    public boolean relleno;
    public int orden;

    public Figura(String tipo, int x1, int y1, int x2, int y2,
                  int lados, Color colorLinea, Color colorRelleno,
                  boolean relleno, int orden) {

        this.tipo = tipo;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.lados = lados;
        this.colorLinea = colorLinea;
        this.colorRelleno = colorRelleno;
        this.relleno = relleno;
        this.orden = orden;
    }

    public void dibujar(Graphics g) {
        g.setColor(colorLinea);

        if (tipo.equals("Punto")) {
            g.fillOval(x1 - 3, y1 - 3, 6, 6);
        }

        if (tipo.equals("Recta")) {
            g.drawLine(x1, y1, x2, y2);
        }

        if (tipo.equals("Circunferencia")) {
            dibujarCircunferencia(g);
        }

        if (tipo.equals("Poligono regular")) {
            dibujarPoligonoRegular(g);
        }
    }

    private void dibujarCircunferencia(Graphics g) {
        int radio = (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        int x = x1 - radio;
        int y = y1 - radio;
        int diametro = radio * 2;

        if (relleno) {
            g.setColor(colorRelleno);
            g.fillOval(x, y, diametro, diametro);
            g.setColor(colorLinea);
        }

        g.drawOval(x, y, diametro, diametro);
    }

    private void dibujarPoligonoRegular(Graphics g) {
        int radio = (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        int[] xs = new int[lados];
        int[] ys = new int[lados];

        double anguloInicial = Math.atan2(y2 - y1, x2 - x1);

        for (int i = 0; i < lados; i++) {
            double angulo = anguloInicial + i * 2 * Math.PI / lados;
            xs[i] = x1 + (int) (radio * Math.cos(angulo));
            ys[i] = y1 + (int) (radio * Math.sin(angulo));
        }

        if (relleno) {
            g.setColor(colorRelleno);
            g.fillPolygon(xs, ys, lados);
            g.setColor(colorLinea);
        }

        g.drawPolygon(xs, ys, lados);
    }
}