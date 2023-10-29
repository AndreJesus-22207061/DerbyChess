package pt.ulusofona.lp2.deisichess;

public class ContadorJogadas {

    int capturasPreta=0;

    int contadorSemCaptura = 0;
    int jogadasValidasPreta=0;

    int jogadasInvalidasPreta=0;

    int capturasBranca=0;

    int jogadasValidasBranca=0;

    int jogadasInvalidasBranca= 0;

    int contadorDeJogadas= 0;


    int getContador(){
        return this.contadorDeJogadas;
    }

    void jogadaConcluida(){
        this.contadorDeJogadas++;
    }

    void pecaFoiCapturada(int equipa){
        if(equipa == 0){
            this.capturasPreta++;
        }else{
            this.capturasBranca++;
        }
    }

    void jogadaValida(int equipa){
        if(equipa == 0){
            this.jogadasValidasPreta++;
        }else{
            this.jogadasValidasBranca++;
        }

    }

    void jogadaInvalida(int equipa){
        if(equipa == 0){
            this.jogadasInvalidasPreta++;
        }else{
            this.jogadasInvalidasBranca++;
        }
    }


    int getContadorSemCaptura(){
        return this.contadorSemCaptura;
    }

    void jogadaConcluidaSemCaptura(){
        this.contadorSemCaptura++;
    }


    void resetContadorSemCaptura(){
        contadorSemCaptura = 0;
    }










}
