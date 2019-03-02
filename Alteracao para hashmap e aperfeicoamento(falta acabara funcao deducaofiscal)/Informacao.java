import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;


/**
 * Classe responsavel por guardar a informacao num ficheiro
 *
 * @autor Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 */
public class Informacao implements Serializable
{
    int numeroFatura;
    private Map<Integer, Utilizador> utilizadores;
    private Map<Integer, Fatura> faturas;
    private Map<Integer, FaturaAtividadeCorrigida> faturasAtividadeCorrigida;

    public Informacao(){
        numeroFatura = 0;
        this.utilizadores = new HashMap<Integer, Utilizador>();
        this.faturas = new HashMap<Integer, Fatura>();
        this.faturasAtividadeCorrigida = new HashMap<Integer, FaturaAtividadeCorrigida>();
    }

    /**
     * Construtor parametrizado da classe Informacao
     *
     * @param  numeroFatura    Numero de despesa criadas
     * @param  u               Conjunto de Actores
     * @param  f               Conjunto de despesas
     * @param  fac             Conjunto de faturas com atividades economicas corrigidas
     */
    public Informacao (int numeroFatura, Collection<Utilizador> u, Collection<Fatura> f, Collection<FaturaAtividadeCorrigida> fac){
        this.numeroFatura = numeroFatura;
        this.utilizadores = new HashMap<Integer, Utilizador>(u.size());
        for(Utilizador a : u){
            this.utilizadores.put(a.getNIF(), a.clone());
        }
        this.faturas = new HashMap<Integer, Fatura>(f.size());
        for(Fatura a : f){
            this.faturas.put(a.getCodigo(), a.clone());
        }
        this.faturasAtividadeCorrigida = new HashMap<Integer, FaturaAtividadeCorrigida>(fac.size());
        for(FaturaAtividadeCorrigida a : fac){
            this.faturasAtividadeCorrigida.put(a.getCodigo(), a.clone());
        }
    }

    /**
     * Construtor de copia da classe Informacao
     */
    public Informacao (Informacao i){
        this.numeroFatura = i.getNumeroFatura();
        this.utilizadores = i.getUtilizadores();
        this.faturas = i.getFaturas();
    }

    /**
     * Metodo que devolve o numero de despesa criadas
     *
     * @return  Numero de despesas criadas
     */
    public int getNumeroFatura(){
        return this.numeroFatura;
    }

    /**
     * Metodo que define o numero de despesas criadas
     *
     * @param  numeroFatura   Novo numero de despesa
     */
    public void setNumeroFatura (int numeroFatura){
        this.numeroFatura = numeroFatura;
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
     * @param  u   Utilizador que verifica as suas despesas
     */
    public Set<Fatura> verificarDespesas (Utilizador u){

        Set<Fatura> des = new HashSet<Fatura>();
        Iterator<Map.Entry<Integer, Fatura>> it = this.faturas.entrySet().iterator();
        while(it.hasNext()){
            if((it.next().getValue().getClienteNIF()) == u.getNIF()){
                des.add(it.next().getValue());
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
        Set<AtividadeEconomica> caeIndividual = ui.getAtividadeEconomica();
        Iterator<Map.Entry<Integer, Fatura>> it = this.faturas.entrySet().iterator();
        while(it.hasNext()){
            if(it.next().getValue().getClienteNIF() == ui.getNIF()){
                for(AtividadeEconomica cae : caeIndividual){
                    if(cae.getClass().getSimpleName().equals(it.next().getValue().getClass().getSimpleName())){
                        if(ui.getClass().getSimpleName().equals("Individual")){
                            deducao += cae.valorDeducao() * it.next().getValue().getValorFatura();
                            for (Integer af : ui.getNIFAgregadoFamiliar()){
                                Iterator<Map.Entry<Integer, Utilizador>> itaf = this.utilizadores.entrySet().iterator();
                                while(itaf.hasNext()){
                                    if(itaf.next().getValue().getNIF() == af){
                                        Iterator<Map.Entry<Integer, Fatura>> itaffat = this.faturas.entrySet().iterator();
                                            while(itaffat.hasNext()){
                                                if(it.next().getValue().getClienteNIF() == af){
                                                    Set<AtividadeEconomica> ataf =
                                                    for(AtividadeEconomica cae : caeIndividual){
                                                        if(cae.getClass().getSimpleName().equals(it.next().getValue().getClass().getSimpleName())){
                                                }
                                            }
                                    }
                                }
                            }
                        }
                        if((ui.getClass().getSimpleName().equals("Individual"))){
                            deducao += (cae.valorDeducao() + 0.05*ui.getNumeroFilhos()) * it.next().getValue().getValorFatura();
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
     * @param  u   Utilizador que vai corrigir a classificacao de atividade economica
     */
    public void corrigirClassificacaoAtividadeEconomicaDespesaIndividual (Utilizador u){
        AtividadeEconomica atividadeNova = new NaoValidada();
        Scanner input = new Scanner(System.in);
        System.out.println("Codigo da despesa: ");
        int codigoDespesa = input.nextInt();
        Iterator<Map.Entry<Integer, Fatura>> it = this.faturas.entrySet().iterator();
        while(it.hasNext()){
            try{
                if(((it.next().getValue().getCodigo()) == codigoDespesa) && ((it.next().getValue().getClienteNIF()) == u.getNIF())){
                    AtividadeEconomica atividadeAntiga = it.next().getValue().getAtividadeEconomica();
                    Menu.menuCodigoAtividadeEconomica();
                    int escolha = input.nextInt();
                    switch (escolha){
                        case 1:
                            atividadeNova = new Construcao();
                            break;
                        case 2:
                            atividadeNova = new Transporte();
                            break;
                        case 3:
                            atividadeNova = new Alimento();
                            break;
                        case 4:
                            atividadeNova = new Saude();
                            break;
                        case 5:
                            atividadeNova = new Educacao();
                            break;
                    }
                    FaturaAtividadeCorrigida fac = new FaturaAtividadeCorrigida(codigoDespesa, atividadeAntiga, atividadeNova);
                    adicionaFaturaAtividadeCorrigida(fac.clone());
                }
            } catch(Exception e){
                System.out.println("Despesa nao existe ou nao esta associada a si");
                corrigirClassificacaoAtividadeEconomicaDespesaIndividual(u);
            }
        }
    }

    /**
     * Funcao que permite associar uma atividade economica a uma despesa nao validada
     */
    public void associarAtividadeEconomica (Utilizador u){
        AtividadeEconomica atividadeNova = new NaoValidada();
        Scanner input = new Scanner(System.in);
        System.out.println("Codigo da despesa: ");
        int codigoDespesa = input.nextInt();
        Iterator<Map.Entry<Integer, Fatura>> it = this.faturas.entrySet().iterator();
        while(it.hasNext()){
            try{
                if(((it.next().getValue().getCodigo()) == codigoDespesa) && (it.next().getValue().getClienteNIF() == u.getNIF()) && (it.next().getValue().getClass().getSimpleName().equals("NaoValidada"))) {
                    Menu.menuCodigoAtividadeEconomica();
                    int escolha = input.nextInt();
                    switch (escolha) {
                        case 1:
                            atividadeNova = new Construcao();
                            break;
                        case 2:
                            atividadeNova = new Transporte();
                            break;
                        case 3:
                            atividadeNova = new Alimento();
                            break;
                        case 4:
                            atividadeNova = new Saude();
                            break;
                        case 5:
                            atividadeNova = new Educacao();
                            break;
                    }
                }
                it.next().getValue().setAtividadeEconomica(atividadeNova);
            }catch(Exception e){
                System.out.println("Despesa nao existe ou nao esta associada a si");
                corrigirClassificacaoAtividadeEconomicaDespesaIndividual(u);
            }
        }
    }

    //--------------
    //OPCOES EMPRESA
    //--------------
    /**
     * Funcao para um utilizador criar uma factura
     *
     * @param  ue   Utilizador que vai criar a factura
     */
    public void criarFactura (Empresa ue){

        Scanner input = new Scanner(System.in);
        boolean f = true;
        LocalDate dataDespesa = null;
        int codigo = getNumeroFatura()+1;
        setNumeroFatura(codigo);
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
        AtividadeEconomica atividadeEconomica = new NaoValidada();
        int escolha = input.nextInt();
        switch (escolha){
            case 1:
                atividadeEconomica = new Construcao();
                break;
            case 2:
                atividadeEconomica = new Transporte();
                break;
            case 3:
                atividadeEconomica = new Alimento();
                break;
            case 4:
                atividadeEconomica = new Saude();
                break;
            case 5:
                atividadeEconomica = new Educacao();
                break;
            case 6:
                atividadeEconomica = new NaoValidada();
                break;
        }
        System.out.println ("Valor da despesa: ");
        Double valorDespesa = input.nextDouble();
        Fatura des = new Fatura(codigo, emitenteNIF, designacaoEmitente, dataDespesa, clienteNIF, designacaoDespesa, atividadeEconomica, valorDespesa);
        adicionaDespesa(des);

        Iterator<Map.Entry<Integer, Utilizador>> it = this.utilizadores.entrySet().iterator();
        while(it.hasNext()){
            if(it.next().getValue().getClass().getSimpleName().equals("Individual") || it.next().getValue().getClass().getSimpleName().equals("IndividualNumerosa")){
                if(it.next().getValue().getNIF() == clienteNIF){
                    it.next().getValue().setValorGasto(it.next().getValue().getValorGasto() + valorDespesa);
                }
            }
        }
    }

    /**
     * Mostra a lista de despesas associadas a uma empresa ordenada por data
     *
     * @param  ue   Empresa cujas despesas estao associadas
     */
    public Set<Fatura> listaDespesaOrdenadaData (Empresa ue){
        Set h = new TreeSet<Fatura>(new Comparator<Fatura>() {
            public int compare(Fatura f1, Fatura f2) {
                if (f1.getDataFatura().isAfter(f2.getDataFatura())) return 1;
                else return -1;
            }
        });
        Iterator<Map.Entry<Integer, Fatura>> it = this.faturas.entrySet().iterator();
        while(it.hasNext()){
            if(it.next().getValue().getEmitenteNIF()==ue.getNIF()){
                h.add(it.next().getValue().clone());
            }
        }
        return h;
    }

    /**
     * Lista de despesas associadas a uma empresa ordenadas por valor
     *
     * @param  ue   Empresa cujas despesas estÃ£o associadas
     */
    public Set<Fatura> listaDespesaOrdenadaValor (Empresa ue){
        Set h = new TreeSet<Fatura>(new Comparator<Fatura>() {
            public int compare(Fatura f1, Fatura f2) {
                if (f1.getValorFatura() > f2.getValorFatura()) return 1;
                else if (f1.getValorFatura() < f2.getValorFatura()) return -1;
                else return 0;
            }
        });
        Iterator<Map.Entry<Integer, Fatura>> it = this.faturas.entrySet().iterator();
        while(it.hasNext()) {
            if ((it.next().getValue().getEmitenteNIF()) == ue.getNIF()) {
                h.add(it.next().getValue().clone());
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
    public Set<Fatura> listaDespesaContribuinteEntreData(Empresa ue, int clienteNIF){
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

        Set<Fatura> des = new HashSet<Fatura>();
        Iterator<Map.Entry<Integer, Fatura>> it = this.faturas.entrySet().iterator();
        while(it.hasNext()){
            LocalDate data = it.next().getValue().getDataFatura();
            if(it.next().getValue().getClienteNIF()==clienteNIF && inicio.isBefore(data) && fim.isAfter(data)){
                des.add(it.next().getValue().clone());
            }
        }
        return des;
    }

    /**
     * Mostra a lista de despesas por parte de uma empresa por contribuinte por valor da despesa por ordem decrescente
     *
     * @param  ue           Empresa que verifica as despesas
     * @param  clienteNIF   NIF do contribuinte individual que vai ter as despesas verificadas
     */
    public Set<Fatura> listaDespesaContribuinteOrdenadaDecrescenteDespesa (Empresa ue, int clienteNIF){
        Set<Fatura> des = new TreeSet<Fatura>(new Comparator<Fatura>(){
            public int compare (Fatura f1, Fatura f2){
                if (f1.getValorFatura() > f2.getValorFatura()) return -1;
                else if (f1.getValorFatura() < f2.getValorFatura()) return 1;
                else return 0;
            }
        });
        Iterator<Map.Entry<Integer, Fatura>> it = this.faturas.entrySet().iterator();
        while(it.hasNext()){
            if(it.next().getValue().getClienteNIF() == clienteNIF){
                des.add(it.next().getValue());
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
        Iterator<Map.Entry<Integer, Fatura>> it = this.faturas.entrySet().iterator();
        while(it.hasNext()){
            LocalDate data = it.next().getValue().getDataFatura();
            if(inicio.isBefore(data) && fim.isAfter(data)){
                tf+=it.next().getValue().getValorFatura();
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
        TreeSet ind = new TreeSet<Individual>(new Comparator<Individual>(){
            public int compare (Individual i1, Individual i2){
                if (i1.getValorGasto() > i2.getValorGasto()) return 1;
                else if (i1.getValorGasto() < i2.getValorGasto()) return -1;
                else return 0;
            }
        });
        Iterator<Map.Entry<Integer, Utilizador>> it = this.utilizadores.entrySet().iterator();
        while(it.hasNext()){
            if(it.next().getValue().getClass().getSimpleName().equals("Individual") || it.next().getValue().getClass().getSimpleName().equals("IndividualNumerosa")){
                ind.add(it.next().getValue().clone());
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
        TreeSet emp = new TreeSet<Empresa>(new Comparator<Empresa>(){
            public int compare (Empresa e1, Empresa e2){
                if (e1.getNumeroFaturas() > e2.getNumeroFaturas()) return 1;
                else if (e1.getNumeroFaturas() < e2.getNumeroFaturas()) return -1;
                else return 0;
            }
        });
        Iterator<Map.Entry<Integer, Utilizador>> it = this.utilizadores.entrySet().iterator();
        while(it.hasNext()){
            if(it.next().getValue().getClass().getSimpleName().equals("Empresa") || it.next().getValue().getClass().getSimpleName().equals("EmpresaInterior")) {
                emp.add(it.next().getValue().clone());
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
    public double montanteDeducaoFiscal (int x){
        TreeSet<Empresa> emp = XEmpresasMaisFaturas(x);
        double deducao = 0.0;
        Iterator<Map.Entry<Integer, Fatura>> it = this.faturas.entrySet().iterator();
        while(it.hasNext()){
            for(Empresa xempfat : emp) {
                if (xempfat.getClass().getSimpleName().equals("Empresa")) {
                    if (it.next().getValue().getEmitenteNIF() == xempfat.getNIF()) {
                        deducao += it.next().getValue().getAtividadeEconomica().valorDeducao(it.next().getValue().getValorFatura());
                    }
                }
                if (xempfat.getClass().getSimpleName().equals(("EmpresaInterior"))) {
                    if (it.next().getValue().getEmitenteNIF() == xempfat.getNIF()) {
                        deducao += it.next().getValue().getAtividadeEconomica().valorDeducao((it.next().getValue().getValorFatura()));





                    }
                }
            }
        }
        return deducao;
    }





    /**
     * Funcao que imprime todos os utilizadores individuais
     */
    public Set<Utilizador> imprimeIndividual(){
        Set<Utilizador> ind = new HashSet<Utilizador>();
        Iterator<Map.Entry<Integer, Utilizador>> it = this.utilizadores.entrySet().iterator();
        while(it.hasNext()){
            if(it.next().getClass().getSimpleName().equals("Individual")){
                ind.add(it.next().getValue());
            }
            if(it.next().getClass().getSimpleName().equals("IndividualNumerosa")){
                ind.add(it.next().getValue());
            }
        }
        return ind;
    }

    /**
     * Funcao que imprime todas as empresas
     */
    public Set<Utilizador> imprimeEmpresas(){
        Set<Utilizador> emp = new HashSet<Utilizador>();
        Iterator<Map.Entry<Integer, Utilizador>> it = this.utilizadores.entrySet().iterator();
        while(it.hasNext()){
            if(it.next().getValue().getClass().getSimpleName().equals("Empresa")){
                emp.add(it.next().getValue());
            }
            if(it.next().getValue().getClass().getSimpleName().equals("EmpresaInterior")){
                emp.add(it.next().getValue());
            }
        }
        return emp;
    }

    /**
     * Funcao que imprime todas as faturas
     */
    public Set<Fatura> imprimeFaturas(){
        Set<Fatura> fat = new HashSet<Fatura>();
        Iterator<Map.Entry<Integer, Fatura>> it = this.faturas.entrySet().iterator();
        while(it.hasNext()){
            fat.add(it.next().getValue().clone());
        }
        return fat;
    }

    /**
     * Funcao que da a lista das despesa com atividades economicas alteradas
     *
     * @return  Lista de despesas com atividades corrigidas
     */
    public Set<FaturaAtividadeCorrigida> listaFaturasAtividadeCorrigida(){
        Set<FaturaAtividadeCorrigida> fac = new HashSet<FaturaAtividadeCorrigida>();
        Iterator<Map.Entry<Integer, FaturaAtividadeCorrigida>> it = this.faturasAtividadeCorrigida.entrySet().iterator();
        while(it.hasNext()){
            fac.add(it.next().getValue().clone());
        }
        return fac;
    }

    /**
     * Metodo que devolve o conjunto de Actores
     */
    public Map<Integer, Utilizador> getUtilizadores(){
        return this.utilizadores;
    }

    /**
     * Metodo que devolve o conjunto de Despesas
     */
    public Map<Integer, Fatura> getFaturas(){
        return this.faturas;
    }

    /**
     * Procura um Actor (Individual ou Empresa) que esteja registado no sistema
     *
     * @param   NIF   NIF do actor
     * @return  a     Devolve um Actor ou null caso nao exista
     */
    public Utilizador getUtilizador(int NIF){
        return this.utilizadores.get(NIF);
    }

    /**
     * Metodo que adiciona um utilizador ao conjunto de utilizadores
     *
     * @param  u   Utilizador
     */
    public void adicionaUtilizador (Utilizador u){
        this.utilizadores.put(u.getNIF(), u.clone());
    }

    /**
     * Metodo que adiciona uma despesa ao conjunto de despesas
     *
     * @param  f   Despesa
     */
    public void adicionaDespesa (Fatura f){
        this.faturas.put(f.getCodigo(), f.clone());
    }

    /**
     * Metodo que adiciona o registo de uma fatura corrigida
     *
     * @param  fac   Fatura com atividade corrigida
     */
    public void adicionaFaturaAtividadeCorrigida (FaturaAtividadeCorrigida fac){
        this.faturasAtividadeCorrigida.put(fac.getCodigo(), fac.clone());
    }

    /**
     * Metodo clone da classe Informacao
     */
    public Informacao  clone(){
        return new Informacao(this);
    }
}

