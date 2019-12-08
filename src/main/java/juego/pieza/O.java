package juego.pieza;

/**
 * Clase de la pieza O
 */
public class O extends Pieza {

    public O(Posicion posicion) {
        super(posicion);
        Bloque[][] matriz = new Bloque[3][4];
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j < 3; j++) {
            matriz[i][j] = new Bloque(Color.AMARILLO);
            }
        }
        this.matriz = matriz;
    }

    public void rotarSentidoHorario() {};
    public void rotarSentidoAntihorario() {};
}