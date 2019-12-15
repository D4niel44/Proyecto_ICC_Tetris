package juego.pieza;

/**
 * Clase de la pieza O
 */
public class O extends Pieza {

    /**
     * Crea una nueva pieza con indice en la posicion dada
     * @param posicion posicion
     */
    public O(Posicion posicion) {
        super(posicion);
        /* Crea la matriz de bloques que representa la estructura de la pieza
         */
        Bloque[][] matriz = new Bloque[3][4];
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j < 3; j++) {
            matriz[i][j] = new Bloque(Color.AMARILLO);
            }
        }
        this.matriz = matriz;
    }

    public void rotarSentidoHorario() {}; // no hace nada ya que la O no rota
    public void rotarSentidoAntihorario() {}; // no hace nada ya que la O no rota
}