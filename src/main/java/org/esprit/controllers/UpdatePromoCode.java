package org.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.esprit.models.Commande;
import org.esprit.services.CommandeService;

import javax.swing.text.html.ImageView;
import java.awt.*;

public class UpdatePromoCode {
    private String id;
    private Stage primaryStage;

    @FXML
    private Label addorder;

    @FXML
    private AnchorPane orderin;

    @FXML
    private ImageView pilates;

    @FXML
    private Label prod;

    @FXML
    private TextField productNameField;

    @FXML
    private TextField promoCodeField;

    @FXML
    private Label promocode;

    @FXML
    private Label total;

    @FXML
    private TextField totalField;

    @FXML
    void updateB(ActionEvent event) {

    }
    private final CommandeService rc = new CommandeService();
    public void initData(Commande selectedCommande) {
        Commande commande = rc.rechercheCommande(id);
        rc.rechercheCommande(id);
    }

    public void setPassedId(String id) {
        this.id = id;
    }
    public void setPrimaryStage(Stage primaryStage) {

        this.primaryStage = primaryStage;
    }
}
