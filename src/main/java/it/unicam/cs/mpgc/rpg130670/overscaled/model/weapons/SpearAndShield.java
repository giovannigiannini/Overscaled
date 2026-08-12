package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapons;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Stats;

public class SpearAndShield extends Weapon {
    public SpearAndShield() {
        super("Scudo & Lancia", new Stats(1000,80), "+10 danno ogni turno \n+50 vita ogni turno");
    }
    @Override
    public int calculateDamageWin(int turn) {
        return getBaseStats().getDamage() + (turn-1)*10; //al turno 1 deve incrementare di 0 il danno
    }
    @Override
    public int calculateHpWin(int turn) {
        return getBaseStats().getHp() + (turn-1)*50;
    }
}
