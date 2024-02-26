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
    private TextField productNameField; // assuming this is for the product name

    @FXML
    private TextField promoCodeField;


    @FXML
    private TextField search; // assuming this is for the search field

    @FXML
    private TextField totalField; // assuming this is for the total

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle)  {
//        setupValidation();
//    }

    private final List<String> CODE_PROMO = Arrays.asList("pilates01", "pwellb11", "mindsoult", "pilatesforlife");

    private final CommandeService cs = new CommandeService();

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void boutouna(ActionEvent event) {
        String productName = productNameField.getText();
        String promoCode = promoCodeField.getText();
        int total = Integer.parseInt(totalField.getText());

        // You may need to adjust this logic based on your requirements for order creation

            
         if (!isValidCodePromo(promoCodeField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite");
            alert.setContentText("Ajouter code Promo correct !.");
            alert.showAndWait();
            
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherOrder.fxml"));
                Parent root = loader.load();

                Scene currentScene = ((Node) event.getSource()).getScene();

                currentScene.setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


            System.out.println("Veuillez remplir tous les champs.");
        }


    private void validateAndAddOrder() throws Exception {
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
            showAlert(Alert.AlertType.ERROR, "Missing Information", "Please fill in all fields.");
        }
    }

    private boolean isValidCodePromo(String promoCode) {
        if (CODE_PROMO.contains(promoCode)) {
            return true;
        } else
            return false;


    }
}


//package org.esprit.controllers;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.stage.Stage;
//import org.esprit.services.CommandeService;
//import org.esprit.models.Commande;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.Arrays;
//import java.util.List;
//import java.util.ResourceBundle;
//
//
//public class AjouterOrder implements Initializable {
//
//
//
//
//    @FXML
//    private TextField productNameField; // assuming this is for the product name
//
//    @FXML
//    private TextField promoCodeField;
//
//    @FXML
//    private TextField totalField; // assuming this is for the total
//
//    // Regular expression for validating promo code
//    private final List<String> CODE_PROMO = Arrays.asList("pilates01", "pwellb11", "mindsoult", "pilatesforlife");
//
//    private final CommandeService cs = new CommandeService();
//
//    //
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle)  {
//        setupValidation();
//    }
//    private void showAlert(Alert.AlertType type, String title, String message) {
//        Alert alert = new Alert(type);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    @FXML
//    void boutouna(ActionEvent event) {
//        try {
//            validateAndAddOrder();
//        } catch (NumberFormatException e) {
//            showAlert(Alert.AlertType.ERROR, "Invalid Total", "Please enter a valid Total !");
//        } catch (Exception e) {
//            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
//        }
//    }
//
//    private void validateAndAddOrder() throws Exception {
//        String productName = productNameField.getText();
//        String promoCode = promoCodeField.getText();
//        int total = Integer.parseInt(totalField.getText());
//
//        // Validate promo code
//        if (!isValidCodePromo(promoCode)) {
//            showAlert(Alert.AlertType.ERROR, "Invalid Promo Code", "Please enter a valid Code Promo !");
//            return; // Stop processing if promo code is invalid
//        }
//
//        // Validate if productName and promoCode are not empty
//        if (!productName.isEmpty() && !promoCode.isEmpty()) {
//            // Assuming recipeService works for orders too
//            cs.ajouter(new Commande(total, promoCode, productName));
//            productNameField.clear();
//            promoCodeField.clear();
//            totalField.clear();
//        } else {
//            showAlert(Alert.AlertType.ERROR, "Missing Information", "Please fill in all fields.");
//        }
//    }
//
//
//
////    @FXML
////    void naviguer(ActionEvent event) {
////        try {
////            FXMLLoader loader = new FXMLLoader(getClass().getResource("src/org/main/resources/AjouterOrder.fxml"));
////            Parent root = loader.load();
//////            Scene scene = productNameField.getScene();
////            Stage stage = new Stage();
//////            Stage stage = (Stage) scene.getWindow();
////            stage.setScene(new Scene(root));
////            stage.setTitle("Display infos");
////            stage.show();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////    }
//
//
//
//
//
////    @FXML
////    void back(ActionEvent event) {
////        try {
////            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCommande.fxml"));
////            Parent root = loader.load();
////            Scene scene = new Scene(root);
////            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
////            window.setScene(scene);
////        } catch (IOException e) {
////            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
////        }
////    }
//
////    public void Delete(ActionEvent actionEvent) {
////    }
//
//
//}
//
//
