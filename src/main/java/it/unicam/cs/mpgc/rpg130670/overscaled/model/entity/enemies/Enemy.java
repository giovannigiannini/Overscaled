package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Character;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;

public abstract class Enemy extends Character {
    private static final int HP_SCALE_PER_VICTORY = 100;
    private static final int DMG_SCALE_PER_VICTORY = 15;

    private final int bonusHpWin;
    private final int bonusDamageWin;
    private final double healPercentage;

    public Enemy(EnemyType type, int victories) {
        super(type.getName(), new Stats(
                type.getBaseHp() + (victories * HP_SCALE_PER_VICTORY),
                type.getBaseDamage() + (victories * DMG_SCALE_PER_VICTORY)
        ));
        this.bonusHpWin = type.getBonusHp();
        this.bonusDamageWin = type.getBonusDamage();
        this.healPercentage = type.getHealPercentage();
    }

    public abstract int calculateDamage(int turn);

    public int getBonusHpWin() { return bonusHpWin; }
    public int getBonusDamageWin() { return bonusDamageWin; }
    public double getHealPercentage() { return healPercentage; }
}