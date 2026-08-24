package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Character;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;

public abstract class Enemy extends Character {
    public Enemy(String name, Stats baseStats, int victories) {
        // I nemici guadagnano +100 HP e +15 Danno base per ogni vittoria del Player
        super(name, new Stats(baseStats.hp() + (victories * 100), baseStats.damage() + (victories * 15)));
    }
    public abstract int calculateDamage(int turn);
    public abstract int getBonusHpWin();
    public abstract int getBonusDamageWin();
    public abstract double getHealPercentage();
}