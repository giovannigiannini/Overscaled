package it.unicam.cs.mpgc.rpg130670.overscaled.controller;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Player;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.*;
import java.util.Random;

/**
 * Controller per la gestione del gioco e dell'esplorazione della mappa.
 * Gestione dei movimenti e posizione del Player, della generazione casuale di Enemy,
 * transizione alla schermata di battaglia.
 *
 * @author Giannini Giovanni
 */
public class GameController {
    private static final int ENEMY_PROBABILITY = 20;
    private static final int MAP_BOUND_MIN = 0;
    private static final int MAP_BOUND_MAX = 19;

    private final Player player;
    private final SceneManager sceneManager;
    private final Random random;

    private int playerX = 4;
    private int playerY = 4;

    public GameController(Player player, SceneManager sceneManager) {
        this.player = player;
        this.sceneManager = sceneManager;
        this.random = new Random();
    }

    public void movePlayer(int deltaX, int deltaY) {
        int newX = Math.max(MAP_BOUND_MIN, Math.min(MAP_BOUND_MAX, playerX + deltaX));
        int newY = Math.max(MAP_BOUND_MIN, Math.min(MAP_BOUND_MAX, playerY + deltaY));

        if (newX != playerX || newY != playerY) {
            this.playerX = newX;
            this.playerY = newY;
            checkForEncounter();
        }
    }

    private void checkForEncounter() {
        if (random.nextInt(100) < ENEMY_PROBABILITY) {
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
            default -> new Snake(victories);
        };
    }

    /**
     * Restituisce il path per il player (metodo creato per ridurre le responsabilità della GameView)
     */
    public String getPlayerSpritePath() {
        return player.getWeapon().getIconPath();
    }

    /**
     * Restituisce il numero di vittorie correnti del player(metodo creato per ridurre le responsabilità della GameView)
     */
    public int getPlayerVictories() {
        return player.getVictories();
    }


    public Player getPlayer() {
        return player;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }
}