package juego.pieza;

import java.io.Serializable;

/**
 * Modela los bloques or los cuales estan compuestas las fichas
 * 
 * @param color color del bloque
 */
public class Bloque implements Serializable {
    private Color color;

    /**
     * Crea un bloque del color dado
     * 
     * @param color color del bloque a crear
     */
    public Bloque(Color color) {
        this.color = color;
    }

    /**
     * devuelve el color del bloque
     * 
     * @return color del bloque
     */
    public Color obtenerColor() {
        return color;
    }

    /**
     * Asigna un nuevo color al bloque
     * @param color Color que se le va a asignar al bloque
     */
    public void asignarColor(Color color) {
        this.color = color;
    }
}