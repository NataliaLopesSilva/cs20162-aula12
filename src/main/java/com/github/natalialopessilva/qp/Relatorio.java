/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.natalialopessilva.qp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da Classe Relatorio.
 *
 * <p>
 * Responsável pelo armazenamento dos dados pertinentes ao relatório que será
 * gerado, além disso gera o relatório em duas formatações, de acordo com a
 * opção escolhida pelo usuário, podendo gerar um relaório do tipo HTML, e outro
 * do tipo de Json.
 *
 */
public class Relatorio {

    /**
     * Variável que receberá a quantidade total de testes realizados.
     */
    private int testesTotais;

    /**
     * Variável que receberá a quantidade de testes realizados corretamente.
     */
    private int corretos;

    /**
     * Variável que receberá a quantidade de testes realizados incorretamente.
     */
    private int falhas;

    /**
     * Variável que receberá o tempo total de execução do processo de testes.
     */
    private long tempoExecucao;

    /**
     * Variável que receberá o tempo médio de execução por teste requerido.
     */
    private long tempoMedio;

    /**
     * Variável que receberá a quantidade de memória inicial antes da realização
     * do processo.
     */
    private long memoriaInicial;

    /**
     * Variável que receberá a quantidade de memória final depois da realização
     * do processo.
     */
    private long memoriaFinal;

    /**
     * Construtor da classe Relatorio que impede que a mesma seja instanciada ou
     * acessada.
     */
    protected Relatorio() {
    }

    /**
     * Método necessário que acessa a variável testesTotais pois ela é privada.
     *
     * @return testesTotais que nada mais é do que o valor da própia variável.
     *
     */
    public final int getTestesTotais() {
        return testesTotais;
    }

    /**
     * Método necessário que atribui valor a variável memoriaInicial.
     *
     * @param valorTestesTotais que vem preenchido do Main.
     *
     */
    public final void setTestesTotais(final int valorTestesTotais) {
        this.testesTotais = valorTestesTotais;
    }

    /**
     * Método necessário que acessa a variável corretos pois ela é privada.
     *
     * @return corretos que nada mais é do que o valor da própia variável.
     *
     */
    public final int getCorretos() {
        return corretos;
    }

    /**
     * Método necessário que atribui valor a variável corretos.
     *
     * @param valorCorretos que vem preenchido do Main.
     *
     */
    public final void setCorretos(final int valorCorretos) {
        this.corretos = valorCorretos;
    }

    /**
     * Método necessário que acessa a variável falhas pois ela é privada.
     *
     * @return falhas que nada mais é do que o valor da própia variável.
     *
     */
    public final int getFalhas() {
        return falhas;
    }

    /**
     * Método necessário que atribui valor a variável falhas.
     *
     * @param valorFalhas que vem preenchido do Main.
     *
     */
    public final void setFalhas(final int valorFalhas) {
        this.falhas = valorFalhas;
    }

    /**
     * Método necessário que acessa a variável tempoExecucao pois ela é privada.
     *
     * @return tempoExecucao que nada mais é do que o valor da própia variável.
     *
     */
    public final long getTempoExecucao() {
        return tempoExecucao;
    }

    /**
     * Método necessário que atribui valor a variável tempoExecucao.
     *
     * @param valorTempoExecucao que vem preenchido do Main.
     *
     */
    public final void setTempoExecucao(final long valorTempoExecucao) {
        this.tempoExecucao = valorTempoExecucao;
    }

    /**
     * Método necessário que acessa a variável tempoMedio pois ela é privada.
     *
     * @return tempoMedio que nada mais é do que o valor da própia variável.
     *
     */
    public final long getTempoMedio() {
        return tempoMedio;
    }

    /**
     * Método necessário que atribui valor a variável tempoMedio.
     *
     * @param valorTempoMedio que vem preenchido do Main.
     *
     */
    public final void setTempoMedio(final long valorTempoMedio) {
        this.tempoMedio = valorTempoMedio;
    }

    /**
     * Método necessário que acessa a variável memoriaInicial pois ela é privada
     * .
     *
     * @return memoriaInicial que nada mais é do que o valor da própia variável.
     *
     */
    public final long getMemoriaInicial() {
        return memoriaInicial;
    }

    /**
     * Método necessário que atribui valor a variável memoriaInicial.
     *
     * @param valorMemoriaInicial que vem preenchido do Main.
     *
     */
    public final void setMemoriaInicial(final long valorMemoriaInicial) {
        this.memoriaInicial = valorMemoriaInicial;
    }

    /**
     * Método necessário que acessa a variável memoriaFinal pois ela é privada.
     *
     * @return memoriaFinal que nada mais é do que o valor da própia variável.
     *
     */
    public final long getMemoriaFinal() {
        return memoriaFinal;
    }

    /**
     * Método necessário que atribui valor a variável memoriaFinal.
     *
     * @param valorMemoriaFinal que vem preenchido do Main.
     *
     */
    public final void setMemoriaFinal(final long valorMemoriaFinal) {
        this.memoriaFinal = valorMemoriaFinal;
    }

    /**
     * Gera o arquivo/relatório do tipo html, com as informações requeridas.
     *
     * @param relatorio que indica o objeto vindo do Main, com os valores, a
     * serem passados para o relatorio.
     * @param listaExpressoes que consiste, na lista de expressões que estão
     * localizadas linha a linha no arquivo lido no início do processo.
     *
     * @throws IOException se ocorrer algum tipo de erro na geração do relatório
     * HTML.
     *
     */
    public final void gerarArquivoHtml(final Relatorio relatorio,
            final List<Expressoes> listaExpressoes) throws IOException {
        List<String> arquivo = new ArrayList<>();

        arquivo.add("<!DOCTYPE html>");
        arquivo.add("<html>");
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
        arquivo.add("<body>");
        arquivo.add("<table>");
        arquivo.add("<tr>");
        arquivo.add("<th id=\"tituloAzul\" style=\"text-align: center;\">");
        arquivo.add("<h2 style=\"color: white\"> RELATORIO DE TESTES</h2>");
        arquivo.add("</th>");
        arquivo.add("</tr>");
        arquivo.add("</table>");

        arquivo.add("<hr/>");

        arquivo.add("<table>");
        arquivo.add("<tr>");
        arquivo.add("<th style=\"width: 25%\">Tempo gasto na Execucao</th>");
        arquivo.add("<th style=\"width: 25%\">Tempo medio de Execucao</th>");
        arquivo.add("<th style=\"width: 25%\">Quantidade de memoria Inicial "
                + "</th>");
        arquivo.add("<th style=\"width: 25%\">Quantidade de memoria Final "
                + "</th>");
        arquivo.add("</tr>");
        arquivo.add("<tr>");
        arquivo.add("<td>" + String.format("%d", relatorio.getTempoExecucao())
                + " nanosegundos </td>");
        arquivo.add("<td>" + String.format("%d", relatorio.getTempoMedio())
                + " nanosegundos por teste </td>");
        arquivo.add("<td>" + String.format("%d", relatorio.getMemoriaInicial())
                + " bytes </td>");
        arquivo.add("<td>" + String.format("%d", relatorio.getMemoriaFinal())
                + " bytes </td>");
        arquivo.add("</tr>");
        arquivo.add("</table>");

        arquivo.add("<table>");
        arquivo.add("<tr>");
        arquivo.add("<th style=\"width: 50%\">Total de Testes Executados</th>");
        arquivo.add("<th style=\"width: 25%\">Corretos </th>");
        arquivo.add("<th style=\"width: 25%\">Falhas</th>");
        arquivo.add("</tr>");
        arquivo.add("<tr>");
        arquivo.add("<td>" + relatorio.getTestesTotais() + "</td>");
        arquivo.add("<td><label style=\"color: green\">"
                + relatorio.getCorretos() + "</label></td>");
        arquivo.add("<td><label style=\"color: red\">"
                + relatorio.getFalhas() + "</label></td>");
        arquivo.add("</tr>");
        arquivo.add("</table>");

        arquivo.add("");

        arquivo.add("<br/><br/><br/>");
        arquivo.add("<hr/>");
        arquivo.add("<h2 style=\"text-align: center; color: #1A237E\"> "
                + "RELATORIO DETALHADO DE TESTES </h2>");
        arquivo.add("<hr/>");

        arquivo.add("<table>");
        arquivo.add("<tr>");
        arquivo.add("<th style=\"width: 25%\">Expressao</th>");
        arquivo.add("<th style=\"width: 20%\">Variaveis</th>");
        arquivo.add("<th style=\"width: 15%\">Resultado Esperado</th>");
        arquivo.add("<th style=\"width: 15%\">Resultado Obtido</th>");
        arquivo.add("<th style=\"width: 25%\">Situacao</th>");
        arquivo.add("</tr>");

        listaExpressoes.stream().map((expressoes) -> {
            arquivo.add("<tr>");
            arquivo.add("<td>" + expressoes.getExpressao() + "</td>");
            return expressoes;
        }).map((Expressoes expressoes) -> {
            if (!expressoes.getVariaveis().equals("")) {
                arquivo.add("<td>" + expressoes.getVariaveis() + "</td>");
            } else {
                arquivo.add("<td> Nao possui variaveis</td>");
            }
            return expressoes;
        }).map((expressoes) -> {
            arquivo.add("<td>" + String.format("%.2f",
                    expressoes.getEsperado())
                    + "</td>");
            return expressoes;
        }).map((expressoes) -> {
            arquivo.add("<td>" + String.format("%.2f",
                    expressoes.getResultado())
                    + "</td>");
            return expressoes;
        }).map((expressoes) -> {
            if (expressoes.getSituacao().equals("Aprovado")) {
                arquivo.add("<td><label style=\"color: green\">"
                        + expressoes.getSituacao() + "</label></td>");
            } else {
                arquivo.add("<td><label style=\"color: red\">"
                        + expressoes.getSituacao() + "</label></td>");
            }
            return expressoes;
        }).forEach((item) -> {
            arquivo.add("</tr>");
        });
        arquivo.add("</table>");
        arquivo.add("</body>");
        arquivo.add("</html>");

        FileWriter fw = new FileWriter("relatorioHTML.html");
        fw.write(arquivo.toString());
    }

    /**
     * Gera o arquivo/relatório do tipo json, com as informações requeridas.
     *
     * @param relatorio que indica o objeto vindo do Main, com os valores, a
     * serem passados para o relatorio.
     * @param listaExpressoes que consiste, na lista de expressões que estão
     * localizadas linha a linha no arquivo lido no início do processo.
     *
     * @throws IOException se ocorrer algum tipo de erro na geração do relatório
     * JSON.
     *
     */
    public final void gerarArquivoJson(final Relatorio relatorio,
            final List<Expressoes> listaExpressoes) throws IOException {
        List<String> arquivo = new ArrayList<>();

        arquivo.add("{");
        arquivo.add("    \"testesTotais\":" + relatorio.getTestesTotais()
                + ",");
        arquivo.add("    \"testesCorretos\":" + relatorio.getCorretos()
                + ",");
        arquivo.add("    \"testesFalhos\":" + relatorio.getFalhas()
                + ",");
        arquivo.add("    \"tempoTotal\":" + relatorio.getTempoExecucao() + ",");
        arquivo.add("    \"tempoMedio\":" + relatorio.getTempoMedio() + ",");
        arquivo.add("    \"memoriaInicial\":" + relatorio.getMemoriaInicial()
                + ",");
        arquivo.add("    \"memoriaFinal\":" + relatorio.getMemoriaFinal()
                + ",");

        arquivo.add("    \"testes\":[");
        listaExpressoes.stream().forEach((expressoes) -> {
            String virgula = ",";
            if (listaExpressoes.indexOf(expressoes) == listaExpressoes.size()
                    - 1) {
                virgula = "";
            }
            arquivo.add("        {");
            arquivo.add("            \"expressao\":\""
                    + expressoes.getExpressao()
                    + "\",");
            if (!expressoes.getVariaveis().equals("")) {
                arquivo.add("            \"variaveis\":"
                        + expressoes.getVariaveis()
                        + ",");
            } else {
                arquivo.add("            \"variavei\": nao possui ,");
            }
            arquivo.add("            \"esperado\":" + expressoes.getEsperado()
                    + ",");
            arquivo.add("            \"obtido\":" + expressoes.getResultado()
                    + ",");
            arquivo.add("        }" + virgula);
        });
        arquivo.add("    ]");
        arquivo.add("}");

        FileWriter fw = new FileWriter("relatorioJSON.json");
        fw.write(arquivo.toString());
    }
}
