package pt.ulusofona.lp2.deisichess;

public class ContadorRondas {

    private int rondasJoker= 1;

    private int rondasSemCaptura = 0;

    private int rondaAtual = 0;

    private String resultado;



    int getRondasJoker(){
        return this.rondasJoker;
    }

    void incrementaRondaJoker(){
        this.rondasJoker++;
    }

    void decrementaRondaJoker(){
        this.rondasJoker--;
    }

    void resetRondaJoker(){
        this.rondasJoker = 1;
    }

    int getRondasSemCaptura(){
        return this.rondasSemCaptura;
    }

    void jogadaConcluidaSemCaptura(){
        this.rondasSemCaptura++;
    }


    void resetRondasSemCaptura(){
        rondasSemCaptura = 0;
    }

    void defineResultado(String resultado){
        this.resultado = resultado;
    }

    String getResultado(){
        return this.resultado;
    }

    int getRondaAtual(){
        return this.rondaAtual;
    }

    void incrementaRondaAtual(){
        this.rondaAtual++;
    }

    void decrementaRondaAtual(){
        this.rondaAtual--;
    }

    void decrementaRondasSemCaptura(){
        this.rondasSemCaptura--;
    }



}
