import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

/**
 * Clase que representa as Despesas.
 * 
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class FaturaAtividadeCorrigida implements Serializable
{
    private int codigoFatura;
    Set<CodigoAtividadeEconomica> atividadeAnterior;
    Set<CodigoAtividadeEconomica> atividadeNova;

    /**
     * Construtor vazio da classe Despesa
     */
    public FaturaAtividadeCorrigida()
    {
        this.codigoFatura = 0;
        this.atividadeAnterior = new HashSet<CodigoAtividadeEconomica>();
        this.atividadeNova = new HashSet<CodigoAtividadeEconomica>();
    }

    /**
     * Construtor parametrizado da classe
     * 
     * @param  codigoFatura        Codigo da fatura
     * @param  atividadeAnterior   Atividade economica errada
     * @param  atividadeNova       Atividade economica certa
     */
    public FaturaAtividadeCorrigida (int codigoFatura, Set<CodigoAtividadeEconomica> atividadeAnterior, Set<CodigoAtividadeEconomica> atividadeNova){
        this.codigoFatura = codigoFatura;
        this.atividadeAnterior = atividadeAnterior;
        this.atividadeNova = atividadeNova;
    }

    /**
     * Construtor por copia
     * 
     * @param  d  Objeto da classe despesa que vai ser copiada
     */
    public FaturaAtividadeCorrigida (FaturaAtividadeCorrigida fac){
        this.codigoFatura = fac.getCodigoFatura();
        this.atividadeAnterior = fac.getAtividadeAnterior();
        this.atividadeNova = fac.getAtividadeNova();
    }

    /**
     * Metodo que devolve o codigo da fatura
     * 
     * @return  codigo da fatura
     */
    public int getCodigoFatura(){
        return this.codigoFatura;
    }

    /**
     * Metodo que devolve a atividade economica anterior
     * 
     * @return  atividade economica
     */
    public Set<CodigoAtividadeEconomica> getAtividadeAnterior(){
        return this.atividadeAnterior;
    }

    /**
     * Metodo que devolve a atividade economica correta
     * 
     * @return  atividade economica correta
     */
    public Set<CodigoAtividadeEconomica> getAtividadeNova(){
        return this.atividadeNova;
    }

    /**
     * Metodo equals da classe
     */
    public boolean equals (Object o){
        if (o==this) {return true;}
        if( o==null || o.getClass() != this.getClass()) {return false;}
        FaturaAtividadeCorrigida fac = (FaturaAtividadeCorrigida) o;
        return fac.getCodigoFatura()==(codigoFatura) &&
               fac.getAtividadeAnterior()==(atividadeAnterior) &&
               fac.getAtividadeNova()==(atividadeNova);
    }

    /**
     * Metodo clone da classe
     */
    public FaturaAtividadeCorrigida clone(){
        return new FaturaAtividadeCorrigida(this);
    }

    /**
     * Metodo toString da classe
     * 
     * @return  String com informacao do Individual
     */
    public String toString(){
        StringBuilder sb = new StringBuilder("Codigo da fatura: ");
        sb.append(this.getCodigoFatura());
        sb.append("; Atividade errada: ");
        for(CodigoAtividadeEconomica cae : this.getAtividadeAnterior()){
            sb.append(cae.toString());
        }
        sb.append("; Atividade correta: ");
        for(CodigoAtividadeEconomica cae : this.getAtividadeNova()){
            sb.append(cae.toString());
        }
        return sb.toString();
    }
}
