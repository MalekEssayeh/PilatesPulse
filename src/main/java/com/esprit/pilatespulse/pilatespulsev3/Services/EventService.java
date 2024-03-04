package com.esprit.pilatespulse.pilatespulsev3.Services;

import com.esprit.pilatespulse.pilatespulsev3.Interfaces.IEventService;
import com.esprit.pilatespulse.pilatespulsev3.Models.Event;
import com.esprit.pilatespulse.pilatespulsev3.Utils.MyDB;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventService implements IEventService<Event> {

    Connection cnx = MyDB.getInstance().getCnx();

    @Override
    public void addNewEvent(Event event) {
        try {
            String query = "INSERT INTO event (name, date, nbrParticipants, description, coach_id, imageURL) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, event.getName());
            ps.setDate(2, Date.valueOf(event.getDate()));
            ps.setInt(3, event.getNbrParticipants());
            ps.setString(4, event.getDescription());
            ps.setInt(5, event.getCoachID());
            ps.setString(6, event.getImageURL());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Event added successfully");
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    event.setEventID(generatedKeys.getInt(1));
                }
            } else {
                System.out.println("Failed to add event");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Event> displayEvents() {
        List<Event> events = new ArrayList<>();

        try {
            String query = "SELECT * FROM event";
            PreparedStatement ps = cnx.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Event event = new Event();
                event.setEventID(rs.getInt("event_id"));
                event.setName(rs.getString("name"));
                event.setDate(rs.getDate("date").toLocalDate());
                event.setNbrParticipants(rs.getInt("nbrParticipants"));
                event.setDescription(rs.getString("description"));
                event.setCoachID(rs.getInt("coach_id"));

                events.add(event);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return events;
    }

    @Override
    public void updateEvent(Event event, int id) {
        try {
            String query = "UPDATE event SET `name`=?, `date`=?,  `nbrParticipants`=?, `description`=?, `coach_id`=? WHERE event_id = ?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, event.getName());
            ps.setDate(2, Date.valueOf(event.getDate()));
            ps.setInt(3, event.getNbrParticipants());
            ps.setString(4, event.getDescription());
            ps.setInt(5, event.getCoachID());
            ps.setInt(6, id);

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

    @Override
    public void deleteEvent(int id) {
        try {
        String query = "DELETE FROM event WHERE event_id = ?";
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

    public List<Event> getByID(int id) {
        List<Event> events = new ArrayList<>();
        try {
            String request = "SELECT * FROM event WHERE eventID = ?";
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Event e = new Event();
                e.setEventID(rs.getInt("eventID"));
                e.setName(rs.getString("name"));
                e.setDate(rs.getDate("date").toLocalDate());
                e.setNbrParticipants(rs.getInt("nbrParticipants"));
                e.setDescription(rs.getString("description"));
                e.setCoachID(rs.getInt("coachID"));

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
            String request = "SELECT * FROM event WHERE name = ?";
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Event e = new Event();
                e.setEventID(rs.getInt("eventID"));
                e.setName(rs.getString("name"));
                e.setDate(rs.getDate("date").toLocalDate());
                e.setNbrParticipants(rs.getInt("nbrParticipants"));
                e.setDescription(rs.getString("description"));
                e.setCoachID(rs.getInt("coachID"));

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
            String request = "SELECT * FROM event WHERE startDate = ?";
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Event e = new Event();
                e.setEventID(rs.getInt("eventID"));
                e.setName(rs.getString("name"));
                e.setDate(rs.getDate("date").toLocalDate());
                e.setNbrParticipants(rs.getInt("nbrParticipants"));
                e.setDescription(rs.getString("description"));
                e.setCoachID(rs.getInt("coachID"));

                events.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return events;
    }
}
