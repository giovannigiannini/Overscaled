package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapon;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;

public class Sword extends Weapon {
    public Sword() {
        super("Spada", new Stats(800, 130), " +25 danno ogni vittoria \n+40 vita ogni vittoria","player_icon_sword.png");
    }
    @Override
    public int calculateDamageWin(int victories) {
        return getBaseStats().damage() + victories * 25;
    }
    @Override
    public int calculateHpWin(int victories) {
        return getBaseStats().hp() + victories * 40;
    }

}