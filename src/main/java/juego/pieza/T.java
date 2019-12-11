package juego.pieza;

public class T extends Pieza {

	private static final Bloque[][][] estadoPieza = new Bloque[][][]{
        {{null, new Bloque(Color.MORADO), null},
         {new Bloque(Color.MORADO), new Bloque(Color.MORADO), new Bloque(Color.MORADO)},
         {null, null, null}
        },
        {{null, new Bloque(Color.MORADO), null},
         {null, new Bloque(Color.MORADO), new Bloque(Color.MORADO)},
         {null, new Bloque(Color.MORADO), null}
        },
        {{null, null, null},
         {new Bloque(Color.MORADO), new Bloque(Color.MORADO), new Bloque(Color.MORADO)},
         {null, new Bloque(Color.MORADO), null}
        },
        {{null, new Bloque(Color.MORADO), null},
         {new Bloque(Color.MORADO), new Bloque(Color.MORADO), null},
         {null, new Bloque(Color.MORADO), null},
        }
    };

    public T(Posicion posicion) {
        super(posicion);
        matriz = estadoPieza[estado.obtenerEstado()];
    }

    public void rotarSentidoHorario() {
    	Bloque[][] matrizRotada = estadoPieza[estado.siguienteEstado().obtenerEstado()];
    	boolean puedeRotar = false;
    	int movimientoColumnas = -1 ;
    	int movimientoFilas = 1;

        // maneja los wall kicks
        while (movimientoColumnas <= 1 && !puedeRotar) {
                movimientoColumnas++;
                if (this.puedeMoverse(matrizRotada,
                        posicion.moverPosicion(0, movimientoColumnas))) {
                    puedeRotar = true;
                }
            }

        // maneja los floor kicks en caso de que no se halla podido rotar previamente
        while (movimientoFilas >= -1 && !puedeRotar) {
            movimientoFilas--;
            if (this.puedeMoverse(matrizRotada,
                    posicion.moverPosicion(0, movimientoFilas))) {
                puedeRotar = true;
            }
        }

        // si se puede rotar la piesa la rota
        if (puedeRotar) {
            this.posicion = posicion.moverPosicion(movimientoFilas, movimientoColumnas);
            this.matriz = matrizRotada;
            estado = estado.siguienteEstado();
        }

    }

    public void rotarSentidoAntihorario() {
    	Bloque[][] matrizRotada = estadoPieza[estado.anteriorEstado().obtenerEstado()];
    	boolean puedeRotar = false;
    	int movimientoColumnas = -1 ;
    	int movimientoFilas = 1;

        // maneja los wall kicks
        while (movimientoColumnas <= 1 && !puedeRotar) {
                movimientoColumnas++;
                if (this.puedeMoverse(matrizRotada,
                        posicion.moverPosicion(0, movimientoColumnas))) {
                    puedeRotar = true;
                }
            }

        // maneja los floor kicks en caso de que no se halla podido rotar previamente
        while (movimientoFilas >= -1 && !puedeRotar) {
            movimientoFilas--;
            if (this.puedeMoverse(matrizRotada,
                    posicion.moverPosicion(0, movimientoFilas))) {
                puedeRotar = true;
            }
        }

        // si se puede rotar la piesa la rota
        if (puedeRotar) {
            this.posicion = posicion.moverPosicion(movimientoFilas, movimientoColumnas);
            this.matriz = matrizRotada;
            estado = estado.anteriorEstado();
        }
    }
}