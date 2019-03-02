import java.util.Comparator;

/**
 * Classe responsavel por comparar faturas por valor
 * 
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class ComparadorIndividualValorGasto implements Comparator<Individual>
{
    public int compare(Individual i1,Individual i2){
        if (i1.getValorGasto()>i2.getValorGasto()) return 1;
        else if (i1.getValorGasto()<i2.getValorGasto()) return -1;
        else return 0;
    }
}