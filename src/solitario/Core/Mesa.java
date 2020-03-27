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
    
   private Stack<Carta> [][] montonInterior;
    private Stack<Carta> [] montonExterior;

    public Mesa() {                                 //Constructor pilas vacias
        for(int i=0 ; i<4 ; i++){
            for(int j=0 ; j<4 ; j++){
                montonInterior[i][j]=null;
            }
        }
        for(int k=0; k<4 ; k++){
            montonExterior=null;
        }
    }

    public Stack<Carta>[][] inicio(Baraja baraja){              //metmos las cartas en las pilas desde baraja-podrias ser el contructor??
        
        for(int k=0 ; k<2 ; k++){                   //las dos primeras capas de la matriz
            for(int i=0 ; i<4 ; i++){
                for(int j=0 ; j<4 ; j++){
                    Carta x = baraja.getCarta();              // teoricamente aqui coje quita la ultima carta de la baraja(entiendo que barajada)* 
                    montonInterior[i][j].push(x);  // *y la pone en la posición de la matriz.pila
               
                }
            }
        }
        Carta a = baraja.getCarta();                     //estasson las posiciones restantes de las diagonales*
        montonInterior[0][0].push(a);         //* que no se me ocurre como ponerlas de mejor manera
        Carta b = baraja.getCarta();
        montonInterior[0][3].push(b);
        Carta c = baraja.getCarta();
        montonInterior[1][1].push(c);
        Carta d = baraja.getCarta();
        montonInterior[1][2].push(d);
        Carta e = baraja.getCarta();
        montonInterior[2][1].push(e);
        Carta f = baraja.getCarta();
        montonInterior[2][2].push(f);
        Carta g = baraja.getCarta();
        montonInterior[3][0].push(g);
        Carta h = baraja.getCarta();
        montonInterior[3][3].push(h);
        
        return montonInterior;
    }

    public Stack<Carta>[][] getMontonInterior() {
        return montonInterior;
    }                                                //tal vez que los setter y getter no hacen falta

    public Stack<Carta>[] getMontonExterior() {
        return montonExterior;
    }

    public void setMontonInterior(Stack<Carta>[][] montonInterior) {
        this.montonInterior = montonInterior;
    }

    public void setMontonExterior(Stack<Carta>[] montonExterior) {
        this.montonExterior = montonExterior;
    }
    
    public void movInterior(){              //movimientos solo en de monton interno a monton interno
                                            // pido posicion o pido num de carta y palo????
    }
    
    public void movIntToExt(){              //movimientos del monton interior al exterior/misma pregunta que antes
        
    }

    
    public String toString() {
        StringBuilder toret= new StringBuilder();
        toret.append("Montón Interior:\n");
        for(int i=0 ; i<4 ; i++){
        	for(int j=0 ; j<4 ; j++){
                	toret.append(montonInterior[i][j]).append("\t");
            	}
            	toret.append("\n");
        }
        
        toret.append("\nMontón Exterior:\n");
        for(int k=0 ; k<4 ; k++){
                toret.append(montonExterior[k]).append("\t");       
        }
        return toret.toString();
    }
    
    
   
    
}
