package juego;

import juego.pieza.*;
import java.io.Serializable;

/**
 * Clase Tablero que contiene el estado del juego
 */
public class Tablero implements Serializable {

    private int puntuacion;
    private BolsaPiezas bolsa;
    private Pieza piezaActiva;
    private Pieza piezaGuardada;
    private boolean puedeCambiar;
    private Bloque[][] campoDeJuego;
    private static final Tablero INSTANCIA = new Tablero();
    private boolean juegoFinalizado;

    private Tablero() {
        bolsa = new BolsaPiezas();
        piezaActiva = bolsa.sacarPieza();
        piezaActiva.asignarPosicion(new Posicion(20, 3));
        piezaGuardada = null;
        puedeCambiar = true;
        campoDeJuego = new Bloque[40][10];
        juegoFinalizado = false;
    }

    /**
     * obtiene la instancia de tablero
     * 
     * @return la instancia del tablero
     */
    public static Tablero obtenerInstancia() {
        return INSTANCIA;
    }

    /**
     * obtiene la bolsa de piezas del tablero
     * 
     * @return bolse de piezas del tablero
     */
    public BolsaPiezas obtenerBolsa() {
        return bolsa;
    }

    /**
     * obtiene la pieza activa en el tablero
     * 
     * @return la pieza que se esta moviendo en el tablero
     */
    public Pieza obtenerPiezaActiva() {
        return piezaActiva;
    }

     /**
     * obtiene la pieza guardada en el tablero
     * 
     * @return la pieza que esta guardada en el tablero
     */
    public Pieza obtenerPiezaGuardada() {
        return piezaGuardada;
    }

    /**
     * obtiene el bloque que ocupa la posicion dada en el tablero
     * 
     * @param posicion posicion del bloque a obtener
     * @return bloque que se encuentra en la posicion, null en caso de no haber
     */
    public Bloque obtenerBloque(Posicion posicion) {
        return campoDeJuego[posicion.obtenerFila()][posicion.obtenerColumna()];
    }

    /**
     * Regresa la puntuacion del juego actual
     * 
     * @return puntuacion
     */
    public int obtenerPuntuacion() {
        return puntuacion;
    }

    /**
     * Metodo que indica si se encuentra algun bloque en la posicion dada, en caso
     * de que la posicion pasada como parametro sobrepase los limites del campo de
     * juego devuelve false
     * 
     * @param posicion Posicion de entrada
     * @return true si no hay un bloque en la posicion
     */
    public boolean estaLibre(Posicion posicion) {
        //int fila = posicion.obtenerFila();
        //int columna = posicion.obtenerColumna();
        if (posicion.obtenerFila() < 0 || posicion.obtenerFila() >= 40 || posicion.obtenerColumna() < 0
                || posicion.obtenerColumna() >= 10) {
            return false;
        }
        return obtenerBloque(posicion) == null;
    }

    /**
     * guarda la pieza actual, en caso de que haya una guardada la cambia por esta
     * NOTA este metodo no se encuentra bien hecho aun
     */
    public void guardarPieza() {
        if (puedeCambiar) {
            if (piezaGuardada == null) {
                piezaGuardada = piezaActiva;
                piezaActiva = bolsa.sacarPieza();
            } else {
                Pieza piezaAuxiliar = piezaActiva;
                piezaActiva = piezaGuardada;
                piezaActiva.asignarPosicion(new Posicion((piezaActiva instanceof I) ? 17 : 18, 3));
                piezaGuardada = piezaAuxiliar;

                if (piezaActiva.puedeMoverse(new Posicion(20, 3))) {
                    piezaActiva.asignarPosicion(new Posicion(20, 3));
                }
            }
            puedeCambiar = false;
        }
    }

    /**
     * desplaza un bloque hacia abajo la pieza
     */
    public void caerPieza() {
        if (piezaActiva.puedeMoverse(piezaActiva.obtenerPosicion().moverPosicion(1, 0))) {
            piezaActiva.asignarPosicion(piezaActiva.obtenerPosicion().moverPosicion(1, 0));
        } else {
            int contadorFilasEliminadas = 0;
            for (int i = 0; i < piezaActiva.obtenerFilas(); i++) {
                for (int j = 0; j < piezaActiva.obtenerColumnas(); j++) {
                    if (piezaActiva.obtenerBloque(new Posicion(i, j)) != null) {
                        campoDeJuego[piezaActiva.obtenerPosicion().obtenerFila() + i][piezaActiva.obtenerPosicion()
                                .obtenerColumna() + j] = piezaActiva.obtenerBloque(new Posicion(i, j));
                        if (sePuedeEliminarFila(piezaActiva.obtenerPosicion().obtenerFila() + i)) {
                            eliminarFila(piezaActiva.obtenerPosicion().obtenerFila() + i);
                            contadorFilasEliminadas++;
                        }
                    }
                }
            }
            puntuacion += contadorFilasEliminadas * 100;

            if (finalizoJuego()) {
                juegoFinalizado = true;
            } else {
                cambiarDePieza();
            }
        }

    }

    /**
     * revisa si alguna pieza sobrepaso los limites del tablero
     * 
     * @return Verdadero si alguna pieza sobrepaso los limites del tablero
     */
    public boolean finalizoJuego() {
        for (int i = 0; i < campoDeJuego[19].length; i++) {
            if (campoDeJuego[19][i] != null) {
                return true;
            }
        }
        return false;
    }

    public boolean obtenerEstadoJuego() {
        return juegoFinalizado;
    }

    /**
     * Revisa si la fila dada esta completa
     * 
     * @param fila la fila a revisar
     * @return verdadero si la fila se encuentra completa
     */
    public boolean sePuedeEliminarFila(int fila) {
        for (int i = 0; i < campoDeJuego[fila].length; i++) {
            if (obtenerBloque(new Posicion(fila, i)) == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * elimina la fila dada, desplaza todas las filas superiores una hacia abajo
     * 
     * @param fila la fila a eliminar
     */
    public void eliminarFila(int fila) {
        for (int i = fila - 1; i > 17; i--) {
            for (int j = 0; j < campoDeJuego[i].length; j++) {
                campoDeJuego[i + 1][j] = campoDeJuego[i][j];
            }
        }
    }

    /**
     * Realiza el cambio por una pieza nueva al bloquearse la anterior
     */
    public void cambiarDePieza() {
        piezaActiva = bolsa.sacarPieza();
        puedeCambiar = true;
        Posicion posicionAux = new Posicion(20, 3);
        if (piezaActiva.puedeMoverse(posicionAux)) {
            piezaActiva.asignarPosicion(posicionAux);
        }

    }

}