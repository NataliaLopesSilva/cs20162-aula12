package com.github.natalialopessilva.qp;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Relatorio {

    private int testesTotais;
    private int corretos;
    private int falhas;
    private long tempoExecucao;
    private long tempoMedio;
    private long memoriaInicial;
    private long memoriaFinal;

    public int getTestesTotais() {
        return testesTotais;
    }

    public void setTestesTotais(int testesTotais) {
        this.testesTotais = testesTotais;
    }

    public int getCorretos() {
        return corretos;
    }

    public void setCorretos(int corretos) {
        this.corretos = corretos;
    }

    public int getFalhas() {
        return falhas;
    }

    public void setFalhas(int falhas) {
        this.falhas = falhas;
    }

    public long getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(long tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    public long getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(long tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public long getMemoriaInicial() {
        return memoriaInicial;
    }

    public void setMemoriaInicial(long memoriaInicial) {
        this.memoriaInicial = memoriaInicial;
    }

    public long getMemoriaFinal() {
        return memoriaFinal;
    }

    public void setMemoriaFinal(long memoriaFinal) {
        this.memoriaFinal = memoriaFinal;
    }

    private void gerarArquivoHtml(String diretorio, Relatorio relatorio, List<Expressoes> listaExpressoes) throws IOException {
        List<String> arquivo = new ArrayList<>();

        arquivo.add("<!DOCTYPE html>");
        arquivo.add("<html>");
        arquivo.add("");
        arquivo.add("<head>");
        arquivo.add("<meta charset=\"UTF-8\">");
        arquivo.add(" <style>\n"
                + "            table {\n"
                + "                font-family: arial, sans-serif;\n"
                + "                border-collapse: collapse;\n"
                + "                width: 100%;\n"
                + "            }\n"
                + "\n"
                + "            th {\n"
                + "                border: 1px solid #dddddd;\n"
                + "                text-align: center;\n"
                + "                padding: 8px;\n"
                + "                background-color: #C5CAE9;\n"
                + "            }\n"
                + "\n"
                + "            td  {\n"
                + "                border: 1px solid #dddddd;\n"
                + "                text-align: center;\n"
                + "                padding: 8px;\n"
                + "            }\n"
                + "\n"
                + "            #tituloAzul{\n"
                + "                border: 1px solid #dddddd;\n"
                + "                text-align: center;\n"
                + "                background-color: #1A237E;\n"
                + "            }\n"
                + "\n"
                + "            #subTituloAzul{\n"
                + "                background-color: #1A237E;\n"
                + "            }\n"
                + "\n"
                + "        </style>");
        arquivo.add("</head>");
        arquivo.add("");
        arquivo.add("<body>");
        arquivo.add("<table>");
        arquivo.add("<tr>\n"
                + "                <th id=\"tituloAzul\" style=\"text-align: center;\">\n"
                + "                    <h2 style=\"color: white\"> RELATÓRIO DE TESTES</h3> \n"
                + "                </th>\n"
                + "            </tr>");
        arquivo.add("</table>");

        arquivo.add("");
        arquivo.add("<hr/>");
        arquivo.add("");

        arquivo.add("<table>");
        arquivo.add("<tr>");
        arquivo.add("<th style=\"width: 25%\">Tempo gasto na Execução</th>");
        arquivo.add("<th style=\"width: 25%\">Tempo médio de Execução</th>");
        arquivo.add("<th style=\"width: 25%\">Quantidade de memória Inicial </th>");
        arquivo.add("<th style=\"width: 25%\">Quantidade de memória Final </th>");
        arquivo.add("</tr>");
        arquivo.add("<tr>");
        arquivo.add("<td>" + String.format("%.0f", relatorio.getTempoExecucao()) + " milisegundos </td>");
        arquivo.add("<td>" + String.format("%.3f", relatorio.getTempoMedio()) + " milisegundos/teste </td>");
        arquivo.add("<td>" + String.format("%.2f", relatorio.getMemoriaInicial()) + " bytes </td>");
        arquivo.add("<td>" + String.format("%.2f", relatorio.getMemoriaFinal()) + " bytes </td>");
        arquivo.add("</tr>");
        arquivo.add("</table>");

        arquivo.add("");

        arquivo.add("<table>");
        arquivo.add("<tr>");
        arquivo.add("<th style=\"width: 50%\">Total de Testes Executados</th>");
        arquivo.add("<th style=\"width: 25%\">Corretos </th>");
        arquivo.add("<th style=\"width: 25%\">Falhas</th>");
        arquivo.add("</tr>");
        arquivo.add("<tr>");
        arquivo.add("<td>" + relatorio.getTestesTotais() + "</td>");
        arquivo.add("<td><label style=\"color: green\">" + relatorio.getCorretos() + "</label></td>");
        arquivo.add("<td><label style=\"color: red\">" + relatorio.getFalhas() + "</label></td>");
        arquivo.add("</tr>");
        arquivo.add("</table>");

        arquivo.add("");

        //Resultado dos testes um por um
        arquivo.add("<br/><br/><br/>");
        arquivo.add("<hr/>");
        arquivo.add("<h2 style=\"text-align: center; color: #1A237E\">\n"
                + "            RELATÓRIO DETALHADO DE TESTES\n"
                + "        </h2>");
        arquivo.add("<hr/>");

        arquivo.add("<table>");
        arquivo.add("<tr>");
        arquivo.add("<th style=\"width: 40%\">Expressão</th>");
        arquivo.add("<th style=\"width: 30%\">Resultado Esperado</th>");
        arquivo.add("<th style=\"width: 30%\">Resultado Obtido</th>");
        arquivo.add("</tr>");

        for (Expressoes expressoes : listaExpressoes) {
            arquivo.add("<tr>");
            arquivo.add("<td>" + expressoes.getExpressao() + "</td>");
            arquivo.add("<td>" + expressoes.getVariaveis() + "</td>");
            arquivo.add("<td>" + String.format("%.4f", expressoes.getEsperado())
                    + "</td>");
            arquivo.add("<td>" + String.format("%.4f", expressoes.getResultado())
                    + "</td>");
            arquivo.add("</tr>");
        }
        arquivo.add("</table>");
        arquivo.add("");
        arquivo.add("</body>");
        arquivo.add("");
        arquivo.add("</html>");

        //Cria e salva o arquivo
        Path file = Paths.get(diretorio + "/relatorio.html");
        Files.write(file, arquivo, Charset.forName("UTF-8"));
    }

    private void gerarArquivoJson(final String diretorio, Relatorio relatorio, List<Expressoes> listaExpressoes) throws IOException {
        List<String> arquivo = new ArrayList<>();

        arquivo.add("{");
        arquivo.add("    \"testesTotais\":" + relatorio.getTestesTotais() + ",");
        arquivo.add("    \"testesCorretos\":" + relatorio.getCorretos() + ",");
        arquivo.add("    \"testesFalhos\":" + relatorio.getFalhas() + ",");
        arquivo.add("    \"tempoTotal\":" + relatorio.getTempoExecucao() + ",");
        arquivo.add("    \"tempoMedio\":" + relatorio.getTempoMedio() + ",");
        arquivo.add("    \"memoriaInicial\":" + relatorio.getMemoriaInicial() + ",");
        arquivo.add("    \"memoriaFinal\":" + relatorio.getMemoriaFinal() + ",");

        //Testes
        arquivo.add("    \"testes\":[");
        for (Expressoes expressoes : listaExpressoes) {
            String virgula = ",";
            if (listaExpressoes.indexOf(expressoes) == listaExpressoes.size() - 1) {
                virgula = "";
            }
            arquivo.add("        {");
            arquivo.add("            \"expressao\":\"" + expressoes.getExpressao()
                    + "\",");
            if (expressoes.temVariaveis()) {
                arquivo.add("            \"variaveis\":[");
                for (int i = 0; i < expressoes.getVariaveisNome().length; i++) {
                    String virgulaVar = ",";
                    if (i == expressoes.getVariaveisNome().length - 1) {
                        virgulaVar = "";
                    }
                    arquivo.add("                {");
                    arquivo.add("                    \""
                            + expressoes.getVariaveisNome()[i] + "\":"
                            + expressoes.getVariaveisValor()[i]);
                    arquivo.add("                }" + virgulaVar);
                }
                arquivo.add("            ],");

            }
            arquivo.add("            \"esperado\":" + expressoes.getEsperado()
                    + ",");
            arquivo.add("            \"obtido\":" + expressoes.getResultado() + ",");
            arquivo.add("        }" + virgula);
        }
        arquivo.add("    ]");
        arquivo.add("}");

        //Cria e salva o arquivo
        Path file = Paths.get(diretorio + "/relatorio.json");
        Files.write(file, arquivo, Charset.forName("UTF-8"));
    }
}
