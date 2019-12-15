package juego.pieza;

/**
 * Clase Z
 */
public class Z extends Pieza {

    /*
     *Constante con la matriz de bloques para cada uno de los estados de la pieza 
     */
	private static final Bloque[][][] estadoPieza = new Bloque[][][]{
        {{new Bloque(Color.ROJO), new Bloque(Color.ROJO), null},
         {null, new Bloque(Color.ROJO), new Bloque(Color.ROJO)},
         {null, null, null}
        },
        {{null, null, new Bloque(Color.ROJO)},
         {null, new Bloque(Color.ROJO), new Bloque(Color.ROJO)},
         {null, new Bloque(Color.ROJO), null}
        },
        {{null, null, null},
         {new Bloque(Color.ROJO), new Bloque(Color.ROJO), null},
         {null, new Bloque(Color.ROJO), new Bloque(Color.ROJO)}
        },
        {{null, new Bloque(Color.ROJO), null},
         {new Bloque(Color.ROJO), new Bloque(Color.ROJO), null},
         {new Bloque(Color.ROJO), null, null},
        }
    };

    /**
     * Crea una nueva pieza en la posicion dada
     * @param posicion posicion
     */
    public Z(Posicion posicion) {
        super(posicion);
        matriz = estadoPieza[estado.obtenerEstado()];
    }

    public void rotarSentidoHorario() {
        Bloque[][] matrizRotada = estadoPieza[estado.siguienteEstado().obtenerEstado()];
        boolean puedeRotar = false;
        int movimientoFilas = 0;
        int movimientoColumnas = 0;
        if (this.puedeMoverse(matrizRotada, this.obtenerPosicion())) {
            puedeRotar = true;
        } else if (this.puedeMoverse(matrizRotada, this.obtenerPosicion().moverPosicion(0, -1))) {
            puedeRotar = true;
            movimientoColumnas = -1;
        } else if (this.puedeMoverse(matrizRotada, this.obtenerPosicion().moverPosicion(0, 1))) {
            puedeRotar = true;
            movimientoColumnas = 1;
        } else if (this.puedeMoverse(matrizRotada, this.obtenerPosicion().moverPosicion(-1, 0))) {
            puedeRotar = true;
            movimientoFilas = -1;
        }
        if (puedeRotar) {
            this.posicion = posicion.moverPosicion(movimientoFilas, movimientoColumnas);
            this.matriz = matrizRotada;
            estado = estado.siguienteEstado();
        }

    }

    public void rotarSentidoAntihorario() {
        Bloque[][] matrizRotada = estadoPieza[estado.anteriorEstado().obtenerEstado()];
        boolean puedeRotar = false;
        int movimientoFilas = 0;
        int movimientoColumnas = 0;
        if (this.puedeMoverse(matrizRotada, this.obtenerPosicion())) {
            puedeRotar = true;
        } else if (this.puedeMoverse(matrizRotada, this.obtenerPosicion().moverPosicion(0, 1))) {
            puedeRotar = true;
            movimientoColumnas = 1;
        } else if (this.puedeMoverse(matrizRotada, this.obtenerPosicion().moverPosicion(0, -1))) {
            puedeRotar = true;
            movimientoColumnas = -1;
        } else if (this.puedeMoverse(matrizRotada, this.obtenerPosicion().moverPosicion(-1, 0))) {
            puedeRotar = true;
            movimientoFilas = -1;
        }    
        if (puedeRotar) {
            this.posicion = posicion.moverPosicion(movimientoFilas, movimientoColumnas);
            this.matriz = matrizRotada;
            estado = estado.anteriorEstado();
        }

    }
}