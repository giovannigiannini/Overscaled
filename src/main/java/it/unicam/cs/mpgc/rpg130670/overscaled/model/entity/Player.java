package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.Enemy;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.Snake;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.entity.enemies.Wolf;
import it.unicam.cs.mpgc.rpg130670.overscaled.model.weapon.Weapon;

public class Player extends Character {
    private final Weapon weapon;
    private int victories;
    private int bonusHpWin = 0;
    private int bonusDamageWin = 0;

    public Player(String name, Weapon weapon) {
        super(name, weapon.getBaseStats());
        this.weapon = weapon;
        this.victories = 0;
    }

    public void onVictory(Enemy enemy) {
        this.victories++;
        // Calcola i nuovi valori scalati tramite l'arma e tramite il nemico sconfitto
        int newMaxHp = weapon.calculateHpWin(this.victories);
        int newDamage = weapon.calculateDamageWin(this.victories);
        if(enemy instanceof Snake){
            bonusHpWin+=15;
            bonusDamageWin+=5;
        } else if (enemy instanceof Wolf) {
            bonusHpWin+=20;
            bonusDamageWin+=10;
        }
        else{ // Gorilla
            bonusHpWin+=50;
            bonusDamageWin+=20;
        }
        setMaxHp(newMaxHp +  bonusHpWin);
        setAttackStat(newDamage +  bonusDamageWin);
        setCurrentHp(newMaxHp + bonusHpWin);
    }

    public Weapon getWeapon() { return weapon; }
    public int getVictories() { return victories; }
}