package pt.ulusofona.lp2.deisichess;

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

    int getID (){
        return id;
    }

    void setCoordenadas (int coordenadaX, int coordenadaY ){
        this.x = coordenadaX;
        this.y = coordenadaY;
    }




}
