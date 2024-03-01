package org.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ChoosePayment {

    @FXML
    private AnchorPane methodP;
    // Reference to AjouterLiv controller
    private AjouterLiv ajouterLivController; // Ensure this field is declared

    public void setAjouterLivController(AjouterLiv ajouterLivController) {
        this.ajouterLivController = ajouterLivController;
    }

    @FXML
    void PaiementCash(ActionEvent event) throws IOException {
        // Set methodPay in AjouterLiv
        ajouterLivController.setMethodPay("Paiement a la livraison");

        // Assuming ajouterLivController is properly set
        ajouterLivController.setMethodPay("Cash");
        // Switch back to AjouterLiv
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterLiv.fxml"));
        Parent root = loader.load();
        AjouterLiv ajouterLivController = loader.getController();
        ajouterLivController.setMethodPay("Paiement a la livraison");

        // Update the methodlabel in AjouterLiv
        ajouterLivController.setMethodLabel("Paiement a la livraison");

        // Set the updated scene
        methodP.getScene().setRoot(root);
    }

    @FXML
    void addCard(ActionEvent event) {

    }

}
