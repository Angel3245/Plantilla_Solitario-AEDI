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
    
    /**
     * Devuelve el número de la carta
     *
     * @return numero el número de la carta, como int
     */
    public int getNumero(){
        return numero;
    }
    
    /**
     * Devuelve el palo de la carta
     *
     * @return palo el palo de la carta, como Palos
     */
    public Palos getPalos(){
        return palo;
    }
    
    /**
     * Indica si la carta está oculta o no
     *
     * @return true si la carta está oculta o false en caso contrario
     */
    public boolean getOculta(){
        return oculta;
    }
    
    /**
     * Cambia el estado de la carta entre oculta y visible
     *@param ocult indica el estado anterior de la carta
     * 
     */
    public void setOculta(boolean ocult){
        this.oculta = ocult;
    }
    
    /**
     * Cambia el estado de la carta entre oculta y visible
     * 
     */
    public void voltear(){
        this.oculta = !this.oculta;
    }
    
    /**
     * Devuelve una carta con su número y palo
     * @return una carta, como String
     */
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