package org.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.esprit.services.CommandeService;
import org.esprit.models.Commande;

import java.io.IOException;


public class AjouterOrder {

    @FXML
    private Label addorder;

    @FXML
    private Button confirm;

    @FXML
    private ImageView image;
    @FXML
    private TextField product;


    @FXML
    private Label label;

    @FXML
    private MenuBar menu;

    @FXML
    private TextField productNameField; // assuming this is for the product name

    @FXML
    private TextField promoCodeField;


    @FXML
    private TextField search; // assuming this is for the search field

    @FXML
    private TextField totalField; // assuming this is for the total

    private final CommandeService cs = new CommandeService();

    @FXML
    void boutouna(ActionEvent event) {
        String productName = productNameField.  getText();
        String promoCode = promoCodeField.getText();
        int total = Integer.parseInt(totalField.getText());

        // You may need to adjust this logic based on your requirements for order creation
        if (!productName.isEmpty() && !promoCode.isEmpty()) {
            // Assuming recipeService works for orders too
            cs.ajouter(new Commande(total, promoCode, productName));
            productNameField.clear();
            promoCodeField.clear();
            totalField.clear();
            search.clear();
        } else {
            System.out.println("Veuillez remplir tous les champs.");
        }
    }

    @FXML
    void naviguer(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherOrder.fxml"));
            Parent root = loader.load();
            Scene scene = productNameField.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}