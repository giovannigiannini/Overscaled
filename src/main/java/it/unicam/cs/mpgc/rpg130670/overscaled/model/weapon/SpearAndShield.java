package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapon;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;

public class SpearAndShield extends Weapon {
    public SpearAndShield() {
        super("Scudo & Lancia", new Stats(1000,80), "+10 danno ogni turno \n+50 vita ogni turno");
    }
    @Override
    public int calculateDamageWin(int victories) {
        return getBaseStats().damage() + victories * 10;
    }
    @Override
    public int calculateHpWin(int victories) {
        return getBaseStats().hp() + victories * 50;
    }
}
