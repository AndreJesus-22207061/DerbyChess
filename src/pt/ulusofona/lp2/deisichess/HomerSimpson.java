package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Peca{
    public HomerSimpson(int id,int tipo ,int equipa, String alcunha) {
        super(id,tipo ,equipa, alcunha);
        definirPontos();
        defenirImagem();
    }

    @Override
    String toString(ContadorRondas contadorRondas) {
        if(!getEstado()) {
            return getID() + " | " +getTipoString(contadorRondas)+ " | " +getValor()+ " | " + getEquipa() + " | " + getAlcunha() + " @ (n/a)";
        }else{
            int ronda = contadorRondas.getRondaAtual();

            if(estaADormir(ronda)){
                return "Doh! zzzzzz";
            }

            return getID() + " | " +getTipoString(contadorRondas)+ " | " +getValor()+ " | " + getEquipa() + " | " + getAlcunha() +  " @ (" + getX() + ", " + getY() + ")";

        }

    }

    @Override
    String getTipoString(ContadorRondas contadorRondas) {
        return "Homer Simpson";
    }

    @Override
    void definirPontos() {
        this.valor = 2;
    }

    @Override
    void defenirImagem(){
        if(getEquipa()==10){
            this.imagem = "HomerPreto.png";
        }else{
            this.imagem = "HomerBranco.png";
        }
    }

    @Override
    boolean validMove(int xFinal, int yFinal, Tabuleiro tabuleiro) {

        ContadorRondas contadorRondas = tabuleiro.getContadorRondas();

        int ronda = contadorRondas.getRondaAtual();

        if ((xFinal < 0 || xFinal >= tabuleiro.getTamanho() || yFinal < 0 || yFinal >= tabuleiro.getTamanho())) {
            return false;
        }

        if(estaADormir(ronda)){
            return false;
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
        }else{
            return false;
        }
    }








}
