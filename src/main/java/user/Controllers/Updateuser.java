package user.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import user.Services.userService;
import user.Models.user;

public class Updateuser {
    @FXML
    private TextField mailTF;

    @FXML
    private TextField mdpTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField prenomTF;

    private user selectedUser; // Declare the selectedUser variable
    private final userService userService = new userService();

    public void initData(user selectedUser) {
        // Populate the UI with the selected user's information
        // For example:
        nomTF.setText(selectedUser.getNom());
        prenomTF.setText(selectedUser.getPrenom());
        mailTF.setText(selectedUser.getMail());
        mdpTF.setText(selectedUser.getMdp());
    }

    void updateUser() {
        // Get the updated information from the UI fields
        String updatedNom = nomTF.getText();
        String updatedPrenom = prenomTF.getText();
        String updatedMail = mailTF.getText();
        String updatedMdp = mdpTF.getText();

        // Update the user object with the new information
        selectedUser.setNom(updatedNom);
        selectedUser.setPrenom(updatedPrenom);
        selectedUser.setMail(updatedMail);
        selectedUser.setMdp(updatedMdp);

        // Call the userService to update the user in the database
        try {
            userService.update(selectedUser);
            // Show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("User updated successfully.");
            alert.showAndWait();
        } catch (Exception e) {
            // Show an error message if the update fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Failed to update user: " + e.getMessage());
            alert.showAndWait();
        }
    }

}
