package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Stats;

public class Snake extends Enemy {
    public Snake() {
        super("Serpente", new Stats(400,30));
    }

    @Override
    public int calculateDamage(int turn) {
        return getBaseStats().getDamage() + turn*5;
    }
}
