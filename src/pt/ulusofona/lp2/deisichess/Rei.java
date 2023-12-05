package pt.ulusofona.lp2.deisichess;

public class Rei extends Peca {

    public Rei(int id, int equipa, String alcunha,int valor) {
        super(id, 0, equipa, alcunha);
        definirPontos();
    }

    @Override
    void definirPontos() {
        this.valor = 10;
    }

    @Override
    boolean validMove(int x, int y, Tabuleiro tabuleiro) {
        return false;
    }


}
