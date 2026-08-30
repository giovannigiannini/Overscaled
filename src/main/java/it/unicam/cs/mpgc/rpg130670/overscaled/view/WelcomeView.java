package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.controller.SaveManager;
import it.unicam.cs.mpgc.rpg130670.overscaled.controller.SceneManager;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.PlayerData;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

/**
 * Vista di benvenuto e avvio partita.
 * Gestisce direttamente l'input utente e la transizione tramite SceneManager.
 *
 * @author Giannini Giovanni
 */
public class WelcomeView {
    private final BorderPane root;

    public WelcomeView(SceneManager sceneManager) {
        root = new BorderPane();
        root.setStyle(UIStyle.MAIN_CONTAINER);
        root.setPadding(new Insets(20));

        // Top Bar: Pulsanti Guida e Classifica
        Button helpBtn = createOutlineButton("❓ GUIDA", UIStyle.WHITE_TEXT);
        helpBtn.setOnAction(e -> showHelpDialog());

        Button leaderboardBtn = createOutlineButton("🏆 CLASSIFICA", UIStyle.YELLOW_TITLE);
        leaderboardBtn.setOnAction(e -> showLeaderboardDialog());

        HBox topBar = new HBox(12, helpBtn, leaderboardBtn);
        topBar.setAlignment(Pos.TOP_RIGHT);
        root.setTop(topBar);

        // Sezione centrale
        Label titleLabel = new Label("OVERSCALED");
        titleLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 48));
        titleLabel.setTextFill(Color.web(UIStyle.YELLOW_TITLE));

        TextField nameField = new TextField();
        nameField.setPromptText("Nome Giocatore...");
        nameField.setMaxWidth(250);
        nameField.setStyle(UIStyle.TEXT_FIELD);

        Label errorLabel = new Label("");
        errorLabel.setTextFill(Color.web("#E74C3C"));
        errorLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 12));

        Button startButton = new Button("INIZIA PARTITA");
        startButton.setStyle(UIStyle.BUTTON_GREEN);

        Runnable startAction = () -> {
            String inputName = nameField.getText() != null ? nameField.getText().trim() : "";

            if (inputName.length() < 2) {
                errorLabel.setText("Inserisci un nome valido! (2+ caratteri)");
            } else {
                sceneManager.showWeaponSelectionScreen(inputName);
            }
        };

        startButton.setOnAction(e -> startAction.run());
        nameField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) startAction.run();
        });

        VBox centerBox = new VBox(20, titleLabel, nameField, errorLabel, startButton);
        centerBox.setAlignment(Pos.CENTER);
        root.setCenter(centerBox);

        Platform.runLater(nameField::requestFocus);
    }

    private Button createOutlineButton(String text, String colorHex) {
        Button btn = new Button(text);
        btn.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-text-fill: " + colorHex + ";" +
                        "-fx-font-family: 'Consolas';" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14px;" +
                        "-fx-border-color: " + colorHex + ";" +
                        "-fx-border-radius: 4px;" +
                        "-fx-cursor: hand;"
        );
        return btn;
    }

    private void showHelpDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Come Giocare");
        dialog.setResizable(false);

        Label title = new Label("🎮 GUIDA DI GIOCO");
        title.setFont(Font.font("Consolas", FontWeight.BOLD, 20));
        title.setTextFill(Color.web(UIStyle.YELLOW_TITLE));

        String helpText =
                "• COMANDI DI MOVIMENTO:\n" +
                        "  [W / A / S / D] oppure [FRECCE DIREZIONALI] per muoverti sulla mappa.\n\n" +
                        "• SCOPO DEL GIOCO:\n" +
                        "  Esplora la mappa, sconfiggi i nemici nei combattimenti a turni\n" +
                        "  e sopravvivi il più a lungo possibile per scalare la classifica\n" +
                        "  ATTENZIONE più nemici sconfiggerai più diventeranno potenti!\n" +
                        "  I nemici che incontrerai saranno:\n" +
                        "  1. Serpente: nemico debole,\n" +
                        "     dopo 4 turni del combattimento danno si incrementa di 30 per turno,\n" +
                        "     sconfiggendolo fa rigenerare 1/4 della vita mancante e da pochi bonus.\n" +
                        "  2. Lupo: nemico medio,\n" +
                        "     ogni 2 turni il suo danno aumenta del 50%,\n" +
                        "     sconfiggendolo ti fa rigenerare metà vita mancante e da medi bonus.\n" +
                        "  3. Gorilla: nemico forte,\n" +
                        "     ogni 3 turni di combattimento infligge danno con boost,\n" +
                        "     fa rigenerare l'85% della vita mancante se lo sconfiggi e da buoni bonus.";

        Label infoLbl = new Label(helpText);
        infoLbl.setFont(Font.font("Consolas", 13));
        infoLbl.setTextFill(Color.web(UIStyle.WHITE_TEXT));

        VBox infoContainer = new VBox(infoLbl);
        infoContainer.setPadding(new Insets(15));
        infoContainer.setStyle("-fx-border-color: #384E63; -fx-border-width: 1px; -fx-border-radius: 4px; -fx-background-color: #242D35;");

        Button closeBtn = new Button("HO CAPITO");
        closeBtn.setStyle(UIStyle.BUTTON_GREEN);
        closeBtn.setOnAction(e -> dialog.close());

        VBox mainLayout = new VBox(20, title, infoContainer, closeBtn);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #181A1B;");

        Scene scene = new Scene(mainLayout, 640, 480);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void showLeaderboardDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Top 10 Giocatori");
        dialog.setResizable(false);

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #181A1B;");

        Label title = new Label("🏆 CLASSIFICA TOP 10");
        title.setFont(Font.font("Consolas", FontWeight.BOLD, 22));
        title.setTextFill(Color.web(UIStyle.YELLOW_TITLE));

        VBox tableContainer = new VBox();
        tableContainer.setStyle("-fx-border-color: #384E63; -fx-border-width: 1px; -fx-border-radius: 4px;");

        HBox headerRow = createTableRow("#", "GIOCATORE", "ARMA", "VITTORIE", true);
        headerRow.setStyle("-fx-background-color: #242D35; -fx-padding: 8;");
        tableContainer.getChildren().add(headerRow);

        List<PlayerData> topPlayers = SaveManager.getTop10Players();
        if (topPlayers.isEmpty()) {
            Label emptyLbl = new Label("Nessun salvataggio presente.");
            emptyLbl.setFont(Font.font("Consolas", 13));
            emptyLbl.setTextFill(Color.web("#95A5A6"));
            emptyLbl.setPadding(new Insets(15));
            tableContainer.getChildren().add(emptyLbl);
        } else {
            int rank = 1;
            for (PlayerData p : topPlayers) {
                HBox row = createTableRow(
                        String.valueOf(rank++),
                        p.name(),
                        p.weaponName(),
                        String.valueOf(p.victories()),
                        false
                );
                row.setStyle("-fx-border-color: #2C3E50; -fx-border-width: 1px 0 0 0; -fx-padding: 6 8;");
                tableContainer.getChildren().add(row);
            }
        }

        Button closeBtn = new Button("CHIUDI");
        closeBtn.setStyle(UIStyle.BUTTON_GREEN);
        closeBtn.setOnAction(e -> dialog.close());

        layout.getChildren().addAll(title, tableContainer, closeBtn);

        Scene scene = new Scene(layout, 480, 420);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private HBox createTableRow(String rank, String name, String weapon, String wins, boolean isHeader) {
        HBox row = new HBox();
        row.setAlignment(Pos.CENTER_LEFT);

        Label rankLbl = new Label(rank);
        rankLbl.setPrefWidth(35);

        Label nameLbl = new Label(name);
        nameLbl.setPrefWidth(140);

        Label weaponLbl = new Label(weapon);
        weaponLbl.setPrefWidth(140);

        Label winsLbl = new Label(wins);
        winsLbl.setPrefWidth(80);
        winsLbl.setAlignment(Pos.CENTER_RIGHT);

        Font font = Font.font("Consolas", isHeader ? FontWeight.BOLD : FontWeight.NORMAL, 13);
        String textColor = isHeader ? UIStyle.YELLOW_TITLE : UIStyle.WHITE_TEXT;

        for (Label lbl : new Label[]{rankLbl, nameLbl, weaponLbl, winsLbl}) {
            lbl.setFont(font);
            lbl.setTextFill(Color.web(textColor));
        }

        row.getChildren().addAll(rankLbl, nameLbl, weaponLbl, winsLbl);
        return row;
    }

    public Parent getRoot() {
        return root;
    }
}