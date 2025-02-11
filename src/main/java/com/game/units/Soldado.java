package com.game.units;

import com.game.core.Jogador;
import com.game.core.Recurso;
import com.game.map.MapaDoJogo;

public class Soldado extends Unidade {
    private MapaDoJogo mapa;
    private Jogador jogador;

    // Estado de movimento
    private boolean emMovimento = false;
    private int destinoX, destinoY;

    // Estado da ação (movendo, coletando, parado)
    private Soldado.EstadoAcao estadoAtual = Soldado.EstadoAcao.PARADO;
    private enum EstadoAcao {
        MOVENDO, LUTANDO, PARADO
    }

    public Soldado(int x, int y, MapaDoJogo mapa, Jogador jogador) {
        super(x, y, 100, 1, 20);
        this.mapa = mapa;
        this.jogador = jogador;
    }

    @Override
    public void mover(int newX, int newY) {

    }

    @Override
    public void performAction() {
        // Lógica específica do soldado (ex.: atacar)
    }

    @Override
    public Recurso getRecursoNecessario() {
        return null;
    }

    @Override
    public int getCustoRecurso() {
        return 0;
    }

    @Override
    public int getCustoPopulacao() {
        return 0;
    }
}