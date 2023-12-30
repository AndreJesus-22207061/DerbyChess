package pt.ulusofona.lp2.deisichess;

import java.util.*;

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
        if(tabuleiro[x][y] != null){
            return tabuleiro[x][y];
        }
        return null;
    }

    void colocaPecaNoTabuleiro(Peca peca, int x, int y){
        if(tabuleiro[x][y] == null){
            tabuleiro[x][y] = peca;
        }
    }

    Peca getPecaPorIDTabuleiro(int id) {        // buscar peca especifica que tem que estar em jogo
        for (int x = 0; x < tamanho; x++) {
            for (int y = 0; y < tamanho; y++) {   //usado para verificar ao inicio se o ficheiro ja vem
                Peca peca = tabuleiro[x][y];      // com peca capturada ou não
                if (peca != null && peca.getID() == id) {
                    return peca;
                }
            }
        }
        return null;
    }

    Peca getPecaPorIDLista(int id) {       // buscar peca especifica que pode estar ou nao em jogo
        for (Peca peca : pecas) {
            if (peca != null && peca.getID() == id) {
                return peca;
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

    void setEquipaAJogar(int equipa){
        this.equipaAJogar = equipa;
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
        int idPecaCapturada = ultimaJogada.getIdPecaCapturada();

        movePeca(xDestino, yDestino, xOrigem, yOrigem);
        Peca pecaQueJogou = getPeca(xOrigem,yOrigem);
        pecaQueJogou.setCoordenadas(xOrigem,yOrigem);

        if (ultimaJogada.houveCaptura()) {   // Se houve uma captura, reativa a peça capturada
            Peca pecaCapturada = getPecaPorIDLista(idPecaCapturada);
            pecaCapturada.reativar(xDestino,yDestino);
            colocaPecaNoTabuleiro(pecaCapturada,xDestino,yDestino);
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
        String linha5= Integer.toString(contadorPretas.getPecasCapturadas());
        String linha6=Integer.toString(contadorPretas.getJogadasValidas());
        String linha7=Integer.toString(contadorPretas.getJogadasInvalidas());
        String linha8="Equipa das Brancas";
        String linha9=Integer.toString(contadorBrancas.getPecasCapturadas());
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


    boolean isXequeMate(ArrayList<Peca> listaPecas){
        int encontrou = 0;
        int estaCapturado = 0;
        for(Peca peca :listaPecas){
            if(peca.getTipo() == 0){
                encontrou++;
                if(!peca.getEstado()){
                    estaCapturado++;
                }
            }
        }
        if(encontrou==estaCapturado && encontrou != 0){
            return true;
        }
        return false;
    }



    ArrayList<String> top5Capturas(){
        ArrayList<Peca> listaDePecas = getListaPecas();
        Collections.sort(listaDePecas, Comparator.comparingInt((Peca peca )->(peca.getListaPecasCapturadas().size())).reversed());

        ArrayList<String> pecasString = new ArrayList<>();

        for(int i = 0; i <=5 ; i++){
            Peca peca = listaDePecas.get(i);
            String pecaString = "";

            if(peca.getEquipa() == 10){
                pecaString = peca.getAlcunha()+" (PRETA) fez "+peca.getListaPecasCapturadas().size()+" capturas";
                pecasString.add(pecaString);
            }else{
                pecaString = peca.getAlcunha()+" (BRANCA) fez "+peca.getListaPecasCapturadas().size()+" capturas";
                pecasString.add(pecaString);
            }


        }

        return pecasString;
    }

    ArrayList<String> pecasComMais5capturas(){
        ArrayList<Peca> listaDePecas = getListaPecas();
        Collections.sort(listaDePecas, Comparator.comparingInt((Peca peca )->(peca.getListaPecasCapturadas().size())).reversed());

        ArrayList<String> pecasString = new ArrayList<>();


        for(Peca peca:listaDePecas){
            if(peca.getListaPecasCapturadas().size() > 5){
                String pecaString = "";

                if(peca.getEquipa() == 10){
                    pecaString = "PRETA:" + peca.getAlcunha()+":"+ peca.getListaPecasCapturadas().size();
                    pecasString.add(pecaString);
                }else{
                    pecaString = "BRANCA:" + peca.getAlcunha() +":" + peca.getListaPecasCapturadas().size();
                    pecasString.add(pecaString);
                }

            }
        }

        return pecasString;
    }

    ArrayList<String> top5Pontos(){
        ArrayList<Peca> listaDePecas = getListaPecas();
        ArrayList<Peca> pecasComPeloMenos1Capt = new ArrayList<>();

        for(Peca peca : listaDePecas){
            if(peca.getListaPecasCapturadas().size() >= 1){
                pecasComPeloMenos1Capt.add(peca);
            }
        }

        Collections.sort(pecasComPeloMenos1Capt, Comparator
                .comparingInt((Peca peca) -> peca.pontosCapturados(listaDePecas)).reversed()
                .thenComparing(Comparator.comparing(Peca::getAlcunha)));

        ArrayList<String> pecasString = new ArrayList<>();

        for(int i = 0; i <5 ; i++){
            if(pecasComPeloMenos1Capt.size()>i){
                Peca peca = pecasComPeloMenos1Capt.get(i);
                String pecaString = "";

                if(peca.getEquipa() == 10){
                    pecaString = peca.getAlcunha()+" (PRETA) tem "+peca.pontosCapturados(listaDePecas)+" pontos";
                    pecasString.add(pecaString);
                }else{
                    pecaString = peca.getAlcunha()+" (BRANCA) tem "+peca.pontosCapturados(listaDePecas)+" pontos";
                    pecasString.add(pecaString);
                }
            }else{
                break;
            }
        }

        return pecasString;
    }




    ArrayList<String> tiposPecasCapturados(){
        ArrayList<Peca> listaDePecas = getListaPecas();

        ArrayList<Integer> tiposDePecas = new ArrayList<>();

        ArrayList<Peca> listaPecasTipoUnico = new ArrayList<>();


        for(Peca peca : listaDePecas){
            if(!tiposDePecas.contains(peca.getTipo()) && !peca.getEstado()){
                listaPecasTipoUnico.add(peca);
                tiposDePecas.add(peca.getTipo());
            }
        }

        Collections.sort(listaPecasTipoUnico, Comparator.comparing((Peca peca )->(peca.getTipoString(getContadorRondas()))));

        ArrayList<String> pecasString = new ArrayList<>();

        for(Peca peca : listaPecasTipoUnico){

                String pecaString = "";

                pecaString = peca.getTipoString(getContadorRondas());
                pecasString.add(pecaString);

        }

        return pecasString;
    }

    ArrayList<String> top3Baralhadas(){
        ArrayList<Peca> listaDePecas = getListaPecas();
        ArrayList<Peca> pecasComPeloMenos1Invalida = new ArrayList<>();

        for(Peca peca : listaDePecas){
            CountJogadas countJogadas = peca.getCountJogadas();
            if(countJogadas.getJogadasInvalidas()>=1){
                pecasComPeloMenos1Invalida.add(peca);
            }
        }



        Collections.sort(pecasComPeloMenos1Invalida, Comparator.comparingInt((Peca peca) -> peca.mediaJogadas()));


        ArrayList<String> pecasString = new ArrayList<>();

        for(int i = 0; i <5 ; i++){
            if(pecasComPeloMenos1Invalida.size()>i){
                Peca peca = pecasComPeloMenos1Invalida.get(i);
                CountJogadas countJogadas = peca.getCountJogadas();
                int jogadasValidas = countJogadas.getJogadasValidas();
                int jogadasInvalidas = countJogadas.getJogadasInvalidas();

                String pecaString = "";

                pecaString = peca.getEquipa()+":"+peca.getAlcunha()+":"+jogadasInvalidas+":"+jogadasValidas;
                pecasString.add(pecaString);



            }else{
                break;
            }
        }

        return pecasString;
    }






}

