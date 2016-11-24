/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.nelsonwilliam.qp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

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
            System.out.println("O arquivo de testes será obtido remotamente.");
            localFile = false;
        }

        //Obtém o arquivo
        System.out.println("Obtendo arquivo de testes...");
        try {
            testes = Leitura.obterLinhas(args[0], localFile);
            System.out.println("Arquivo de testes obtido.");
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado.");
            System.exit(1);
        } catch (MalformedURLException ex) {
            System.out.println("A URL fornecida é inválida.");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("Não foi possível acessar o arquivo.");
            System.exit(1);
        }

        //Obtém o diretório em que o relatório será salvo
        String diretorioQp = null;
        try {
            diretorioQp = new File(Main.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI().getPath())
                    .getParent();
        } catch (URISyntaxException ex) {
            System.out.println("Não é possível gerar arquivo de relatório no "
                    + "diretório atual.");
            System.exit(1);
        }

        //Realiza as expressões e gera o relatório
        System.out.println("Realizando expressões matemáticas...");
        Relatorio gerador = new Relatorio(testes, exportHtml);
        try {
            gerador.gerarRelatorio(diretorioQp);
        } catch (IOException ex) {
            System.out.println("Não foi possível gerar arquivo de relatório.");
            System.exit(1);
        }
        System.out.println("Expressões realizadas.");
        if (gerador.todosSucessos()) {
            System.out.println("Todos os testes obtiveram SUCESSO.");
        } else {
            System.out.println("Houveram testes que FALHARAM.");
        }
        if (exportHtml) {
            System.out.println("Relatório HTML salvo em \"" + diretorioQp
                    + "\\relatorio.html.\"");
        } else {
            System.out.println("Relatório JSON salvo em \"" + diretorioQp
                    + "\\relatorio.json.\"");
        }
        System.exit(0);
    }

}
