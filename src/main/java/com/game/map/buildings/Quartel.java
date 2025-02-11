package com.game.map.buildings;

import com.game.core.Jogador;
import com.game.core.Recurso;
import java.util.HashMap;
import java.util.Map;

public class Quartel extends Edificio {
    public Quartel(int x, int y) {
        super(x, y);
        this.saude = 300;
        this.custoConstrucao = new HashMap<>();
        custoConstrucao.put(Recurso.MADEIRA, 100);
        custoConstrucao.put(Recurso.PEDRA, 50);
    }

    @Override
    public void construir() {
        System.out.println("Quartel construído!");
        // Lógica adicional pode ser adicionada aqui
    }

    @Override
    public void destruir() {
        System.out.println("Quartel destruído!");
    }

    public boolean treinarSoldado(Jogador jogador) {
        if (jogador.gastarRecurso(Recurso.COMIDA, 50) && jogador.gastarRecurso(Recurso.OURO, 20)) {
            System.out.println("Soldado treinado!");
            return true;
        }
        System.out.println("Recursos insuficientes para treinar soldado.");
        return false;
    }
}