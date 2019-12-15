package juego.pieza;

import java.io.Serializable;
import juego.Tablero;

/**
 * Clase padre abstracta de las diferentes piezas
 */
public abstract class Pieza implements Serializable {
    /**
     * Clase que representa los estados en los que se encuentra una pieza donde cada
     * estado corresponde al resultado de rotar la pieza desde su estado inicial
     * 
     * Cada estado es una representacion de cada una de las estructuras de los
     * bloques de la piez. El estado inicial esta representado por el 0.
     */
    protected class EstadoPieza implements Serializable {
        private int estado;

        /**
         * crea un nuevo objeto en el estado pasado como parametro
         * 
         * @param estado estado (0) es el inicial de la pieza
         */
        protected EstadoPieza(int estado) {
            this.estado = (estado == -1) ? 3 : (estado == 4) ? 0 : estado;
        }

        /**
         * Crea un objeto con el estado inicial (0)
         */
        protected EstadoPieza() {
            this(0);
        }

        /**
         * Retorna el siguiente estado de la pieza
         * 
         * @return Siguiente estado
         */
        protected EstadoPieza siguienteEstado() {
            return new EstadoPieza(estado + 1);
        }

        /**
         * Retorna el estado anterior de la pieza
         * 
         * @return estado anterior
         */
        protected EstadoPieza anteriorEstado() {
            return new EstadoPieza(estado - 1);
        }

        /**
         * Obtiene el estado actual de la pieza
         * 
         * @return entero que representa el estado actual de la pieza
         */
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
        this.estado = new EstadoPieza();
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
     * Obtiene el numero de filas de la matriz que representa a la pieza
     * 
     * @return numero de filas de la matriz
     */
    public int obtenerFilas() {
        return this.matriz.length;
    }

    /**
     * Obtiene el numero de columnas de la matriz
     * 
     * @return numero de columnas de la matriz
     */
    public int obtenerColumnas() {
        return this.matriz[0].length;
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
     * Mueve un luga a la izquierda la pieza en caso de que sea posible
     */
    public void moverIzquierda() {
        if (this.puedeMoverse(this.posicion.moverPosicion(0, -1))) {
            this.posicion = this.posicion.moverPosicion(0, -1);
        }
    }

    /**
     * Mueve un lugar a la derecha la pieza en caso de que sea posible
     */
    public void moverDerecha() {
        if (this.puedeMoverse(this.posicion.moverPosicion(0, 1))) {
            this.posicion = this.posicion.moverPosicion(0, 1);
        }
    }

    /**
     * Determina si los bloques pueden colocarse en el tablero en el indice dado
     * 
     * @param estructuraPieza Matriz de bloques
     * @param indice          indice respecto al tablero de la matriz
     * @return Verdadero si se puede colocar la matriz
     */
    public boolean puedeMoverse(Bloque[][] estructuraPieza, Posicion indice) {
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                if (!Tablero.obtenerInstancia().estaLibre(indice.moverPosicion(i, j))
                        && estructuraPieza[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Determina si los bloques pueden colocarse en el tablero en el indice dado,
     * toma los bloques actuales de la pieza para esto
     * 
     * @param indice indice respecto al tablero de la matriz
     * @return Verdadero si se puede colocar la matriz
     */
    public boolean puedeMoverse(Posicion indice) {
        return this.puedeMoverse(this.matriz, indice);
    }

    /**
     * Devuelve la pieza fantasma relacionada a la pieza
     * 
     * @return pieza fantasma
     */
    public Pieza piezaFantasma() {
        // Evalua hasta donde puede caer la pieza actual
        int i = 0;
        while (this.puedeMoverse(this.obtenerPosicion().moverPosicion(i + 1, 0))) {
            i++;
        }
        // Crea una nueva pieza con la posicion hasta la cual puede caer la pieza actual
        Pieza piezaFantasma;
        switch (this.getClass().getSimpleName()) {
        case "I":
            piezaFantasma = new I(this.obtenerPosicion().moverPosicion(i, 0));
            break;
        case "L":
            piezaFantasma = new L(this.obtenerPosicion().moverPosicion(i, 0));
            break;
        case "J":
            piezaFantasma = new J(this.obtenerPosicion().moverPosicion(i, 0));
            break;
        case "O":
            piezaFantasma = new O(this.obtenerPosicion().moverPosicion(i, 0));
            break;
        case "S":
            piezaFantasma = new S(this.obtenerPosicion().moverPosicion(i, 0));
            break;
        case "Z":
            piezaFantasma = new Z(this.obtenerPosicion().moverPosicion(i, 0));
            break;
        case "T":
            piezaFantasma = new T(this.obtenerPosicion().moverPosicion(i, 0));
            break;
        default:
            piezaFantasma = null;
        }
        piezaFantasma.matriz = this.matriz;
        piezaFantasma.estado = this.estado;
        return piezaFantasma;
    }
}