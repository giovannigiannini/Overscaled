package it.unicam.cs.mpgc.rpg130670.overscaled.model.entity;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.weapons.Weapon;

public class Player extends Character {
    private final Weapon weapon;
    private int victories;

    public Player(String name, Weapon weapon) {
        super(name, weapon.getBaseStats());
        this.weapon = weapon;
        this.victories = 0;
    }

    public void onVictory() {
        this.victories++;

        // 1. Calcola i nuovi valori scalati tramite l'arma
        int newMaxHp = weapon.calculateHpWin(this.victories);
        int newDamage = weapon.calculateDamageWin(this.victories);

        // 2. Aggiorna i dati nel Character (Max HP e Danno)
        setMaxHp(newMaxHp);
        setAttackStat(newDamage);

        // 3. Ripristina completamente la vita del giocatore per il prossimo scontro
        setCurrentHp(newMaxHp);
    }

    public Weapon getWeapon() { return weapon; }
    public int getVictories() { return victories; }
}