package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Peca{
    public HomerSimpson(int id, int equipa, String alcunha) {
        super(id, equipa, alcunha);
        definirPontos();
        setTipo(TipoPeca.HOMMER);
    }

    @Override
    void definirPontos() {
        this.valor = 2;
    }

    @Override
    boolean validMove(int xFinal, int yFinal, Tabuleiro tabuleiro) {

        ContadorJogadas contadorJogadas = tabuleiro.getContadorJogadas();

        int ronda = contadorJogadas.getRondaAtual();

        if ((xFinal < 0 || xFinal >= tabuleiro.getTamanho() || yFinal < 0 || yFinal >= tabuleiro.getTamanho())) {
            return false;
        }

        if(ronda%3==0){
            return false;
        }



        if ((xFinal == getX() && (yFinal == getY() + 1 || yFinal == getY() - 1))) {
            // Movimento vertical //

            return true;
        }

        if (yFinal == getY() && (xFinal == getX() + 1 || xFinal == getX() - 1)) {
            // Movimento horizontal //
            return true;
        }

        if ((xFinal == getX() + 1 || xFinal == getX() - 1) && (yFinal == getY() + 1 || yFinal == getY() - 1)) {
            // Movimento diagonal //
            return true;
        }










        return false;
    }
}
