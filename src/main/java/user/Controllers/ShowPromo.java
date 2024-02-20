package user.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import user.Models.Promo;
import user.Services.PromoService;
import user.Services.userService;
import user.Models.user;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ShowPromo {
    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button Add;
    @FXML
    private Button Delete;
    @FXML
    private Button back;

    @FXML
    private ListView<Promo> promosLV;
    private final PromoService ps = new PromoService();

    @FXML
    void initialize() {
        try {
            List<Promo> promoList = ps.show();
            promosLV.getItems().addAll(promoList);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }
    @FXML
    void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
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
        // Get the selected promo from the ListView
        Promo selectedPromo = promosLV.getSelectionModel().getSelectedItem();

        if (selectedPromo != null) {
            try {
                // Call the delete method from PromoService
                ps.delete(selectedPromo.getCode());

                // Remove the deleted promo from the ListView
                promosLV.getItems().remove(selectedPromo);

                showAlert(Alert.AlertType.INFORMATION, "Success", "Promo deleted successfully.");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete promo: " + e.getMessage());
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a promo to delete.");
        }
    }


    @FXML
    void Update(ActionEvent event) {
        // Get the selected promo from the ListView
        Promo selectedPromo = promosLV.getSelectionModel().getSelectedItem();

        // Check if a promo is selected
        if (selectedPromo == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a promo to update.");
            return;
        }

        try {
            // Load the UpdatePromo.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdatePromo.fxml"));
            Parent root = loader.load();

            // Pass the selected promo's information to the UpdatePromoController
            UpdatePromo controller = loader.getController();
            controller.initialize();
            controller.initData(selectedPromo);

            // Create a new stage for the update promo interface
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Update Promo");

            // Show the update promo stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load update promo interface.");
        }
    }


    @FXML
    void Add(ActionEvent event) {
        try {
            // Load the Adduser.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddPromo.fxml"));
            Parent root = loader.load();

            // Create a new stage for the Adduser interface
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Sign Up");

            // Show the Adduser stage
            stage.show();
        } catch (IOException e) {
            // Handle any potential IOException
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load ");
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
    void search(ActionEvent event) {
        String keyword = searchTextField.getText();
        List<Promo> searchResults = ps.search(keyword);
        ObservableList<Promo> observableSearchResults = FXCollections.observableArrayList(searchResults);
        promosLV.setItems(observableSearchResults);
    }


}
