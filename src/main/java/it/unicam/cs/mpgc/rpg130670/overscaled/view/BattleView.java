package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.controller.BattleController;
import it.unicam.cs.mpgc.rpg130670.overscaled.controller.SceneManager;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.application.Platform;
import java.util.Optional;

public class BattleView {
    private final BattleController controller;
    private final VBox root;

    private final Label playerHpLabel;
    private final Label enemyHpLabel;
    private final TextArea logArea;
    private final Button attackButton;

    public BattleView(BattleController controller) {
        this.controller = controller;

        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #1a1a1a; -fx-padding: 30;");

        Label title = new Label("COMBATTIMENTO!");
        title.setFont(Font.font("Consolas", 28));
        title.setTextFill(Color.RED);

        HBox statsBox = new HBox(50);
        statsBox.setAlignment(Pos.CENTER);

        playerHpLabel = new Label();
        playerHpLabel.setTextFill(Color.LIGHTGREEN);
        playerHpLabel.setFont(Font.font(16));

        enemyHpLabel = new Label();
        enemyHpLabel.setTextFill(Color.ORANGE);
        enemyHpLabel.setFont(Font.font(16));

        statsBox.getChildren().addAll(playerHpLabel, enemyHpLabel);

        logArea = new TextArea("Un " + controller.getEnemy().getName() + " selvatico appare!\n");
        logArea.setEditable(false);
        logArea.setMaxWidth(500);
        logArea.setMaxHeight(200);

        attackButton = new Button();
        attackButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");
        attackButton.setOnAction(e -> handleAttack());

        root.getChildren().addAll(title, statsBox, logArea, attackButton);
        updateUi();
    }

    private void handleAttack() {
        String turnLog = controller.executeTurn();
        logArea.appendText(turnLog);
        if (!controller.getEnemy().isAlive()) {
            attackButton.setText("TORNA ALLA MAPPA");
            attackButton.setOnAction(e -> controller.returnToMap());
        } else if (!controller.getPlayer().isAlive()) {
            updateUi(); // Aggiunta qui perchè altrimenti bug visivo, il player veniva sconfitto prima dell'attacco finale
            showGameOverDialog(controller.getSceneManager());
            return;
        }
        updateUi();
    }

    private void updateUi() {
        // Mostra gli HP correnti rispetto agli HP MASSIMI scalati + il danno di attacco attuale
        playerHpLabel.setText(controller.getPlayer().getName() +
                "\nHP: " + controller.getPlayer().getCurrentHp() + "/" + controller.getPlayer().getMaxHp() +
                "\nDanno: " + controller.getPlayer().getAttackStat());
        // Per il nemico mostra i suoi HP correnti rispetto al suo HP massimo (base)
        enemyHpLabel.setText(controller.getEnemy().getName() +
                "\nHP: " + controller.getEnemy().getCurrentHp() + "/" + controller.getEnemy().getMaxHp());
        if (controller.getEnemy().isAlive() && controller.getPlayer().isAlive()) {
            attackButton.setText("ATTACCA (Turno " + controller.getTurn() + ")");
        }
    }
    public void showGameOverDialog(SceneManager sceneManager) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText("Sei stato sconfitto!");
        alert.setContentText("Cosa vuoi fare adesso?");

        ButtonType btnNewGame = new ButtonType("Nuova Partita", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnExit = new ButtonType("Esci dal Gioco", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(btnNewGame, btnExit);

        // Mostra la finestra di dialogo e attende la scelta dell'utente
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == btnNewGame) {
            sceneManager.showWelcomeScreen();
        } else {
            Platform.exit();
            System.exit(0);
        }
    }
    public Parent getRoot() { return root; }
}