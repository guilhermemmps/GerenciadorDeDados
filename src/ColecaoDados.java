import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ColecaoDados {

    private ArrayList<Dados> lista;

    private ArrayList<Dados> listaDeBusca;

    public ColecaoDados() {
        lista = new ArrayList<>(1000);
    }


    public void leDadosArquivo(String nomeArquivo) {
        String uf;
        String municipio;
        String novoUf;
        String restricao;
        int quantVeiculos;
        Path path = Paths.get(nomeArquivo);

        try (Scanner scanner = new Scanner(Files.newBufferedReader(path, Charset.defaultCharset()))) {
            String token = null;
            scanner.useDelimiter("[;\\r]");
            while (scanner.hasNext()) {
                uf = scanner.next();
                novoUf = uf.replaceAll("\\n", "");
                municipio = scanner.next();
                restricao = scanner.next();
                token = scanner.next();
                quantVeiculos = Integer.parseInt(token);

                lista.add(new Dados(novoUf, municipio, restricao, quantVeiculos));

            }

        } catch (IOException e) {
            System.out.println("Erro de entrada e saída." + e.toString());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro." + e.toString());
        }
    }

    public boolean mostraLista() {
        if(lista.isEmpty()) return false;
        for (Dados d : lista) {
            System.out.println(d.toString());
        }
        return true;
    }


    public boolean buscaNomeIncompleto(String nome) {
        listaDeBusca = new ArrayList<>();

        for (Dados d : lista) {
            if (d.getMunicipio().contains(nome)) {
                listaDeBusca.add(d);
            }
        }
        if (listaDeBusca.isEmpty()) return false;

        System.out.println("Dados encontrados com o respectivo nome do município: ");
        for (Dados d2 : listaDeBusca) {
            System.out.println(d2.toString());
        }
        return true;
    }

    public boolean salvaDados(String nome, String opcao) {
        Path path = Paths.get(nome);
        if (opcao.equals("4")) {
            if (listaDeBusca.isEmpty()) {
                System.out.println("Ocoreu um erro.");
                return false;
            }
            try {
                BufferedWriter writer = Files.newBufferedWriter(path, Charset.defaultCharset());
                PrintWriter pw = new PrintWriter(writer);
                for (Dados d : listaDeBusca) {
                    pw.format("%s;%s;%s;%d%n", d.getMunicipio(), d.getUf(), d.getRestricao(), d.getQuantVeiculos());
                }
                pw.close();
                return true;
            } catch (IOException e) {
                System.out.println("Ocorreu um erro." + e.toString());
            }
        }
        if (lista.isEmpty()) {
            System.out.println("Ocorreu um erro.");
            return false;
        }
        try {
            BufferedWriter writer = Files.newBufferedWriter(path, Charset.defaultCharset());
            PrintWriter pw = new PrintWriter(writer);
            for (Dados d : lista) {
                pw.format("%s;%s;%s;%d%n", d.getMunicipio(), d.getUf(), d.getRestricao(), d.getQuantVeiculos());
            }
            pw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Ocorreu um erro." + e.toString());
        }
        return false;
    }


    public boolean comparador(String opcao) {
        if(lista.isEmpty()) return false;
        if (opcao.equals("1")) {
            Collections.sort(lista, new Comparator<Dados>() {
                @Override
                public int compare(Dados d1, Dados d2) {
                    return d1.getMunicipio().compareTo(d2.getMunicipio());
                }
            });
            return true;
            //Collections.sort(lista, (Dados d1, Dados d2) -> d1.getMunicipio().compareTo(d2.getMunicipio()));
        }
        Collections.sort(lista, new Comparator<Dados>() {
            @Override
            public int compare(Dados d1, Dados d2) {
                return d2.getMunicipio().compareTo(d1.getMunicipio());
            }
        });
        return true;
        //Collections.sort(lista, (Dados d1, Dados d2) -> d2.getMunicipio().compareTo(d1.getMunicipio()));
    }

}



