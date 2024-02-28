package org.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.esprit.models.Commande;
import org.esprit.services.CommandeService;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdatePromoCode implements Initializable {


    private Commande id;
    private Stage primaryStage;

    @FXML
    private AnchorPane orderin;


    @FXML
    private TextField productNameField;

    @FXML
    private TextField promoCodeField;


    private Commande commande;


    @FXML
    private TextField totalField;


    @FXML
    void updateB(ActionEvent event) {
        String promoCodeText = promoCodeField.getText().trim(); // Trim to remove leading/trailing whitespaces

        try {
            int promoCode = Integer.parseInt(promoCodeText);
            // Your code to handle the parsed integer...
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            System.out.println("Invalid promo code: " + promoCodeText);
        }


    }

    private final CommandeService rc = new CommandeService();
//    public void initData(Commande selectedCommande) {
//        Commande commande = rc.rechercheCommande();
//        rc.rechercheCommande(id);
//    }

    public void setPassedId(Commande id) {
        this.id = id;
    }

    public void setPrimaryStage(Stage primaryStage) {

        this.primaryStage = primaryStage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (commande != null) {
            promoCodeField.setText(commande.getCodePromo());
            System.out.println(id);
            promoCodeField.setText(id.getCodePromo());
            productNameField.setText(id.getNomProd());
            totalField.setText(id.getTotal() + "");

        }


    }

    public void initData(Commande selectedOrder) {
    }
}
