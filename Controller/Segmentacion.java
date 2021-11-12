package Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Segmentacion {
    private final Color white = Color.WHITE;
    private final Color black = Color.BLACK;
    BufferedImage imagenRGB;
    BufferedImage imagenBlancoNegro;

    public Segmentacion(String path) {
        try {

            this.imagenRGB = ImageIO.read(new File(path));
            this.imagenBlancoNegro = ImageIO.read(new File(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void segmentar(String color) {
        int tipoDeColor = -1; // red = 1, green = 2, blue = 3
        boolean pass = true;

        switch (color) {
            case "red":
                tipoDeColor = 1;
                break;
            case "green":
                tipoDeColor = 2;
                break;
            case "blue":
                tipoDeColor = 3;
                break;
            default:
                pass = false;
                break;
        }

        if (pass) {
            try {
                for (int i = 0; i < this.imagenRGB.getWidth(); i++) {
                    for (int j = 0; j < this.imagenRGB.getHeight(); j++) {
                        int rgb = this.imagenRGB.getRGB(i, j);

                        Color colorOriginal = new Color(rgb);
                        Color colorBN = this.black;

                        if (tipoDeColor == 1) { //red
                            if (colorOriginal.getRed() >= 200) {
                                colorBN = this.white;
                            }else{
                                colorOriginal = this.white;
                            }
                        } else if (tipoDeColor == 2) {//green
                            if (colorOriginal.getGreen() >= 200) {
                                colorBN = this.white;
                            }else{
                                colorOriginal = this.white;
                            }
                        } else {//blue
                            if (colorOriginal.getBlue() >= 200) {
                                colorBN = this.white;
                            }else{
                                colorOriginal = this.white;
                            }
                        }
                        imagenRGB.setRGB(i, j, colorOriginal.getRGB());
                        imagenBlancoNegro.setRGB(i, j, colorBN.getRGB());

                    }
                }
                ImageIO.write(imagenRGB, "png", new File("src/Imagenes/imagenRGB_" + color + ".png"));
                ImageIO.write(imagenBlancoNegro, "png", new File("src/Imagenes/imagenBlancoNegro_" + color + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Verifica que tu color sea correcto");
        }
    }

}
