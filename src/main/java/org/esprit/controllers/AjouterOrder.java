package org.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.esprit.services.CommandeService;
import org.esprit.models.Commande;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class AjouterOrder {

    @FXML
    private Label addorder;

    @FXML
    private Button confirm;

    @FXML
    private Button listet;
    @FXML
    private TextField productNameField; // assuming this is for the product name

    @FXML
    private TextField promoCodeField;

    @FXML
    private TextField totalField; // assuming this is for the total

    // Regular expression for validating promo code
    private final List<String> CODE_PROMO = Arrays.asList("pilates01", "pwellb11", "mindsoult", "pilatesforlife");

    private final CommandeService cs = new CommandeService();

    // Method to set up text field validation
    // Method to set up text field validation
    private void setupValidation() {
        // Validate productname and promocode fields to allow only letters
        setupLetterValidation(productNameField);


        // Validate promocode field to match the codepromo pattern
        promoCodeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidCodePromo(newValue)) {
                promoCodeField.setStyle("-fx-border-color: red;");
            } else {
                promoCodeField.setStyle(""); // Remove red border if codepromo is valid
            }
        });
    }


// Method to validate promo code against predefined list
    private boolean isValidCodePromo(String codePromo) {
        return CODE_PROMO.contains(codePromo);
    }

    // Method to set up validation to allow only letters in the text field
    private void setupLetterValidation(TextField textField) {
        textField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[A-Za-z]*")) {
                return change;
            }
            return null;
        }));
    }

    @FXML
    void initialize() {
        setupValidation(); // Call setupValidation method when the controller is initialized
    }

    @FXML
    void boutouna(ActionEvent event) {
        try {
            String productName = productNameField.getText();
            String promoCode = promoCodeField.getText();
            int total = Integer.parseInt(totalField.getText());

            // Validate promo code
            if (!isValidCodePromo(promoCode)) {
                showAlert(Alert.AlertType.ERROR, "Invalid Promo Code", "Please enter a valid Code Promo !");
                return; // Stop processing if promo code is invalid
            }

            // Validate if productName and promoCode are not empty
            if (!productName.isEmpty() && !promoCode.isEmpty()) {
                // Assuming recipeService works for orders too
                cs.ajouter(new Commande(total, promoCode, productName));
                productNameField.clear();
                promoCodeField.clear();
                totalField.clear();
            } else {
                System.out.println("Veuillez remplir tous les champs.");
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Total", "Please enter a valid Total !");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }



    @FXML
    void naviguer(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherOrder.fxml"));
            Parent root = loader.load();
//            Scene scene = productNameField.getScene();
            Stage stage = new Stage();
//            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Display infos");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load display interface.");
        }
    }


    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCommande.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }
}


