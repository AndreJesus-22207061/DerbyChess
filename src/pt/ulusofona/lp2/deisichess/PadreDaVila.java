package pt.ulusofona.lp2.deisichess;

public class PadreDaVila extends Peca{

    public PadreDaVila(int id, int equipa, String alcunha) {
        super(id, 3, equipa, alcunha);
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

        // Verificar se a posição de destino está na diagonal
        int deltaX = Math.abs(x - getX());
        int deltaY = Math.abs(y - getY());
        if (deltaX != deltaY || deltaX > 3) { //Fazer retas no papel
            return false;
        }

        // Verificar se há peças no caminho
        int stepX = (x - getX()) / Math.max(1, deltaX);
        int stepY = (y - getY()) / Math.max(1, deltaY);

        for (int i = 1; i < deltaX; i++) {
            int currentX = getX() + i * stepX;
            int currentY = getY() + i * stepY;

            // Verificar se há uma peça na posição atual do caminho
            if (tabuleiro.getPeca(currentX, currentY) != null) {
                return false; // Movimento inválido devido a uma peça no caminho
            }
        }

        // Verificar se a posição de destino está vazia ou contém uma peça adversária
        Peca pecaDestino = tabuleiro.getPeca(x, y);
        return pecaDestino == null || pecaDestino.getEquipa() != getEquipa();
    }
}
