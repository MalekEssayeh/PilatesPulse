package org.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.esprit.models.Commande;
import org.esprit.models.Livraison;
import org.esprit.services.CommandeService;
import org.esprit.services.LivraisonService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherLiv implements Initializable {




    @FXML
    private ListView<Livraison> deliveryDetailsListView;

    Stage primaryStage= new Stage();
    private final LivraisonService ls = new LivraisonService();




    public void delete(ActionEvent actionEvent) {
        Livraison selectedLiv = deliveryDetailsListView.getSelectionModel().getSelectedItem();

        if (selectedLiv != null) {
            int id = selectedLiv.getIdLiv();
            ls.supprimer(id);
            deliveryDetailsListView.getItems().remove(selectedLiv);
        }
    }



    public void Update(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateAdress.fxml"));
            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == UpdateAdress.class) {
                    UpdateAdress updateAdressController = new UpdateAdress();
                    Livraison selectedLiv = deliveryDetailsListView.getSelectionModel().getSelectedItem();
                    Livraison id = selectedLiv;
                    updateAdressController.setPassedId(id);
                    return updateAdressController;
                } else {
                    return new UpdatePromoCode();
                }
            });

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));



            stage.show();

            UpdateAdress updateAdressController = loader.getController();

            updateAdressController.setPrimaryStage(primaryStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<Livraison> livraisonList = ls.fetchLivraisons();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        // Set the items for all the list views
        ObservableList<Livraison> observableList = FXCollections.observableList(livraisonList);
        deliveryDetailsListView.getItems().addAll(ls.fetchLivraisons());
    }
}

