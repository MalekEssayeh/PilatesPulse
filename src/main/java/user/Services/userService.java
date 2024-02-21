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



public class userService implements userInterface<user> {
    private Connection cnx;
    public userService(){
        cnx=MyConnection.getInstance().getCnx();
    }
    @Override
    public void add(user user) {
        try {
            String req = "INSERT INTO `user`(`nom`, `prenom`, `mdp`, `mail`) VALUES ('"+ user.getNom() +"',"+user.getPrenom()+","+user.getMdp()+","+user.getMail()+")";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("User Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void add2(user user) {
        try {
            String req = "INSERT INTO `user`(`nom`, `prenom`, `mdp`, `mail`) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setString(1, user.getNom());
            pstmt.setString(2, user.getPrenom());
            pstmt.setString(3, user.getMdp());
            pstmt.setString(4, user.getMail());

            pstmt.executeUpdate();

            System.out.println("User Added successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(user user) {
        try {
            String req = "UPDATE `user` SET  `nom`=?, `prenom`=?, `mail`=?, `mdp`=? WHERE id=?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setString(1, user.getNom());
            pstmt.setString(2, user.getPrenom());
            pstmt.setString(3, user.getMail());
            pstmt.setString(4, user.getMdp());
            pstmt.setInt(5, user.getId());
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
                user u=new user();
                u.setId(rs.getInt(1));
                u.setNom(rs.getString(2));
                u.setPrenom(rs.getString(3));
                u.setMdp(rs.getString(4));
                u.setMail(rs.getString(5));

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

                userList.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public List<user> filterByName(String keyword) {
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

            // If the result set has any rows, it means the user exists with the given email and password
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        // Return false if any exception occurs or if no user is found with the given credentials
        return false;
    }

}
