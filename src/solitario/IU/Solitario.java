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

        // Bucle ppal
        try {
            do {

                System.out.println("\nSOLITARIO");

                op = menu();

                switch (op) {
                    case 0:
                        System.out.println("Fin.");
                        break;
                    case 1:
                        inicioPartida();
                        break;
                    case 2:
                        reglas();
                        break;
                    case 3:
                        creditos();
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
                + "\n\n- No se puede mover una carta a un hueco vacío en la zona-interior.  ");
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
                + "\n\nSpecial thanks to Rosalía de Castro, Kings y Charlie porque sin "
                + "ellas esto no sería posible. Y no nos olvidemos del COVID-19 "
                + "por darnos tantas esperanzas y tiempo para poder hacerlo."
        );
    }

    private static void inicioPartida() throws Exception {

        String nombre = ES.pideCadena("Introduce tu nombre: ");
        Jugador j1 = new Jugador(nombre);
        //Jugador j1 = new Jugador("solitario");

        Mesa mesa = new Mesa();

        String tipoCarta;
        int[] coordenadas;
        char eleccion;

        while (mesa.hayMovimentos()){
            try{
                System.out.println(mesa); //Mostramos la mesa actual

                System.out.println("Existen " + mesa.getMovimientos() + " movimientos posibles.\n"); //Indicamos el número de posibles movimientos

                coordenadas = getCoordenadas(ES.pideCadena("Introduce posicion: ")); //Pedimos al usuario que introduzca una posición para realizar un movimiento

                tipoCarta = j1.comprobarPosicion(coordenadas[0], coordenadas[1], mesa);

                switch (tipoCarta) {
                    case ("vacia"): // Si no hay ninguna carta en la posición indicada
                        System.err.println("La posición está: " + tipoCarta);
                        break;

                    case ("pertenece al montón exterior"): // Si la carta pertenece al montón exterior
                        System.err.println("La carta " + tipoCarta);
                        break;

                    case ("oculta"): //Si la carta está oculta
                        
                        eleccion = pideEleccion("¿Que quieres hacer " + j1.getNombre() + " ?\n"
                                + "\t[A] Voltear carta" + "  [B] Atrás");
                        switch (eleccion) {
                            case 'A':
                                if(!j1.voltear(coordenadas[0], coordenadas[1], mesa))
                                    System.err.println("No se ha podido voltear la carta.");
                                break;
                        }
                        break;

                    default: // Si la carta existe y no está oculta
                        System.out.println("Has escogido la carta: " + mesa.getMontonInterior()[coordenadas[0]][coordenadas[1]].peek());
                        eleccion = pideEleccion("¿Que quieres hacer " + j1.getNombre() + " ?\n"
                                + "\t[A] Mover carta" + "  [B] Atrás");
                        switch (eleccion) {
                            case 'A':
                                int[] coordenadasDestino;

                                System.out.println("¿A dónde quieres mover la carta " + j1.getNombre() + " ?");
                                coordenadasDestino = getCoordenadas(ES.pideCadena("Introduce posicion: "));
                                if(!j1.mover(coordenadas[0], coordenadas[1], coordenadasDestino[0], coordenadasDestino[1], mesa))
                                    System.err.println("No se ha podido mover la carta. La carta regresa al origen.");

                                break;
                    }
                }
            }
            catch(Exception exc){
                System.err.println(exc.getMessage());
            }

        } 
        
        if(mesa.ganador()){
            System.out.println("FELICIDADES: HAS GANADO " + j1.getNombre() + " :)");
        }
        else{
            System.err.println("F. HAS PERDIDO otra vez... " + j1.getNombre() + " :(");
        }
    }

    //Divide la posicion recibida en las coordenadas i, j que devuelve en un array
    private static int[] getCoordenadas(String posicion) throws Exception {
        int i, j;
        
        if(posicion.length() != 2){
            throw new Exception("Has introducido una cantidad de elementos distinta de 2.");
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
            eleccion = ES.pideCadena("Introduce elección: ").charAt(0);
        } while (eleccion != 'A' && eleccion != 'B');

        return eleccion;
    }

}
