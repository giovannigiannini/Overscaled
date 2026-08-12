package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Stats;

public class Wolf extends Enemy{
    public Wolf() {
        super("Lupo", new Stats(500,40));
    }

    @Override
    public int calculateDamage(int turn) {
        return getBaseStats().getDamage() + (turn * 10);
    }
}
