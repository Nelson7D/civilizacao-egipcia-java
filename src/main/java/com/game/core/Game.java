package com.game.core;

import com.game.map.MapaDoJogo;

public class Game {
    private boolean estaFuncionando;
    private Jogador jogador;
    private MapaDoJogo mapa;

    public void init() {
        jogador = new Jogador();
        mapa = new MapaDoJogo(); // Mapa 50x50
        estaFuncionando = true;
    }

    public void iniciarLoopJogo() {
        while (estaFuncionando) {
            atualizar();
            renderizar();
            // Adicionar delay para controle de FPS
        }
    }

    private void atualizar() {
        // Atualizar lógica do jogo (unidades, IA, combate)
    }

    private void renderizar() {
        // Renderizar mapa e interface (por enquanto, texto no console)
    }
}
