package it.unicam.cs.mpgc.rpg130670.overscaled.controller;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Player;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.Enemy;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.weapon.Weapon;
import it.unicam.cs.mpgc.rpg130670.overscaled.view.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe per la gestione della navigazione e transizione tra le diverse schermate.
 *
 * @author Giannini Giovanni
 */
public class SceneManager {
    private final Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void showWelcomeScreen() {
        WelcomeView welcomeView = new WelcomeView(this);
        Scene scene = new Scene(welcomeView.getRoot(), 800, 600);
        stage.setScene(scene);
        stage.setTitle("OVERSCALED - Benvenuto");
        stage.show();
    }

    public void showWeaponSelectionScreen(String playerName) {
        WeaponSelectionView view = new WeaponSelectionView(this, playerName);
        Scene scene = new Scene(view.getRoot(), 900, 650);
        stage.setScene(scene);
        stage.setTitle("OVERSCALED - Selezione Arma");
    }

    public void startGame(String playerName, Weapon selectedWeapon) {
        Player player = new Player(playerName, selectedWeapon);
        GameController gameController = new GameController(player);
        GameView gameView = new GameView(gameController, this);
        Scene gameScene = new Scene(gameView.getRoot());
        gameScene.setOnKeyPressed(gameView::handleKeyPress);
        stage.setScene(gameScene);
        stage.setTitle("OVERSCALED - SOPRAVVIVI");
        stage.sizeToScene();
    }

    public void showBattleScreen(Player player, Enemy enemy, GameController gameController) {
        BattleController battleController = new BattleController(player, enemy);
        BattleView battleView = new BattleView(battleController, this, gameController);
        stage.setScene(new Scene(battleView.getRoot(), 800, 800));
        stage.setTitle("OVERSCALED - Combattimento");
    }
    public void showEndScreen(Player player) {
        EndGameView endGameView = new EndGameView(this, player);
        Scene endScene = new Scene(endGameView.getRoot(), 800, 600);
        stage.setScene(endScene);
        stage.setTitle("OVERSCALED - GAME OVER");
    }
    public void returnToMap(GameView gameView) {
        gameView.render();
        Scene gameScene = new Scene(gameView.getRoot());
        gameScene.setOnKeyPressed(gameView::handleKeyPress);
        stage.setScene(gameScene);
        stage.setTitle("OVERSCALED - SOPRAVVIVI");
        stage.sizeToScene();
    }
}