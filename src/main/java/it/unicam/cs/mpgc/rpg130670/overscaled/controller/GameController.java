package it.unicam.cs.mpgc.rpg130670.overscaled.controller;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Player;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.*;
import java.util.Random;

/**
 * Controller per la gestione del gioco e dell'esplorazione della mappa.
 * Gestione dei movimenti e posizione del Player, della generazione casuale di Enemy, transizione alla schermata di battaglia.
 *
 * @author Giannini Giovanni
 */
public class GameController {
    private final Player player;
    private final SceneManager sceneManager;
    private int playerX = 4;
    private int playerY = 4;
    private final Random random = new Random();

    public GameController(Player player, SceneManager sceneManager) {
        this.player = player;
        this.sceneManager = sceneManager;
    }

    public void movePlayer(int deltaX, int deltaY) {
        int newX = Math.max(0, Math.min(19, playerX + deltaX));
        int newY = Math.max(0, Math.min(19, playerY + deltaY));

        if (newX != playerX || newY != playerY) {
            this.playerX = newX;
            this.playerY = newY;
            checkForEncounter();
        }
    }

    private void checkForEncounter() {
        if (random.nextInt(100) < 20) { // 20% probabilità
            Enemy enemy = generateRandomEnemy();
            sceneManager.showBattleScreen(player, enemy, this);
        }
    }

    private Enemy generateRandomEnemy() {
        int victories = player.getVictories();
        int roll = random.nextInt(3);
        return switch (roll) {
            case 0 -> new Gorilla(victories);
            case 1 -> new Wolf(victories);
            default -> new Snake(victories); // Ho messo default e non 2 perchè creava problemi
        };
    }

    public Player getPlayer() { return player; }
    public int getPlayerX() { return playerX; }
    public int getPlayerY() { return playerY; }
}