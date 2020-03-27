/*
 * Representa al único jugador de la partida, identificado por el nombre 
 * Funcionalidad: le da la vuelta a una carta que está boca abajo, escoge una carta para moverla o al montón de descarte
 *                o la mueve encima de otra carta del interior
 */
package solitario.Core;

/**
 *
 * @author AEDI
 */
public class Jugador {
    
    private String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }       
    
    // Este metodo retorna "vacia" si la pila esta vacia y "ocualta" si la carta de arriba esta boca abajo
    public String comprobarPosicion(int i, int j, Mesa mesa){
    
        if(mesa.getMontonInterior()[i][j].empty()) return "vacia";                  //Comprobamos si esta vacia
        if(mesa.getMontonInterior()[i][j].peek().getOculta()) return "oculta";      //Comprobamos si la carta de arriba esta boca abajo
        return "0";                                                                 //Si la carta no esta boca abajo y la pila no esta vacia retorna 0
        
    }
    
    public boolean voltear(int i, int j, Mesa mesa){    //Retorna un booleano en funcion de si voltea la carta o no
    
       if (mesa.getMontonInterior()[i][j].peek().getOculta()){      //Si la carta de arriba del monton esta boca abajo o no 
       
           mesa.voltear(i, j);      //Si esta boca abajo, le damos la vuelta
           return true;
           
       }else{
       
           return false;            //Si no esta boca abajo no hacemos nada
       }
    }
    
    public boolean mover(int iOrigen, int jOrigen, int iDestino, int jDestino, Mesa mesa){
    
        Carta c = mesa.getMontonInterior()[iOrigen][jOrigen].pop();     //Asignamos la carta al objeto c (Desde la posicion introducida por parametros)
        
        if(jDestino == 5){      //Si jDestino es un 5 sabemos que el destino es el monton exterior; Si es 1,2,3,4 es el monton interior
        
           if(mesa.getMontonExterior()[iDestino].empty()){
           
               if(c.getNumero() == 1){
               
                   mesa.moverExterior(c, iDestino);
                   return true;
               }
          // }else if(){ EN CONSTRUCCION: Comprobar que la carta que sea mayor (Justo la siguiente) de la ultima en el monton
           
               
           }
            
        }
        return false;
    }
}