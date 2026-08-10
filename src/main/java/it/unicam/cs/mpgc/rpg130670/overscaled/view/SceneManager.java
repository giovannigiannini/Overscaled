package it.unicam.cs.mpgc.rpg130670.overscaled.view;

import it.unicam.cs.mpgc.rpg130670.overscaled.view.WelcomeView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private final Stage stage;
    private String playerName;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void showWelcomeScreen() {
        WelcomeView welcomeView = new WelcomeView(this);
        Scene scene = new Scene(welcomeView.getRoot(), 800, 600);
        stage.setScene(scene);
        stage.setTitle("OVERSCALED - Benvenuto");
        stage.show();
    }

    public void showChampionSelectionScreen(String name) {
        this.playerName = name;
        System.out.println("Nome giocatore salvato: " + playerName);

        // TODO: Qui richiamerò la vista della selezione campioni
        // ChampionSelectionView selectionView = new ChampionSelectionView(this);
        // stage.setScene(new Scene(selectionView.getRoot(), 800, 600));
    }

    public String getPlayerName() {
        return playerName;
    }
}
