package com.game.map.buildings;

import com.game.core.Recurso;
import java.util.HashMap;
import java.util.Map;

public class CentroDaCidade extends Edificio {
    public CentroDaCidade(int x, int y) {
        super(x, y);
        this.saude = 500;
        this.custoConstrucao = new HashMap<>();
        custoConstrucao.put(Recurso.MADEIRA, 200);
        custoConstrucao.put(Recurso.PEDRA, 100);
    }

    @Override
    public void construir() {
        System.out.println("Centro da Cidade construído!");
        // Lógica para aumentar capacidade populacional (ex: +5)
    }

    @Override
    public void destruir() {
        System.out.println("Centro da Cidade destruído!");
    }
}