package user.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.Models.user;
import user.Services.userService;

import java.io.IOException;

public class ResetPwd {
    @FXML
    private Button backBT;

    @FXML
    private TextField mailTF;

    @FXML
    private Button resetBT;
    private final userService userService= new userService();

    @FXML
    void Reset(ActionEvent event) {
        String email = mailTF.getText();

        // Check if the email field is empty
        if (email.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter your email.");
            return;
        }

        // Call the UserService to handle the password reset logic
        boolean resetSuccessful = userService.resetPassword(email);

        if (resetSuccessful) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Password reset successful. Check your email for the new password.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to reset password. Please try again later.");
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
}
