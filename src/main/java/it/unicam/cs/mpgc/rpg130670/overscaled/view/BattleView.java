package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.controller.BattleController;
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
        root.setStyle("-fx-background-color: #141414; -fx-padding: 30;");

        Label title = new Label("COMBATTIMENTO!");
        title.setFont(Font.font("Consolas", FontWeight.BOLD, 32));
        title.setTextFill(Color.web("#E74C3C"));

        HBox statsBox = new HBox(60);
        statsBox.setAlignment(Pos.CENTER);

        playerHpLabel = new Label();
        playerHpLabel.setTextFill(Color.web("#2ECC71"));
        playerHpLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 15));

        enemyHpLabel = new Label();
        enemyHpLabel.setTextFill(Color.web("#F1C40F"));
        enemyHpLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 15));

        statsBox.getChildren().addAll(playerHpLabel, enemyHpLabel);

        logArea = new TextArea("Un " + controller.getEnemy().getName() + " selvatico appare!\n");
        logArea.setEditable(false);
        logArea.setWrapText(true);
        logArea.setMaxWidth(520);
        logArea.setMaxHeight(200);

        logArea.setStyle(
                "-fx-control-inner-background: #2B3E50;" +
                        "-fx-text-fill: #ECEFF4;" +
                        "-fx-font-family: 'Consolas', monospace;" +
                        "-fx-font-size: 13px;" +
                        "-fx-border-color: #384E63;" +
                        "-fx-border-width: 1.5px;" +
                        "-fx-border-radius: 4px;" +
                        "-fx-background-radius: 4px;"
        );

        attackButton = new Button();
        attackButton.setFont(Font.font("Consolas", FontWeight.BOLD, 14));
        attackButton.setStyle(
                "-fx-background-color: #E74C3C;" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 10 25;" +
                        "-fx-background-radius: 4px;" +
                        "-fx-cursor: hand;"
        );

        attackButton.setOnAction(e -> handleAttack());

        root.getChildren().addAll(title, statsBox, logArea, attackButton);
        updateUi();
    }

    private void handleAttack() {
        String turnLog = controller.executeTurn();
        logArea.appendText(turnLog);
        updateUi();
        if (!controller.getEnemy().isAlive()) {
            attackButton.setText("TORNA ALLA MAPPA");
            attackButton.setStyle(
                    "-fx-background-color: #2ECC71;" +
                            "-fx-text-fill: white;" +
                            "-fx-padding: 10 25;" +
                            "-fx-background-radius: 4px;" +
                            "-fx-cursor: hand;"
            );
            attackButton.setOnAction(e -> controller.returnToMap());
        } else if (!controller.getPlayer().isAlive()) {
            int victories = controller.getPlayer().getVictories();
            controller.getSceneManager().showEndScreen(victories);
        }
    }

    private void updateUi() {
        playerHpLabel.setText(controller.getPlayer().getName() +
                "\nHP: " + controller.getPlayer().getCurrentHp() + "/" + controller.getPlayer().getMaxHp() +
                "\nDanno: " + controller.getPlayer().getAttackStat());

        enemyHpLabel.setText(controller.getEnemy().getName() +
                "\nHP: " + controller.getEnemy().getCurrentHp() + "/" + controller.getEnemy().getMaxHp());

        if (controller.getEnemy().isAlive() && controller.getPlayer().isAlive()) {
            attackButton.setText("ATTACCA (Turno " + controller.getTurn() + ")");
        }
    }

    public Parent getRoot() { return root; }
}