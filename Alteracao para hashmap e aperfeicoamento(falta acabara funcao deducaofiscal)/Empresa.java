import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

/**
 * Clase que representa as Empresas que usam a plataforma
 *
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class Empresa extends Utilizador implements Serializable {
    private int codigoEmpresa;
    private Set<AtividadeEconomica> atividadeEconomica;
    private double fatorDeducaoFiscal;
    private int numeroFaturas;

    /**
     * Construtor vazio da Classe Empresa
     */
    public Empresa (){
        super();
        codigoEmpresa = 0;
        atividadeEconomica = new HashSet<>();
        fatorDeducaoFiscal = 0.0;
        numeroFaturas = 0;
    }

    /**
     * Construtor parametrizado da classe
     *
     * @param  NIF                  NIF do contribuinte individual
     * @param  email                Email do contribuinte individual
     * @param  nome                 Nome do contribuinte individual
     * @param  morada               Morada do contribuinte individual
     * @param  password             Password do contribuinte individual
     * @param  codigoEmpresa        Codigo da empresa (unico para cada empresa)
     * @param  atividadeEconomica   Atividade economica da empresa
     * @param  fatorDeducaoFiscal   Fator de deducao fiscal
     * @param  numeroFaturas        Numero de faturas que a empresa emitiu
     */
    public Empresa (int NIF, String email, String nome, String morada, int password, double valorGasto, int codigoEmpresa, Set<AtividadeEconomica> atividadeEconomica, double fatorDeducaoFiscal, int numeroFaturas){
        super(NIF, email, nome, morada, password, valorGasto);
        this.codigoEmpresa = codigoEmpresa;
        this.atividadeEconomica = atividadeEconomica;
        this.fatorDeducaoFiscal = fatorDeducaoFiscal;
        this.numeroFaturas = numeroFaturas;
    }

    /**
     * Construtor por copia da
     *
     * @param  e   Objeto da classe eEmpresa a ser copiada
     */
    public Empresa (Empresa e){
        super(e);
        this.codigoEmpresa = e.getCodigoEmpresa();
        this.atividadeEconomica = e.getAtividadeEconomica();
        this.fatorDeducaoFiscal = e.getFatorDeducaoFiscal();
        this.numeroFaturas = e.getNumeroFaturas();
    }

    /**
     * Metodo que devolve o codigo da empresa
     *
     * @return  Codigo da empresa
     */
    public int getCodigoEmpresa(){
        return this.codigoEmpresa;
    }

    /**
     * Metodo que devolve a atividade economica da empresa
     *
     * @return  Atividade economica
     */
    public Set<AtividadeEconomica> getAtividadeEconomica (){
        return this.atividadeEconomica;
    }

    /**
     * Metodo que devolve o fator de deducao fiscal
     *
     * @return  Fator de deducao fiscal
     */
    public double getFatorDeducaoFiscal (){
        return this.fatorDeducaoFiscal;
    }

    /**
     * Metodo que devolve o numero de faturas que uma empresa emitiu
     *
     * @return  numeroFaturas
     */
    public int getNumeroFaturas (){
        return this.numeroFaturas;
    }

    /**
     * Metodo que define o codigo de uma empresa
     *
     * @param  codigoEmpresa   Codigo que a empresa vai ter
     */
    public void setCodigoEmpresa (int codigoEmpresa){
        this.codigoEmpresa = codigoEmpresa;
    }

    /**
     * Metodo que define a atividade economica
     *
     * @param  atividadeEconomica
     */
    public void setAtividadeEconomica (Set<AtividadeEconomica> atividadeEconomica){
        this.atividadeEconomica = atividadeEconomica;
    }

    /**
     * Metodo que define o fator de deducao fiscal
     *
     * @param  fatorDeducaoFiscal
     */
    public void setFatorDeducaoFiscal (double fatorDeducaoFiscal){
        this.fatorDeducaoFiscal = fatorDeducaoFiscal;
    }

    /**
     * Metodo equals da classe
     *
     * @return  true se os objetos sao iguais, false se sao diferentes
     */
    public boolean equals(Object o) {
        if (o==this) {return true;}
        if(o==null || o.getClass() != this.getClass()) {return false;}
        Empresa e = (Empresa) o;
        return  super.equals(e) &&
                e.getCodigoEmpresa()==codigoEmpresa &&
                e.getFatorDeducaoFiscal()==fatorDeducaoFiscal &&
                e.getAtividadeEconomica().equals(atividadeEconomica);
    }

    /**
     * Metodo clone da classe
     *
     * @return  Clone do objeto Empresa
     */
    public Empresa clone() {
        return new Empresa(this);
    }

    /**
     * Metodo toString da classe Empresa
     *
     * @return  String com informacao da Empresa
     */
    public String toString(){
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("; Codigo da empresa: ");
        sb.append(this.getCodigoEmpresa());
        sb.append("; Atividade economica: ");
        for (AtividadeEconomica cod : this.getAtividadeEconomica()){
            sb.append("  "+cod.getClass().getName());
        }
        sb.append("; Fator de deducao fiscal: ");
        sb.append(this.getFatorDeducaoFiscal());
        return sb.toString();
    }
}
