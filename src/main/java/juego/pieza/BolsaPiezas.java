package juego.pieza;

import java.util.List;
import java.util.LinkedList;
import java.util.Random;
import java.io.Serializable;

/**
 * Clase que genera las piezas aleatoriamente.
 * 
 */
public class BolsaPiezas implements Serializable {

    private List<Pieza> bolsa;

    /**
     * Constructor de la clase genera una bolsa con 7 piezas
     */
    public BolsaPiezas() {
        bolsa = generarPiezas();
    }

    /**
     * Extrae la primera pieza de la lista y la elimina de esta
     * 
     * @return Primera pieza de la lista
     */
    public Pieza sacarPieza() {
        Pieza retorno = bolsa.remove(0);
        if (bolsa.size() == 1) {
            bolsa.addAll(generarPiezas());
        }
        return retorno;
    }

    /**
     * Obtiene la primera pieza de la lista(no la elimina)
     * 
     * @return la primera pieza de la lista
     */
    public Pieza observarSiguientePieza() {
        return bolsa.get(0);
    }

    private static List<Pieza> generarPiezas() {
        // Crea una lista iniciar de todas la piezas la cual se ordenara aleatoriamente
        List<Pieza> piezas = new LinkedList<Pieza>();
        piezas.add(new I(new Posicion(17, 3)));
        piezas.add(new J(new Posicion(18, 3)));
        piezas.add(new L(new Posicion(18, 3)));
        piezas.add(new O(new Posicion(18, 3)));
        piezas.add(new S(new Posicion(18, 3)));
        piezas.add(new T(new Posicion(18, 3)));
        piezas.add(new Z(new Posicion(18, 3)));

        // Crea la lista a la cual se le añadiran las piezas en orden aleatorio
        List<Pieza> piezasOrdenadas = new LinkedList<Pieza>();

        // Añade las piezas a la nueva lista en orden aleatorio
        Random generador = new Random();
        while (piezas.size() > 0) {
            double aleatorio = generador.nextDouble();
            Pieza p = piezas.get((int) Math.floor(aleatorio * piezas.size()));
            piezas.remove(p);
            piezasOrdenadas.add(p);
        }
        return piezasOrdenadas;
    }
}