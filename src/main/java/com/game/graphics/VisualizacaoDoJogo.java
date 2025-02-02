package com.game.graphics;

import com.game.core.Jogador;
import com.game.map.MapaDoJogo;
import com.game.map.Celula;
import com.game.map.TipoTerreno;
import com.game.units.Trabalhador;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VisualizacaoDoJogo extends Application {
    private static final int TAMANHO_CELULA = 20;
    private MapaDoJogo mapa;
    private Jogador jogador;
    private Trabalhador trabalhadorSelecionado;
    private GridPane grade;
    private Timeline timeline;

    @Override
    public void start(Stage palcoPrincipal) {
        mapa = new MapaDoJogo();
        jogador = new Jogador();
        trabalhadorSelecionado = new Trabalhador(0, 0);

        grade = new GridPane();
        renderizarMapa();

        // Configuração do loop de animação para movimento
        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> atualizar()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // Evento de clique para seleção/movimento
        grade.setOnMouseClicked(e -> {
            int x = (int) (e.getX() / TAMANHO_CELULA);
            int y = (int) (e.getY() / TAMANHO_CELULA);

            if (trabalhadorSelecionado == null) {
                // Selecionar trabalhador se clicar em sua posição
                if (x == trabalhadorSelecionado.getX() && y == trabalhadorSelecionado.getY()) {
                    System.out.println("Trabalhador selecionado!");
                }
            } else {
                // Definir novo destino
                trabalhadorSelecionado.definirDestino(x, y, mapa);
            }
        });

        Scene cena = new Scene(grade, mapa.getLargura() * TAMANHO_CELULA, mapa.getAltura() * TAMANHO_CELULA);
        palcoPrincipal.setTitle("Civilização Egípcia");
        palcoPrincipal.setScene(cena);
        palcoPrincipal.show();
    }

    private void atualizar() {
        if (trabalhadorSelecionado != null) {
            trabalhadorSelecionado.atualizarPosicao(mapa);
            renderizarMapa(); // Atualiza a visualização
        }
    }

    private void renderizarMapa() {
        grade.getChildren().clear(); // Limpa o mapa anterior

        for (int x = 0; x < mapa.getLargura(); x++) {
            for (int y = 0; y < mapa.getAltura(); y++) {
                Celula celula = mapa.getCelula(x, y);
                Rectangle retangulo = new Rectangle(TAMANHO_CELULA, TAMANHO_CELULA);

                // Cor do terreno
                retangulo.setFill(getCorPorTerreno(celula.getTerreno()));

                // Destacar trabalhador
                if (trabalhadorSelecionado != null && x == trabalhadorSelecionado.getX() && y == trabalhadorSelecionado.getY()) {
                    if (trabalhadorSelecionado.estaColetando()) {
                        retangulo.setFill(Color.ORANGE); // Cor durante coleta
                    } else {
                        retangulo.setFill(Color.YELLOW); // Cor normal
                    }
                }

                grade.add(retangulo, x, y);
            }
        }
    }

    private Color getCorPorTerreno(TipoTerreno terreno) {
        return switch (terreno) {
            case TERRA_FERTIL -> Color.GREEN;
            case FLORESTA -> Color.DARKGREEN;
            case PEDREIRA -> Color.GRAY;
            case RIO -> Color.BLUE;
            case DESERTO -> Color.BEIGE;
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}