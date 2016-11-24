/*
 *
 */
package com.github.nelsonwilliam.qp;

import com.github.kyriosdata.parser.Lexer;
import com.github.kyriosdata.parser.Parser;
import com.github.kyriosdata.parser.Token;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utiliza o Parser a ser testado para o cálculo de expressões matemáticas com
 * ou sem variáveis.
 *
 * @author NelsonWilliam
 */
public final class Calcular {

    /**
     * Usa o Parser o valor de uma expressão matemática, com base nos valores
     * das variáveis fornecidas (no formato "x=1" separadas por vírgula).
     *
     * @param teste
     * @return
     */
    public static float resultadoExpressao(Teste teste) {

        if (teste.getVariaveis() == null || teste.getVariaveis().isEmpty()) {
            List<Token> tokens = new Lexer(teste.getExpressao()).tokenize();
            Parser parser = new Parser(tokens);
            return parser.expressao().valor();

        } else {
            Map<String, Float> ctx = new HashMap<>();
            for (int i = 0; i < teste.getVariaveisNome().length; i++) {
                ctx.put(teste.getVariaveisNome()[i], teste.getVariaveisValor()[i]);
            }
            List<Token> tokens = new Lexer(teste.getExpressao()).tokenize();
            Parser parser = new Parser(tokens);
            return parser.expressao().valor(ctx);
        }
    }

}
