package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Peca{
    public HomerSimpson(int id, int equipa, String alcunha) {
        super(id, equipa, alcunha);
        definirPontos();
        setTipo(TipoPeca.HOMER);
    }

    @Override
    String toString(Tabuleiro tabuleiro) {
        if(!getEstado()) {
            return getID() + " | " +getTipoString()+ "| " +getValor()+ " | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }else {
            ContadorJogadas contadorJogadas = tabuleiro.getContadorJogadas();
            int ronda = contadorJogadas.getRondaAtual();

            if(estaADormir(ronda)){
                return "Doh! zzzzzz";
            }

            return getID() + " | " +getTipoString()+ "| " +getValor()+ " | " + getEquipa() + " | " + getAlcunha() +  " @ (" + getX() + ", " + getY() + ")";

        }

    }

    @Override
    String getTipoString() {
        return "Homer Simpson";
    }

    @Override
    void definirPontos() {
        this.valor = 2;
    }

    @Override
    boolean validMove(int xFinal, int yFinal, Tabuleiro tabuleiro) {

        ContadorJogadas contadorJogadas = tabuleiro.getContadorJogadas();

        int ronda = contadorJogadas.getRondaAtual();

        if ((xFinal < 0 || xFinal >= tabuleiro.getTamanho() || yFinal < 0 || yFinal >= tabuleiro.getTamanho())) {
            return false;
        }

        if(estaADormir(ronda)){
            return false;
        }



        if ((xFinal == getX() && (yFinal == getY() + 1 || yFinal == getY() - 1))) {
            // Movimento vertical //

            return true;
        }

        if (yFinal == getY() && (xFinal == getX() + 1 || xFinal == getX() - 1)) {
            // Movimento horizontal //
            return true;
        }

        if ((xFinal == getX() + 1 || xFinal == getX() - 1) && (yFinal == getY() + 1 || yFinal == getY() - 1)) {
            // Movimento diagonal //
            return true;
        }

        return false;
    }


    boolean estaADormir(int ronda){
        if(ronda%3==0){
            return true;
        }
        return false;
    }







}
