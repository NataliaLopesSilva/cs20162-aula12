/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.natalialopessilva.qp;

import static com.github.natalialopessilva.qp.Calcular.calculaExpressao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aluno
 */
public class Main {

    public static void main(String[] args) throws Exception {

        List<String> lista = new ArrayList<String>();
        List<Expressoes> listaResultados = new ArrayList<Expressoes>();

        long inicioExecucao = System.nanoTime();
        lista = Leitura.leituraLinhas();

        for (int i = 0; i < lista.size(); i++) {
            Expressoes objExpressoes = new Expressoes(lista.get(i));
            listaResultados.add(objExpressoes);
        }

        calculaExpressao(listaResultados);
        long finalExecucao = System.nanoTime();
        long tempoTotalExecucao = finalExecucao - inicioExecucao;
        geraRelatorio(listaResultados, tempoTotalExecucao);

    }

    public static void geraRelatorio(List<Expressoes> listaResultados, long tempoExecucao) {
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
