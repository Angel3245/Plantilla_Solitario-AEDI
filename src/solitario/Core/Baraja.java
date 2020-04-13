/*
* Representa la baraja española, 40 cartas, 4 palos, valores de las cartas de 1 a 12 (excepto 8 y 9). 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: estando la baraja desordenada, devolverá la carta situada encima del montón de cartas
 */
package solitario.Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Baraja {
	
    List<Carta> baraja;

    public Baraja(){
        
        //Se recorren los distintos palos
        this.baraja = new ArrayList<Carta>();

        for(Palos p: Palos.values()){
            
            for(int i=1;i<13;i++){
                
                //If con el que me salto los numeros 8 y 9
                if(i == 8) i = 10;
                
                //Creamos cada una de las cartas boca abajo    
                Carta c = new Carta(i,p,true);
                
                //Se añade la carta al array
                baraja.add(c);
            }

        }
        
        //Baraja las cartas
        barajar();

    }

  
    /**
     * Método para barajar las cartas
     * 
     */
    public void barajar(){

    //Baraja el arraylist baraja y le pasamos un objeto aleatorio
    Collections.shuffle(this.baraja,new Random());

    //La barajo 4 veces más (esta parte va al gusto)
    Collections.shuffle(this.baraja,new Random());
    Collections.shuffle(this.baraja,new Random());
    Collections.shuffle(this.baraja,new Random());
    Collections.shuffle(this.baraja,new Random());
    
    }
    
    
    /**
     * Saca la carta de la parte superior
     *
     * @return la carta de la parte superior, como Carta
     */
    public Carta getCarta(){

        Carta c = baraja.get(baraja.size()-1);
        baraja.remove(baraja.size()-1);

        return c;
    }
    
    //METODOS QUE PUEDE QUE SEAN ELIMINADOS EN LA VERSIÓN FINAL

    //Devuelve la cantidad de cartas que ACTUALMENTE quedan en la baraja
    //IMPORTANTE cuidado al utilizar este método en bucles  
    /**
     * Devuelve el númeoro de caratas de la baraja
     *
     * @return el número de cartas de la baraja, como int
     */
    public int size(){

        return baraja.size();

    }

    /**
     * Devuelve toda la baraja
     * @return una baraja, como String
     */
    @Override
    public String toString(){

    StringBuilder toret = new StringBuilder();
        for(int i = 0 ; i<baraja.size();i++){
            toret.append(baraja.get(i) + "\n");
        }
    return toret.toString();
    }
    
}