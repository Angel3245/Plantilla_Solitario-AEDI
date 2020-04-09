/*
 * Representa una carta, formada por un número y su palo correspondiente
 */
package solitario.Core;

/**
 *
 * @author AEDI
 */
public class Carta {
    
    private int numero;
    private Palos palo;
    private boolean oculta;
    
    public Carta(int num,Palos pal,boolean ocult){
    
        this.numero = num;
        this.palo = pal;        
        this.oculta = ocult;
    
    }    
    
    public int getNumero(){
        return numero;
    }
    
    public Palos getPalos(){
        return palo;
    }
    
    public boolean getOculta(){
        return oculta;
    }
    
    public void setOculta(boolean ocult){
        this.oculta = ocult;
    }
    
    //Cambia el estado de la carta a su contrario
    public void voltear(){
        this.oculta = !this.oculta;
    }
    
    @Override
    public String toString(){
        StringBuilder toret = new StringBuilder();
    
        toret.append("[");
        
        if(oculta == true){
            
            toret.append("    X    ");
        
        }else{
        
            switch(numero){
                case 10: toret.append("SOTA");
                break;
                case 11: toret.append("CBLL");
                break;
                case 12: toret.append("REY");
                break;
                default: toret.append(numero);
                break;
            
            }
            
            toret.append(" " + palo);
        }
        
        toret.append("]");
                
        return toret.toString();
    }
}