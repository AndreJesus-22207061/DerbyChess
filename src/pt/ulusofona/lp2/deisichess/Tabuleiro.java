package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Tabuleiro {
    int tamanho;
    Peca[][] tabuleiro;

    ArrayList<Peca> pecas;

    public Tabuleiro(int tamanho,ArrayList<Peca>pecas) {
        this.tamanho = tamanho;
        this.tabuleiro = new Peca[tamanho][tamanho];
        this.pecas = pecas;
    }

    public void inicializarTabuleiro() {
        // Coloque as peças iniciais no tabuleiro, como reis, rainhas, torres, etc.
        // Implemente isso de acordo com as regras do xadrez.
    }






}
