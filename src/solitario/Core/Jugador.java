package solitario.Core;

/** Representa al único jugador de la partida, identificado por el nombre 
 *  Funcionalidad: le da la vuelta a una carta que está boca abajo, escoge una carta para moverla o al montón de descarte
 *                o la mueve encima de otra carta del interior
 *
 * @author AEDI
 */
public class Jugador {

    private String nombre;
    
    /**
     * Constructor jugador
     * @param nombre el nombre del jugador, como String
     * 
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nombre del jugador
     *
     * @return nombre el nombre del jugador como String
     */
    public String getNombre() {
        return nombre;
    }
    
    
    /**
     * Devuelve "v" si la pila esta vacia, "e" si la carta pertenece al montón 
     * exterior o "0" si no se da ninguna
     * @param i la posición (fila) del montón interior 
     * @param j la posición (columna) del montón 
     * @param mesa la mesa con la que se está jugando
     * @return la letra indicadora de situación, como char
     */

    //Este método se utilizara en la clase Solitario
    public char comprobarPosicion(int i, int j, Mesa mesa) {
        if (j == 4) {
            return 'e';                   //La carta pertenece al montón exterior
        }
        if (mesa.montonInteriorVacio(i, j)) {
            return 'v';                  //Comprobamos si esta vacia
        }
        /*if (mesa.mirarCartaMontonInterior(i, j).getOculta()) {
            return "oculta";      //Comprobamos si la carta de arriba esta boca abajo
        }*/

        return '0';                    //Si (la carta no esta boca abajo y )la pila no esta vacia retorna 0

    }

    /*public boolean voltear(int i, int j, Mesa mesa) {    //Retorna un booleano en funcion de si voltea la carta o no

        if (mesa.mirarCartaMontonInterior(i, j).getOculta()) {      //Si la carta de arriba del monton esta boca abajo o no 

            mesa.voltear(i, j);      //Si esta boca abajo, le damos la vuelta
            return true;

        } else {

            return false;            //Si no esta boca abajo no hacemos nada
        }
    }*/
    
    /**
     * Mueve una carta de una posición pasada como parámetro a otra pasada también si el movimiento es posible
     * @param iOrigen la posición origen (fila) del montón interior 
     * @param jOrigen la posición origen (columna) del montón 
     * @param iDestino la posición destino (fila) del montón interior o posición del montón exterior
     * @param jDestino la posición destino (columna) del montón interior o, si es 4 indicadora de que el destino es el montón exterior
     * @param mesa la mesa con la que se está jugando
     * @return true si el movimiento se produce o false en caso contrario
     */
    public boolean mover(int iOrigen, int jOrigen, int iDestino, int jDestino, Mesa mesa) {

        //Necesita que se pueda sacar la carta
        Carta c = mesa.sacarCartaMontonInterior(iOrigen, jOrigen);    //Asignamos la carta al objeto c (Desde la posicion introducida por parametros)        
        //MONTON EXTERIOR
        if (jDestino == 4) {      //Si jDestino es un 4 sabemos que el destino es el monton exterior; Si es 0,1,2,3 es el monton interior

            if (mesa.montonExteriorVacio(iDestino)) {
                //Meter la primera carta en el montón
                if (c.getNumero() == 1) {

                    mesa.moverExterior(c, iDestino);

                    //Se voltear la cartade abajo
                    mesa.voltear(iOrigen, jOrigen);

                    return true;
                }       //Continuar metiendo cartas en un montón ya iniciado
            } else if (mesa.comprobarMayor(mesa.mirarCartaMontonExterior(iDestino), c)) {

                mesa.moverExterior(c, iDestino);

                //Se voltear la cartade abajo
                mesa.voltear(iOrigen, jOrigen);

                return true;
            }

        } else {
            //MONTON INTERIOR
            if (!mesa.montonInteriorVacio(iDestino, jDestino)) {
                Carta abajo = mesa.mirarCartaMontonInterior(iDestino, jDestino);

                if (mesa.comprobarMayor(c, abajo) && !abajo.getOculta()) {
                    mesa.moverInterior(c, iDestino, jDestino);

                    //Se voltear la cartade abajo
                    mesa.voltear(iOrigen, jOrigen);

                    return true;

                }
            }

        }

        mesa.moverInterior(c, iOrigen, jOrigen);  //La carta la devolvemos al montón de origen

        return false;
    }

}
