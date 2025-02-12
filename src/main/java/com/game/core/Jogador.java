package com.game.core;

import com.game.core.Recurso;
import com.game.map.MapaDoJogo;
import com.game.map.buildings.Casa;
import com.game.map.buildings.Edificio;
import com.game.units.Unidade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Jogador {
    private Map<Recurso, Integer> recursos;
    private int populacaoCapacidade;
    private int populacaoUsada;
    private List<Unidade> unidades;
    private MapaDoJogo mapa;

    public Jogador(MapaDoJogo mapa) {
        this.recursos = new HashMap<>();
        this.mapa = mapa; // Inicializa o mapa
        inicializarRecursos();
        this.populacaoCapacidade = 5;
        this.populacaoUsada = 0;
        this.unidades = new ArrayList<>();
    }

    private void inicializarRecursos() {
        for (Recurso recurso : Recurso.values()) {
            recursos.put(recurso, 0);
        }
    }

    public void adicionarRecurso(Recurso tipo, int quantidade) {
        recursos.put(tipo, recursos.getOrDefault(tipo,0) + quantidade);
    }

    public boolean gastarRecurso(Recurso tipo, int quantidade) {
        if (recursos.containsKey(tipo) && recursos.get(tipo) >= quantidade) {
            recursos.put(tipo, recursos.get(tipo) - quantidade);
            return true;
        }
        return false;
    }
    public boolean construirEdificio(Edificio edificio, int x, int y) {
        if (mapa == null) {System.out.println("Erro: O mapa não foi inicializado!");return false;}
        if (temRecursosParaConstruir(edificio)) {
            if (mapa.estaDentroDosLimites(x, y) && !mapa.getCelula(x, y).isOcupadaPorEdificio()) {
                for (Map.Entry<Recurso, Integer> requisito : edificio.getCustoConstrucao().entrySet()) {
                    gastarRecurso(requisito.getKey(), requisito.getValue());
                }
                if (edificio instanceof Casa) {((Casa) edificio).setJogador(this);}
                mapa.getCelula(x, y).setEdificio(edificio);
                edificio.construir();
                mapa.getCelula(x, y).setEdificio(edificio); // Define o edifício como ocupante da célula
                return true;
            } else {
                System.out.println("Posição ocupada!");
            }
        } else {
            System.out.println("Recursos Insuficientes!");
        }
        return false;
    }

    private boolean temRecursosParaConstruir(Edificio edificio) {
        for (Map.Entry<Recurso, Integer> requisito : edificio.getCustoConstrucao().entrySet()) {
            if (!recursos.containsKey(requisito.getKey()) || recursos.get(requisito.getKey()) < requisito.getValue()) {
                return false;
            }
        }
        return true;
    }

    public boolean criarUnidade(Unidade unidade) {
        if (populacaoUsada + unidade.getCustoPopulacao() <= populacaoCapacidade) {
            if (gastarRecurso(unidade.getRecursoNecessario(), unidade.getCustoRecurso())) {
                unidades.add(unidade);
                populacaoUsada += unidade.getCustoPopulacao();
                mapa.adicionarUnidadeAoMapa(unidade, unidade.getX(), unidade.getY());
                System.out.println("Unidade criada: " + unidade.getClass().getSimpleName());
                return true;
            }
        } else {
            System.out.println("Capacidade de população insuficiente ou recursos insuficientes.");
        }
        return false;
    }


    // Getters e Setters
    public Map<Recurso, Integer> getRecursos() { return recursos; }
    public int getCapacidadePopulacao() { return populacaoCapacidade; }
    public int getPopulacaoUsada() {return populacaoUsada;}
    public List<Unidade> getUnidades() {return unidades;}
    public void aumentarCapacidadePopulacao(int quantidade) {populacaoCapacidade += quantidade;}
}
