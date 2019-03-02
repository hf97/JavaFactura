import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

/**
 * Clase que representa as Faturas.
 *
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class Fatura implements Serializable
{
    private int codigo;
    private int emitenteNIF;
    private String designacaoEmitente;
    private LocalDate dataFatura;
    private int clienteNIF;
    private String descricaoFatura;
    private AtividadeEconomica atividadeEconomica;
    private double valorFatura;

    /**
     * Construtor vazio da classe Fatura
     */
    public Fatura () {
        this.codigo = 0;
        this.emitenteNIF = 0;
        this.designacaoEmitente = "";
        this.dataFatura = LocalDate.now();
        this.clienteNIF = 0;
        this.descricaoFatura = "";
        this.atividadeEconomica = new NaoValidada();
        this.valorFatura = 0;
    }

    /**
     * Construtor parametrizado da classe
     *
     * @param  codigo               codigo da fatura
     * @param  emitenteNIF          NIF do emitente
     * @param  designacaoEmitente   Designacao do emitente
     * @param  dataFatura          Data da Fatura
     * @param  clienteNIF           NIF do cliente
     * @param  descricaoFatura     Descricao da Fatura
     * @param  atividadeEconomica      Atividade a que a Fatura diz respeito
     * @param  valorFatura         Valor da Fatura
     */
    public Fatura (int codigo, int emitenteNIF, String designacaoEmitente, LocalDate dataFatura, int clienteNIF, String descricaoFatura, AtividadeEconomica atividadeEconomica, double valorFatura){
        this.codigo = codigo;
        this.emitenteNIF = emitenteNIF;
        this.designacaoEmitente = designacaoEmitente;
        this.dataFatura = dataFatura;
        this.clienteNIF = clienteNIF;
        this.descricaoFatura = descricaoFatura;
        this.atividadeEconomica = atividadeEconomica;
        this.valorFatura = valorFatura;
    }

    /**
     * Construtor por copia
     *
     * @param  d  Objeto da classe Fatura que vai ser copiada
     */
    public Fatura (Fatura d){
        this.codigo = d.getCodigo();
        this.emitenteNIF = d.getEmitenteNIF();
        this.designacaoEmitente = d.getDesignacaoEmitente();
        this.dataFatura = d.getDataFatura();
        this.clienteNIF = d.getClienteNIF();
        this.descricaoFatura = d.getDescricaoFatura();
        this.atividadeEconomica = d.getAtividadeEconomica();
        this.valorFatura = d.getValorFatura();
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
     * Metodo que devolve o NIF do emitente
     *
     * @return  NIF do emitente
     */
    public int getEmitenteNIF(){
        return this.emitenteNIF;
    }

    /**
     * Metodo que devolve a designacao do emitente
     *
     * @return  designacao do emitente
     */
    public String getDesignacaoEmitente(){
        return this.designacaoEmitente;
    }

    /**
     * Metodo que devolve a data da Fatura
     *
     * @return  data da Fatura
     */
    public LocalDate getDataFatura(){
        return this.dataFatura;
    }

    /**
     * Metodo que devolve o NIF do cliente
     *
     * @return  NIF do cliente
     */
    public int getClienteNIF(){
        return this.clienteNIF;
    }

    /**
     * Metodo que devolve a descricao da Fatura
     *
     * @return  descricao da Fatura
     */
    public String getDescricaoFatura(){
        return this.descricaoFatura;
    }

    /**
     * Metodo que devolve a natureza da Fatura (atividade economica a que diz respeito)
     *
     * @return  natureza da Fatura
     */
    public AtividadeEconomica getAtividadeEconomica(){
        return this.atividadeEconomica;
    }

    /**
     * Metod que devolve o valor da Fatura
     *
     * @return  valor da Fatura
     */
    public double getValorFatura(){
        return this.valorFatura;
    }

    /**
     * Metodo que define o NIF do emitente
     *
     * @param  NIF   NIF do emitente
     */
    public void setEmitenteNIF(int NIF){
        this.emitenteNIF = NIF;
    }

    /**
     * Metodo que define a designacao do emitente
     *
     * @param  designacaoEmitente
     */
    public void setDesignacaoEmitente(String designacaoEmitente){
        this.designacaoEmitente = designacaoEmitente;
    }

    /**
     * Metodo que define a data da Fatura
     *
     * @param  dataFatura   Data da Fatura
     */
    public void setDataFatura(LocalDate dataFatura){
        this.dataFatura = dataFatura;
    }

    /**
     * Metodo que define o NIF do cliente
     *
     * @param  clienteNIF   NIF do cliente
     */
    public void setClienteNIF(int clienteNIF){
        this.clienteNIF = clienteNIF;
    }

    /**
     * Metodo que define a descricao da Fatura
     *
     * @param  descricaoFatura   Descricao da Fatura
     */
    public void setDescricaoFatura(String descricaoFatura){
        this.descricaoFatura = descricaoFatura;
    }

    /**
     * Metodo que define a natureza da Fatura (atividade economica a que diz respeito)
     *
     * @param  atividadeEconomica   Natureza da Fatura
     */
    public void setAtividadeEconomica(AtividadeEconomica atividadeEconomica){
        this.atividadeEconomica = atividadeEconomica;
    }

    /**
     * Metodo que define o valor da Fatura
     *
     * @param  valorFatura   Valor da Fatura
     */
    public void setValorFatura(double valorFatura){
        this.valorFatura = valorFatura;
    }

    /**
     * Metodo equals da classe
     *
     * @return  true se os objetos sao iguais, false se sao diferentes
     */
    public boolean equals (Object o){
        if (o==this) {return true;}
        if(o==null || o.getClass() != this.getClass()) {return false;}
        Fatura d = (Fatura) o;
        return  emitenteNIF == (d.getEmitenteNIF()) &&
                designacaoEmitente.equals(d.getDesignacaoEmitente()) &&
                dataFatura.equals(d.getDataFatura()) &&
                clienteNIF == (d.getClienteNIF())&&
                descricaoFatura.equals(d.getDescricaoFatura()) &&
                atividadeEconomica.equals(d.getAtividadeEconomica()) &&
                valorFatura == (d.getValorFatura());
    }

    /**
     * Metodo clone da classe Fatura
     *
     * @return  Clone do objeto Fatura
     */
    public Fatura clone(){
        return new Fatura(this);
    }

    /**
     * Metodo toString da classe Individual
     *
     * @return  String com informacao do Individual
     */
    public String toString(){
        StringBuilder sb = new StringBuilder("Codigo da fatura: ");
        sb.append(this.getCodigo());
        sb.append("; NIF do emitente: ");
        sb.append(this.getEmitenteNIF());
        sb.append("; Designacao do emitente: ");
        sb.append(this.getDesignacaoEmitente());
        sb.append("; Data da Fatura: ");
        sb.append(this.getDataFatura());
        sb.append("; NIF do cliente: ");
        sb.append(this.getClienteNIF());
        sb.append("; Descricao da Fatura: ");
        sb.append(this.getDescricaoFatura());
        sb.append("; Natureza da Fatura: ");
        sb.append(this.getAtividadeEconomica().toString());
        sb.append("; Valor da Fatura: ");
        sb.append(this.getValorFatura());
        return sb.toString();
    }
}
