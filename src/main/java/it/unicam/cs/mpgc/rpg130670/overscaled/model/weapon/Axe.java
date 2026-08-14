package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapon;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.Stats;

public class Axe extends Weapon {
    public Axe() {
        super("Ascia",new Stats(500,150),"+20 danno ogni turno \n+10 vita ogni turno");
    }
    @Override
    public int calculateDamageWin(int victories) {
        return getBaseStats().damage() + victories* 20;
    }
    @Override
    public int calculateHpWin(int victories) {
        return getBaseStats().hp() + victories * 10;
    }
}
