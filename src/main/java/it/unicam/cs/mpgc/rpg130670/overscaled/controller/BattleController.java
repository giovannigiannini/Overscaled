package it.unicam.cs.mpgc.rpg130670.overscaled.controller;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Player;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.Enemy;
import it.unicam.cs.mpgc.rpg130670.overscaled.view.GameView;

/**
 * Questa classe gestisce la logica di controllo durante una fase di combattimento a turni
 * tra {@link Player} e {@link Enemy}.
 * Le responsabilità di questa classe sono :
 * 1.Alternare i turni di attacco e quindi calcolare i rispettivi danni subiti
 * 2.Controllare quando {@link Player} o {@link Enemy} hanno 0 HP quindi sono stati sconfitti
 * 3.Transizione di ritorno alla mappa in caso di vittoria del {@link Player}
 *
 * @author Giannini Giovanni
 */
public class BattleController {

    private final Player player;
    private final Enemy enemy;
    private final SceneManager sceneManager;
    private final GameController gameController;
    private int turn = 1;

    public BattleController(Player player, Enemy enemy, SceneManager sceneManager, GameController gameController) {
        this.player = player;
        this.enemy = enemy;
        this.sceneManager = sceneManager;
        this.gameController = gameController;
    }

    public String executeTurn() {
        StringBuilder log = new StringBuilder();
        // Danno del Player
        int playerDamage = player.getAttackStat();
        enemy.takeDamage(playerDamage);
        log.append("Turno ").append(turn).append(": ")
                .append(player.getName()).append(" infligge ").append(playerDamage).append(" danni con ")
                .append(player.getWeapon().getName()).append("!\n");
        if (!enemy.isAlive()) {
            player.onVictory();
            log.append("\nHAI VINTO LO SCONTRO!\n");
            log.append("L'arma scala! Nuovi HP: ").append(player.getMaxHp())
                    .append(" | Nuovo Danno: ").append(player.getAttackStat()).append("\n");
            return log.toString();
        }
        // Danno del Nemico
        int enemyDamage = enemy.calculateDamage(turn);
        player.takeDamage(enemyDamage);
        log.append(enemy.getName()).append(" risponde infliggendo ").append(enemyDamage).append(" danni!\n\n");
        if (!player.isAlive()) {
            log.append("\nHAI PERSO! GAME OVER\n");
            return log.toString();
        }
        turn++;
        return log.toString();
    }

    public void returnToMap() {
        GameView gameView = new GameView(gameController);
        sceneManager.returnToMap(gameView);
    }

    public Player getPlayer() { return player; }
    public Enemy getEnemy() { return enemy; }
    public int getTurn() { return turn; }
}