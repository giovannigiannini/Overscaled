package it.unicam.cs.mpgc.rpg130670.overscaled;

import it.unicam.cs.mpgc.rpg130670.overscaled.controller.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class OverscaledApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.showWelcomeScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}