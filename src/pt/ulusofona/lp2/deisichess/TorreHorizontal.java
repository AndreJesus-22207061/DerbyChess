package pt.ulusofona.lp2.deisichess;

public class TorreHorizontal extends Peca {

    public TorreHorizontal(int id, int equipa, String alcunha) {
        super(id, 4, equipa, alcunha);
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
