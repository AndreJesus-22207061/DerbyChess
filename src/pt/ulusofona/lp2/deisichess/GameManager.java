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
            InvalidGameInputException, IOException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {

        }

        try {
            String linha;
            boolean primeiraLinha = true;
            boolean segundaLinha = false;
            int tamanhoTabuleiro = 0;
            int count = 0;
            int countColunas = 0;
            int numeroPecas = 0;
            ArrayList<Peca> pecas = new ArrayList<>();


            while ((linha = reader.readLine()) != null) {
                if (primeiraLinha) {
                    tamanhoTabuleiro = Integer.parseInt(linha);

                    primeiraLinha = false;
                    segundaLinha = true;
                    continue;
                }
                if (segundaLinha) {
                    numeroPecas = Integer.parseInt(linha);
                    segundaLinha = false;
                    continue;
                }

                if (primeiraLinha == false && segundaLinha == false && count < numeroPecas) {
                    String[] partes = linha.split(":");
                    int idPeca = Integer.parseInt(partes[0]);
                    int tipoPeca = Integer.parseInt(partes[1]);
                    int equipaPeca = Integer.parseInt(partes[2]);
                    String alcunhaPeca = partes[3];

                    // vai criar um novo contador para cada equipa nova


                    Peca peca = switch (tipoPeca) {
                        case 0 -> new Rei(idPeca, tipoPeca, equipaPeca, alcunhaPeca);
                        case 1 -> new Rainha(idPeca, tipoPeca, equipaPeca, alcunhaPeca);
                        case 2 -> new PoneiMagico(idPeca, tipoPeca, equipaPeca, alcunhaPeca);
                        case 3 -> new PadreDaVila(idPeca, tipoPeca, equipaPeca, alcunhaPeca);
                        case 4 -> new TorreHorizontal(idPeca, tipoPeca, equipaPeca, alcunhaPeca);
                        case 5 -> new TorreVertical(idPeca, tipoPeca, equipaPeca, alcunhaPeca);
                        case 6 -> new HomerSimpson(idPeca, tipoPeca, equipaPeca, alcunhaPeca);
                        case 7 -> new Joker(idPeca, tipoPeca, equipaPeca, alcunhaPeca);
                        default -> throw new IllegalStateException("Tipo de peca Invalido " + tipoPeca);
                    };

                    pecas.add(peca);
                    count++;
                    continue;
                }

                if (count == numeroPecas) {
                    String[] partes = linha.split(":");
                    int countlinhas = 0;
                    for (String parte : partes) {
                        int parteInt = Integer.parseInt(parte);
                        if (parteInt == 0) {
                            countlinhas++;
                        } else {
                            for (Peca peca : pecas) {
                                if (peca.getID() == parteInt) {
                                    peca.setCoordenadas(countlinhas, countColunas);
                                    countlinhas++;
                                    break;
                                }
                            }
                        }
                    }
                    countColunas++;
                }
            }

            tabuleiro = new Tabuleiro(tamanhoTabuleiro, pecas);
            tabuleiro.inicializarTabuleiro();

            for (Peca peca : tabuleiro.getListaPecas()) {
                int id = peca.getID();

                if (tabuleiro.getPecaPorIDTabuleiro(id) == null) {
                    peca.capturada();
                }
                tabuleiro.adicionarNovoContador(peca.getEquipa());
                tabuleiro.adicionarPecaAEquipa(peca); //adicionar as pecas a lista de cada equipa
            }


            reader.close();
        } catch (IOException e) {

        }


    }


    public void reset() {
        tabuleiro = new Tabuleiro(0, null);
    }

    public int getBoardSize() {
        return tabuleiro.getTamanho();
    }


    public boolean move(int x0, int y0, int x1, int y1) {


        boolean foiCapturada = false;

        CountJogadas contadorEquipaAJogar = tabuleiro.getContadorEquipa(tabuleiro.getEquipaAJogar());

        ContadorRondas contadorRondas = tabuleiro.getContadorRondas();

        int rondaAtual = contadorRondas.getRondaAtual();
        int rondaJoker = contadorRondas.getRondasJoker();
        int rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        //------------Confirmacoes Peca--------------------//

        //  ContadorJogadas contadorJogadas = tabuleiro.getContadorJogadas();

        Peca peca = tabuleiro.getPeca(x0, y0);


        if (peca == null) {
            contadorEquipaAJogar.jogadaInvalida();
            //jogada invalida esta a mover o vazio
            return false;
        }

        int equipaPeca = peca.getEquipa();

        CountJogadas contadorEquipaPeca = tabuleiro.getContadorEquipa(equipaPeca);


        if (!(peca.validMove(x1, y1, tabuleiro))) {
            contadorEquipaPeca.jogadaInvalida();
            //jogada invalida nao se pode mover para essa coordenada
            return false;
        }

        if (equipaPeca != tabuleiro.getEquipaAJogar()) {
            contadorEquipaPeca.jogadaInvalida();
            //jogada invalida turno invalido
            return false;
        }

        //------------Confirmacoes destino--------------------//

        Peca pecaDestino = tabuleiro.getPeca(x1, y1);

        if (pecaDestino != null) {  //Se no destino houver peca

            int equipaDestino = pecaDestino.getEquipa();

            CountJogadas contadorEquipaPecaDestino = tabuleiro.getContadorEquipa(equipaDestino);


            if (equipaPeca == equipaDestino) {
                contadorEquipaPecaDestino.jogadaInvalida();
                //jogada invalida peca da mesma equipa no destino
                return false;
            } else {
                foiCapturada = true;
                contadorRondas.resetRondasSemCaptura();
                pecaDestino.capturada();
                contadorEquipaPeca.pecaFoiCapturada();   //!!!!!!!!!!!!!! pode estar erro aqui !!!!!!!!!!!!!!!!
                tabuleiro.removerPeca(x1, y1);
            }
        }

        CountJogadas countPreta = tabuleiro.getContadorEquipa(10);  //caso se crie uma nova equipa criar adicionar countNovo
        CountJogadas countBranca = tabuleiro.getContadorEquipa(20);

        //Se no destino não houver peca é so mover
        if ((!foiCapturada && countBranca.getPecasCapturadas() >= 1) || (!foiCapturada && countPreta.getPecasCapturadas() >= 1)) {
            contadorRondas.jogadaConcluidaSemCaptura();
        }


        if (contadorRondas.getRondasJoker() == 6) {
            contadorRondas.resetRondaJoker();
        } else {
            contadorRondas.incrementaRondaJoker();
        }


        tabuleiro.movePeca(x0, y0, x1, y1);
        peca.setCoordenadas(x1, y1);
        contadorEquipaPeca.jogadaValida();
        //contadorRondas.incrementaRondaAtual();     //!!!!!!!!! pode estar errado !!!!!!!!//
        contadorRondas.incrementaRondaAtual();
        tabuleiro.mudarEquipaAjogar();


        Jogada jogada = new Jogada(x0, y0, x1, y1, foiCapturada, rondaAtual, rondaJoker, rondasSemCaptura,
                contadorEquipaAJogar.getJogadasInvalidas());

        CountJogadas contadorPretas = tabuleiro.getContadorEquipa(10);
        CountJogadas contadorBrancas = tabuleiro.getContadorEquipa(20);




        if (foiCapturada) {
            jogada.addIDPecaCapturada(pecaDestino.getID());
        }

        tabuleiro.adicionarJogadaAoHistorico(jogada);

        return true;

    }

    public String[] getSquareInfo(int x, int y) {
        return tabuleiro.buscarInformacaoQuadrado(x, y);
    }

    public String[] getPieceInfo(int ID) {
        return tabuleiro.buscarInfomacaoPeca(ID);
    }

    public String getPieceInfoAsString(int ID) {
        return tabuleiro.buscarInformacaoPecaAsString(ID);
    }

    public int getCurrentTeamID() {
        return tabuleiro.getEquipaAJogar();
    }

    public boolean gameOver() {

        ContadorRondas contadorRondas = tabuleiro.getContadorRondas();

        CountJogadas contadorPretas = tabuleiro.getContadorEquipa(10);
        int pecasCapturadasPorPreta = contadorPretas.getPecasCapturadas();

        CountJogadas contadorBrancas = tabuleiro.getContadorEquipa(20);
        int pecasCapturadasPorBranca = contadorBrancas.getPecasCapturadas();

        ArrayList<Peca> equipaPreta = tabuleiro.getListaPretas();
        ArrayList<Peca> equipaBranca = tabuleiro.getListaBrancas();

        if (tabuleiro.isXequeMate(equipaPreta)) {
            contadorRondas.defineResultado("VENCERAM AS BRANCAS");
            return true;
        }

        if (pecasCapturadasPorBranca == equipaPreta.size()) {
            contadorRondas.defineResultado("VENCERAM AS BRANCAS");
            return true;
        }

        if (tabuleiro.isXequeMate(equipaBranca)) {
            contadorRondas.defineResultado("VENCERAM AS PRETAS");
            return true;
        }

        if (pecasCapturadasPorPreta == equipaBranca.size()) {
            contadorRondas.defineResultado("VENCERAM AS PRETAS");
            return true;
        }

        if (pecasCapturadasPorBranca == (equipaPreta.size() - 1) && pecasCapturadasPorPreta == (equipaBranca.size() - 1)) {
            contadorRondas.defineResultado("EMPATE");
            return true;
        }

        if (contadorRondas.getRondasSemCaptura() == 10) {
            contadorRondas.defineResultado("EMPATE");
            return true;
        }
        return false;
    }

    public ArrayList<String> getGameResults() {
        return tabuleiro.menuFinal();
    }

    public JPanel getAuthorsPanel() {
        JPanel creditos = new JPanel();
        JLabel imageLabel = new JLabel();

        ImageIcon imageIcon = new ImageIcon("src/images/powerpuffboys.png");
        imageLabel.setIcon(imageIcon);

        creditos.add(imageLabel);


        return creditos;
    }


    public void saveGame(File file) throws IOException {

    }


    public void undo() {
        if (tabuleiro != null) {
            Jogada ultimaJogada = tabuleiro.obterUltimaJogada();

            if (ultimaJogada != null) { // Se existe uma jogada no historico

                ContadorRondas contadorRondas = tabuleiro.getContadorRondas();

                Peca pecaQueJogou = tabuleiro.getPeca(ultimaJogada.getXDestino(), ultimaJogada.getYDestino());

                CountJogadas countPecaQueJogou = tabuleiro.getContadorEquipa(pecaQueJogou.getEquipa());


                if (ultimaJogada.houveCaptura()) {
                    countPecaQueJogou.decrementaPecaFoiCapturada();
                }

                countPecaQueJogou.decrementaJogadaValida();


                int rondaAtualJogada = ultimaJogada.getRondaAtualJogada();
                int rondaJokerJogada = ultimaJogada.getRondaJokerJogada();
                int rondaSemCapturaJogada = ultimaJogada.getRondaSemCapturaJogada();

                contadorRondas.alteraRondaAtual(rondaAtualJogada);
                contadorRondas.alteraRondaJoker(rondaJokerJogada);
                contadorRondas.alteraRondaSemCaptural(rondaSemCapturaJogada);
                countPecaQueJogou.alteraJogadasInvalidas(ultimaJogada.getJogadasInvalidas());

                tabuleiro.restaurarTabuleiro(ultimaJogada);
                tabuleiro.mudarEquipaAjogar();
            }
        }
    }


    public List<Comparable> getHints(int x, int y) {

        Peca pecaSelecionada = tabuleiro.getPeca(x, y);
        List<Comparable> listaDeSugestoes = new ArrayList<Comparable>();

        if (x < 0 || x >= tabuleiro.getTamanho() || y < 0 || y >= tabuleiro.getTamanho()) {
            return null;
        }

        if (pecaSelecionada == null) {
            return null;
        }

        if (pecaSelecionada.getEquipa() != tabuleiro.getEquipaAJogar()) {
            return null;
        }

        for (int xPosicao = 0; xPosicao < tabuleiro.getTamanho(); xPosicao++) {
            for (int yPosicao = 0; yPosicao < tabuleiro.getTamanho(); yPosicao++) {   //usado para verificar ao inicio se o ficheiro ja vem
                Peca pecaSugestao = tabuleiro.getPeca(xPosicao, yPosicao);

                int valor = 0;

                if (pecaSugestao != null) {
                    valor = pecaSugestao.getValor();

                    if (pecaSugestao.getEquipa() == pecaSelecionada.getEquipa()) {
                        continue;
                    }
                }

                if (pecaSelecionada.validMove(xPosicao, yPosicao, tabuleiro)) {
                    Comparable sugestao = new Comparable(xPosicao, yPosicao, valor);
                    listaDeSugestoes.add(sugestao);
                }


            }
        }
        return listaDeSugestoes;
    }

    public Map<String, String> customizeBoard() {

        Map<String, String> aaa = new HashMap<>();

        return aaa;
    }


}


