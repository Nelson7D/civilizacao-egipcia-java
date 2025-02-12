package com.game.core;

import com.game.map.Celula;
import com.game.map.MapaDoJogo;
import com.game.units.Trabalhador;
import com.game.units.Unidade;

import java.util.Map;

public class Game {
    private boolean estaFuncionando;
    private Jogador jogador;
    private MapaDoJogo mapa;

    public void init() {
        mapa = new MapaDoJogo(); // Mapa 50x50
        jogador = new Jogador(mapa);
        estaFuncionando = true;
        // Adicionar alguns recursos iniciais ao jogador
        jogador.adicionarRecurso(Recurso.MADEIRA, 200);
        jogador.adicionarRecurso(Recurso.PEDRA, 100);

        // Criar unidades iniciais
        criarUnidadesIniciais();
    }
    private void criarUnidadesIniciais() {
        for (int i = 0; i < 3; i++) { // Cria 3 trabalhadores
            Trabalhador trabalhador = new Trabalhador(i, i, mapa, jogador); // Posição inicial (0, 0)
            if (!jogador.criarUnidade(trabalhador)) {
                System.out.println("Não foi possível criar o trabalhador " + i);
            }
        }
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
        // Renderizar mapa e interface (por enquanto, texto no console)
        System.out.println("Recursos do jogador:");
        for (Map.Entry<Recurso, Integer> entry : jogador.getRecursos().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    /*private void renderizar() {
            // Renderizar mapa e interface no console (ou gráfico simples)
            System.out.println("Mapa:");
            for (int y = 0; y < mapa.getAltura(); y++) {
                for (int x = 0; x < mapa.getLargura(); x++) {
                    Celula celula = mapa.getCelula(x, y);
                    if (celula == null || celula.getUnidades() == null) {
                        System.out.print(".");
                    } else {
                        System.out.print("W"); // Representa uma unidade ("Worker")
                    }
                }
                System.out.println();
            }
    }*/

}
