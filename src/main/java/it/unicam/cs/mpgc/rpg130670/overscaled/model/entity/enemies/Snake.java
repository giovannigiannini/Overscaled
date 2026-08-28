package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

public class Snake extends Enemy {
    public Snake(int victories) {
        super(EnemyType.SNAKE, victories);
    }
    @Override
    public int calculateDamage(int turn) {
        if (turn > 4 ) return getAttackStat() + (turn * 30);
        return getAttackStat() + (turn * 15);
    }
}