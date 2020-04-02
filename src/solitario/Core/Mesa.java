/*
* Representa la mesa de juego, donde estarán todas las cartas.
* Tendrá dos partes diferenciadas:
* - la parte interior, donde inicialmente estarán colocadas las cartas correctamente para jugar al solitario
* - los montones exteriores, donde estarán colocadas las cartas por palo ordenadas de menor a mayor
* Estructura: Se utilizarán TADs adecuados para su respresentación. En concreto:
* - Una matriz de Pilas para representar la parte o montón interior 
* - Un array de Pilas para representar los montones exteriores.
* Funcionalidad: colocar las cartas para iniciar el juego, quitar una carta de la parte interior, colocar una carta en el interior,
* colocar una carta en el montón exterior correspondiente, visualizar cartas en la mesa, etc

La Pila es una estructura de datos que existe en Java y que se corresponde con la clase Stack. Por lo tanto debereis hacer uso de dicha
clase para representar la mesa de juego, y en particular de los métodos que se indican a continuación (de ser necesarios):

        public boolean empty();
        // Produce: Si la pila está vacía devuelve true, sino false.
        public Carta peek();
        // Produce: Devuelve la Carta del tope de la pila, sin eliminarla de ella.
        public Carta pop();
        // Produce: Elimina la Carta del tope de la pila y la devuelve.
        public void push(Carta item);
        // Produce: Introduce la Carta en el tope de la pila.
 */
package solitario.Core;

import java.util.Stack;

/**
 *
 * @author AEDI
 */
public class Mesa {

    private Stack<Carta>[][] montonInterior;
    private Stack<Carta>[] montonExterior;
    //Movimientos posibles
    private int movimientos;
    private final int DIM = 4;

    public Mesa() {                      //Constructor pilas vacias (Mesa vacia)

        this.movimientos = 0;

        this.montonInterior = new Stack[DIM][DIM];
        this.montonExterior = new Stack[DIM];

        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                montonInterior[i][j] = new Stack<Carta>();
            }
        }
        for (int k = 0; k < DIM; k++) {
            montonExterior[k] = new Stack<Carta>();
        }

        inicio();           //Despues de construir mesa vacia, llamamos a metodo para rellenarla
    }

    public void inicio() {              //Metodo que crea la baraja y rellena la mesa

        Baraja baraja = new Baraja();   //Creamos la baraja de la mesa

        for (int i = 0; i < DIM; i++) {               //Coloca las cartas iniciales
            for (int j = 0; j < DIM; j++) {
                Carta x = baraja.getCarta();    //Saca una carta de la baraja
                montonInterior[i][j].push(x);   //La pone en la posición de la matriz.pila
            }
        }

        for (int i = 0; i < DIM; i++) {           //Colocamos las cartas en la diagonal ([0,0] hasta [3,3])

            Carta a = baraja.getCarta();        //Saca una carta de la baraja
            montonInterior[i][i].push(a);       //La pone en la posición de la matriz.pila            
        }

        for (int i = 0; i < DIM; i++) {           //Colocamos las cartas en la diagonal ([0,3] hasta [3,0])

            Carta a = baraja.getCarta();        //Saca una carta de la baraja
            montonInterior[i][3 - i].push(a);     //La pone en la posición de la matriz.pila            
        }

        for (int i = 0; i < DIM; i++) {               //Coloca las cartas boca arriba
            for (int j = 0; j < DIM; j++) {
                Carta x = baraja.getCarta();    //Saca una carta de la baraja
                x.voltear();                    //Le da la vuelta a la carta antes de añadirla (Para poder ver cual es)
                montonInterior[i][j].push(x);   //La pone en la posición de la matriz.pila
            }
        }
    }

    public boolean hayMovimentos() {

        boolean toret = false;

        int mov = 0;

        //Comprobamos que no esté ninguna carta boca abajo
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; i < DIM; j++) {

                if (montonInterior[i][j].peek().getOculta()) {
                    movimientos++;

                }

            }

        }

        //Movimento del monton Interior al Interior 
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {

                Carta encima = montonInterior[i][j].peek();

                if (!encima.getOculta()) {

                    for (int x = 0; x < DIM; x++) {
                        for (int y = 0; y < DIM; y++) {

                            Carta debajo = montonInterior[x][y].peek();

                            if (!debajo.getOculta() && comprobarMayor(encima, debajo)) {
                                movimientos++;
                            }

                        }

                    }
                }
            }

        }

        //Movimento del monton Interior al Exterior
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {

                Carta encima = montonInterior[i][j].peek();

                for (int y = 0; y < DIM; y++) {

                    Carta debajo = montonExterior[y].peek();

                    if (comprobarMayor(debajo, encima)) {
                        movimientos++;
                    }

                }

            }

        }

        return movimientos > 0;
    }

    //La uno es la carta menor y dos es la mayor, también comprueba el Palo
    public boolean comprobarMayor(Carta uno, Carta dos) {

        int menor = uno.getNumero();
        int mayor = dos.getNumero();

        //Se comprueba si los Palos son iguales
        if (!uno.getPalos().equals(dos.getPalos())) {
            return false;
        }

        if (menor == 7 && mayor == 10) {
            return true;
        }

        return menor == mayor - 1;

    }

    public Stack<Carta>[][] getMontonInterior() {
        return montonInterior;
    }

    public Stack<Carta>[] getMontonExterior() {
        return montonExterior;
    }

    public void setMontonInterior(Stack<Carta>[][] montonInterior) {
        this.montonInterior = montonInterior;
    }

    public void setMontonExterior(Stack<Carta>[] montonExterior) {
        this.montonExterior = montonExterior;
    }

    public Carta sacarCarta(int i, int j) {  //Metodo que saca la primera carta de un monton

        return montonInterior[i][j].pop();
    }

    public void moverExterior(Carta c, int posicion) {        //Movemos la carta que se pasa como parametro al monton exterior en la posicion pasada como parametro

        montonExterior[posicion].push(c);

    }

    public void moverInterior(Carta c, int i, int j) {        //Movemos la carta que se pasa como parametro al monton interior en la posicion pasada como parametro

        montonInterior[i][j].push(c);
    }

    public void voltear(int i, int j) {

        montonInterior[i][j].peek().voltear();      //Da la vuelta a la primera carta del monton pasado como parametro

    }

    @Override
    public String toString() {
        StringBuilder toret = new StringBuilder();
        String secuencia = "\t     A\t\t     B\t\t     C\t\t     D\t\t\t\t\t     E";


        toret.append(secuencia).append("\n\n");

        for (int i = 0; i < DIM; i++) {

            toret.append(i + 1 + "\t");

            for (int j = 0; j < DIM; j++) {

                if (!montonInterior[i][j].empty()) {
                    toret.append(montonInterior[i][j].peek()).append("\t");
                } else {

                    toret.append("Ø\t");
                }

            }

            if (!montonExterior[i].empty()) {
                toret.append("\t\t\t" + montonExterior[i].peek());
            } else {

                toret.append("\t\t\t     " + "Ø");

            }
            toret.append("\n");
        }

        toret.append("\n\t\t\tMONTON INTERIOR \t\t\t\t\t\t\tMONTON EXTERIOR");

        return toret.toString();
    }
}
