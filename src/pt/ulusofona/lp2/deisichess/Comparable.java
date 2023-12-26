package pt.ulusofona.lp2.deisichess;

public class Comparable implements java.lang.Comparable<Comparable> {
        int x;
        int y;

        int valor;

    public Comparable(int x, int y, int valor) {
        this.x = x;
        this.y = y;
        this.valor = valor;
    }



    @Override
    public String toString() {
        return "(" + x + "," + y + ") -> " + valor ;
    }

    @Override
    public int compareTo( Comparable other) {
        if(this.valor == other.valor){
            return 0;
        }
        if(this.valor < other.valor){
            return 1;
        }else{
            return -1;
        }
    }
}
