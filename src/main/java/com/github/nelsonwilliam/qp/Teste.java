/*
 *
 */
package com.github.nelsonwilliam.qp;

import java.util.Objects;

/**
 *
 * @author NelsonWilliam
 */
public class Teste {

    private String completo;
    private final String expressao;
    private final String variaveis;
    private final String[] variaveisNome;
    private final Float[] variaveisValor;
    private final Float esperado;
    private Float obtido;
    private boolean sucesso;

    /**
     * Constroi um teste e já obtém seus atributos com base na linha completa.
     *
     * @param completo Linha completa do teste, no formato
     * "expressao;variaveis;esperado". Por exemplo: "x+2 ; x=2; 4".
     */
    public Teste(String completo) {
        String teste = completo.replace(" ", "");

        String[] partes;
        partes = teste.split(";");

        expressao = partes[0];

        variaveis = partes[1];
        if (variaveis == null || variaveis.isEmpty()) {
            variaveisNome = null;
            variaveisValor = null;
        } else {
            String[] variaveisSeparadas = variaveis.split(",");
            variaveisNome = new String[variaveisSeparadas.length];
            variaveisValor = new Float[variaveisSeparadas.length];
            int i = 0;
            for (String variavel : variaveisSeparadas) {
                String[] partesDaVariavel = variavel.split("=");
                variaveisNome[i] = partesDaVariavel[0];
                variaveisValor[i] = Float.parseFloat(partesDaVariavel[1]);
                i++;
            }
        }

        esperado = Float.parseFloat(partes[2]);
        obtido = null;
    }

    public String getExpressao() {
        return expressao;
    }

    public boolean temVariaveis() {
        return !variaveis.isEmpty();
    }

    public String getVariaveis() {
        return variaveis;
    }

    public String[] getVariaveisNome() {
        return variaveisNome;
    }

    public Float[] getVariaveisValor() {
        return variaveisValor;
    }

    public Float getEsperado() {
        return esperado;
    }

    public Float getObtido() {
        return obtido;
    }

    public boolean getSucesso() {
        return sucesso;
    }

    public void calcularValor() {
        this.obtido = Calcular.resultadoExpressao(this);
    }

    /**
     * Atualiza se este Teste ocorreu com sucesso (resultado obtido é o
     * esperado) ou não, ou seja, atualiza o atributo "sucesso". Esta função não
     * é chamada logo no cálculo do valor ("calcularValor()") pois isto
     * implicaria em interferência na medição de tempo e memória do parser.
     * Desta forma, deve ser chamada após a medição do tempo.
     */
    public void atualizarSucesso() {
        this.sucesso = Objects.equals(esperado, obtido);
    }

}
