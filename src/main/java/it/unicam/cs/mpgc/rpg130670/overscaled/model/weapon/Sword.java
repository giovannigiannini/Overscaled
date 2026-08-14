package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapon;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;

public class Sword extends Weapon {
    public Sword() {
        super("Spada", new Stats(700, 100), "+15 danno ogni turno vinto \n+30 vita ogni turno vinto");
    }
    @Override
    public int calculateDamageWin(int victories) {
        return getBaseStats().damage() + victories * 15;
    }
    @Override
    public int calculateHpWin(int victories) {
        return getBaseStats().hp() + victories * 30;
    }

}