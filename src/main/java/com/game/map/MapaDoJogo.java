package com.game.map;

import com.game.core.Recurso;
import java.util.Random;

public class MapaDoJogo {
    private static final int LARGURA = 50;
    private static final int ALTURA = 50;
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
                TipoTerreno terreno = gerarTerreno(x, y);
                Recurso recurso = gerarRecurso(terreno);
                int quantidade = (recurso != null) ? random.nextInt(100) + 50 : 0;
                grade[x][y] = new Celula(terreno, recurso, quantidade);
            }
        }
    }

    private TipoTerreno gerarTerreno(int x, int y) {
        double chance = random.nextDouble();
        if (chance < 0.2) return TipoTerreno.FLORESTA;       // 20%
        else if (chance < 0.3) return TipoTerreno.RIO;       // 10%
        else if (chance < 0.5) return TipoTerreno.PEDREIRA;  // 20%
        else if (chance < 0.7) return TipoTerreno.TERRA_FERTIL; // 20%
        else return TipoTerreno.DESERTO;                     // 30%
    }

    private Recurso gerarRecurso(TipoTerreno terreno) {
        return switch (terreno) {
            case FLORESTA -> Recurso.MADEIRA;
            case PEDREIRA -> random.nextBoolean() ? Recurso.PEDRA : Recurso.CASCALHO;
            case RIO -> Recurso.AGUA;
            case TERRA_FERTIL -> Recurso.COMIDA;
            default -> null; // Deserto não tem recursos
        };
    }

    public Celula getCelula(int x, int y) {
        if (x >= 0 && x < LARGURA && y >= 0 && y < ALTURA) {
            return grade[x][y];
        }
        return null;
    }

    // Getters para largura e altura
    public int getLargura() { return LARGURA; }
    public int getAltura() { return ALTURA; }
}
