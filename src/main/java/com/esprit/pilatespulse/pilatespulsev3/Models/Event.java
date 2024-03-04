package com.esprit.pilatespulse.pilatespulsev3.Models;


import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Event {

    private int eventID;
    private String name;
    private LocalDate date;
    private int nbrParticipants;
    private String description;
    private int coachID;
    private String imageURL;
    private final ObservableSet<User> eventParticipants = FXCollections.observableSet();
    private final ObservableSet<Session> sessionList = FXCollections.observableSet();

    public Event() {
    }

    public Event(int eventID, String name, LocalDate date, int nbrParticipants, String description, int coachID) {
        this.eventID = eventID;
        this.name = name;
        this.date = date;
        this.nbrParticipants = nbrParticipants;
        this.description = description;
        this.coachID = coachID;
    }

    public Event(int eventID, String name, LocalDate date, int nbrParticipants, String description, int coachID, String imageURL) {
        this.eventID = eventID;
        this.name = name;
        this.date = date;
        this.nbrParticipants = nbrParticipants;
        this.description = description;
        this.coachID = coachID;
        this.imageURL = imageURL;
    }

    public Event(String name, LocalDate date, int nbrParticipants, String description, int coachID, String imageURL) {
        this.name = name;
        this.date = date;
        this.nbrParticipants = nbrParticipants;
        this.description = description;
        this.coachID = coachID;
        this.imageURL = imageURL;
    }

    public Event(String name, LocalDate date, int nbrParticipants, String description, int coachID) {
        this.name = name;
        this.date = date;
        this.nbrParticipants = nbrParticipants;
        this.description = description;
        this.coachID = coachID;
    }

    public Event(String name, LocalDate date, int nbrParticipants) {
        this.name = name;
        this.date = date;
        this.nbrParticipants = nbrParticipants;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate startDate) {
        this.date = startDate;
    }

    public int getNbrParticipants() {
        return nbrParticipants;
    }

    public void setNbrParticipants(int nbrParticipants) {
        this.nbrParticipants = nbrParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCoachID() {
        return coachID;
    }

    public void setCoachID(int coachID) {
        this.coachID = coachID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public ObservableSet<User> getEventParticipants() {
        return eventParticipants;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventID=" + eventID +
                ", name='" + name + '\'' +
                ", startDate=" + date +
                ", nbrParticipants=" + nbrParticipants +
                ", description='" + description + '\'' +
                ", coachID=" + coachID +
                ", eventParticipants=" + eventParticipants +
                '}';
    }
}
