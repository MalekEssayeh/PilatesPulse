package org.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.esprit.models.Commande;
import org.esprit.services.CommandeService;

import java.io.IOException;
import java.util.List;

public class AfficherOrder {

    @FXML
    private ListView<Commande> listOrders;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private ImageView logoImageView;

    private ObservableList<Commande> orders;

    @FXML
    public void initialize() {
        // Initialize the ListView with sample data (you should replace this with your actual order data)
        CommandeService cs = new CommandeService();
        List<Commande> fetchedOrders = cs.fetch();

        // Initialize the ListView with fetched data
        orders = FXCollections.observableArrayList(fetchedOrders);
        listOrders.setItems(orders);
    }

    @FXML
    public void Delete() {
        // Implement the functionality for the delete button action
        // You can use listOrders.getSelectionModel().getSelectedItem() to get the selected item
        System.out.println("Delete Button clicked");
        Commande selectedOrder = listOrders.getSelectionModel().getSelectedItem();
        System.out.println("Deleting order: " + selectedOrder);
        orders.remove(selectedOrder);
    }

    @FXML
    public void Update() {System.out.println("Update Button clicked");
        Commande selectedOrder = listOrders.getSelectionModel().getSelectedItem();
        System.out.println("Updating order: " + selectedOrder);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdatePromoCode.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            UpdatePromoCode updatePromoCode = loader.getController();
            updatePromoCode.initData(selectedOrder);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addOrder(Commande order) {
        // Add the new order to the ListView
        orders.add(order);
        CommandeService cs = new CommandeService();
        cs.ajouter(order);
    }




}
