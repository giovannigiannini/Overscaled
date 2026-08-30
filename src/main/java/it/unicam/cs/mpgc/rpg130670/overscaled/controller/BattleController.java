package it.unicam.cs.mpgc.rpg130670.overscaled.controller;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.TurnResult;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Player;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.Enemy;

/**
 * Controller per la gestione del combattimento tra il Player e un Enemy.
 * Calcola i turni e i danni applicati senza occuparsi della formattazione grafica (SRP).
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

    /**
     * Esegue un turno di combattimento tra Player ed Enemy.
     * @return Oggetto BattleResult contenente lo stato grezzo del turno eseguito.
     */
    public TurnResult executeTurn() {
        int currentTurn = turn;

        // Turno del Player
        int playerDamage = player.getAttackStat();
        enemy.takeDamage(playerDamage);

        // Controllo vittoria Player
        if (!enemy.isAlive()) {
            int oldMaxHp = player.getMaxHp();
            int oldDamage = player.getAttackStat();

            player.onVictory(enemy);

            int hpGained = player.getMaxHp() - oldMaxHp;
            int damageGained = player.getAttackStat() - oldDamage;

            return new TurnResult(currentTurn, playerDamage, 0, true, false, hpGained, damageGained);
        }

        // Turno del Nemico
        int enemyDamage = enemy.calculateDamage(turn);
        player.takeDamage(enemyDamage);

        boolean playerKilled = !player.isAlive();
        TurnResult result = new TurnResult(currentTurn, playerDamage, enemyDamage, false, playerKilled, 0, 0);

        turn++;
        return result;
    }

    public Player getPlayer() { return player; }
    public Enemy getEnemy() { return enemy; }
    public int getTurn() { return turn; }
}