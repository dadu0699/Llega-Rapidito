package org.jd.utilidades;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class PropiedadesPantalla {

    private static PropiedadesPantalla instancia;
    private Rectangle2D screenBounds;

    private PropiedadesPantalla() {
        screenBounds = Screen.getPrimary().getBounds();
    }

    public static PropiedadesPantalla getInstancia() {
        if (instancia == null) {
            instancia = new PropiedadesPantalla();
        }
        return instancia;
    }

    public Double getX() {
        return screenBounds.getWidth(); // Tamaño total eje X de la pantalla
    }

    public Double getY() {
        return screenBounds.getHeight(); // Tamaño total eje Y de la pantalla
    }
}
