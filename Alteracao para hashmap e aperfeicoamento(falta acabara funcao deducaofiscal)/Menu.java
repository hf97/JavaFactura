public class Menu
{
    public static void menuPrincipal(){
        System.out.println(
                "1 - Entrar utilizador\n"+
                "2 - Registar utilizador\n"+
                "\n0 - Sair");
    }
    public static void menuEntrar(){
        System.out.println(
                "1 - Entrar utilizador\n"+
                "2 - Entrar administrador\n"+
                "\n0 - Voltar");
    }
    public static void menuOpcoesIndividual(){
        System.out.println(
                "1 - Verificar despesas\n"+
                "2 - Verificar montante de deducao fiscal\n"+
                "3 - Corrigir a classificacao de actividade economica de um documento de despesa\n"+
                "4 - Associar atividade economica\n"+
                "\n0 - Voltar");
    }
    public static void menuOpcoesEmpresa(){
        System.out.println(
                "1 - Criar fatura\n"+
                "2 - Lista de faturas ordenada por data de emissao\n"+
                "3 - Lista de faturas ordenada por valor\n"+
                "4 - Lista de faturas por contribuinte num intervalo de datas\n"+
                "5 - Lista de faturas por contribuinte ordenadas por valor decrescente de despesa\n"+
                "6 - Total faturado num determinado periodo\n"+
                "7 - Corrigir a classificacao de actividade economica de um documento de despesa\n"+
                "\n0 - Voltar");
    }
    public static void menuOpcoesAdministrador(){
        System.out.println(
                "1 - Relacao dos 10 contribuintes que mais gastam no sistema\n"+
                "2 - Relacao das X empresas com mais facturas e montante de deducoes fiscais\n"+
                "3 - Lista de todos os contribuintes individuas\n"+
                "4 - Lista de todas as empresas\n"+
                "5 - Lista de todas as despesas\n"+
                "6 - Faturas com atividades economicas corrigidas\n"+
                "\n0 - Voltar");
    }
    public static void menuRegistarUtilizador(){
        System.out.println(
                "1 - Contribuinte indivual\n"+
                "2 - Empresa\n"+
                "\n0 - Voltar");
    }
    public static void menuCodigoAtividadeEconomica(){
        System.out.println(
                "1 - Construcao\n" +
                "2 - Transporte\n" +
                "3 - Alimento\n" +
                "4 - Saude\n" +
                "5 - Educacao\n" +
                "6 - Nao validada\n");
    }
}
