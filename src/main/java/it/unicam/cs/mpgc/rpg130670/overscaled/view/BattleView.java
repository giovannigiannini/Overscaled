package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.controller.BattleController;
import it.unicam.cs.mpgc.rpg130670.overscaled.controller.GameController;
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
import javafx.scene.text.FontWeight;

/**
 * Vista per l'interfaccia grafica del combattimento a turni.
 *
 * @author Giannini Giovanni
 */
public class BattleView {
    private final BattleController controller;
    private final SceneManager sceneManager;
    private final GameController gameController;

    private final VBox root;
    private final Label playerHpLabel;
    private final Label enemyHpLabel;
    private final TextArea logArea;
    private final Button actionButton;

    public BattleView(BattleController controller, SceneManager sceneManager, GameController gameController) {
        this.controller = controller;
        this.sceneManager = sceneManager;
        this.gameController = gameController;

        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle(UIStyle.MAIN_CONTAINER + " -fx-padding: 30;");

        Label title = new Label("COMBATTIMENTO!");
        title.setFont(Font.font("Consolas", FontWeight.BOLD, 32));
        title.setTextFill(Color.web("#E74C3C"));

        HBox statsBox = new HBox(60);
        statsBox.setAlignment(Pos.CENTER);

        playerHpLabel = new Label();
        playerHpLabel.setTextFill(Color.web(UIStyle.GREEN_ACCENT));
        playerHpLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 15));

        enemyHpLabel = new Label();
        enemyHpLabel.setTextFill(Color.web(UIStyle.YELLOW_TITLE));
        enemyHpLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 15));

        statsBox.getChildren().addAll(playerHpLabel, enemyHpLabel);

        logArea = new TextArea("Un " + controller.getEnemy().getName() + " selvatico appare!\n");
        logArea.setEditable(false);
        logArea.setWrapText(true);
        logArea.setMaxWidth(640);
        logArea.setMaxHeight(420);

        logArea.setStyle(
                "-fx-control-inner-background: #181A1B;" +
                        "-fx-text-fill: #E8E6E3;" +
                        "-fx-font-family: 'Consolas', monospace;" +
                        "-fx-font-size: 13px;" +
                        "-fx-border-color: #454A4D;" +
                        "-fx-border-width: 1.5px;" +
                        "-fx-border-radius: 4px;" +
                        "-fx-background-radius: 4px;"
        );

        actionButton = new Button();
        actionButton.setFont(Font.font("Consolas", FontWeight.BOLD, 14));
        actionButton.setStyle(
                "-fx-background-color: #E74C3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 10 25;" +
                        "-fx-background-radius: 4px;" +
                        "-fx-cursor: hand;"
        );

        actionButton.setOnAction(e -> handleAttack());

        root.getChildren().addAll(title, statsBox, logArea, actionButton);
        updateUi();
    }

    private void handleAttack() {
        String turnLog = controller.executeTurn();
        logArea.appendText(turnLog);

        logArea.setScrollTop(Double.MAX_VALUE);
        logArea.selectRange(logArea.getLength(), logArea.getLength());

        updateUi();

        // Morte del Player
        if (!controller.getPlayer().isAlive()) {
            sceneManager.showEndScreen(controller.getPlayer());
        }
        // Vittoria del Player
        else if (!controller.getEnemy().isAlive()) {
            actionButton.setText("TORNA ALLA MAPPA");
            actionButton.setStyle(UIStyle.BUTTON_GREEN);
            actionButton.setOnAction(e -> sceneManager.returnToMap(new GameView(gameController, sceneManager)));
        }
    }

    private void updateUi() {
        playerHpLabel.setText(controller.getPlayer().getName() +
                "\nHP: " + controller.getPlayer().getCurrentHp() + "/" + controller.getPlayer().getMaxHp() +
                "\nDanno: " + controller.getPlayer().getAttackStat());

        enemyHpLabel.setText(controller.getEnemy().getName() +
                "\nHP: " + controller.getEnemy().getCurrentHp() + "/" + controller.getEnemy().getMaxHp());

        if (controller.getEnemy().isAlive() && controller.getPlayer().isAlive()) {
            actionButton.setText("ATTACCA (Turno " + controller.getTurn() + ")");
        }
    }

    public Parent getRoot() { return root; }
}