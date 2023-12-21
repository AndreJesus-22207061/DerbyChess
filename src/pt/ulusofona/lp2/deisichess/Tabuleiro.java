package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.Stack;

public class Tabuleiro {
    private  int tamanho;
    private Peca[][] tabuleiro;

    private ArrayList<Peca> pecas;

    private ArrayList<Peca> pecasPretas;

    private ArrayList<Peca> pecasBrancas;

    private int equipaAJogar;

    private ArrayList<CountJogadas> contadoresJogadas;

    private ContadorRondas contadorRondas;

    private Stack<Jogada> historicoJogadas;



    public Tabuleiro(int tamanho,ArrayList<Peca>pecas) {
        this.tamanho = tamanho;
        this.tabuleiro = new Peca[tamanho][tamanho];
        this.pecas = pecas;
        this.pecasPretas = new ArrayList<>();
        this.pecasBrancas   = new ArrayList<>();
        this.equipaAJogar = 10; //Começa a 10 (preta)
        this.contadoresJogadas = new ArrayList<>();
        this.contadorRondas = new ContadorRondas();
        this.historicoJogadas = new Stack<>();
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

    ArrayList<Peca> getListaPretas(){
        return this.pecasPretas;
    }

    ArrayList<Peca> getListaBrancas(){
        return this.pecasBrancas;
    }

    void adicionarPecaAEquipa(Peca peca){
        if(peca.getEquipa()==10){
            this.pecasPretas.add(peca);
        }else{
            this.pecasBrancas.add(peca);
        }
    }

    ContadorRondas getContadorRondas(){
        return this.contadorRondas;
    }

    CountJogadas getContadorEquipa(int equipa){
        for(CountJogadas contador : contadoresJogadas){
            if(contador.getEquipa() == equipa){
                return contador;
            }
        }
        return null;
    }

    void adicionarNovoContador(int equipa){
        boolean existe = false;
        for(CountJogadas contador : contadoresJogadas){
            if(equipa == contador.getEquipa()){
                existe = true;
            }
        }
        if(!existe){
            CountJogadas novoContador = new CountJogadas(equipa);
            contadoresJogadas.add(novoContador);
        }
    }

    Jogada obterUltimaJogada() {
        if (!historicoJogadas.isEmpty()) {
            return historicoJogadas.peek();
        }
        return null;
    }


    int getTamanho(){
        return tamanho;
    }

    int getEquipaAJogar(){
        return this.equipaAJogar;
    }

    void mudarEquipaAjogar(){
        if(equipaAJogar ==10){
            equipaAJogar =20;
        }else{
            equipaAJogar =10;
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
            informacaoQuadrado[4] = peca.getImagem();

            return informacaoQuadrado;
        }else{
            return new String[0];
        }
    }


    String buscarInformacaoPecaAsString(int ID){
        for (Peca peca : pecas) {
            if(peca.getID() ==ID){
                return peca.toString(contadorRondas);
            }

        }
        return null;
    }



    void restaurarTabuleiro(Jogada ultimaJogada) {
        int xOrigem = ultimaJogada.getXOrigem();
        int yOrigem = ultimaJogada.getYOrigem();
        int xDestino = ultimaJogada.getXDestino();
        int yDestino = ultimaJogada.getYDestino();

        movePeca(xDestino, yDestino, xOrigem, yOrigem);

        if (ultimaJogada.houveCaptura()) {   // Se houve uma captura, reativa a peça capturada
            Peca pecaCapturada = getPeca(xDestino, yDestino);
            pecaCapturada.reativar();
        }

        historicoJogadas.pop(); // Remove a última jogada do histórico
    }

    void adicionarJogadaAoHistorico(Jogada ultimaJogada) {
        historicoJogadas.push(ultimaJogada);
    }






    ArrayList<String> menuFinal(){
        ArrayList<String> menuFinal = new ArrayList<>();

        CountJogadas contadorBrancas = getContadorEquipa(20);
        CountJogadas contadorPretas = getContadorEquipa(10);


        String linha1 = "JOGO DE CRAZY CHESS";
        String linha2 = "Resultado: " +contadorRondas.getResultado();
        String linha3 = "---";
        String linha4="Equipa das Pretas";
        String linha5= Integer.toString(contadorBrancas.getPecasCapturadas());
        String linha6=Integer.toString(contadorPretas.getJogadasValidas());
        String linha7=Integer.toString(contadorPretas.getJogadasInvalidas());
        String linha8="Equipa das Brancas";
        String linha9=Integer.toString(contadorPretas.getPecasCapturadas());
        String linha10 =Integer.toString(contadorBrancas.getJogadasValidas());
        String linha11 = Integer.toString(contadorBrancas.getJogadasInvalidas());

        menuFinal.add(linha1);
        menuFinal.add(linha2);
        menuFinal.add(linha3);
        menuFinal.add(linha4);
        menuFinal.add(linha5);
        menuFinal.add(linha6);
        menuFinal.add(linha7);
        menuFinal.add(linha8);
        menuFinal.add(linha9);
        menuFinal.add(linha10);
        menuFinal.add(linha11);

        return menuFinal;
    }


}


