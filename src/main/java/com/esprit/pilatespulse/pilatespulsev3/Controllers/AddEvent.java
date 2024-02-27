package com.esprit.pilatespulse.pilatespulsev3.Controllers;

import com.esprit.pilatespulse.pilatespulsev3.Models.Event;
import com.esprit.pilatespulse.pilatespulsev3.Services.EventService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.time.LocalDate;

public class AddEvent {

    @FXML
    private TextField nameTextField;

    @FXML
    private DatePicker dateDatePicker;

    @FXML
    private DatePicker finishDatePicker;

    @FXML
    private TextField participantsTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button addEventButton;

    private final EventService serviceEvent = new EventService();

    @FXML
    public void initialize() {
        // You can perform any initialization here
    }

    @FXML
    public void addEvent() {
        String name = nameTextField.getText();
        LocalDate startDate = dateDatePicker.getValue();
        LocalDate finishDate = finishDatePicker.getValue();
        int participants = Integer.parseInt(participantsTextField.getText());
        String description = descriptionTextArea.getText();

        // Assuming coachID is obtained from somewhere (e.g., logged-in user)
        int coachID = 1; // Replace with the actual coachID

        Event event = new Event(name, Date.valueOf(startDate), Date.valueOf(finishDate), participants, description, coachID);

        serviceEvent.addEvent(event);


    }



}
