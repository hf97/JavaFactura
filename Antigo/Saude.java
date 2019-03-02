import java.io.Serializable;

/**
 * Clase que representa a atividade economica Saudes
 * 
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class Saude extends CodigoAtividadeEconomica implements Serializable
{
    /**
     * Construtor vazio da classe
     */
    public Saude(){
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
        return valor * 0.1;
    }
    
    /**
     * Metodo equals
     */
    public boolean equals (Object o){
        if (o == this) {return true;}
        if ((o == null) || (o.getClass() != this.getClass())) return false;
        Saude a = (Saude) o;
        return super.equals(a);
    }
    
    /**
     * Metodo clone
     */
    public Saude clone(){
        return new Saude();
    }
}
