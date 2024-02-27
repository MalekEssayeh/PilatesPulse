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



    @FXML
    void updateB(ActionEvent event) {
        rc.modifier(new Commande( Integer.parseInt(totalField.getText()),promoCodeField.getText(),productNameField.getText()),promoCodeField.getText());



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

        System.out.println(id);
        promoCodeField.setText(id.getCodePromo());
        productNameField.setText(id.getNomProd());
        totalField.setText(id.getTotal()+"");

    }
}
