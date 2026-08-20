package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.controller.SceneManager;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class WelcomeView {
    private final VBox root;

    public WelcomeView(SceneManager sceneManager) {
        root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle(UIStyle.MAIN_CONTAINER);

        Label titleLabel = new Label("OVERSCALED");
        titleLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 48));
        titleLabel.setTextFill(Color.web(UIStyle.YELLOW_TITLE));

        Label subtitleLabel = new Label("Inserisci il tuo nome per iniziare");
        subtitleLabel.setFont(Font.font("Consolas", FontWeight.NORMAL, 16));
        subtitleLabel.setTextFill(Color.web(UIStyle.WHITE_TEXT));

        TextField nameField = new TextField();
        nameField.setPromptText("Nome Giocatore...");
        nameField.setMaxWidth(250);
        nameField.setStyle(UIStyle.TEXT_FIELD);

        Label errorLabel = new Label("");
        errorLabel.setTextFill(Color.web("#E74C3C"));
        errorLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 12));

        Button startButton = new Button("INIZIA PARTITA");
        startButton.setStyle(UIStyle.BUTTON_GREEN);

        startButton.setOnAction(e -> {
            String inputName = nameField.getText().trim();
            if (inputName.length() < 2) {
                errorLabel.setText("Inserisci un nome valido! (2+ caratteri)");
            } else {
                sceneManager.showWeaponSelectionScreen(inputName);
            }
        });

        root.getChildren().addAll(titleLabel, subtitleLabel, nameField, errorLabel, startButton);
    }

    public Parent getRoot() {
        return root;
    }
}