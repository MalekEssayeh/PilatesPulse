package user.Services;

import user.Interfaces.userInterface;
import user.Models.Promo;
import user.Models.user;
import user.Utils.MyConnection;
import java.sql.Date;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PromoService implements userInterface<Promo> {
    private Connection cnx;

    public PromoService(){cnx= MyConnection.getInstance().getCnx();}


    @Override
    public void add2(Promo promo) {
        try {
            // Update isActive tekhou false ken date t3addet
            promo.setActive(promo.getValidite().toLocalDate().isAfter(LocalDate.now()));
            String req = "INSERT INTO `promo`(`pourcentage`, `validite`, `isActive`, `id`) VALUES ( ?, ?, ?, ?)";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setFloat(1, promo.getPourcentage());
            pstmt.setDate(2, promo.getValidite());
            pstmt.setBoolean(3, promo.isActive());
            pstmt.setInt(4, promo.getId());

            pstmt.executeUpdate();

            System.out.println("Promo Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Promo promo) {
        try {
            String req = "UPDATE `promo` SET `pourcentage` = ?, `isActive` = ?, `validite` = ?, `id` = ? WHERE `code` = ?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setDouble(1, promo.getPourcentage());
            pstmt.setBoolean(2, promo.isActive());
            pstmt.setDate(3, promo.getValidite());
            pstmt.setInt(4, promo.getId());
            pstmt.setInt(5, promo.getCode());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Promo Updated successfully!");
            } else {
                System.out.println("No promo found with the provided ID.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int code) {
        try {
            String req = "DELETE FROM `promo` WHERE code = ?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setInt(1, code);
            pstmt.executeUpdate();
            System.out.println("Discount deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Promo> show() {

        List<Promo> promoList = new ArrayList<>();
        try {
            String req = "SELECT * FROM `promo`";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Promo u=new Promo();
                u.setCode(rs.getInt(1));
                u.setPourcentage(rs.getFloat(2));
                u.setValidite(rs.getDate(3));
                u.setActive(rs.getBoolean(4));
                u.setId(rs.getInt(5));

                promoList.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return promoList;
    }


    public List<Promo> search(String keyword) {
        return null;
    }

    public List<Promo> search2(float percentage) {
        List<Promo> promoList = new ArrayList<>();
        try {
            String req = "SELECT * FROM `promo` WHERE `pourcentage` = ?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setFloat(1, percentage);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Promo promo = new Promo();
                promo.setCode(rs.getInt("code"));
                promo.setPourcentage(rs.getFloat("pourcentage"));
                promo.setValidite(rs.getDate("validite"));
                promo.setActive(rs.getBoolean("isActive"));
                promo.setId(rs.getInt("id"));

                promoList.add(promo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return promoList;
    }
    public List<Promo> searchByUserId(int userId) {
        List<Promo> promoList = new ArrayList<>();
        try {
            String req = "SELECT * FROM `promo` WHERE `id` = ?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Promo promo = new Promo();
                promo.setCode(rs.getInt("code"));
                promo.setPourcentage(rs.getFloat("pourcentage"));
                promo.setValidite(rs.getDate("validite"));
                promo.setActive(rs.getBoolean("isActive"));
                promo.setId(rs.getInt("id"));

                promoList.add(promo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return promoList;
    }



    @Override
    public List<Promo> filterByName(String keyword) {
        return null;
    }
    public List<Promo> filterByPercentage(float percentage) {
        List<Promo> promoList = new ArrayList<>();
        try {
            String req = "SELECT * FROM `promo` WHERE `pourcentage` = ?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setFloat(1, percentage);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Promo promo = new Promo();
                promo.setCode(rs.getInt("code"));
                promo.setPourcentage(rs.getFloat("pourcentage"));
                promo.setValidite(rs.getDate("validite"));
                promo.setActive(rs.getBoolean("isActive"));
                promo.setId(rs.getInt("id"));

                promoList.add(promo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return promoList;
    }



    public boolean login(String email, String password) {
        return false;
    }
}
