/*
 * Copyright (c) 2016. Fábrica de Software - Instituto de Informática (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
package com.github.natalialopessilva.qp;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Classe de testes, responsável por realizar os testes de cobertura para classe
 * Leitura.
 */
public class LeituraTest {

    @Test
    public void testeLeituraArquivoLocal() throws Exception {

        List<String> testeArq = new ArrayList<>();
        testeArq.add("a + b; a=1, b=2; 3");
        String diretorio = new File(Main.class.getProtectionDomain()
                .getCodeSource().getLocation().toURI().getPath())
                .getParent();
        Path file = Paths.get(diretorio + "/teste.txt");
        Files.write(file, testeArq, Charset.forName("UTF-8"));

        Leitura.leituraArquivoLocal(diretorio + "/teste.txt");

        Files.delete(file);
    }

    @Test
    public void testeLeituraArquivoURL() throws MalformedURLException, IOException, Exception {
        String diretorio = "http://lennusoft.16mb.com/open/testes.txt";
        Leitura.leituraArquivoUrl(diretorio);
    }
    
    @Test(expected = Exception.class)
    public void testeLeituraArquivoLocalErro() throws Exception {

        List<String> testeArq = new ArrayList<>();
        testeArq.add("a + b; a=1, b=2; 3");
        String diretorio = new File(Main.class.getProtectionDomain()
                .getCodeSource().getLocation().toURI().getPath())
                .getParent();
        Path file = Paths.get(diretorio + "/teste.txt");
        Files.write(file, testeArq, Charset.forName("UTF-8"));

        Leitura.leituraArquivoLocal(diretorio + "/teste.txt");

        Files.delete(file);
    }
    
}
