/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nelsonwilliam.qp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author aluno
 */
public final class LeituraArquivo {

    /**
     * Tenta obter o conteúdo de um arquivo de texto no computador ou em um
     * servidor.
     *
     * @param path Local do arquivo a ser obtido, local ou remoto (http).
     * @param local Se falso, o arquivo será obtido de um servidor remoto via
     * internet. Se verdade, o arquivo está no computador do usuário.
     * @return Lista com as linhas do arquivo obtido.
     * @throws java.io.FileNotFoundException Caso o arquivo não exista.
     */
    public static List<String> obterLinhas(String path, boolean local)
            throws FileNotFoundException {

        List<String> linhas = new ArrayList<>();

        if (local) {
            //Obtém o arquivo local
            Scanner leitor = new Scanner(new FileReader(path));
            while (leitor.hasNextLine()) {
                linhas.add(leitor.nextLine());
            }
        } else {
            //Obtém o arquivo remotamente
            //...
        }

        return linhas;
    }

}
