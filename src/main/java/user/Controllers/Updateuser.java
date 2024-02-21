package user.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import user.Services.userService;
import user.Models.user;

import java.io.IOException;

public class Updateuser {
    @FXML
    private Button Back;
    @FXML
    private TextField mailTF;

    @FXML
    private TextField mdpTF;

    @FXML
    private TextField nomTF;

    @FXML
    private TextField prenomTF;
    private int id;
    private user selectedUser; // Declare the selectedUser variable
    private final userService userService = new userService();

    public void setSelectedUser(user selectedUser) {
        this.selectedUser = selectedUser;
    }

    public void initData(user selectedUser) {
        id=selectedUser.getId();
        // Populate the UI with the selected user's information
        // For example:
        nomTF.setText(selectedUser.getNom());
        prenomTF.setText(selectedUser.getPrenom());
        mailTF.setText(selectedUser.getMail());
        mdpTF.setText(selectedUser.getMdp());

        // Set the selected user
        setSelectedUser(selectedUser);
    }


    @FXML
    void updateUser() {
        // Get the updated information from the UI fields
        String updatedNom = nomTF.getText();
        String updatedPrenom = prenomTF.getText();
        String updatedMail = mailTF.getText();
        String updatedMdp = mdpTF.getText();

        // Update the user object with the new information
        selectedUser.setId(id);
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
    @FXML
    void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Showuser.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            // Close the current stage (update user interface)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }


}
