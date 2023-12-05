package pt.ulusofona.lp2.deisichess;

public class Rei extends Peca {

    public Rei(int id, int equipa, String alcunha) {
        super(id, 0, equipa, alcunha);
        definirPontos();

    }

    @Override
    void definirPontos() {
        this.valor = 1000;
    }

    @Override
    boolean validMove(int x, int y, Tabuleiro tabuleiro) {

            if ((x < 0 || x >= tabuleiro.getTamanho() || y < 0 || y >= tabuleiro.getTamanho())) {
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
