package org.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.time.LocalDate;

public class AfficherLiv {

    @FXML
    private Label locationLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label dateLabel;

    private Stage primaryStage;

    private String location;
    private String phone;
    private LocalDate deliveryDate;

    public void setDeliveryDetails(String location, String phone, LocalDate deliveryDate) {
        this.location = location;
        this.phone = phone;
        this.deliveryDate = deliveryDate;

        updateLabels();
    }

    private void updateLabels() {
        locationLabel.setText(location);
        phoneLabel.setText(phone);
        dateLabel.setText(deliveryDate.toString());
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void closeWindow() {
        primaryStage.close();
    }
}
