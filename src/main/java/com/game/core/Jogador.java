package com.game.core;

import com.game.core.Recurso;
import java.util.HashMap;
import java.util.Map;

public class Jogador {
    private Map<Recurso, Integer> recursos;
    private int populacaoCapacidade;
    private int populacaoUsada;

    public Jogador() {
        recursos = new HashMap<>();
        inicializarRecursos();
        populacaoCapacidade = 5; // Capacidade inicial (aumenta com casas)
        populacaoUsada = 0;
    }

    private void inicializarRecursos() {
        for (Recurso recurso : Recurso.values()) {
            recursos.put(recurso, 100); // Valor inicial para cada recurso
        }
    }

    public void adicionarRecurso(Recurso tipo, int quantidade) {
        recursos.put(tipo, recursos.get(tipo) + quantidade);
    }

    public boolean gastarRecurso(Recurso tipo, int quantidade) {
        if (recursos.get(tipo) >= quantidade) {
            recursos.put(tipo, recursos.get(tipo) - quantidade);
            return true;
        }
        return false;
    }

    // Getters e Setters
    public Map<Recurso, Integer> getRecursos() { return recursos; }
    public int getCapacidadePopulacao() { return populacaoCapacidade; }
}
