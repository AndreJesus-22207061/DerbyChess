package pt.ulusofona.lp2.deisichess;

public class Rainha extends Peca{

    public Rainha(int id, int equipa, String alcunha,int valor) {
        super(id, 1, equipa, alcunha);
        definirPontos();
    }

    @Override
    void definirPontos() {
        this.valor = 8;
    }

    @Override
    boolean validMove(int x, int y, Tabuleiro tabuleiro) {
        return false;
    }

}
