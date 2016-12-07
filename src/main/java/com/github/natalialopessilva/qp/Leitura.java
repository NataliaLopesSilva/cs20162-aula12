/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.natalialopessilva.qp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Implementação da Classe Leitura.
 *
 * <p>
 * Responsável por obter a leitura dos arquivos fornecidos pelo usuário, que
 * executa as funções pertinentes ao tipo de arquivo desejado pelo usuário, seja
 * ele local ou te um tipo url.
 *
 */
public final class Leitura {

    /**
     * Construtor da classe Leitura que impede que a mesma seja instanciada ou
     * acessada.
     */
    private Leitura() {
    }

    /**
     * Leitura de arquivo de um diretório local/arquivo local, e leitura de
     * linhas do arquivo, além de ler o arquivo passado pelo usuário que está em
     * um diretório local , o método também lê linha a linha do arquivo e as
     * separa, colocando-as em uma lista de Strings.
     *
     * @param diretorio que indica o endereço onde o documento de texto, com os
     * testes está (Ex: c:/testes.txt).
     *
     * @return listaLinhas, que consiste em uma lista a qual cada posição, está
     * contida uma linha do arquivo local lido anteriormente.
     * @throws java.lang.Exception se ocorrer algum tipo de erro na abertura do
     * arquivo.
     *
     */
    public static ArrayList<String> leituraArquivoLocal(final String diretorio)
            throws Exception {

        List<String> listaLinhas = new ArrayList<>();

        try {
            try (FileReader arq = new FileReader(diretorio)) {
                BufferedReader lerArq = new BufferedReader(arq);

                String linha = lerArq.readLine();

                while (linha != null) {
                    listaLinhas.add(linha);
                    linha = lerArq.readLine();
                }
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo local: %s.\n",
                    e.getMessage());
        }

        System.out.println();
        return (ArrayList<String>) listaLinhas;
    }

    /**
     * Leitura de arquivo de um diretório web/ arquivo URL, e leitura de linhas
     * do arquivo, além de ler o arquivo passado pelo usuário que está em uma
     * URL ,o método também lê linha a linha do arquivo e as separa,
     * colocando-as em uma lista de Strings.
     *
     * @param diretorio que indica o endereço onde o documento de texto, com os
     * testes está (Ex: http://lennusoft.16mb.com/open/testes.txt).
     *
     * @return listaLinhas, que consiste em uma lista a qual cada posição, está
     * contida uma linha do arquivo url lido anteriormente.
     * @throws java.lang.Exception se ocorrer algum tipo de erro na abertura do
     * arquivo.
     *
     */
    public static List<String> leituraArquivoUrl(final String diretorio)
            throws Exception {
        List<String> linhas = new ArrayList<>();

        try {
            URL url = new URL(diretorio);
            HttpURLConnection connection
                    = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (Scanner read = new Scanner(
                    new InputStreamReader(connection.getInputStream()))) {
                while (read.hasNextLine()) {
                    linhas.add(read.nextLine().replace(" ", ""));
                }
            }
        } catch (Exception e) {
            System.err.printf("Erro na abertura do arquivo URL: %s.\n",
                    e.getMessage());
        }

        return linhas;
    }
}
