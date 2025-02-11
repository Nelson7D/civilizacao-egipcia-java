package com.game.map;


import com.game.core.Recurso;
import com.game.units.Unidade;

import javafx.scene.paint.Color;

public class Celula {
    private TipoTerreno terreno;
    private Recurso recurso; // Representa o recurso disponível nesta célula
    private int quantidadeRecurso; // Quantidade do recurso disponível
    private boolean ocupada; // Indica se a célula está ocupada
    private Unidade ocupante; // Unidade que ocupa esta célula

    public Celula(TipoTerreno terreno, Recurso recurso, int quantidadeRecurso) {
        this.terreno = terreno;
        this.recurso = recurso;
        this.quantidadeRecurso = quantidadeRecurso;
        this.ocupada = false;
    }

    public Color getColor() {
        return switch (terreno) {
            case TERRA_FERTIL -> Color.GREEN;
            case FLORESTA -> Color.DARKGREEN;
            case PEDREIRA -> Color.GRAY;
            case RIO -> Color.BLUE;
            case DESERTO -> Color.SANDYBROWN;
        };
    }

    public TipoTerreno getTerreno() {
        return terreno;
    }

    public void setTerreno(TipoTerreno terreno) {
        this.terreno = terreno;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public int getQuantidadeRecurso() {
        return quantidadeRecurso;
    }

    public void setQuantidadeRecurso(int quantidadeRecurso) {
        this.quantidadeRecurso = quantidadeRecurso;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Unidade getOcupante() {
        return ocupante;
    }

    public void setOcupante(Unidade ocupante) {
        this.ocupante = ocupante;
        this.ocupada = ocupante != null; // Atualiza o estado "ocupada" automaticamente
    }
}
