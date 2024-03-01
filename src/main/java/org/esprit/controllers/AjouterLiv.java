package org.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class AjouterLiv {

    private static final String URL = "jdbc:mysql://localhost:3306/pilatecommandedelivery"; ;
    @FXML
    private TextField adresseTextField;

    @FXML
    private Label adresslabel;

    @FXML
    private DatePicker dateField;

    @FXML
    private Label datelabel;

    @FXML
    private Label detailslabel;

    @FXML
    private Label labelAjouterLiv;

    @FXML
    private AnchorPane liv;

    @FXML
    private Label methodlabel;

    @FXML
    private TextField phoneField;

    @FXML
    private Label phonelabel;

    @FXML
    private Label scriptone;

    @FXML
    private Label scripttwo;

    private ChoosePayment ajouterLivController;

    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Add this method to set the AjouterLivController in AjouterLiv

    public void setAjouterLivController(ChoosePayment choosePaymentController) {
        this.ajouterLivController = choosePaymentController;
    }

    @FXML
    void add(ActionEvent event) {


        String phoneText = phoneField.getText();
        if (!phoneText.matches("\\d{8}|\\d{2} \\d{3} \\d{3}")) {
            showAlert("Invalid Phone Number", "Please enter a valid 8-digit phone number (e.g., 29******) or with spaces (e.g., 29 *** ***).");
            return;
        }

        // List of valid Governorates
        String governorates = "Tunis|Ariana|Ben Arous|Manouba|Nabeul|Zaghouan|Beja|Jendouba|Kef|Siliana|Sousse|Monastir|Mahdia|Sfax|Kairouan|Kasserine|Sidi Bouzid|Gabes|Medenine|Tataouine|Tozeur|Kebili";

        // Validation for adresseTextField
        String adresseText = adresseTextField.getText();
        if (!adresseText.matches("Tunisie,(?i:" + governorates + "),.*")) {
            showAlert("Invalid Address", "Please enter a valid address in the format: Tunisie, Gouvernorat, Nom de la rue, Batiment (optional)");
            return;
        }

        LocalDate selectedDate = dateField.getValue();
        if (selectedDate == null || selectedDate.isEqual(LocalDate.now()) || selectedDate.isBefore(LocalDate.now())) {
            showAlert("Invalid Date", "Please select a date different from today and in the future.");
            return;
        }
        saveToDatabase();

        try {
            // Load ChoosePayment.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChoosePayment.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Access the current stage (the window) from the button
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveToDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pilatecommandedelivery", "root", "")) {
            // Create a prepared statement with the SQL INSERT query
            String sql = "INSERT INTO livraison (methodePay, adresseLiv, dateLiv, phone) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set values for the prepared statement
                // Assuming 'methodePay' is a placeholder for payment method, update it accordingly
                preparedStatement.setString(1, "your_payment_method_here");
                preparedStatement.setString(2, adresseTextField.getText());
                preparedStatement.setDate(3, java.sql.Date.valueOf(dateField.getValue()));
                preparedStatement.setString(4, phoneField.getText());

                // Execute the query
                preparedStatement.executeUpdate();

                // Show a success alert
                showAlert("Success", "Data added to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save data to the database.");
        }
    }

    // Helper method to show an alert
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @FXML
    void ajouterMethodPay(ActionEvent event) {


        try {
            // Load ChoosePayment.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChoosePayment.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Access the current stage (the window) from the button
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void passDelivery(ActionEvent event) {

    }

    public void setMethodPay(String paiementALaLivraison) {
    }

    public void setMethodLabel(String paiementALaLivraison) {
    }
}