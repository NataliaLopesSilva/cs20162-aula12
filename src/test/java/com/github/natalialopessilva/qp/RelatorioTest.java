package com.github.natalialopessilva.qp;

/*
 * Copyright (c) 2016. FÃ¡brica de Software - Instituto de InformÃ¡tica (UFG)
 * Creative Commons Attribution 4.0 International License.
 */
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * Classe de testes, responsÃ¡vel por realizar os testes de cobertura para
 * classe Relatorio.
 */
public class RelatorioTest {

    List<Expressoes> listaExpressoes = new ArrayList<Expressoes>();

    @Test
    public void testeGeraHtml() throws Exception {
        List<String> testes = new ArrayList<>();
        testes.add("a + b; a=1, b=2; 3");
        testes.add("5 / (6 - 5);;5");
        testes.add("(4 + x) * (9 - y); x = 0, y=0; 36");
        testes.add("(4 + x) * (9 - y);; 36");
        testes.add("3.14 * (z + 1);;3.14");
        testes.add("1 + 2;;5");
        String diretorio = new File(Main.class.getProtectionDomain()
                .getCodeSource().getLocation().toURI().getPath())
                .getParent();
        Path file = Paths.get(diretorio + "/teste.txt");
        Files.write(file, testes, Charset.forName("UTF-8"));

        Main.main(new String[]{diretorio + "/teste.txt",
            "-h"});

        Files.delete(file);
    }

    @Test
    public void testeGeraJson() throws IOException, URISyntaxException, Exception {
        List<String> testes = new ArrayList<>();
        testes.add("a + b; a=1, b=2; 3");
        testes.add("5 / (6 - 5);;5");
        testes.add("(4 + x) * (9 - y); x = 0, y=0; 36");
        testes.add("(4 + x) * (9 - y);; 36");
        testes.add("3.14 * (z + 1);;3.14");
        testes.add("1 + 2;;5");
        String diretorio = new File(Main.class.getProtectionDomain()
                .getCodeSource().getLocation().toURI().getPath())
                .getParent();
        Path file = Paths.get(diretorio + "/teste.txt");
        Files.write(file, testes, Charset.forName("UTF-8"));

        Main.main(new String[]{diretorio + "/teste.txt"});

        Files.delete(file);
    }
}
