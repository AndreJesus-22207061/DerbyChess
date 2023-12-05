package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Peca{

    public PoneiMagico(int id, int equipa, String alcunha) {
        super(id, 2, equipa, alcunha);
        definirPontos();
    }

    @Override
    void definirPontos() {
        this.valor = 5;
    }

    @Override
    boolean validMove(int x, int y, Tabuleiro tabuleiro) {
        return false;
    }



}
