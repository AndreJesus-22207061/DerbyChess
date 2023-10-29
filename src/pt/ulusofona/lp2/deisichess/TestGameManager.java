package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestGameManager {

    public GameManager jogo = new GameManager();



    @Test
    public void loadGame4x4(){
        File file = new File("test-files","4x4.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        ArrayList<Peca> pecasTeste = new ArrayList<>();

        Peca peca1 = new Peca(1,0,0,"Chefe");
        peca1.setCoordenadas(1,0);
        pecasTeste.add(peca1);

        Peca peca2 = new Peca(2,0,0,"Selvagem");
        peca2.setCoordenadas(3,0);
        pecasTeste.add(peca2);

        Peca peca3 = new Peca(3,0,0,"Grande Artista");
        peca3.setCoordenadas(2,1);
        pecasTeste.add(peca3);

        Peca peca4 = new Peca(4,0,1,"O Maior");
        peca4.setCoordenadas(2,3);
        pecasTeste.add(peca4);

        Peca peca5 = new Peca(5,0,1,"O Amigo");
        peca5.setCoordenadas(1,3);
        pecasTeste.add(peca5);

        Peca peca6 = new Peca(6,0,1,"O Beberolas");
        peca6.setCoordenadas(1,2);
        pecasTeste.add(peca6);

        assertEquals(pecasTeste,tabuleiro.pecas);

    }
    @Test
    public void loadGame4x4jaVitoria(){
        File file = new File("test-files","4x4jaVitoria.txt");
        jogo.reset();
        jogo.loadGame(file);
        jogo.gameOver();
        Tabuleiro tabuleiro = jogo.tabuleiro;

        ArrayList<Peca> pecasTeste = new ArrayList<>();

        Peca peca1 = new Peca(1,0,0,"Chefe");
        peca1.setCoordenadas(1,0);
        pecasTeste.add(peca1);

        Peca peca2 = new Peca(2,0,0,"Selvagem");
        peca2.setCoordenadas(3,0);
        pecasTeste.add(peca2);

        Peca peca3 = new Peca(3,0,0,"Grande Artista");
        peca3.setCoordenadas(2,1);
        pecasTeste.add(peca3);

        Peca peca4 = new Peca(4,0,1,"O Maior");
        peca4.setCoordenadas(2,3);
        pecasTeste.add(peca4);

        Peca peca5 = new Peca(5,0,1,"O Amigo");
        peca5.setCoordenadas(1,3);
        pecasTeste.add(peca5);

        Peca peca6 = new Peca(6,0,1,"O Beberolas");
        peca6.setCoordenadas(1,2);
        pecasTeste.add(peca6);

        assertEquals(pecasTeste,tabuleiro.pecas);

    }



    @Test
    public void loadGame8x8(){
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        ArrayList<Peca> pecasTeste = new ArrayList<>();

        Peca peca1 = new Peca(1,0,0,"Chefe");
        peca1.setCoordenadas(1,0);
        pecasTeste.add(peca1);

        Peca peca2 = new Peca(2,0,0,"Selvagem");
        peca2.setCoordenadas(3,0);
        pecasTeste.add(peca2);

        Peca peca3 = new Peca(3,0,0,"Grande Artista");
        peca3.setCoordenadas(2,1);
        pecasTeste.add(peca3);

        Peca peca4 = new Peca(4,0,0,"Amante de Praia");
        peca4.setCoordenadas(4,1);
        pecasTeste.add(peca4);

        Peca peca5 = new Peca(5,0,0,"Artolas");
        peca5.setCoordenadas(6,1);
        pecasTeste.add(peca5);

        Peca peca6 = new Peca(6,0,1,"O Maior");
        peca6.setCoordenadas(4,5);
        pecasTeste.add(peca6);

        Peca peca7 = new Peca(7,0,1,"O Amigo");
        peca7.setCoordenadas(2,6);
        pecasTeste.add(peca7);

        Peca peca8 = new Peca(8,0,1,"O Beberolas");
        peca8.setCoordenadas(6,6);
        pecasTeste.add(peca8);

        Peca peca9 = new Peca(9,0,1,"O Esperto");
        peca9.setCoordenadas(2,7);
        pecasTeste.add(peca9);

        Peca peca10 = new Peca(10,0,1,"O Barulhento");
        peca10.setCoordenadas(4,7);
        pecasTeste.add(peca10);

        assertEquals(pecasTeste,tabuleiro.pecas);

    }

    @Test
    public void testGetSquareInfo() {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        // -------------------Teste para coordenadas inválidas---------------------
        String[] resultInvalid = jogo.getSquareInfo(10, 20);
        assertNull(resultInvalid);


        // -------------------Teste para coordenadas com peça-------------------------------
        String[] resultComPeca = jogo.getSquareInfo(1, 0);
        assertArrayEquals(new String[]{"1", "0", "0", "Chefe", null}, resultComPeca);


        // ------------------ Teste para coordenadas sem peça-----------------------------
        String[] resultSemPeca = jogo.getSquareInfo(2, 2);
        assertArrayEquals(new String[]{}, resultSemPeca);
    }


    @Test
    public void testGetPieceInfo() {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        // -------------------Teste para peça inválida---------------------
        String[] resultInvalid = jogo.getPieceInfo(11);
        assertArrayEquals(new String[]{}, resultInvalid);


        // -------------------Teste para peça válida-------------------------------
        String[] resultComPeca = jogo.getPieceInfo(1);
        assertArrayEquals(new String[]{"1", "0", "0", "Chefe","em jogo","1","0"}, resultComPeca);



    }

    @Test
    public void testGetPieceInfoAsString() {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        // -------------------Teste para peça inválida---------------------
        String resultInvalid = jogo.getPieceInfoAsString(11);
        assertNull(resultInvalid);


        // -------------------Teste para peça válida-------------------------------
        String resultComPeca = jogo.getPieceInfoAsString(1);
        assertEquals("1 | 0 | 0 | Chefe @ (1, 0)", resultComPeca);



    }


    @Test
    public void movePosicaoInvalida() {
        File file = new File("test-files","4x4.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;



        boolean jogada = jogo.move(2,1,0,3 );
        String[] resultComPeca = jogo.getPieceInfo(3);
        assertArrayEquals(new String[]{"3", "0", "0", "Grande Artista","em jogo","2","1"}, resultComPeca); //A peca mantem se igual pois é jogada invalida
        assertFalse(jogada);                                                                               // Logo a peca nao se move fica nas mesmas coordenadas



    }

    @Test
    public void moveCorreto() {
        File file = new File("test-files","4x4.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;



        boolean jogada = jogo.move(2,1,2,2 );
        String[] resultComPeca = jogo.getPieceInfo(3);
        assertArrayEquals(new String[]{"3", "0", "0", "Grande Artista","em jogo","2","2"}, resultComPeca); //Peca move se e as coordenadas mudam
        String[] resultCasaAnterior = jogo.getSquareInfo(2,1);
        assertArrayEquals(new String[]{}, resultCasaAnterior);     // A casa onde a Peca estava fica a 0 (sem informaçao)
        assertTrue(jogada);



    }






}









