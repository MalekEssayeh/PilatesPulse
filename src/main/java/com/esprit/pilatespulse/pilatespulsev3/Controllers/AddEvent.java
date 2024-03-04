package com.esprit.pilatespulse.pilatespulsev3.Controllers;

import com.esprit.pilatespulse.pilatespulsev3.Models.Event;
import com.esprit.pilatespulse.pilatespulsev3.Services.EventService;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class AddEvent implements Initializable {


    @FXML
    private TextField eventName;

    @FXML
    private DatePicker eventDate;

    @FXML
    private TextField nbrParticipants;

    @FXML
    private Button addButton;

    @FXML
    private Button imageButton;

    @FXML
    private TextArea description;

    private EventService eventService;

    private FileChooser fileChooser;
    private String imagePath ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventService = new EventService();

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
    }

//
@FXML
public void choisirImage(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png", "*.gif");
    fileChooser.getExtensionFilters().add(filter);
    File selectedFile = fileChooser.showOpenDialog(new Stage());
    if (selectedFile != null) {
        imagePath = selectedFile.toURI().toString();
        System.out.println("Fichier sélectionné : " + imagePath);
    }
}


    @FXML
    public void addEvent() {
        // Get values from UI components
        String name = eventName.getText();
//        String dateStr = eventDate.getEditor().getText(); // Get text from DatePicker editor
        int participants = 0;
        try {
            participants = Integer.parseInt(nbrParticipants.getText());
        } catch (NumberFormatException e) {
            showErrorAlert("Number of Participants must be a valid integer.");
            return; // Stop execution if validation fails
        }

        String desc = description.getText();

        // Validate input
        if (name.isEmpty()) {
            showErrorAlert("Event Name cannot be empty.");
            return;
        }

//        if (dateStr.isEmpty()) {
//            showErrorAlert("Event Date cannot be empty.");
//            return;
//        }

       /*if (!isValidDate(dateStr)) {
            showErrorAlert("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }*/

        if (participants <= 0) {
            showErrorAlert("Number of Participants must be greater than 0.");
            return;
        }

        // Assuming you have a Coach ID, replace 1 with the actual Coach ID
        int coachID = 1;

        String imageURL = imagePath; // Initialize imageURL to an empty string


        // Create an Event object with the gathered information
        Event newEvent = new Event(name,eventDate.getValue(), participants, desc, coachID, imageURL);

        // Call the service method to add the new event to the database
        eventService.addNewEvent(newEvent);

        // Optionally, you can reset the input fields after adding the event
        clearInputFields();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Validation Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private void clearInputFields() {
        eventName.clear();
        eventDate.setValue(null);
        nbrParticipants.clear();
        description.clear();
    }



}
