package tn.PilatePulse.services;

import tn.PilatePulse.interfaces.InterfaceRating;
import tn.PilatePulse.model.RatingModel;
import tn.PilatePulse.util.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingService implements InterfaceRating<RatingModel> {

    Connection cnx = MaConnexion.getInstance().getCnx();

    @Override
    public void addRating(RatingModel rating) {
        try {
            String req = "INSERT INTO `rating`(`idProduct`,`idUser`,`nbStars`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, rating.getIdProduct());
            ps.setInt(2, rating.getIdUser());
            ps.setDouble(3, rating.getNbStars());

            ps.executeUpdate();

            System.out.println("Rating submitted");


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateRating(RatingModel rating) {
        try {
            String req = "UPDATE `rating` SET `idProduct`=?,`idUser`=?,`nbStars`=? WHERE `idProduct`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, rating.getIdProduct());
            ps.setInt(2, rating.getIdUser());
            ps.setDouble(3, rating.getNbStars());


            ps.executeUpdate();

            System.out.println("Rating updated");
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    @Override
    public void removeRating(int id) {
        try {
            String req ="DELETE FROM `rating` WHERE idRating = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
            System.out.println("Rating removed");
        }catch (SQLException exp){
            exp.printStackTrace();
        }
    }


    @Override
    public List<RatingModel> fetchRating() {
        List<RatingModel> ratings = new ArrayList<>();
        try{
            String req = "SELECT * FROM rating";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()){
                RatingModel r = new RatingModel();
                r.setIdRating(rs.getInt(1));
                r.setIdProduct(rs.getInt(2));
                r.setIdUser(1);
                r.setNbStars((int) rs.getDouble(4));

                ratings.add(r);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return ratings;    }
}
