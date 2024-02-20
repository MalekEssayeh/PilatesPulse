package org.example.Models;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Event {

    private int id;
    private String nom;
    private LocalDate date;
    private Time heureDebut;
    private Time getHeureFin;
    private int numParticipants;
    private String description;
    private List<User> event_participants;

    public List<User> getEvent_participants() {
        return event_participants;
    }

    public void setEvent_participants(List<User> event_participants) {
        this.event_participants = event_participants;
    }

    public Event(String nom, LocalDate date, Time heureDebut, Time getHeureFin, int numParticipants, String description, List<User> event_participants) {
        this.nom = nom;
        this.date = date;
        this.heureDebut = heureDebut;
        this.getHeureFin = getHeureFin;
        this.numParticipants = numParticipants;
        this.description = description;
        this.event_participants = event_participants;
    }

    public Event() {
    }

    public Event(int id, String nom, LocalDate date, Time heureDebut, Time getHeureFin, int numParticipants, String description) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.heureDebut = heureDebut;
        this.getHeureFin = getHeureFin;
        this.numParticipants = numParticipants;
        this.description = description;
    }

    public Event(String nom, LocalDate date, Time heureDebut, Time getHeureFin, int numParticipants, String description) {
        this.nom = nom;
        this.date = date;
        this.heureDebut = heureDebut;
        this.getHeureFin = getHeureFin;
        this.numParticipants = numParticipants;
        this.description = description;
    }

    public Event(String nom, LocalDate date, Time heureDebut, Time getHeureFin, int numParticipants) {
        this.nom = nom;
        this.date = date;
        this.heureDebut = heureDebut;
        this.getHeureFin = getHeureFin;
        this.numParticipants = numParticipants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return getHeureFin;
    }

    public void setHeureFin(Time getHeureFin) {
        this.getHeureFin = getHeureFin;
    }

    public int getNumParticipants() {
        return numParticipants;
    }

    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", date=" + date +
                ", heureDebut=" + heureDebut +
                ", getHeureFin=" + getHeureFin +
                ", numParticipants=" + numParticipants +
                ", description='" + description + '\'' +
                '}';
    }


}
