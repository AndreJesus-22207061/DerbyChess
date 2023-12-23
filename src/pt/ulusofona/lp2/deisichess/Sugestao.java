package pt.ulusofona.lp2.deisichess;

import org.jetbrains.annotations.NotNull;

public class Sugestao implements Comparable<Sugestao>{
        int x;
        int y;

    public Sugestao(int x, int y, int valor) {
        this.x = x;
        this.y = y;
        this.valor = valor;
    }

    int valor;

    @Override
    public String toString() {
        return "(" + x + "," + y + ") ->" + valor ;
    }

    @Override
    public int compareTo( Sugestao other) {
        if(this.valor == other.valor){
            return 0;
        }
        if(this.valor < other.valor){
            return -1;
        }else{
            return 1;
        }
    }
}
