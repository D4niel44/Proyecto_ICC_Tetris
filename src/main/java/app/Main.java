package app;

import java.io.*;
import processing.core.PApplet;
import processing.event.KeyEvent;
import juego.Tablero;
import juego.pieza.Bloque;
import juego.pieza.BolsaPiezas;
import juego.pieza.Color;
import juego.pieza.Pieza;
import juego.pieza.Posicion;

public class Main extends PApplet {

    private Tablero tablero;
    private boolean caer;
    float largo;
    float ancho;

    public static void main(String[] args) {
        PApplet.main("app.Main");
    }

    @Override
    public void settings() {
        // dividiendo la ventana en 26 filas x 23 columnas de bloques y tomando como la
        // altura como displayHeight * 4 / 5
        size(displayHeight * 46 / 65, displayHeight * 4 / 5);
    }

    @Override
    public void setup() {
        ancho = displayHeight * 46 / 65;
        largo = displayHeight * 4 / 5;

        // Esto esta aqui para recordar como se manejan archivos :)
        /*try (var in = new ObjectInputStream(new FileInputStream("juego"))) {
            tablero = (Tablero) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            tablero = Tablero.obtenerInstancia();
        }*/
        tablero = Tablero.obtenerInstancia();
        frameRate(2);
    }

    @Override
    public void draw() {
        if (tablero.finalizoJuego()) {
            exit();
        }

        if (caer) {
            tablero.caerPieza();
        } else {
            caer = true;
        }
        background(0x000000);

        line(3 / 23 * ancho, 3 / 26 * largo, 13 / 23 * ancho, 3 / 26 * largo);
        line(3 / 23 * ancho, 3 / 26 * largo, 3 / 23 * ancho, 13 / 26 * largo);
        line(3 / 23 * ancho, 13 / 26 * largo, 13 / 23 * ancho, 13 / 26 * largo);
        line(13 / 23 * ancho, 3 / 26 * largo, 13 / 23 * ancho, 13 / 26 * largo);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                Posicion posicionBloque = new Posicion(20 + i, j);
                if (tablero.obtenerBloque(posicionBloque) != null) {
                    fill(colorRGB(tablero.obtenerBloque(posicionBloque).obtenerColor()));
                    rect((j * 1 / 23 + 3 / 23) * ancho, (i * 1 / 26 + 3 / 26) * largo, 1 / 23 * ancho, 1 / 26 * largo);
                }
            }
        }

        Pieza piezaActiva = tablero.obtenerPiezaActiva();
        for (int i = 0; i < piezaActiva.obtenerFilas(); i++) {
            for (int j = 0; j < piezaActiva.obtenerFilas(); j++) {
                if (piezaActiva.obtenerBloque(new Posicion(i, j)) != null) {
                    fill(colorRGB(piezaActiva.obtenerBloque(new Posicion(i, j)).obtenerColor()));
                    int columna = piezaActiva.obtenerPosicion().obtenerColumna();
                    int fila = piezaActiva.obtenerPosicion().obtenerFila();
                    rect(((columna + j) * 1/23 + 3 / 23) * ancho, ((fila - 20 + i) * 1/26 + 3/26) * largo, 1/23 * ancho, 1/26 * largo);
                }
            }
        }

        Pieza piezaSiguiente = tablero.obtenerBolsa().observarSiguientePieza(); 
        for (int i = 0; i < piezaSiguiente.obtenerFilas(); i++) {
            for ( int j = 0; j < piezaSiguiente.obtenerColumnas(); j++ ) {
                if (piezaSiguiente.obtenerBloque(new Posicion(i, j)) != null) {
                    fill(colorRGB(piezaSiguiente.obtenerBloque(new Posicion(i, j)).obtenerColor()));
                    rect((j * 1 / 23 + 16 / 23) * ancho , (i * 1 / 26 + 5 /26) * largo, 1 / 23 * ancho, 1 / 26 * largo);
                }
            }
        }
    }

    /**
     * Ejecuta las siguientes acciones en dependencia de la tecla presionada: FLECHA
     * ARRIBA rota a la derecha FLECHA ABAJO realiza un soft-drop FLECHA DERECHA se
     * mueve un bloque a la derecha FLECHA IZQUIERDA se mueve un bloque a la
     * izquierda C guarda/cambia por la guardada la pieza actual Z rota a la
     * izquierda
     */
    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
        case KeyEvent.VK_UP:
            tablero.obtenerPiezaActiva().rotarSentidoHorario();
            break;
        case KeyEvent.VK_DOWN:
            tablero.caerPieza();
            break;
        case KeyEvent.VK_LEFT:
            tablero.obtenerPiezaActiva().moverIzquierda();
            break;
        case KeyEvent.VK_RIGHT:
            tablero.obtenerPiezaActiva().moverDerecha();
            break;
        case KeyEvent.VK_C:
            tablero.obtenerPiezaActiva().rotarSentidoAntihorario();
            break;
        case KeyEvent.VK_Z:
            tablero.guardarPieza(); // est metodo no esta bien implementado aun.
            break;
        }
        caer = false;
        redraw(); // actualiza la pantalla
    }

    /*@Override
    public void exit() {
        // Esto esta aqui para recordar como se manejan archivos :)
        try (var out = new ObjectOutputStream(new FileOutputStream("juego"))) {
            out.writeObject(tablero);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            dispose();
            System.exit(0);
        }
    }*/

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
