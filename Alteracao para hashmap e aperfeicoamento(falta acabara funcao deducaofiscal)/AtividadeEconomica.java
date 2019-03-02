import java.io.Serializable;

/**
 * Clase abstrata que representa os codigos das atividades economicas
 *
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public abstract class AtividadeEconomica implements Serializable
{
    /**
     * Metodo que calcula o valor de deducao
     *
     * @return  Valor de deducao
     */
    public abstract double valorDeducao();

    /**
     * Metodo equals
     */
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) return true;
        else return false;
    }

    /**
     * Metodo abstract toString da classe
     */
    public abstract String toString();
}
