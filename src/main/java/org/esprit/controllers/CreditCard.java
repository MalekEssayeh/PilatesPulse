package org.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreditCard {

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField expirationDateField;

    @FXML
    private TextField cvvField;

    // Getter methods for accessing credit card details
    public String getCardNumber() {
        return cardNumberField.getText();
    }

    public String getExpirationDate() {
        return expirationDateField.getText();
    }

    public String getCvv() {
        return cvvField.getText();
    }

    public boolean isValid() {
        // Implement your credit card validation logic
        // For example, check if the card number, expiration date, and CVV are valid
        return isValidCardNumber(getCardNumber()) && isValidExpirationDate(getExpirationDate()) && isValidCVV(getCvv());
    }

    private boolean isValidCardNumber(String cardNumber) {
        // Implement card number validation logic
        return cardNumber.matches("\\d{16}");  // Example: 16 digits
    }

    private boolean isValidExpirationDate(String expirationDate) {
        // Implement expiration date validation logic
        // You may want to use a library like java.time for better date handling
        return expirationDate.matches("\\d{2}/\\d{4}");  // Example: MM/YYYY
    }

    private boolean isValidCVV(String cvv) {
        // Implement CVV validation logic
        return cvv.matches("\\d{3}");  // Example: 3 digits
    }
}

