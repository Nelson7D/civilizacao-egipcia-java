package com.game.units;

import com.game.core.Jogador;
import com.game.core.Recurso;

public abstract class Unidade {
    protected int saude;
    protected int ataque;
    protected int velocidade;
    protected int x, y; // Posição no mapa
    protected Recurso recursoNecessario; // Novo campo para recurso necessário
    protected int custoRecurso; // Novo campo para custo do recurso
    protected int custoPopulacao; // Novo campo para custo populacional
    protected Jogador jogador; // Novo campo para vincular a unidade ao jogador

    public Unidade(int x, int y, int saude, int velocidade, int ataque) {
        this.x = x;
        this.y = y;
        this.saude = saude;
        this.velocidade = velocidade;
        this.ataque = ataque;

    }

    public void tomarDano(int dano) {
        saude -= dano;
        if (saude <= 0) {
            morrer();
        }
    }

    protected void morrer() {
        System.out.println(this.getClass().getSimpleName()+ " destruída!");
    }
    // Getters

    public int getSaude() {return saude;}
    public int getX() { return x; }
    public int getY() { return y; }
    public Jogador getJogador() { return jogador; }

    public abstract void mover(int newX, int newY);
    public abstract void performAction();
    public abstract Recurso getRecursoNecessario();
    public abstract int getCustoRecurso();
    public abstract int getCustoPopulacao();
}
