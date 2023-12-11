package pt.ulusofona.lp2.deisichess;

public class Rei extends Peca {

    public Rei(int id , int equipa, String alcunha) {
        super(id,equipa, alcunha);
        definirPontos();
        setTipo(TipoPeca.REI);

    }

    @Override
    String toString(Tabuleiro tabuleiro) {
        if(!getEstado()) {
          return  getID() + " | " +getTipoString()+  " | (infinito) | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }
        return getID() + " | "+getTipoString()+ " | (infinito) | " + getEquipa() + " | " + getAlcunha() + " @ (" + getX() + ", " + getY() + ")";
    }

    @Override
    void definirPontos() {
        this.valor = 1000;
    }

    @Override
    String getTipoString() {
        return "Rei";
    }

    @Override
    boolean validMove(int xFinal, int y, Tabuleiro tabuleiro) {

            if ((xFinal < 0 || xFinal >= tabuleiro.getTamanho() || y < 0 || y >= tabuleiro.getTamanho())) {
                return false;
            }

            if ((xFinal == getX() && (y == getY() + 1 || y == getY() - 1))) {
                // Movimento vertical //

                return true;
            }

            if (y == getY() && (xFinal == getX() + 1 || xFinal == getX() - 1)) {
                // Movimento horizontal //
                return true;
            }

            if ((xFinal == getX() + 1 || xFinal == getX() - 1) && (y == getY() + 1 || y == getY() - 1)) {
                // Movimento diagonal //
                return true;
            }


            return false;
        }






}
