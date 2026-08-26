package it.unicam.cs.mpgc.rpg130670.overscaled.controller;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Player;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.Enemy;

/**
 * Controller per la gestione della logica di combattimento a turni tra Player ed Enemy.
 *
 * @author Giannini Giovanni
 */
public class BattleController {
    private final Player player;
    private final Enemy enemy;
    private int turn = 1;

    public BattleController(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    public String executeTurn() {
        StringBuilder log = new StringBuilder();
        // Turno del Player
        int playerDamage = player.getAttackStat();
        enemy.takeDamage(playerDamage);
        log.append(String.format("Turno %d: %s infligge %d danni con %s!\n",
                turn, player.getName(), playerDamage, player.getWeapon().getName()));
        // Controllo vittoria
        if (!enemy.isAlive()) {
            return playerVictory(log);
        }
        // Turno del Nemico
        int enemyDamage = enemy.calculateDamage(turn);
        player.takeDamage(enemyDamage);
        log.append(enemy.getName()).append(" risponde infliggendo ").append(enemyDamage).append(" danni!\n\n");
        // Controllo sconfitta
        if (!player.isAlive()) {
            log.append("\nHAI PERSO! GAME OVER\n");
            return log.toString();
        }
        turn++;
        return log.toString();
    }

    private String playerVictory(StringBuilder log) {
        int oldMaxHp = player.getMaxHp();
        int oldDamage = player.getAttackStat();

        player.onVictory(enemy);

        int hpGained = player.getMaxHp() - oldMaxHp;
        int damageGained = player.getAttackStat() - oldDamage;

        log.append("\nHAI VINTO LO SCONTRO!\n")
                .append(String.format("Sconfitto %s!\n", enemy.getName()))
                .append(String.format("Bonus ottenuto: +%d HP | +%d Danno\n", hpGained, damageGained))
                .append(String.format("Statistiche attuali -> HP: %d | Danno: %d\n", player.getMaxHp(), player.getAttackStat()));

        return log.toString();
    }
    public Player getPlayer() { return player; }
    public Enemy getEnemy() { return enemy; }
    public int getTurn() { return turn; }
}