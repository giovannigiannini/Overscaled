package it.unicam.cs.mpgc.rpg130670.overscaled;

import it.unicam.cs.mpgc.rpg130670.overscaled.controller.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * Entry point del programma
 *
 * @author Giannini Giovanni
 */
public class Launcher {
    // Classe interna statica che gestisce il ciclo di vita JavaFX
    public static class OverscaledApp extends Application {
        @Override
        public void start(Stage primaryStage) {
            SceneManager sceneManager = new SceneManager(primaryStage);
            primaryStage.setResizable(false);
            sceneManager.showWelcomeScreen();
        }
    }
    public static void main(String[] args) {
        Application.launch(OverscaledApp.class, args);
    }
}
