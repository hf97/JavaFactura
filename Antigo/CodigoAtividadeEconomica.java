import java.io.Serializable;

/**
 * Clase abstrata que representa os codigos das atividades economicas
 * 
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public abstract class CodigoAtividadeEconomica implements Serializable
{
    private String nome;
    
    /**
     * Construtor vazio da classe
     */
    public CodigoAtividadeEconomica(){
        this.nome = "";
    }

    /**
     * Construtor parametrizado da classe
     * 
     * @param  nome   nome da atividade economica
     */
    public CodigoAtividadeEconomica (String nome){
        this.nome = nome;
    }

    /**
     * Construtor por copia da classe
     * 
     * @param  cae   Objeto da classe CodigosAtividadesEconomicas que vai ser copiado
     */
    public CodigoAtividadeEconomica (CodigoAtividadeEconomica cae){
        this.nome = getNome();
    }

    /**
     * Metodo que devolve o codigo da atividade
     * 
     * @return  nome da atividade economica
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * Metodo que calcula o valor de deducao
     * 
     * @return  Valor de deducao
     */
    public double valorDeducao (double valor){
        return valor * 0.0;
    }

    /**
     * Metodo clone da classe
     * 
     * @return  Objeto CodigoAtividadeEconomica
     */
    public abstract CodigoAtividadeEconomica clone();

    /**
     * Metodo equals da classe
     */
    public boolean equals (Object o){
        if (o==this) return true;
        if ((o==null) || (o.getClass() !=this.getClass())) return false;
        CodigoAtividadeEconomica cae = (CodigoAtividadeEconomica) o;
        return cae.getNome() == (nome);
    }
    /**
     * Metodo toString da classe
     */
    public String toString(){
        StringBuilder sb = new StringBuilder("Nome: ");
        sb.append(this.getNome());
        return sb.toString();
    }
}
