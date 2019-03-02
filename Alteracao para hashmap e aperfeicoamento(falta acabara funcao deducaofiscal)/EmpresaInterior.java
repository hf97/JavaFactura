import java.io.Serializable;
import java.util.Set;

/**
 * Classe que representa as Empresas do interior que usam a plataforma
 *
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class EmpresaInterior extends Empresa implements Serializable {

    private Concelho concelho;

    /**
     * Construtor vazio da Classe Empresa
     */
    public EmpresaInterior (){
        super();
        this.concelho = new Outro();
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
    public EmpresaInterior (int NIF, String email, String nome, String morada, int password, double valorGasto, int codigoEmpresa, Set<AtividadeEconomica> atividadeEconomica, double fatorDeducaoFiscal, int numeroFaturas, Concelho concelho){
        super(NIF, email, nome, morada, password, valorGasto, codigoEmpresa, atividadeEconomica, fatorDeducaoFiscal, numeroFaturas);
        this.concelho = concelho;
    }

    /**
     * Construtor por copia da
     *
     * @param  e   Objeto da classe eEmpresa a ser copiada
     */
    public EmpresaInterior (EmpresaInterior e){
        super(e);
        this.concelho = e.getConcelho();
    }

    /**
     * Metodo que devolve o concelho
     *
     * @return   concelho   Concelho da empresa
     */
    public Concelho getConcelho(){
        return this.concelho;
    }

    /**
     * Metodo equals da classe
     *
     * @return  true se os objetos sao iguais, false se sao diferentes
     */
    public boolean equals(Object o) {
        if (o==this) {return true;}
        if(o==null || o.getClass() != this.getClass()) {return false;}
        EmpresaInterior e = (EmpresaInterior) o;
        return  super.equals(e) &&
                e.getConcelho().equals(concelho);
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

