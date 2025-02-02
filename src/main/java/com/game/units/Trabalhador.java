package com.game.units;

import com.game.map.Celula;
import com.game.core.Jogador;
import com.game.core.Recurso;
import com.game.map.MapaDoJogo;

public class Trabalhador extends Unidade {
    private static final int VELOCIDADE_COLETA = 10;
    private boolean emMovimento = false;
    private int destinoX;
    private int destinoY;

    // Estado da ação (movendo, coletando, parado)
    private EstadoAcao estadoAtual = EstadoAcao.PARADO;

    public Trabalhador(int x, int y) {
        super(x, y); // Assume que a classe Unidade tem um construtor com x e y
        this.saude = 50;
        this.velocidade = 2;
        this.ataque = 0;
    }

    // Enum para estados de ação
    private enum EstadoAcao {
        MOVENDO, COLETANDO, PARADO
    }

    // Método para definir destino e iniciar movimento
    public void definirDestino(int destinoX, int destinoY, MapaDoJogo mapa) {
        if (!mapa.estaDentroDosLimites(destinoX, destinoY)) {
            System.out.println("Destino fora do mapa!");
            return;
        }

        Celula celulaDestino = mapa.getCelula(destinoX, destinoY);
        if (celulaDestino.isOcupada()) {
            System.out.println("Célula ocupada!");
            return;
        }

        this.destinoX = destinoX;
        this.destinoY = destinoY;
        this.emMovimento = true;
        this.estadoAtual = EstadoAcao.MOVENDO;
    }

    // Atualiza a posição e verifica se chegou ao destino
    public void atualizarPosicao(MapaDoJogo mapa) {
        if (!emMovimento) return;

        int dx = Integer.compare(destinoX, x);
        int dy = Integer.compare(destinoY, y);

        // Verifica se pode se mover para a próxima célula
        int novoX = x + dx;
        int novoY = y + dy;

        if (mapa.estaDentroDosLimites(novoX, novoY) && !mapa.getCelula(novoX, novoY).isOcupada()) {
            // Libera a célula atual e ocupa a nova
            mapa.getCelula(x, y).setOcupada(false);
            x = novoX;
            y = novoY;
            mapa.getCelula(x, y).setOcupada(true);
        }

        // Verifica se chegou ao destino
        if (x == destinoX && y == destinoY) {
            emMovimento = false;
            estadoAtual = EstadoAcao.PARADO;
            realizarAcaoAutomatica(mapa); // Coleta recursos ao chegar
        }
    }

    // Ação automática ao chegar no destino (coleta de recursos)
    private void realizarAcaoAutomatica(MapaDoJogo mapa) {
        Celula celulaAtual = mapa.getCelula(x, y);
        if (celulaAtual.getRecurso() != null) {
            estadoAtual = EstadoAcao.COLETANDO;
            coletarRecursos(celulaAtual, jogador); // Assume que "jogador" está acessível
        }
    }

    // Método de coleta ajustado
    public void coletarRecursos(Celula celula, Jogador jogador) {
        if (celula == null || estadoAtual != EstadoAcao.COLETANDO) return;

        Recurso recurso = celula.getRecurso();
        if (recurso == null) return;

        int quantidadeColetada = Math.min(VELOCIDADE_COLETA, celula.getQuantidadeRecurso());
        jogador.adicionarRecurso(recurso, quantidadeColetada);
        celula.setQuantidadeRecurso(celula.getQuantidadeRecurso() - quantidadeColetada);

        if (celula.getQuantidadeRecurso() <= 0) {
            celula.setRecurso(null);
        }
    }

    // Getters para estados (útil para interface gráfica)
    public boolean estaMovendo() { return emMovimento; }
    public boolean estaColetando() { return estadoAtual == EstadoAcao.COLETANDO; }
}