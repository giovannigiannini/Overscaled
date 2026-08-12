package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Stats;

public class Gorilla extends Enemy{
    public Gorilla() {
        super("Gorilla", new Stats(800,80));
    }

    @Override
    public int calculateDamage(int turn) {
        if (turn % 3 == 0) {
            return getBaseStats().getDamage() * 3;
        }
        return getBaseStats().getDamage();
    }
}
