package paintmvc.svg;

import paintmvc.figuras.Figura;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GeneradorSVG {

    public static void generar(ArrayList<Figura> figuras, String archivo) {
        try {
            FileWriter fw = new FileWriter(archivo);

            fw.write("<svg width='1200' height='700' xmlns='http://www.w3.org/2000/svg'>\n");
            fw.write("<rect width='100%' height='100%' fill='white'/>\n");

            for (Figura f : figuras) {

                if (f.tipo.equals("Punto")) {
                    fw.write("<circle cx='" + f.x1 + "' cy='" + f.y1 + "' r='3' fill='" +
                            colorHex(f.colorLinea.getRGB()) + "' />\n");
                }

                if (f.tipo.equals("Recta")) {
                    fw.write("<line x1='" + f.x1 + "' y1='" + f.y1 + "' x2='" + f.x2 + "' y2='" + f.y2 +
                            "' stroke='" + colorHex(f.colorLinea.getRGB()) + "' />\n");
                }

                if (f.tipo.equals("Circunferencia")) {
                    int radio = (int) Math.sqrt(Math.pow(f.x2 - f.x1, 2) + Math.pow(f.y2 - f.y1, 2));

                    String relleno;

                    if (f.relleno) {
                        relleno = colorHex(f.colorRelleno.getRGB());
                    } else {
                        relleno = "none";
                    }

                    fw.write("<circle cx='" + f.x1 + "' cy='" + f.y1 + "' r='" + radio +
                            "' stroke='" + colorHex(f.colorLinea.getRGB()) +
                            "' fill='" + relleno + "' />\n");
                }

                if (f.tipo.equals("Poligono regular")) {
                    fw.write(poligonoRegularSVG(f));
                }
            }

            fw.write("</svg>");
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String poligonoRegularSVG(Figura f) {
        int radio = (int) Math.sqrt(Math.pow(f.x2 - f.x1, 2) + Math.pow(f.y2 - f.y1, 2));

        double anguloInicial = Math.atan2(f.y2 - f.y1, f.x2 - f.x1);

        String puntos = "";

        for (int i = 0; i < f.lados; i++) {
            double angulo = anguloInicial + i * 2 * Math.PI / f.lados;

            int x = f.x1 + (int) (radio * Math.cos(angulo));
            int y = f.y1 + (int) (radio * Math.sin(angulo));

            puntos = puntos + x + "," + y + " ";
        }

        String relleno;

        if (f.relleno) {
            relleno = colorHex(f.colorRelleno.getRGB());
        } else {
            relleno = "none";
        }

        return "<polygon points='" + puntos +
                "' stroke='" + colorHex(f.colorLinea.getRGB()) +
                "' fill='" + relleno +
                "' />\n";
    }

    private static String colorHex(int rgb) {
        return String.format("#%06X", (0xFFFFFF & rgb));
    }
}