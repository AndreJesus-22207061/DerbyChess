package pt.ulusofona.lp2.deisichess;

public class TorreHorizontal extends Peca {

    public TorreHorizontal(int id, int equipa, String alcunha) {
        super(id, equipa, alcunha);
        definirPontos();
        setTipo(TipoPeca.TORREHORIZONTAL);
    }

    @Override
    String toString(Tabuleiro tabuleiro) {
        if(!getEstado()) {
            return getID() + " | " +getTipoString()+ "| " +getValor()+ " | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }
        return getID() + " | " +getTipoString()+ "| " +getValor()+ " | " + getEquipa() + " | " + getAlcunha() +  " @ (" + getX() + ", " + getY() + ")";

    }

    @Override
    String getTipoString() {
        return "TorreHor";
    }

    @Override
    void definirPontos() {
        this.valor = 3;
    }

    @Override
    boolean validMove(int xFinal, int yFinal, Tabuleiro tabuleiro) {
        // Verificar se a posição de destino está dentro do tabuleiro
        if (xFinal < 0 || xFinal >= tabuleiro.getTamanho() || yFinal < 0 || yFinal >= tabuleiro.getTamanho()) {
            return false;
        }

        // Verificar se a posição de destino está na mesma linha horizontal
        if (yFinal != getY()) {
            return false;
        }

        int deltaX = Math.abs(xFinal - getX());

        // Verificar se há peças no caminho
        int stepX = (xFinal - getX()) / Math.max(1, deltaX);

        for (int i = 1; i < deltaX; i++) {
            int currentX = getX() + i * stepX;

            // Verificar se há uma peça na posição atual do caminho
            if (tabuleiro.getPeca(currentX, yFinal) != null) {
                return false;
            }
        }

        // Verificar se a posição de destino está vazia ou contém uma peça adversária
        Peca pecaDestino = tabuleiro.getPeca(xFinal, yFinal);
        return pecaDestino == null || pecaDestino.getEquipa() != getEquipa();
    }




}
