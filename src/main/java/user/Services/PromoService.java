package user.Services;

import user.Interfaces.userInterface;
import user.Models.Promo;
import user.Models.user;
import user.Utils.MyConnection;
import java.sql.Date;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromoService implements userInterface<Promo> {
    private Connection cnx;

    public PromoService(){cnx= MyConnection.getInstance().getCnx();}
    @Override
    public void add(Promo promo) {

    }

    @Override
    public void add2(Promo promo) {
        try {
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
    public void update(Promo promo) {}


    @Override
    public void update2(Promo promo) {
        try {
            String req = "UPDATE `promo` SET `pourcentage` = ?, `isActive` = ?, `validite` = ? WHERE `id` = ?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setDouble(1, promo.getPourcentage());
            pstmt.setBoolean(2, promo.isActive());
            pstmt.setDate(3, promo.getValidite());
            pstmt.setInt(4, promo.getId());

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
                u.setId(rs.getInt(4));

                promoList.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return promoList;
    }

    @Override
    public List<Promo> search(String keyword) {
        return null;
    }

    @Override
    public List<Promo> filterByName(String keyword) {
        return null;
    }


    public boolean login(String email, String password) {
        return false;
    }
}
