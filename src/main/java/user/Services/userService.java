package user.Services;
import user.Interfaces.userInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import user.Models.user;
import user.Utils.MyConnection;
import java.sql.Date;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class userService implements userInterface<user> {
    private Connection cnx;
    public userService(){
        cnx=MyConnection.getInstance().getCnx();
    }

    public void add2(user user) {
        try {
            String req = "INSERT INTO `user`(`nom`, `prenom`, `mdp`, `mail`, `role`) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setString(1, user.getNom());
            pstmt.setString(2, user.getPrenom());
            pstmt.setString(3, user.getMdp());
            pstmt.setString(4, user.getMail());
            pstmt.setString(5, user.getRole());

            pstmt.executeUpdate();

            System.out.println("User added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(user user) {
        try {
            String req = "UPDATE `user` SET `nom`=?, `prenom`=?, `mdp`=?, `mail`=?, `role`=? WHERE `id`=?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setString(1, user.getNom());
            pstmt.setString(2, user.getPrenom());
            pstmt.setString(3, user.getMdp());
            pstmt.setString(4, user.getMail());
            pstmt.setString(5, user.getRole());
            pstmt.setInt(6, user.getId());
            pstmt.executeUpdate();
            System.out.println("User updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void delete(int id) {
        try {
            String req = "DELETE FROM `user` WHERE id = ?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("User deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<user> show() {
        List<user> userList = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user`";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user u = new user();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setMdp(rs.getString("mdp"));
                u.setMail(rs.getString("mail"));
                u.setRole(rs.getString("role"));

                userList.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public List<user> search(String keyword) {
        List<user> userList = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user` WHERE `nom` LIKE ? OR `prenom` LIKE ?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user u = new user();
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setMdp(rs.getString(4));
                u.setMail(rs.getString(5));
                u.setRole(rs.getString(6));

                userList.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }
    public List<user> search2(float percentage){return null;}

    public List<user> filterByName(String keyword) {
        List<user> userList = new ArrayList<>();
        try {
            String req = "SELECT * FROM `user` WHERE `nom` LIKE ? OR `prenom` LIKE ?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user u = new user();
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setMdp(rs.getString(4));
                u.setMail(rs.getString(5));
                u.setRole(rs.getString(6));

                userList.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }


    public boolean login(String mail, String mdp) {
        try {
            String req = "SELECT * FROM `user` WHERE `mail` = ? AND `mdp` = ?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setString(1, mail);
            pstmt.setString(2, mdp);
            ResultSet rs = pstmt.executeQuery();
            // resultat aandou rows donc user mrigel w mawjoud
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Return false ken exception wela user ghalet/mafamech
        return false;
    }
    public boolean isAdmin(String mail) {
        try {
            String req = "SELECT * FROM `user` WHERE `mail` = ? AND `role` = 'admin'";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setString(1, mail);
            ResultSet rs = pstmt.executeQuery();
            // resultat aandou rows donc user mrigel w admin
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Return false ken exception wela user ghalet/mafamech
        return false;
    }
    public boolean emailExists(String email) {
        try {
            String query = "SELECT COUNT(*) FROM `user` WHERE `mail` = ?";
            PreparedStatement pstmt = cnx.prepareStatement(query);
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
