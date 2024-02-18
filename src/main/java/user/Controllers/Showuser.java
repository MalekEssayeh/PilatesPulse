package user.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import user.Services.userService;
import user.Models.user;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Showuser {
    @FXML
    private Button Delete;

    @FXML
    private Button Update;
    @FXML
    private ListView<user> mailLV;

    @FXML
    private ListView<user> mdpLV;

    @FXML
    private ListView<user> nomLV;

    @FXML
    private ListView<user> prenomLV;

    private final userService us = new userService();

    @FXML
    void initialize() {
        try {
            List<user> userList = us.show();

            // Set the items for all the list views
            ObservableList<user> observableList = FXCollections.observableList(userList);
            nomLV.setItems(observableList);
            prenomLV.setItems(observableList);
            mailLV.setItems(observableList);
            mdpLV.setItems(observableList);

            // Customize cell appearance
            nomLV.setCellFactory(param -> new ListCell<user>() {
                @Override
                protected void updateItem(user item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getNom());
                    }
                }
            });

            prenomLV.setCellFactory(param -> new ListCell<user>() {
                @Override
                protected void updateItem(user item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getPrenom());
                    }
                }
            });

            mailLV.setCellFactory(param -> new ListCell<user>() {
                @Override
                protected void updateItem(user item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getMail());
                    }
                }
            });

            mdpLV.setCellFactory(param -> new ListCell<user>() {
                @Override
                protected void updateItem(user item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getMdp());
                    }
                }
            });

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void LogOut(ActionEvent actionEvent) {
        try {
            // Close the current stage (Home stage)
            Node source = (Node) actionEvent.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();

            // Load the login.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();

            // Create a new stage for the login interface
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");

            // Show the login stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Delete(ActionEvent event) {
        // Get the selected user from the ListView
        user selectedUser = mailLV.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            int userId = selectedUser.getId(); // Assuming getId() returns the user's ID
            // Call the delete method from userService
            try {
                us.delete(userId);
                // If the delete method executes without throwing an exception,
                // assume that the deletion was successful
                // Remove the deleted user from the list view
                mailLV.getItems().remove(selectedUser);
                // Optionally, you can show a success message
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("User deleted successfully.");
                alert.showAndWait();
            } catch (Exception e) {
                // Show an error message if the deletion fails
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Failed to delete user: " + e.getMessage());
                alert.showAndWait();
            }
        } else {
            // Show a warning message if no user is selected for deletion
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select a user to delete.");
            alert.showAndWait();
        }
    }

    @FXML
    void Update(ActionEvent event) {
        // Get the selected user from the ListView
        user selectedUser = mailLV.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                // Load the UpdateUser.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Updateuser.fxml"));
                Parent root = loader.load();

                // Pass the selected user's information to the UpdateUserController
                Updateuser controller = loader.getController();
                controller.initData(selectedUser);

                // Create a new stage for the update user interface
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Update User");

                // Show the update user stage
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Show a warning message if no user is selected for updating
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select a user to update.");
            alert.showAndWait();
        }
    }






}




