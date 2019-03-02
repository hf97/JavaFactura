import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Clase que representa os contribuintes individuais que usam a plataforma
 * 
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class Individual extends Actor implements Serializable 
{
    private int numeroAgregadoFamiliar;
    private int numeroFilhos;
    private ArrayList<Integer> NIFAgregadoFamiliar;
    private double coeficienteFiscal;
    private double valorGasto;
    private Set<CodigoAtividadeEconomica> codigoAtividadeEconomica;
    

    /**
     * Construtor vazio da classe
     */
    public Individual(){
        super();
        numeroAgregadoFamiliar = 0;
        numeroFilhos = 0;
        NIFAgregadoFamiliar = new ArrayList<Integer>();
        coeficienteFiscal = 0;
        codigoAtividadeEconomica = new HashSet<>();
    }
    
    /**
     * Construtor parametrizador da classe
     * 
     * @param  NIF                        NIF do contribuinte individual
     * @param  email                      Email do contribuinte individual
     * @param  nome                       Nome do contribuinte individual
     * @param  morada                     Morada do contribuinte individual
     * @param  password                   Password do contribuinte individual
     * @param  numeroAgregadoFamiliar     Numero do agregado familiar do contribuinte individual
     * @param  numeroFilhos               Numero de filhos do agregado familiar
     * @param  NIFAgregadoFamiliar        NIF dos elementos do agregado familiar
     * @param  coeficienteFiscal          Coeficiente fiscal
     * @param  codigoAtividadeEconomica   Codigos das atividades economicas em que um contribuinte pode deduzir despesas
     */
    public Individual (int NIF, String email, String nome, String morada, int password, double valorGasto, int numeroAgregadoFamiliar, int numeroFilhos, ArrayList<Integer> NIFAgregadoFamiliar, double coeficienteFiscal, Set<CodigoAtividadeEconomica> codigoAtividadeEconomica){
        super(NIF,email,nome,morada,password, valorGasto);
        this.numeroAgregadoFamiliar = numeroAgregadoFamiliar;
        this.numeroFilhos = numeroFilhos;
        this.NIFAgregadoFamiliar = NIFAgregadoFamiliar;
        this.coeficienteFiscal = coeficienteFiscal;
        this.codigoAtividadeEconomica = codigoAtividadeEconomica;
    }
    
    /**
     * Construtor por copia da classe
     * 
     * @param  i   Objeto da classe iIndividual a ser copiado
     */
    public Individual (Individual i){
        super(i);
        this.numeroAgregadoFamiliar = i.getNumeroAgregadoFamiliar();
        this.numeroFilhos = i.getNumeroFilhos();
        this.NIFAgregadoFamiliar = i.getNIFAgregadoFamiliar();
        this.coeficienteFiscal = i.getCoeficienteFiscal();
        this.codigoAtividadeEconomica = i.getCodigoAtividadeEconomica();
    }
    
    /**
     * Metodo que devolve o numero de dependentes do agregado familiar
     * 
     * @return   numero dependentes do agregado familiar
     */
    public int getNumeroAgregadoFamiliar(){
        return this.numeroAgregadoFamiliar;
    }

    /**
     * Metodo que devolve o numero de filhos num agregado familiar
     * 
     * @return   numero de filhos num agregado familiar
     */
    public int getNumeroFilhos(){
        return this.numeroFilhos;
    }
    
    /**
     * Metodo que devolve a lista dos NIF dos elementos do agregado familiar
     * 
     * @return   NIF dos elementos do agregado familiar
     */
    public ArrayList<Integer> getNIFAgregadoFamiliar(){
        return this.NIFAgregadoFamiliar;
    }
    
    /**
     * Metodo que devolve o numero do coeficiente fiscal
     * 
     * @return  numero coeficiente fiscal
     */
    public double getCoeficienteFiscal(){
        return this.coeficienteFiscal;
    }
    
    /**
     * Metodo que devolve o codigo da atividade economica
     * 
     * @return   codigo atividade economica
     */
    public Set<CodigoAtividadeEconomica> getCodigoAtividadeEconomica(){
        return this.codigoAtividadeEconomica;
    }
    
    /**
     * Metodo que define o numero de dependentes do agregado familiar
     * 
     * @param   numero de dependentes do agregado familiar
     */
    public void setNumeroAgregadoFamiliar (int numeroAgregadoFamiliar){
        this.numeroAgregadoFamiliar = numeroAgregadoFamiliar;
    }
    
    /**
     * Metodo que define o numero de filhos num agregado familiar
     * 
     * @param   numero de filhos num agregado familiar
     */
    public void setNumeroFilhos (int numeroFilhos){
        this.numeroFilhos = numeroFilhos;
    }
    
    /**
     * Metodo que adiciona o NIF de um elemento do agregado familiar a lista
     * 
     * @param   NIF dum elemento do agregado familar
     */
    public void setNIFAgregadoFamiliar (ArrayList<Integer> NIFAgregadoFamiliar, int NIF){
        NIFAgregadoFamiliar.add(NIF);
    }
    
    /**
     * Metodo que define o numero docoeficiente fiscal
     * 
     * @param   coeficiente fiscal
     */
    public void setCoeficienteFiscal (double coeficienteFiscal){
        this.coeficienteFiscal = coeficienteFiscal;
    }
    
    /**
     * Metodo que define o codigo da atividade economica
     * 
     * @param   codigo da atividade economica
     */
    public void setCodigoAtividadeEconomica (Set<CodigoAtividadeEconomica> codigoAtividadeEconomica){
        this.codigoAtividadeEconomica = codigoAtividadeEconomica;
    }
    
    /**
     * Metodo equals da classe
     * 
     * @return  true se os objetos sao iguais, false se sao diferentes
     */
    public boolean equals (Object o){
        if (o==this) {return true;}
        if(o==null || o.getClass() != this.getClass()) {return false;}
        Individual i = (Individual) o;
        return i.getNumeroAgregadoFamiliar()==(numeroAgregadoFamiliar) &&
               i.getNumeroFilhos()==(numeroFilhos) &&
               i.getNIFAgregadoFamiliar()==(NIFAgregadoFamiliar) &&
               i.getCoeficienteFiscal()==(coeficienteFiscal) &&
               i.getCodigoAtividadeEconomica()==(codigoAtividadeEconomica);
    }
    
    /**
     * Metodo clone da classe
     * 
     * @return  Clone do objeto Individual
     */
    public Individual clone(){
        return new Individual(this);
    }

    /**
     * Metodo toString da classe Individual
     * 
     * @return  String com informacao do Individual
     */
    public String toString(){
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("; Numero do agregado familiar: ");
        sb.append(this.getNumeroAgregadoFamiliar());
        sb.append("; Numero de filhos: ");
        sb.append(this.getNumeroFilhos());
        sb.append("; NIF dos elementos do agregado familiar: ");
        for (int nif : NIFAgregadoFamiliar) {
            sb.append("  "+nif);
        }
        sb.append("; Coeficiente fiscal: ");
        sb.append(this.getCoeficienteFiscal());
        sb.append("; Codigos das atidvidades economicas: ");
        for (CodigoAtividadeEconomica cod : this.getCodigoAtividadeEconomica()){
            sb.append("  "+cod.getClass().getName());
        }
        return sb.toString();
    }
}

