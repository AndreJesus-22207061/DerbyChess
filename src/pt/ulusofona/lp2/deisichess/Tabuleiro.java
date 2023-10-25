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
        for (Peca peca : pecas) {
            int x = peca.getX();
            int y = peca.getY();

            if (x >= 0 && x < tamanho && y >= 0 && y < tamanho) {
                tabuleiro[x][y] = peca;
            }
        }
    }


    int getTamanho(){
        return tamanho;
    }

    String[] buscarInfomacaoPeca(int ID) {
        String[] informacaoPeca = new String[7];

        for (int x = 0; x < tamanho; x++) {
            for (int y = 0; y < tamanho; y++) {
                Peca peca = tabuleiro[x][y];

                if (peca != null && peca.getID() == ID) {
                    informacaoPeca[0] = String.valueOf(peca.getID());
                    informacaoPeca[1] = String.valueOf(peca.getTipo());
                    informacaoPeca[2] = String.valueOf(peca.getEquipa());
                    informacaoPeca[3] = peca.getAlcunha();
                    informacaoPeca[4] = peca.estaEmJogo();
                    informacaoPeca[5] = String.valueOf(x);
                    informacaoPeca[6] = String.valueOf(y);
                    return informacaoPeca;
                }
            }
        }
        return new String[0];
    }



    public String[] buscarInformacaoQuadrado(int x, int y) {
        String[] informacaoQuadrado = new String[5];

        if (x < 0 || x >= tamanho || y < 0 || y >= tamanho) {
            return null;
        }

        Peca peca = tabuleiro[x][y];

        if (peca != null) {
            informacaoQuadrado[0] = String.valueOf(peca.getID());
            informacaoQuadrado[1] = String.valueOf(peca.getTipo());
            informacaoQuadrado[2] = String.valueOf(peca.getEquipa());
            informacaoQuadrado[3] = peca.getAlcunha();
            informacaoQuadrado[4] = null;
            return informacaoQuadrado;
        }else{
            return new String[0];
        }
    }


    String buscarInformacaoPecaAsString(int ID){
        for (Peca peca : pecas) {
            if(peca.getID() ==ID){
                return peca.toString();
            }

        }
       return null;
    }








}


