package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapons;

public class SpearAndShield extends Weapon {
    public SpearAndShield() {
        super("Scudo & Lancia", 80, 1000, "+10 danno ogni turno \n+50 vita ogni turno");
    }
    @Override
    public int calculateDamageWin(int turn) {
        return getBaseDamage() + (turn-1)*10; //al turno 1 deve incrementare di 0 il danno
    }
    @Override
    public int calculateMaxHpWin(int turn) {
        return getBaseMaxHp() + (turn-1)*50;
    }
}
