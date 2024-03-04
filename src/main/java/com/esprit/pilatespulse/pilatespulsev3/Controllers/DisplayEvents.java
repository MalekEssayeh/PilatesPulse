package com.esprit.pilatespulse.pilatespulsev3.Controllers;

import com.esprit.pilatespulse.pilatespulsev3.Models.Event;
import com.esprit.pilatespulse.pilatespulsev3.Services.EventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class DisplayEvents implements Initializable {

    private EventService eventService ;
    @FXML
    private ListView<Event> eventList;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            eventService = new EventService();

            if (eventList == null) {
                throw new IllegalStateException("There are no events in the list");
            }
            List<Event> eventsList = eventService.displayEvents();
            eventList.getItems().addAll(eventsList);
        } catch (IllegalStateException ex1){
            showErrorAlert("Error", ex1.getMessage());
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void initData() {
        List<Event> evenementList = eventService.displayEvents();
        ObservableList<Event> observableList = FXCollections.observableArrayList(evenementList);
        eventList.setItems(observableList);
    }

    @FXML
    private void updateList(ActionEvent event){
        Event selectedEvent = eventList.getSelectionModel().getSelectedItem();
        if (selectedEvent != null ){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("com/esprit/pilatespulse/pilatespulsev3/EditEvent.fxml"));
                Parent parent = loader.load();
                EditEvent controller = loader.getController();
                controller.initData(selectedEvent);
                Stage stage = (Stage) eventList.getScene().getWindow();
                stage.setScene(new Scene(parent));
                stage.show();
            }catch (IOException ex){
                throw new RuntimeException(ex);
            }
        }else {
            displayErrorMessage("Please select an event to modify!");
        }

    }


    private void displayErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void deleteEvent(ActionEvent event) {
        Event selectedUser = eventList.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm ");
            confirmationAlert.setHeaderText("delete event ?");
            confirmationAlert.setContentText("Are you sure you want to delete?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {


                    eventService.deleteEvent(selectedUser.getEventID());

                    initData();
                } catch (Exception e) {
                    showErrorAlert("Erreur .", e.getMessage());
                }
            }
        } else {

           showErrorAlert("error","Please select an event to delete.");
        }
    }

    public void goBack(ActionEvent event) throws IOException{
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("com/esprit/pilatespulse/pilatespulsev3/AddEvent.fxml"));
            Parent root=loader.load();
            Scene scene=new Scene(root);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
        }catch(IOException e){
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }

    @FXML
    void goModifier(ActionEvent event) {
        // Code pour modifier l'utilisateur sélectionné dans la liste
        Event selectedevenement = eventList.getSelectionModel().getSelectedItem();
        if (selectedevenement != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/apollogui/ModifierEvenement.fxml"));
                Parent root = loader.load();
                EditEvent controller = loader.getController();
                controller.initData(selectedevenement);
                controller.setEvenement(selectedevenement);
                // Passer l'utilisateur sélectionné au contrôleur de l'interface de modification
                Stage window = (Stage) eventList.getScene().getWindow();
                window.setScene(new Scene(root));
                window.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Aucun utilisateur sélectionné, affichez un message d'erreur
            showErrorAlert("Error","Veuillez sélectionner un encher à modifier.");
        }
    }

}
