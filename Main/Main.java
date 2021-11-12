package Main;

import Controller.Segmentacion;

public class Main {
    public static void main(String[] args) {
        Segmentacion segmentacion = new Segmentacion("src/Imagenes/imagen.png");

        segmentacion.segmentar("blue");
    }
}
