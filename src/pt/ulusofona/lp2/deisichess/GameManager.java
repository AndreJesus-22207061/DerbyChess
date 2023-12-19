package pt.ulusofona.lp2.deisichess;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {

    Tabuleiro tabuleiro;

    public GameManager() {
    }

    public void loadGame(File file) throws
            InvalidGameInputException, IOException{
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {

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

                    Peca peca;

                    switch (tipoPeca) {
                        case 0 :
                            peca = new Rei(idPeca,tipoPeca,equipaPeca,alcunhaPeca);
                            break;
                        case 1 :
                            peca = new Rainha(idPeca,tipoPeca,equipaPeca,alcunhaPeca);
                            break;
                        case 2 :
                            peca = new PoneiMagico(idPeca,tipoPeca,equipaPeca,alcunhaPeca);
                            break;
                        case 3 :
                            peca = new PadreDaVila(idPeca,tipoPeca,equipaPeca,alcunhaPeca);
                            break;
                        case 4 :
                            peca = new TorreHorizontal(idPeca,tipoPeca,equipaPeca,alcunhaPeca);
                            break;
                        case 5 :
                            peca = new TorreVertical(idPeca,tipoPeca,equipaPeca,alcunhaPeca);
                            break;
                        case 6 :
                            peca = new HomerSimpson(idPeca,tipoPeca,equipaPeca,alcunhaPeca);
                            break;
                        case 7 :
                             peca = new Joker(idPeca,tipoPeca,equipaPeca,alcunhaPeca);
                             break;
                        default:
                            throw new IllegalStateException("Tipo de peca Invalido " + tipoPeca);
                    }

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

            for(Peca peca : tabuleiro.getListaPecas()){
                int id = peca.getID();

                if(tabuleiro.getPecaPorID(id)== null){
                    peca.capturada();
                }
                tabuleiro.adicionarPecaAEquipa(peca); //adicionar as pecas a lista de cada equipa
            }


            reader.close();
        } catch (IOException e) {

        }


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
            contadorJogadas.jogadaInvalida(tabuleiro.getEquipaAJogar());
            //jogada invalida esta a mover o vazio
            return false;
        }

        int equipaPeca = peca.getEquipa();


        if(!(peca.validMove(x1,y1,tabuleiro))){
            contadorJogadas.jogadaInvalida(equipaPeca);
            //jogada invalida nao se pode mover para essa coordenada
            return false;
        }

        if (equipaPeca!=tabuleiro.getEquipaAJogar()) {
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

        if(contadorJogadas.getRondasJoker() == 6){
            contadorJogadas.resetRondaJoker();
        }


        tabuleiro.movePeca( x0, y0, x1, y1);
        peca.setCoordenadas(x1,y1);
        contadorJogadas.jogadaValida(equipaPeca);
        contadorJogadas.incrementaRondaJoker();
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
     return tabuleiro.getEquipaAJogar();
    }

    public boolean gameOver() {
        ContadorJogadas contadorJogadas = tabuleiro.getContadorJogadas();
        ArrayList<Peca> equipaPreta = tabuleiro.getListaPretas();
        ArrayList<Peca> equipaBranca = tabuleiro.getListaBrancas();

        int[] informacoes = tabuleiro.informacaoPecasCapturadas();
        int pecasCapturadasPreta = informacoes[0];
        int pecasCapturadasBranca = informacoes[1];


        if (pecasCapturadasPreta == equipaPreta.size()) {
            contadorJogadas.defineResultado("VENCERAM AS BRANCAS");
            return true;
        }

        if (pecasCapturadasBranca == equipaBranca.size()) {
            contadorJogadas.defineResultado("VENCERAM AS PRETAS");
            return true;
        }

        if (pecasCapturadasPreta == (equipaPreta.size() - 1) && pecasCapturadasBranca == (equipaBranca.size() - 1)) {
            contadorJogadas.defineResultado("EMPATE");
            return true;
        }

        if(contadorJogadas.getContadorSemCaptura() == 10){
            contadorJogadas.defineResultado("EMPATE");
            return true;
        }
        return false;
    }

    public ArrayList<String> getGameResults(){
        ContadorJogadas contadorJogadas = tabuleiro.getContadorJogadas();

        return contadorJogadas.menuFinal();
    }

    public JPanel getAuthorsPanel() {
        JPanel creditos = new JPanel();
        JLabel imageLabel = new JLabel();

        ImageIcon imageIcon = new ImageIcon("src/images/powerpuffboys.png");
        imageLabel.setIcon(imageIcon);

        creditos.add(imageLabel);


        return creditos;
    }


    public void saveGame(File file) throws IOException{

    }


    public void undo(){

    }


    public List<Comparable> getHints(int x, int y){

        List<Comparable> aaa = new ArrayList<>();
        return aaa;
    }

    public Map<String,String> customizeBoard(){

        Map<String,String> aaa = new HashMap<>();

        return aaa;
    }



}


