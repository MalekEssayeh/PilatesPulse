package org.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.esprit.models.Livraison;
import org.esprit.services.LivraisonService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherLiv implements Initializable {


    @FXML
    private ListView<Livraison> deliveryDetailsListView;

    Stage primaryStage = new Stage();
    private final LivraisonService ls = new LivraisonService();
    private ObservableList<Livraison> livraisons;
    LivraisonService liv = new LivraisonService();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Fetch the last added order from the database
        List<Livraison> lastAddedDeliveries = liv.fetchLivraisons();

        // Check if there are any last added orders
        if (!lastAddedDeliveries.isEmpty()) {
            // Assuming you want the last added delivery
            Livraison lastAddedDelivery = lastAddedDeliveries.get(lastAddedDeliveries.size() - 1);

            // Populate the product name and total from the last added order
            String lastAddedAdress = lastAddedDelivery.getAdresseLiv();
            LocalDate lastdate = Date.valueOf(lastAddedDelivery.getDateLiv()).toLocalDate();
            String lastmethodPay = lastAddedDelivery.getMethodePay();
            int lastphone = lastAddedDelivery.getPhone();

            // Add the last added delivery to the listOrders
            livraisons = FXCollections.observableArrayList(lastAddedDelivery);
            deliveryDetailsListView.setItems(livraisons);
        }

//        // Show the welcome notification
//        showWelcomeNotification();
    }

//    private void showWelcomeNotification() {
//        String message = "Thank you for trusting us, Pilates Pulse is GRATEFUL FOR YOU <3";
//        Notifications.create()
//                .title("Welcome!")
//                .text(message)
//                .darkStyle()
//                .graphic(null)
//                .showInformation(); // Utilisez showInformation() pour une notification rose

    public void delete(ActionEvent actionEvent) {
        // Implement the functionality for the delete button action
        // You can use listOrders.getSelectionModel().getSelectedItem() to get the selected item
        System.out.println("Delete Button clicked");
        Livraison selectedLiv = deliveryDetailsListView.getSelectionModel().getSelectedItem();
        System.out.println("Deleting order: " + selectedLiv);
        livraisons.remove(selectedLiv);
    }
//      public void updateListView(Livraison newLivraison) {
//            // Clear existing items in the ListView
//            deliveryDetailsListView.getItems().clear();
//
//            // Fetch the updated list of deliveries and add them to the ListView
//            List<Livraison> updatedLivraisons = ls.fetchLivraisons();
//        ObservableList<Livraison> observableList = FXCollections.observableList(updatedLivraisons);
//          deliveryDetailsListView.getItems().addAll(observableList);
//
//           // Add the newLivraison to the ListView if it's not null
//           if (newLivraison != null) {deliveryDetailsListView.getItems().add(newLivraison);
//          }
//       }
////       private void refreshListView() {
//           // Fetch data from the database using your CommandeService
//            CommandeService cs = new CommandeService();
    //     List<Commande> fetchedOrders = cs.fetch()
//            // Clear and re-populate the ListView with fetched data
//            orders.clear();
//            orders.addAll(fetchedOrders);
//        }
//        private void showUpdateNotification(String oldPromoCode, String newPromoCode) {
////            String message = String.format("You updated your promo code from %s to %s", oldPromoCode, newPromoCode);
////            Notifications.create()
////                    .title("Promo Code Updated")
////                    .text(message)
////                    .showInformation();
////        }
////
////
////
//
//






    List<Livraison> livraisonList = ls.fetchLivraisons();



    @FXML
    void check1(ActionEvent event) {

    }

    @FXML
    void check2(ActionEvent event) {

    }



    @FXML
    void recherchebouton(ActionEvent event) {

    }


    @FXML
    void Update(ActionEvent event) throws IOException {
        System.out.println("Update Button clicked");
        Livraison selectedLiv = deliveryDetailsListView.getSelectionModel().getSelectedItem();
        System.out.println("Updating Delivery: " + selectedLiv);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateAdresse.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        UpdateAdress updateAdress = loader.getController();
        updateAdress.initData(selectedLiv);

        // Set a listener to refresh the ListView after the update
        stage.setOnHidden(e -> {
            String newAdress = updateAdress.getNewAdress();
        });

        // Use showAndWait to block processing until the window is closed
        stage.showAndWait();

    }


    @FXML
    void trierbouton(ActionEvent event) {

    }


}
