package it.unicam.cs.mpgc.rpg130670.overscaled.controller;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Player;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.Enemy;
import it.unicam.cs.mpgc.rpg130670.overscaled.view.GameView;

/**
 * Questa classe gestisce la logica di controllo durante una fase di combattimento a turni
 * tra Player e Enemy.
 * Le responsabilità di questa classe sono :
 * 1.Alternare i turni di attacco e quindi calcolare i rispettivi danni subiti
 * 2.Controllare quando Player o Enemy hanno 0 HP quindi sono stati sconfitti
 * 3.Transizione di ritorno alla mappa in caso di vittoria del Player
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
        log.append(String.format("Turno %d: %s infligge %d danni con %s!\n",
                turn, player.getName(), playerDamage, player.getWeapon().getName()));
        // Controllo vittoria
        if (!enemy.isAlive()){ return playerVictory(log);}
        // Danno del Nemico
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

    public void returnToMap() {
        GameView gameView = new GameView(gameController);
        sceneManager.returnToMap(gameView);
    }

    /**
     * Metodo per calcolare la vittoria del player e tutto lo scaling
     * @param log
     * @return log
     */

    private String playerVictory(StringBuilder log) {
        // Salva le statistiche precedenti per calcolare il bonus esatto
        int oldMaxHp = player.getMaxHp();
        int oldDamage = player.getAttackStat();
        // Applica lo scaling sul Player
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
    public SceneManager getSceneManager() { return sceneManager; }
}