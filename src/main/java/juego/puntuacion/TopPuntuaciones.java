package juego.puntuacion;

import java.io.Serializable;

/**
 * Tiene la lista de las diez mejores puntuaciones
 */
public class TopPuntuaciones implements Serializable {
    private int[] top;
    private static final TopPuntuaciones INSTANCIA = new TopPuntuaciones();

    private TopPuntuaciones() {
        top = new int[10];
    }

    /**
     * Obtiene la instancia del top
     * @return instancia de las puntuaciones
     */
    public static TopPuntuaciones obtenerInstancia() {
        return INSTANCIA;
    }

    /**
     * Añade una puntuacion al top, si esta es menor que las diez primeras no se añade
     * @param puntuacion puntuacion a añadir
     * @throws Exception Si la puntuacion es negativa
     */
    public void añadirPuntuacion(int puntuacion) throws Exception{
        // Checa si al puntuacion es menor que 0
        if (puntuacion < 0) {
            throw new Exception("El valor de la puntuación no puede ser negativo");
        }
        // Añade la puntuaicon en el lugar i si esta es mayor que la que se encuentra en dicho lugar
        for (int i = 0; i < top.length; i++) {
            if (puntuacion > top[i]) {
                recorrer(i);
                top[i] = puntuacion;
                break;
            }
        }
    }

    /**
     * Devuelve un String con la puntuacion del lugar indicado
     * @param lugar lugar del cual obtener la puntuacion
     * @return puntuacion del lugar, devuelve una cadena vacia si no hay una puntuacion en este lugar
     * @throws IndexOutOfBoundsException si el lugar es menor que 0 o mayor que 10
     */
    public String obtenerPuntuacion(int lugar) throws IndexOutOfBoundsException {
        if (lugar < 0 || lugar >= 10) {
            throw new IndexOutOfBoundsException();
        }
        return (top[lugar] == 0) ? "" : Integer.toString(top[lugar]);
    }

    private void recorrer(int indice) {
        int auxiliar = 0;
        int auxiliar2 = 0;
        //Recorre las puntuaciones un lugar a partir del indice indicado
        auxiliar = top[indice];
        for (int i = indice; i < top.length - 1; i++) {
            auxiliar2 = top[i + 1];
            top[i + 1] = auxiliar;
            auxiliar = auxiliar2;
        }
    }
}