import java.io.Serializable;

/**
 * Clase que representa a atividade economica Saudes
 *
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class Saude extends AtividadeEconomica implements Serializable
{

    /**
     * Metodo que devolve o valor a deduzir
     *
     * @return  valor da deducao
     */
    public double valorDeducao (){
        return 0.2;
    }

    /**
     * Metodo equals
     */
    public boolean equals (Object o){
        Saude a = (Saude) o;
        return super.equals(a);
    }

    /**
     * Metodo to String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        return sb.toString();
    }
}
