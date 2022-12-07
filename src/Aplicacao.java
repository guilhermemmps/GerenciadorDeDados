import java.util.Scanner;

public class Aplicacao {

    Scanner input = new Scanner(System.in);

    private ColecaoDados conjuntoDados;

    public Aplicacao(){
        conjuntoDados = new ColecaoDados();
    }

    String opcaoAux;
    public void executa(){
        String opcao = "1";

        do{
            menu();
            opcaoAux = opcao;
            opcao = input.nextLine();

            switch (opcao){
                case "0": System.out.println("Fim do programa.");
                break;

                case "1": System.out.println("Você selecionou para carregar dados de um arquivo texto.\nInforme o nome do arquivo:");
                        String nome = input.nextLine();
                        conjuntoDados.leDadosArquivo(nome);
                        break;
                case "2": comparador();
                break;

                case "3": boolean resultado =conjuntoDados.mostraLista();
                if(resultado==false) System.out.println("Ocorreu um erro.");
                break;

                case "4": buscaPorNome();
                break;

                case "5": salvaArquivo();
                break;

            }
        }while(!opcao.equals("0"));


    }
    public void menu(){
        System.out.println("----MENU----");
        System.out.println("Opção 0 - Encerrar o programa.");
        System.out.println("Opção 1 - Carregar dados de um arquivo texto. Insira o nome do arquivo com a extensão.");
        System.out.println("Opção 2 - Classificar dados em ordem crescente ou decrescente.");
        System.out.println("Opção 3 - Mostrar todos os dados presentes no sistema.");
        System.out.println("Opção 4 - Consultar dados por nome incompleto.");
        System.out.println("Opção 5 - Salvar dados da última consulta em arquivo texto.");
    }

    public void buscaPorNome(){
        System.out.println("Digite o nome do município que desejas buscar: ");
        String nome = input.nextLine();
        boolean resultado = conjuntoDados.buscaNomeIncompleto(nome);
        if(resultado==false){
            System.out.println("Ocorreu um erro. Município não foi encontrado.");
        }
    }

    public void salvaArquivo(){
            System.out.println("Digite o nome do arquivo (com entensão) que desejas salavar os dados da última consulta.");
            String nome = input.nextLine();
            boolean resultado = conjuntoDados.salvaDados(nome,opcaoAux);
            if(resultado) System.out.println("Os dados foram salvos.");
    }

    public void comparador(){
        String opcao;
        do {
            System.out.println("Aperte 1 para classificar em ordem crescente e 2 para ordem decrescente.");
            opcao = input.nextLine();
        }while(!opcao.equals("1") && !opcao.equals("2"));
        boolean resultado = conjuntoDados.comparador(opcao);
        if(resultado==false){
            System.out.println("Ocorreu um erro.");
        }
        else System.out.println("Dados classificados com sucesso.");
    }

}
