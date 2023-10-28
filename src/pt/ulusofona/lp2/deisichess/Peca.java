package pt.ulusofona.lp2.deisichess;

import java.util.Objects;

public class Peca {
    int id;

    int tipo;

    int equipa;

    String alcunha;

    boolean estado;

    int x; //linhas

    int y; //colunas

    public Peca(int id, int tipo, int equipa, String alcunha) {
        this.id = id;
        this.tipo = tipo;
        this.equipa = equipa;
        this.alcunha = alcunha;
        this.estado = true;
    }

    @Override
    public String toString() {
        return id + " | " + tipo + " | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
    }

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


    int getID() {
        return id;
    }

    int getTipo() {
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

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }


    void setCoordenadas(int coordenadaX, int coordenadaY) {
        this.x = coordenadaX;
        this.y = coordenadaY;
    }


    String estaEmJogo() {
        if (!estado) {
            return "Capturada";
        } else {
            return "em jogo";
        }

    }

    boolean validMove(int x, int y, Tabuleiro tabuleiro) {
        if ((x < 0 || x >= tabuleiro.getTamanho() || y < 0 || y >= tabuleiro.getTamanho())) {
            return false;
        }

        if ((x == this.x && (y == this.y + 1 || y == this.y - 1))) {
            // Movimento vertical //

            return true;
        }

        if (y == this.y && (x == this.x + 1 || x == this.x - 1)) {
            // Movimento horizontal //
            return true;
        }

        if ((x == this.x + 1 || x == this.x - 1) && (y == this.y + 1 || y == this.y - 1)) {
            // Movimento diagonal //
            return true;
        }


        return false;
    }

}
