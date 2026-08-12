package it.unicam.cs.mpgc.rpg130670.overscaled.model.weapons;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.Stats;

public class Axe extends Weapon {
    public Axe() {
        super("Ascia",new Stats(500,150),"+20 danno ogni turno \n+10 vita ogni turno");
    }
    @Override
    public int calculateDamageWin(int turn) {
        return getBaseStats().getDamage() + (turn-1)*20; //al turno 1 deve incrementare di 0 il danno
    }
    @Override
    public int calculateHpWin(int turn) {
        return getBaseStats().getHp() + (turn-1)*10;
    }
}
