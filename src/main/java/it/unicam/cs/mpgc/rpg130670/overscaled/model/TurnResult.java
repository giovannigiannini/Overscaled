package it.unicam.cs.mpgc.rpg130670.overscaled.model;

/**
 * Record per la gestione dei dati di ogni turno di una battaglia, serve come tramite tra BattleController e BattleView
 * @param turn
 * @param playerDamage
 * @param enemyDamage
 * @param enemyKilled
 * @param playerKilled
 * @param hpGained
 * @param damageGained
 */
public record TurnResult(int turn, int playerDamage, int enemyDamage, boolean enemyKilled, boolean playerKilled, int hpGained, int damageGained) {}
