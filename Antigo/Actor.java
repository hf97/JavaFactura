import java.io.Serializable;

/**
 * Clase abstrata que representa os utilizadores da plataforma JavaFactura.
 * Usada para heranca
 * 
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public abstract class Actor implements Serializable 
{
    private int NIF;
    private String email;
    private String nome;
    private String morada;
    private int password;
    private double valorGasto;

    /**
     * Construtor vazio da classe
     */
    public Actor () {
        this.NIF=0;
        this.email="";
        this.nome="";
        this.morada="";
        this.password=0;
        this.valorGasto=0.0;
    }

    /**
     * Construtor parameterizado da classe
     * 
     * @param  NIF          NIF do actor
     * @param  email        Email do actor
     * @param  nome         Nome do actor
     * @param  morada       Morada do actor
     * @param  password     Password do actor
     * @param  valorGasto   Valor gasto
     */
    public Actor (int NIF, String email, String nome, String morada, int password, double valorGasto) {
        this.NIF=NIF;
        this.email=email;
        this.nome=nome;
        this.morada=morada;
        this.password=password;
        this.valorGasto=valorGasto;
    }
    
    /**
     * Contrutor por copia da classe
     * 
     * @param  i   Actor a ser copiado
     */
    public Actor (Actor i) {
        this.NIF=i.getNIF();
        this.email=i.getEmail();
        this.nome=i.getNome();
        this.morada=i.getMorada();
        this.password=i.getPassword();
        this.valorGasto=i.getValorGasto();
    }
    
    /**
     * Metodo que devolve o NIF
     * 
     * @return  NIF do actor
     */
    public int getNIF() {
        return this.NIF;
    }

    /**
     * Metodo que devolve o email
     * 
     * @return  Email do actor
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Metodo que devolve o nome
     * 
     * @return  Nome do actor
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo que devolve a morada
     * 
     * @return  Morada do actor
     */
    public String getMorada() {
        return this.morada;
    }

    /**
     * Metodo que devolve a password
     * 
     * @return  Password do actor
     */
    public int getPassword() {
        return this.password;
    }

    /**
     * Metodo que devolve o valor gasto
     * 
     * @return  Valor gasto
     */
    public double getValorGasto(){
        return this.valorGasto;
    }
    
    /**
     * Metodo que define o valor gasto
     * 
     * @param  valor gasto
     */
    public void setValorGasto (double valorGasto){
        this.valorGasto = valorGasto;
    }
    
    /**
     * Metodo equals da classe
     * 
     * @param   o   Objeto da classe actor
     * 
     * @return  true se os objeto sao iguais, false se sao diferentes
     */
    public boolean equals(Object o) {
        if (o==this) {return true;}
        if(o==null || o.getClass() != this.getClass()) {return false;}
        Actor i = (Actor) o;
        return i.getNIF()==NIF &&
                i.getEmail().equals(email) &&
                i.getNome().equals(nome) &&
                i.getMorada().equals(morada) &&
                i.getPassword()==(password) &&
                i.getValorGasto()==(valorGasto);
    }
    
    /**
     * Metodo clone da classe
     */
    public abstract Actor clone();

    /**
     * Metodo toString da classe
     * 
     * @return  String com a informacao do Actor
     */
    public String toString(){
        StringBuilder sb = new StringBuilder("NIF: ");
        sb.append(this.getNIF());
        sb.append("; Email: ");
        sb.append(this.getEmail());
        sb.append("; Nome: ");
        sb.append(this.getNome());
        sb.append("; Morada: ");
        sb.append(this.getMorada());
        sb.append("; Password: ");
        sb.append(this.getPassword());
        sb.append("; Valor Gasto: ");
        sb.append(this.getValorGasto());
        return sb.toString();
    }
}
