package org.esprit.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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

    @FXML
    private ImageView pilatesImageView;

    private Commande sharedCommande; // Shared data model
    CommandeService cs = new CommandeService();

    public void initialize() {
        sharedCommande = new Commande();
    }

    @FXML
    public void boutouna() {
        try {
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

