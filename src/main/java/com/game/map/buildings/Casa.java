package com.game.map.buildings;

import com.game.core.Jogador;
import com.game.core.Recurso;
import java.util.HashMap;

public class Casa extends Edificio {
    private Jogador jogador;

    public Casa(int x, int y) {
        super(x, y);
        this.saude = 200;
        this.custoConstrucao = new HashMap<>();
        custoConstrucao.put(Recurso.MADEIRA, 50);
        custoConstrucao.put(Recurso.PEDRA, 20);
    }

    @Override
    public void construir() {
        System.out.println("Casa construída!");
        if (jogador != null) {jogador.aumentarCapacidadePopulacao(3);}
    }


    @Override
    public void destruir() {
        System.out.println("Casa destruída!");
        if (jogador != null) {
            jogador.aumentarCapacidadePopulacao(-3); // Diminui a capacidade populacional em -3
        }
    }

    public void setJogador(Jogador jogador) {
    }
}