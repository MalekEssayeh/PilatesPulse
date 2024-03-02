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
import org.esprit.models.Livraison;
import org.esprit.services.LivraisonService;

import java.io.IOException;
import java.time.LocalDate;

public class AjouterLiv {

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

    LivraisonService ls = new LivraisonService();



    @FXML
    void addCard(ActionEvent event) {

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

        // Create a Livraison object
        Livraison livraison = new Livraison("Cash on delivery", adresseText, selectedDate, Integer.parseInt(phoneText));

        // Set idCmd to NULL explicitly
        livraison.setIdCmd(null);

        // Call the service method to add to the database
        ls.ajouter(livraison);

        System.out.println("Livraison added successfully to the database!");


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
    void passDelivery(ActionEvent event) {


        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherLiv.fxml"));
            Parent root = loader.load();

            // Create a new scene
            Scene scene = new Scene(root);

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(scene);

            // Show the new scene
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    }
