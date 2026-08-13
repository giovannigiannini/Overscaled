package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity;

public abstract class Character {
    private final String name;
    private int currentHp;
    private int currentDamage;

    public Character(String name, Stats baseStats) {
        this.name = name;
        this.currentHp = baseStats.getHp();
        this.currentDamage = baseStats.getDamage();
    }

    public void takeDamage(int amount) {
        this.currentHp = Math.max(0, this.currentHp - amount);
    }

    public boolean isAlive() {
        return this.currentHp > 0;
    }

    public String getName() { return name; }
    public int getCurrentDamage() { return currentDamage; }
    public int getCurrentHp() { return currentHp; }
    protected void setCurrentHp(int hp) { this.currentHp = hp; }
    protected void setCurrentDamage(int damage) { this.currentDamage = damage; }

}
