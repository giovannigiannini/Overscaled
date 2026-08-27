package it.unicam.cs.mpgc.rpg130670.overscaled.controller;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Player;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.*;
import java.util.Random;

/**
 * Controller per la gestione del gioco e dell'esplorazione della mappa.
 * Gestisce i movimenti, la posizione del Player e il controllo degli incontri.
 *
 * @author Giannini Giovanni
 */
public class GameController {
    private static final int ENEMY_PROBABILITY = 20;
    private static final int MAP_BOUND_MIN = 0;
    private static final int MAP_BOUND_MAX = 19;

    private final Player player;
    private final Random random;

    private int playerX = 4;
    private int playerY = 4;

    public GameController(Player player) {
        this.player = player;
        this.random = new Random();
    }

    /**
     * Sposta il player sulla mappa.
     *
     * @param X Spostamento sull'asse X (-1, 0, 1)
     * @param Y Spostamento sull'asse Y (-1, 0, 1)
     * @return L'oggetto Enemy incontrato durante lo spostamento, oppure null se non c'è stato alcun incontro.
     */
    public Enemy movePlayer(int X, int Y) {
        int newX = Math.max(MAP_BOUND_MIN, Math.min(MAP_BOUND_MAX, playerX + X));
        int newY = Math.max(MAP_BOUND_MIN, Math.min(MAP_BOUND_MAX, playerY + Y));

        if (newX != playerX || newY != playerY) {
            this.playerX = newX;
            this.playerY = newY;
            return checkForEncounter();
        }

        return null;
    }
/**
     * Controlla se il player ha incontrato un nemico durante lo spostamento.
     *
     * @return L'oggetto Enemy incontrato, oppure null se non c'è stato alcun incontro.
     */
    private Enemy checkForEncounter() {
        if (random.nextInt(100) < ENEMY_PROBABILITY) {
            return createRandomEnemy(player.getVictories());
        }
        return null;
    }

    private Enemy createRandomEnemy(int victories) {
        int roll = random.nextInt(3);
        return switch (roll) {
            case 0 -> new Gorilla(victories);
            case 1 -> new Wolf(victories);
            default -> new Snake(victories);
        };
    }

    public String getPlayerSpritePath() {
        return player.getWeapon().getIconPath();
    }

    public int getPlayerVictories() {
        return player.getVictories();
    }

    public Player getPlayer() { return player; }
    public int getPlayerX() { return playerX; }
    public int getPlayerY() { return playerY; }
}