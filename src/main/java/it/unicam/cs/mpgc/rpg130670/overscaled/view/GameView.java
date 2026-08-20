package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
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

        root = new VBox();
        root.setStyle(UIStyle.MAIN_CONTAINER);
        root.setAlignment(Pos.CENTER);
        canvas = new Canvas(800, 800);

        BorderPane hudOverlay = new BorderPane();
        hudOverlay.setStyle(
                "-fx-background-color: " + UIStyle.BG_BLACK + ";" +
                        "-fx-border-color: #333333;" +
                        "-fx-border-width: 0 0 2px 0;" +
                        "-fx-padding: 8 18;"
        );
        hudOverlay.setMaxWidth(800);
        hudOverlay.setMaxHeight(45);
        hudOverlay.setMinHeight(45);

        Label controlsLabel = new Label("WASD / FRECCE PER MUOVERTI");
        controlsLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 13));
        controlsLabel.setTextFill(Color.web(UIStyle.WHITE_TEXT));

        statsLabel = new Label();
        statsLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 14));
        statsLabel.setTextFill(Color.web(UIStyle.YELLOW_TITLE));

        hudOverlay.setLeft(controlsLabel);
        hudOverlay.setRight(statsLabel);

        StackPane mapContainer = new StackPane(canvas, hudOverlay);
        StackPane.setAlignment(hudOverlay, Pos.TOP_CENTER);

        root.getChildren().add(mapContainer);

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
        gc.setFill(Color.web("#27ae60"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.web("#1e8449"));
        for (int i = 0; i < 800; i += tileSize) {
            for (int j = 0; j < 800; j += tileSize) {
                gc.strokeRect(i, j, tileSize, tileSize);
            }
        }

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