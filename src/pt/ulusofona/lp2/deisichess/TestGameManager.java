package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestGameManager {

    public GameManager jogo = new GameManager();


    @Test
    public void loadGame8x8() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        ArrayList<Peca> pecasTeste = new ArrayList<>();

        Peca peca1 = new Rei(1,0,10,"O Poderoso Chefao");
        peca1.setCoordenadas(0,0);
        pecasTeste.add(peca1);

        Peca peca2 = new Rainha(2,1,10,"A Dama Selvagem");
        peca2.setCoordenadas(1,0);
        pecasTeste.add(peca2);

        Peca peca3 = new PoneiMagico(3,2,10,"O Grande Artista");
        peca3.setCoordenadas(2,0);
        pecasTeste.add(peca3);

        Peca peca4 = new PadreDaVila(4,3,10,"Amante de Praia");
        peca4.setCoordenadas(3,0);
        pecasTeste.add(peca4);

        Peca peca5 = new TorreHorizontal(5,4,10,"Artolas");
        peca5.setCoordenadas(4,0);
        pecasTeste.add(peca5);

        Peca peca6 = new TorreVertical(6,5,10,"O Maior Grande");
        peca6.setCoordenadas(5,0);
        pecasTeste.add(peca6);

        Peca peca7 = new HomerSimpson(7,6,10,"Hommie");
        peca7.setCoordenadas(6,0);
        pecasTeste.add(peca7);

        Peca peca8 = new Joker(8,7,10,"O Beberolas");
        peca8.setCoordenadas(7,0);
        pecasTeste.add(peca8);

        Peca peca9 = new Rei(9,0,20,"Chefe dos Indios");
        peca9.setCoordenadas(0,7);
        pecasTeste.add(peca9);

        Peca peca10 = new Rainha(10,1,20,"A Barulhenta do Bairro");
        peca10.setCoordenadas(1,7);
        pecasTeste.add(peca10);

        Peca peca11 = new PoneiMagico(11,2,20,"My Little Pony");
        peca8.setCoordenadas(2,7);
        pecasTeste.add(peca11);

        Peca peca12 = new PadreDaVila(12,3,20,"Padreco");
        peca8.setCoordenadas(3,7);
        pecasTeste.add(peca12);

        Peca peca13 = new TorreHorizontal(13,4,20,"Torre Padreco");
        peca8.setCoordenadas(4,7);
        pecasTeste.add(peca13);

        Peca peca14 = new TorreVertical(14,5,20,"Torre Trapalhona");
        peca8.setCoordenadas(5,7);
        pecasTeste.add(peca14);

        Peca peca15 = new HomerSimpson(15,6,20,"Homer Jay Simpson");
        peca8.setCoordenadas(6,7);
        pecasTeste.add(peca15);

        Peca peca16 = new Joker(16,7,20,"O Bobo da Corte");
        peca8.setCoordenadas(7,7);
        pecasTeste.add(peca16);


        assertEquals(pecasTeste,tabuleiro.getListaPecas());

    }

    @Test
    public void testGetSquareInfo() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        // -------------------Teste para coordenadas inválidas---------------------
        String[] resultInvalid = jogo.getSquareInfo(10, 20);
        assertNull(resultInvalid);


        // -------------------Teste para coordenadas com peça-------------------------------
        String[] resultComPeca = jogo.getSquareInfo(0, 0);
        assertArrayEquals(new String[]{"1", "0", "10", "O Poderoso Chefao", null}, resultComPeca);


        // ------------------ Teste para coordenadas sem peça-----------------------------
        String[] resultSemPeca = jogo.getSquareInfo(2, 2);
        assertArrayEquals(new String[]{}, resultSemPeca);
    }


    @Test
    public void testGetPieceInfo() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        // -------------------Teste para peça inválida---------------------
        String[] resultInvalid = jogo.getPieceInfo(20);
        assertArrayEquals(new String[7], resultInvalid);


        // -------------------Teste para peça válida-------------------------------
        String[] resultComPeca = jogo.getPieceInfo(1);
        assertArrayEquals(new String[]{"1", "0", "10", "O Poderoso Chefao","em jogo","0","0"}, resultComPeca);



    }

    @Test
    public void testGetPieceInfoAsString() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        // -------------------Teste para peça inválida---------------------
        String resultInvalid = jogo.getPieceInfoAsString(20);
        assertNull(resultInvalid);


        // -------------------Teste para peça válida-------------------------------
        String resultComPeca = jogo.getPieceInfoAsString(1);
        assertEquals("1 | Rei | (infinito) | 10 | O Poderoso Chefao @ (0, 0)", resultComPeca);



    }


    @Test
    public void movePosicaoInvalida() throws IOException, InvalidGameInputException {
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
    public void moveCorreto() throws IOException, InvalidGameInputException {
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

    @Test
    public void jogoVitoriaBranca() throws IOException, InvalidGameInputException {
        File file = new File("test-files","4x4pequeno.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;
        jogo.move(1,0,1,1 );
        boolean jogada1 = jogo.gameOver();
        assertFalse(jogada1);

        jogo.move(1,2,1,1 );
        boolean jogada2 =jogo.gameOver();
        assertFalse(jogada2);

        jogo.move(3,0,2,0 );
        boolean jogada3 =jogo.gameOver();
        assertFalse(jogada3);

        jogo.move(1,1,2,0 );
        boolean jogada4 =jogo.gameOver();
        assertTrue(jogada4);


    }

    @Test
    public void jogoEmpate1x1() throws IOException, InvalidGameInputException {
        File file = new File("test-files","4x4pequeno.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;
        jogo.move(1,0,1,1 );
        boolean jogada1 = jogo.gameOver();
        assertFalse(jogada1);

        jogo.move(1,2,1,1 );
        boolean jogada2 =jogo.gameOver();
        assertFalse(jogada2);

        jogo.move(3,0,2,0 );
        boolean jogada3 =jogo.gameOver();
        assertFalse(jogada3);

        jogo.move(1,1,2,1 );
        boolean jogada4 =jogo.gameOver();
        assertFalse(jogada4);

        jogo.move(2,0,2,1 );
        boolean jogada5 =jogo.gameOver();
        assertFalse(jogada5);

        jogo.move(2,3,2,2 );
        boolean jogada6 =jogo.gameOver();
        assertFalse(jogada6);

        jogo.move(2,1,2,2 );
        boolean jogada7 =jogo.gameOver();
        assertTrue(jogada7);


    }




    @Test
    public void movePoneiMagico() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;
        jogo.move(2,0,4,2 );  //move ponei preto
        boolean jogada1 = jogo.gameOver();
        assertFalse(jogada1);
        jogo.move(1,7,1,5 ); // move rainha branco
        boolean jogada2 = jogo.gameOver();
        assertFalse(jogada2);
        jogo.move(0,0,0,1 ); // move rei preto
        boolean jogada3 = jogo.gameOver();
        assertFalse(jogada3);
        jogo.move(1,5,2,4 ); // move rainha branca
        boolean jogada4 = jogo.gameOver();
        assertFalse(jogada4);
        jogo.move(4,2,2,4 ); // move ponei preto , come rainha branca
        boolean jogada5 = jogo.gameOver();
        assertFalse(jogada5);


    }
    @Test
    public void Joker() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;
        jogo.move(7,0,7,1 );
        boolean jogada1 = jogo.gameOver();
        assertFalse(jogada1);

    }


    @Test
    public void Undo() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        jogo.move(1,0,1,5 );
        boolean jogada1 = jogo.gameOver();
        assertFalse(jogada1);
        jogo.move(1,7,1,5 );
        boolean jogada2 = jogo.gameOver();
        assertFalse(jogada2);
        jogo.undo();
        jogo.undo();

    }


    @Test
    public void rainhaUndo() throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;



        boolean mov1 = jogo.move(1,0,1,5 );
        assertTrue(mov1);
        boolean jogada1 = jogo.gameOver();
        assertFalse(jogada1);

        ContadorRondas contadorRondas = tabuleiro.getContadorRondas();
        int rondaAtual = contadorRondas.getRondaAtual();
        int rondaJoker = contadorRondas.getRondasJoker();
        int rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(1,rondaAtual);
        assertEquals(2,rondaJoker);
        assertEquals(0,rondasSemCaptura);


        boolean mov2 =jogo.move(1,7,1,5 );
        assertTrue(mov2);
        boolean jogada2 = jogo.gameOver();
        assertFalse(jogada2);

        contadorRondas = tabuleiro.getContadorRondas();
        rondaAtual = contadorRondas.getRondaAtual();
        rondaJoker = contadorRondas.getRondasJoker();
        rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(2,rondaAtual);
        assertEquals(3,rondaJoker);
        assertEquals(0,rondasSemCaptura);


        boolean mov3 =jogo.move(2,0,4,2 );
        assertTrue(mov3);
        boolean jogada3 = jogo.gameOver();
        assertFalse(jogada3);

        contadorRondas = tabuleiro.getContadorRondas();
        rondaAtual = contadorRondas.getRondaAtual();
        rondaJoker = contadorRondas.getRondasJoker();
        rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(3,rondaAtual);
        assertEquals(4,rondaJoker);
        assertEquals(1,rondasSemCaptura);


        boolean mov4 =jogo.move(3,7,6,4 );
        assertTrue(mov4);
        boolean jogada4 = jogo.gameOver();
        assertFalse(jogada4);

        contadorRondas = tabuleiro.getContadorRondas();
        rondaAtual = contadorRondas.getRondaAtual();
        rondaJoker = contadorRondas.getRondasJoker();
        rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(4,rondaAtual);
        assertEquals(5,rondaJoker);
        assertEquals(2,rondasSemCaptura);


        boolean mov5 =jogo.move(5,0,5,1 );
        assertTrue(mov5);
        boolean jogada5 = jogo.gameOver();
        assertFalse(jogada5);

        contadorRondas = tabuleiro.getContadorRondas();
        rondaAtual = contadorRondas.getRondaAtual();
        rondaJoker = contadorRondas.getRondasJoker();
        rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(5,rondaAtual);
        assertEquals(6,rondaJoker);
        assertEquals(3,rondasSemCaptura);


        boolean mov6 =jogo.move(6,4,4,2 );
        assertTrue(mov6);
        boolean jogada6 = jogo.gameOver();
        assertFalse(jogada6);

        contadorRondas = tabuleiro.getContadorRondas();
        rondaAtual = contadorRondas.getRondaAtual();
        rondaJoker = contadorRondas.getRondasJoker();
        rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(6,rondaAtual);
        assertEquals(1,rondaJoker);
        assertEquals(0,rondasSemCaptura);


        jogo.undo();
        contadorRondas = tabuleiro.getContadorRondas();
        rondaAtual = contadorRondas.getRondaAtual();
        rondaJoker = contadorRondas.getRondasJoker();
        rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(5,rondaAtual);
        assertEquals(6,rondaJoker);
        assertEquals(3,rondasSemCaptura);

        jogo.undo();
        contadorRondas = tabuleiro.getContadorRondas();
        rondaAtual = contadorRondas.getRondaAtual();
        rondaJoker = contadorRondas.getRondasJoker();
        rondasSemCaptura = contadorRondas.getRondasSemCaptura();

        assertEquals(4,rondaAtual);
        assertEquals(5,rondaJoker);
        assertEquals(2,rondasSemCaptura);

        jogo.getPieceInfo(8);
        jogo.getPieceInfoAsString(8);
        jogo.getSquareInfo(7,0);

    }



    @Test
    public void rainhaSugestao()throws IOException, InvalidGameInputException {
        File file = new File("test-files","8x8.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        jogo.getHints(1,0);





    }

    @Test
    public void soReis()throws IOException, InvalidGameInputException {
        File file = new File("test-files","4x4.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        boolean mov1 = jogo.move(2,1,1,2 );
        assertTrue(mov1);
        boolean jogada1 = jogo.gameOver();
        assertFalse(jogada1);

        boolean mov2 = jogo.move(1,3,1,2 );
        assertTrue(mov2);
        boolean jogada2 = jogo.gameOver();
        assertFalse(jogada2);

        boolean mov3 = jogo.move(1,0,1,1 );
        assertTrue(mov3);
        boolean jogada3 = jogo.gameOver();
        assertFalse(jogada3);

        boolean mov4 = jogo.move(1,2,2,2 );
        assertTrue(mov4);
        boolean jogada4 = jogo.gameOver();
        assertFalse(jogada4);

        boolean mov5 = jogo.move(1,1,2,2 );
        assertTrue(mov5);
        boolean jogada5 = jogo.gameOver();
        assertFalse(jogada5);

        boolean mov6 = jogo.move(2,3,2,2 );
        assertTrue(mov6);
        boolean jogada6 = jogo.gameOver();
        assertTrue(jogada6);




    }


    @Test
    public void SaveGameTest()throws IOException, InvalidGameInputException {
        File file = new File("test-files","4x4.txt");
        jogo.reset();
        jogo.loadGame(file);

        Tabuleiro tabuleiro = jogo.tabuleiro;

        boolean mov1 = jogo.move(1,2,2,1 );
        boolean jogada1 = jogo.gameOver();



        File fileSave = new File("test-files","branco.txt");
        jogo.saveGame(fileSave);






    }






}









