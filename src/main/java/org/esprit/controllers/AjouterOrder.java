package org.esprit.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.esprit.models.Commande;
import org.esprit.services.CommandeService;

public class AjouterOrder {

    @FXML
    private TextField productNameField;

    @FXML
    private TextField promoCodeField;

    @FXML
    private TextField totalField;

    @FXML
    private Button confirmButton;

    @FXML
    private Label addOrderLabel;

    @FXML
    private Label productLabel;

    @FXML
    private Label promoCodeLabel;

    @FXML
    private Label totalLabel;



    private Commande sharedCommande; // Shared data model
    CommandeService cs = new CommandeService();


         public void initialize() {
             // Fetch the last order from the database
             Commande lastOrder = cs.fetchLastAddedOrder(); // Implement a method in CommandeService to fetch the last order

             if (lastOrder != null) {
                 // Use the product name and total from the last order
                 String productName = lastOrder.getNomProd();
                 int total = lastOrder.getTotal();

                 // Set the product name and total in their respective fields
                 productNameField.setText(productName);
                 totalField.setText(String.valueOf(total));

                 // Make the product name and total fields non-editable
                 productNameField.setEditable(false);
                 totalField.setEditable(false);

                 // Get the entered promo code
                 String promoCode = promoCodeField.getText();

                 // Implement control de saisie for the promo code
                 if (isValidPromoCode(promoCode)) {
                     // The promo code is valid, proceed with any additional logic if needed
                 } else {
                     // Show an error notification for an invalid promo code
                     showErrorNotification("Invalid promo code. Please enter a valid promo code.");
                 }
             } else {
                 // Handle the case where lastOrder is null, e.g., show an error message or take appropriate action
                 showErrorNotification("Error fetching the last order from the database.");
             }
         }
    @FXML
    public void boutouna() {
        try {
            if (sharedCommande == null) {
                // Initialize sharedCommande if not initialized
                sharedCommande = new Commande();
            }


            sharedCommande.setNomProd(productNameField.getText());
            sharedCommande.setCodePromo(promoCodeField.getText());
            sharedCommande.setTotal(Integer.parseInt(totalField.getText()));

            // Call the ajouter method to add the data to the database
            cs.ajouter(sharedCommande);

            System.out.println("Confirm Button clicked");
            System.out.println("Product Name: " + sharedCommande.getNomProd());
            System.out.println("Promo Code: " + sharedCommande.getCodePromo());
            System.out.println("Total: " + sharedCommande.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void addOrder() {
        // Get the entered promo code
        String promoCode = promoCodeField.getText();

        // Implement control de saisie for the promo code
        if (isValidPromoCode(promoCode)) {
            // The promo code is valid, proceed with the add order logic
            // ...
        } else {
            // Show an error notification for an invalid promo code
            showErrorNotification("Invalid promo code. Please enter a valid promo code.");
        }
    }

    private void showErrorNotification(String errorMessage) {
             // Show an error notification using JavaFX Alert
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText(null);
             alert.setContentText(errorMessage);

             alert.showAndWait();
    }

    private boolean isValidPromoCode(String promoCode) {
        // List of allowed promo codes
        List<String> allowedPromoCodes = Arrays.asList("pilatespulse12", "soulmindT", "therapy22");

        // Check if the entered promo code is in the allowed list
        boolean isValid = allowedPromoCodes.contains(promoCode);

        // Show an error notification if the promo code is not valid
        if (!isValid) {
            showErrorNotification("Invalid promo code. Please enter a valid promo code.");
        }

        // Return the result of the validation
        return isValid;
    }

    public void naviguer(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherOrder.fxml"));
            Parent root = loader.load();
            Scene scene = productNameField.getScene();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    }

