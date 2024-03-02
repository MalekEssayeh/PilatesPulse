package org.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.esprit.models.Commande;
import org.esprit.models.Livraison;
import org.esprit.services.CommandeService;
import org.esprit.services.LivraisonService;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateAdress implements Initializable {

    private Livraison id;
    private Stage primaryStage;
    @FXML
    private Label Adr;

    @FXML
    private TextField AdressField;
    private Livraison selectedLiv;
    private String nouvAdress;

    @FXML
    private Label addLiv;

    @FXML
    private ImageView pilates;

    LivraisonService liv = new LivraisonService();

    public String getNewAdress() {
        return nouvAdress;
    }

    @FXML
    void updateB(ActionEvent event) {
        String adressText = AdressField.getText().trim(); // Trim to remove leading/trailing whitespaces

        try {
            int adressLiv = Integer.parseInt(adressText);
            // Your code to handle the parsed integer...

            // Update the promo code in the selected order
            selectedLiv.setAdresseLiv(adressText);

            // Save the changes to the database (assuming your service has an update method)
            CommandeService cs = new CommandeService();
            liv.modifier(selectedLiv, adressText);

            // Set the new promo code
            nouvAdress = adressText;

            // Close the stage after updating
            Stage stage = (Stage) AdressField.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            showErrorNotification("Invalid adress: " + adressText);

        }


//    private void showUpdateNotification(String adressLiv, String nouvAdress) {
//        // Display a message with the updated promo code and discounted total
//        String message = String.format("You updated your adress to %s. ", adressLiv, nouvAdress);
//        Notifications.create()
//                .title("Promo Code Updated")
//                .text(message)
//                .showInformation();
//    }
//
//
//        private void showErrorNotification (String errorMessage){
//            Notifications.create()
//                    .title("Error")
//                    .text(errorMessage)
//                    .showError();
//        }
//
//        private final LivraisonService ls = new LivraisonService();

//        public void setPassedId (Livraison id){
//            this.id = id;
//        }
//
//        public void setPrimaryStage (Stage primaryStage){
//
//            this.primaryStage = primaryStage;
//        }
//
//


//    public String getNewAdress() {
//
//    }

        }

    private void showErrorNotification(String s) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            System.out.println(id);
            AdressField.setText(id.getAdresseLiv());

            if (selectedLiv != null) {
                AdressField.setText(selectedLiv.getAdresseLiv());
            }

        
        public void initData (Livraison selectedLiv){
            this.selectedLiv = selectedLiv;
            AdressField.setText(selectedLiv.getAdresseLiv());
    }
}

    public void initData(Livraison selectedLiv) {
    }
}