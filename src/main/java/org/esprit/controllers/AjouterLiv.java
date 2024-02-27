package org.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.net.URL;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.esprit.models.Livraison;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AjouterLiv {

    @FXML
    private DatePicker date;

    @FXML
    private TextField loc;

    @FXML
    private ComboBox<String> payment;

    @FXML
    private TextField phone;

    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        String phoneNumber = phone.getText();
        String address = loc.getText();
        LocalDate deliveryDate = date.getValue();
        String selectedPaymentMethod = payment.getValue();

        if (isValidPhoneNumber(phoneNumber) && isValidAddress(address) && isValidDeliveryDate(deliveryDate) && isValidPaymentMethod(selectedPaymentMethod)) {
            if ("Pay with Credit Card".equals(selectedPaymentMethod)) {
                // If "Pay with Credit Card" is selected, load credit card view
                loadCreditCardView();
            } else {
                // Otherwise, perform the necessary action (e.g., save delivery details)
                showAlert("Delivery Confirmation", "Delivery confirmed!");
            }
        } else {
            showAlert("Invalid Input", "Please enter valid information for all fields.");
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
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void pay(ActionEvent event) {
        String selectedPaymentMethod = payment.getValue();
        String phoneNumberStr = phone.getText();
        String address = loc.getText();

        if (selectedPaymentMethod != null) {
            if ("Pay with Cash".equals(selectedPaymentMethod)) {
                // Assuming Livraison class expects an integer for phone number
                if (isValidPhoneNumber(phoneNumberStr) && isValidAddress(address)) {
                    int phoneNumber = Integer.parseInt(phoneNumberStr);
                    Livraison newLivraison = new Livraison(selectedPaymentMethod, address, LocalDate.now(), phoneNumber);
                    showAlert("Payment Successful", "Payment confirmed!");
                } else {
                    showAlert("Invalid Input", "Please enter a valid phone number and address.");
                }
            } else if ("Pay with Credit Card".equals(selectedPaymentMethod)) {
                // Chargez la vue de carte de crédit
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