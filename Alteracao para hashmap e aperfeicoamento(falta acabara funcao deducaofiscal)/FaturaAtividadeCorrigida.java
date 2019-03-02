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
    private int codigo;
    private AtividadeEconomica atividadeAnterior;
    private AtividadeEconomica atividadeNova;

    /**
     * Construtor vazio da classe Despesa
     */
    public FaturaAtividadeCorrigida()
    {
        this.codigo = 0;
        this.atividadeAnterior = new NaoValidada();
        this.atividadeNova = new NaoValidada();
    }

    /**
     * Construtor parametrizado da classe
     *
     * @param  codigo        Codigo da fatura
     * @param  atividadeAnterior   Atividade economica errada
     * @param  atividadeNova       Atividade economica certa
     */
    public FaturaAtividadeCorrigida (int codigo, AtividadeEconomica atividadeAnterior, AtividadeEconomica atividadeNova){
        this.codigo = codigo;
        this.atividadeAnterior = atividadeAnterior;
        this.atividadeNova = atividadeNova;
    }

    /**
     * Construtor por copia
     *
     * @param  fac  Objeto da classe despesa que vai ser copiada
     */
    public FaturaAtividadeCorrigida (FaturaAtividadeCorrigida fac){
        this.codigo = fac.getCodigo();
        this.atividadeAnterior = fac.getAtividadeAnterior();
        this.atividadeNova = fac.getAtividadeNova();
    }

    /**
     * Metodo que devolve o codigo da fatura
     *
     * @return  codigo da fatura
     */
    public int getCodigo(){
        return this.codigo;
    }

    /**
     * Metodo que devolve a atividade economica anterior
     *
     * @return  atividade economica
     */
    public AtividadeEconomica getAtividadeAnterior(){
        return this.atividadeAnterior;
    }

    /**
     * Metodo que devolve a atividade economica correta
     *
     * @return  atividade economica correta
     */
    public AtividadeEconomica getAtividadeNova(){
        return this.atividadeNova;
    }

    /**
     * Metodo clone da classe
     */
    public FaturaAtividadeCorrigida clone(){
        return new FaturaAtividadeCorrigida(this);
    }

    /**
     * Metodo equals
     */
    public boolean equals (Object o){
        if (o==this) {return true;}
        if( o==null || o.getClass() != this.getClass()) {return false;}
        FaturaAtividadeCorrigida fac = (FaturaAtividadeCorrigida) o;
        return  codigo == fac.getCodigo() &&
                atividadeAnterior.equals(fac.getAtividadeAnterior()) &&
                atividadeNova.equals(fac.getAtividadeNova());
    }

    /**
     * Metodo toString da classe
     *
     * @return  String com informacao do Individual
     */
    public String toString(){
        StringBuilder sb = new StringBuilder("Codigo da fatura: ");
        sb.append(this.getCodigo());
        sb.append("; Atividade anterior: ");
        sb.append(this.getAtividadeAnterior().toString());
        sb.append("; Atividade correta: ");
        sb.append(this.getAtividadeNova().toString());
        return sb.toString();
    }
}
