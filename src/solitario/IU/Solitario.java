/**
 * Representa el juego del solitario, con sus reglas. 
 * Se recomienda una implementación modular.
 */
package solitario.IU;


/**
 *
 * @author AEDI
 */
public class Solitario {
    

    public static void inicioPartida(){
        int op;
        boolean repite;

        // Bucle ppal
        try{
            do {
                System.out.println( "\nSOLITARIO" );

                op = menu();

                switch( op ) {
                    case 0:
                        System.out.println( "Fin." );
                        break;
                    case 1:
                        //juego();
                        break;
                    case 2:
                        reglas();
                        break;
                    case 3:
                        //creditos();
                        break;

                    default:
                        System.out.println( "La opción introducida no es correcta ( " 
                                            + op + " )" );
                }
            } while( op != 0 );
        }
        catch(Exception exc){
            System.err.println(exc.getMessage());
        }
    } 

    /**
     * Presenta un menu con las opciones, y permite seleccionar una.
     * @return la opcion seleccionada, como entero
     */
    private static int menu()
    {
        int toret;

        do {
            System.out.println(
                              "\n1. Nueva partida\n"
                            + "2. Reglas\n"
                            + "3. Créditos\n"
                            + "0. Salir\n" );
            toret = ES.pideNumero( "Selecciona: " );
        } while( toret < 0
              && toret > 4 );

        System.out.println();
        return toret;
    }
    
    /**
     * Muestra por pantalla las reglas del juego.
     * @return las reglas por pantalla
     */
    private static void reglas()
    {
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
     * @return los créditos por pantalla
     */
    private static void creditos()
    {
        System.out.println("\nCRÉDITOS:");
    }
}
