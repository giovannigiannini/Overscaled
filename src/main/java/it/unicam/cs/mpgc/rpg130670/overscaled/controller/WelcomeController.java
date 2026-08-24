package it.unicam.cs.mpgc.rpg130670.overscaled.controller;

import it.unicam.cs.mpgc.rpg130670.overscaled.model.PlayerData;

import java.util.List;

public class WelcomeController {

    private final SceneManager sceneManager;

    public WelcomeController(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Valida il nome ed effettua il passaggio di schermata se valido.
     */
    public boolean startNewGame(String playerName) {
        if (playerName == null || playerName.trim().length() < 2) {
            return false;
        }
        sceneManager.showWeaponSelectionScreen(playerName.trim());
        return true;
    }

    /**
     * Recupera la classifica tramite il SaveManager.
     */
    public List<PlayerData> getTopPlayers() {
        return SaveManager.getTop10Players();
    }
}