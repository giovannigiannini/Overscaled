package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;

public class Snake extends Enemy {
    public Snake(int victories) {
        super("Serpente", new Stats(300, 20), victories);
    }
    @Override
    public int calculateDamage(int turn) {
        return getAttackStat() + (turn * 12);
    }
}