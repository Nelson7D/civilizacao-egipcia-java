package com.game.map;


import com.game.core.Recurso;
import com.game.map.buildings.Edificio;
import com.game.units.Unidade;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Celula {
    private TipoTerreno terreno;
    private Recurso recurso; // Representa o recurso disponível nesta célula
    private int quantidadeRecurso; // Quantidade do recurso disponível
    private List<Unidade> unidades;
    private Edificio edificio; // Um edifício por célula
    private final int maxUnidadesPorCelula = 3; // Limite máximo de unidades

    public Celula(TipoTerreno terreno, Recurso recurso, int quantidadeRecurso) {
        this.terreno = terreno;
        this.recurso = recurso;
        this.quantidadeRecurso = quantidadeRecurso;
        this.unidades = new ArrayList<>();
    }

    // Métodos para adicionar/remover unidades
    public boolean adicionarUnidade(Unidade unidade) {
        if (unidades.size() < maxUnidadesPorCelula) {
            unidades.add(unidade);
            return true;
        }
        return false;
    }

    public void removerUnidade(Unidade unidade) {
        unidades.remove(unidade);
    }

    public Color getColor() {
        return switch (terreno) {
            case TERRA_FERTIL -> Color.GREEN;
            case FLORESTA -> Color.DARKGREEN;
            case PEDREIRA -> Color.GRAY;
            case RIO -> Color.BLUE;
            case DESERTO -> Color.rgb(194, 178, 128);
        };
    }

    public Edificio getEdificio() {return edificio;}
    public void setEdificio(Edificio edificio) {
        if (this.edificio == null) {
            this.edificio = edificio;
        } else {
            System.out.println("A célula já contém um edifício!");
        }
    }

    public TipoTerreno getTerreno() {return terreno;}
    public void setTerreno(TipoTerreno terreno) {this.terreno = terreno;}

    public Recurso getRecurso() {return recurso;}
    public void setRecurso(Recurso recurso) {this.recurso = recurso;}

    public int getQuantidadeRecurso() {return quantidadeRecurso;}
    public void setQuantidadeRecurso(int quantidadeRecurso) {this.quantidadeRecurso = quantidadeRecurso;}



    public List<Unidade> getUnidades() {
        return unidades;
    }

    public boolean isOcupadaPorEdificio() {
        return edificio != null;
    }

    public boolean isOcupadaPorUnidades() {
        return !unidades.isEmpty();
    }

    public boolean isCompletamenteOcupada() {
        return isOcupadaPorEdificio() && unidades.size() >= maxUnidadesPorCelula;
    }
}
