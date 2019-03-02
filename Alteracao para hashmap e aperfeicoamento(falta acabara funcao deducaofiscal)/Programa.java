import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Classe responsavel por guardar a informacao num ficheiro e pelo menu
 *
 * @author Filipe Barbosa A77252, Hugo Ferreira A78555, Nuno Morais A77368
 * @version 2018
 */
public class Programa
{
    /**
     * Funcao principal
     */
    public void main() throws Exception{
        Informacao i= new Informacao();
        try{
            i = i.carregaObjetoInformacao();
        }catch(Exception e){
            System.out.println("Erro a carregar");
        }
        executa(i);
        try{
            i.gravaObjetoInformacao();
        }catch(Exception e){
            System.out.println("Erro a guardar");
            return;
        }
    }

    /**
     * Funcao que executa o Programa
     */
    private void executa(Informacao i) throws Exception{
        Scanner input = new Scanner(System.in);
        int x = -1;
        limparEcra();
        Menu.menuPrincipal();
        while(x!=0 && x!=1 && x!=2){
            x = input.nextInt();
        }
        limparEcra();
        switch(x){
            case(1):
                menuEntrar(i);
                break;
            case(2):
                registarUtilizador(i);
                break;
            case(0):
                break;
        }
    }

    /**
     * Funcao responsavel pelo menu de entrada
     *
     * @param  i   Estado atual da informacao
     */
    public void menuEntrar (Informacao i) throws Exception{
        Scanner input = new Scanner(System.in);
        int x = -1;
        limparEcra();
        Menu.menuEntrar();
        while(x!=0 && x!=1 && x!=2){
            x = input.nextInt();
        }
        limparEcra();
        switch(x){
            case(1):
                try{
                    entrarUtilizador(i);
                }
                catch(Exception e){
                    System.out.println("NIF ou Password errados");
                    entrarUtilizador(i);
                }
                break;
            case(2):
                try{
                    entrarAdministrador(i);
                }
                catch(Exception e){
                    System.out.println("Password errada");
                    entrarAdministrador(i);
                }
                break;
            case(0):
                limparEcra();
                executa(i);
                break;
        }
    }

    /**
     * Funcao responsavel pela entrada do utilizador na aplicacao
     *
     * @param  i   Estado atual da Informacao
     */
    private void entrarUtilizador (Informacao i) throws Exception{
        Scanner input = new Scanner(System.in);
        limparEcra();
        System.out.println("NIF: ");
        int x = 0;
        Utilizador a = null;
        x = input.nextInt();
        a = i.getUtilizador(x);
        System.out.println("Password: ");
        int y = 0;
        y = input.nextInt();
        while(!(a.getPassword()==(y))){
            y = input.nextInt();
        }
        limparEcra();
        if(a.getClass().getSimpleName().equals("Individual") || a.getClass().getSimpleName().equals("IndividualNumerosa")){
            opcoesIndividual((Individual)a,i);
        }
        else if(a.getClass().getSimpleName().equals("Empresa") || a.getClass().getSimpleName().equals(("EmpresaInterior"))){
            opcoesEmpresa((Empresa)a,i);
        }
    }

    /**
     * Funcao responsavel pelo menu de contribuintes individuais
     *
     * @param  ui   Objecto tipo Individual
     * @param  i    Estado atual da Informacao
     */
    private void opcoesIndividual (Individual ui, Informacao i) throws Exception{
        Scanner input = new Scanner(System.in);
        limparEcra();
        int x=-1;
        Menu.menuOpcoesIndividual();
        while(x!=0 && x!=1 && x!=2 && x!=3 && x!=4){
            x = input.nextInt();
        }
        limparEcra();
        switch(x){
            case(1):
                for(Fatura d : i.verificarDespesas(ui)){
                    System.out.println(d.toString());
                }
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                opcoesIndividual(ui, i);
                break;
            case(2):
                System.out.println(i.verificarMontanteDeducaoFiscal(ui));
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                opcoesIndividual(ui, i);
                break;
            case(3):
                i.corrigirClassificacaoAtividadeEconomicaDespesaIndividual(ui);
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                opcoesIndividual(ui, i);
                break;
            case(4):
                i.associarAtividadeEconomica(ui);
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                opcoesIndividual(ui, i);
            case(0):
                executa(i);
                break;
        }
    }

    /**
     * Funcao responsavel do menu de contribuintes Empresariais
     *
     * @param  ue   Objecto tipo Empresa
     * @param  i    Estado atual da Informacao
     */
    private void opcoesEmpresa (Empresa ue, Informacao i) throws Exception{
        Scanner input = new Scanner(System.in);
        int x=-1;
        limparEcra();
        Menu.menuOpcoesEmpresa();
        while(x!=0 && x!=1 && x!=2 && x!=3 && x!=4 && x!=5 && x!=6 && x!=7){
            x = input.nextInt();
        }
        limparEcra();
        switch(x){
            case(1):
                i.criarFactura(ue);
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                opcoesEmpresa(ue, i);
                break;
            case(2):
                for(Fatura d : i.listaDespesaOrdenadaData(ue)){
                    System.out.println(d.toString());
                }
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                opcoesEmpresa(ue, i);
                break;
            case(3):
                for(Fatura des : i.listaDespesaOrdenadaValor(ue)){
                    System.out.println(des.toString());
                }
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                opcoesEmpresa(ue, i);
                break;
            case(4):
                System.out.println("NIF do contribuinte: ");
                int clienteNIF = input.nextInt();
                for(Fatura desp : i.listaDespesaContribuinteEntreData(ue, clienteNIF)){
                    System.out.println(desp.toString());
                }
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                opcoesEmpresa(ue, i);
                break;
            case(5):
                System.out.println("NIF do contribuinte: ");
                int cliNIF = input.nextInt();
                for(Fatura despe : i.listaDespesaContribuinteOrdenadaDecrescenteDespesa(ue, cliNIF)){
                    System.out.println(despe.toString());
                }
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                opcoesEmpresa(ue, i);
                break;
            case(6):
                System.out.println(i.totalFaturadoPeriodo(ue));
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                opcoesEmpresa(ue, i);
                break;
            case(0):
                executa(i);
                break;
        }
    }

    /**
     * Funcao responsavel pelo menu principal de admin
     *
     * @param  i    Estado atual da Informacao
     */
    private void entrarAdministrador (Informacao i) throws Exception{
        Scanner input = new Scanner(System.in);
        int y=0;
        limparEcra();
        while(y!=12345){
            System.out.println("Password: ");
            y=input.nextInt();
        }
        limparEcra();
        menuOpcoesAdministrador(i);
    }

    /**
     * Funcao responsavel pelas opcoes do admin
     */
    private void menuOpcoesAdministrador(Informacao i) throws Exception{
        int x=-1;
        Scanner input = new Scanner(System.in);
        Menu.menuOpcoesAdministrador();
        while(x!=0 && x!=1 && x!=2 && x!=3 && x!=4 && x!=5 && x!=6 && x!=7){
            x = input.nextInt();
        }
        switch(x){
            case(1):
                for(Individual a : i.relacaoDezContribuintesMaisGastadores()){
                    System.out.println(a.toString());
                }
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                menuOpcoesAdministrador(i);
                break;
            case(2):
                System.out.println("Numero de empresas: ");
                int a = input.nextInt();
                for(Empresa acto : i.XEmpresasMaisFaturas(a)){
                    System.out.println(acto.toString());
                }
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                menuOpcoesAdministrador(i);
                break;
            case(3):
                for(Utilizador ac : i.imprimeIndividual()){
                    System.out.println(ac.toString());
                }
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                menuOpcoesAdministrador(i);
                break;
            case(4):
                for(Utilizador act : i.imprimeEmpresas()){
                    System.out.println(act.toString());
                }
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                menuOpcoesAdministrador(i);
                break;
            case(5):
                for(Fatura d : i.imprimeFaturas()){
                    System.out.println(d.toString());
                }
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                menuOpcoesAdministrador(i);
                break;
            case(6):
                for(FaturaAtividadeCorrigida fac : i.listaFaturasAtividadeCorrigida()){
                    System.out.println(fac.toString());
                }
                System.out.println("\n\n0 - Voltar");
                x = input.nextInt();
                while(x!=0){
                    x = input.nextInt();
                }
                menuOpcoesAdministrador(i);
                break;
            case(0):
                executa(i);
                break;
        }
    }

    /**
     * Funcao para registo de contribuintes individuais e empresas
     *
     * @param  i   Estado atual da Informacao
     */
    private void registarUtilizador (Informacao i) throws Exception{
        Scanner input = new Scanner(System.in);
        limparEcra();
        Menu.menuRegistarUtilizador();
        int x = input.nextInt();
        if(x==0){
            executa(i);
        }
        if(x>0 && x<=2){
            limparEcra();
            System.out.println("NIF: ");
            int NIF = input.nextInt();
            System.out.println("Email: ");
            String email = input.next();
            System.out.println("Nome: ");
            String nome = input.next();
            System.out.println("Morada:" );
            String morada = input.next();
            System.out.println("Password: ");
            int password = input.nextInt();
            int valorGasto = 0;
            switch(x){
                case(1):
                    System.out.println("Numero do agregado familiar: ");
                    int numeroAgregadoFamiliar = input.nextInt();
                    System.out.println("NIF dos elementos do agregado familiar: ");
                    ArrayList<Integer> NIFAgregadoFamiliar = new ArrayList<Integer>();
                    for(int a=0; a<numeroAgregadoFamiliar; a++){
                        int NIFElementoAgregadoFamiliar = input.nextInt();
                        NIFAgregadoFamiliar.add(NIFElementoAgregadoFamiliar);
                    }
                    System.out.println("Numero filhos: ");
                    int numeroFilhos = input.nextInt();
                    double coeficienteFiscal = 0.0;
                    System.out.println("Numero de atividades economicas: ");
                    int numeroAtividadeEconomica = input.nextInt();
                    System.out.println("Atividade economica: ");
                    Menu.menuCodigoAtividadeEconomica();
                    Set<AtividadeEconomica> atividadeEconomica = new HashSet<AtividadeEconomica>(numeroAtividadeEconomica);
                    for(int j=0; j<numeroAtividadeEconomica; j++){
                        Menu.menuCodigoAtividadeEconomica();
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
                                atividadeEconomica.add(new Saude());;
                                break;
                            case 5:
                                atividadeEconomica.add(new Educacao());
                                break;
                            case 6:
                                atividadeEconomica.add(new NaoValidada());
                                break;
                        }
                    }

                    Individual ind = new Individual(NIF, email, nome, morada, password, valorGasto, numeroAgregadoFamiliar, numeroFilhos, NIFAgregadoFamiliar, coeficienteFiscal, atividadeEconomica);
                    i.adicionaUtilizador(ind);
                    break;

                case(2):
                    System.out.println("CodigoEmpresa: ");
                    int codigoEmpresa = input.nextInt();
                    System.out.println("Numero de atividades economicas: ");
                    int numAtiEco = input.nextInt();
                    System.out.println("Atividade economica: ");
                    Menu.menuCodigoAtividadeEconomica();
                    Set<AtividadeEconomica> atiEco = new HashSet<AtividadeEconomica>(numAtiEco);
                    for(int j=0; j<numAtiEco; j++){
                        int escolha = input.nextInt();
                        String ae = "";
                        switch (escolha){
                            case 1:
                                atiEco.add(new Construcao());
                                break;
                            case 2:
                                atiEco.add(new Transporte());
                                break;
                            case 3:
                                atiEco.add(new Alimento());
                                break;
                            case 4:
                                atiEco.add(new Saude());;
                                break;
                            case 5:
                                atiEco.add(new Educacao());
                                break;
                            case 6:
                                atiEco.add(new NaoValidada());
                                break;
                        }
                    }
                    System.out.println("Fator de deducao fiscal: ");
                    Double fatorDeducaoFiscal = input.nextDouble();
                    int numeroFaturas = 0;
                    Empresa emp = new Empresa(NIF, email, nome, morada, password, valorGasto, codigoEmpresa, atiEco, fatorDeducaoFiscal, numeroFaturas);
                    i.adicionaUtilizador(emp);
                    break;
            }
        }
        executa(i);
    }

    /**
     * Funcao que limpaa o ecra
     */
    private void limparEcra(){
        System.out.print('\f');
    }
}
