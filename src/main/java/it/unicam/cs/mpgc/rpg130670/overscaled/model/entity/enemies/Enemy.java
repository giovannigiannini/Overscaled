package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies;

public abstract class Enemy {
    private final String name;
    private final int maxHp;
    private int currentHp;
    private final int baseDamage;

    public Enemy(String name, int maxHp, int baseDamage) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.baseDamage = baseDamage;
    }

    public void takeDamage(int amount) {
        this.currentHp = Math.max(0, this.currentHp - amount);
    }

    public boolean isAlive() {
        return this.currentHp > 0;
    }

    public String getName() { return name; }
    public int getMaxHp() { return maxHp; }
    public int getCurrentHp() { return currentHp; }
    public int getBaseDamage() { return baseDamage; }


    public abstract int calculateDamage(int turn);

}
