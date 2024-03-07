package tn.PilatePulse.services;

import tn.PilatePulse.interfaces.InterfaceRating;
import tn.PilatePulse.model.RatingModel;
import tn.PilatePulse.util.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            String req = "UPDATE `rating` SET `idUser`=?, `nbStars`=? WHERE `idProduct`=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, rating.getIdUser());
            ps.setDouble(2, rating.getNbStars());
            ps.setInt(3, rating.getIdProduct()); // Set idProduct in the WHERE clause

            ps.executeUpdate();

            System.out.println("Rating updated");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


    @Override
    public boolean ratingExists(int idProduct, int idUser) {
        try {
            String query = "SELECT * FROM `rating` WHERE `idProduct` = ? AND `idUser` = ?";
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, idProduct);
            ps.setInt(2, idUser);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return false;
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
                r.setIdUser(rs.getInt(3));
                r.setNbStars((int) rs.getDouble(4));

                ratings.add(r);
            }
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return ratings;    }

    @Override
    public Map<String, Double> fetchProductRatings() {
        Map<String, Double> productRatings = new HashMap<>();

        String query = "SELECT p.nameProduct, r.nbStars FROM rating r JOIN product p ON r.idProduct = p.idProduct";

        try (PreparedStatement statement = cnx.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String productName = resultSet.getString("nameProduct");
                double rating = resultSet.getDouble("nbStars");
                productRatings.put(productName, rating);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return productRatings;
    }

    @Override
    public Map<String, Double> fetchAverageProductRatings() {
        Map<String, Double> productRatings = new HashMap<>();

        String query = "SELECT r.idProduct, AVG(r.nbStars) AS avgRating " +
                "FROM rating r " +
                "GROUP BY r.idProduct";

        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("idProduct");
                double avgRating = rs.getDouble("avgRating");
                productRatings.put(getProductName(productId), avgRating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productRatings;
    }

    private String getProductName(int productId) throws SQLException {
        String query = "SELECT nameProduct FROM product WHERE idProduct = ?";
        try (PreparedStatement ps = cnx.prepareStatement(query)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("nameProduct");
            }
        }
        throw new SQLException("Product with ID " + productId + " not found.");
    }


}
