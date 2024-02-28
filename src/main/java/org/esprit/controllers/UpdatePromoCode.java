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





    @FXML
    private TextField totalField;
    private Commande selectedOrder; // Store the selected order


    @FXML
    void updateB(ActionEvent event) {
        String promoCodeText = promoCodeField.getText().trim(); // Trim to remove leading/trailing whitespaces

        try {
            int promoCode = Integer.parseInt(promoCodeText);
            // Your code to handle the parsed integer...

            // Update the promo code in the selected order
            selectedOrder.setCodePromo(promoCodeText);

            // Save the changes to the database (assuming your service has an update method)
            CommandeService cs = new CommandeService();
            cs.modifier(selectedOrder, promoCodeText);

            // Close the stage after updating
            Stage stage = (Stage) promoCodeField.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            System.out.println("Invalid promo code: " + promoCodeText);
        }


    }
    public String getNewPromoCode() {
        return promoCodeField.getText();
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

            if (selectedOrder != null) {
                promoCodeField.setText(selectedOrder.getCodePromo());
                productNameField.setText(selectedOrder.getNomProd());
                totalField.setText(String.valueOf(selectedOrder.getTotal()));
            }
        }




    public void initData(Commande id) {
        selectedOrder = id;
        promoCodeField.setText(id.getCodePromo());
    }

}
