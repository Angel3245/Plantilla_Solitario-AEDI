
package solitario.IU;

import static solitario.IU.Solitario.inicioPartida;

/**
 *
 * @author AEDI
 */
public class Main {
    
 public static void main(String[] args) { 
     try{
        inicioPartida();
     }
     catch(Exception exc){
        System.err.println("Se ha producido un error inesperado.");
    }
           
}

    
}
