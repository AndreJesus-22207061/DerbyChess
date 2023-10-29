package pt.ulusofona.lp2.deisichess;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class GameManager {

    Tabuleiro tabuleiro;

 public boolean loadGame(File file) {
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


    public void reset(){
        tabuleiro = new Tabuleiro(0,null);
    }

    public int getBoardSize(){
        return tabuleiro.getTamanho();
    }



    public boolean move( int x0, int y0, int x1, int y1){


        int[] informacoes = tabuleiro.informacaoPecasCapturadas();
        int pecasCapturadasPreta = informacoes[0];
        int pecasCapturadasBranca = informacoes[1];

     boolean foiCapturada = false;
        //------------Confirmacoes Peca--------------------//
        ContadorJogadas contadorJogadas = tabuleiro.getContadorJogadas();

        Peca peca = tabuleiro.getPeca(x0,y0);

        if(peca == null){
            contadorJogadas.jogadaInvalida(tabuleiro.getEquipaAjogar());
            //jogada invalida esta a mover o vazio
            return false;
        }

        int equipaPeca = peca.getEquipa();


        if(!(peca.validMove(x1,y1,tabuleiro))){
            contadorJogadas.jogadaInvalida(equipaPeca);
            //jogada invalida nao se pode mover para essa coordenada
            return false;
        }

        if (equipaPeca!=tabuleiro.getEquipaAjogar()) {
            contadorJogadas.jogadaInvalida(equipaPeca);
            //jogada invalida turno invalido
            return false;
        }

       //------------Confirmacoes destino--------------------//

        Peca pecaDestino = tabuleiro.getPeca(x1,y1);

        if(pecaDestino != null){  //Se no destino houver peca

            int equipaDestino = pecaDestino.getEquipa();

            if(equipaPeca == equipaDestino){
                contadorJogadas.jogadaInvalida(equipaPeca);
                //jogada invalida peca da mesma equipa no destino
                return false;
            }else{
                foiCapturada = true;
                contadorJogadas.resetContadorSemCaptura();
                pecaDestino.capturada();
                contadorJogadas.pecaFoiCapturada(equipaDestino);
                tabuleiro.removerPeca(x1,y1);
            }
        }
        //Se no destino não houver peca é so mover
        if((!foiCapturada && pecasCapturadasBranca >= 1) || (!foiCapturada && pecasCapturadasPreta >=1)){
            contadorJogadas.jogadaConcluidaSemCaptura();
        }
        tabuleiro.movePeca( x0, y0, x1, y1);
        peca.setCoordenadas(x1,y1);
        contadorJogadas.jogadaValida(equipaPeca);
        contadorJogadas.jogadaConcluida();
        tabuleiro.mudarEquipaAjogar();
        return true;


    }
    public  String[] getSquareInfo(int x, int y){
        return tabuleiro.buscarInformacaoQuadrado(x,y);
    }

    public String[] getPieceInfo(int ID){
        return tabuleiro.buscarInfomacaoPeca(ID);
    }

    public String getPieceInfoAsString(int ID){
        return tabuleiro.buscarInformacaoPecaAsString(ID);
    }

    public int getCurrentTeamID(){
     return tabuleiro.getEquipaAjogar();
    }

    public boolean gameOver() {
        ContadorJogadas contadorJogadas = tabuleiro.getContadorJogadas();

        int[] informacoes = tabuleiro.informacaoPecasCapturadas();
        int pecasCapturadasPreta = informacoes[0];
        int pecasCapturadasBranca = informacoes[1];
        int numeroDePecas = informacoes[2];

        if (pecasCapturadasPreta == (numeroDePecas / 2)) {
            return true;
        }

        if (pecasCapturadasBranca == (numeroDePecas / 2)) {
            return true;
        }

        if (pecasCapturadasPreta == ((numeroDePecas / 2) - 1) && pecasCapturadasBranca == ((numeroDePecas / 2) - 1)) {
            return true;
        }

        if(contadorJogadas.getContadorSemCaptura() == 10){
            return true;
        }
        return false;
    }

    public ArrayList<String> getGameResults(){
        ArrayList<String> aaa = new ArrayList<>();
        return aaa;
    }

    public JPanel getAuthorsPanel(){

        return new JPanel();
    }


}


