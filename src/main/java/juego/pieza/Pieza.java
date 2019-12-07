package juego.pieza;

import java.io.Serializable;

/**
 * Clase padre abstracta de las diferentes piezas
 */
public abstract class Pieza implements Serializable {

    protected enum Orientacion {
        HORIZONTAL, VERTICAL
    }

    private Posicion posicion;
    protected Bloque[][] matriz;
    protected Orientacion orientacion;

    /**
     * Constructor de la clase
     * 
     * @param posicion Posicion inicial de la pieza
     * @param matriz   Estructura de la pieza
     */
    public Pieza(Posicion posicion, Bloque[][] matriz) {
        this.posicion = posicion;
        this.matriz = matriz;
        this.orientacion = Orientacion.HORIZONTAL;
    }

    /**
     * Devuelve el bloque de la posicion determinada o null si esta vacia
     * @param p Posicion
     * @return Bloque de la posicion dada
     */
    public Bloque obtenerBloque(Posicion p) {
        return matriz[p.obtenerFila()][p.obtenerColumna()];
    }
    /**
     * Asigna un bloque a la posicion dada
     * @param bloque Bloque a asignar
     * @param p Posicion
     */
    public void asignarBloque(Bloque bloque, Posicion p) {
        matriz[p.obtenerFila()][p.obtenerColumna()] = bloque;
    }
    /**
     * Devuelve la posicion indice de la pieza
     * @return Posicion indice  de la pieza
     */
    public Posicion obtenerPosicion() {
        return posicion;
    }

    /**
     * Asigna una nueva posicion indice a la pieza
     * @param p nueva posicion
     */
    public void asignarPosicion(Posicion p) {
        this.posicion = p;
    }
    /**
     * Rota la pieza hacia la izquierda
     */
    public abstract void rotarIzquierda();
    /**
     * Rota la pieza hacia la derecha
     */
    public abstract void rotarDerecha();

    protected Bloque[][] transpuestaEsquinaSuperiorIzquierda() {
        Bloque[][] transpuesta = new Bloque[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                transpuesta[i][j] = matriz[j][i];
            }
        }
        return transpuesta;
    }

    protected Bloque[][] transpuestaEsquinaSuperiorDerecha() {
        Bloque[][] transpuesta = new Bloque[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                transpuesta[i][j] = matriz[matriz[i].length - j][i];
            }
        }
        return transpuesta;
    }

    protected Bloque[][] transpuestaEsquinaInferiorIzquierda() {
        Bloque[][] transpuesta = new Bloque[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                transpuesta[i][j] = matriz[j][matriz.length - i];
            }
        }
        return transpuesta;
    }

    protected Bloque[][] transpuestaEsquinaInferiorDerecha() {
        Bloque[][] transpuesta = new Bloque[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                transpuesta[i][j] = matriz[matriz[i].length - j][matriz.length - i];
            }
        }
        return transpuesta;
    }

}