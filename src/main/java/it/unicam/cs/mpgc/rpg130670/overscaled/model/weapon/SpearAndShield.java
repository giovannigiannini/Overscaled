package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapon;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;

public class SpearAndShield extends Weapon {
    public SpearAndShield() {
        super("Scudo & Lancia", new Stats(1000,80), " +20 danno ogni vittoria \n+80 vita ogni vittoria", "player_icon_ss.png");
    }
    @Override
    public int calculateDamageWin(int victories) {
        return getBaseStats().damage() + victories * 20;
    }
    @Override
    public int calculateHpWin(int victories) {
        return getBaseStats().hp() + victories * 80;
    }
}
