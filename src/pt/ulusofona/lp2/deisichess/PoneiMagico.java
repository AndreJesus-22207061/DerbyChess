package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Peca{

    public PoneiMagico(int id,int tipo, int equipa, String alcunha) {
        super(id,tipo, equipa, alcunha);
        definirPontos();
        defenirImagem();
    }

    @Override
    String toString(ContadorRondas contadorRondas) {
        if(!getEstado()) {
            return getID() + " | " +getTipoString(contadorRondas)+ " | " +getValor()+ " | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }
        return getID() + " | " +getTipoString(contadorRondas)+ " | " +getValor()+ " | " + getEquipa() + " | " + getAlcunha() +  " @ (" + getX() + ", " + getY() + ")";
    }

    @Override
    String getTipoString(ContadorRondas contadorRondas) {
        return "Ponei Mágico";
    }

    @Override
    void definirPontos() {
        this.valor = 5;
    }

    @Override
    void defenirImagem(){
        if(getEquipa()==10){
            this.imagem = "ponei_magico_black.png";
        }else{
            this.imagem = "ponei_magico_white.png";
        }
    }

    @Override
    boolean validMove(int xFinal, int yFinal, Tabuleiro tabuleiro) {
        // Verificar se a posição de destino está dentro do tabuleiro
        if (xFinal < 0 || xFinal >= tabuleiro.getTamanho() || yFinal < 0 || yFinal >= tabuleiro.getTamanho()) {
            return false;
        }

//------- Verificar se a coordenada de destino está a uma distância válida para o Ponei Mágico----------------//

        int deltaX = Math.abs(xFinal - getX());
        int deltaY = Math.abs(yFinal - getY());

        if (!(deltaX == 2 && deltaY == 2)) {
            return false;
        }

//----------------------------- Verificar o primeiro caminho do movimento --------------------------------------//

        int xIncial = getX();
        int yInicial = getY();

        boolean primeiroMovimento = true;
        boolean segundoMovimento = true;

       //-----------------Movimento eixo Y --------------------------//
        for (int i = 1; i < 3; i++) {
            int currentY = getY() + i * sentidoMovimentoEixoY(yFinal);

            // Verificar se há uma peça na posição atual do caminho
            if (tabuleiro.getPeca(xIncial, currentY) != null) {
                primeiroMovimento = false; // Movimento inválido devido a uma peça no caminho
            }
        }


        //-----------------Movimento eixo x --------------------------//
        for (int i = 1; i < 2; i++) {
            int currentX = getX() + i * sentidoMovimentoEixoX(xFinal);

            // Verificar se há uma peça na posição atual do caminho
            if (tabuleiro.getPeca(currentX, yFinal ) != null) {
                primeiroMovimento = false;
            }
        }

//---------------------------- Verificar o segundo caminho do movimento -------------------------------------------//

        //-----------------Movimento eixo x --------------------------//
        for (int i = 1; i < 3; i++) {
            int currentX = getX() + i * sentidoMovimentoEixoX(xFinal);

            // Verificar se há uma peça na posição atual do caminho
            if (tabuleiro.getPeca(currentX, yInicial) != null) {
                segundoMovimento = false;
            }
        }

        //-----------------Movimento eixo Y --------------------------//
        for (int i = 1; i < 2; i++) {
            int currentY = getY() + i * sentidoMovimentoEixoY(yFinal);

            // Verificar se há uma peça na posição atual do caminho
            if (tabuleiro.getPeca(xFinal, currentY) != null) {
                segundoMovimento = false;
            }
        }

        return (primeiroMovimento || segundoMovimento);
    }

    private int sentidoMovimentoEixoY(int yFinal ){
        if(yFinal-getY() == 2){
            return 1;
        }
        return -1;
    }

    private int sentidoMovimentoEixoX(int xFinal ){
        if(xFinal -getX() == 2){
            return 1;
        }
        return -1;
    }



}
