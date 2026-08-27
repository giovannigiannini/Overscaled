package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.*;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.weapon.Weapon;
/**
 * Classe che rappresenta il giocatore nel gioco.
 * Estende la classe Character e gestisce le statistiche del giocatore, le vittorie e i bonus ottenuti.
 */
public class Player extends Character {
    private final Weapon weapon;
    private int victories;
    private int bonusHpWin = 0;
    private int bonusDamageWin = 0;

    public Player(String name, Weapon weapon) {
        super(name, weapon.getBaseStats());
        this.weapon = weapon;
        this.victories = 0;
    }

    /**
     * Gestisce l'evento di vittoria del giocatore.
     * aggiorna le statistiche del giocatore in base al nemico sconfitto e calcola gli hp guariti.
     * @param enemy Il nemico sconfitto.
     * @return Gli hp guariti.
     */
    public int onVictory(Enemy enemy) {
        this.victories++;
        this.bonusHpWin += enemy.getBonusHpWin();
        this.bonusDamageWin += enemy.getBonusDamageWin();
        double healPercentage = enemy.getHealPercentage();

        int newMaxHp = weapon.calculateHpWin(this.victories) + this.bonusHpWin;
        int newDamage = weapon.calculateDamageWin(this.victories) + this.bonusDamageWin;
        int hpHealed = calculateHealAmount(newMaxHp, healPercentage);
        updatePlayerStats(newMaxHp, newDamage, hpHealed);

        return hpHealed;
    }

    private int calculateHealAmount(int newMaxHp, double healPercentage) {
        int missingHp = newMaxHp - getCurrentHp();
        return (int) Math.max(0, missingHp * healPercentage);
    }

    private void updatePlayerStats(int newMaxHp, int newDamage, int hpHealed) {
        setMaxHp(newMaxHp);
        setAttackStat(newDamage);
        setCurrentHp(getCurrentHp() + hpHealed);
    }

    public Weapon getWeapon() { return weapon; }
    public int getVictories() { return victories; }
}