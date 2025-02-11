package com.game.units;

import com.game.core.Recurso;

public abstract class Unidade {
    protected int saude;
    protected int ataque;
    protected int velocidade;
    protected int x, y; // Posição no mapa
    protected Recurso recursoNecessario; // Novo campo para recurso necessário
    protected int custoRecurso; // Novo campo para custo do recurso
    protected int custoPopulacao; // Novo campo para custo populacional

    public Unidade(int x, int y, int saude, int velocidade, int ataque) {
        this.x = x;
        this.y = y;
        this.saude = saude;
        this.velocidade = velocidade;
        this.ataque = ataque;

    }
    // Getters
    public int getX() { return x; }
    public int getY() { return y; }

    public abstract void mover(int newX, int newY);
    public abstract void performAction();
    public abstract Recurso getRecursoNecessario();
    public abstract int getCustoRecurso();
    public abstract int getCustoPopulacao();
}
