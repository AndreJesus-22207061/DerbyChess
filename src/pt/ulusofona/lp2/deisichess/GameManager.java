package pt.ulusofona.lp2.deisichess;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class GameManager {

    Tabuleiro tabuleiro;

    boolean loadGame(File file) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            return false;
        }

        try{
            String linha;
            boolean primeiraLinha = true;
            boolean segundaLinha = false;
            int tamanhoTabuleiro = 0;
            int count = 0;
            int countColunas = 0;
            int numeroPecas = 0;
            ArrayList<Peca> pecas = new ArrayList<>();



            while((linha = reader.readLine()) != null){
                if(primeiraLinha){
                    tamanhoTabuleiro = Integer.parseInt(linha);

                    primeiraLinha = false;
                    segundaLinha= true;
                    continue;
                }
                if(segundaLinha){
                    numeroPecas = Integer.parseInt(linha);
                    segundaLinha = false;
                    continue;
                }

                if(primeiraLinha== false && segundaLinha == false && count<numeroPecas){
                    String[] partes = linha.split(":");
                    int idPeca = Integer.parseInt(partes[0]);
                    int tipoPeca = Integer.parseInt(partes[1]);
                    int equipaPeca = Integer.parseInt(partes[2]);
                    String alcunhaPeca = partes[3];

                    Peca peca = new Peca(idPeca,tipoPeca,equipaPeca,alcunhaPeca);
                    pecas.add(peca);
                    count++;
                    continue;
                }

                if(count==numeroPecas){
                    String[] partes = linha.split(":");
                    int countlinhas = 0;
                    for(String parte : partes){
                        int parteInt = Integer.parseInt(parte);
                        if(parteInt==0){
                            countlinhas++;
                        }else{
                            for(Peca peca : pecas){
                                if(peca.getID()==parteInt){
                                    peca.setCoordenadas(countlinhas,countColunas);
                                    countlinhas++;
                                    break;
                                }
                            }
                        }
                    }
                    countColunas++;
                }
            }
            tabuleiro = new Tabuleiro(tamanhoTabuleiro,pecas);
            tabuleiro.inicializarTabuleiro();


            reader.close();
        } catch (IOException e) {
            return false;
        }


        return true;
    }


    void reset(){
        tabuleiro = new Tabuleiro(0,null);
    }

    int getBoardSize(){
        return tabuleiro.getTamanho();
    }



    boolean move( int x0, int y0, int x1, int y1){
        String[] informacaoPeca = tabuleiro.buscarInformacaoQuadrado(x0,y0);
        int equipaPeca = Integer.parseInt(informacaoPeca[2]);

        String[] informacaoDestino = tabuleiro.buscarInformacaoQuadrado(x1,y1);
        int equipaDestino = Integer.parseInt(informacaoDestino[2]);

        if (equipaPeca!=tabuleiro.getEquipaAjogar()) {
            //jogada invalida turno invalido
            return false;
        }

        if(equipaPeca == equipaDestino){
            //jogada invalida peca da mesma equipa no destino
            return false;
        }



        return true;
    }

    String[] getSquareInfo(int x, int y){
        return tabuleiro.buscarInformacaoQuadrado(x,y);
    }

    String[] getPieceInfo(int ID){
        return tabuleiro.buscarInfomacaoPeca(ID);
    }

    String getPieceInfoAsString(int ID){
        return tabuleiro.buscarInformacaoPecaAsString(ID);
    }

    int getCurrentTeamID(){

        return 1;
    }

    boolean gameOver(){

        return true;
    }

    ArrayList<String> getGameResults(){
        ArrayList<String> aaa = new ArrayList<>();
        return aaa;
    }

    JPanel getAuthorsPanel(){

        return new JPanel();
    }


}


