package user.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import user.Models.user;
import user.Services.userService;
import java.util.List;
import java.io.IOException;
import java.util.regex.Pattern;

import javafx.scene.Node;


public class Adduser {

    @FXML
    private TextField mailTF;

    @FXML
    private TextField mdpTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField prenomTF;
    private final userService us= new userService();

    // Regular expression for validating email addresses
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    // Method to set up text field validation
    // Method to set up text field validation
    private void setupValidation() {
        // Validate nom and prenom fields to allow only letters
        setupLetterValidation(nomTF);
        setupLetterValidation(prenomTF);

        // Validate email field to match the email regex pattern
        mailTF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidEmail(newValue)) {
                mailTF.setStyle("-fx-border-color: red;");
            } else {
                mailTF.setStyle(""); // Remove red border if email is valid
            }
        });
    }

    // Method to validate email using regex pattern
    private boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    // Method to set up validation to allow only letters in the text field
    private void setupLetterValidation(TextField textField) {
        textField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[A-Za-z]*")) {
                return change;
            }
            return null;
        }));
    }

    @FXML
    void initialize() {
        setupValidation(); // Call setupValidation method when the controller is initialized
    }

    @FXML
    void signUp(ActionEvent event) {
        try {
            String email = mailTF.getText();
            if (!isValidEmail(email)) {
                showAlert(Alert.AlertType.ERROR, "Invalid Email", "Please enter a valid email address.");
                return; // Stop sign-up process if email is invalid
            }

            // Attempt to add the user only if the email is valid
            us.add2(new user(nomTF.getText(), prenomTF.getText(), mdpTF.getText(), email,"client"));
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
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
