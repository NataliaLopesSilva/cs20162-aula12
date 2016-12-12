package com.github.natalialopessilva.qp;

/*
 * Copyright (c) 2016. FÃ¡brica de Software - Instituto de InformÃ¡tica (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
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
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * Classe de testes, responsÃ¡vel por realizar os testes de cobertura para
 * classe Leitura.
 */
public class LeituraTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

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

    @Test
    public void testeLeituraArquivoLocalErro() throws IOException, URISyntaxException, Exception {
        exit.expectSystemExitWithStatus(1);
        Main.main(new String[]{"c:\\diretorioInexistente\\teste.txt"});
    }
    
    @Test
    public void testeLeituraArquivoURLErro() throws IOException, URISyntaxException, Exception {
        exit.expectSystemExitWithStatus(1);
        Main.main(new String[]{"http://lennusoft.16mb.com/testes.txt"});
    }
}
