package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapons;
/**
 * Classe astratta weapon che serve da struttura per le 3 armi attualmente disponibili nel gioco
 *
 * @Author Giannini Giovanni
 */
public abstract class Weapon {

    private final String name;
    private final int baseDamage;
    private final int baseMaxHp;
    private final String description;

    public Weapon(String name, int baseDamage, int baseMaxHp, String description) {
        // Controllo attualmente opzionale perchè ci sono 3 weapon ben definite
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
    // Metodi astratti da implementare obbligatoriamente nelle weapon specifiche
    public abstract int calculateDamageWin(int turn); // Calcolo Damage incrementato dopo un turno vinto
    public abstract int calculateMaxHpWin(int turn);// Calcolo HP incrementato dopo un turno vinto
}
