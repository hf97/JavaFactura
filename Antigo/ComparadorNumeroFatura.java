import java.util.Comparator;

/**
 * Classe responsavel por comparar faturas por valor
 * 
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class ComparadorNumeroFatura implements Comparator<Empresa>
{
    public int compare(Empresa e1,Empresa e2){
        if (e1.getNumeroFaturas()>e2.getNumeroFaturas()) return 1;
        else if (e1.getNumeroFaturas()<e2.getNumeroFaturas()) return -1;
        else return 0;
    }
}
