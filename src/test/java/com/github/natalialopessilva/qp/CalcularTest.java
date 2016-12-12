package com.github.natalialopessilva.qp;

/*
 * Copyright (c) 2016. FÃ¡brica de Software - Instituto de InformÃ¡tica (UFG)
 * Creative Commons Attribution 4.0 International License.
 */


import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe de testes, responsÃ¡vel por realizar os testes de cobertura para classe
 * Calcular.
 */
public class CalcularTest {

    List<Expressoes> listaExpressoes = new ArrayList<Expressoes>();

    @Test
    public void expressaoSimples() throws Exception {
        Expressoes expressoes = new Expressoes("5/ 2 ;; 2.5");
        listaExpressoes.add(expressoes);
        Calcular.calculaExpressao(listaExpressoes);
        listaExpressoes.stream().forEach((expressao) -> {
            Assert.assertEquals(2.5f, expressao.getEsperado(), 0.001f);
        });
    }
    
    @Test
    public void expressaoComVariaveis() throws Exception {
        Expressoes expressoes = new Expressoes("a + 5;a=2; 7");
        listaExpressoes.add(expressoes);
        Calcular.calculaExpressao(listaExpressoes);
        listaExpressoes.stream().forEach((expressao) -> {
            Assert.assertEquals(7f, expressao.getEsperado(), 0.001f);
        });
    }
    
    @Test
    public void expressaoSimplesReprovada() throws Exception {
        Expressoes expressoes = new Expressoes("6/ 2 ;; 2.5");
        listaExpressoes.add(expressoes);
        Calcular.calculaExpressao(listaExpressoes);
        listaExpressoes.stream().forEach((expressao) -> {
            Assert.assertEquals(3f, expressao.getResultado(), 0.001f);
        });
    }

    @Test(expected = Exception.class)
    public void expressaoSimplesInvalida() throws Exception {
        Expressoes expressoes = new Expressoes("2 + 3 + 3;; 8");
        listaExpressoes.add(expressoes);
        Calcular.calculaExpressao(listaExpressoes);
    }

    @Test(expected = Exception.class)
    public void expressaoComVariavelInvalida() throws Exception {
        Expressoes expressoes = new Expressoes("2 + a + 1;x=2; 5");
        listaExpressoes.add(expressoes);
        Calcular.calculaExpressao(listaExpressoes);
    }
}
