package com.game.units;

import com.game.map.Celula;
import com.game.core.Jogador;
import com.game.core.Recurso;
import com.game.map.MapaDoJogo;

public class Trabalhador extends Unidade {
    private MapaDoJogo mapa;
    private Jogador jogador;

    // Estado de movimento
    private boolean emMovimento = false;
    private int destinoX, destinoY;

    // Estado da ação (movendo, coletando, parado)
    private EstadoAcao estadoAtual = EstadoAcao.PARADO;
    private enum EstadoAcao {
        MOVENDO, COLETANDO, PARADO
    }

    // Constante para coleta de recursos
    private static final int VELOCIDADE_COLETA = 10;

    public Trabalhador(int x, int y, MapaDoJogo mapa, Jogador jogador) {
        super(x, y,50,2,0);
        this.mapa = mapa;
        this.jogador = jogador;
    }

    @Override
    public void performAction() {
        // Exemplo: Coletar recursos automaticamente se estiver em célula válida
        Celula celulaAtual = mapa.getCelula(x, y);
        if (celulaAtual != null && celulaAtual.getRecurso() != null) {
            coletarRecursos(celulaAtual, jogador);
        }
    }

    @Override
    public void mover(int newX, int newY) {
        // Implementação realista (ex: verificar colisões)
        if (mapa.estaDentroDosLimites(newX, newY) && !mapa.getCelula(newX, newY).isOcupada()) {
            mapa.getCelula(x, y).setOcupada(false);
            this.x = newX;
            this.y = newY;
            mapa.getCelula(x, y).setOcupada(true);
        }
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

        // Movimento em eixos separados (horizontal primeiro, depois vertical)
        if (x != destinoX) {
            int dx = Integer.compare(destinoX, x);
            int novoX = x + dx;
            if (mapa.estaDentroDosLimites(novoX, y) && !mapa.getCelula(novoX, y).isOcupada()) {
                mapa.getCelula(x, y).setOcupada(false);
                x = novoX;
                mapa.getCelula(x, y).setOcupada(true);
            }
        } else if (y != destinoY) {
            int dy = Integer.compare(destinoY, y);
            int novoY = y + dy;
            if (mapa.estaDentroDosLimites(x, novoY) && !mapa.getCelula(x, novoY).isOcupada()) {
                mapa.getCelula(x, y).setOcupada(false);
                y = novoY;
                mapa.getCelula(x, y).setOcupada(true);
            }
        }

        // Verifica se chegou ao destino
        if (x == destinoX && y == destinoY) {
            emMovimento = false;
            realizarAcaoAutomatica(mapa);
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

    @Override
    public Recurso getRecursoNecessario() {
        return Recurso.COMIDA;
    }

    @Override
    public int getCustoRecurso() {
        return 50; // Custo de criação do trabalhador
    }

    @Override
    public int getCustoPopulacao() {
        return 1; // Custo populacional do trabalhador
    }
}