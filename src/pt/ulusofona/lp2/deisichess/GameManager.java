package pt.ulusofona.lp2.deisichess;


import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

    public class GameManager {

        boolean loadGame( File file ){
            return true;
        }

        int getBoardSize(){
            return 1;
        }

        boolean move( int x0, int y0, int x1, int y1){
            return true;
        }

        String[] getSquareInfo(int x, int y){
            String[] dibil3 = new String[1];
            return dibil3;
        }

        String[] getPieceInfo(int ID){
            String[] dibil= new String[1];
            return dibil;
        }

        String getPieceInfoAsString(int ID){
            return "aa";
        }

        int getCurrentTeamID(){
            return 1;
        }

        boolean gameOver(){
            return true;
        }

        ArrayList<String> getGameResults(){
            ArrayList<String> aaa = new ArrayList<>();
            return aaa;
        }

        JPanel getAuthorsPanel(){
            return ;
        }
    }


