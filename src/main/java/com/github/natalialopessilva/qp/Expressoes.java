package com.github.natalialopessilva.qp;

import java.util.ArrayList;
import java.util.List;

public class Expressoes {

    private String expressao;
    private String variaveis;
    private String[] variaveisNome;
    private Float[] variaveisValor;
    private Float esperado;
    private Float obtido;
    private float resultado;
    private String situacao;

    public Expressoes(final String expressaoCompleta) {
        String teste = expressaoCompleta.replace(" ", "");

        String[] partes;
        partes = teste.split(";");

        expressao = partes[0];

        variaveis = partes[1];
        if (variaveis.isEmpty()) {
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

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    public String getVariaveis() {
        return variaveis;
    }

    public void setVariaveis(String variaveis) {
        this.variaveis = variaveis;
    }

    public float getEsperado() {
        return esperado;
    }

    public void setEsperado(float esperado) {
        this.esperado = esperado;
    }

    public String[] getVariaveisNome() {
        return variaveisNome;
    }

    public void setVariaveisNome(String[] variaveisNome) {
        this.variaveisNome = variaveisNome;
    }

    public Float[] getVariaveisValor() {
        return variaveisValor;
    }

    public void setVariaveisValor(Float[] variaveisValor) {
        this.variaveisValor = variaveisValor;
    }

    public float getResultado() {
        return resultado;
    }

    public void setResultado(float resultado) {
        this.resultado = resultado;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * Obtém se este teste tem variáveis definidas.
     *
     * @return Verdadeiro se o bloco de variáveis não for vazio.
     */
    public final boolean temVariaveis() {
        return !(variaveis.isEmpty());
    }
}
