package pt.ulusofona.lp2.deisichess;

public class Rei extends Peca {

    public Rei(int id,int tipo, int equipa, String alcunha) {   // ANDA 1 CASA EM TODAS AS DIRECOES
        super(id,tipo, equipa, alcunha);
        definirPontos();
        defenirImagem();

    }

    @Override
    String toString(ContadorRondas contadorRondas) {
        if (!getEstado()) {
            return getID() + " | " + getTipoString(contadorRondas) + " | (infinito) | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }
        return getID() + " | " + getTipoString(contadorRondas) + " | (infinito) | " + getEquipa() + " | " + getAlcunha() + " @ (" + getX() + ", " + getY() + ")";
    }

    @Override
    void definirPontos() {
        this.valor = 1000;
    }

    @Override
    void defenirImagem(){
        if(getEquipa()==10){
            this.imagem = "ReiPreto.png";
        }else{
            this.imagem = "ReiBranco.png";
        }
    }

    @Override
    String getTipoString(ContadorRondas contadorRondas) {
        return "Rei";
    }

    @Override
    boolean validMove(int xFinal, int y, Tabuleiro tabuleiro) {

        if ((xFinal < 0 || xFinal >= tabuleiro.getTamanho() || y < 0 || y >= tabuleiro.getTamanho())) {
            return false;   // DENTRO DOS LIMITES
        }

        if ((xFinal == getX() && (y == getY() + 1 || y == getY() - 1))) { //move se para cima muda o y
            // Movimento vertical //
            return true;
        }

        if (y == getY() && (xFinal == getX() + 1 || xFinal == getX() - 1)) { //move se para esquerda muda o x
            // Movimento horizontal //
            return true;
        }

        if ((xFinal == getX() + 1 || xFinal == getX() - 1) && (y == getY() + 1 || y == getY() - 1)) {
            // Movimento diagonal //
            return true;                //decrementa e aumenta o x com o mesmo valor
        }                               //valor indica as casas que se movem


        return false;
    }


}
