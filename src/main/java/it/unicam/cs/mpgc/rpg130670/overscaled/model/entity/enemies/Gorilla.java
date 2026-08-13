package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Stats;

public class Gorilla extends Enemy{
    public Gorilla() {
        super("Gorilla", new Stats(800,80));
    }

    @Override
    public void calculateDamage(int turn) {
        if (turn % 3 == 0) {
            setCurrentDamage(getBaseStats().getDamage() * 3 + turn * 15);
        }
        setCurrentDamage(getBaseStats().getDamage() + turn * 15);
    }
}
