package org.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.esprit.models.Commande;
import org.esprit.services.CommandeService;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class UpdatePromoCode implements Initializable {


    private Commande id;
    private Stage primaryStage;

    @FXML
    private AnchorPane orderin;
    private String newPromoCode;


    @FXML
    private TextField productNameField;

    @FXML
    private TextField promoCodeField;


    @FXML
    private TextField totalField;
    private Commande selectedOrder; // Store the selected order


    public String getNewPromoCode() {
        return newPromoCode;
    }
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

            // Set the new promo code
            newPromoCode = promoCodeText;

            // Close the stage after updating
            Stage stage = (Stage) promoCodeField.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid integer
            showErrorNotification("Invalid promo code: " + promoCodeText);
        }

    }

    private boolean isValidPromoCode(String promoCode) {
        // Implement logic to check if the promo code is valid
        // You can compare it with a list of allowed promo codes
        List<String> allowedPromoCodes = Arrays.asList("pilates987", "mindsoulT", "moveurmind", "peacepilate");
        return allowedPromoCodes.contains(promoCode);
    }

    private void showUpdateNotification(String promoCode, double discountedTotal) {
        // Display a message with the updated promo code and discounted total
        String message = String.format("You updated your promo code to %s. Your discounted total is %.2f", promoCode, discountedTotal);
        Notifications.create()
                .title("Promo Code Updated")
                .text(message)
                .showInformation();
    }

    private void showErrorNotification(String errorMessage) {
        Notifications.create()
                .title("Error")
                .text(errorMessage)
                .showError();
    }
        private final CommandeService rc = new CommandeService();

        public void setPassedId (Commande id){
            this.id = id;
        }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            if (selectedOrder != null) {
                promoCodeField.setText(selectedOrder.getCodePromo());
                productNameField.setText(selectedOrder.getNomProd());
                totalField.setText(String.valueOf(selectedOrder.getTotal()));
            }


        }

        public void initData (Commande selectedOrder){
            this.selectedOrder = selectedOrder;
            promoCodeField.setText(selectedOrder.getCodePromo());

        }

    }

