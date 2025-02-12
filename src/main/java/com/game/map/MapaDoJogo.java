package com.game.map;

import com.game.core.Recurso;
import com.game.units.Unidade;

import java.util.Random;

public class MapaDoJogo {
    private static final int LARGURA = 150;
    private static final int ALTURA = 150;
    private Celula[][] grade;
    private Random random;

    public MapaDoJogo() {
        this.grade = new Celula[LARGURA][ALTURA];
        this.random = new Random();
        gerarMapaAleatorio();
    }

    private void gerarMapaAleatorio() {
        for (int x = 0; x < LARGURA; x++) {
            for (int y = 0; y < ALTURA; y++) {
               grade[x][y] = new Celula(TipoTerreno.DESERTO, null, 0);
            }
        }

        // Adiciona florestas agrupadas
        gerarRegiaoAgrupada(TipoTerreno.FLORESTA, Recurso.MADEIRA, 5);

        // Adiciona pedreiras agrupadas
        gerarRegiaoAgrupada(TipoTerreno.PEDREIRA, Recurso.PEDRA, 4);

        // Adiciona terra fértil agrupada
        gerarRegiaoAgrupada(TipoTerreno.TERRA_FERTIL, Recurso.COMIDA, 6);

        // Adiciona um rio/lago
        gerarRio();
    }

    private void gerarRegiaoAgrupada(TipoTerreno terreno, Recurso recurso, int tamanhoMaximo) {
        int quantidadeRegioes = random.nextInt(3) + 1; // 1 a 3 regiões
        for (int i = 0; i < quantidadeRegioes; i++) {
            int xInicial = random.nextInt(LARGURA);
            int yInicial = random.nextInt(ALTURA);
            expandirRegiao(xInicial, yInicial, terreno, recurso, tamanhoMaximo);
        }
    }

    private void expandirRegiao(int x, int y, TipoTerreno terreno, Recurso recurso, int tamanhoMaximo) {
        if (!estaDentroDosLimites(x, y) || grade[x][y].getTerreno() != TipoTerreno.DESERTO) {
            return;
        }
        grade[x][y] = new Celula(terreno, recurso, random.nextInt(100) + 50);
        if (random.nextInt(tamanhoMaximo) > 0) {
            expandirRegiao(x + 1, y, terreno, recurso, tamanhoMaximo - 1);
            expandirRegiao(x - 1, y, terreno, recurso, tamanhoMaximo - 1);
            expandirRegiao(x, y + 1, terreno, recurso, tamanhoMaximo - 1);
            expandirRegiao(x, y - 1, terreno, recurso, tamanhoMaximo - 1);
        }
    }

    private void gerarRio() {
        int xInicial = random.nextInt(LARGURA / 2) + LARGURA / 4; // Centralizado
        int yInicial = random.nextInt(ALTURA / 2) + ALTURA / 4;
        gerarCursoDeAgua(xInicial, yInicial, 10); // Comprimento do rio
    }

    private void gerarCursoDeAgua(int x, int y, int comprimento) {
        if (comprimento <= 0 || !estaDentroDosLimites(x, y)) {
            return;
        }
        grade[x][y] = new Celula(TipoTerreno.RIO, Recurso.AGUA, 0);
        int direcaoX = random.nextBoolean() ? 1 : -1;
        int direcaoY = random.nextBoolean() ? 1 : -1;
        gerarCursoDeAgua(x + direcaoX, y + direcaoY, comprimento - 1);
    }

    public Celula getCelula(int x, int y) {
        if (x >= 0 && x < LARGURA && y >= 0 && y < ALTURA) {
            return grade[x][y];
        }
        return null;
    }

    public boolean estaDentroDosLimites(int x, int y) {
        return x >= 0 && x < LARGURA && y >= 0 && y < ALTURA;
    }

    public boolean podeAdicionarUnidade(int x, int y) {
        Celula celula = getCelula(x, y);
        return celula != null && !celula.isCompletamenteOcupada();
    }

    public void adicionarUnidadeAoMapa(Unidade unidade, int x, int y) {
        Celula celula = getCelula(x, y);
        if (celula != null) {
            celula.adicionarUnidade(unidade);
        }
    }

    public void removerUnidadeDoMapa(Unidade unidade, int x, int y) {
        Celula celula = getCelula(x, y);
        if (celula != null) {
            celula.removerUnidade(unidade);
        }
    }

    // Getters para largura e altura
    public int getLargura() { return LARGURA; }
    public int getAltura() { return ALTURA; }
}
