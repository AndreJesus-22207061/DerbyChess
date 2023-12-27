package pt.ulusofona.lp2.deisichess;

public class CountJogadas {

    private int equipa;

    private int jogadasValidas=0;

    private int jogadasInvalidas=0;


    public CountJogadas(int equipa) {
        this.equipa = equipa;

    }


    void jogadaValida() {
        this.jogadasValidas++;
    }

    void decrementaJogadaValida() {
        this.jogadasValidas--;
    }

    void jogadaInvalida(){
        this.jogadasInvalidas++;
    }

    void decrementaJogadaInvalida(){
        this.jogadasInvalidas--;
    }
    int getEquipa(){
        return this.equipa;
    }

    int getJogadasValidas(){
        return this.jogadasValidas;
    }

    int getJogadasInvalidas(){
        return this.jogadasInvalidas;
    }

    void alteraJogadasInvalidas(int jogInvalidas) {
        this.jogadasInvalidas = jogInvalidas;
    }


}
