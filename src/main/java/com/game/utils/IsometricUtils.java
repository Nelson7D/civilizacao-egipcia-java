package com.game.utils;

import javafx.geometry.Point2D;

public class IsometricUtils {
    // Tamanho dos tiles em pixels (ajuste conforme seus sprites)
    private static final int TILE_WIDTH = 64;
    private static final int TILE_HEIGHT = 32;

    public static Point2D toScreenCoords(int gridX, int gridY) {
        double screenX = (gridX - gridY) * (TILE_WIDTH / 2);
        double screenY = (gridX + gridY) * (TILE_HEIGHT / 2);
        return new Point2D(screenX, screenY);
    }

    public static Point2D toGridCoords(double screenX, double screenY) {
        double gridX = (screenX / (TILE_WIDTH / 2) + screenY / (TILE_HEIGHT / 2)) / 2;
        double gridY = (screenY / (TILE_HEIGHT / 2) - (screenX / (TILE_WIDTH / 2))) / 2;
        return new Point2D((int) Math.floor(gridX), (int) Math.floor(gridY));
    }
}



