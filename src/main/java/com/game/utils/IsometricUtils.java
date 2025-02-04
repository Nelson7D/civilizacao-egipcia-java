package com.game.units;

import javafx.geometry.Point2D;

public class IsometricUtils {
    public static Point2D toScreenCoords(int gridX, int gridY, int tileWidth, int tileHeight) {
        double screenX = (gridX - gridY) * (tileWidth / 2);
        double screenY = (gridX + gridY) * (tileHeight / 2);
        return new Point2D(screenX, screenY);
    }

    public static Point2D toGridCoords(double screenX, double screenY, int tileWidth, int tileHeight) {
        double gridX = (screenX / (tileWidth / 2) + screenY / (tileHeight / 2)) / 2;
        double gridY = (screenY / (tileHeight / 2) - (screenX / (tileWidth / 2))) / 2;
        return new Point2D((int) gridX, (int) gridY);
    }
}
