package juego.pieza;

import java.io.Serializable;
import juego.Tablero;

/**
 * Clase padre abstracta de las diferentes piezas
 */
public abstract class Pieza implements Serializable {

    protected class EstadoPieza implements Serializable {
        private int estado;

        protected EstadoPieza(int estado) {
            this.estado = (estado == -1) ? 3 : (estado == 4) ? 0 : estado;
        }

        protected EstadoPieza() {
            this(0);
        }

        protected EstadoPieza siguienteEstado() {
            return new EstadoPieza(estado + 1);
        }

        protected EstadoPieza anteriorEstado() {
            return new EstadoPieza(estado - 1);
        }

        protected int obtenerEstado() {
            return estado;
        }
    }

    protected Posicion posicion;
    protected Bloque[][] matriz;
    protected EstadoPieza estado;

    /**
     * Constructor de la clase
     * 
     * @param posicion Posicion inicial de la pieza
     */
    public Pieza(Posicion posicion) {
        this.posicion = posicion;
        this.estado = new EstadoPiezas();
    }

    /**
     * Devuelve el bloque de la posicion determinada o null si esta vacia
     * 
     * @param p Posicion
     * @return Bloque de la posicion dada
     */
    public Bloque obtenerBloque(Posicion p) {
        return matriz[p.obtenerFila()][p.obtenerColumna()];
    }

    /**
     * Asigna un bloque a la posicion dada
     * 
     * @param bloque Bloque a asignar
     * @param p      Posicion
     */
    public void asignarBloque(Bloque bloque, Posicion p) {
        matriz[p.obtenerFila()][p.obtenerColumna()] = bloque;
    }

    /**
     * Devuelve la posicion indice de la pieza
     * 
     * @return Posicion indice de la pieza
     */
    public Posicion obtenerPosicion() {
        return posicion;
    }

    /**
     * Asigna una nueva posicion indice a la pieza
     * 
     * @param p nueva posicion
     */
    public void asignarPosicion(Posicion p) {
        this.posicion = p;
    }

    /**
     * Rota la pieza hacia la izquierda
     */
    public abstract void rotarSentidoHorario();

    /**
     * Rota la pieza hacia la derecha
     */
    public abstract void rotarSentidoAntihorario();
    /**
     * Determina si los bloques pueden colocarse en el tablero en el indice dado
     * @param estructuraPieza Matriz de bloques
     * @param indice indice respecto al tablero de la matriz
     * @return Verdadero si se puede colocar la matriz
     */
    public boolean puedeMoverse(Bloque[][] estructuraPieza, Posicion indice) {
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                if (!Tablero.obtenerInstancia().estaLibre(indice.moverPosicion(i, j)) && estructuraPieza[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

}