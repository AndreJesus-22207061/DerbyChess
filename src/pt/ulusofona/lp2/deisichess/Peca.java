package pt.ulusofona.lp2.deisichess;

import java.util.Objects;

public abstract class Peca {
   private int id;

    private TipoPeca tipo;

    private int equipa;

   private String alcunha;

   private boolean estado;

   protected int x = -1; //linhas

    protected int y = -1; //colunas

    protected int valor;

    public Peca(int id, int equipa, String alcunha) {
        this.id = id;
        this.equipa = equipa;
        this.alcunha = alcunha;
        this.estado = true;
    }


    abstract String toString(Tabuleiro tabuleiro);


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Peca peca = (Peca) o;
        return id == peca.id && tipo == peca.tipo && equipa == peca.equipa && x == peca.x && y == peca.y && Objects.equals(alcunha, peca.alcunha);
    }

    protected void setTipo(TipoPeca tipo){
        this.tipo = tipo;
    }


    int getID() {
        return id;
    }

    TipoPeca getTipo() {
        return tipo;
    }

    int getEquipa() {
        return equipa;
    }

    String getAlcunha() {
        return alcunha;
    }

    boolean getEstado() {
        return estado;
    }

   protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    abstract String getTipoString();

    int getValor() {
        return valor;
    }

    abstract void definirPontos();


    void setCoordenadas(int coordenadaX, int coordenadaY) {
        this.x = coordenadaX;
        this.y = coordenadaY;
    }


    String estaEmJogo() {
        if (!estado) {
            return "capturado";
        } else {
            return "em jogo";
        }

    }

    void capturada(){
        this.estado = false;
    }

     abstract boolean validMove(int x, int y, Tabuleiro tabuleiro);


}
