package solitario.IU;

import static solitario.IU.Solitario.menuInicio;

/**
 *
 * @author AEDI
 */
public class Main {
    
    public static void main(String[] args) {
     
        try{
         
            menuInicio();
        }
     
        catch(Exception exc){
         
            System.err.println("Se ha producido un error inesperado.");
        }           
    }    
}


/*
   a b c d  e
 1 x x x x  x
 2 x x x x  x
 3 x x x x  x
 4 x x x x  x

 

¿Introduce posicion?

R: 1e

R: 3c

¿Que quieres hacer?

[A] Mover carta
[B] Voltear carta

¿A donde?

R: 3b

*/