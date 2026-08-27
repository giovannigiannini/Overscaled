package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.controller.GameController;
import it.unicam.cs.mpgc.rpg130670.overscaled.controller.SceneManager;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.Enemy;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.InputStream;

/**
 * Questa classe rappresenta la vista principale del gioco, gestendo la visualizzazione della mappa, del giocatore e delle statistiche.
 * Si occupa anche di gestire l'input dell'utente per il movimento del giocatore
 * e la transizione alla schermata di battaglia quando il giocatore incontra un nemico
 *
 * @author Giannini Giovanni
 */
public class GameView {
    private static final int TILE_SIZE = 40;
    private static final int GRID_SIZE = 20;

    private final GameController controller;
    private final SceneManager sceneManager;

    private final VBox root;
    private final Canvas canvas;
    private final Label statsLabel;
    private Image playerSprite;

    public GameView(GameController controller, SceneManager sceneManager) {
        this.controller = controller;
        this.sceneManager = sceneManager;

        this.root = new VBox();
        this.root.setStyle(UIStyle.MAIN_CONTAINER);
        this.root.setAlignment(Pos.CENTER);

        int mapDimension = GRID_SIZE * TILE_SIZE;
        this.canvas = new Canvas(mapDimension, mapDimension);

        BorderPane hudOverlay = createHudOverlay(mapDimension);
        this.statsLabel = (Label) hudOverlay.getRight();

        StackPane mapContainer = new StackPane(canvas, hudOverlay);
        StackPane.setAlignment(hudOverlay, Pos.TOP_CENTER);

        this.root.getChildren().add(mapContainer);

        loadPlayerSprite();
        render();
    }

    /**
     * Crea l'overlay HUD che mostra i controlli e le statistiche del giocatore.
     */
    private BorderPane createHudOverlay(int width) {
        BorderPane hud = new BorderPane();
        hud.setStyle(
                "-fx-background-color: " + UIStyle.BG_BLACK + ";" +
                        "-fx-border-color: #333333;" +
                        "-fx-border-width: 0 0 2px 0;" +
                        "-fx-padding: 8 18;"
        );
        hud.setMaxWidth(width);
        hud.setMaxHeight(45);
        hud.setMinHeight(45);

        Label controlsLabel = new Label("WASD / FRECCE PER MUOVERTI");
        controlsLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 13));
        controlsLabel.setTextFill(Color.web(UIStyle.WHITE_TEXT));

        Label stats = new Label();
        stats.setFont(Font.font("Consolas", FontWeight.BOLD, 14));
        stats.setTextFill(Color.web(UIStyle.YELLOW_TITLE));

        hud.setLeft(controlsLabel);
        hud.setRight(stats);
        return hud;
    }

    private void loadPlayerSprite() {
        String path = controller.getPlayerSpritePath();
        try (InputStream stream = getClass().getResourceAsStream(path)) {
            if (stream != null) {
                this.playerSprite = new Image(stream);
            } else {
                System.err.println("Immagine non trovata al percorso: " + path);
            }
        } catch (Exception e) {
            System.err.println("Errore nel caricamento dell'immagine: " + e.getMessage());
        }
    }

    /**
     * Renderizza la mappa di gioco, il giocatore e le statistiche sul canvas.
     * Viene chiamato ogni volta che il giocatore si muove o quando è necessario aggiornare la visualizzazione.
     */
    public void render() {
        statsLabel.setText("NEMICI SCONFITTI: " + controller.getPlayerVictories());

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.web("#27ae60"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.web("#1e8449"));

        for (int i = 0; i < canvas.getWidth(); i += TILE_SIZE) {
            for (int j = 0; j < canvas.getHeight(); j += TILE_SIZE) {
                gc.strokeRect(i, j, TILE_SIZE, TILE_SIZE);
            }
        }
        double x = controller.getPlayerX() * TILE_SIZE;
        double y = controller.getPlayerY() * TILE_SIZE;

        if (playerSprite != null) {
            gc.drawImage(playerSprite, x, y, TILE_SIZE, TILE_SIZE);
        } else {
            gc.setFill(Color.web("#f39c12"));
            gc.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        }
    }

    /**
     * Gestisce l'input dell'utente per il movimento del giocatore.
     * In base al tasto premuto, calcola la direzione del movimento e aggiorna la posizione del giocatore tramite il GameController.
     * Se il giocatore incontra un nemico, viene mostrata la schermata di battaglia tramite lo SceneManager.
     * @param event (tasto premuto)
     */
    public void handleKeyPress(KeyEvent event) {
        int X = 0;
        int Y = 0;

        switch (event.getCode()) {
            case W, UP    -> Y = -1;
            case S, DOWN  -> Y = 1;
            case A, LEFT  -> X = -1;
            case D, RIGHT -> X = 1;
            default -> { return; }
        }

        Enemy enemy = controller.movePlayer(X, Y);
        render();
        if (enemy != null) {
            sceneManager.showBattleScreen(controller.getPlayer(), enemy, controller);
        }
    }

    public Parent getRoot() {
        return root;
    }
}