package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity;

public class Stats {
    private final int hp;
    private final int damage;

    public Stats(int hp, int damage) {
        this.hp = hp;
        this.damage = damage;
    }

    public int getHp() { return hp; }
    public int getDamage() { return damage; }
}
