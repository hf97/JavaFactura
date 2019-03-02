import java.util.Comparator;

/**
 * Classe responsavel por comparar faturas por data
 * 
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class ComparadorDespesaData implements Comparator<Despesa>
{
    public int compare(Despesa d1,Despesa d2){
        if (d1.getDataDespesa().isAfter(d2.getDataDespesa())) return -1;
        else if (d1.getDataDespesa().isBefore(d2.getDataDespesa())) return 1;
        else return 0;
    }
}
