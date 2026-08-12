package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapons;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Stats;

public class Sword extends Weapon {
    public Sword() {
        super("Spada", new Stats(700, 100), "+15 danno ogni turno \n+30 vita ogni turno");
    }
    @Override
    public int calculateDamageWin(int turn) {
        return getBaseStats().getDamage() + (turn-1)*15; //al turno 1 deve incrementare di 0 il danno
    }
    @Override
    public int calculateHpWin(int turn) {
        return getBaseStats().getHp() + (turn-1)*30;
    }

}