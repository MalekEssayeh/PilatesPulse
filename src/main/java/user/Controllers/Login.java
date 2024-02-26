package user.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.Models.user;
import user.Services.userService;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


public class Login {

    @FXML
    private TextField mailTF;

    @FXML
    private PasswordField mdpTF;

    @FXML
    private Button loginBT;

    @FXML
    private Button SignUp;
    @FXML
    private Button resetPwdBT;

    private final userService userService = new userService();

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


    @FXML
    public void LogIn(ActionEvent event) {
        String email = mailTF.getText();
        String password = mdpTF.getText();
        boolean isAdmin = userService.isAdmin(email);

        // Input validation
        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter email and password.");
            return;
        }

        try {
            // Authenticate the user
            boolean authenticated = userService.login(email,doHashing(mdpTF.getText()));

            // Check if authentication was successful
            if (authenticated) {
                // Successful login logic (navigate to the next scene, etc.)
                System.out.println("Login successful!");
                String fxmlFile = isAdmin ? "/Backend.fxml" : "/Home.fxml";
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = loader.load();
                Stage stage = (Stage) loginBT.getScene().getWindow(); // Get the current stage
                stage.setScene(new Scene(root));
                stage.setTitle(isAdmin ? "Admin" : "Home");
                stage.show();
            } else {
                // Display an error message for invalid credentials
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid email or password.");
            }
        } catch (IOException e) {
            // Handle any potential exceptions
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred: " + e.getMessage());
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
    void SignUp(ActionEvent event) {
        try {
            // Load the Adduser.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Adduser.fxml"));
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
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Sign Up interface.");
        }
    }
    @FXML
    void ResetPwd(ActionEvent event) {
        try {
            // Load the Adduser.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ResetPwd.fxml"));
            Parent root = loader.load();

            // Create a new stage for the Adduser interface
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Reset Password");

            // Show the Adduser stage
            stage.show();
        } catch (IOException e) {
            // Handle any potential IOException
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load Reset pwd interface.");
        }

    }


}

