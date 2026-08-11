package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapons;

public class Axe extends Weapon {
    public Axe() {
        super("Ascia",150,500,"+20 danno ogni turno \n+10 vita ogni turno");
    }
    @Override
    public int calculateDamageWin(int turn) {
        return getBaseDamage() + (turn-1)*20; //al turno 1 deve incrementare di 0 il danno
    }
    @Override
    public int calculateMaxHpWin(int turn) {
        return getBaseMaxHp() + (turn-1)*10;
    }
}
