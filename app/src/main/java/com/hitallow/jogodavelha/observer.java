package com.hitallow.jogodavelha;

interface Observer {
    public void marcouX(int x, int y);
    public void marcouBola(int x, int y);
    public void bolaGanhou();
    public void xGanhou();
    public void jogoTrancado();

    public void vezDaBola();
    void vezDoX();

}
