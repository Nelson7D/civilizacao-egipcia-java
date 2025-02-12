package com.game;

import com.game.core.Jogador;
import com.game.core.Recurso;
import com.game.graphics.VisualizacaoDoJogo;
import com.game.map.MapaDoJogo;
import com.game.map.buildings.CentroDaCidade;
import com.game.units.Trabalhador;

public class Main {
    public static void main(String[] args) {
        MapaDoJogo mapa = new MapaDoJogo();
        Jogador jogador = new Jogador(mapa);

        // Adicionar alguns recursos ao jogador
        jogador.adicionarRecurso(Recurso.MADEIRA, 200);
        jogador.adicionarRecurso(Recurso.PEDRA, 100);

        // Criar uma unidade
        Trabalhador trabalhador = new Trabalhador(5, 5, mapa, jogador);
        jogador.criarUnidade(trabalhador);

        // Construir um edifício
        VisualizacaoDoJogo.launch(args);
        CentroDaCidade centroDaCidade = new CentroDaCidade(10, 10);
        if (jogador.construirEdificio(centroDaCidade, 10, 10)) {
            System.out.println("Centro da Cidade construído com sucesso!");
        }
    }

}