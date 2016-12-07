/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.natalialopessilva.qp;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de testes, responsável por realizar os testes de cobertura para classe
 * Expressoes.
 */
public class ExpressoesTest {

    List<Expressoes> listaExpressoes = new ArrayList<Expressoes>();

    @Test
    public void testeSetEsperado() throws Exception {
        Expressoes expressoes = new Expressoes("6/ 3 ;; 2");
        listaExpressoes.add(expressoes);
        Calcular.calculaExpressao(listaExpressoes);
        listaExpressoes.stream().forEach((expressao) -> {
            float teste = expressao.getEsperado();
            expressao.setEsperado(teste);
            Assert.assertEquals(2f, expressao.getEsperado(), 0.001f);
        });
    }

    @Test
    public void testeSetExpressao() throws Exception {
        Expressoes expressoes = new Expressoes("6/ x ;x=3; 2");
        listaExpressoes.add(expressoes);
        Calcular.calculaExpressao(listaExpressoes);
        listaExpressoes.stream().forEach((expressao) -> {
            String teste = expressao.getExpressao();
            expressao.setExpressao(teste);
            Assert.assertEquals("6/x", expressao.getExpressao());
        });
    }

    @Test
    public void testeSetVariaveis() throws Exception {
        Expressoes expressoes = new Expressoes("6/ x ;x=3; 2");
        listaExpressoes.add(expressoes);
        Calcular.calculaExpressao(listaExpressoes);
        listaExpressoes.stream().forEach((expressao) -> {
            String teste = expressao.getVariaveis();
            expressao.setVariaveis(teste);
        });
    }

    @Test
    public void testeSetVariaveisNome() throws Exception {
        Expressoes expressoes = new Expressoes("6/ x ;x=3; 2");
        listaExpressoes.add(expressoes);
        Calcular.calculaExpressao(listaExpressoes);
        listaExpressoes.stream().forEach((expressao) -> {
            String[] teste = expressao.getVariaveisNome();
            expressao.setVariaveisNome(teste);
        });
    }

    @Test
    public void testeSetVariaveisValor() throws Exception {
        Expressoes expressoes = new Expressoes("6/ x ;x=3; 2");
        listaExpressoes.add(expressoes);
        Calcular.calculaExpressao(listaExpressoes);
        listaExpressoes.stream().forEach((expressao) -> {
            Float[] teste = expressao.getVariaveisValor();
            expressao.setVariaveisValor(teste);
        });
    }

    @Test
    public void testeSetSituacao() throws Exception {
        Expressoes expressoes = new Expressoes("6/ 3 ;; 2");
        listaExpressoes.add(expressoes);
        Calcular.calculaExpressao(listaExpressoes);
        listaExpressoes.stream().forEach((expressao) -> {
            String teste = expressao.getSituacao();
            expressao.setSituacao(teste);
            Assert.assertEquals("Aprovado", expressao.getSituacao());
        });
    }
}
