package com.game.map.buildings;

import com.game.core.Recurso;
import java.util.Map;

public abstract class Edificio {
    protected int saude;
    protected int x, y; // Posição no mapa
    protected Map<Recurso, Integer> custoConstrucao; // Recursos necessários para construir

    public Edificio(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void construir(); // Lógica de construção
    public abstract void destruir();  // Lógica de destruição

    // Getters
    public int getSaude() { return saude; }
    public Map<Recurso, Integer> getCustoConstrucao() { return custoConstrucao; }
}
