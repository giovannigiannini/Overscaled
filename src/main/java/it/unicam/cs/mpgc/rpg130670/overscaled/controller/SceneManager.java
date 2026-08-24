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
        WelcomeController welcomeController = new WelcomeController(this);
        WelcomeView welcomeView = new WelcomeView(welcomeController);
        stage.setScene(new Scene(welcomeView.getRoot(), 800, 600));
        stage.setTitle("OVERSCALED - Benvenuto");
        stage.show();
        stage.requestFocus();
    }

    public void showWeaponSelectionScreen(String playerName) {
        WeaponSelectionController controller = new WeaponSelectionController(this, playerName);
        WeaponSelectionView view = new WeaponSelectionView(controller);
        stage.setScene(new Scene(view.getRoot(), 800, 600));
        stage.setTitle("OVERSCALED - Selezione Arma");
        stage.show();
    }

    public void startGame(String playerName, Weapon selectedWeapon) {
        Player player = new Player(playerName, selectedWeapon);
        GameController gameController = new GameController(player, this);
        GameView gameView = new GameView(gameController);

        Scene gameScene = new Scene(gameView.getRoot());
        gameScene.setOnKeyPressed(gameView::handleKeyPress);
        stage.setScene(gameScene);
        stage.setTitle("OVERSCALED - SOPRAVVIVI");
        stage.sizeToScene();
    }

    public void showBattleScreen(Player player, Enemy enemy, GameController gameController) {
        BattleController battleController = new BattleController(player, enemy, this, gameController);
        BattleView battleView = new BattleView(battleController);
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
        gameView.updateStats();
        Scene gameScene = new Scene(gameView.getRoot());
        gameScene.setOnKeyPressed(gameView::handleKeyPress);
        stage.setScene(gameScene);
        stage.setTitle("OVERSCALED - SOPRAVVIVI");
        stage.sizeToScene();
    }
}