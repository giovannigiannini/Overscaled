package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.weapons.*;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Player;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.Enemy;
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
        // Crea l'istanza del giocatore con il nome e l'arma scelta
        Player player = new Player(playerName, selectedWeapon);

        GameView gameView = new GameView(this, player);
        Scene gameScene = new Scene(gameView.getRoot(), 800, 800);

        // Collega la tastiera per il movimento
        gameScene.setOnKeyPressed(gameView::handleKeyPress);

        stage.setScene(gameScene);
        stage.setTitle("OVERSCALED - SOPRAVVIVI");
    }
    public void showBattleScreen(Player player, Enemy enemy, GameView gameView) {
        BattleView battleView = new BattleView(this, player, enemy, gameView);
        stage.setScene(new Scene(battleView.getRoot(), 800, 800));
        stage.setTitle("OVERSCALED - Combattimento!");
    }

    public void returnToMap(GameView gameView) {
        Scene gameScene = new Scene(gameView.getRoot(), 800, 800);
        gameScene.setOnKeyPressed(gameView::handleKeyPress);
        stage.setScene(gameScene);
        stage.setTitle("OVERSCALED - Mappa della Giungla");
    }
}