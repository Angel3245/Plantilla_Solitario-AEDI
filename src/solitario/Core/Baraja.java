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
                if(i == 8){
                 i = 10;
                }    
                    
                Carta c = new Carta(i,p,false);
                
                baraja.add(c);
                //Muestro todas las cartas para comprobar su numero
                
                }
                
            }
            //Baraja la baraja
            barajar();
                    
        }
        
        //Método para barajar
        public void barajar(){
        
        //Baraja el arraylist baraja y le paso un objeto aleatorio
        Collections.shuffle(this.baraja,new Random());
        
        //La barajo 4 veces más (esta parte va al gusto)
        Collections.shuffle(this.baraja,new Random());
        Collections.shuffle(this.baraja,new Random());
        Collections.shuffle(this.baraja,new Random());
        Collections.shuffle(this.baraja,new Random());
        
        
        
        
        
        }
        
        
        //Saca la carta de la parte superior
        public Carta getCarta(){
        
            Carta c = baraja.get(0);
            baraja.remove(0);
        
            return c;
        }
        
        
        
        
        //METODOS QUE PUEDE QUE SEAN ELIMINADOS EN LA VERSIÓN FINAL
        
        //Devuelve la cantidad de cartas que ACTUALMENTE quedan en la baraja
        //IMPORTANTE cuidado al utilizar este método en bucles  
        public int size(){
        
            return baraja.size();
            
        }
        
        //Muestra toda la baraja
        @Override
        public String toString(){
        
        StringBuilder toret = new StringBuilder();
            for(int i = 0 ; i<baraja.size();i++){
                toret.append(baraja.get(i) + "\n");
            }
        return toret.toString();
        }
	
        
	
	
	
}
