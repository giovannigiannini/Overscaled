package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

public class Wolf extends Enemy{
    public Wolf() {
        super("Lupo", 400, 40);
    }

    @Override
    public int calculateDamage(int turn) {
        return getBaseDamage() + (turn * 10);
    }
}
