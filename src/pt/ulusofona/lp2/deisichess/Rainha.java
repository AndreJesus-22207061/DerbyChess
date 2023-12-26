package pt.ulusofona.lp2.deisichess;

import java.util.Objects;

public class Rainha extends Peca{

    public Rainha(int id, int tipo, int equipa, String alcunha) {
        super(id, tipo, equipa, alcunha);
        definirPontos();
        defenirImagem();
    }

    @Override
    String toString(ContadorRondas contadorRondas) {

        if(!getEstado()) {
            return getID() + " | " +getTipoString()+ " | " +getValor()+ " | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }
        return getID() + " | " +getTipoString()+ " | " +getValor()+ " | " + getEquipa() + " | " + getAlcunha() +  " @ (" + getX() + ", " + getY() + ")";
    }



    @Override
    void definirPontos() {
        this.valor = 8;
    }

    @Override
    void defenirImagem(){
        if(getEquipa()==10){
            this.imagem = "rainha_black.png";
        }else{
            this.imagem = "rainha_white.png";
        }
    }


    @Override
    String getTipoString() {
        return "Rainha";
    }

    @Override
    boolean validMove(int xFinal, int yFinal, Tabuleiro tabuleiro) {
        // Verificar se a posição de destino está dentro do tabuleiro
        if (xFinal < 0 || xFinal >= tabuleiro.getTamanho() || yFinal < 0 || yFinal >= tabuleiro.getTamanho()) {
            return false;
        }

        // Verificar se a posição de destino está dentro do limite de movimento (máximo 5 casas)
        int deltaX = Math.abs(xFinal - getX());
        int deltaY = Math.abs(yFinal - getY());
        if (deltaX > 5 || deltaY > 5 || (deltaX != 0 && deltaY != 0 && deltaX != deltaY)) {
            return false;
        }

        // Verificar se há peças no caminho
        int stepX = (xFinal - getX()) / Math.max(1, deltaX);
        int stepY = (yFinal - getY()) / Math.max(1, deltaY);

        for (int i = 1; i < Math.max(deltaX, deltaY); i++) {
            int currentX = getX() + i * stepX;
            int currentY = getY() + i * stepY;

            // Verificar se há uma peça na posição atual do caminho
            if (tabuleiro.getPeca(currentX, currentY) != null) {
                return false;
            }
        }

        return true;
    }

}
