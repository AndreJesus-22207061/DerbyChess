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


    int getTamanho(){
        return tamanho;
    }

    String[] buscarInfomacaoPeca (int ID){
        String[]informacaoPeca = new String[7];
        for (Peca peca : pecas) {
            if(peca.getID()== ID) {
                informacaoPeca[0] = String.valueOf(peca.getID());
                informacaoPeca[1] = String.valueOf(peca.getTipo());
                informacaoPeca[2] = String.valueOf(peca.getEquipa());
                informacaoPeca[3] = peca.getAlcunha();
                informacaoPeca[4] = peca.estaEmJogo();
                informacaoPeca[5] = String.valueOf(peca.getX());
                informacaoPeca[6] = String.valueOf(peca.getY());
                return informacaoPeca;
            }
        }
        return null;
    }








}


