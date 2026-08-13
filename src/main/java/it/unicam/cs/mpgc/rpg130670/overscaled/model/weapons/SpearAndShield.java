package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapons;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Stats;

public class SpearAndShield extends Weapon {
    public SpearAndShield() {
        super("Scudo & Lancia", new Stats(1000,80), "+10 danno ogni turno \n+50 vita ogni turno");
    }
    @Override
    public int calculateDamageWin(int victories) {
        return getBaseStats().damage() + Math.max(0, victories - 1) * 10; //alla vittoria 1 deve incrementare di 0 il danno
    }
    @Override
    public int calculateHpWin(int victories) {
        return getBaseStats().hp() + Math.max(0, victories - 1) * 50;
    }
}
