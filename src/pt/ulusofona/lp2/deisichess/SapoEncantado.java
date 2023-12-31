package pt.ulusofona.lp2.deisichess;

public class SapoEncantado extends Peca{

    public SapoEncantado(int id, int tipo, int equipa, String alcunha) {
        super(id, tipo, equipa, alcunha);
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
    void definirPontos() {
        this.valor = 5;
    }

    @Override
    void defenirImagem(){
        if(getEquipa()==10){
            this.imagem = "SapoEncantadoPreto.png";
        }else{
            this.imagem = "SapoEncantadoBranco.png";
        }
    }


    @Override
    String getTipoString(ContadorRondas contadorRondas) {
        return "Sapo Encantado";
    }

    @Override
    boolean validMove(int xFinal, int yFinal, Tabuleiro tabuleiro) {
        // Verificar se a posição de destino está dentro do tabuleiro
        if (xFinal < 0 || xFinal >= tabuleiro.getTamanho() || yFinal < 0 || yFinal >= tabuleiro.getTamanho()) {
            return false;
        }

        Peca peca = tabuleiro.getPeca(xFinal,yFinal);

        ContadorRondas contadorRondas = tabuleiro.getContadorRondas();

        //Vreifica se a peca que vamos capturar é uma rainha
        Peca sapo = tabuleiro.getPeca(getX(),getY());

        // Verificar se a posição de destino está dentro do limite de movimento (máximo 2 casas)
        int deltaX = Math.abs(xFinal - getX());
        int deltaY = Math.abs(yFinal - getY());
        if (deltaX > 2 || deltaY > 2 || (deltaX != 0 && deltaY != 0 && deltaX != deltaY)) {
            return false;
        }

        // Verificar se há peças no caminho
        int stepX = (xFinal - getX()) / Math.max(1, deltaX);
        int stepY = (yFinal - getY()) / Math.max(1, deltaY);

        for (int i = 1; i < Math.max(deltaX, deltaY); i++) {
            int currentX = getX() + i * stepX;
            int currentY = getY() + i * stepY;

            // Verificar se há uma peça na posição atual do caminho da equipa oposta
            if (tabuleiro.getPeca(currentX, currentY) != null && (tabuleiro.getPeca(currentX,currentY)).getEquipa() != sapo.getEquipa()) {
                return false;
            }
        }

        return true;
    }

}

