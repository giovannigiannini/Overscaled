package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.controller.GameController;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameView {
    private final GameController controller;
    private final VBox root;
    private final Canvas canvas;
    private final int tileSize = 40;

    public GameView(GameController controller) {
        this.controller = controller;
        root = new VBox();
        canvas = new Canvas(800, 800);
        root.getChildren().add(canvas);
        drawMap();
    }
    public void drawMap() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Disegna Sfondo Erba
        gc.setFill(Color.web("#27ae60"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // Disegna Griglia
        gc.setStroke(Color.web("#1e8449"));
        for (int i = 0; i < 800; i += tileSize) {
            for (int j = 0; j < 800; j += tileSize) {
                gc.strokeRect(i, j, tileSize, tileSize);
            }
        }
        // Disegna Giocatore
        gc.setFill(Color.web("#f39c12"));
        gc.fillRect(controller.getPlayerX() * tileSize, controller.getPlayerY() * tileSize, tileSize, tileSize);
    }
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case W, UP -> controller.movePlayer(0, -1);
            case S, DOWN -> controller.movePlayer(0, 1);
            case A, LEFT -> controller.movePlayer(-1, 0);
            case D, RIGHT -> controller.movePlayer(1, 0);
        }
        drawMap();
    }
    public Parent getRoot() { return root; }
}