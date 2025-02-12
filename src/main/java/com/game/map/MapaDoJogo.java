package com.game.map;

import com.game.core.Recurso;
import com.game.units.Unidade;

import java.util.Random;

public class MapaDoJogo {
    private static final int LARGURA = 50;
    private static final int ALTURA = 50;
    private Celula[][] grade;
    private Random random;

    public MapaDoJogo() {
        this.grade = new Celula[LARGURA][ALTURA];
        this.random = new Random();
        gerarMapaRealista();
    }

    private void gerarMapaRealista() {
        // Inicializa tudo como deserto
        for (int x = 0; x < LARGURA; x++) {
            for (int y = 0; y < ALTURA; y++) {
                grade[x][y] = new Celula(TipoTerreno.DESERTO, null, 0);
            }
        }

        // Adiciona florestas, pedreiras e terras férteis agrupadas
        adicionarRegiaoAgrupada(TipoTerreno.FLORESTA, Recurso.MADEIRA, 5);
        adicionarRegiaoAgrupada(TipoTerreno.PEDREIRA, Recurso.PEDRA, 4);
        adicionarRegiaoAgrupada(TipoTerreno.TERRA_FERTIL, Recurso.COMIDA, 6);

        // Adiciona um rio/lago
        gerarCursoDeAgua();
    }

    private void adicionarRegiaoAgrupada(TipoTerreno terreno, Recurso recurso, int tamanhoMaximo) {
        int quantidadeRegioes = random.nextInt(3) + 1; // 1 a 3 regiões
        for (int i = 0; i < quantidadeRegioes; i++) {
            int xInicial = random.nextInt(LARGURA/2);
            int yInicial = random.nextInt(ALTURA/2);
            expandirRegiao(xInicial, yInicial, terreno, recurso, tamanhoMaximo);
        }
    }

    private void expandirRegiao(int x, int y, TipoTerreno terreno, Recurso recurso, int tamanhoRestante) {
        if (!estaDentroDosLimites(x, y) || tamanhoRestante <= 0 || grade[x][y].getTerreno() != TipoTerreno.DESERTO) {
            return;
        }

        grade[x][y] = new Celula(terreno, recurso, random.nextInt(100) + 50);

        expandirRegiao(x + 1, y, terreno, recurso, tamanhoRestante - 1);
        expandirRegiao(x - 1, y, terreno, recurso, tamanhoRestante - 1);
        expandirRegiao(x, y + 1, terreno, recurso, tamanhoRestante - 1);
        expandirRegiao(x, y - 1, terreno, recurso, tamanhoRestante - 1);
    }

    private void gerarCursoDeAgua() {
        int xInicial = random.nextInt(LARGURA / 2) + LARGURA / 4;
        int yInicial = random.nextInt(ALTURA / 2) + ALTURA / 4;
        int comprimento = random.nextInt(20) + 20;
        criarRio(xInicial, yInicial, comprimento);
    }

    private void criarRio(int x, int y, int comprimento) {
        if (comprimento <= 0 || !estaDentroDosLimites(x, y)) {
            return;
        }

        // Verifica se a célula já tem um rio
        if (grade[x][y].getTerreno() != TipoTerreno.RIO) {
            grade[x][y] = new Celula(TipoTerreno.RIO, Recurso.AGUA, 0);
        }

        // Escolhe uma direção aleatória
        int[] direcoesX = {-1, 0, 1, 0};
        int[] direcoesY = {0, -1, 0, 1};

        int proximaDirecao = random.nextInt(direcoesX.length);
        criarRio(x + direcoesX[proximaDirecao], y + direcoesY[proximaDirecao], comprimento - 1);
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
