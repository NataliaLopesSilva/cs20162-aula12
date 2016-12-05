package com.github.natalialopessilva.qp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Leitura {

    public static ArrayList<String> leituraLinhas() {

        Scanner ler = new Scanner(System.in);
        List<String> listaLinhas = new ArrayList<String>();

        System.out.printf("Informe o nome de arquivo texto:\n");
        String nome = ler.nextLine();

        try {
            FileReader arq = new FileReader(nome);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            while (linha != null) {
                listaLinhas.add(linha);
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }

            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }

        System.out.println();
        return (ArrayList<String>) listaLinhas;
    }
    
    
}
