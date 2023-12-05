package pt.ulusofona.lp2.deisichess;

public class Rainha extends Peca{

    public Rainha(int id, int equipa, String alcunha) {
        super(id, 1, equipa, alcunha);
        definirPontos();
    }

    @Override
    void definirPontos() {
        this.valor = 8;
    }

    @Override
    boolean validMove(int x, int y, Tabuleiro tabuleiro) {
        // Verificar se a posição de destino está dentro do tabuleiro
        if (x < 0 || x >= tabuleiro.getTamanho() || y < 0 || y >= tabuleiro.getTamanho()) {
            return false;
        }

        // Verificar se a posição de destino está dentro do limite de movimento (máximo 5 casas)
        int deltaX = Math.abs(x - getX());
        int deltaY = Math.abs(y - getY());
        if (deltaX > 5 || deltaY > 5 || (deltaX != 0 && deltaY != 0 && deltaX != deltaY)) {
            return false;
        }

        // Verificar se há peças no caminho
        int stepX = (x - getX()) / Math.max(1, deltaX);
        int stepY = (y - getY()) / Math.max(1, deltaY);

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
