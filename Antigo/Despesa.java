import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

/**
 * Clase que representa as Despesas.
 * 
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class Despesa implements Serializable 
{
    private int codigo;
    private int emitenteNIF;
    private String designacaoEmitente;
    private LocalDate dataDespesa;
    private int clienteNIF;
    private String descricaoDespesa;
    private Set<CodigoAtividadeEconomica> atividadeEconomica;
    private double valorDespesa;
    
    /**
     * Construtor vazio da classe Despesa
     */
    public Despesa () {
        this.codigo = 0;
        this.emitenteNIF = 0;
        this.designacaoEmitente = "";
        this.dataDespesa = LocalDate.now();
        this.clienteNIF = 0;
        this.descricaoDespesa = "";
        this.atividadeEconomica = new HashSet<CodigoAtividadeEconomica>();
        this.valorDespesa = 0;
    }
    
    /**
     * Construtor parametrizado da classe
     * 
     * @param  codigo               codigo da fatura
     * @param  emitenteNIF          NIF do emitente
     * @param  designacaoEmitente   Designacao do emitente
     * @param  dataDespesa          Data da despesa
     * @param  clienteNIF           NIF do cliente
     * @param  descricaoDespesa     Descricao da despesa
     * @param  atividadeEconomica      Atividade a que a Despesa diz respeito
     * @param  valorDespesa         Valor da despesa
     */
    public Despesa (int codigo, int emitenteNIF, String designacaoEmitente, LocalDate dataDespesa, int clienteNIF, String descricaoDespesa, Set<CodigoAtividadeEconomica> atividadeEconomica, double valorDespesa){
        this.codigo=codigo;
        this.emitenteNIF=emitenteNIF;
        this.designacaoEmitente=designacaoEmitente;
        this.dataDespesa=dataDespesa;
        this.clienteNIF=clienteNIF;
        this.descricaoDespesa=descricaoDespesa;
        this.atividadeEconomica=atividadeEconomica;
        this.valorDespesa=valorDespesa;
    }
    
    /**
     * Construtor por copia
     * 
     * @param  d  Objeto da classe despesa que vai ser copiada
     */
    public Despesa (Despesa d){
        this.codigo=d.getCodigo();
        this.emitenteNIF=d.getEmitenteNIF();
        this.designacaoEmitente=d.getDesignacaoEmitente();
        this.dataDespesa=d.getDataDespesa();
        this.clienteNIF=d.getClienteNIF();
        this.descricaoDespesa=d.getDescricaoDespesa();
        this.atividadeEconomica=d.getAtividadeEconomica();
        this.valorDespesa=d.getValorDespesa();
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
     * Metodo que devolve a data da despesa
     * 
     * @return  data da despesa
     */
    public LocalDate getDataDespesa(){
        return this.dataDespesa;
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
     * Metodo que devolve a descricao da despesa
     * 
     * @return  descricao da despesa
     */
    public String getDescricaoDespesa(){
        return this.descricaoDespesa;
    }
    
    /**
     * Metodo que devolve a natureza da despesa (atividade economica a que diz respeito)
     * 
     * @return  natureza da despesa
     */
    public Set<CodigoAtividadeEconomica> getAtividadeEconomica(){
        return this.atividadeEconomica;
    }
    
    /**
     * Metod que devolve o valor da despesa
     * 
     * @return  valor da despesa
     */
    public double getValorDespesa(){
        return this.valorDespesa;
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
     * Metodo que define a data da despesa
     * 
     * @param  dataDespesa   Data da despesa
     */
    public void setDataDespesa(LocalDate dataDespesa){
        this.dataDespesa = dataDespesa;
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
     * Metodo que define a descricao da despesa
     * 
     * @param  descricaoDespesa   Descricao da despesa
     */
    public void setDescricaoDespesa(String descricaoDespesa){
        this.descricaoDespesa = descricaoDespesa;
    }
    
    /**
     * Metodo que define a natureza da despesa (atividade economica a que diz respeito)
     * 
     * @param  atividadeEconomica   Natureza da despesa
     */
    public void setAtividadeEconomica(Set<CodigoAtividadeEconomica> atividadeEconomica){
        this.atividadeEconomica = atividadeEconomica;
    }
    
    /**
     * Metodo que define o valor da despesa
     * 
     * @param  valorDespesa   Valor da despesa
     */
    public void setValorDespesa(double valorDespesa){
        this.valorDespesa = valorDespesa;
    }

    /**
     * Metodo equals da classe
     * 
     * @return  true se os objetos sao iguais, false se sao diferentes
     */
    public boolean equals (Object o){
        if (o==this) {return true;}
        if(o==null || o.getClass() != this.getClass()) {return false;}
        Despesa d = (Despesa) o;
        return d.getEmitenteNIF()==(emitenteNIF) &&
               d.getDesignacaoEmitente()==(designacaoEmitente) &&
               d.getDataDespesa()==(dataDespesa) &&
               d.getClienteNIF()==(clienteNIF)&&
               d.getDescricaoDespesa()==(descricaoDespesa) &&            
               d.getAtividadeEconomica()==(atividadeEconomica) &&
               d.getValorDespesa()==(valorDespesa);
    }
    
    /**
     * Metodo clone da classe Despesa
     * 
     * @return  Clone do objeto Despesa
     */
    public Despesa clone(){
        return new Despesa(this);
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
        sb.append("; Data da despesa: ");
        sb.append(this.getDataDespesa());
        sb.append("; NIF do cliente: ");
        sb.append(this.getClienteNIF());
        sb.append("; Descricao da despesa: ");
        sb.append(this.getDescricaoDespesa());
        sb.append("; Natureza da despesa: ");
        for (CodigoAtividadeEconomica cod : this.getAtividadeEconomica()){
            sb.append("  "+cod.getClass().getName());
        }
        sb.append("; Valor da Despesa: ");
        sb.append(this.getValorDespesa());
        return sb.toString();
    }
}
