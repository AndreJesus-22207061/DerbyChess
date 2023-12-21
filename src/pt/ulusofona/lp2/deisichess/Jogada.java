package pt.ulusofona.lp2.deisichess;

public class Jogada {

    private int xOrigem;
    private int yOrigem;
    private int xDestino;
    private int yDestino;
    private boolean captura;

    private int idPecaCapturada;

    public Jogada(int xOrigem, int yOrigem, int xDestino, int yDestino, boolean captura ) {
        this.xOrigem = xOrigem;
        this.yOrigem = yOrigem;
        this.xDestino = xDestino;
        this.yDestino = yDestino;
        this.captura = captura;
    }

    public int getXOrigem() {
        return xOrigem;
    }

    public int getYOrigem() {
        return yOrigem;
    }

    public int getXDestino() {
        return xDestino;
    }

    public int getYDestino() {
        return yDestino;
    }

    public boolean houveCaptura() {
        return captura;
    }

    public void addIDPecaCapturada(int id){
        this.idPecaCapturada = id;
    }

    public int getIdPecaCapturada() {
        return idPecaCapturada;
    }



}
