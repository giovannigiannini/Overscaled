package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Stats;

public class Wolf extends Enemy {

    public Wolf(int victories) {
        super("Lupo", new Stats(380, 25), victories);
    }

    @Override
    public int calculateDamage(int turn) {
        int currentDmg = getAttackStat() + (turn * 5);
        if (turn % 2 == 0) {
            return (int) (currentDmg * 1.5);
        }
        return currentDmg;
    }
}