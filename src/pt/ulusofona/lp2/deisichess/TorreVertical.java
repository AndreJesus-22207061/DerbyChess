package pt.ulusofona.lp2.deisichess;

public class TorreVertical extends Peca{
    public TorreVertical(int id, int equipa, String alcunha,int valor) {
        super(id, 5, equipa, alcunha);
        definirPontos();
    }

    @Override
    void definirPontos() {
        this.valor = 3;
    }

    @Override
    boolean validMove(int x, int y, Tabuleiro tabuleiro) {
        return false;
    }
}
