/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nelsonwilliam.qp;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.github.kyriosdata.parser.Lexer;
import com.github.kyriosdata.parser.Parser;
import com.github.kyriosdata.parser.Token;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author aluno
 */
public class Testes {

    /**
     * As linhas inteiras de cada teste (expressao;variaveis;esperado).
     */
    private List<String> testes;

    /**
     * As expressões de cada teste.
     */
    private List<String> expressoes = new ArrayList<>();

    /**
     * O conjunto de variáveis de cada teste.
     */
    private List<String> variaveis = new ArrayList<>();

    /**
     * O valor esperado de cada teste.
     */
    private List<Float> esperado = new ArrayList<>();

    /**
     * O valor obtido de cada teste.
     */
    private List<Float> obtidas = new ArrayList<>();

    private float tempoTotal;
    private float tempoMedio;
    private float memoriaConsumida;
    private boolean todosPassaram;
    private boolean gerarHtml;

    public Testes(List<String> testes, boolean gerarHtml) {
        this.testes = testes;
        this.gerarHtml = gerarHtml;
    }

    /**
     * Gera o arquivo HTML ou JSON com o relatório dos testes atuais.
     */
    public void gerarRelatorio() {
        destrincharTestes();

        long inicio = System.currentTimeMillis();

        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        for (String teste : testes) {
            int index = testes.indexOf(teste);
            obtidas.set(index, resultadoExpressao(expressoes.get(index), variaveis.get(index)));
        }

        tempoTotal = System.currentTimeMillis() - inicio;
        tempoMedio = tempoTotal / testes.size();

        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());

        gerarArquivo();
    }

    /**
     * Separa os testes em expressoes, variaveis e resultados esperados.
     */
    private void destrincharTestes() {

        for (String teste : testes) {
            teste = teste.replace(" ", "");

            String[] partes;
            partes = teste.split(";");

            expressoes.add(partes[0]);
            variaveis.add(partes[1]);
            esperado.add(Float.parseFloat(partes[2]));
            obtidas.add(null);
        }

    }

    /**
     * Retorna o valor de uma expressão matemática, com base nos valores das
     * variáveis fornecidas (no formato "x=1" separadas por vírgula).
     *
     * @param expressao
     * @param variaveis
     * @return
     */
    private float resultadoExpressao(String expressao, String variaveis) {

        if (variaveis == null || variaveis.isEmpty()) {
            List<Token> tokens = new Lexer(expressao).tokenize();
            Parser parser = new Parser(tokens);
            return parser.expressao().valor();

        } else {
            String[] variaveisSeparadas = variaveis.split(",");

            Map<String, Float> ctx = new HashMap<>();
            for (String variavel : variaveisSeparadas) {
                String[] partes = variavel.split("=");
                ctx.put(partes[0], Float.parseFloat(partes[1]));
            }
            List<Token> tokens = new Lexer(expressao).tokenize();
            Parser parser = new Parser(tokens);
            return parser.expressao().valor(ctx);
        }
    }

    private void gerarArquivo() {
        if (gerarHtml) {
            gerarArquivoHtml();
        } else {
            gerarArquivoJson();
        }
    }

    private void gerarArquivoJson() {
        System.out.println("NÃO FIZ AINDA");
    }

    private void gerarArquivoHtml() {

        List<String> arquivo = new ArrayList<>();

        arquivo.add("<!DOCTYPE html>");
        arquivo.add("<html>");
        arquivo.add("<head>");
        arquivo.add("<meta charset=\"UTF-8\">");
        arquivo.add("<title>Relatório de Testes</title>");
        arquivo.add("</head>");
        arquivo.add("<body>");
        arquivo.add("<h1>Relatório de testes do Parser</h1>");
        arquivo.add("<h2>Testes detalhados</h2>");
        arquivo.add("<table>");
        arquivo.add("<tr>");
        arquivo.add("<th><b>Expressão</b></th>");
        arquivo.add("<th><b>Variáveis</b></th>");
        arquivo.add("<th><b>Esperado</b></th>");
        arquivo.add("<th><b>Obtido</b></th>");
        arquivo.add("<th><b>Avaliação</b></th>");
        arquivo.add("</tr>");
        for (String teste : testes) {
            int index = testes.indexOf(teste);
            arquivo.add("<tr>");
            arquivo.add("<td>" + expressoes.get(index) + "</td>");
            arquivo.add("<td>" + variaveis.get(index) + "</td>");
            arquivo.add("<td>" + esperado.get(index) + "</td>");
            arquivo.add("<td>" + obtidas.get(index) + "</td>");
            if (Objects.equals(obtidas.get(index), esperado.get(index))) {
                arquivo.add("<td><span style=\"color:#00FF00\">OK</span></td>");
            } else {
                arquivo.add("<td><span style=\"color:#FF0000\">ERRO</span></td>");
            }
            arquivo.add("</tr>");
        }
        arquivo.add("</table>");
        if (todosPassaram) {
            arquivo.add("<b><span style=\"color:#00FF00\">Todos os testes passaram.</span></b>");
        } else {
            arquivo.add("<b><span style=\"color:#FF0000\">Os testes não foram executados com sucesso.</span></b>");
        }
        arquivo.add("<h2>Informações gerais</h2>");
        arquivo.add("<p>Tempo total: " + tempoTotal + " milisegundos.");
        arquivo.add("</br>Tempo médio: " + tempoMedio + " milisegundos.");
        arquivo.add("</br>Memória consumida: " + memoriaConsumida / 1024 + " KB.");
        arquivo.add("</p>");
        arquivo.add("</body>");
        arquivo.add("</html>");

        //Geração do arquivo HTML
        Path file = Paths.get("/home/aluno/Área de Trabalho/relatorio.html");
        try {
            Files.write(file, arquivo, Charset.forName("UTF-8"));
            System.out.println("Relatório HTML gerado.");
        } catch (IOException ex) {
            System.out.println("Não foi possível salvar o arquivo!");
        }
    }
}
