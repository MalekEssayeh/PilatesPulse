package user.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import user.Services.userService;
import user.Models.user;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class Showuser {
    @FXML
    private ChoiceBox<String> filterCB;
    @FXML
    private Button filterBT;
    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;
    @FXML
    private Button Delete;

    @FXML
    private Button Update;
    @FXML
    private Button back;

    @FXML
    private ListView<user> usersLV;

    private final userService us = new userService();


 @FXML
 void initialize() {
     try {
         // Listen for changes in the search text field
         searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
             // Call search method with the updated keyword
             String keyword = newValue.trim();
             List<user> searchResults = us.search(keyword);
             ObservableList<user> observableSearchResults = FXCollections.observableArrayList(searchResults);
             usersLV.setItems(observableSearchResults);
         });
         List<user> userList = us.show();
         usersLV.setItems(FXCollections.observableArrayList(userList));

         // Set custom cell factory for the ListView
         usersLV.setCellFactory(new Callback<ListView<user>, ListCell<user>>() {
             @Override
             public ListCell<user> call(ListView<user> param) {
                 return new ListCell<user>() {
                     @Override
                     protected void updateItem(user item, boolean empty) {
                         super.updateItem(item, empty);
                         if (empty || item == null) {
                             setText(null);
                         } else {
                             // Set the text of the cell to display only the desired fields
                             setText("Nom: " + item.getNom() + "\n" +
                                     "Prenom: " + item.getPrenom() + "\n" +
                                     "Mail: " + item.getMail() + "\n" +
                                     "Role: " + item.getRole());
                         }
                     }
                 };
             }
         });
     } catch (Exception e) {
         showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
     }
     filterCB.getItems().addAll("nom", "prenom");
 }


    @FXML
    void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Backend.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
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
        user selectedUser = usersLV.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            int userId = selectedUser.getId(); // Assuming getId() returns the user's ID
            // Call the delete method from userService
            try {
                us.delete(userId);
                // If the delete method executes without throwing an exception,
                // assume that the deletion was successful
                // Remove the deleted user from the list view
                usersLV.getItems().remove(selectedUser);
                // Optionally, you can show a success message
                showAlert(Alert.AlertType.INFORMATION, "Success", "User deleted successfully.");
            } catch (Exception e) {
                // Show an error message if the deletion fails
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete user: " + e.getMessage());
            }
        } else {
            // Show a warning message if no user is selected for deletion
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a user to delete.");
        }
    }


    @FXML
    void Update(ActionEvent event) {
        // Get the selected user from the ListView
        user selectedUser = usersLV.getSelectionModel().getSelectedItem();
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
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a user to update.");
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
    void search(ActionEvent event) {
        String keyword = searchTextField.getText();
        List<user> searchResults = us.search(keyword);
        ObservableList<user> observableSearchResults = FXCollections.observableArrayList(searchResults);
        usersLV.setItems(observableSearchResults);
    }


  public void filterUsers(ActionEvent actionEvent) {
      if(filterCB.getValue().equals("nom")) {
          List<user> filteredUsers =  usersLV.getItems().stream().sorted(Comparator.comparing(user::getNom)).toList();
          ObservableList<user> observableUserList = FXCollections.observableArrayList(filteredUsers);
          usersLV.setItems(observableUserList);
      }
      if(filterCB.getValue().equals("prenom")) {
          List<user> filteredUsers =  usersLV.getItems().stream().sorted(Comparator.comparing(user::getPrenom)).toList();
          ObservableList<user> observableUserList = FXCollections.observableArrayList(filteredUsers);
          usersLV.setItems(observableUserList);
      }
  }
}




