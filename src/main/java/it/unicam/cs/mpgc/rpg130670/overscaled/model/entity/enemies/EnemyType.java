package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;

/**
 * Questo enum è stato fatto per non dover ripetere delle righe in giro di codice, e per avere un unico posto dove modificare le statistiche dei nemici.
 */
public enum EnemyType {
    GORILLA("Gorilla", new Stats(450, 30), 30, 10, 0.85),
    SNAKE("Serpente", new Stats(300, 20), 10, 5, 0.30),
    WOLF("Lupo", new Stats(380, 25), 15, 7, 0.50);

    private final String name;
    private final int baseHp;
    private final int baseDamage;
    private final int bonusHp;
    private final int bonusDamage;
    private final double healPercentage;

    EnemyType(String name, Stats stats, int bonusHp, int bonusDamage, double healPercentage) {
        this.name = name;
        this.baseHp = stats.hp();
        this.baseDamage = stats.damage();
        this.bonusHp = bonusHp;
        this.bonusDamage = bonusDamage;
        this.healPercentage = healPercentage;
    }

    public String getName() { return name; }
    public int getBaseHp() { return baseHp; }
    public int getBaseDamage() { return baseDamage; }
    public int getBonusHp() { return bonusHp; }
    public int getBonusDamage() { return bonusDamage; }
    public double getHealPercentage() { return healPercentage; }
}
