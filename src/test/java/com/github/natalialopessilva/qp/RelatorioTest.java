/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.natalialopessilva.qp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * Classe de testes, responsável por realizar os testes de cobertura para classe
 * Relatorio.
 */
public class RelatorioTest {

    List<Expressoes> listaExpressoes = new ArrayList<Expressoes>();

    @Test
    public void testeGeraHtml() throws IOException, URISyntaxException {
        List<String> testes = new ArrayList<>();
        testes.add("a + b; a=1, b=2; 3");
        testes.add("5 / (6 - 5);;5");
        testes.add("(4 + x) * (9 - y); x = 0, y=0; 36");
        testes.add("(4 + x) * (9 - y);; 36");
        testes.add("3.14 * (z + 1);;3.14");
        testes.add("1 + 2;;5");

        testes.stream().map((teste) -> {
            Expressoes expressoes;
            expressoes = new Expressoes(teste);
            return expressoes;
        }).forEach((expressoes) -> {
            listaExpressoes.add(expressoes);
        });

        Relatorio relatorio = new Relatorio();
        relatorio.setTempoExecucao(83460470);
        relatorio.setTempoMedio(13910078);
        relatorio.setMemoriaInicial(64487424);
        relatorio.setMemoriaFinal(930909920);
        relatorio.setTestesTotais(6);
        relatorio.setCorretos(5);
        relatorio.setFalhas(1);

        relatorio.gerarArquivoHtml(relatorio, listaExpressoes);
    }

    @Test
    public void testeGeraJson() throws IOException, URISyntaxException {
        List<String> testes = new ArrayList<>();
        testes.add("a + b; a=1, b=2; 3");
        testes.add("5 / (6 - 5);;5");
        testes.add("(4 + x) * (9 - y); x = 0, y=0; 36");
        testes.add("(4 + x) * (9 - y);; 36");
        testes.add("3.14 * (z + 1);;3.14");
        testes.add("1 + 2;;5");

        testes.stream().map((teste) -> {
            Expressoes expressoes;
            expressoes = new Expressoes(teste);
            return expressoes;
        }).forEach((expressoes) -> {
            listaExpressoes.add(expressoes);
        });

        Relatorio relatorio = new Relatorio();
        relatorio.setTempoExecucao(83460470);
        relatorio.setTempoMedio(13910078);
        relatorio.setMemoriaInicial(64487424);
        relatorio.setMemoriaFinal(930909920);
        relatorio.setTestesTotais(6);
        relatorio.setCorretos(5);
        relatorio.setFalhas(1);

        relatorio.gerarArquivoJson(relatorio, listaExpressoes);
    }
    
     @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
    
     @Test
    public void testeGeraHtmlErro() throws IOException, URISyntaxException, Exception {
        exit.expectSystemExitWithStatus(1);
        Main.main(new String[]{"c:\\diretorioInexistente\\teste.txt"});
    }
}
