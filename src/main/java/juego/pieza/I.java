package juego.pieza;

/**
 * Clase de la pieza I
 */
public class I extends Pieza {

    private static final Bloque[][][] estadoPieza = new Bloque[][][]{
        {{null, null, null, null},
         {new Bloque(Color.CYAN), new Bloque(Color.CYAN), new Bloque(Color.CYAN), new Bloque(Color.CYAN)},
         {null, null, null, null},
         {null, null, null, null}
        },
        {{null, null, new Bloque(Color.CYAN), null},
         {null, null, new Bloque(Color.CYAN), null},
         {null, null, new Bloque(Color.CYAN), null},
         {null, null, new Bloque(Color.CYAN), null}
        },
        {{null, null, null, null},
         {null, null, null, null},
         {new Bloque(Color.CYAN), new Bloque(Color.CYAN), new Bloque(Color.CYAN), new Bloque(Color.CYAN)},
         {null, null, null, null}
        },
        {{null, new Bloque(Color.CYAN), null, null},
         {null, new Bloque(Color.CYAN), null, null},
         {null, new Bloque(Color.CYAN), null, null},
         {null, new Bloque(Color.CYAN), null, null}
        }
    };


    public I(Posicion posicion) {
        super(posicion);
        matriz = estadoPieza[estado.obtenerEstado()];
    }


    public void rotarSentidoHorario() {
        Bloque[][] matrizRotada = estadoPieza[estado.siguienteEstado().obtenerEstado()];
        boolean puedeRotar = false;
        int modificadorDireccionColumnas = -3;
        int movimientoFilas = 1;
        int movimientoColumnas = -1;
        // maneja los wall kicks
        while (modificadorDireccionColumnas <= 1 && !puedeRotar) {
            modificadorDireccionColumnas += 2;
            while (movimientoColumnas <= 2 && !puedeRotar) {
                movimientoColumnas++;
                if (this.puedeMoverse(matrizRotada,
                        posicion.moverPosicion(0, modificadorDireccionColumnas * movimientoColumnas))) {
                    puedeRotar = true;
                }
            }
        }
        // maneja los floor kicks en caso de que no se halla podido rotar previamente
        while (movimientoFilas >= -2 && !puedeRotar) {
            movimientoFilas--;
            if (this.puedeMoverse(matrizRotada,
                    posicion.moverPosicion(0, modificadorDireccionColumnas * movimientoFilas))) {
                puedeRotar = true;
            }
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
        int modificadorDireccionColumnas = 3;
        int movimientoFilas = 1;
        int movimientoColumnas = -1;
        // maneja los wall kicks
        while (modificadorDireccionColumnas >= -1 && !puedeRotar) {
            modificadorDireccionColumnas -= 2;
            while (movimientoColumnas <= 2 && !puedeRotar) {
                movimientoColumnas++;
                if (this.puedeMoverse(matrizRotada,
                        posicion.moverPosicion(0, modificadorDireccionColumnas * movimientoColumnas))) {
                    puedeRotar = true;
                }
            }
        }
        // maneja los floor kicks en caso de que no se halla podido rotar previamente
        while (movimientoFilas >= -2 && !puedeRotar) {
            movimientoFilas--;
            if (this.puedeMoverse(matrizRotada,
                    posicion.moverPosicion(0, movimientoFilas))) {
                puedeRotar = true;
            }
        }
        if (puedeRotar) {
            this.posicion = posicion.moverPosicion(movimientoFilas, movimientoColumnas);
            this.matriz = matrizRotada;
            estado = estado.anteriorEstado();
        }
    }
}