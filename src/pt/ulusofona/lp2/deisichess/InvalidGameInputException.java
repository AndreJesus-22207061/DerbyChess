package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Exception {

    private String problemDescription;
    private int lineWithError;

    public InvalidGameInputException(String problemDescription, int lineWithError) {
        this.problemDescription = problemDescription;
        this.lineWithError = lineWithError;
    }

   public String getProblemDescription() {
        return problemDescription;
    }

   public int getLineWithError() {
        return lineWithError;
    }

}