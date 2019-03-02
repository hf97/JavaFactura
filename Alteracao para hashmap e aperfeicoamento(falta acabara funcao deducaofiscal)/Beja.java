/**
 * Clase que representa o concelho de Beja
 *
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */

public class Beja extends Concelho{
    /**
     * Metodo que devolve o valor a deduzir
     *
     * @return  valor da deducao
     */
    public double valorDeducao (){ return 0.17; }

    /**
     * Metodo equals
     */
    public boolean equals (Object o){
        Outro a = (Outro) o;
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
