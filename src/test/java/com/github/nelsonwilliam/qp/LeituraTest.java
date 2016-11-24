/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.nelsonwilliam.qp;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class LeituraTest {

    @Test
    public void obterLinhasOnline() throws MalformedURLException, IOException {
        Leitura.obterLinhas("http://lennusoft.16mb.com/open/testes.txt", false);
    }

    @Test
    public void obterLinhasLocal() throws MalformedURLException, IOException,
            URISyntaxException {
        //cria arquivo local apenas pra testes//
        List<String> testeArq = new ArrayList<>();
        testeArq.add("a + b; a=1, b=2; 3");
        testeArq.add("5 / (6 - 5);;5");
        String diretorio = new File(Main.class.getProtectionDomain()
                .getCodeSource().getLocation().toURI().getPath())
                .getParent();
        Path file = Paths.get(diretorio + "/teste.txt");
        Files.write(file, testeArq, Charset.forName("UTF-8"));

        //testes//
        Leitura.obterLinhas(diretorio + "/teste.txt", true);

        //deleta o arquivo criado//
        Files.delete(file);
    }

    @Test(expected = IOException.class)
    public void obterLinhasLocalFalso() throws MalformedURLException,
            IOException {

        Leitura.obterLinhas("c:\\localInexistente\\imaginoeu.txt", true);
    }

}
