/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.nelsonwilliam.qp;

import org.junit.Assert;
import org.junit.Test;

public class CalcularTest {

    @Test
    public void expressaoSimples() throws Exception {
        Teste teste = new Teste("5/ 2 ;; 2.5");
        Assert.assertEquals(2.5f, Calcular.resultadoExpressao(teste), 0.001f);
    }

    @Test
    public void expressaoVariavel() throws Exception {
        Teste teste = new Teste("2 * x;x=4; 8");
        Assert.assertEquals(8f, Calcular.resultadoExpressao(teste), 0.001f);
    }

    @Test(expected = Exception.class)
    public void expressaoSimplesInvalida() throws Exception {
        Teste teste = new Teste("2 + 1 + 1;; 4");
        Calcular.resultadoExpressao(teste);
    }

    @Test(expected = Exception.class)
    public void expressaoVariavelInvalida() throws Exception {
        Teste teste = new Teste("2 + x + 1;x=1; 4");
        Calcular.resultadoExpressao(teste);
    }
}
