package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.weapons.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private final Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void showWelcomeScreen() {
        WelcomeView welcomeView = new WelcomeView(this);
        stage.setScene(new Scene(welcomeView.getRoot(), 800, 600));
        stage.setTitle("OVERSCALED - Benvenuto");
        stage.show();
    }

    public void showWeaponSelectionScreen(String playerName) {
        WeaponSelectionView selectionView = new WeaponSelectionView(this, playerName);
        stage.setScene(new Scene(selectionView.getRoot(), 800, 600));
        stage.setTitle("OVERSCALED - Selezione Campione");
    }

    public void startGame(String playerName, Weapon selectedWeapon) {
        System.out.println("Partita Avviata!");
        System.out.println("Giocatore: " + playerName);
        System.out.println("Arma Scelta: " + selectedWeapon.getName()
                + " (DMG: " + selectedWeapon.getBaseDamage()
                + ", HP: " + selectedWeapon.getBaseMaxHp() + ")");

        // Inizializza e mostra la schermata di gioco vera e propria
        GameView gameView = new GameView(this, playerName, selectedWeapon);
        Scene gameScene = new Scene(gameView.getRoot(), 800, 800);

        // Collega i controlli da tastiera per il movimento
        gameScene.setOnKeyPressed(gameView::handleKeyPress);

        stage.setScene(gameScene);
        stage.setTitle("OVERSCALED - Sopravvivi");
    }
}