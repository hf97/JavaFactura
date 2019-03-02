import java.io.Serializable;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.TreeMap;
import java.io.Serializable;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.TreeMap;

import java.util.Map;

import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import java.util.stream.Stream;
import java.util.Comparator;

import java.util.Collections;

/**
 * Classe responsavel por guardar a informacao num ficheiro
 * 
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class Informacao implements Serializable
{
    int numeroDespesa;
    private Set<Actor> utilizador;
    private Set<Despesa> despesa;
    private Set<FaturaAtividadeCorrigida> faturaAtividadeCorrigida;
    
    public Informacao(){
        numeroDespesa = 0;
        this.utilizador = new HashSet<Actor>();
        this.despesa = new HashSet<Despesa>();
        this.faturaAtividadeCorrigida = new HashSet<FaturaAtividadeCorrigida>();
    }

    /**
     * Construtor parametrizado da classe Informacao
     * 
     * @param  numeroDespesa    Numero de despesa criadas
     * @param  act              Conjunto de Actores
     * @param  des              Conjunto de despesas
     */
    public Informacao (int numeroDespesa, Set<Actor> uti, Set<Despesa> des, Set<FaturaAtividadeCorrigida> fac){
        this.numeroDespesa = numeroDespesa;
        this.utilizador = new HashSet<Actor>(uti.size());
        for(Actor a: uti){
            this.utilizador.add(a.clone());
        }
        this.despesa = new HashSet<Despesa>(des.size());
        for(Despesa d: des){
            this.despesa.add(d.clone());
        }
        this.faturaAtividadeCorrigida = new HashSet<FaturaAtividadeCorrigida>(fac.size());
        for(FaturaAtividadeCorrigida fat : fac){
            this.faturaAtividadeCorrigida.add(fat.clone());
        }
    }
    
    /**
     * Construtor de copia da classe Informacao
     */
    public Informacao (Informacao i){
        this.numeroDespesa = i.getNumeroDespesa();
        this.utilizador = i.getUtilizador();
        this.despesa = i.getDespesa();
    }
    
    /**
     * Metodo que devolve o numero de despesa criadas
     * 
     * @return  Numero de despesas criadas
     */
    public int getNumeroDespesa(){
        return this.numeroDespesa;
    }
    
    /**
     * Metodo que define o numero de despesas criadas
     * 
     * @param  numeroDespesa   Novo numero de despesa
     */
    public void setNumeroDespesa (int numeroDespesa){
        this.numeroDespesa = numeroDespesa;
    }
    
    /**
     * Carrega num ficheiro um objecto da classe Informacao (esse ficheiro tem sempre o nome BaseDados)
     */
    public static Informacao carregaObjetoInformacao() throws IOException, ClassNotFoundException, FileNotFoundException
    {
       FileInputStream carregaFicheiro = new FileInputStream("Base Dados");
       if (carregaFicheiro == null) {
            throw new FileNotFoundException("");
       } else {
            ObjectInputStream obj = new ObjectInputStream(carregaFicheiro);
            if (obj == null) {
                throw new IOException("");
            } else {
                Informacao i = (Informacao) obj.readObject();
                if (i == null) {
                    throw new ClassNotFoundException("");
                } else {
                    obj.close();
                    return i;
                }
            }
       }
    }
    
    /**
     * Guarda num ficheiro um objecto da classe Informacao (esse ficheiro tem sempre o nome BaseDados)
     */
    public void gravaObjetoInformacao() throws IOException, FileNotFoundException
    {
       FileOutputStream guardaFicheiro = new FileOutputStream("Base Dados");
       if (guardaFicheiro == null) { 
           throw new FileNotFoundException("");
       } else { 
           ObjectOutputStream objeto = new ObjectOutputStream(guardaFicheiro);
           if (objeto == null) {
               throw new IOException("");
           } else {
               objeto.writeObject(this);
               objeto.flush();
               objeto.close();
           }
       }
    }
    
    //-----------------
    //OPCOES INDIVIDUAL
    //-----------------
    /**
     * Funcao que permite verificar as despesas emitidas por um utilizador
     * 
     * @param  ui   Utilizador que verifica as suas despesas
     */
    public Set<Despesa> verificarDespesas (Individual ui){
        Set<Despesa> des = new HashSet<Despesa>();
        for(Despesa d : this.getDespesa()){
            if(ui.getNIF()==d.getClienteNIF()){
                des.add(d);
            }
        }
        return des;
    }
    
    /**
     * Funcao que permite verificar o montante de deducao fiscal acumulado por o contribuinte individual e pelo seu agregado
     * 
     * @param   ui                       Utilizador que verifica o montante de deducao fiscal
     * @return  a.getCoeficienteFiscal   Montante de deducao fiscal
     */
    public double verificarMontanteDeducaoFiscal (Individual ui){
        double deducao = 0.0;
        Set<CodigoAtividadeEconomica> caeIndividual = ui.getCodigoAtividadeEconomica();
        for(Despesa des : this.getDespesa()){
            if(des.getClienteNIF()==ui.getNIF()){
                Set<CodigoAtividadeEconomica> atiEco = des.getAtividadeEconomica();
                for(CodigoAtividadeEconomica cae : caeIndividual){
                    for(CodigoAtividadeEconomica at : atiEco){
                        if(ui.getNumeroFilhos()>0){
                            deducao += at.valorDeducao(des.getValorDespesa())*0.05*ui.getNumeroFilhos();
                        }
                    }
                }   
            }
        }
        return deducao;
    }              
    
    /**
     * Funcao que permite corrigir a classificacao de atividade economica a uma despesa
     * 
     * @param  ui   Utilizador que vai corrigir a classificacao de atividade economica
     * @param  i    Estado da Informacao
     */
    public void corrigirClassificacaoAtividadeEconomicaDespesaIndividual (Individual ui){
        Scanner input = new Scanner(System.in);
        System.out.println("Codigo da despesa: ");
        int codigoDespesa = input.nextInt();
        for(Despesa des : despesa){
            try{
                if(des.getCodigo()==codigoDespesa && des.getClienteNIF()==ui.getNIF()){
                    Set<CodigoAtividadeEconomica> atividadeAntiga = new HashSet<CodigoAtividadeEconomica>();
                    atividadeAntiga = des.getAtividadeEconomica();
                    Set<CodigoAtividadeEconomica> cae = new HashSet<CodigoAtividadeEconomica>();
                    Menu.menuCodigoAtividadeEconomica();
                    int escolha = input.nextInt();
                    switch (escolha){
                        case 1:
                            cae.add(new Construcao());
                            break;
                        case 2:
                            cae.add(new Transporte());
                            break;
                        case 3:
                            cae.add(new Alimento());
                            break;
                        case 4:
                            cae.add(new Saude());;
                            break;
                        case 5:
                            cae.add(new Educacao());
                            break;
                    }
                    des.setAtividadeEconomica(cae);
                    FaturaAtividadeCorrigida fac = new FaturaAtividadeCorrigida(codigoDespesa, atividadeAntiga, cae);
                    adicionaFaturaAtividadeCorrigida(fac);
                }
            } catch(Exception e){
                System.out.println("Despesa nao existe ou nao esta associada a si");
                corrigirClassificacaoAtividadeEconomicaDespesaIndividual(ui);
            }
        }
    }
    
    /**
     * Funcao que permite associar uma atividade economica a uma despesa nao validada
     */
    public void associarAtividadeEconomica (Individual ui){
        Scanner input = new Scanner(System.in);
        System.out.println("Codigo da despesa: ");
        int codigoDespesa = input.nextInt();
        for(Despesa des : getDespesa()){
            try{
                Set<CodigoAtividadeEconomica> cae = des.getAtividadeEconomica();
                Set<CodigoAtividadeEconomica> codAtiEco = new HashSet<CodigoAtividadeEconomica>();
                if(des.getCodigo()==codigoDespesa){
                    for(CodigoAtividadeEconomica at : cae){
                        if(at.getClass().getSimpleName().equals("NaoValidada")){
                            Menu.menuCodigoAtividadeEconomica();
                            int escolha = input.nextInt();
                            switch (escolha){
                                case 1:
                                    codAtiEco.add(new Construcao());
                                    break;
                                case 2:
                                    codAtiEco.add(new Transporte());
                                    break;
                                case 3:
                                    codAtiEco.add(new Alimento());
                                    break;
                                case 4:
                                    codAtiEco.add(new Saude());;
                                    break;
                                case 5:
                                    codAtiEco.add(new Educacao());
                                    break;
                            }
                            des.setAtividadeEconomica(codAtiEco);
                        }
                    }
                }
            }catch(Exception e){
                System.out.println("Despesa nao existe ou nao esta associada a si");
                corrigirClassificacaoAtividadeEconomicaDespesaIndividual(ui);
            } 
        }
    }
    
    //--------------
    //OPCOES EMPRESA
    //--------------
    /**
     * Funcao para um utilizador criar uma factura
     * 
     * @param  ui   Utilizador que vai criar a factura
     */
    public void criarFactura (Empresa ue){
        
        Scanner input = new Scanner(System.in);
        boolean f = true;
        LocalDate dataDespesa = null;
        int codigo = getNumeroDespesa()+1;
        setNumeroDespesa(codigo);
        int emitenteNIF = ue.getNIF();
        System.out.println ("Designacao do emitente: ");
        String designacaoEmitente = input.next();
        System.out.println ("Data da despesa (dd-MM-yyyy): ");
        do{
            try{
                String dataDespesaInput = input.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                dataDespesa = LocalDate.parse(dataDespesaInput, formatter);
                f=false;
            }
            catch(Exception e){System.out.println("Data invalida.");}
        }while(f);
        System.out.println("NIF do cliente: ");
        int clienteNIF = input.nextInt();
        System.out.println ("Descricao da despesa: ");
        String designacaoDespesa = input.next();
        System.out.println ("Atividade Economica: ");
        Menu.menuCodigoAtividadeEconomica();
        Set<CodigoAtividadeEconomica> atividadeEconomica = new HashSet<CodigoAtividadeEconomica>();
        int escolha = input.nextInt();
        switch (escolha){
                case 1:
                    atividadeEconomica.add(new Construcao());
                    break;
                case 2:
                    atividadeEconomica.add(new Transporte());
                    break;
                case 3:
                    atividadeEconomica.add(new Alimento());
                    break;
                case 4:
                    atividadeEconomica.add(new Saude());
                    break;
                case 5:
                    atividadeEconomica.add(new Educacao());
                    break;
                case 6:
                    atividadeEconomica.add(new NaoValidada());
                    break;
        }
        System.out.println ("Valor da despesa: ");
        Double valorDespesa = input.nextDouble();
        Despesa des = new Despesa(codigo, emitenteNIF, designacaoEmitente, dataDespesa, clienteNIF, designacaoDespesa, atividadeEconomica, valorDespesa);
        adicionaDespesa(des);
        for(Actor a : getUtilizador()){
            if(a.getClass().getSimpleName().equals("Individual")){    
                if(a.getNIF()==clienteNIF){
                    a.setValorGasto(a.getValorGasto() + valorDespesa);
                }
            }
        }
    }
    
    /**
     * Mostra a lista de despesas associadas a uma empresa ordenada por data
     * 
     * @param  ue   Empresa cujas despesas estao associadas
     */
    public Set<Despesa> listaDespesaOrdenadaData (Empresa ue){
        Set h = new TreeSet<Despesa>(new ComparadorDespesaData());
        for(Despesa d : getDespesa()){
            if(d.getEmitenteNIF()==ue.getNIF()){
                h.add((Despesa) d);
            }
        }
        return h;
    }

    /**
     * Lista de despesas associadas a uma empresa ordenadas por valor
     * 
     * @param  ue   Empresa cujas despesas estão associadas
     */
    public Set<Despesa> listaDespesaOrdenadaValor (Empresa ue){
        Set h = new TreeSet<Despesa>(new ComparadorDespesaValor());
        for(Despesa d : getDespesa()){
            if(d.getEmitenteNIF()==ue.getNIF()){
                h.add((Despesa) d);
            }
        }
        return h;
    }

    /**
     * Mostra a lista de despesas por parte de uma empresa de um contribuinte entre duas datas
     * 
     * @param  ue           Empresa que verifica as despesas entre duas datas
     * @param  clienteNIF   Contribuinte individual que vai ter as despesas verificadas
     */
    public Set<Despesa> listaDespesaContribuinteEntreData(Empresa ue, int clienteNIF){
        Scanner input = new Scanner(System.in);        
        boolean flag= true;
        LocalDate inicio = null;
        LocalDate fim = null;
        String date = null;
        System.out.println("Insira a data inicial no formato seguinte: dd-MM-yyyy");
        flag = true;
        do{
            try{
                date = input.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                inicio = LocalDate.parse(date, formatter);
                flag = false;
            }catch(java.time.format.DateTimeParseException e){System.out.println("Data invalida.");}
        }while(flag);
        System.out.println("Insira a data final no formato seguinte: dd-MM-yyyy");
        flag = true;
        do{
            try{
                date = input.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                fim = LocalDate.parse(date, formatter);
                flag = false;
            }catch(java.time.format.DateTimeParseException e){System.out.println("Data invalida.");}
        }while(flag);

        Set<Despesa> des = new HashSet<Despesa>();
        for(Despesa d : getDespesa()){
            LocalDate data = d.getDataDespesa();
            if(d.getClienteNIF()==clienteNIF && inicio.isBefore(data) && fim.isAfter(data)){
                des.add(d);
            }
        }
        return des;
    }

    /**
     * Mostra a lista de despesas por parte de uma empresa por contribuinte por valor da despesa por ordem decrescente
     * 
     * @param  eu           Empresa que verifica as despesas
     * @param  clienteNIF   NIF do contribuinte individual que vai ter as despesas verificadas
     */
    public Set<Despesa> listaDespesaContribuinteOrdenadaDecrescenteDespesa (Empresa ue, int clienteNIF){
        Set<Despesa> des = new TreeSet<Despesa>(new ComparadorDespesaValorDecrescente());
        for(Despesa d : getDespesa()){
            if(d.getClienteNIF()==clienteNIF){
                des.add((Despesa) d);
            }
        }
        return des;
    }    
    
    /**
     * Funcao que da o total faturado por uma empresa num periodo
     * 
     * @param  ue   Empresa que verifica o total fatorado
     * 
     * @return  tf   Total fatorado
     */
    public double totalFaturadoPeriodo (Empresa ue){
        Scanner input = new Scanner(System.in);        
        boolean flag= true;
        double tf = 0.0;
        LocalDate inicio = null;
        LocalDate fim = null;
        String date = null;
        System.out.println("Insira a data inicial no formato seguinte: dd-MM-yyyy");
        flag = true;
        do{
            try{
                date = input.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                inicio = LocalDate.parse(date, formatter);
                flag = false;
            }catch(java.time.format.DateTimeParseException e){System.out.println("Data invalida.");}
        }while(flag);
        System.out.println("Insira a data final no tipo: dd-MM-yyyy");
        flag = true;
        do{
            try{
                date = input.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                fim = LocalDate.parse(date, formatter);
                flag = false;
            }catch(java.time.format.DateTimeParseException e){System.out.println("Data invalida.");}
        }while(flag);
        Set<Despesa> des = new HashSet<Despesa>();
        for(Despesa d : getDespesa()){
            LocalDate data = d.getDataDespesa();
            if(inicio.isBefore(data) && fim.isAfter(data)){
                tf+=d.getValorDespesa();
            }
        }
        return tf;
    }   

    //------------
    //OPCOES ADMIN
    //------------
    /**
     * Funcao que da os 10 contribuintes individuais mais gastadores
     * 
     * @return  ind   TreeSet que contem os 10 contribuintes individuais mais gastadores
     */
    public TreeSet<Individual> relacaoDezContribuintesMaisGastadores(){
        TreeSet ind = new TreeSet<Individual>(new ComparadorIndividualValorGasto());
        for(Actor a : this.getUtilizador()){
            if(a.getClass().getSimpleName().equals("Individual")){
                ind.add((Individual)a);
            }
        }
        while(ind.size()>10){
            ind.remove(ind.first());
        }
        return ind;
    }
    
    /**
     * Funcao que da as X empresas com mais faturas
     * 
     * @return  emp   TreeSet com a lista das X empresas com mais faturas
     */
    public TreeSet<Empresa> XEmpresasMaisFaturas (int x){
        TreeSet<Empresa> emp = new TreeSet<Empresa>(new ComparadorNumeroFatura());
        for(Actor a : getUtilizador()){
            if(a.getClass().getSimpleName().equals("Empresa")){
                emp.add((Empresa) a);
            }
        }
        while(emp.size()>x){
            emp.remove(emp.first());
        }
        return emp;
    }
    
    
    
    
    /**
     * Funcao que da Montante das deducoes fiscais das despesas
     * 
     * @return  
     */
    public Map<Integer, Double> montanteDeducaoFiscal (int x){
        TreeSet<Empresa> emp = XEmpresasMaisFaturas(x);
        TreeMap<Integer, Double> m = new TreeMap<Integer, Double>();
        double deducao = 0.0;
        for(Empresa e : emp){
            for(Despesa des : this.getDespesa()){
                if(des.getEmitenteNIF()==e.getNIF()){
                    Set<CodigoAtividadeEconomica> atiEco = des.getAtividadeEconomica();
                    for(CodigoAtividadeEconomica at : atiEco){
                        deducao += at.valorDeducao(des.getValorDespesa());
                        m.put(e.getNIF(), deducao);
                    }
                }
            }   
        }
        return m;
    }  
    
   
   
    
    
    /**
     * Funcao que imprime todos os utilizadores individuais
     */
    public Set<Actor> imprimeIndividual(){
        Set<Actor> ind = new HashSet<Actor>();
        for(Actor a : this.utilizador){
            if(a.getClass().getSimpleName().equals("Individual")){
                ind.add(a);
            }
        }
        return ind;
    }
    
    /**
     * Funcao que imprime todas as empresas
     */
    public Set<Actor> imprimeEmpresa(){
        Set<Actor> ind = new HashSet<Actor>();
        for(Actor a : getUtilizador()){
            if(a.getClass().getSimpleName().equals("Empresa")){
                ind.add(a);
            }
            if(a.getClass().getSimpleName().equals("EmpresaInterior")){
                ind.add(a);
            }
        }
        return ind;
    }
    
    /**
     * Funcao que imprime todas as despesas
     */
    public Set<Despesa> imprimeDespesa(){
        Set<Despesa> des = new HashSet<Despesa>();
        for(Despesa d : getDespesa()){
            des.add(d);
        }
        return des;
    }

    /**
     * Funcao que da a lista das despesa com atividades economicas alteradas
     * 
     * @return  Lista de despesas com atividades corrigidas
     */
    public Set<FaturaAtividadeCorrigida> listaFaturaAtividadeCorrigida(){
        Set<FaturaAtividadeCorrigida> fac = new HashSet<FaturaAtividadeCorrigida>();
        for(FaturaAtividadeCorrigida f : this.faturaAtividadeCorrigida){
            fac.add(f);
        }
        return fac;
    }

    /**
     * Metodo que devolve o conjunto de Actores
     */
    public Set<Actor> getUtilizador(){
        return this.utilizador;
    }
    
    /**
     * Metodo que devolve o conjunto de Despesas
     */
    public Set<Despesa> getDespesa(){
        return this.despesa;
    }
    
    /**
     * Metodo clone da classe Informacao
     */
    public Informacao  clone(){
        return new Informacao(this);
    }
    
    /**
     * Procura um Actor (Individual ou Empresa) que esteja registado no sistema
     * 
     * @param   NIF   NIF do actor
     * @return  a     Devolve um Actor ou null caso nao exista
     */
    public Actor getActor(int NIF){
        for(Actor a : this.utilizador){
            if(a.getNIF()==NIF){
                return a;
            }
        }
        return null;
    }
    
    /**
     * Procura uma despeja que esteja registado no sistema
     * 
     * @param   emitenteNIF   NIF do emitente
     * @return  d             Devolve uma Despesa ou null caso nao exista
     */
    public Despesa getDespesa (int emitenteNIF){
        for(Despesa d : this.despesa){
            if(d.getEmitenteNIF()==emitenteNIF){
                return d;
            }
        }
        return null;
    }
    
    /**
     * Metodo que adiciona um utilizador ao conjunto de utilizadores
     * 
     * @param  a   Utilizador
     */
    public void adicionaUtilizador (Actor a){
        this.utilizador.add(a);
    }
    
    /**
     * Metodo que adiciona uma despesa ao conjunto de despesas
     * 
     * @param  d   Despesa
     */
    public void adicionaDespesa (Despesa d){
        this.despesa.add(d);
    }
    
    /**
     * Metodo que adiciona o registo de uma fatura corrigida
     * 
     * @param  fac   Fatura com atividade corrigida
     */
    public void adicionaFaturaAtividadeCorrigida (FaturaAtividadeCorrigida fac){
        this.faturaAtividadeCorrigida.add(fac);
    }
    
    /**
     * Metodo que remove uma utilizador do conjunto de utilizadores
     * 
     * @param  a   Utilizador
     */
    public void removeUtilizador (Actor a){
        this.utilizador.remove(a);
    }
    
    /**
     * Metodo que remove uma despesa do conjunto de despesas
     * 
     * @param  d   Despesa
     */
    public void removeDespesa (Despesa d){
        this.despesa.remove(d);
    }
 
}

