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
        // Verificar se a posição de destino está dentro do tabuleiro
        if (x < 0 || x >= tabuleiro.getTamanho() || y < 0 || y >= tabuleiro.getTamanho()) {
            return false;
        }

        // Verificar se a posição de destino está na mesma linha horizontal
        if (y != getY()) {
            return false;
        }

        int deltaX = Math.abs(x - getX());

        // Verificar se há peças no caminho
        int stepX = (x - getX()) / Math.max(1, deltaX);

        for (int i = 1; i < deltaX; i++) {
            int currentX = getX() + i * stepX;

            // Verificar se há uma peça na posição atual do caminho
            if (tabuleiro.getPeca(currentX, y) != null) {
                return false;
            }
        }

        // Verificar se a posição de destino está vazia ou contém uma peça adversária
        Peca pecaDestino = tabuleiro.getPeca(x, y);
        return pecaDestino == null || pecaDestino.getEquipa() != getEquipa();
    }




}
