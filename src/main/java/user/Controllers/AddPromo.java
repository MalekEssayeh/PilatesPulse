package user.Controllers;

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
import user.Models.Promo;
import user.Models.user;
import user.Services.PromoService;
import user.Services.userService;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import java.sql.Date;
import java.sql.SQLException;

public class AddPromo {
    @FXML
    private Button BackBT;

    @FXML
    private Button AddBT;

    @FXML
    private TextField PercentageTF;

    @FXML
    private DatePicker validiteDP;

    @FXML
    private ChoiceBox<user> userCB;

    private final PromoService promoService = new PromoService();
    private final userService userService = new userService();

    @FXML
    void initialize() {
        // Populate the choice box with users
        List<user> userList = userService.show();
        userCB.setItems(FXCollections.observableArrayList(userList));
        // Set a custom cell factory to display only nom and prenom
       /* userCB.setCellFactory(new Callback<ListView<user>, ListCell<user>>() {
            @Override
            public ListCell<user> call(ListView<user> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(user user, boolean empty) {
                        super.updateItem(user, empty);
                        if (empty || user == null) {
                            setText(null);
                        } else {
                            // Display only the nom and prenom attributes of the user object
                            setText(user.getNom() + " " + user.getPrenom());
                        }
                    }
                };
            }
        });*/

        // Set up text formatter to allow only numeric input for the percentage text field
        PercentageTF.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*\\.?\\d*")) { // Allow digits and optional decimal point
                return change;
            }
            return null;
        }));
        // Control de saisie calendrier
        validiteDP.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                // Disable past dates
                setDisable(date.isBefore(LocalDate.now()));
            }
        });
    }


    @FXML
    void Add(ActionEvent event) {
        try {
            // Parse the percentage from the UI
            float percentage = Float.parseFloat(PercentageTF.getText());

            // Parse the validite from the UI
            Date validite = Date.valueOf(validiteDP.getValue());

            // Calculate the isActive status
            boolean isActive = validite.toLocalDate().isAfter(LocalDate.now());

            // Get the selected user from the choice box
            user selectedUser = userCB.getValue();

            // Create a new Promo object
            Promo promo = new Promo(percentage, validite, isActive, selectedUser.getId());

            // Call the add2 method in PromoService to add the promo
            promoService.add2(promo);

            // Optionally, show a success message
            System.out.println("Promo added successfully!");
        } catch (NumberFormatException e) {
            // Handle the case where the percentage is not a valid float
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid percentage value.");
        } catch (Exception e) {
            // Handle any other exceptions
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add promo: " + e.getMessage());
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
    void Back(ActionEvent event) {
        try {
            // Load the previous interface (for example, Main.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowPromo.fxml"));
            Parent root = loader.load();

            // Create a new stage for the previous interface
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Show the previous interface stage
            stage.show();

            // Close the current stage (ShowPromo interface)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load previous interface");
        }
    }
}

