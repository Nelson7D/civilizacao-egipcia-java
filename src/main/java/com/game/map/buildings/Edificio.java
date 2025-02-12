package com.game.map.buildings;

import com.game.core.Recurso;
import java.util.Map;

public abstract class Edificio {
    protected int saude;
    protected int x, y; // Posição no mapa
    protected Map<Recurso, Integer> custoConstrucao; // Recursos necessários para construir
    protected Recurso recursoNecessario; // Novo campo para recurso necessário
    protected int custoRecurso; // Novo campo para custo do recurso
    protected int custoPopulacao; // Novo campo para custo populacional

    public Edificio(int x, int y) {
        this.x = x;
        this.y = y;
        this.recursoNecessario = recursoNecessario;
        this.custoRecurso = custoRecurso;
        this.custoPopulacao = custoPopulacao;
    }


    public abstract void construir(); // Lógica de construção
    public abstract void destruir();  // Lógica de destruição
    // Getters
    public int getSaude() { return saude; }
    public Map<Recurso, Integer> getCustoConstrucao() { return custoConstrucao; }
    public Recurso getRecursoNecessario() { return recursoNecessario; }
    public int getCustoRecurso() { return custoRecurso; }
    public int getCustoPopulacao() { return custoPopulacao; }
}
