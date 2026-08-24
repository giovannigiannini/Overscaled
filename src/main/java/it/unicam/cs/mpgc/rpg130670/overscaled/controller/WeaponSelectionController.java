package it.unicam.cs.mpgc.rpg130670.overscaled.controller;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.weapon.*;
import java.util.List;

public class WeaponSelectionController {

    private final SceneManager sceneManager;
    private final String playerName;

    public WeaponSelectionController(SceneManager sceneManager, String playerName) {
        this.sceneManager = sceneManager;
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    /**
     * Restituisce la lista di armi selezionabili nel gioco.
     */
    public List<Weapon> getAvailableWeapons() {
        return List.of(
                new Sword(),
                new Axe(),
                new SpearAndShield()
        );
    }

    /**
     * Inizia la partita con l'arma scelta.
     */
    public void selectWeapon(Weapon weapon) {
        if (weapon != null) {
            sceneManager.startGame(playerName, weapon);
        }
    }
}
