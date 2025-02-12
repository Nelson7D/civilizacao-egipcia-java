package com.game.units;

import com.game.core.Jogador;
import com.game.core.Recurso;
import com.game.map.Celula;
import com.game.map.MapaDoJogo;

import java.util.List;

public class Soldado extends Unidade {
    private MapaDoJogo mapa;
    private Jogador jogador;

    // Estado de movimento
    private boolean emMovimento = false;
    private int destinoX, destinoY;

    // Estado da ação (movendo, lutando, parado)
    private Soldado.EstadoAcao estadoAtual = Soldado.EstadoAcao.PARADO;
    private enum EstadoAcao {
        MOVENDO, LUTANDO, PARADO
    }

    public Soldado(int x, int y, MapaDoJogo mapa, Jogador jogador) {
        super(x, y, 100, 2, 20);
        this.mapa = mapa;
        this.jogador = jogador;
    }

    @Override
    public void performAction() {
        Celula celulaAtual = mapa.getCelula(x, y);
        if (celulaAtual != null) {
            List <Unidade> unidadesNaCelula = celulaAtual.getUnidades();
            for (Unidade unidade : unidadesNaCelula) {
                if (unidade != this && unidade.getJogador() != this.jogador) { // Verifica se é um inimigo
                    while (unidade.getSaude() > 0)atacar(unidade);
                    return;
                }
            }
        }
    }

    public void atacar(Unidade alvo) {
        if (alvo != null) {
            System.out.println(alvo.getClass().getSimpleName() +" atacando!");
            alvo.tomarDano(this.ataque);
        }
    }

    public void tomarDano(int dano) {
        saude -= dano;
        if (saude <= 0) {
            morrer();
        }
    }

    public void morrer() {
        System.out.println("Soldado destruído!");
        if (mapa != null) {
            Celula celulaAtual = mapa.getCelula(x, y);
            if (celulaAtual != null) {
                celulaAtual.removerUnidade(this); // Remove o soldado da lista de unidades na célula
            }
        }
    }

    @Override
    public void mover(int newX, int newY) {
        if (mapa.estaDentroDosLimites(newX, newY) && mapa.podeAdicionarUnidade(newX, newY)) {
            Celula celulaAtual = mapa.getCelula(x, y);
            Celula celulaDestino = mapa.getCelula(newX, newY);

            // Remove a unidade da célula atual
            if (celulaAtual != null) {
                celulaAtual.removerUnidade(this);
            }

            // Adiciona a unidade à célula destino
            if (celulaDestino != null) {
                celulaDestino.adicionarUnidade(this);
            }
            this.x = newX;
            this.y = newY;
        }
    }

    @Override
    public Recurso getRecursoNecessario() {return Recurso.COMIDA;}

    @Override
    public int getCustoRecurso() {return 50;}

    @Override
    public int getCustoPopulacao() {return 2;}

    public Jogador getJogador() {return jogador;}

}