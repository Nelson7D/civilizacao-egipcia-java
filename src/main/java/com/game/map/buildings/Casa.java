package com.game.map.buildings;

import com.game.core.Recurso;
import java.util.HashMap;
import java.util.Map;

public class Casa extends Edificio {
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
        // Lógica para aumentar a capacidade populacional do jogador
        if (jogador != null) {
            jogador.aumentarCapacidadePopulacao(3); // Aumenta em +3
        }
    }

    @Override
    public void destruir() {
        System.out.println("Casa destruída!");
    }
}