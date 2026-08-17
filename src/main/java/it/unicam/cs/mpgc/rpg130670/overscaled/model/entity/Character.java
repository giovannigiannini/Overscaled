package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;

public abstract class Character {
    private final String name;
    private final Stats baseStats;
    private int maxHp;         // Vita massima (scala nel tempo)
    private int currentHp;     // Vita attuale (scende in battaglia)
    private int attackStat;    // Danno d'attacco (scala nel tempo)

    public Character(String name, Stats baseStats) {
        this.name = name;
        this.baseStats = baseStats;
        this.maxHp = baseStats.hp();
        this.currentHp = baseStats.hp();
        this.attackStat = baseStats.damage();
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
    public int getAttackStat() { return attackStat; }

    // SETTERS PROTETTI (Per lo scaling del Player)
    protected void setMaxHp(int maxHp) { this.maxHp = maxHp; }
    protected void setCurrentHp(int hp) { this.currentHp = hp; }
    protected void setAttackStat(int damage) { this.attackStat = damage; }
}