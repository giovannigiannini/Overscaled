package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.controller.GameController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.InputStream;

public class GameView {
    private final GameController controller;
    private final VBox root;
    private final Canvas canvas;
    private final Label statsLabel;
    private final int tileSize = 40;
    private Image playerSprite;

    public GameView(GameController controller) {
        this.controller = controller;
        root = new VBox(10);
        root.setStyle("-fx-background-color: #121212;");
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER);
        topBar.setStyle("-fx-background-color: #1e1e1e; -fx-padding: 10; -fx-background-radius: 8;");
        topBar.setMinHeight(45);

        statsLabel = new Label();
        statsLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 20));
        statsLabel.setTextFill(Color.web("#f1c40f"));
        topBar.getChildren().add(statsLabel);

        canvas = new Canvas(800, 800);

        VBox.setVgrow(topBar, Priority.NEVER);
        VBox.setVgrow(canvas, Priority.NEVER);

        root.getChildren().addAll(topBar, canvas);

        loadPlayerSprite();
        updateStats();
        drawMap();
    }

    public void updateStats() {
        int victories = controller.getPlayer().getVictories();
        statsLabel.setText("NEMICI SCONFITTI: " + victories);
    }

    private void loadPlayerSprite() {
        try {
            String path = controller.getPlayer().getWeapon().getIconPath();
            InputStream stream = getClass().getResourceAsStream(path);
            if (stream != null) {
                playerSprite = new Image(stream);
            } else {
                System.err.println("Immagine non trovata al percorso: " + path);
            }
        } catch (Exception e) {
            System.err.println("Errore nel caricamento dell'immagine: " + e.getMessage());
        }
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
        double x = controller.getPlayerX() * tileSize;
        double y = controller.getPlayerY() * tileSize;
        if (playerSprite != null) {
            gc.drawImage(playerSprite, x, y, tileSize, tileSize);
        } else {
            gc.setFill(Color.web("#f39c12"));
            gc.fillRect(x, y, tileSize, tileSize);
        }
    }

    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case W, UP -> controller.movePlayer(0, -1);
            case S, DOWN -> controller.movePlayer(0, 1);
            case A, LEFT -> controller.movePlayer(-1, 0);
            case D, RIGHT -> controller.movePlayer(1, 0);
        }
        updateStats();
        drawMap();
    }
    public Parent getRoot() {
        return root;
    }
}