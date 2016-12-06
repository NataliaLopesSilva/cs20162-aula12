package com.github.natalialopessilva.qp;

import com.github.kyriosdata.parser.Lexer;
import com.github.kyriosdata.parser.Parser;
import com.github.kyriosdata.parser.Token;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calcular {

    public static void calculaExpressao(List<Expressoes> listaResultados)
            throws Exception {

        for (Expressoes resultado : listaResultados) {
            if (resultado.getVariaveis().isEmpty()) {
                List<Token> tokens = new Lexer(resultado.getExpressao()).tokenize();
                Parser parser = new Parser(tokens);
                float result;
                try {
                    result = parser.expressao().valor();
                } catch (IllegalArgumentException iae) {
                    throw new Exception();
                }
                resultado.setResultado(result);

            } else {

                Map<String, Float> ctx = new HashMap<>();
                for (int i = 0; i < resultado.getVariaveisNome().length; i++) {
                    ctx.put(resultado.getVariaveisNome()[i],
                            resultado.getVariaveisValor()[i]);
                }
                List<Token> tokens = new Lexer(resultado.getExpressao()).tokenize();
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

        listaResultados.stream().forEach((resultado) -> {
            if (resultado.getEsperado() == resultado.getResultado()) {
                resultado.setSituacao("Aprovado");
            } else {
                resultado.setSituacao("Reprovado");
            }
        });
    }
}
