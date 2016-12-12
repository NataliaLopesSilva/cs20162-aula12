/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.natalialopessilva.qp;

/**
 * Implementação da Classe Expressoes.
 *
 * <p>
 * Responsável pelo separação das expressões em suas respectivas partes
 * devidas, bem como guarda todos os valores pertencentes a elas, fora o valor
 * do comparativo do Parser.
 *
 */
public class Expressoes {

    /**
     * Variável que receberá a expressão por inteiro.
     */
    private String expressao;

    /**
     * Variável que receberá as variáveis da expressão, separadamente.
     */
    private String variaveis;

    /**
     * Variável que receberá as variáveis da expressão, do tipo incógnita.
     */
    private String[] variaveisNome;

    /**
     * Variável que receberá as variáveis da expressão, do tipo valor
     * literal.
     */
    private Float[] variaveisValor;

    /**
     * Variável que receberá o valor esperado para aquela expressão.
     */
    private Float esperado;

    /**
     * Variável que receberá o resultado obtido pelo Parser para a expressão
     * em questão.
     */
    private Float resultado;

    /**
     * Variável que receberá a situação da expressão (Aprovada ou
     * Reprovada).
     */
    private String situacao;

    /**
     * Construtor da classe Expressoes que impede seu acesso em outro pacote, no
     * entanto no pacote onde ela se encontra possui a finalidade de separar a
     * expressão em suas respectivas partes, a cada linha lida um objeto
     * expressão é criado dentro de uma lista de Expressões.
     *
     * @param expressaoCompleta refere-se a expressão completa recebida, sem
     * separação
     */
    protected Expressoes(final String expressaoCompleta) {
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
        resultado = null;
    }

    /**
     * Método necessário que acessa a variável expressao pois ela é privada.
     *
     * @return expressao que nada mais é do que o valor da própia variável.
     *
     */
    public final String getExpressao() {
        return expressao;
    }

    /**
     * Método necessário que atribui valor a variável expressao.
     *
     * @param valorExpressao que vem preenchido do obj.
     *
     */
    public final void setExpressao(final String valorExpressao) {
        this.expressao = valorExpressao;
    }

    /**
     * Método necessário que acessa a variável variaveis pois ela é privada.
     *
     * @return variaveis que nada mais é do que o valor da própia variável.
     *
     */
    public final String getVariaveis() {
        return variaveis;
    }

    /**
     * Método necessário que atribui valor a variável variaveis.
     *
     * @param valorVariaveis que vem preenchido do obj.
     *
     */
    public final void setVariaveis(final String valorVariaveis) {
        this.variaveis = valorVariaveis;
    }

    /**
     * Método necessário que acessa a variável esperado pois ela é privada.
     *
     * @return esperado que nada mais é do que o valor da própia variável.
     *
     */
    public final float getEsperado() {
        return esperado;
    }

    /**
     * Método necessário que atribui valor a variável esperado.
     *
     * @param valorEsperado que vem preenchido do obj.
     *
     */
    public final void setEsperado(final float valorEsperado) {
        this.esperado = valorEsperado;
    }

    /**
     * Método necessário que acessa a variável variaveisNome pois ela é
     * privada.
     *
     * @return variaveisNome que nada mais é do que o valor da própia
     * variável.
     *
     */
    public final String[] getVariaveisNome() {
        return variaveisNome;
    }

    /**
     * Método necessário que atribui valor a variável variaveisNome.
     *
     * @param valorVariaveisNome que vem preenchido do obj.
     *
     */
    public final void setVariaveisNome(final String[] valorVariaveisNome) {
        this.variaveisNome = valorVariaveisNome;
    }

    /**
     * Método necessário que acessa a variável variaveisValor pois ela é
     * privada .
     *
     * @return variaveisValor que nada mais é do que o valor da própia
     * variável.
     *
     */
    public final Float[] getVariaveisValor() {
        return variaveisValor;
    }

    /**
     * Método necessário que atribui valor a variável variaveisValor.
     *
     * @param valorVariaveisValor que vem preenchido do obj.
     *
     */
    public final void setVariaveisValor(final Float[] valorVariaveisValor) {
        this.variaveisValor = valorVariaveisValor;
    }

    /**
     * Método necessário que acessa a variável resultado pois ela é privada.
     *
     * @return resultado que nada mais é do que o valor da própia variável.
     *
     */
    public final float getResultado() {
        return resultado;
    }

    /**
     * Método necessário que atribui valor a variável resultado.
     *
     * @param valorResultado que vem preenchido do obj.
     *
     */
    public final void setResultado(final float valorResultado) {
        this.resultado = valorResultado;
    }

    /**
     * Método necessário que acessa a variável situacao pois ela é privada.
     *
     * @return situacao que nada mais é do que o valor da própia variável.
     *
     */
    public final String getSituacao() {
        return situacao;
    }

    /**
     * Método necessário que atribui valor a variável situacao.
     *
     * @param valorSituacao que vem preenchido do obj expressão.
     *
     */
    public final void setSituacao(final String valorSituacao) {
        this.situacao = valorSituacao;
    }
}
