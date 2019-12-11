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
        // Esto esta aqui para recordar como se manejan archivos :)
        try (var in = new ObjectInputStream(new FileInputStream("juego"))) {
            tablero = (Tablero) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            tablero = Tablero.obtenerInstancia();
        }
        frameRate(2);
    }

    @Override
    public void draw() {
        background(0x000000);

    }
    /**
     * Ejecuta las siguientes acciones en dependencia de la tecla presionada:
     * FLECHA ARRIBA rota a la derecha
     * FLECHA ABAJO realiza un soft-drop
     * FLECHA DERECHA se mueve un bloque a la derecha
     * FLECHA IZQUIERDA se mueve un bloque a la izquierda
     * C guarda/cambia por la guardada la pieza actual
     * Z rota a la izquierda
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
        redraw(); // actualiza la pantalla
    }

    @Override
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
    }

}
