package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapons;

public abstract class Weapon {

    private final String name;
    private final int baseDamage;
    private final int baseMaxHp;
    private final String description;

    public Weapon(String name, int baseDamage, int baseMaxHp, String description) {
        // Controllo attualmente inutile ma in caso di sviluppi futuri di una possibile arma a scelta del giocatore potrebbe risultare utile
        if(name==null || name.isEmpty() || baseDamage<=0 || baseMaxHp<=0 || description==null) throw new NullPointerException();
        this.name = name;
        this.baseDamage = baseDamage;
        this.baseMaxHp = baseMaxHp;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getBaseMaxHp() {
        return baseMaxHp;
    }

    public String getDescription() {
        return description;
    }
}
