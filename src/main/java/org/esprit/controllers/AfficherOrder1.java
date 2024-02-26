//package org.esprit.controllers;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.stage.Stage;
//import javafx.util.Callback;
//import org.esprit.models.Commande;
//import org.esprit.services.CommandeService;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//public class AfficherOrder1 {
//    @FXML
//    private ChoiceBox<String> filterCB;
//
//    @FXML
//    private Button search;
//
//    @FXML
//    private TextField searchTextField;
//
//    @FXML
//    private Button Delete;
//
//    @FXML
//    private Button Update;
//
//
//
//    @FXML
//    private ListView<Commande> commandesLV;
//
//    private final CommandeService commandeService = new CommandeService();
//
//    @FXML
//    void initialize() {
//        try {
//            List<Commande> commandeList = commandeService.fetch();
//            commandesLV.setItems(FXCollections.observableArrayList(commandeList));
//
//            // Set custom cell factory for the ListView
//            commandesLV.setCellFactory(new Callback<ListView<Commande>, ListCell<Commande>>() {
//                @Override
//                public ListCell<Commande> call(ListView<Commande> param) {
//                    return new ListCell<Commande>() {
//                        @Override
//                        protected void updateItem(Commande item, boolean empty) {
//                            super.updateItem(item, empty);
//                            if (empty || item == null) {
//                                setText(null);
//                            } else {
//                                // Set the text of the cell to display only the desired fields
//                                setText("Total: " + item.getTotal() + "\n" +
//                                        "Code Promo: " + item.getCodePromo() + "\n" +
//                                        "Product Name: " + item.getNomProd() + "\n" +
//                                        "User ID: " + item.getIdUser());
//                            }
//                        }
//                    };
//                }
//            });
//        } catch (Exception e) {
//            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
//        }
//        filterCB.getItems().addAll("Product Name");
//    }
//
////    @FXML
////    void back(ActionEvent event) throws IOException {
////        try {
////            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterOrder.fxml"));
////            Parent root = loader.load();
////            Scene scene = new Scene(root);
////            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
////            window.setScene(scene);
////        } catch (IOException e) {
////            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
////        }
////    }
//
//    @FXML
//    void search(ActionEvent event) throws SQLException {
//        String productName = searchTextField.getText();
//        List<Commande> searchResults = commandeService.rechercheCommande(productName);
//        ObservableList<Commande> observableSearchResults = FXCollections.observableArrayList(searchResults);
//        commandesLV.setItems(observableSearchResults);
//    }
//
//    private void showAlert(Alert.AlertType type, String title, String message) {
//        Alert alert = new Alert(type);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    @FXML
//    void Delete(ActionEvent event) {
//        Commande selectedCommande = commandesLV.getSelectionModel().getSelectedItem();
//        if (selectedCommande != null) {
//            int commandeId = selectedCommande.getIdCmd();
//            try {
//                commandeService.supprimer(commandeId);
//                commandesLV.getItems().remove(selectedCommande);
//                showAlert(Alert.AlertType.INFORMATION, "Success", "Commande deleted successfully.");
//            } catch (Exception e) {
//                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete commande: " + e.getMessage());
//            }
//        } else {
//            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a commande to delete.");
//        }
//    }
//
//    @FXML
//    void Update(ActionEvent event) {
//        Commande selectedCommande = commandesLV.getSelectionModel().getSelectedItem();
//        if (selectedCommande != null) {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateCommande.fxml"));
//                Parent root = loader.load();
//
//                UpdateCommande controller = loader.getController();
//                controller.initData(selectedCommande);
//
//                Stage stage = new Stage();
//                stage.setScene(new Scene(root));
//                stage.setTitle("Update Commande");
//
//                stage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a commande to update.");
//        }
//    }
//    @FXML
//    void filterCommandes(ActionEvent event) {
//        String selectedFilter = filterCB.getValue();
//
//        if ("Product Name".equals(selectedFilter)) {
//            List<Commande> filteredCommandes = commandeService.filtreCommande("nomProd");
//            ObservableList<Commande> observableFilteredCommandes = FXCollections.observableArrayList(filteredCommandes);
//            commandesLV.setItems(observableFilteredCommandes);
//        } else {
//            return;
//        }
//    }
//}
