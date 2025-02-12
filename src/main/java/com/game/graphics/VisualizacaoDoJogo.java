package com.game.graphics;

import com.game.core.Jogador;
import com.game.map.MapaDoJogo;
import com.game.map.Celula;
import com.game.units.Unidade;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class VisualizacaoDoJogo extends Application {
    private MapaDoJogo mapa;
    private Jogador jogador;

/*
* Combate entre Edifícios e Unidades:

    Permita que soldados ataquem edifícios reduzindo sua saúde gradualmente.


IA Simples:

    Adicione lógica básica para que soldados inimigos movam-se em direção ao jogador e ataquem automaticamente.

* */
    @Override
    public void start(Stage palcoPrincipal) {
        mapa = new MapaDoJogo();
        jogador = new Jogador(mapa);

        Pane root = new Pane();
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        root.getChildren().add(canvas);


        Scene scene = new Scene(root, 800, 600);
        palcoPrincipal.setTitle("Civilização Egípcia");
        palcoPrincipal.setScene(scene);
        palcoPrincipal.show();


        // Inicia o AnimationTimer para renderização contínua
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                renderizarMapa(gc);
            }
        }.start();
        // Adiciona evento de clique para interação
        root.setOnMouseClicked(event -> {
            int tileWidth = 16;
            int tileHeight = 16;
            int x = (int) (event.getX() / tileWidth);
            int y = (int) (event.getY() / tileHeight);

            if (mapa.estaDentroDosLimites(x, y)) {
                Celula celulaClicada = mapa.getCelula(x, y);
                if (celulaClicada.getUnidades() instanceof Unidade) {
                    System.out.println("Unidade selecionada!");
                } else {
                    System.out.println("Coordenadas clicadas: (" + x + ", " + y + ")");
                }
            }
        });
    }



    private void renderizarMapa(GraphicsContext gc) {
        int tileWidth = 16;
        int tileHeight = 16;
        gc.clearRect(0, 0, 800, 600);
        for (int x = 0; x < mapa.getLargura(); x++) {
            for (int y = 0; y < mapa.getAltura(); y++) {
                Celula celula = mapa.getCelula(x, y);
                if (celula != null) {
                    gc.setFill(celula.getColor());
                    gc.fillRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);

                    // Renderiza recursos na célula (opcional)
                    if (celula.getRecurso() != null) {
                        gc.setFill(Color.YELLOW); // Cor padrão para recursos
                        gc.fillOval(x * tileWidth + 4, y * tileHeight + 4, 8, 8); // Desenha um círculo pequeno
                    }
                    // Renderiza bordas das células
                    gc.setStroke(Color.BLACK);
                    gc.strokeRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                }
            }
        }

        // Renderiza unidades (trabalhadores)
        for (Unidade unidade : jogador.getUnidades()) {
            int x = unidade.getX();
            int y = unidade.getY();
            if (mapa.estaDentroDosLimites(x, y)) {
                gc.setFill(Color.RED); // Cor para representar trabalhadores
                gc.fillOval(x * tileWidth + 2, y * tileHeight + 2, 2, 2); // Desenha um círculo maior
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}