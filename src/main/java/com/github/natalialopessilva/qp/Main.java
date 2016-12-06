/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.natalialopessilva.qp;

import static com.github.natalialopessilva.qp.Calcular.calculaExpressao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author aluno
 */
public class Main {

    public static void main(String[] args) throws Exception {

        String diretorio = args[0];
        String saida = args[1];

        if (saida.equals("-h")) {
            saida = "html";
        } else {
            saida = "json";
        }

        List<String> lista = new ArrayList<String>();
        List<Expressoes> listaResultados = new ArrayList<Expressoes>();

        long inicioExecucao = System.nanoTime();

        Scanner ler = new Scanner(System.in);

        lista = Leitura.leituraLinhas(diretorio);

        for (int i = 0; i < lista.size(); i++) {
            Expressoes objExpressoes = new Expressoes(lista.get(i));
            listaResultados.add(objExpressoes);
        }

        calculaExpressao(listaResultados);
        long finalExecucao = System.nanoTime();
        long tempoTotalExecucao = finalExecucao - inicioExecucao;
        geraRelatorio(saida, diretorio, listaResultados, tempoTotalExecucao);

    }

    public static void geraRelatorio(String saida, String diretorio, List<Expressoes> listaResultados, long tempoExecucao) throws IOException {
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

        if (saida.equals("html")) {
            relatorio.gerarArquivoHtml(diretorio, relatorio, listaResultados);
        } else {
            relatorio.gerarArquivoJson(diretorio, relatorio, listaResultados);
        }
    }

    public static void calculoMemoria(Relatorio relatorio) {
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
