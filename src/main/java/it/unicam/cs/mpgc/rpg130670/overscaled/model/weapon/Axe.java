package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapon;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;

public class Axe extends Weapon {
    public Axe() {
        super("Ascia",new Stats(600,160)," +40 danno ogni vittoria \n+10 vita ogni vittoria", "player_icon_axe.png");
    }
    @Override
    public int calculateDamageWin(int victories) {
        return getBaseStats().damage() + victories* 40;
    }
    @Override
    public int calculateHpWin(int victories) {
        return getBaseStats().hp() + victories * 10;
    }
}
