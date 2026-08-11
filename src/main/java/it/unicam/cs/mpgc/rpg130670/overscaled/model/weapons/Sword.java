package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapons;

public class Sword extends Weapon {
    public Sword() {
        super("Spada", 100, 700, "+15 danno ogni turno \n+30 vita ogni turno");
    }
    @Override
    public int calculateDamageWin(int turn) {
        return getBaseDamage() + (turn-1)*15; //al turno 1 deve incrementare di 0 il danno
    }
    @Override
    public int calculateMaxHpWin(int turn) {
        return getBaseMaxHp() + (turn-1)*30;
    }

}