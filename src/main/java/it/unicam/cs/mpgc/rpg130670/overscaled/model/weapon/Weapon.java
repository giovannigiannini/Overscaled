package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapon;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;

/**
 * Classe astratta Weapon che serve da struttura per le armi disponibili nel gioco.
 *
 * @author Giannini Giovanni
 */
public abstract class Weapon {

    private final String name;
    private final Stats baseStats;
    private final String description;
    private final String iconFileName;

    public Weapon(String name, Stats baseStats, String description, String iconFileName) {
        this.name = name;
        this.baseStats = baseStats;
        this.description = description;
        this.iconFileName = iconFileName;
    }

    public abstract int calculateDamageWin(int victories);
    public abstract int calculateHpWin(int victories);

    public String getName() { return name; }
    public Stats getBaseStats() { return baseStats; }
    public String getDescription() { return description; }

    public String getIconPath() {
        return "/images/" + iconFileName;
    }
}