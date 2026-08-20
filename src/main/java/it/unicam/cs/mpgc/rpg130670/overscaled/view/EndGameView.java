package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.controller.SceneManager;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EndGameView {
    private final VBox root;

    public EndGameView(SceneManager sceneManager, int finalVictories) {
        root = new VBox(20);
        root.setStyle(UIStyle.MAIN_CONTAINER + " -fx-padding: 40;");
        root.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("GAME OVER");
        titleLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 48));
        titleLabel.setTextFill(Color.web("#e74c3c"));

        Label subTitleLabel = new Label("Sei stato sconfitto in battaglia...");
        subTitleLabel.setFont(Font.font("Consolas", 20));
        subTitleLabel.setTextFill(Color.web(UIStyle.MUTED_TEXT));

        Label statsLabel = new Label("Nemici sconfitti: " + finalVictories);
        statsLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 22));
        statsLabel.setTextFill(Color.web(UIStyle.YELLOW_TITLE));

        Button btnNewGame = new Button("NUOVA PARTITA");
        styleButton(btnNewGame, UIStyle.GREEN_ACCENT);
        btnNewGame.setOnAction(e -> sceneManager.showWelcomeScreen());

        Button btnExit = new Button("ESCI DAL GIOCO");
        styleButton(btnExit, "#c0392b");
        btnExit.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

        root.getChildren().addAll(titleLabel, subTitleLabel, statsLabel, btnNewGame, btnExit);
    }

    private void styleButton(Button btn, String colorHex) {
        btn.setFont(Font.font("Consolas", FontWeight.BOLD, 18));
        btn.setStyle("-fx-background-color: " + colorHex + "; -fx-text-fill: white; -fx-padding: 10 20; -fx-background-radius: 5; -fx-cursor: hand;");
        btn.setPrefWidth(220);
    }

    public VBox getRoot() {
        return root;
    }
}