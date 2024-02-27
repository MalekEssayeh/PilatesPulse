package user.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import user.Models.user;
import user.Services.userService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.io.IOException;
import java.util.regex.Pattern;

import javafx.scene.Node;


public class Adduser {
    @FXML
    private Button signupBT;
    @FXML
    private TextField nomdpTF;
    @FXML
    private CheckBox showpwdCB;

    @FXML
    private TextField mailTF;

    @FXML
    private PasswordField mdpTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField prenomTF;
    private final userService us= new userService();
    // Hashage MD5
    public static String doHashing(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

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
            String nom = nomTF.getText().trim();
            String prenom = prenomTF.getText().trim();
            String email = mailTF.getText().trim();
            String password = mdpTF.getText(); // No need to trim password

            // Check if nom and prenom fields are empty
            if (nom.isEmpty() || prenom.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Empty Fields", "Nom and prenom fields can't be empty.");
                return;
            }

            // Check if the combination of nom and prenom already exists
            if (us.userExists(nom, prenom)) {
                showAlert(Alert.AlertType.ERROR, "Existing User", "A user with the same nom and prenom already exists.");
                return;
            }

            // Check if the email is already registered
            if (us.emailExists(email)) {
                showAlert(Alert.AlertType.ERROR, "Existing Email", "This email is already registered.");
                return;
            }

            // Validate email format
            if (!isValidEmail(email)) {
                showAlert(Alert.AlertType.ERROR, "Invalid Email", "Please enter a valid email address.");
                return;
            }
            // Attempt to add the user if all validations pass
            us.add2(new user(nom, prenom, doHashing(password), email, "client"));
            showAlert(Alert.AlertType.INFORMATION, "Success", "sign up successful.");
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
    @FXML
    void showPwd(ActionEvent event) {
        if (showpwdCB.isSelected()) {
            nomdpTF.setText(mdpTF.getText());
            nomdpTF.setVisible(true);
            mdpTF.setVisible(false);
            return;
        }
        mdpTF.setText( nomdpTF.getText());
        mdpTF.setVisible(true);
        nomdpTF.setVisible(false);

    }

}
