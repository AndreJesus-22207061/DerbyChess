package pt.ulusofona.lp2.deisichess;

public class CountJogadas {

    private int equipa = 0;

    private int pecasCapturadas =0;

    private int jogadasValidas=0;

    private int jogadasInvalidas=0;


    public CountJogadas(int equipa) {
        this.equipa = equipa;

    }

    void pecaFoiCapturadaE() {
        this.pecasCapturadas++;
    }

    void jogadaValidaE() {
        this.jogadasValidas++;
    }

    void jogadaInvalidaE(){
        this.jogadasInvalidas++;

    }

    int getRondaAtual(){
        return this.jogadasValidas;
    }

    int getEquipa(){
        return this.equipa;
    }

    int getPecasCapturadas(){
        return this.pecasCapturadas;
    }

    int getJogadasValidas(){
        return this.jogadasValidas;
    }

    int getJogadasInvalidas(){
        return this.jogadasInvalidas;
    }







}
