package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Character;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Stats;

public abstract class Enemy extends Character {

    public Enemy(String name, Stats baseStats) {
        super(name, baseStats);
    }
    public abstract void calculateDamage(int turn);
}
