package pt.ulusofona.lp2.deisichess;

public class Joker extends Peca {
    public Joker(int id,int tipo ,int equipa, String alcunha) {
        super(id,tipo ,equipa, alcunha);
        definirPontos();
        defenirImagem();
    }

    @Override
    String toString(ContadorJogadas contadorJogadas) {
        int rondas = contadorJogadas.getRondasJoker();
        Peca pecaAtual = obterPecaCorrespondente(rondas);
        String tipoPecaString = pecaAtual.getTipoString();

        if(!getEstado()) {
            return getID() + " | "+getTipoString()+ tipoPecaString + " | "+getValor()+ " | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }
        return  getID() + " | Joker/" + tipoPecaString + " | "+getValor()+ " | " + getEquipa() + " | " + getAlcunha() + " @ (" + getX() + ", " + getY() + ")";
    }

    @Override
    String getTipoString() {
        return "Joker/";
    }

    @Override
    void definirPontos() {
        this.valor = 4;
    }

    @Override
    void defenirImagem(){
        if(getEquipa()==10){
            this.imagem = "joker_black.png";
        }else{
            this.imagem = "joker_white.png";
        }
    }

    @Override
    boolean validMove(int xFinal, int yFinal, Tabuleiro tabuleiro) {

        ContadorJogadas contador = tabuleiro.getContadorJogadas();

        int rondaJoker = contador.getRondasJoker();

        Peca pecaAtual = obterPecaCorrespondente(rondaJoker);

        return pecaAtual.validMove(xFinal,yFinal,tabuleiro);
    }



    private Peca obterPecaCorrespondente(int ronda) {


        switch (ronda) {
            case 1:
                Peca peca1 = new Rainha(getID(),getTipo(), getEquipa(), getAlcunha());
                peca1.setCoordenadas(getX(),getY());
                return peca1;

            case 2:
                Peca peca2 = new PoneiMagico(getID(),getTipo(), getEquipa(), getAlcunha());
                peca2.setCoordenadas(getX(),getY());
                return peca2;
            case 3:
                Peca peca3 = new PadreDaVila(getID(),getTipo(), getEquipa(), getAlcunha());
                peca3.setCoordenadas(getX(),getY());
                return peca3;
            case 4:
                return new TorreHorizontal(getID(),getTipo(), getEquipa(), getAlcunha());
            case 5:
                return new TorreVertical(getID(),getTipo(), getEquipa(), getAlcunha());
            case 6:
                return new HomerSimpson(getID(),getTipo(), getEquipa(), getAlcunha());

            default:
                throw new IllegalArgumentException("Tipo de peça inválido");
        }
    }


}




