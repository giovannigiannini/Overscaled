package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.controller.WeaponSelectionController;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.weapon.Weapon;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Objects;

public class WeaponSelectionView {
    private final VBox root;
    private final WeaponSelectionController controller;

    public WeaponSelectionView(WeaponSelectionController controller) {
        this.controller = controller;

        root = new VBox(30);
        root.setAlignment(Pos.CENTER);
        root.setStyle(UIStyle.MAIN_CONTAINER);

        Label titleLabel = new Label("Scegli la tua arma, " + controller.getPlayerName() + "!");
        titleLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.web(UIStyle.YELLOW_TITLE));

        HBox cardsContainer = new HBox(20);
        cardsContainer.setAlignment(Pos.CENTER);

        // Richiesta delle armi al controller (separazione responsabilità)
        for (Weapon weapon : controller.getAvailableWeapons()) {
            VBox card = createWeaponCard(weapon);
            cardsContainer.getChildren().add(card);
        }

        root.getChildren().addAll(titleLabel, cardsContainer);
    }

    private VBox createWeaponCard(Weapon weapon) {
        VBox card = new VBox(15);
        card.setAlignment(Pos.CENTER);
        card.setStyle(UIStyle.CARD_PANEL);

        Label nameLabel = new Label(weapon.getName());
        nameLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 20));
        nameLabel.setTextFill(Color.web(UIStyle.WHITE_TEXT));

        ImageView iconView = new ImageView();
        try {
            String path = weapon.getIconPath();
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            iconView.setImage(image);
            iconView.setFitWidth(80);
            iconView.setFitHeight(80);
            iconView.setPreserveRatio(true);
        } catch (Exception e) {
            System.err.println("Impossibile caricare l'immagine per " + weapon.getName() + ": " + e.getMessage());
        }

        Label statsLabel = new Label("DMG: " + weapon.getBaseStats().damage() + "\nHP: " + weapon.getBaseStats().hp());
        statsLabel.setFont(Font.font("Consolas", FontWeight.BOLD, 14));
        statsLabel.setTextFill(Color.web(UIStyle.GREEN_ACCENT));
        statsLabel.setStyle("-fx-text-alignment: center;");

        Label descLabel = new Label(weapon.getDescription());
        descLabel.setFont(Font.font("Consolas", 11));
        descLabel.setTextFill(Color.web(UIStyle.MUTED_TEXT));
        descLabel.setWrapText(true);
        descLabel.setMaxWidth(180);
        descLabel.setStyle("-fx-text-alignment: center;");

        Button selectButton = new Button("SELEZIONA");
        selectButton.setStyle(UIStyle.BUTTON_GREEN);

        // il click del bottone sarà gestito dal controller
        selectButton.setOnAction(e -> controller.selectWeapon(weapon));

        card.getChildren().addAll(nameLabel, iconView, statsLabel, descLabel, selectButton);
        return card;
    }

    public Parent getRoot() {
        return root;
    }
}