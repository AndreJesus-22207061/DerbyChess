package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Peca {
   private int id;

    private int tipo;

    private int equipa;

   private String alcunha;

   private boolean estado;

   protected int x = -1; //linhas

    protected int y = -1; //colunas

    protected int valor;

    protected String imagem;

    protected CountJogadas countJogadas;

    protected ArrayList<Integer> listaPecasCapturadas;

    public Peca(int id,int tipo, int equipa, String alcunha) {
        this.id = id;
        this.tipo = tipo;
        this.equipa = equipa;
        this.alcunha = alcunha;
        this.estado = true;
        this.countJogadas = new CountJogadas(equipa);
        this.listaPecasCapturadas = new ArrayList<>();
    }


    abstract String toString(ContadorRondas contadorRondas);


    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject){
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()){
            return false;
        }
        Peca peca = (Peca) otherObject;
        return id == peca.getID() && equipa == peca.getEquipa() && estado == peca.getEstado() && x == peca.getX() && y == peca.getY() && valor == peca.getValor() && tipo == peca.getTipo() && Objects.equals(alcunha, peca.getAlcunha());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, equipa, alcunha, estado, x, y, valor);
    }



    int getID() {
        return id;
    }

    int getTipo() {
        return tipo;
    }

    int getEquipa() {
        return equipa;
    }

    String getAlcunha() {
        return alcunha;
    }

    boolean getEstado() {
        return estado;
    }

   protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    abstract String getTipoString(ContadorRondas contadorRondas);

    int getValor() {
        return valor;
    }

    String getImagem(){
        return imagem;
    }

    ArrayList<Integer> getListaPecasCapturadas(){
        return this.listaPecasCapturadas;
    }

    void removePecaCapturada(int ID){
        for(Integer id : listaPecasCapturadas){
            if(ID == id ){
                listaPecasCapturadas.remove(id);
                break;
            }
        }
    }

    void adicionarPecaQueCaptorou(int id){
        this.listaPecasCapturadas.add(id);
    }

    int pontosCapturados(ArrayList<Peca> listaDePecas){
        int pontos = 0;
        for(int id : listaPecasCapturadas){
            for(Peca peca : listaDePecas){
                if(peca.getID()== id){
                    pontos = pontos+peca.getValor();
                }
            }
        }
        return pontos;
    }

    int mediaJogadas(){
        int jogadasValidas = getCountJogadas().getJogadasValidas();
        int jogadasInvalidas = getCountJogadas().getJogadasInvalidas();

        return jogadasValidas - jogadasInvalidas;
    }

    CountJogadas getCountJogadas(){
        return this.countJogadas;
    }

    abstract void definirPontos();

    abstract void defenirImagem();


    void setCoordenadas(int coordenadaX, int coordenadaY) {
        this.x = coordenadaX;
        this.y = coordenadaY;
    }


    String estaEmJogo() {
        if (!estado) {
            return "capturado";
        } else {
            return "em jogo";
        }

    }

    void capturada(){
        this.estado = false;
    }

    void reativar(int x , int y) {
        this.estado = true;
        setCoordenadas(x,y);
    }


    abstract boolean validMove(int x, int y, Tabuleiro tabuleiro);


}
