package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

public class Gorilla extends Enemy{
    public Gorilla() {
        super("Gorilla", 800, 80);
    }

    @Override
    public int calculateDamage(int turn) {
        if (turn % 3 == 0) {
            return getBaseDamage() * 3;
        }
        return getBaseDamage();
    }
}
