package pt.ulusofona.lp2.deisichess;

public class Joker extends Peca {
    public Joker(int id, int equipa, String alcunha) {
        super(id, 7, equipa, alcunha);
        definirPontos();
    }

    @Override
    void definirPontos() {
        this.valor = 4;
    }

    @Override
    boolean validMove(int x, int y, Tabuleiro tabuleiro) {
        return false;
    }



}
