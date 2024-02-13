package org.example.Services;

import org.example.Interfaces.IServiceEvent;
import org.example.Models.Event;
import org.example.Models.Seance;
import org.example.Utils.MyDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServiceEvent implements IServiceEvent<Event> {

    Connection cnx = MyDB.getInstance().getCnx();

    @Override
    public void ajouter(Event event) {


        try {
            String query = "INSERT INTO event (nom, date, heureDebut, heureFin, numParticipants, description) VALUES (?, ?, ?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, event.getNom());
            ps.setDate(2, Date.valueOf(event.getDate()));
            ps.setTime(3, event.getHeureDebut());
            ps.setTime(4, event.getHeureFin());
            ps.setInt(5, event.getNumParticipants());
            ps.setString(6, event.getDescription());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Event added successfully");
            } else {
                System.out.println("Failed to add event");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    ;

    @Override
    public List<Event> afficher()  {
        List<Event> events = new ArrayList<>();

        try {
            String query = "SELECT * FROM event";
            PreparedStatement ps = cnx.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Event event = new Event();
                event.setId(rs.getInt("id"));
                event.setNom(rs.getString("nom"));
                event.setDate(rs.getDate("date").toLocalDate()); // Convert to LocalDate
                event.setHeureDebut(rs.getTime("heureDebut")); // Convert to LocalTime
                event.setHeureFin(rs.getTime("heureFin")); // Convert to LocalTime
                event.setNumParticipants(rs.getInt("numParticipants"));
                event.setDescription(rs.getString("description"));

                events.add(event);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return events;

    }

    ;

    @Override
    public void modifier(Event event, int id) {
        try {
            String query = "UPDATE event SET `nom`=? ,`date`=? ,`heureDebut`=? ,`heureFin`=? ,`numParticipants`=? ,`description`=? WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, event.getNom());
            ps.setDate(2, Date.valueOf(event.getDate()));
            ps.setTime(3, event.getHeureDebut());
            ps.setTime(4, event.getHeureFin());
            ps.setInt(5, event.getNumParticipants());
            ps.setString(6, event.getDescription());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Event modified successfully");
            } else {
                System.out.println("Failed to modify event");
            }

        } catch (SQLException ex) {
            System.out.println("Error in modifying event: " + ex);
        }
    }

    ;

    @Override
    public void supprimer(int id) {
        try {
            String query = "DELETE FROM event WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Event deleted successfully");
            } else {
                System.out.println("Failed to delete event");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    ;


    public List<Event> getByID(int id) {
        List<Event> events = new ArrayList<>();
        try {
            String request = "select * from event where id = ?";
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setDate(rs.getDate("date").toLocalDate());
                //e.setHeureDebut(LocalDateTime.from(rs.getTime("heureDebut").toLocalTime())); // Convert to LocalTime
                e.setHeureDebut(rs.getTime("heureDebut"));
                e.setHeureFin(rs.getTime("heureFin"));
                e.setDescription(rs.getString("description"));
                e.setNumParticipants(rs.getInt("numParticipants"));

                events.add(e);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return events;
    }

    public List<Event> getByName(String nom) {
        List<Event> events = new ArrayList<>();
        try {
            String request = "select * from event where nom = ?";
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setDate(rs.getDate("date").toLocalDate());
                e.setHeureDebut(rs.getTime("heureDebut")); // Convert to LocalTime
                e.setHeureFin(rs.getTime("heureFin"));
                e.setDescription(rs.getString("description"));
                e.setNumParticipants(rs.getInt("numParticipants"));

                events.add(e);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return events;
    }

    public List<Event> getByDate(Date date) {
        List<Event> events = new ArrayList<>();
        try {
            String request = "select * from event where date = ?";
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Event e = new Event();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setDate(rs.getDate("date").toLocalDate());
                e.setHeureDebut(rs.getTime("heureDebut")); // Convert to LocalTime
                e.setHeureFin(rs.getTime("heureFin"));
                e.setDescription(rs.getString("description"));
                e.setNumParticipants(rs.getInt("numParticipants"));

                events.add(e);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return events;
    }


}
