package app;

import java.io.*;
import processing.core.PApplet;
import processing.sound.SoundFile;
import juego.Tablero;
import juego.pieza.Color;
import juego.pieza.Pieza;
import juego.pieza.Posicion;
import juego.puntuacion.Puntuaciones;

/**
 * Clase principal de la parte gráfica del juego
 */
public class Main extends PApplet {

    private Tablero tablero;
    boolean estaReproduciendo;
    SoundFile musica;
    String[][] topPuntuaciones;
    final int retardo = 500;
    int tiempo;

    /**
     * inicia la aplicacion
     * 
     * @param args
     */
    public static void main(String[] args) {
        PApplet.main("app.Main");
    }

    /**
     * Esteblece el tamaño de la ventana del juego
     */
    @Override
    public void settings() {
        size(700, 900);
    }

    /**
     * Establece las condiciones iniciales del juego
     */
    @Override
    public void setup() {

        musica = new SoundFile(this, getClass().getResource("/Tetris.mp3").getPath());
        estaReproduciendo = true;
        musica.loop();
        tablero = Tablero.obtenerInstancia();
        try {
            topPuntuaciones = Puntuaciones.topPuntuaciones("/puntuaciones.csv");
        } catch (IOException e) {
            topPuntuaciones = new String[10][];
            String[] puntuacion = new String[] { "", "" };
            for (int i = 0; i < topPuntuaciones.length; i++) {
                topPuntuaciones[i] = puntuacion;
            }
        }
        tiempo = 0;
        //frameRate(30);

    }

    /**
     * Dibuja la ventana
     */
    @Override
    public void draw() {

        // Cierra el juego si este se acaba
        if (tablero.obtenerEstadoJuego()) {
            exit();
        }

        // Evalua si la pieza debe caer o no
        if (millis() > tiempo + retardo ) {
            tablero.caerPieza();
            tiempo = millis();
        }
        

        // Dibuja los limites del tablero
        background(0x000000);
        stroke(0xffffffff);
        line(50, 50, 450, 50);
        line(50, 50, 50, 850);
        line(50, 850, 450, 850);
        line(450, 50, 450, 850);

        // Dibuja el tablero
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                Posicion posicionBloque = new Posicion(20 + i, j);
                if (tablero.obtenerBloque(posicionBloque) != null) {
                    fill(colorRGB(tablero.obtenerBloque(posicionBloque).obtenerColor()));
                    rect(50 + j * 40, 50 + i * 40, 40, 40);
                }
            }
        }
        // Dibuja la pieza activa
        Pieza piezaActiva = tablero.obtenerPiezaActiva();
        for (int i = 0; i < piezaActiva.obtenerFilas(); i++) {
            for (int j = 0; j < piezaActiva.obtenerFilas(); j++) {
                if (piezaActiva.obtenerPosicion().obtenerFila() > 19) {
                    if (piezaActiva.obtenerBloque(new Posicion(i, j)) != null) {
                        fill(colorRGB(piezaActiva.obtenerBloque(new Posicion(i, j)).obtenerColor()));
                        int columna = piezaActiva.obtenerPosicion().obtenerColumna();
                        int fila = piezaActiva.obtenerPosicion().obtenerFila();
                        rect((columna + j) * 40 + 50, (fila - 20 + i) * 40 + 50, 40, 40);
                    }
                }
            }
        }
        // Dibuja la pieza fantasma
        Pieza piezaFantasma = tablero.obtenerPiezaActiva().piezaFantasma();
        for (int i = 0; i < piezaFantasma.obtenerFilas(); i++) {
            for (int j = 0; j < piezaFantasma.obtenerFilas(); j++) {
                if (piezaFantasma.obtenerPosicion().obtenerFila() > 19) {
                    if (piezaFantasma.obtenerBloque(new Posicion(i, j)) != null) {
                        fill(colorRGB(piezaFantasma.obtenerBloque(new Posicion(i, j)).obtenerColor()) - 0xb0000000);
                        int columna = piezaFantasma.obtenerPosicion().obtenerColumna();
                        int fila = piezaFantasma.obtenerPosicion().obtenerFila();
                        rect((columna + j) * 40 + 50, (fila - 20 + i) * 40 + 50, 40, 40);
                    }
                }
            }
        }
        // dibuja la siguiente pieza
        fill(0xff008080);
        text("Siguiente Pieza", 500, 80);
        Pieza piezaSiguiente = tablero.obtenerBolsa().observarSiguientePieza();
        for (int i = 0; i < piezaSiguiente.obtenerFilas(); i++) {
            for (int j = 0; j < piezaSiguiente.obtenerColumnas(); j++) {
                if (piezaSiguiente.obtenerBloque(new Posicion(i, j)) != null) {
                    fill(colorRGB(piezaSiguiente.obtenerBloque(new Posicion(i, j)).obtenerColor()));
                    rect(40 * j + 490, 40 * i + 100, 40, 40);
                }
            }
        }
        // dibuja la pieza guardada
        fill(0xff008080);
        text("Pieza Guardada", 500, 280);
        if (tablero.obtenerPiezaGuardada() != null) {
            Pieza piezaGuardada = tablero.obtenerPiezaGuardada();
            for (int i = 0; i < piezaGuardada.obtenerFilas(); i++) {
                for (int j = 0; j < piezaGuardada.obtenerColumnas(); j++) {
                    if (piezaGuardada.obtenerBloque(new Posicion(i, j)) != null) {
                        fill(colorRGB(piezaGuardada.obtenerBloque(new Posicion(i, j)).obtenerColor()));
                        rect(40 * j + 490, 40 * i + 300, 40, 40);
                    }
                }
            }
        }

        // Dibuja el top de puntuaciones
        fill(0xff008080);
        text("top Puntuaciones", 500, 500);
        for (int i = 0; i < 10; i++) {
            text((i + 1) + " " + topPuntuaciones[i][0] + ": " + topPuntuaciones[i][0], 500, i * 15 + 515);
        }

        // Dibuja puntuacion del juego actual
        fill(0xff008080);
        text("puntuacion actual: " + tablero.obtenerPuntuacion(), 500, 680);
    }

    /**
     * Ejecuta las siguientes acciones en dependencia de la tecla presionada: FLECHA
     * ARRIBA rota a la derecha FLECHA ABAJO realiza un soft-drop FLECHA DERECHA se
     * mueve un bloque a la derecha FLECHA IZQUIERDA se mueve un bloque a la
     * izquierda C guarda/cambia por la guardada la pieza actual Z rota a la
     * izquierda, P para reproducir/pausar la musica SPACE para que la pieza se mueva hasta la posicion donde pueda caer(hardDrop)
     */
    @Override
    public void keyPressed() {
        if (key == CODED) {
            switch (keyCode) {
            case UP:
                tablero.obtenerPiezaActiva().rotarSentidoHorario();
                break;
            case DOWN:
                tablero.caerPieza();
                break;
            case LEFT:
                tablero.obtenerPiezaActiva().moverIzquierda();
                break;
            case RIGHT:
                tablero.obtenerPiezaActiva().moverDerecha();
                break;
            }
        } else {
            switch (key) {
            case 'z':
            case 'Z':
                tablero.obtenerPiezaActiva().rotarSentidoAntihorario();
                break;
            case 'c':
            case 'C':
                tablero.guardarPieza();
                break;
            case 'p':
            case 'P':
                if (estaReproduciendo) {
                    musica.stop();
                    estaReproduciendo = false;
                } else {
                    musica.play();
                    estaReproduciendo = true;
                }
                break;
            case ' ':
                tablero.caidaFuerte();
                break;
            }
        }
    }

    /**
     * Cierra la aplicacion
     */
    @Override
    public void exit() {
        musica.stop();
        try {
            Puntuaciones.guardarPuntuacion(getClass().getResource("/puntuaciones.csv").getPath(),
                    new String[] { "", Integer.toString(tablero.obtenerPuntuacion()) });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dispose();
            System.exit(0);
        }
    }

    /**
     * Devuelve un entero con la representacion rgb del color pasado
     * 
     * @param color Color de entrada
     * @return Entero con la representacion en rgb del color
     */
    public int colorRGB(Color color) {
        int rgb;
        switch (color) {
        case CYAN:
            rgb = 0xff00ffff;
            break;
        case AZUL:
            rgb = 0xff0000ff;
            break;
        case NARANJA:
            rgb = 0xffff8000;
            break;
        case AMARILLO:
            rgb = 0xffffff00;
            break;
        case VERDE:
            rgb = 0xff00ff00;
            break;
        case MORADO:
            rgb = 0xff800080;
            break;
        case ROJO:
            rgb = 0xffff0000;
            break;
        default:
            rgb = 0x00000000;
        }
        return rgb;
    }

}
