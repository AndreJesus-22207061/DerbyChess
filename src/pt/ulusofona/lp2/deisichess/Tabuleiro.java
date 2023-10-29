package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Tabuleiro {
    int tamanho;
    Peca[][] tabuleiro;

    ArrayList<Peca> pecas;

    int equipaAjogar;

    ContadorJogadas contadorJogadas;



    public Tabuleiro(int tamanho,ArrayList<Peca>pecas) {
        this.tamanho = tamanho;
        this.tabuleiro = new Peca[tamanho][tamanho];
        this.pecas = pecas;
        this.equipaAjogar = 0; //Começa a 0 (preta)
        this.contadorJogadas = new ContadorJogadas();
    }

    public void inicializarTabuleiro() {
        for (Peca peca : pecas) {
            Integer x = peca.getX();
            Integer y = peca.getY();

            if (x >= 0 && x < tamanho && y >= 0 && y < tamanho) {
                tabuleiro[x][y] = peca;
            }
        }
    }

    Peca getPeca(int x , int y){
        return tabuleiro[x][y];
    }

     Peca getPecaPorID(int id) {
        for (int x = 0; x < tamanho; x++) {
            for (int y = 0; y < tamanho; y++) {
                Peca peca = tabuleiro[x][y];
                if (peca != null && peca.getID() == id) {
                    return peca;
                }
            }
        }
        return null;
    }

    ArrayList<Peca> getListaPecas(){
        return this.pecas;
    }


    ContadorJogadas getContadorJogadas(){
        return this.contadorJogadas;
    }


    int getTamanho(){
        return tamanho;
    }

    int getEquipaAjogar(){
        return this.equipaAjogar;
    }

    void mudarEquipaAjogar(){
        if(equipaAjogar==0){
            equipaAjogar=1;
        }else{
            equipaAjogar=0;
        }
    }

     void movePeca(int x0, int y0, int x1, int y1) {
        tabuleiro[x1][y1] = tabuleiro[x0][y0];
        tabuleiro[x0][y0] = null;
    }

    void removerPeca(int x , int y){
        tabuleiro[x][y]= null;
    }



    String[] buscarInfomacaoPeca(int ID) {
        String[] informacaoPeca = new String[7];

        for (Peca peca : pecas) {
            if (peca.getID() == ID) {
                informacaoPeca[0] = String.valueOf(peca.getID());
                informacaoPeca[1] = String.valueOf(peca.getTipo());
                informacaoPeca[2] = String.valueOf(peca.getEquipa());
                informacaoPeca[3] = peca.getAlcunha();
                informacaoPeca[4] = peca.estaEmJogo();

                if (peca.estaEmJogo().equals("capturado")) {
                    informacaoPeca[5] = "";
                    informacaoPeca[6] = "";
                } else {
                    informacaoPeca[5] = String.valueOf(peca.getX());
                    informacaoPeca[6] = String.valueOf(peca.getY());
                }

                return informacaoPeca;
            }
        }
        return informacaoPeca;
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
            if(peca.getEquipa()==0){
                informacaoQuadrado[4] = "pecaPreta.png";
            }else{
                informacaoQuadrado[4] = "pecaBranca.png";
            }
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

    int[] informacaoPecasCapturadas() {
        int[] arrayInfo = new int[3];
        int countEquipaPreta = 0;
        int countEquipaBranca = 0;
        int numeroPecasTotal = pecas.size();


        for (Peca peca : pecas) {
            if (!(peca.getEstado())) {
                if (peca.getEquipa() == 0) {
                    countEquipaPreta++;
                } else {
                    countEquipaBranca++;
                }
            }
        }

        arrayInfo[0] = countEquipaPreta;
        arrayInfo[1] = countEquipaBranca;
        arrayInfo[2] = numeroPecasTotal;
        return arrayInfo;
    }






}


