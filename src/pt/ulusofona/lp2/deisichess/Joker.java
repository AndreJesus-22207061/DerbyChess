package pt.ulusofona.lp2.deisichess;

public class Joker extends Peca {
    public Joker(int id, int equipa, String alcunha) {
        super(id, equipa, alcunha);
        definirPontos();
        setTipo(TipoPeca.J0KER);
    }

    @Override
    String toString(Tabuleiro tabuleiro) {
        ContadorJogadas contador = tabuleiro.getContadorJogadas();
        int rondas = contador.getRondasJoker();
        Peca pecaAtual = obterPecaCorrespondente(rondas);
        TipoPeca tipoPeca = pecaAtual.getTipo();

        if(!getEstado()) {
            return getID() + " | "+getTipoString()+ pecaAtual.getTipoString() + " | "+getValor()+ " | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }
        return  getID() + " | Joker/" + pecaAtual.getTipoString() + " | "+getValor()+ " | " + getEquipa() + " | " + getAlcunha() + " @ (" + getX() + ", " + getY() + ")";
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
    boolean validMove(int xFinal, int yFinal, Tabuleiro tabuleiro) {

        ContadorJogadas contador = tabuleiro.getContadorJogadas();

        int rondaJoker = contador.getRondasJoker();

        Peca pecaAtual = obterPecaCorrespondente(rondaJoker);

        return pecaAtual.validMove(xFinal,yFinal,tabuleiro);
    }



    private Peca obterPecaCorrespondente(int ronda) {


        switch (ronda) {
            case 1:
                return new Rainha(getID(), getEquipa(), getAlcunha());
            case 2:
                return new PoneiMagico(getID(), getEquipa(), getAlcunha());
            case 3:
                return new PadreDaVila(getID(), getEquipa(), getAlcunha());
            case 4:
                return new TorreHorizontal(getID(), getEquipa(), getAlcunha());
            case 5:
                return new TorreVertical(getID(), getEquipa(), getAlcunha());
            case 6:
                return new HomerSimpson(getID(), getEquipa(), getAlcunha());

            default:
                throw new IllegalArgumentException("Tipo de peça inválido");
        }
    }


}




