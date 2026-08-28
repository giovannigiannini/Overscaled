package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

public class Wolf extends Enemy {
    public Wolf(int victories) {
        super(EnemyType.WOLF, victories);
    }
    @Override
    public int calculateDamage(int turn) {
        int currentDmg = getAttackStat() + (turn * 25);
        if (turn % 2 == 0) {
            return (int) (currentDmg * 1.5);
        }
        return currentDmg;
    }
}