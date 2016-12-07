/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.natalialopessilva.qp;

import static com.github.natalialopessilva.qp.Calcular.calculaExpressao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Natália
 */
public final class Main {

    /**
     * Construtor da classe Main usado para atender ao checkstyle.
     */
    private Main() {
    }

    /**
     * Interação principal.
     *
     * <p>
     * Responsável pelas interações principais do projeto, bem como definições
     * de arquivo, relatórios, validações de entrada, além de especificações e
     * cáculos necessários ao relatório que será gerado em diante.
     *
     * @param args que recebe os argumentos do Main, nesse caso específico os
     * argumentos serão passados na linha de comando da seguinte forma: java
     * -jar qp.jar (diretório do arquivo de leitura) (tipo de relatório a ser
     * gerado).Ex: java -jar qp.jar c:\testes.txt -h.
     *
     * @throws Exception caso corra algum tipo de exceção ao decorrer da
     * execução do projeto.
     */
    public static void main(final String[] args) throws Exception {

        if (args.length < 1 || args.length > 2) {
            System.out.println("ENTRADA INVÁLIDA!");
            System.exit(1);
        }

        List<String> listaLinhas = new ArrayList<String>();
        List<Expressoes> listaResultados = new ArrayList<Expressoes>();

        String saida = "json";

        if (args[0].startsWith("http")) {
            listaLinhas = Leitura.leituraArquivoUrl(args[0]);
        } else {
            listaLinhas = Leitura.leituraArquivoLocal(args[0]);
        }

        if (args.length == 2) {
            if (args[1].equals("-h")) {
                saida = "html";
            } else {
                System.out.println("ENTRADA INVÁLIDA!");
                System.exit(1);
            }
        }

        long inicioExecucao = System.nanoTime();

        for (int i = 0; i < listaLinhas.size(); i++) {
            Expressoes objExpressoes = new Expressoes(listaLinhas.get(i));
            listaResultados.add(objExpressoes);
        }

        calculaExpressao(listaResultados);
        long finalExecucao = System.nanoTime();
        long tempoTotalExecucao = finalExecucao - inicioExecucao;
        geraDadosRelatorio(saida, listaResultados, tempoTotalExecucao);
    }

    /**
     * Implementa método responsável por calcular dados do relatório.
     *
     * <p>
     * Responsável pelo cálculo de todos os parâmetros exigidos pelo relatório.
     *
     * @param saida que possui o tipo de relatório que deve ser gerado.
     * @param listaResultados que possui todas as expressoes com suas divisões,
     * resultados esperados e obtidos segundo o Parser.
     * @param tempoExecucao que possui o tempo de Execução total do processo.
     * @throws java.io.IOException que verifica exceções existentes no método.
     *
     */
    public static void geraDadosRelatorio(final String saida,
            final List<Expressoes> listaResultados, final long tempoExecucao)
            throws IOException {
        int corretos = 0;
        int falhas = 0;
        int testesTotais = listaResultados.size();
        long tempoMedio = 0;

        for (Expressoes resultado : listaResultados) {
            if (resultado.getSituacao().equals("Aprovado")) {
                corretos++;
            } else {
                falhas++;
            }
        }

        tempoMedio = tempoExecucao / testesTotais;

        Relatorio relatorio = new Relatorio();

        relatorio.setTestesTotais(testesTotais);
        relatorio.setCorretos(corretos);
        relatorio.setFalhas(falhas);
        relatorio.setTempoExecucao(tempoExecucao);
        relatorio.setTempoMedio(tempoMedio);
        calculoMemoria(relatorio);

        if (saida.equals("html")) {
            relatorio.gerarArquivoHtml(relatorio, listaResultados);
        } else {
            relatorio.gerarArquivoJson(relatorio, listaResultados);
        }
    }

    /**
     * Implementa os Calculos referentes a memória.
     *
     * <p>
     * Responsável pelos cáculos requeridos no que diz respeito a memória.
     *
     * @param relatorio que possui o obj para armazenamento dos dados referentes
     * a memória.
     *
     */
    public static void calculoMemoria(final Relatorio relatorio) {
        Runtime rt = Runtime.getRuntime();
        long memoriaInicial = 0;
        long memoriaUsada = 0;
        long memoriaLivre = 0;

        memoriaInicial = rt.totalMemory();
        memoriaUsada = rt.totalMemory() - rt.freeMemory();
        memoriaLivre = rt.maxMemory() - memoriaUsada;

        relatorio.setMemoriaInicial(memoriaInicial);
        relatorio.setMemoriaFinal(memoriaLivre);
    }
}
