package com.game.units;

public abstract class Unidade {
    protected int saude;
    protected int ataque;
    protected int velocidade;
    protected int x, y; // Posição no mapa
    public Unidade(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }

    public abstract void mover(int newX, int newY);
    public abstract void performAction();
}
