package com.esprit.pilatespulse.pilatespulsev3.Services;

import com.esprit.pilatespulse.pilatespulsev3.Models.Participation;
import com.esprit.pilatespulse.pilatespulsev3.Models.Session;
import com.esprit.pilatespulse.pilatespulsev3.Utils.MyDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParticipationService {
    private Connection cnx = MyDB.getInstance().getCnx();

    public void addParticipation(Participation participation) {
        String query = "INSERT INTO participation (event_id, user_id, session_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, participation.getEvent_id());
            statement.setInt(2, participation.getUser_id());
            statement.setInt(3, participation.getSession_id());
            statement.executeUpdate();
            System.out.println("Participation added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteParticipation(int eventId, int userId) {
        String query = "DELETE FROM participation WHERE event_id = ? AND user_id = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, eventId);
            statement.setInt(2, userId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Participation deleted successfully.");
            } else {
                System.out.println("Participation not found for deletion.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Participation> displayParticipation() {
        List<Participation> participations = new ArrayList<>();
        String query = "SELECT * FROM participation";
        try (PreparedStatement statement = cnx.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Participation participation = new Participation();
                participation.setEvent_id(resultSet.getInt("event_id"));
                participation.setUser_id(resultSet.getInt("user_id"));
                participation.setSession_id(resultSet.getInt("session_id"));
                participations.add(participation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participations;
    }


    public void addParticipationToAllSessions(int eventId, int userId) {
        List<Session> sessions = new ArrayList<>();
        String query = "SELECT * FROM `participation`";
        for (Session session : sessions) {
            addParticipation(new Participation(eventId, userId, session.getSessionID()));
        }
        System.out.println("Participation added to all sessions successfully.");
    }

    private List<Integer> getSessionIdsForEvent(int eventId) {
        List<Integer> sessionIds = new ArrayList<>();
        String query = "SELECT session_id FROM sessions WHERE event_id = ?";

        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, eventId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    sessionIds.add(resultSet.getInt("session_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sessionIds;
    }


}
