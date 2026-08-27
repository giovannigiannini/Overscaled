package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;

public class Snake extends Enemy {
    public Snake(int victories) {
        super("Serpente", new Stats(300, 20), victories);
    }
    @Override
    public int calculateDamage(int turn) {
        if (turn > 4 ) return getAttackStat() + (turn * 30);
        return getAttackStat() + (turn * 15);
    }
    @Override
    public int getBonusHpWin() { return 10; }
    @Override
    public int getBonusDamageWin() { return 5; }
    @Override
    public double getHealPercentage() { return 0.30; }
}