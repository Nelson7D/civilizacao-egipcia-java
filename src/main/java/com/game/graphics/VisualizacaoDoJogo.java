package com.game.graphics;

import com.game.core.Jogador;
import com.game.map.MapaDoJogo;
import com.game.map.Celula;
import com.game.map.TipoTerreno;
import com.game.units.Trabalhador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class VisualizacaoDoJogo extends Application {
    private MapaDoJogo mapa;
    private Jogador jogador;


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

        new Thread(() -> {
            while (true) {
                renderizarMapa(gc);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void renderizarMapa(GraphicsContext gc) {
        gc.clearRect(0, 0, 800, 600);

        int tileWidth = 16;
        int tileHeight = 16;

        for (int x = 0; x < mapa.getLargura(); x++) {
            for (int y = 0; y < mapa.getAltura(); y++) {
                Celula celula = mapa.getCelula(x, y);
                if (celula != null) {
                    gc.setFill(celula.getColor());
                    gc.fillRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}