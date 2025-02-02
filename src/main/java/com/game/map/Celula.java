package com.game.map;


import com.game.core.Recurso;

public class Celula {
    private TipoTerreno terreno;
    private Recurso recurso;
    private int quantidadeRecurso;
    private boolean ocupada;

    public Celula(TipoTerreno terreno, Recurso recurso, int quantidadeRecurso) {
        this.terreno = terreno;
        this.recurso = recurso;
        this.quantidadeRecurso = quantidadeRecurso;
        this.ocupada = false;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public void setQuantidadeRecurso(int quantidadeRecurso) {
        this.quantidadeRecurso = quantidadeRecurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public void setTerreno(TipoTerreno terreno) {
        this.terreno = terreno;
    }

    public TipoTerreno getTerreno() { return terreno; }
    public Recurso getRecurso() { return recurso; }
    public int getQuantidadeRecurso() { return quantidadeRecurso; }
    public boolean isOcupada() { return ocupada; }
}
