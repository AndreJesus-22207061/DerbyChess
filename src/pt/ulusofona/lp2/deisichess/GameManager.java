package pt.ulusofona.lp2.deisichess;


import javax.swing.*;
import java.io.*;
import java.util.*;

public class GameManager {

    Tabuleiro tabuleiro;

    public GameManager() {
    }

    Tabuleiro getTabuleiro(){
        return this.tabuleiro;
    }

    public void loadGame(File file) throws
            InvalidGameInputException, IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));


        try {

            if(reader == null){
                throw new IOException();
            }

            String linha;
            boolean primeiraLinha = true;
            boolean segundaLinha = false;
            int tamanhoTabuleiro = 0;
            int count = 0;
            int countColunas = 0;
            int numeroPecas = 0;
            int linhaAtual = 0;
            int estatisticasPeca = 0;


            int pretaPecasCapturadas = 0;
            int pretaJogadasInvalidas = 0;
            int pretaJogadasValidas = 0;

            int brancaPecasCapturadas = 0;
            int brancaJogadasInvalidas = 0;
            int brancaJogadasValidas = 0;

            int rondasJoker = 1;
            int rondasSemCaptura = 0;
            int rondaAtual = 0;

            int equipaAJogar = 10;

            ArrayList<Peca> pecas = new ArrayList<>();

            while ((linha = reader.readLine()) != null) {

                if (primeiraLinha) {
                    linhaAtual++;
                    tamanhoTabuleiro = Integer.parseInt(linha);

                    primeiraLinha = false;
                    segundaLinha = true;
                    continue;
                }
                if (segundaLinha) {
                    linhaAtual++;
                    numeroPecas = Integer.parseInt(linha);
                    segundaLinha = false;
                    continue;
                }

                if (primeiraLinha == false && segundaLinha == false && count < numeroPecas) {
                    linhaAtual++;
                    String[] partes = linha.split(":");

                    if(partes.length>4){
                        throw new InvalidGameInputException("DADOS A MAIS (Esperava: 4 ; Obtive: "+partes.length+")",linhaAtual);
                    }else if(partes.length<4){
                        throw new InvalidGameInputException("DADOS A MENOS (Esperava: 4 ; Obtive: "+partes.length+")",linhaAtual);
                    }

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

                if (count == numeroPecas && countColunas < tamanhoTabuleiro) {
                    linhaAtual++;

                    String[] partes = linha.split(":");
                    if(partes.length>tamanhoTabuleiro){
                        throw new InvalidGameInputException("DADOS A MAIS (Esperava: " +tamanhoTabuleiro+ " ; Obtive: "+partes.length+")",linhaAtual);
                    }else if(partes.length<tamanhoTabuleiro){
                        throw new InvalidGameInputException("DADOS A MENOS (Esperava: " +tamanhoTabuleiro+ " ; Obtive: "+partes.length+")",linhaAtual);
                    }

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


                if (linha.equals("Contadores por equipa:")) {
                    pretaPecasCapturadas = Integer.parseInt(reader.readLine().split(":")[1]);
                    pretaJogadasInvalidas = Integer.parseInt(reader.readLine().split(":")[1]);
                    pretaJogadasValidas = Integer.parseInt(reader.readLine().split(":")[1]);

                    brancaPecasCapturadas = Integer.parseInt(reader.readLine().split(":")[1]);
                    brancaJogadasInvalidas = Integer.parseInt(reader.readLine().split(":")[1]);
                    brancaJogadasValidas = Integer.parseInt(reader.readLine().split(":")[1]);
                }

                if (linha.equals("Contador de Rondas:")) {
                    rondasJoker = Integer.parseInt(reader.readLine().split(":")[1]);
                    rondasSemCaptura = Integer.parseInt(reader.readLine().split(":")[1]);
                    rondaAtual = Integer.parseInt(reader.readLine().split(":")[1]);
                }

                if (linha.equals("Equipa a Jogar:")) {
                    equipaAJogar = Integer.parseInt(reader.readLine().split(":")[1]);
                }

                if (linha.equals("Estatisticas de cada Peca:") || estatisticasPeca ==1){
                    if(estatisticasPeca ==1){

                            String[] partes1 = linha.split("->");
                            int idPeca = Integer.parseInt(partes1[0]);
                            
                            Peca pecaAtual = null;
                        
                            for(Peca peca : pecas ){
                                if(peca.getID() == idPeca){
                                    pecaAtual = peca;
                                    break;
                                }
                            }

                            String []partes2 = partes1[1].split("\\|");

                            String[] parteJogadas = partes2[0].split(":");

                            int jodasValidasPeca =  Integer.parseInt(parteJogadas[0]);
                            int jodasInvalidasPeca =  Integer.parseInt(parteJogadas[1]);

                            CountJogadas countJogadasPeca = pecaAtual.getCountJogadas();
                            countJogadasPeca.alteraJogadasValidas(jodasValidasPeca);
                            countJogadasPeca.alteraJogadasInvalidas(jodasInvalidasPeca);

                            if(partes2.length==1){
                                continue;
                            }

                            String[] partePecasCapturadas = partes2[1].split(":");

                            for(String stringID : partePecasCapturadas){
                                int idPecaCapturada =Integer.parseInt(stringID);
                                pecaAtual.adicionarPecaQueCaptorou(idPecaCapturada);
                            }

                    }

                    estatisticasPeca = 1;

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


            CountJogadas countPreta = tabuleiro.getContadorEquipa(10);
            CountJogadas countBranco = tabuleiro.getContadorEquipa(20);
            ContadorRondas contadorRondas = tabuleiro.getContadorRondas();

            countPreta.alteraPecasCapturadas(pretaPecasCapturadas);
            countPreta.alteraJogadasInvalidas(pretaJogadasInvalidas);
            countPreta.alteraJogadasValidas(pretaJogadasValidas);

            countBranco.alteraPecasCapturadas(brancaPecasCapturadas);
            countBranco.alteraJogadasInvalidas(brancaJogadasInvalidas);
            countBranco.alteraJogadasValidas(brancaJogadasValidas);

            contadorRondas.alteraRondaJoker(rondasJoker);
            contadorRondas.alteraRondaSemCaptural(rondasSemCaptura);
            contadorRondas.alteraRondaAtual(rondaAtual);
            tabuleiro.setEquipaAJogar(equipaAJogar);


            reader.close();


        } catch (FileNotFoundException e) {
            // Trate a exceção de arquivo não encontrado aqui
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

        CountJogadas contadorPeca = peca.getCountJogadas();

        if (equipaPeca != tabuleiro.getEquipaAJogar()) {
            contadorEquipaAJogar.jogadaInvalida();
            contadorPeca.jogadaInvalida();
            //jogada invalida turno invalido
            return false;
        }

        if (!(peca.validMove(x1, y1, tabuleiro))) {
            contadorEquipaAJogar.jogadaInvalida();
            contadorPeca.jogadaInvalida();
            //jogada invalida nao se pode mover para essa coordenada
            return false;
        }


        //------------Confirmacoes destino--------------------//

        Peca pecaDestino = tabuleiro.getPeca(x1, y1);

        if (pecaDestino != null) {  //Se no destino houver peca

            int equipaDestino = pecaDestino.getEquipa();

            CountJogadas contadorEquipaPecaDestino = tabuleiro.getContadorEquipa(equipaDestino);


            if (equipaPeca == equipaDestino) {
                contadorEquipaAJogar.jogadaInvalida();
                contadorPeca.jogadaInvalida();
                //jogada invalida peca da mesma equipa no destino
                return false;
            } else {
                foiCapturada = true;
                contadorRondas.resetRondasSemCaptura();
                pecaDestino.capturada();
                peca.adicionarPecaQueCaptorou(pecaDestino.getID());
                contadorEquipaAJogar.pecaFoiCapturada();    //contadorEquipaPeca.pecaFoiCapturada();
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
        contadorPeca.jogadaValida();
        contadorRondas.incrementaRondaAtual();
        tabuleiro.mudarEquipaAjogar();


        Jogada jogada = new Jogada(x0, y0, x1, y1, foiCapturada, rondaAtual, rondaJoker, rondasSemCaptura,
                contadorEquipaAJogar.getJogadasInvalidas(),contadorPeca.getJogadasInvalidas());

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

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        try {
            // Write board size
            writer.write(String.valueOf(tabuleiro.getTamanho()));
            writer.newLine();

            // Write number of pieces
            List<Peca> pecas = tabuleiro.getListaPecas();
            writer.write(String.valueOf(pecas.size()));
            writer.newLine();

            // Write piece information
            for (Peca peca : pecas) {
                writer.write(peca.getID()+":"+peca.getTipo()+":"+peca.getEquipa()+":"+peca.getAlcunha());
                writer.newLine();
            }

            // Write board configuration

            for (int yPosicao = 0; yPosicao < tabuleiro.getTamanho(); yPosicao++) {
                for (int xPosicao = 0; xPosicao < tabuleiro.getTamanho(); xPosicao++) {   //usado para verificar ao inicio se o ficheiro ja vem
                    Peca peca = tabuleiro.getPeca(xPosicao, yPosicao);

                    if (peca != null) {
                        if(xPosicao == tabuleiro.getTamanho() -1){
                            writer.write(String.valueOf(peca.getID()));
                            writer.newLine();
                        }else{
                            writer.write(peca.getID()+":");
                        }

                    }else{
                        if(xPosicao == tabuleiro.getTamanho() -1){
                            writer.write("0");
                            writer.newLine();
                        }else{
                            writer.write("0:");
                        }
                    }

                }
            }
            CountJogadas countPreta = tabuleiro.getContadorEquipa(10);
            CountJogadas countBranco = tabuleiro.getContadorEquipa(20);
            ContadorRondas contadorRondas = tabuleiro.getContadorRondas();

            writer.write("Contadores por equipa:"+"\n");

            // Equipa Preta
            writer.write("Preta - Peças Capturadas:" + countPreta.getPecasCapturadas()+"\n");
            writer.write("Preta - Jogadas Inválidas:" + countPreta.getJogadasInvalidas()+"\n");
            writer.write("Preta - Jogadas Válidas:" + countPreta.getJogadasValidas()+"\n");

            // Equipa Branca
            writer.write("Branca - Peças Capturadas:" + countBranco.getPecasCapturadas()+"\n");
            writer.write("Branca - Jogadas Inválidas:" + countBranco.getJogadasInvalidas()+"\n");
            writer.write("Branca - Jogadas Válidas:" + countBranco.getJogadasValidas()+"\n");

            // Informações de contador de rondas
            writer.write("Contador de Rondas:"+"\n");
            writer.write("Rondas Joker:" + contadorRondas.getRondasJoker()+"\n");
            writer.write("Rondas sem Captura:" + contadorRondas.getRondasSemCaptura()+"\n");
            writer.write("Ronda Atual:" + contadorRondas.getRondaAtual()+"\n");

            writer.write("Equipa a Jogar:"+"\n");
            writer.write("Equipa :"+ tabuleiro.getEquipaAJogar()+"\n");

            writer.write("Estatisticas de cada Peca:"+"\n");

            for(Peca peca : tabuleiro.getListaPecas()){
                CountJogadas countJogadas = peca.getCountJogadas();
                writer.write(+peca.getID()+"->"+countJogadas.getJogadasValidas()+":"+countJogadas.getJogadasInvalidas()+"|");
                int count = 0;
                if(peca.getListaPecasCapturadas().size()>0){
                    for(Integer pecaQueCapturou : peca.getListaPecasCapturadas()){
                        if(count == peca.getListaPecasCapturadas().size()-1){
                            writer.write(pecaQueCapturou+"\n");
                        }else{
                            writer.write(pecaQueCapturou+":");
                        }
                        count++;
                    }
                }else{
                    writer.write("\n");
                }
            }



        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }


    public void undo() {
        if (tabuleiro != null) {
            Jogada ultimaJogada = tabuleiro.obterUltimaJogada();

            if (ultimaJogada != null) { // Se existe uma jogada no historico

                ContadorRondas contadorRondas = tabuleiro.getContadorRondas();

                Peca pecaQueJogou = tabuleiro.getPeca(ultimaJogada.getXDestino(), ultimaJogada.getYDestino());

                CountJogadas countPecaQueJogou = tabuleiro.getContadorEquipa(pecaQueJogou.getEquipa());

                CountJogadas contadorPeca = pecaQueJogou.getCountJogadas();


                if (ultimaJogada.houveCaptura()) {
                    countPecaQueJogou.decrementaPecaFoiCapturada();
                }

                countPecaQueJogou.decrementaJogadaValida();
                contadorPeca.decrementaJogadaValida();


                int rondaAtualJogada = ultimaJogada.getRondaAtualJogada();
                int rondaJokerJogada = ultimaJogada.getRondaJokerJogada();
                int rondaSemCapturaJogada = ultimaJogada.getRondaSemCapturaJogada();

                contadorRondas.alteraRondaAtual(rondaAtualJogada);
                contadorRondas.alteraRondaJoker(rondaJokerJogada);
                contadorRondas.alteraRondaSemCaptural(rondaSemCapturaJogada);
                countPecaQueJogou.alteraJogadasInvalidas(ultimaJogada.getJogadasInvalidas());
                contadorPeca.alteraJogadasInvalidas(ultimaJogada.getJogadasInvalidasPeca());
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


