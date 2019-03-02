import java.util.Comparator;

/**
 * Classe responsavel por comparar faturas por valor
 * 
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class ComparadorDespesaValorDecrescente implements Comparator<Despesa>
{
    public int compare(Despesa d1,Despesa d2){
        if (d1.getValorDespesa()>d2.getValorDespesa()) return 1;
        else if (d1.getValorDespesa()<d2.getValorDespesa()) return -1;
        else return 0;
    }
}
