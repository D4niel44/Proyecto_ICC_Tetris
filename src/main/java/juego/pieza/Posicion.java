package juego.pieza;

import java.io.Serializable;

/**
 * Clase posicion, representa una posicion con filas y columnas
 */
public class Posicion implements Serializable {

    private int fila;
    private int columna;

    /**
     * Crea un objeto POsicion a partir de dos parametros que representan las coordenadas
     * @param fila entero que representa la fila o renglon de la pieza
     * @param columna entero que representa la columna de la pieza
     */
    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Obtiene la fila
     * @return la fila
     */
    public int obtenerFila() {
        return fila;
    }
    /**
     * Obtiene la columna 
     * @return la columna
     */
    public int obtenerColumna() {
        return columna;
    }
    /**
     * Compara si dos posiciones son la misma, teniendo en cuenta lo siguiente:
     * (a, b) = (c, d) si y solo si a = c y b = d
     * @return Verdadero si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Posicion otra = (Posicion) obj;
        return fila == otra.fila && columna == otra.columna;
    }
    /**
     * Devuelve el par ordenado que representa la posicion.
     * @return String con el par ordenado de la forma (fila, columna)
     */
    public String toString() {
        return "(" + fila + ", " + columna + ")";
    }

}