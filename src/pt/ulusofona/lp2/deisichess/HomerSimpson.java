package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Peca{
    public HomerSimpson(int id, int equipa, String alcunha) {
        super(id, 6, equipa, alcunha);
        definirPontos();
    }

    @Override
    void definirPontos() {
        this.valor = 2;
    }

    @Override
    boolean validMove(int x, int y, Tabuleiro tabuleiro) {
        return false;
    }
}
