import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

/**
 * Clase que representa uma familia numerosa
 *
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class IndividualNumerosa extends Individual implements Serializable{

    /**
     * Construtor vazio da classe
     */
    public IndividualNumerosa(){
        super();
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
     * @param  atividadeEconomica         Codigos das atividades economicas em que um contribuinte pode deduzir despesas
     */
    public IndividualNumerosa (int NIF, String email, String nome, String morada, int password, double valorGasto, int numeroAgregadoFamiliar, int numeroFilhos, ArrayList<Integer> NIFAgregadoFamiliar, double coeficienteFiscal, Set<AtividadeEconomica> atividadeEconomica) {
        super(NIF, email, nome, morada, password, valorGasto, numeroAgregadoFamiliar, numeroFilhos, NIFAgregadoFamiliar, coeficienteFiscal, atividadeEconomica);
    }

    /**
     * Construtor por copia da classe
     *
     * @param  i   Objeto da classe iIndividual a ser copiado
     */
    public IndividualNumerosa (IndividualNumerosa i) {
        super(i);
    }

    /**
     * Metodo equals da classe
     *
     * @return  true se os objetos sao iguais, false se sao diferentes
     */
    public boolean equals (Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Individual i = (Individual) o;
        return super.equals(i);
    }

    /**
     * Metodo clone da classe
     *
     * @return  Clone do objeto Individual
     */
    public IndividualNumerosa clone(){
        return new IndividualNumerosa(this);
    }

    /**
     * Metodo toString da classe Individual
     *
     * @return  String com informacao do Individual
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        return sb.toString();
    }
}
