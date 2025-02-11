package com.game.core;

import com.game.map.Celula;
import com.game.map.MapaDoJogo;
import com.game.units.Unidade;

public class Game {
    private boolean estaFuncionando;
    private Jogador jogador;
    private MapaDoJogo mapa;

    public void init() {
        mapa = new MapaDoJogo(); // Mapa 50x50
        jogador = new Jogador(mapa);
        estaFuncionando = true;
    }

    public void iniciarLoopJogo() {
        while (estaFuncionando) {
            atualizar();
            renderizar();
            try {
                Thread.sleep(100); // Delay para controle de FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void atualizar() {
        // Atualizar lógica do jogo (movimento de unidades, coleta de recursos)
        for (Unidade unidade : jogador.getUnidades()) {
            unidade.performAction();
        }
    }

    private void renderizar() {
            // Renderizar mapa e interface no console (ou gráfico simples)
            System.out.println("Mapa:");
            for (int y = 0; y < mapa.getAltura(); y++) {
                for (int x = 0; x < mapa.getLargura(); x++) {
                    Celula celula = mapa.getCelula(x, y);
                    if (celula == null || celula.getOcupante() == null) {
                        System.out.print(".");
                    } else {
                        System.out.print("W"); // Representa uma unidade ("Worker")
                    }
                }
                System.out.println();
            }
    }

}
