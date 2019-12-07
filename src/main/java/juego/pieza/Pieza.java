package juego.pieza;

import java.io.Serializable;

public class Pieza implements Serializable {

    private Color color;
    private Posicion posicion;
    //private Bloque[][] matriz; 

    public Pieza(Color color, Posicion posicion) {
        this.color = color;
        this.posicion = posicion;
    }
}