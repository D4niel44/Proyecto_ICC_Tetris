package juego.pieza;

public class pruebaBolsaPiezas {
    public static void main(String[] args) {
        int i = 0, j = 0, l = 0, o = 0, s = 0, t = 0, z = 0;
        BolsaPiezas bolsa = new BolsaPiezas();
        for (int k = 0; k < Integer.parseInt(args[0]); k++) {
            System.out.println(bolsa.sacarPieza().getClass().getSimpleName());
            switch (bolsa.sacarPieza().getClass().getSimpleName()) {
            case "I":
                i++;
                break;
            case "J":
                j++;
                break;
            case "L":
                l++;
                break;
            case "O":
                o++;
                break;
            case "S":
                s++;
                break;
            case "T":
                t++;
                break;
            case "Z":
                z++;
                break;
            default:
                break;
            }
        }
        System.out.println();
        System.out.println("I: " + i + " " + i / Double.parseDouble(args[0]));
        System.out.println("J: " + j + " " + j / Double.parseDouble(args[0]));
        System.out.println("L: " + l + " " + l / Double.parseDouble(args[0]));
        System.out.println("O: " + o + " " + o / Double.parseDouble(args[0]));
        System.out.println("S: " + s + " " + s / Double.parseDouble(args[0]));
        System.out.println("T: " + t + " " + t / Double.parseDouble(args[0]));
        System.out.println("Z: " + z + " " + z / Double.parseDouble(args[0]));

    }
}