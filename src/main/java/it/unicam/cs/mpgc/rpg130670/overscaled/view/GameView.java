package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.weapons.*;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameView {

    private final VBox root;
    private final Canvas canvas;
    private final String playerName;
    private final Weapon weapon;

    // Posizione di prova del giocatore sulla griglia
    private int playerX = 4;
    private int playerY = 4;
    private final int tileSize = 40; // Pixel di ciascuna casella

    public GameView(SceneManager sceneManager, String playerName, Weapon weapon) {
        this.playerName = playerName;
        this.weapon = weapon;

        root = new VBox();
        canvas = new Canvas(800, 800);
        root.getChildren().add(canvas);

        drawMap();
    }

    public void drawMap() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Disegna uno sfondo semplice per la mappa (es. erba verde)
        gc.setFill(Color.web("#27ae60"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Disegna una griglia di pixel/caselle
        gc.setStroke(Color.web("#1e8449"));
        for (int i = 0; i < 800; i += tileSize) {
            for (int j = 0; j < 800; j += tileSize) {
                gc.strokeRect(i, j, tileSize, tileSize);
            }
        }

        // Disegna il giocatore come un quadrato colorato (temporaneo giusto per test)
        gc.setFill(Color.web("#f39c12"));
        gc.fillRect(playerX * tileSize, playerY * tileSize, tileSize, tileSize);
    }

    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case W -> playerY = Math.max(0, playerY - 1);
            case S -> playerY = Math.min(19, playerY + 1);
            case A -> playerX = Math.max(0, playerX - 1);
            case D -> playerX = Math.min(19, playerX + 1);
            default -> {}
        }
        drawMap();
    }

    public Parent getRoot() {
        return root;
    }
}
