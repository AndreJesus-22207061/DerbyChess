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

        ContadorJogadas contadorJogadas = tabuleiro.getContadorJogadas();

        int ronda = contadorJogadas.getRondaAtual();

        if ((x < 0 || x >= tabuleiro.getTamanho() || y < 0 || y >= tabuleiro.getTamanho())) {
            return false;
        }

        if(ronda%3==0){
            return false;
        }

        if ((x == this.x && (y == this.y + 1 || y == this.y - 1))) {
            // Movimento vertical //

            return true;
        }

        if (y == this.y && (x == this.x + 1 || x == this.x - 1)) {
            // Movimento horizontal //
            return true;
        }

        if ((x == this.x + 1 || x == this.x - 1) && (y == this.y + 1 || y == this.y - 1)) {
            // Movimento diagonal //
            return true;
        }










        return false;
    }
}
