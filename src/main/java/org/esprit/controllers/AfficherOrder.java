package org.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.esprit.models.Commande;
import org.esprit.services.CommandeService;

import java.util.List;

public class AfficherOrder {



    @FXML
    private ListView<Commande> listOrders;

     Stage primaryStage= new Stage();



    private final CommandeService rc = new CommandeService();

    @FXML
    void initialize() {

        List<Commande> commandeList = rc.fetch();

        // Set the items for all the list views
        ObservableList<Commande> observableList = FXCollections.observableList(commandeList);
        listOrders.getItems().addAll(rc.fetch());
    }






    public void Delete(ActionEvent actionEvent) {
        Commande selectedExercice = listOrders.getSelectionModel().getSelectedItem();

        if (selectedExercice != null) {
            int id = selectedExercice.getIdCmd();
            rc.supprimer(id);
            listOrders.getItems().remove(selectedExercice);
        }


    }

    public void Update(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdatePromoCode.fxml"));
            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == UpdatePromoCode.class) {
                    UpdatePromoCode editionExerciceController = new UpdatePromoCode();
                    Commande selectedOrder = listOrders.getSelectionModel().getSelectedItem();
                    Commande id = selectedOrder;
                    editionExerciceController.setPassedId(id);
                    return editionExerciceController;
                } else {
                    return new UpdatePromoCode();
                }
            });

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));



            stage.show();

            UpdatePromoCode UpdatePromoCodeController = loader.getController();

            UpdatePromoCodeController.setPrimaryStage(primaryStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }}


//
//        Commande selectedCommande = listeProd.getSelectionModel().getSelectedItem();
//        if (selectedCommande != null) {
//            System.out.println("Vous pouvez mettre à jour la commande sélectionnée : " + selectedCommande.getNomProd());
//        } else {
//            System.out.println("Veuillez sélectionner une commande à mettre à jour.");
//        }
//    }
//}
//

//package org.esprit.controllers;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextField;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.Stage;
//import org.esprit.models.Commande;
//import org.esprit.services.CommandeService;
//
//import java.io.IOException;
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class AfficherOrder implements Initializable {
//
//    @FXML
//    private ListView<Commande> listeOrders;
//
//    @FXML
//    private Label order;
//
//    @FXML
//    private TextField searchHere;
//    private final CommandeService cs = new CommandeService();
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        // You can perform any initialization here that needs to be done when the FXML is loaded.
//        // For example, you might want to populate the list view with initial data.
//        List<Commande> initialData = cs.fetch();
//        ObservableList<Commande> observableData = FXCollections.observableArrayList(initialData);
//        listeOrders.setItems(observableData);
//    }
//    @FXML
//    void deleteOrder(MouseEvent event){
//        Commande selectedCommande = listeOrders.getSelectionModel().getSelectedItem();
//        if (selectedCommande != null) {
//            int commandeId = selectedCommande.getIdCmd();
//            try {
//                cs.supprimer(commandeId);
//                listeOrders.getItems().remove(selectedCommande);
//                showAlert(Alert.AlertType.INFORMATION, "Success", "Commande deleted successfully.");
//            } catch (Exception e) {
//                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete commande: " + e.getMessage());
//            }
//        } else {
//            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a commande to delete.");
//        }
//    }
//
//
//
//    @FXML
//    void serachOrder(MouseEvent event)
//throws SQLException {
//            String productName = searchHere.getText();
//            List<Commande> searchResults = cs.rechercheCommande(productName);
//            ObservableList<Commande> observableSearchResults = FXCollections.observableArrayList(searchResults);
//        listeOrders.setItems(observableSearchResults);
//    }
//
//    @FXML
//    void updateOrder(MouseEvent event) {
//            Commande selectedCommande = listeOrders.getSelectionModel().getSelectedItem();
//            if (selectedCommande != null) {
//                try {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateCommande.fxml"));
//                    Parent root = loader.load();
//
//                    UpdateCommande controller = loader.getController();
//                    controller.initData(selectedCommande);
//
//                    Stage stage = new Stage();
//                    stage.setScene(new Scene(root));
//                    stage.setTitle("Update Commande");
//
//                    stage.show();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                showAlert(Alert.AlertType.WARNING, "Warning", "Please select a commande to update.");
//            }
//        }
//    private void showAlert(Alert.AlertType type, String title, String message) {
//        Alert alert = new Alert(type);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    public void Delete(ActionEvent actionEvent) {
//    }
//
//    public void Update(ActionEvent actionEvent) {
//    }
//
//    public void back(ActionEvent actionEvent) {
//    }
//}
//
