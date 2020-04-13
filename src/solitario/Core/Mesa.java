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

    private final int DIM = 4;

    private Stack<Carta>[][] montonInterior;
    private Stack<Carta>[] montonExterior;
    //Movimientos posibles
    private int numMovimientos;
    private StringBuilder movimientos;

    public Mesa() {                      //Constructor pilas vacias (Mesa vacia)

        numMovimientos = 0;

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

    
    /**
     * Crea la baraja y rellena la mesa colocando 16 catas boca abajo,
     * luego en las diagonales y luego otras 16 boca arriba 
     * 
     */
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

    /**
     * Comprueba si hay movimientos posibles contándolos y creando el String movimientos
     * @return true si hay movimientos posibles y false en caso contrario
     * 
     */
    public boolean hayMovimentos() {

        movimientos = new StringBuilder();
        numMovimientos = 0;

        //Comprobamos que no esté ninguna carta boca abajo
        /*for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (!montonInterior[i][j].empty() && montonInterior[i][j].peek().getOculta()) {
                    numMovimientos++;

                }

            }

        }*/
        //Movimento del monton Interior al Interior 
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (!montonInteriorVacio(i, j)) {
                    Carta encima = mirarCartaMontonInterior(i, j);

                    //if (!encima.getOculta()) {
                    for (int x = 0; x < DIM; x++) {
                        for (int y = 0; y < DIM; y++) {
                            if (!montonInteriorVacio(x, y)) {
                                Carta debajo = mirarCartaMontonInterior(x, y);

                                if (/*!debajo.getOculta() && */comprobarMayor(encima, debajo)) {
                                    movimientos.append("\nInterior: " + encima.toString() + " -> " + debajo.toString());
                                    numMovimientos++;
                                }
                            }
                        }

                    }
                    // }
                }
            }

        }

        //Movimento del monton Interior al Exterior
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (!montonInteriorVacio(i, j)) {
                    Carta encima = mirarCartaMontonInterior(i, j);

                    //if (!encima.getOculta()) {
                    if (encima.getNumero() == 1) {
                        movimientos.append("\nExterior: " + encima.toString());
                        numMovimientos++;
                    } else {
                        for (int y = 0; y < DIM; y++) {
                            if (!montonExteriorVacio(y)) {
                                Carta debajo = mirarCartaMontonExterior(y);

                                if (comprobarMayor(debajo, encima)) {
                                    movimientos.append("\nExterior: " + encima.toString() + " -> " + debajo.toString());
                                    numMovimientos++;
                                }
                            }

                        }
                    }
                    //}
                }
            }

        }

        return numMovimientos > 0;
    }

    /**
     * Comprueba si la Carta uno es la menor, la Carta dos la mayor y 
     * si son ambas del mismo palo
     * @param uno la Carta menor
     * @param dos la Carta mayor 
     * @return true si la comprobación es correcta y false en caso contrario
     */
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
    
    
    /**
     * Devuelve el número de posibles movimientos de la mesa
     *
     * @return el número de movimientos posibles (numMovimientos) como int
     */
    public int getNumMovimientos() { //Método para el número de movimientos posibles de la mesa
        return numMovimientos;
    }
    
    
    /**
     * Devuelve los posibles posibles movimientos de la mesa
     *
     * @return los posibles movimientos de la mesa (movimientoa) como String
     */
    public String getMovimientos() {  //Método que devuelve los movimientos que se pueden realizar

        return movimientos.toString();
    }

    
    /**
     * Devuelve una carta sacada del montón interior
     *
     * @return la carta del tope de la pila montonInterior, como Carta
     */
    public Carta sacarCartaMontonInterior(int iOrigen, int jOrigen) { //Metodo que saca la primera carta de un monton de la parte interior

        Carta c = montonInterior[iOrigen][jOrigen].pop();

        return c;
    }

    /**
     * Devuelve la carta del tope del montón interior sin sacarla de la pila
     * @param i la posición (fila) del montón interior 
     * @param j la posición (columna) del montón interior
     * @return la carta del tope de la pila montonInterior, como Carta
     */
    public Carta mirarCartaMontonInterior(int i, int j) { 
        Carta c = montonInterior[i][j].peek();
        return c;
    }

    /**
     * Devuelve la carta del tope del montón exterior sin sacarla de la pila
     * @param i la posición del montón exterior 
     * @return la carta del tope de la pila montonExterior, como Carta
     */
    public Carta mirarCartaMontonExterior(int i) {  
        Carta c = montonExterior[i].peek();
        return c;
    }

    /**
     * Comprueba si un montón de la parte exterior está vacío
     * @param i la posición del montón exterior 
     * @return true si el montón exterior está vacío y false en caso contrario
     */
    public boolean montonExteriorVacio(int i) {  
        return (montonExterior[i].empty());
    }

    /**
     * Comprueba si un montón de la parte interior está vacío
     * @param i la posición (fila) del montón interior 
     * @param j la posición (columna) del montón interior
     * @return true si el montón interior está vacío y false en caso contrario
     */
    public boolean montonInteriorVacio(int i, int j) {  
        return (montonInterior[i][j].empty());
    }

    /**
     * Mueve una carta a un montón exterior
     * @param c Carta que se mueve a un monton exterior
     * @param posicion del montón exterior al que se mueve la carta
     */
    public void moverExterior(Carta c, int posicion) {       

        montonExterior[posicion].push(c);

    }

     /**
     * Mueve una carta a un montón interior
     * @param c Carta que se mueve a un monton interior
     * @param i la posición (fila) del montón interior 
     * @param j la posición (columna) del montón interior
     */
    public void moverInterior(Carta c, int i, int j) {        
        montonInterior[i][j].push(c);
    }

    /**
     * Voltea la carta del tope de un montón de la parte interior
     * @param i la posición (fila) del montón interior 
     * @param j la posición (columna) del montón interior
     */
    public void voltear(int i, int j) { 

        if (comprobarVolteo(i, j)) {
            montonInterior[i][j].peek().voltear();
        }
    }

    /**
     * Comprueba si una carta puede ser volteada, si el montón inerior donde se 
     * situa no esta vacío y la carta está oculta
     * @param i la posición (fila) del montón interior 
     * @param j la posición (columna) del montón interior
     */
    private boolean comprobarVolteo(int i, int j) { //Comprueba si una carta debe ser volteada
        return (!montonInteriorVacio(i, j) && mirarCartaMontonInterior(i, j).getOculta());
    }

    /**
     * Comprueba que todos los montones de la parte exterior tienen 10 cartas, 
     * situación en la cual se gana la partida
     * 
     */
    public boolean ganador() {

        for (int i = 0; i < DIM; i++) {
            if (montonExterior[i].size() != 10) {
                return false;
            }
        }

        return true;
    }
    
    
    /**
     * Devuelve la mesa de cartas 
     * @return la mesa de cartas, como String
     */
    @Override
    public String toString() {

        StringBuilder toret = new StringBuilder();
        String secuencia = "\t     A\t\t     B\t\t     C\t\t     D\t\t\t\t\t     E";

        toret.append("\n");
        toret.append(secuencia).append("\n\n");

        for (int i = 0; i < DIM; i++) {

            toret.append(i + 1 + "\t");

            for (int j = 0; j < DIM; j++) {

                if (!montonInterior[i][j].empty()) {
                    toret.append(montonInterior[i][j].peek()).append("\t");
                } else {

                    toret.append("     Ø\t\t");
                }

            }

            if (!montonExterior[i].empty()) {
                toret.append("\t\t\t" + montonExterior[i].peek());
            } else {

                toret.append("\t\t\t     " + "Ø");

            }
            toret.append("\n");
        }

        toret.append("\n\t\t\tMONTÓN INTERIOR \t\t\t\t\t\t\tMONTÓN EXTERIOR");
        toret.append("\n");

        return toret.toString();
    }
}
