package com.esprit.pilatespulse.pilatespulsev3.Services;

import com.esprit.pilatespulse.pilatespulsev3.Interfaces.ISessionService;
import com.esprit.pilatespulse.pilatespulsev3.Models.Session;
import com.esprit.pilatespulse.pilatespulsev3.Utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionService implements ISessionService<Session> {

    Connection cnx = MyDB.getInstance().getCnx();

    @Override
    public void addSession(Session session) {
        try {
            String request = "INSERT INTO `session`(`name`, `description`, `duration`, `coach_id`, `event_id`, `date`) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(1, session.getName());
            ps.setString(2, session.getDescription());
            ps.setInt(3, session.getDuration());
            ps.setInt(4, session.getCoachID());
            ps.setInt(5, session.getEventID());
            ps.setDate(6, session.getDate());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Session added successfully");
            } else {
                System.out.println("Failed to add session");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Session> displaySessions() {
        List<Session> sessions = new ArrayList<>();

        try {
            String req = "SELECT * FROM session";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Session session = new Session();
                session.setSessionID(rs.getInt("sessionID"));
                session.setName(rs.getString("name"));
                session.setDescription(rs.getString("description"));
                session.setDuration(rs.getInt("duration"));
                session.setCoachID(rs.getInt("coachID"));
                session.setEventID(rs.getInt("eventID"));
                session.setDate(rs.getDate("date"));

                sessions.add(session);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return sessions;
    }

    @Override
    public void updateSession(Session session, int id) {
        try {
            String request = "UPDATE session SET name=?, description=?, duration=?, coach_id=?, event_id=?, date=? WHERE sessionID=?";
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setString(1, session.getName());
            ps.setString(2, session.getDescription());
            ps.setInt(3, session.getDuration());
            ps.setInt(4, session.getCoachID());
            ps.setInt(5, session.getEventID());
            ps.setDate(6, session.getDate());
            ps.setInt(7, session.getSessionID());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Session modified successfully");
            } else {
                System.out.println("Failed to modify session");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteSession(int id) {
        try {
            String request = "DELETE FROM session WHERE sessionID=?";
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Session deleted successfully");
            } else {
                System.out.println("Failed to delete session");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Session> getByID(int id) throws SQLException {
        String request = "SELECT * FROM session WHERE sessionID=?";
        PreparedStatement ps = cnx.prepareStatement(request);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        List<Session> sessions = new ArrayList<>();
        while (rs.next()) {
            Session s = new Session();
            s.setSessionID(rs.getInt("sessionID"));
            s.setName(rs.getString("name"));
            s.setDescription(rs.getString("description"));
            s.setDuration(rs.getInt("duration"));
            s.setCoachID(rs.getInt("coachID"));
            s.setEventID(rs.getInt("eventID"));
            s.setDate(rs.getDate("date"));

            sessions.add(s);
        }

        return sessions;
    }


}
