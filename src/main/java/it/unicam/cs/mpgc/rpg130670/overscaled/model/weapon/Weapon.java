package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapon;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;
/**
 * Classe astratta weapon che serve da struttura per le 3 armi attualmente disponibili nel gioco
 *
 * @Author Giannini Giovanni
 */
public abstract class Weapon {

    private final String name;
    private final Stats baseStats;
    private final String description;

    public Weapon(String name, Stats baseStats, String description) {
        this.name = name;
        this.baseStats = baseStats;
        this.description = description;
    }

    public abstract int calculateDamageWin(int victories);
    public abstract int calculateHpWin(int victories);

    public String getName() { return name; }
    public Stats getBaseStats() { return baseStats; }
    public String getDescription() { return description; }
    public String getIconPath() {
        return "/images/" + switch (this) {
            case Axe a -> "player_icon_axe.png";
            case SpearAndShield sp -> "player_icon_ss.png";
            case Sword s -> "player_icon_sword.png";
            default -> "player_icon.png";
        };
    }
}
