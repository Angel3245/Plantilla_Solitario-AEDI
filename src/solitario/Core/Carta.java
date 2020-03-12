/*
 * Representa una carta, formada por un número y su palo correspondiente
 */
package solitario.Core;

/**
 *
 * @author AEDI
 */
public class Carta {
		
    //Atributos de las cartas
    
    private int numero;
    private Palos palo;
    
    /*
    Atributo para controlar si la carta está va arriba o abajo
    puede ser innecesario    
    */
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
    
    @Override
    public String toString(){
        StringBuilder toret = new StringBuilder();
    
        toret.append("[");
        if(oculta == true){
            toret.append("X");
        
        }else{
        
            toret.append(numero);
            toret.append(" " + palo);
        
        }
        
        toret.append("]");
        
        
        return toret.toString();
    }
}
