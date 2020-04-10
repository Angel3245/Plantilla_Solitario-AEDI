/**
 * Representa el juego del solitario, con sus reglas.
 * Se recomienda una implementación modular.
 */
package solitario.IU;

import solitario.Core.Jugador;
import solitario.Core.Mesa;

/**
 *
 * @author AEDI
 */
public class Solitario {

    public static void menuInicio() {

        int op;
        boolean repite;
        String pausa;

        // Bucle ppal
        try {
            do {

                System.out.println("\nSOLITARIO");

                op = menu();

                switch (op) {
                    case 0:
                        System.out.println("FIN.");
                        System.exit(0); //Acaba con la ejecución
                        break;
                    case 1:
                        inicioPartida();
                        break;
                    case 2:
                        ES.limpiarPantalla(21);
                        reglas();
                        pausa = ES.pideCadena("Pulsa Intro para continuar...");
                        ES.limpiarPantalla(21);
                        break;
                    case 3:
                        ES.limpiarPantalla(21);
                        creditos();
                        pausa = ES.pideCadena("Pulsa Intro para continuar...");
                        ES.limpiarPantalla(21);
                        break;

                    default:
                        System.out.println("La opción introducida no es correcta ( " + op + " )");
                }

            } while (op != 0);
        } catch (Exception exc) {

            System.err.println(exc.getMessage());
        }
    }

    /**
     * Presenta un menu con las opciones, y permite seleccionar una.
     *
     * @return la opcion seleccionada, como entero
     */
    private static int menu() {

        int toret;

        do {
            System.out.println(
                    "\n1. Nueva partida\n"
                    + "2. Reglas\n"
                    + "3. Créditos\n"
                    + "0. Salir\n");
            toret = ES.pideNumero("Selecciona: ");

        } while (toret < 0
                && toret > 4);

        System.out.println();
        return toret;
    }

    /**
     * Muestra por pantalla las reglas del juego.
     *
     * @return las reglas por pantalla
     */
    private static void reglas() {

        System.out.println("\nOBJETIVO:"
                + "\nEl objetivo es ir quitando las cartas de la zona-interior"
                + "\nde menor a mayor colocándolas en cuatro montones exteriores, "
                + "\n(uno para cada palo) que han de empezar con la carta numerada con el 1. \n"
                + "\n\nREGLAS DEL JUEGO:"
                + "\n- Se puede mover una carta de la zona-interior "
                + "\nal montón exterior si la última carta almacenada en el montón exterior "
                + "\nes del mismo palo y una unidad más pequeña que la que se quiere colocar "
                + "\n(fíjate que después del 7 viene el 10). "
                + "\nSi debajo de ella existiese una carta boca abajo se gira para hacerla visible. "
                + "\n\n- Si el montón exterior está vacío, sólo se podrá colocar una carta que contenga un as. "
                + "\n\n- En la zona-interior se puede mover una carta sobre otra, siempre que la "
                + "\ncarta que se va a ocultar sea del mismo palo y una unidad mayor "
                + "\nque la que se mueve (fíjate que encima del 10 se debe colocar un 7). "
                + "\nEste movimiento tiene como objetivo hacer visible la carta "
                + "\nque está debajo de la que se está moviendo. "
                + "\n\n- No se puede mover una carta a un hueco vacío en la zona-interior.\n");
    }

    /**
     * Muestra por pantalla los créditos del juego.
     *
     * @return los créditos por pantalla
     */
    private static void creditos() {

        System.out.println("\nCRÉDITOS:"
                + "\n\tGRUPO AEDI 6"
                + "\n\tDiego Nespereira Rodríguez"
                + "\n\tYeray Villamarín Osorio"
                + "\n\tCristina Outeiriño Cid"
                + "\n\tCésar José Pérez Alén"
                + "\n\tJose Ángel Pérez Garrido"
                + "\n");

    }

    private static void inicioPartida() throws Exception {

        //String pausa;
        char tipoCarta;
        int[] coordenadas = new int[]{0, 0};
        int[] coordenadasDestino = new int[]{0, 0};
        char eleccion;
        String comando;
        String coordenadasDestinoAUX;

        String nombre = ES.pideCadena("Introduce tu nombre: ");
        Jugador j1 = new Jugador(nombre);
        //Jugador j1 = new Jugador("solitario");

        //pausa = ES.pideCadena("Pulsa Intro para continuar...");
        ES.limpiarPantalla(21);

        Mesa mesa = new Mesa();

        while (mesa.hayMovimentos()) {

            try {

                System.out.println(mesa); //Mostramos la mesa actual

                // System.out.println("Existen " + mesa.getNumMovimientos() + " movimientos posibles.\n"); //Indicamos el número de posibles movimientos
                do {
                    System.out.println("Existen " + mesa.getNumMovimientos() + " movimientos posibles.\n"); //Indicamos el número de posibles movimientos
                    comando = ES.pideCadena("Introduce posición (ej.\"1A\"), AYUDA o SALIR: ");
                    comando = comando.toUpperCase().trim();
                    switch (comando) {

                        case "SALIR":

                            char afirmar = ES.pideCadena("\n¿Seguro que quieres volver al menu principal? (S/N)").toUpperCase().trim().charAt(0);

                            if (afirmar == 'S') {

                                Main.main(null); //Volver al menú principal
                            }

                            ES.limpiarPantalla(21);
                            System.out.println(mesa);
                            break;

                        case "AYUDA":
                            ES.limpiarPantalla(21);
                            System.out.println("POSIBLES MOVIMIENTOS:");
                            System.out.println(mesa.getMovimientos());
                            System.out.println(mesa);
                            break;

                        default:

                            coordenadas = getCoordenadas(comando);
                            break;
                    }

                } while ("AYUDA".equals(comando) || "SALIR".equals(comando));

                tipoCarta = j1.comprobarPosicion(coordenadas[0], coordenadas[1], mesa);

                switch (tipoCarta) {
                    case 'v': // Si no hay ninguna carta en la posición indicada
                        ES.limpiarPantalla(21);
                        System.out.println("\033[31mLa posición está vacia \33[30m");
                        break;

                    case 'e': // Si la carta pertenece al montón exterior
                        ES.limpiarPantalla(21);
                        System.out.println("\033[31mLa carta pertenece al montón exterior \33[30m");
                        break;

                    /*case ("oculta"): //Si la carta está oculta
                        
                        eleccion = pideEleccion("\n¿Que quieres hacer " + j1.getNombre() + " ?\n"
                                + "\t[A] Voltear carta" + "  [B] Atrás");
                        switch (eleccion) {
                            case 'A':
                                if(!j1.voltear(coordenadas[0], coordenadas[1], mesa)){
                                    ES.limpiarPantalla(21);
                                    System.err.println("No se ha podido voltear la carta.");
                                }
                                else{
                                    ES.limpiarPantalla(21);
                                }
                                break;
                        }
                        break;*/
                    default: // Si la carta existe y no es de montón exterior (y no está oculta) 

                        System.out.println("\n¿A dónde quieres mover la carta " + mesa.mirarCartaMontonInterior(coordenadas[0], coordenadas[1]) + ", " + j1.getNombre() + "?");

                        coordenadasDestinoAUX = ES.pideCadena("Introduce posición o pulsa Enter para volver atrás: ");

                        if (!coordenadasDestinoAUX.equals("")) {

                            coordenadasDestino = getCoordenadas(coordenadasDestinoAUX);

                            if (!j1.mover(coordenadas[0], coordenadas[1], coordenadasDestino[0], coordenadasDestino[1], mesa)) {

                                ES.limpiarPantalla(21);
                                System.out.println("\033[31mNo se ha podido mover la carta. La carta regresa al origen. \33[30m");
                            } else {
                                ES.limpiarPantalla(21);
                            }

                        } else {

                            ES.limpiarPantalla(21);
                        }

                }
            } catch (Exception exc) {

                ES.limpiarPantalla(21);
                System.err.println(exc.getMessage());
            }

        }

        if (mesa.ganador()) {
            //Mensaje de ganador en color verde
            System.out.println("\033[32mFELICIDADES: HAS GANADO " + j1.getNombre() + " :)\33[30m");
        } else {
            System.out.println("\033[31mF. HAS PERDIDO otra vez... " + j1.getNombre() + " :(\33[30m");
        }
    }

    //Divide la posicion recibida en las coordenadas i, j que devuelve en un array
    private static int[] getCoordenadas(String posicion) throws Exception {
        posicion = posicion.toUpperCase().trim();
        int i, j;

        if (posicion.length() != 2) {
            throw new Exception("Has introducido una posicion / comando no válido.");
        }

        i = Character.getNumericValue(posicion.charAt(0)) - 1;
        j = posicion.charAt(1) - 64 - 1;

        if ((i < 0 || i > 3) || (j < 0 || j > 4)) {
            throw new Exception("La posición introducida no es correcta.");
        }

        return new int[]{i, j};
    }

    private static char pideEleccion(String mensaje) {
        char eleccion;
        do {
            System.out.println(mensaje);
            eleccion = ES.pideCadena("Introduce elección: ").toUpperCase().trim().charAt(0);
        } while (eleccion != 'A' && eleccion != 'B');

        return eleccion;
    }

}
