package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;

public class Gorilla extends Enemy {
    public Gorilla(int victories) {
        super("Gorilla", new Stats(450, 30), victories);
    }
    @Override
    public int calculateDamage(int turn) {
        int currentDmg = getAttackStat() + (turn * 30);
        if (turn % 3 == 0) {
            return (int) (currentDmg * 1.7);
        }
        return currentDmg;
    }
}