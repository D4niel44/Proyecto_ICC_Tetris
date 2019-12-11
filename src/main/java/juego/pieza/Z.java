package juego.pieza;

public class Z extends Pieza {

	private static final Bloque[][][] estadoPieza = new Bloque[][][]{
        {{new Bloque(Color.ROJO), new Bloque(Color.ROJO), null},
         {null, new Bloque(Color.ROJO), new Bloque(Color.ROJO)},
         {null, null, null}
        },
        {{null, null, new Bloque(Color.ROJO)},
         {null, new Bloque(Color.ROJO), new Bloque(Color.ROJO)},
         {null, new Bloque(Color.ROJO), null}
        },
        {{null, null, null},
         {new Bloque(Color.ROJO), new Bloque(Color.ROJO), null},
         {null, new Bloque(Color.ROJO), new Bloque(Color.ROJO)}
        },
        {{null, new Bloque(Color.ROJO), null},
         {new Bloque(Color.ROJO), new Bloque(Color.ROJO), null},
         {new Bloque(Color.ROJO), null, null},
        }
    };

    public Z(Posicion posicion) {
        super(posicion);
    }
}