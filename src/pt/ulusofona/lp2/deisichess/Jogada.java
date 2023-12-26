package pt.ulusofona.lp2.deisichess;

import java.lang.reflect.Array;

public class Jogada {

    private int xOrigem;
    private int yOrigem;
    private int xDestino;
    private int yDestino;
    private boolean captura;

    private int idPecaCapturada;

    private int rondaAtual;

    private int rondaJoker;

    private int rondaSemCaptura;

    private int jogadasInvalidas;

    public Jogada(int xOrigem, int yOrigem, int xDestino, int yDestino, boolean captura , int rondaAtual , int rondaJoker , int rondaSemCaptura, int jogadasInvalidas ) {
        this.xOrigem = xOrigem;
        this.yOrigem = yOrigem;
        this.xDestino = xDestino;
        this.yDestino = yDestino;
        this.captura = captura;
        this.rondaAtual = rondaAtual;
        this.rondaJoker = rondaJoker;
        this.rondaSemCaptura = rondaSemCaptura;
        this.jogadasInvalidas = jogadasInvalidas;
    }

    public int getXOrigem() {
        return xOrigem;
    }

    public int getYOrigem() {
        return yOrigem;
    }

    public int getXDestino() {
        return xDestino;
    }

    public int getYDestino() {
        return yDestino;
    }

    public boolean houveCaptura() {
        return captura;
    }

    public void addIDPecaCapturada(int id){
        this.idPecaCapturada = id;
    }

    public int getIdPecaCapturada() {
        return idPecaCapturada;
    }

    public int getRondaAtualJogada() {
        return rondaAtual;
    }
    public int getRondaJokerJogada() {
        return rondaJoker;
    }

    public int getRondaSemCapturaJogada() {
        return rondaSemCaptura;
    }

    public int getJogadasInvalidas(){return jogadasInvalidas;}




}
