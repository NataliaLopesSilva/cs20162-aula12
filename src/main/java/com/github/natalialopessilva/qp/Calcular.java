/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.natalialopessilva.qp;

import com.github.kyriosdata.parser.Lexer;
import com.github.kyriosdata.parser.Parser;
import com.github.kyriosdata.parser.Token;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementação da Classe Calcular.
 *
 * <p>
 * Responsável pelo cálculo das expressões lidas no arquivo pelo Parser, bem
 * como, a comparação entre o resultado obtido pelo Parser, e o rsultado
 * esperado da expressão, fornecida juntamente com ela no arquivo lido
 * anteriormente.
 *
 */
public final class Calcular {

    /**
     * Construtor da classe Calcular que impede que a mesma seja instanciada ou
     * acessada.
     */
    private Calcular() {
    }

    /**
     * Método responsável por utilizar o Parser para calcular as expressoões,
     * obtidas anteriormente no arquivo lido, e comparar com o resultados
     * fornecido pelo mesmo, realizando assim a comparação entre o Paser, e os
     * testes.
     *
     * @param listaResultados nada mais é do que uma lista com suas expressões,
     * bem como respectivos resultados.
     *
     * @throws java.lang.Exception se houver alguma exceção existente.
     * @throws IllegalArgumentException que trata se houver algum tipo de
     * argumento inválido.
     */
    public static void calculaExpressao(final List<Expressoes> listaResultados)
            throws Exception {

        for (Expressoes resultado : listaResultados) {
            if (resultado.getVariaveis().isEmpty()) {
                List<Token> tokens = new Lexer(resultado.getExpressao()).
                        tokenize();
                Parser parser = new Parser(tokens);
                float result;
                try {
                    result = parser.expressao().valor();
                } catch (IllegalArgumentException iae) {
                    throw new Exception();
                }
                resultado.setResultado(result);

            } else {

                Map<String, Float> ctx = new HashMap<String, Float>();
                for (int i = 0; i < resultado.getVariaveisNome().length; i++) {
                    ctx.put(resultado.getVariaveisNome()[i],
                            resultado.getVariaveisValor()[i]);
                }
                List<Token> tokens = new Lexer(resultado.getExpressao()).
                        tokenize();
                Parser parser = new Parser(tokens);
                float result;
                try {
                    result = parser.expressao().valor(ctx);
                } catch (IllegalArgumentException iae) {
                    throw new IllegalArgumentException();
                }
                resultado.setResultado(result);
            }
        }

        for (Expressoes resultado : listaResultados) {
            if (resultado.getEsperado() == resultado.getResultado()) {
                resultado.setSituacao("Aprovado");
            } else {
                resultado.setSituacao("Reprovado");
            }
        }
    }
}
