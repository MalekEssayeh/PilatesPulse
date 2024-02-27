package org.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.esprit.models.Livraison;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

    public class AjouterLiv {

        @FXML
        private DatePicker date;

        @FXML
        private TextField loc;

        @FXML
        private ComboBox<String> payment;

        @FXML
        private TextField phone;

        @FXML
        void initialize() {
            payment.setItems(FXCollections.observableArrayList("Pay with Cash", "Pay with Credit Card"));

            // Limitez la sélection de la date pour qu'elle soit toujours dans le futur
            date.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.isBefore(LocalDate.now()));
                }
            });
        }

        @FXML
        void pass(ActionEvent event) {
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherLiv.fxml"));
                    Parent root = loader.load();

                    Scene currentScene = ((Node) event.getSource()).getScene();

                    currentScene.setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        private void navigateToAfficherLiv(ActionEvent event) {
            try {
                Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                currentStage.close();

                // Load AfficherLiv.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/esprit/fxml/AfficherLiv.fxml"));
                VBox afficherLivView = loader.load();

                // Create a new stage for the AfficherLiv view
                Stage afficherLivStage = new Stage();
                afficherLivStage.setTitle("Afficher Deliveries");
                afficherLivStage.setScene(new Scene(afficherLivView));

                // Show the AfficherLiv view as a separate stage
                afficherLivStage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Implement phone number validation logic
        // For example, check if the phone number consists of 10 digits
        return phoneNumber.matches("\\d{10}");  // Example: 10 digits
    }

    private boolean isValidDeliveryDate(LocalDate date) {
        // Implement delivery date validation logic
        // For example, check if the date is not null and is in the future
        return date != null && date.isAfter(LocalDate.now());
    }

    private boolean isValidPaymentMethod(String paymentMethod) {
        // Implement payment method validation logic
        // For example, check if a payment method is selected
        return paymentMethod != null && !paymentMethod.trim().isEmpty();
    }

    private boolean isValidAddress(String address) {
        // Implement address validation logic
        // For example, check if the address is not empty
        return !address.trim().isEmpty();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
        private void savePaymentToDatabase(String paymentMethod, String address, LocalDate deliveryDate, int phoneNumber) {
            // Implement your database connection details
            String url = "jdbc:mysql://localhost:3306/your_database";
            String username = "your_username";
            String password = "your_password";

            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                // Create a PreparedStatement to insert data into the database
                String sql = "INSERT INTO deliveries (payment_method, address, delivery_date, phone_number) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, paymentMethod);
                    preparedStatement.setString(2, address);
                    preparedStatement.setDate(3, java.sql.Date.valueOf(deliveryDate));
                    preparedStatement.setInt(4, phoneNumber);

                    // Execute the update
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @FXML
        private void pay(ActionEvent event) {
            String selectedPaymentMethod = payment.getValue();
            String phoneNumberStr = phone.getText();
            String address = loc.getText();

            if (selectedPaymentMethod != null) {
                if ("Pay with Cash".equals(selectedPaymentMethod)) {
                    if (isValidPhoneNumber(phoneNumberStr) && isValidAddress(address)) {
                        int phoneNumber = Integer.parseInt(phoneNumberStr);
                        Livraison newLivraison = new Livraison(selectedPaymentMethod, address, LocalDate.now(), phoneNumber);

                        // Save payment details to the database
                        savePaymentToDatabase(selectedPaymentMethod, address, LocalDate.now(), phoneNumber);

                        showAlert("Payment Successful", "Payment confirmed!");
                    } else {
                        showAlert("Invalid Input", "Please enter a valid phone number and address.");
                    }
                } else if ("Pay with Credit Card".equals(selectedPaymentMethod)) {
                    // Load credit card view for credit card payment
                    loadCreditCardView();
                }
            } else {
                showAlert("Invalid Input", "Please choose a payment method!");
            }
        }

    private void loadCreditCardView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/esprit/controllers/creditCard.fxml"));
            VBox creditCardView = loader.load();

            // Create a new stage for the credit card view
            Stage creditCardStage = new Stage();
            creditCardStage.setTitle("Credit Card Details");
            creditCardStage.setScene(new Scene(creditCardView));

            // Get the controller instance
            CreditCard creditCardController = loader.getController();

            // Show the credit card view as a separate stage
            creditCardStage.showAndWait();

            // Validate credit card details after the stage is closed
            if (creditCardController.isValid()) {
                // TODO: Add logic to handle valid credit card details
                showAlert("Credit Card Payment Confirmation", "Credit card payment confirmed!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}