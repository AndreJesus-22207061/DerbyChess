package pt.ulusofona.lp2.deisichess;

import java.util.Objects;

public class Peca {
    int id;

    int tipo;

    int equipa;

    String alcunha;

    int x;

    int y;

    public Peca(int id, int tipo, int equipa, String alcunha) {
        this.id = id;
        this.tipo = tipo;
        this.equipa = equipa;
        this.alcunha = alcunha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peca peca = (Peca) o;
        return id == peca.id && tipo == peca.tipo && equipa == peca.equipa && x == peca.x && y == peca.y && Objects.equals(alcunha, peca.alcunha);
    }



    int getID (){
        return id;
    }

    int getTipo(){
        return tipo;
    }

    int getEquipa(){
        return equipa;
    }

    String getAlcunha(){
        return alcunha;
    }




    void setCoordenadas (int coordenadaX, int coordenadaY ){
        this.x = coordenadaX;
        this.y = coordenadaY;
    }

int getX(){
        return x;
}

int getY(){
        return y;
}


}
