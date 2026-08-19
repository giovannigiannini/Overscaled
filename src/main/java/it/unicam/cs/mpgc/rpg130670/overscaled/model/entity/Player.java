package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.Enemy;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.Gorilla;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.Snake;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.Wolf;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.weapon.Weapon;

public class Player extends Character {
    private final Weapon weapon;
    private int victories;
    private int bonusHpWin = 0;
    private int bonusDamageWin = 0;
    private double healPercentage;

    public Player(String name, Weapon weapon) {
        super(name, weapon.getBaseStats());
        this.weapon = weapon;
        this.victories = 0;
    }

    public int onVictory(Enemy enemy) {
        this.victories++;
        // Bonus Enemy
        applyEnemyBonuses(enemy);
        // Calcola le nuove statistiche massime e la cura
        int newMaxHp = weapon.calculateHpWin(this.victories) + this.bonusHpWin;
        int newDamage = weapon.calculateDamageWin(this.victories) + this.bonusDamageWin;
        int hpHealed = calculateHealAmount(newMaxHp, healPercentage);
        // Aggiorna le stats
        updatePlayerStats(newMaxHp, newDamage, hpHealed);

        return hpHealed;
    }

    private void applyEnemyBonuses(Enemy enemy) {
        if (enemy instanceof Snake) {
            bonusHpWin += 10;
            bonusDamageWin += 5;
            healPercentage = 0.25;
        }
        if (enemy instanceof Wolf) {
            bonusHpWin += 15;
            bonusDamageWin += 7;
            healPercentage = 0.50;
        }
        if(enemy instanceof Gorilla) {
            bonusHpWin += 30;
            bonusDamageWin += 10;
            healPercentage = 0.85;
        }
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