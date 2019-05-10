package com.hitallow.jogodavelha;

import java.util.ArrayList;
import java.util.List;

public class JogoDaVelha {

    public List<Observer> observeres ;

    public static final int VAZIO = -10;
    private int campo[][];
    private int atual;
    private int anterior;


    private int totalJogadas;

    public JogoDaVelha(Observer o)
    {

        this.campo = new int[3][3];
        this.atual = 1;
        this.anterior =0;

        this.totalJogadas = 9;
        this.zerarMatriz();

        this.observeres =  new ArrayList<>();

        this.addObserver(o);
    }

    public void zerarMatriz(){
        for (int i = 0 ; i <3 ;i++){
            for(int j= 0 ; j<3 ; j++){
                this.campo[i][j] = VAZIO ;
            }

        }
    }

    public void restartGame(){
        this.zerarMatriz();
        this.totalJogadas = 9;
    }

    // verifica se o jogo terminou!
    public boolean endGame()
    {
        if(this.isTerminated() ){
            return true;
        }else if(this.totalJogadas == 0){
            return true;
        }
        return false;
    }
    public void restoreTotalJogadas(){
        this.totalJogadas = 9;
    }
    public int getAtual(){

        return this.atual;
    }
    public int getTotalJogadas(){
        return this.totalJogadas;
    }

    // insere um valor na matriz
    public void jogar(int x , int y ){
        if(this.campo[x][y] == VAZIO) {
            this.campo[x][y] = this.atual;
            this.anterior = this.atual;
            this.atual = (this.atual + 1) % 2;
            this.totalJogadas--;

            this.eventosParaObserver(x , y);


        }
    }

    public void eventosParaObserver(int x, int y){
        // vê quem jogou
        if (anterior == 1){
            fireJogouX(x, y );
        }else{
            fireJogouBola(x, y );
        }
        // vê se alquem ganou
        if(isTerminated()){
            // vê quem ganhou
            if(this.anterior == 1){
                firexGanhou();
            }else{
                fireBolaGanhou();
            }
        }
        // ve se o jogo esta trancado
        else if(totalJogadas == 0){
            fireJogoTrancado();
        }
        if(atual == 1){
            this.fireVezDoX();
        }else{
            this.fireVezDaBola();
        }


    }

    // verfica todas as possibilidades de alguem ter vencido o jogo



    private boolean isTerminated()
    {
        // linha superior
        if (campo[0][0] == campo[0][1] && campo[0][1] == campo[0][2]){
            return campo[0][0] != VAZIO ;
        }
        // diagonal principal
        if(campo[0][0] == campo[1][1] && campo[1][1] == campo[2][2]){
            return campo[0][0] != VAZIO ;
        }
        // coluna direita
        if(campo[0][0] == campo[1][0] && campo[1][0] == campo[2][0]){
            return campo[0][0] != VAZIO ;
        }
        if(campo[0][1] == campo[1][1] && campo[1][1]==campo[2][1]){
            return campo[0][1] != VAZIO ;
        }
        if (campo[0][2]== campo[1][2] && campo[1][2] == campo[2][2]){
            return campo[0][2] != VAZIO ;
        }
        if(campo[0][2] == campo[1][1] && campo[1][1] == campo[2][0]){
            return campo[0][2] != VAZIO ;
        }
        if (campo[1][0] == campo[1][1] && campo[1][1] == campo[1][2]){
            return campo[1][0] != VAZIO ;
        }
        if(campo[2][0] == campo[2][1] && campo[2][1] == campo[2][2]){
            return campo[2][0] != VAZIO ;
        }
        return false;
    }

    public void addObserver(Observer o ){
        this.observeres.add(o);
    }
    public void removeObserver(Observer o ){
        this.observeres.remove(o);
    }



    // para observer
    public void fireJogouX(int x, int y){
        for (Observer o: observeres) {
            o.marcouX(x , y );
        }
    }
    public void fireJogouBola(int x, int y){
        for (Observer o: observeres) {
            o.marcouBola(x , y);
        }
    }

    public void fireBolaGanhou(){
        for (Observer o: observeres) {
            o.bolaGanhou();
        }
    }
    public void firexGanhou(){
        for (Observer o: observeres) {
            o.xGanhou();
        }
    }
    public void fireJogoTrancado(){
        for (Observer o: observeres) {
            o.jogoTrancado();
        }
    }
    public void fireVezDoX(){
        for (Observer o: observeres) {
            o.vezDoX();
        }
    }
    public void fireVezDaBola(){
        for (Observer o: observeres) {
            o.vezDaBola();
        }
    }


}
