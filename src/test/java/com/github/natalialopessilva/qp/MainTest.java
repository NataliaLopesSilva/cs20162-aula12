package com.github.natalialopessilva.qp;

import java.net.URISyntaxException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class MainTest {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void execucaoNoMainParaHtmlComURL() throws Exception {
        Main.main(new String[]{"http://lennusoft.16mb.com/open/testes.txt",
            "-h"});
    }

    @Test
    public void execucaoNoMainParaJsonComURL() throws Exception {
        Main.main(new String[]{"http://lennusoft.16mb.com/open/testes.txt"});
    }

    @Test
    public void execucaoNoMainParaHtmlComArquivoLocal() throws Exception {
        Main.main(new String[]{"c://testes.txt",
            "-h"});
    }

    @Test
    public void execucaoNoMainParaJsonComArquivoLocal() throws Exception {
        Main.main(new String[]{"c://testes.txt"});
    }

    @Test
    public void argsInvalidoVazio() throws Exception, URISyntaxException {
        exit.expectSystemExitWithStatus(1);
        Main.main(new String[]{});
    }

    @Test
    public void argsInvalidoMaximo() throws Exception {
        exit.expectSystemExitWithStatus(1);
        Main.main(new String[]{"http://lennusoft.16mb.com/open/testes.txt",
            "-h" + "j"});
    }
}
