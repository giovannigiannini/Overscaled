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
        root.setStyle("-fx-background-color: #1a1a1a;");

        // Titolo del gioco
        Label titleLabel = new Label("OVERSCALED");
        titleLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 48));
        titleLabel.setTextFill(Color.web("#f1c40f"));

        // Sottotitolo
        Label subtitleLabel = new Label("Inserisci il tuo nome per iniziare");
        subtitleLabel.setFont(Font.font("Arial", 16));
        subtitleLabel.setTextFill(Color.web("#ecf0f1"));

        // Campo di testo per il nome
        TextField nameField = new TextField();
        nameField.setPromptText("Nome Giocatore...");
        nameField.setMaxWidth(250);
        nameField.setStyle("-fx-font-size: 14px; -fx-padding: 8px;");

        // Messaggio di errore se il nome è vuoto
        Label errorLabel = new Label("");
        errorLabel.setTextFill(Color.RED);
        errorLabel.setFont(Font.font("Arial", 12));

        // Pulsante Inizia
        Button startButton = new Button("INIZIA");
        startButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        startButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-padding: 10 25; -fx-cursor: hand;");

        // Azione al click del pulsante
        startButton.setOnAction(e -> {
            String inputName = nameField.getText().trim();
            if (inputName.length() < 2) {
                errorLabel.setText("Inserisci un nome valido per proseguire! (2+ caratteri)");
            } else {
                sceneManager.showWeaponSelectionScreen(inputName);
            }
        });
        // Aggiunta degli elementi al layout
        root.getChildren().addAll(titleLabel, subtitleLabel, nameField, errorLabel, startButton);
    }
    public Parent getRoot() {
        return root;
    }
}
