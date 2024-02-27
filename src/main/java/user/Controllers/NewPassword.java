package user.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import user.Services.userService;

import java.io.IOException;
import java.sql.SQLException;

public class NewPassword {
    @FXML
    private Button backBT;

    @FXML
    private Button confirmBT;

    @FXML
    private PasswordField newMdpTF;
    private final user.Services.userService userService = new userService();

    @FXML
    void confirm(ActionEvent event) {
        String newPassword = newMdpTF.getText();
        int userId = getUserIdFromSession(); //recuperer l'id

        if (newPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a new password.");
            return;
        }
        try {
            userService.updatePassword(userId, newPassword);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Password updated successfully.");
            // Navigate to a new scene or perform any other action after successful password update
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update password: " + e.getMessage());
        }

    }
    private int getUserIdFromSession(){
     return 0;
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
            e.printStackTrace();
        }
    }
}
