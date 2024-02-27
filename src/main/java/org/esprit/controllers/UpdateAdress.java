package org.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.esprit.models.Commande;
import org.esprit.models.Livraison;
import org.esprit.services.CommandeService;
import org.esprit.services.LivraisonService;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateAdress implements Initializable {

    private Livraison id;
    private Stage primaryStage;
    @FXML
    private Label Adr;

    @FXML
    private TextField AdressField;

    @FXML
    private Label addLiv;

    @FXML
    private ImageView pilates;

    @FXML
    void updateB(ActionEvent event) {
        ls.modifier(new Livraison(AdressField.getText()),AdressField.getText());

    }

    private final LivraisonService ls = new LivraisonService();

    public void setPassedId(Livraison id) {
        this.id = id;
    }
    public void setPrimaryStage(Stage primaryStage) {

        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(id);
        AdressField.setText(id.getAdresseLiv());


    }
}