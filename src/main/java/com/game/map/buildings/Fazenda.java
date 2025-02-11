package com.game.map.buildings;

import com.game.core.Jogador;
import com.game.core.Recurso;
import java.util.HashMap;
import java.util.Map;

public class Fazenda extends Edificio {
    public Fazenda(int x, int y) {
        super(x, y);
        this.saude = 150;
        this.custoConstrucao = new HashMap<>();
        custoConstrucao.put(Recurso.MADEIRA, 70);
        custoConstrucao.put(Recurso.AGUA, 30);
    }

    @Override
    public void construir() {
        System.out.println("Fazenda construída!");
        // Lógica para gerar comida automaticamente
    }

    @Override
    public void destruir() {
        System.out.println("Fazenda destruída!");
    }

    public void produzirComida(Jogador jogador) {
        jogador.adicionarRecurso(Recurso.COMIDA, 10); // Produz 10 comida por turno
    }
}