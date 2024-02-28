package org.esprit.controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GameDialog {

    private final String tunisianWord;

    public GameDialog(String tunisianWord) {
        this.tunisianWord = tunisianWord;
    }

    public Optional<String> showAndWait() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Guess the Tunisian Dialect");
        alert.setHeaderText("Translate the Tunisian word:");

        // Provide multiple-choice options
        List<String> options = Arrays.asList(
                "A type of food",
                "A common name",
                "A traditional dance"
        );

        // Customize the dialog content based on the Tunisian word
        alert.setContentText(tunisianWord + "\n\nChoose the correct translation:");

        // Add options as buttons
        alert.getButtonTypes().setAll(
                new ButtonType(options.get(0)),
                new ButtonType(options.get(1)),
                new ButtonType(options.get(2))
        );

        Optional<ButtonType> result = alert.showAndWait();

        // Return the user's choice
        return result.map(ButtonType::getText);
    }
}
