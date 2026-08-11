package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

public class Snake extends Enemy {
    public Snake() {
        super("Serpente", 200, 40);
    }

    @Override
    public int calculateDamage(int turn) {
        return getBaseDamage() + turn*5;
    }
}
