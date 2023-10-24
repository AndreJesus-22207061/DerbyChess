package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TestGameManager {

    public GameManager jogo = new GameManager();



    @Test
    public void loadGame4x4(){
        File file = new File("test-files","4x4.txt");
        jogo.loadGame(file);

        Tabuleiro tabuleiro = GameManager.tabuleiro;

        ArrayList<Peca> pecasTeste = new ArrayList<>();

        Peca peca1 = new Peca(1,0,0,"Chefe");
        peca1.setCoordenadas(0,1);
        pecasTeste.add(peca1);

        Peca peca2 = new Peca(2,0,0,"Selvagem");
        peca2.setCoordenadas(0,3);
        pecasTeste.add(peca2);

        Peca peca3 = new Peca(3,0,0,"Grande Artista");
        peca3.setCoordenadas(1,2);
        pecasTeste.add(peca3);

        Peca peca4 = new Peca(4,0,1,"O Maior");
        peca4.setCoordenadas(3,2);
        pecasTeste.add(peca4);

        Peca peca5 = new Peca(5,0,1,"O Amigo");
        peca5.setCoordenadas(3,1);
        pecasTeste.add(peca5);

        Peca peca6 = new Peca(6,0,1,"O Beberolas");
        peca6.setCoordenadas(2,1);
        pecasTeste.add(peca6);

        assertEquals(pecasTeste,tabuleiro.pecas);

    }


    @Test
    public void loadGame8x8(){
        File file = new File("test-files","8x8.txt");
        jogo.loadGame(file);

        Tabuleiro tabuleiro = GameManager.tabuleiro;

        ArrayList<Peca> pecasTeste = new ArrayList<>();

        Peca peca1 = new Peca(1,0,0,"Chefe");
        peca1.setCoordenadas(0,1);
        pecasTeste.add(peca1);

        Peca peca2 = new Peca(2,0,0,"Selvagem");
        peca2.setCoordenadas(0,3);
        pecasTeste.add(peca2);

        Peca peca3 = new Peca(3,0,0,"Grande Artista");
        peca3.setCoordenadas(1,2);
        pecasTeste.add(peca3);

        Peca peca4 = new Peca(4,0,0,"Amante de Praia");
        peca4.setCoordenadas(1,4);
        pecasTeste.add(peca4);

        Peca peca5 = new Peca(5,0,0,"Artolas");
        peca5.setCoordenadas(1,6);
        pecasTeste.add(peca5);

        Peca peca6 = new Peca(6,0,1,"O Maior");
        peca6.setCoordenadas(5,4);
        pecasTeste.add(peca6);

        Peca peca7 = new Peca(7,0,1,"O Amigo");
        peca7.setCoordenadas(6,2);
        pecasTeste.add(peca7);

        Peca peca8 = new Peca(8,0,1,"O Beberolas");
        peca8.setCoordenadas(6,6);
        pecasTeste.add(peca8);

        Peca peca9 = new Peca(9,0,1,"O Esperto");
        peca9.setCoordenadas(7,2);
        pecasTeste.add(peca9);

        Peca peca10 = new Peca(10,0,1,"O Barulhento");
        peca10.setCoordenadas(7,4);
        pecasTeste.add(peca10);

        assertEquals(pecasTeste,tabuleiro.pecas);

    }








    /*a funcao de teste nao pode tar vazia
    a funcao de teste tem de ter pelo menos um assert
    a funcao de teste tem de chamar codigo do projeto
    o assert tem de comparar o resultado de uma funcao do projeto com um resultado esperado
    */

}
