package com.hitallow.jogodavelha;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Observer{


    private ImageView pos00;
    private ImageView pos01;
    private ImageView pos02;
    private ImageView pos10;
    private ImageView pos11;
    private ImageView pos12;
    private ImageView pos20;
    private ImageView pos21;
    private ImageView pos22;

    private JogoDaVelha game;

    private TextView tv_jogadorDaVez;
    public MainActivity(){
        this.game = new JogoDaVelha(this);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_jogadorDaVez = findViewById(R.id.jogadorDaVez);

        pos00 = findViewById(R.id.pos00);
        pos01 = findViewById(R.id.pos01);
        pos02 = findViewById(R.id.pos02);
        pos10 = findViewById(R.id.pos10);
        pos11 = findViewById(R.id.pos11);
        pos12 = findViewById(R.id.pos12);
        pos20 = findViewById(R.id.pos20);
        pos21 = findViewById(R.id.pos21);
        pos22 = findViewById(R.id.pos22);


        iniciaEvento(pos00 , 0 , 0);
        iniciaEvento(pos01 , 0 , 1);
        iniciaEvento(pos02 , 0 , 2);
        iniciaEvento(pos10 , 1 , 0);
        iniciaEvento(pos11 , 1 , 1);
        iniciaEvento(pos12 , 1 , 2);
        iniciaEvento(pos20 , 2 , 0);
        iniciaEvento(pos21 , 2 , 1);
        iniciaEvento(pos22 , 2, 2);


    }


    protected  void iniciaEvento(ImageView img , final int  x, final int y){
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.game.jogar(x, y );
            }
        });
    }


    @Override
    public void marcouX(int x, int y) {
        if(x == 0){
            if(y == 0){
                pos00.setImageResource(R.drawable.red_x);
            }else if(y == 1){
                pos01.setImageResource(R.drawable.red_x);
            }else{
                pos02.setImageResource(R.drawable.red_x);
            }
        }else if(x == 1){
            if(y == 0){
                pos10.setImageResource(R.drawable.red_x);
            }else if(y == 1){
                pos11.setImageResource(R.drawable.red_x);
            }else{
                pos12.setImageResource(R.drawable.red_x);
            }
        }else if(x == 2){
            if(y == 0) {
                pos20.setImageResource(R.drawable.red_x);
            }else if(y == 1){
                pos21.setImageResource(R.drawable.red_x);
            }else{
                pos22.setImageResource(R.drawable.red_x);
            }
            }
        }



    @Override
    public void marcouBola(int x, int y) {
        if(x == 0){
            if(y == 0){
                pos00.setImageResource(R.drawable.red_o);
            }
            else if(y == 1){
                pos01.setImageResource(R.drawable.red_o);
            }else{
                pos02.setImageResource(R.drawable.red_o);
            }
        }else if(x == 1){
            if(y == 0){
                pos10.setImageResource(R.drawable.red_o);
            }else if(y == 1){
                pos11.setImageResource(R.drawable.red_o);
            }else{
                pos12.setImageResource(R.drawable.red_o);
            }
        }else if(x == 2){
            if(y == 0) {
                pos20.setImageResource(R.drawable.red_o);
            }else if(y == 1){
                pos21.setImageResource(R.drawable.red_o);
            }else{
                pos22.setImageResource(R.drawable.red_o);
            }
        }

    }
    public void restartGame(){
        pos00.setImageResource(0);
        pos01.setImageResource(0);
        pos02.setImageResource(0);
        pos10.setImageResource(0);
        pos11.setImageResource(0);
        pos12.setImageResource(0);
        pos20.setImageResource(0);
        pos21.setImageResource(0);
        pos22.setImageResource(0);

    }

    @Override
    public void bolaGanhou() {
        new AlertDialog.Builder(this).setTitle("O jogador com bola venceu").
                setMessage("Clique em continuar para iniciar outra partida!").
                setCancelable(false).setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.game.restartGame();
                        MainActivity.this.restartGame();
                    }
        }).show();


    }

    @Override
    public void xGanhou() {
        new AlertDialog.Builder(this).setTitle("O Xis ganhou").
                setMessage("Clique em continuar para iniciar outra partida").setCancelable(false).
                setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.game.restartGame();
                        MainActivity.this.restartGame();
                    }
        }).show();

    }

    @Override
    public void jogoTrancado() {

        new AlertDialog.Builder(this).setCancelable(false).setTitle("O jogo finalizou sem vencedor").setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.game.restartGame();
                MainActivity.this.restartGame();
            }
        }).show();

    }
    @Override
    public void vezDaBola(){
            tv_jogadorDaVez.setText("Agora é a vez da Bola");

    }
    @Override
    public void vezDoX(){
        tv_jogadorDaVez.setText("Agora é a vez do X");
    }
}
