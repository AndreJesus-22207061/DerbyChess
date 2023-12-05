package pt.ulusofona.lp2.deisichess;

public class TorreVertical extends Peca{
    public TorreVertical(int id, int equipa, String alcunha) {
        super(id, 5, equipa, alcunha);
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

        // Verificar se a posição de destino está na mesma coluna vertical
        if (x != getX()) {
            return false;
        }

        int deltaY = Math.abs(y - getY());

        // Verificar se há peças no caminho
        int stepY = (y - getY()) / Math.max(1, deltaY);

        for (int i = 1; i < deltaY; i++) {
            int currentY = getY() + i * stepY;

            // Verificar se há uma peça na posição atual do caminho
            if (tabuleiro.getPeca(x, currentY) != null) {
                return false;
            }
        }

        // Verificar se a posição de destino está vazia ou contém uma peça adversária
        Peca pecaDestino = tabuleiro.getPeca(x, y);
        return pecaDestino == null || pecaDestino.getEquipa() != getEquipa();
    }
}
