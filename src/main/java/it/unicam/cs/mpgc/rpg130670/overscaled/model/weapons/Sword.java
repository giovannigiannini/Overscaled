package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapons;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Stats;

public class Sword extends Weapon {
    public Sword() {
        super("Spada", new Stats(700, 100), "+15 danno ogni turno vinto \n+30 vita ogni turno vinto");
    }
    @Override
    public int calculateDamageWin(int victories) {
        return getBaseStats().damage() + Math.max(0, victories - 1) * 15; //al turno 1 deve incrementare di 0 il danno
    }
    @Override
    public int calculateHpWin(int victories) {
        return getBaseStats().hp() + Math.max(0, victories - 1) * 30;
    }

}