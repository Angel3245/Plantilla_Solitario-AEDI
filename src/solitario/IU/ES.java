package solitario.IU;

import java.util.Scanner;

/**
 *
 * @author AEDI
 */
public class ES {

    public static Scanner leer = new Scanner(System.in);

    public static String pideCadena(String mensaje) {

        // Poner el mensaje
        System.out.print(mensaje);

        // Pedir
        return leer.nextLine();

    }

    public static int pideNumero(String mensaje) {

        boolean repite;
        int toret = -1;

        do {

            try {

                repite = false;
                System.out.print(mensaje);

                // Pedir
                toret = Integer.parseInt(leer.nextLine());

            } catch (NumberFormatException exc) {

                repite = true;
                System.err.println(exc.getMessage());
            }

        } while (repite == true);

        return toret;
    }

    public static void limpiarPantalla(int lineas) {

        for (int i = 0; i < lineas; i++) {

            System.out.println();
        }
        //Parada de 10 milisegundos para que no salgan mensajes por el medio
        try {

            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println(e);

        }
    }
}
