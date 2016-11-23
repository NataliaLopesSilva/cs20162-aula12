/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.nelsonwilliam.qp;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NelsonWilliam
 */
public class Main {

    public static void main(String[] args) {
        boolean exportHtml = false;
        boolean localFile = true;
        List<String> testes = null;

        //Checa quantidade de parâmetros (deve ser 1 ou 2)
        if (args.length != 1 && args.length != 2) {
            System.out.println("Quantidade de parâmetros inválida.");
            System.exit(1);
        }

        //Checa o segundo parâmetro (só pode ser "-h")
        if (args.length == 2) {
            if (args[1].equals("-h")) {
                exportHtml = true;
            } else {
                System.out.println("Segundo parâmetro inválido.");
                System.exit(1);
            }
        }

        //Checa se é local ou remoto
        if (args[0].startsWith("http")) {
            localFile = false;
        }

        //Obtém o arquivo
        System.out.println("Obtendo arquivo...");
        try {
            System.out.println("Arquivo obtido.");
            testes = LeituraArquivo.obterLinhas(args[0], localFile);
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo inexistente!");
        }

        //Gera relatorio
        System.out.println("Realizando testes...");
        Testes gerador = new Testes(testes, exportHtml);
        gerador.gerarRelatorio();
        
        System.exit(0);

    }

}
