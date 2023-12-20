package pt.ulusofona.lp2.deisichess;

public class ContadorRondas {

    private int rondasJoker= 1;

    private int rondasSemCaptura = 0;

    private String resultado;



    int getRondasJokerE(){
        return this.rondasJoker;
    }

    void incrementaRondaJokerE(){
        this.rondasJoker++;
    }

    void resetRondaJokerE(){
        this.rondasJoker = 1;
    }

    int getRondasSemCaptura(){
        return this.rondasSemCaptura;
    }

    void jogadaConcluidaSemCapturaE(){
        this.rondasSemCaptura++;
    }


    void resetRondasSemCapturaE(){
        rondasSemCaptura = 0;
    }

    void defineResultadoE(String resultado){
        this.resultado = resultado;
    }

    String getResultado(){
        return this.resultado;
    }





}
