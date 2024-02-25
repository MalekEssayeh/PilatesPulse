package org.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.esprit.models.Commande;
import org.esprit.services.CommandeService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AfficherOrder {

    @FXML
    private ChoiceBox<String> filterCB;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;



    @FXML
    private Button Delete;

    @FXML
    private Button Update;

    @FXML
    private Button back;

    @FXML
    private ListView<Commande> commandesLV;




    private final CommandeService commandeService = new CommandeService();
    ObservableList<Commande> listCommande = FXCollections.observableArrayList(
      new Commande(100,"pilates01","Legging en beige"),
            new Commande(40,"pilates01","Whey")

            );

    @FXML
    void initialize() {
        try {
            List<Commande> listCommande = commandeService.fetch();
            ObservableList<Commande> observableList = FXCollections.observableList(listCommande);

            commandesLV.setItems(observableList);

            // Set custom cell factory for the ListView
            commandesLV.setCellFactory(new Callback<ListView<Commande>, ListCell<Commande>>() {
                @Override
                public ListCell<Commande> call(ListView<Commande> param) {
                    return new ListCell<Commande>() {
                        @Override
                        protected void updateItem(Commande item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                // Set the text of the cell to display only the desired fields
                                setText("ID: " + item.getIdCmd() + "\n" +
                                        "Total: " + item.getTotal() + "\n" +
                                        "Code Promo: " + item.getCodePromo() + "\n" +
                                        "Product: " + item.getNomProd());
                            }
                        }
                    };
                }
            });
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
        filterCB.getItems().addAll("nomProd");
    }
//    @FXML
//    void search(ActionEvent event) throws SQLException {
//        String nomProd = searchTextField.getText();
//        List<Commande> searchResults = commandeService.rechercheCommande(nomProd);
//        ObservableList<Commande> observableSearchResults = FXCollections.observableArrayList(searchResults);
//        commandesLV.setItems(observableSearchResults);
//    }
@FXML
void search(ActionEvent event) {
    try {
        String nomProd = searchTextField.getText();

        // Call the rechercheCommande method to get the search results
        List<Commande> searchResults = commandeService.rechercheCommande(nomProd);

        // Update the ListView with the search results
        ObservableList<Commande> observableSearchResults = FXCollections.observableArrayList(searchResults);
        commandesLV.setItems(observableSearchResults);
    } catch (SQLException e) {
        showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while searching: " + e.getMessage());
    }
}


    @FXML
    void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterOrder.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }

    @FXML
    void Delete(ActionEvent event) {
        // Get the selected commande from the ListView
        Commande selectedCommande = commandesLV.getSelectionModel().getSelectedItem();
        if (selectedCommande != null) {
            int commandeId = selectedCommande.getIdCmd();            // Call the delete method from CommandeService
            try {
                commandeService.supprimer(commandeId);
                // If the delete method executes without throwing an exception,
                // assume that the deletion was successful
                // Remove the deleted commande from the list view
                commandesLV.getItems().remove(String.valueOf(selectedCommande));  // Remove the string representation
                // Optionally, you can show a success message
                showAlert(Alert.AlertType.INFORMATION, "Success", "Commande deleted successfully.");
            } catch (Exception e) {
                // Show an error message if the deletion fails
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete commande: " + e.getMessage());
            }
        } else {
            // Show a warning message if no commande is selected for deletion
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a commande to delete.");
        }
    }

    @FXML
    void Update(ActionEvent event) {
        // Get the selected commande from the ListView
        Commande selectedCommande = commandesLV.getSelectionModel().getSelectedItem();
        if (selectedCommande != null) {
            try {
                // Load the UpdateCommande.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateCommande.fxml"));
                Parent root = loader.load();

                // Pass the selected commande's information to the UpdateCommandeController
                UpdateCommandeController controller = loader.getController();
                controller.initData(selectedCommande);

                // Create a new stage for the update commande interface
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Update Commande");

                // Show the update commande stage
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Show a warning message if no commande is selected for updating
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a commande to update.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @FXML
    void filterCommandes(ActionEvent event) {
        // Get the selected filter option from the ChoiceBox
        String selectedFilter = filterCB.getValue();

        // Retrieve the list of commandes based on the selected filter option
        List<Commande> filteredCommandes;
        if ("nomProd".equals(selectedFilter)) {
            filteredCommandes = commandeService.filtreCommande("nomProd"); // Change this to appropriate ID or value

        } else {
            // Handle invalid or null filter option
            return;
        }

        // Update the ListView with the filtered list of commandes
        ObservableList<Commande> observableFilteredOrders = FXCollections.observableArrayList(filteredCommandes);
        commandesLV.setItems(observableFilteredOrders);
    }
}
