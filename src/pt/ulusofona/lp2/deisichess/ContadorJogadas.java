package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class ContadorJogadas {

    private int pecasPretasCapturadas =0;

    private int contadorSemCaptura = 0;
    private int jogadasValidasPreta=0;

    private int jogadasInvalidasPreta=0;

    private int pecasBrancasCapturadas =0;

    private int jogadasValidasBranca=0;

    private int jogadasInvalidasBranca= 0;

    private String resultado;





    void pecaFoiCapturada(int equipa){
        if(equipa == 0){
            this.pecasPretasCapturadas++;
        }else{
            this.pecasBrancasCapturadas++;
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

    void defineResultado(String resultado){
        this.resultado = resultado;
    }

    int getRondaAtual(){
        return this.jogadasValidasPreta + this.jogadasValidasBranca;
    }

    ArrayList<String> menuFinal(){
        ArrayList<String> menuFinal = new ArrayList<>();

        String linha1 = "JOGO DE CRAZY CHESS";
        String linha2 = "Resultado: " +resultado;
        String linha3 = "---";
        String linha4="Equipa das Pretas";
        String linha5= Integer.toString(pecasBrancasCapturadas);
        String linha6=Integer.toString(jogadasValidasPreta);
        String linha7=Integer.toString(jogadasInvalidasPreta);
        String linha8="Equipa das Brancas";
        String linha9=Integer.toString(pecasPretasCapturadas);
        String linha10 =Integer.toString(jogadasValidasBranca);
        String linha11 = Integer.toString(jogadasInvalidasBranca);

        menuFinal.add(linha1);
        menuFinal.add(linha2);
        menuFinal.add(linha3);
        menuFinal.add(linha4);
        menuFinal.add(linha5);
        menuFinal.add(linha6);
        menuFinal.add(linha7);
        menuFinal.add(linha8);
        menuFinal.add(linha9);
        menuFinal.add(linha10);
        menuFinal.add(linha11);

        return menuFinal;
    }

}
