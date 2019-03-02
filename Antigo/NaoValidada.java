import java.io.Serializable;

/**
 * Clase que representa a atividade economica NaoValidada
 * 
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class NaoValidada extends CodigoAtividadeEconomica implements Serializable
{
    /**
     * Construtor vazio da classe
     */
    public NaoValidada(){
        super();
    }

    /**
     * Metodo que devolve o valor a deduzir
     * 
     * @param  valor   Valor ao qual vai ser deduzido
     * 
     * @return  valor da deducao
     */
    public double valorDeducao (double valor){
        return valor;
    }
    
    /**
     * Metodo equals
     */
    public boolean equals (Object o){
        if (o == this) {return true;}
        if ((o == null) || (o.getClass() != this.getClass())) return false;
        NaoValidada a = (NaoValidada) o;
        return super.equals(a);
    }
    
    /**
     * Metodo clone
     */
    public NaoValidada clone(){
        return new NaoValidada();
    }
}
