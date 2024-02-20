package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.example.Models.Event;
import org.example.Services.ServiceEvent;

import java.util.List;

public class AfficherEvents {



    @FXML
    private ListView<Event> listE;

    private final ServiceEvent serviceEvent = new ServiceEvent();

    @FXML
    private void initialize() {
        // Call the method to populate the ListView
        loadEvents();
    }

    private void loadEvents() {
        // Retrieve the list of events from the database
        List<Event> events = serviceEvent.afficher();

        // Set the retrieved events to the ListView
        listE.getItems().setAll(events);
    }
}
