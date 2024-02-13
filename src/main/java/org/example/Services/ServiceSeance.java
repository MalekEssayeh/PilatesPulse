package org.example.Services;

import com.mysql.cj.x.protobuf.Mysqlx;
import org.example.Interfaces.IServiceSeance;
import org.example.Models.Seance;
import org.example.Utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceSeance implements IServiceSeance<Seance> {

    Connection cnx = MyDB.getInstance().getCnx();


    @Override
    public void ajouter(Seance seance) {
        try {

            String request = "INSERT INTO `seance`(`duration`, `coach_id`, `event_id`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, seance.getDuration());
            ps.setInt(2, seance.getCoach_id());
            ps.setInt(3, seance.getEvent_id());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Seance added successfully");
            } else {
                System.out.println("Failed to add seance");
            }



        }catch (SQLException exception){

            exception.printStackTrace();

        }

    }

    @Override
    public List<Seance> afficher() {
        List<Seance> seances = new ArrayList<>();

        try {

            String req = "SELECT * FROM Seance";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Seance seance = new Seance();
                seance.setId(rs.getInt(1));
                seance.setDuration(rs.getInt(2));
                seance.setCoach_id(rs.getInt(3));
                seance.setEvent_id(rs.getInt(4));

                seances.add(seance);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return seances;
    }

    @Override
    public void modifier(Seance seance) {

        try {

            String request = "UPDATE Seance SET duration = ? WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1, seance.getDuration());

            ps.executeUpdate();
            System.out.println("Seance modifiée !");

        } catch (SQLException ex) {
           ex.printStackTrace();
        }

    }

    @Override
    public void supprimer(int id) {

        try{
            String request = "delete from Seance where id = ? ";
            PreparedStatement ps = cnx.prepareStatement(request);
            ps.setInt(1,id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }

    }

    public List<Seance> getByID(int id) throws SQLException{

        String request = "select * from Seance where id = ?";
        PreparedStatement ps = cnx.prepareStatement(request);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        List<Seance> seances = new ArrayList<>();
        while (rs.next()){
            Seance s = new Seance();
            s.setId(rs.getInt("id"));
            s.setDuration(rs.getInt("Duration"));
            s.setCoach_id(rs.getInt("coach_id"));

            seances.add(s);
        }

        return seances;
    }

    public List<Seance> getByCoachID(int cid) throws SQLException{

        String request = "select * from Seance where coach_id = ?";
        PreparedStatement ps = cnx.prepareStatement(request);
        ps.setInt(1, cid);
        ResultSet rs = ps.executeQuery();
        List<Seance> seances = new ArrayList<>();
        while (rs.next()){
            Seance s = new Seance();
            s.setId(rs.getInt("id"));
            s.setDuration(rs.getInt("Duration"));
            s.setCoach_id(rs.getInt("coach_id"));

            seances.add(s);
        }

        return seances;
    }
}
