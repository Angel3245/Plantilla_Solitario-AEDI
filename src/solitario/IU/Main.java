package solitario.IU;

import static solitario.IU.Solitario.menuInicio;

/** El main del programa.
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