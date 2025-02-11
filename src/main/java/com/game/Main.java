package com.game;

import com.game.core.Jogador;
import com.game.core.Recurso;
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
        Trabalhador trabalhador = new Trabalhador(0, 0, mapa, jogador); // Passar mapa e jogador corretamente
        if (jogador.criarUnidade(trabalhador)) {
            System.out.println("Trabalhador criado com sucesso!");
        }

        // Construir um edifício
        CentroDaCidade centroDaCidade = new CentroDaCidade(0, 0);
        if (jogador.construirEdificio(centroDaCidade,5,5)) {
            System.out.println("Centro da Cidade construído com sucesso!");
        }
    }
}