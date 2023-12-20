package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Throwable {

   String getProblemDescription(){

       return "Dibil";
   }

   int  getLineWithError(){

       return 0;
   }




}
