import java.util.ArrayList;

public class GeneradorSopa {

    static String[][] M;
    static int[][] H;

  
    public static int azar(int limite) {
        return (int) Math.floor(Math.random() * limite);
    }

    public static void mostrarMatriz(String[][] M) {
        for (int f = 0; f < M.length; f++) {
            mostrarFila(M, f);
        }
    }

    public static void mostrarFila(String[][] M, int f) {
        int columnas = M[0].length;

        for (int c = 0; c < columnas; c++) {
            System.out.print(M[f][c] + " ");
        }

        System.out.println();
    }

   
    public static String matrizATexto() {
        String texto = "";

        for (int f = 0; f < M.length; f++) {
            for (int c = 0; c < M[0].length; c++) {
                texto += M[f][c] + " ";
            }
            texto += "\n";
        }

        return texto;
    }

    public String generarSopa(ArrayList<String> palabras) {
        M = new String[10][10];
        H = new int[10][10];

        inicializaMatrices();
        colocarTodas(palabras);

        return matrizATexto();
    }

    public static boolean colocarPalabra0(String[] word) {

        int f = azar(M.length);
        int L = word.length;
        int c = azar(M[0].length - L + 1);

        boolean PERMITIDO = true;

        for (int t = 0; t < L; t++) {
            if ((H[f][c + t] == 1) && (!M[f][c + t].equals(word[t]))) {
                PERMITIDO = false;
            }
        }

        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f][c + t] = word[t];
                H[f][c + t] = 1;
            }
        }

        return PERMITIDO;
    }

    public static boolean colocarPalabra2(String[] word) {

        int L = word.length;
        int f = azar(M.length - L + 1);
        int c = azar(M[0].length);

        boolean PERMITIDO = true;

        for (int t = 0; t < L; t++) {
            if ((H[f + t][c] == 1) && (!M[f + t][c].equals(word[t]))) {
                PERMITIDO = false;
            }
        }

        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f + t][c] = word[t];
                H[f + t][c] = 1;
            }
        }

        return PERMITIDO;
    }

    public static boolean colocarPalabra4(String[] word) {

        int f = azar(M.length);
        int L = word.length;
        int c = azar(M[0].length - L + 1);

        boolean PERMITIDO = true;

        for (int t = 0; t < L; t++) {
            if ((H[f][c + t] == 1) && (!M[f][c + t].equals(word[L - t - 1]))) {
                PERMITIDO = false;
            }
        }

        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f][c + t] = word[L - t - 1];
                H[f][c + t] = 1;
            }
        }

        return PERMITIDO;
    }


    public static boolean colocarPalabra6(String[] word) {

        int L = word.length;
        int f = azar(M.length - L + 1);
        int c = azar(M[0].length);

        boolean PERMITIDO = true;

        for (int t = 0; t < L; t++) {
            if ((H[f + t][c] == 1) && (!M[f + t][c].equals(word[L - t - 1]))) {
                PERMITIDO = false;
            }
        }

        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f + t][c] = word[L - t - 1];
                H[f + t][c] = 1;
            }
        }

        return PERMITIDO;
    }

    public static boolean colocarPalabra1(String[] word) {

        int L = word.length;
        int f = azar(M.length);
        int c = azar(M[0].length);

        boolean PERMITIDO = true;

        for (int t = 0; t < L; t++) {
            if ((f + t >= M.length) || (c + t >= M[0].length)) {
                PERMITIDO = false;
            } else {
                if ((H[f + t][c + t] == 1) && (!M[f + t][c + t].equals(word[t]))) {
                    PERMITIDO = false;
                }
            }
        }

        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f + t][c + t] = word[t];
                H[f + t][c + t] = 1;
            }
        }

        return PERMITIDO;
    }

    public static boolean colocarPalabra5(String[] word) {

        int L = word.length;
        int f = azar(M.length);
        int c = azar(M[0].length);

        boolean PERMITIDO = true;

        for (int t = 0; t < L; t++) {
            if ((f + t >= M.length) || (c + t >= M[0].length)) {
                PERMITIDO = false;
            } else {
                if ((H[f + t][c + t] == 1) && (!M[f + t][c + t].equals(word[L - t - 1]))) {
                    PERMITIDO = false;
                }
            }
        }

        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f + t][c + t] = word[L - t - 1];
                H[f + t][c + t] = 1;
            }
        }

        return PERMITIDO;
    }

    public static boolean colocarPalabra3(String[] word) {

        int L = word.length;
        int f = azar(M.length);
        int c = azar(M[0].length);

        boolean PERMITIDO = true;

        for (int t = 0; t < L; t++) {
            if ((f + t >= M.length) || (c - t < 0)) {
                PERMITIDO = false;
            } else {
                if ((H[f + t][c - t] == 1) && (!M[f + t][c - t].equals(word[t]))) {
                    PERMITIDO = false;
                }
            }
        }

        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f + t][c - t] = word[t];
                H[f + t][c - t] = 1;
            }
        }

        return PERMITIDO;
    }

 
    public static boolean colocarPalabra7(String[] word) {

        int L = word.length;
        int f = azar(M.length);
        int c = azar(M[0].length);

        boolean PERMITIDO = true;

        for (int t = 0; t < L; t++) {
            if ((f + t >= M.length) || (c - t < 0)) {
                PERMITIDO = false;
            } else {
                if ((H[f + t][c - t] == 1) && (!M[f + t][c - t].equals(word[L - t - 1]))) {
                    PERMITIDO = false;
                }
            }
        }

        if (PERMITIDO) {
            for (int t = 0; t < L; t++) {
                M[f + t][c - t] = word[L - t - 1];
                H[f + t][c - t] = 1;
            }
        }

        return PERMITIDO;
    }

    public static void colocarTodas(ArrayList<String> palabras) {

        int orientacion;
        String[] word;
        int longPalabra = palabras.size();
        int cuenta = 0;
        boolean COLOCADO = false;

        do {
            word = palabras.get(cuenta).toUpperCase().split("");
            orientacion = azar(8);
            COLOCADO = false;

            switch (orientacion) {
                case 0:
                    COLOCADO = colocarPalabra0(word);
                    break;

                case 1:
                    COLOCADO = colocarPalabra1(word);
                    break;

                case 2:
                    COLOCADO = colocarPalabra2(word);
                    break;

                case 3:
                    COLOCADO = colocarPalabra3(word);
                    break;

                case 4:
                    COLOCADO = colocarPalabra4(word);
                    break;

                case 5:
                    COLOCADO = colocarPalabra5(word);
                    break;

                case 6:
                    COLOCADO = colocarPalabra6(word);
                    break;

                case 7:
                    COLOCADO = colocarPalabra7(word);
                    break;
            }

            if (COLOCADO) {
                cuenta++;
            }

        } while (cuenta < longPalabra);
    }

    public static void inicializaMatrices() {

        String cadena = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String[] letra;
        letra = cadena.split("");

        int f, c;

        for (f = 0; f < M.length; f++) {
            for (c = 0; c < M[0].length; c++) {
                M[f][c] = letra[azar(27)];
                H[f][c] = 0;
            }
        }
    }
}