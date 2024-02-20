package org.example.Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            Parent root = FXMLLoader.load(getClass().getResource("/AfficherEvents.fxml"));

            // Create the Scene
            Scene scene = new Scene(root);

            // Set the Scene to the Stage
            primaryStage.setScene(scene);

            // Set the Stage title
            primaryStage.setTitle("Events list");

            // Show the Stage
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
