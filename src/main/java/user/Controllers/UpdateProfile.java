package user.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.Models.user;
import user.Services.userService;
import user.Utils.UserSession;

import java.io.IOException;

public class UpdateProfile {
    @FXML
    private Button backBT;

    @FXML
    private TextField mailTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField numTelTF;

    @FXML
    private TextField prenomTF;
    private int id;
    private user user;
    private final userService userService = new userService();
    public void setUser(user user) {
        this.user = user;
    }

    public void initData(user user) {
        this.user = user;
        // Set user's information
        nomTF.setText(user.getNom());
        prenomTF.setText(user.getPrenom());
        mailTF.setText(user.getMail());
        numTelTF.setText(String.valueOf(user.getNumTel()));
    }

    public void UpdateProfile(ActionEvent actionEvent) {
        try {
            // Get updated information from the UI fields
            String nom = nomTF.getText();
            String prenom = prenomTF.getText();
            String mail = mailTF.getText();
            int numTel = Integer.parseInt(numTelTF.getText()); // Convert text to integer

            // Update the user's profile information
            user.setNom(nom);
            user.setPrenom(prenom);
            user.setMail(mail);
            user.setNumTel(numTel);

            userService.update(user);

            // Update the UserSession with the new information
            UserSession.setNom(nom);
            UserSession.setPrenom(prenom);
            UserSession.setMail(mail);
            UserSession.setNumTel(numTel);

            // Show a success message
            showAlert(Alert.AlertType.INFORMATION, "Success", "User updated successfully.");

        } catch (Exception e) {
            // Show an error message if the update fails
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update user: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
