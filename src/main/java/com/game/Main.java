package com.game;

import com.game.core.Jogador;
import com.game.map.MapaDoJogo;
import com.game.units.Soldado;
import com.game.units.Trabalhador;

public class Main {
    public static void main(String[] args) {
        MapaDoJogo mapa = new MapaDoJogo();
        Jogador jogador1 = new Jogador(mapa);
        Jogador jogador2 = new Jogador(mapa);

        // Criar unidades
        Soldado soldado1 = new Soldado(0, 5, mapa, jogador1);
        Soldado soldado2 = new Soldado(1, 5, mapa, jogador2);
        Trabalhador trabalhadorInimigo = new Trabalhador(2, 5, mapa, jogador2);

        // Mover o soldado para a mesma célula que o trabalhador inimigo
        soldado1.mover(5, 5);
        soldado2.mover(5, 5);
        trabalhadorInimigo.mover(2, 5);
        // Executar a ação do soldado (deve atacar o trabalhador inimigo)
        soldado1.performAction();
        trabalhadorInimigo.mover(5, 5);
        soldado1.performAction();
    }
}