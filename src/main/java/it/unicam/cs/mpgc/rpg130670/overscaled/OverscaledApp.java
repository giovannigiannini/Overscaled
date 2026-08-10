package it.unicam.cs.mpgc.rpg130670.overscaled;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class OverscaledApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("OVERSCALED");

        Label label = new Label("Benvenuto in OVERSCALED!");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}