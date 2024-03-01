package org.esprit.services;

import org.esprit.interfaces.IServiceC;
import org.esprit.interfaces.IServiceL;
import org.esprit.models.Commande;
import org.esprit.models.Livraison;
import org.esprit.utils.Connexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivraisonService implements IServiceL<Livraison> {


    Connection connection = Connexion.getInstance().getConnection();

    public LivraisonService() throws SQLException {

    }
    Commande c = new Commande();
    Livraison l = new Livraison();


    @Override
    public void ajouter(Livraison l) {
        try {
            String req = "INSERT INTO `livraison`(`methodePay`, `adresseLiv`, `dateLiv`,`phone`) VALUES (?,?,?,?)";
//            String req2 = "SELECT idLiv FROM livraison ORDER BY idLiv DESC LIMIT 1";


            try (PreparedStatement ps = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, l.getMethodePay());
                ps.setString(2, l.getAdresseLiv());
                ps.setObject(3, java.sql.Date.valueOf(l.getDateLiv())); // Convert LocalDate to java.sql.Date
                ps.setInt(4, l.getPhone());
                ps.executeUpdate();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        l.setIdLiv(generatedKeys.getInt(1));
                    }
                }
            }

            // Adding cmd list
            String req1 = "INSERT INTO `listecommande`(`idLiv`, `idCmd`) VALUES (?, ?)";
            try (PreparedStatement ps1 = connection.prepareStatement(req1)) {
                for (Commande c : l.getListCommande()) {
                    ps1.setInt(1, l.getIdLiv());
                    ps1.setInt(2, c.getIdCmd());
                    ps1.executeUpdate();
                }
            }

            System.out.println("Delivery Added Successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error adding delivery to the database: " + ex.getMessage());

        }
    }

    // Adding cmd list
    String req1 = "INSERT INTO `listecommande`(`idLiv`, `idCmd`) VALUES (?, ?)";
             (PreparedStatement ps1 = connection.prepareStatement(req1)) {
        for (Commande c : l.getListCommande()) {
            // Note: Do not set idCmd, let the database handle it
            ps1.setInt(1, l.getIdLiv());
            // Do not set ps1.setInt(2, c.getIdCmd());
            ps1.executeUpdate();
        }
    }

//    @Override
//    public void ajouter(Livraison l) {
//        try {
//            String req = "INSERT INTO `livraison`(`methodePay`, `adresseLiv`, `dateLiv`,`phone`) VALUES (?,?,?,?)";
//            String req2 = "SELECT idLiv FROM livraison ORDER BY idLiv DESC LIMIT 1";
//            String req1 = "INSERT INTO `listecommande`(`idLiv`,`idCmd` ) VALUES (?,?)";
//
//

//            try (PreparedStatement ps = connection.prepareStatement(req)) {
//                ps.setString(1, l.getMethodePay());
//                ps.setString(2, l.getAdresseLiv());
//                ps.setDate(3, l.getDateLiv());
//                ps.setInt(2, l.getPhone());
//
//                ps.executeUpdate();
//            }
//
//            //Getting liv id
//            try (PreparedStatement ps2 = connection.prepareStatement(req2)) {
//                try (ResultSet rs = ps2.executeQuery()) {
//                    if (rs.next()) {
//                        l.setIdLiv(rs.getInt(1));
//
//                    }
//                }
//            }
//
//            // Adding cmd list
//            try (PreparedStatement ps1 = connection.prepareStatement(req1)) {
//                for (Commande c : l.getListCommande()){
//                    ps1.setInt(1, l.getIdLiv());
//                    ps1.setInt(2, c.getIdCmd());
//                    ps1.executeUpdate();
//                }
//            }
//
//            System.out.println("Delivery Added Successfully!");
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//
//        }



    //fetch

    public List<Livraison> fetchLivraisons() {
        List<Livraison> Livraisons = new ArrayList<>();

        try {
            String req = "SELECT * FROM livraison";
            String req1 = "SELECT * FROM listecommande WHERE idLiv = ?";
            String req2 = "SELECT * FROM commande WHERE idCmd = ?";

            PreparedStatement ps = connection.prepareStatement(req1);
            PreparedStatement ex = connection.prepareStatement(req2);

            Statement st = this.connection.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Livraison l = new Livraison();
                l.setIdLiv(rs.getInt(1));
                l.setMethodePay(rs.getString(2));
                l.setAdresseLiv(rs.getString(3));
                l.setDateLiv(rs.getDate(4).toLocalDate()); // Convert java.sql.Date to LocalDate
                l.setPhone(rs.getInt(5));
                ps.setInt(1, l.getIdLiv());

                ResultSet rs1 = ps.executeQuery();

                List<Commande> commandes = new ArrayList<>();
                while (rs1.next()) {
                    int a = rs1.getInt(2);
                    ex.setInt(1, a);
                    ResultSet rs2 = ex.executeQuery();
                    while (rs2.next()) {
                        Commande c = new Commande();
                        c.setIdCmd(rs2.getInt(1));  // Fix here
                        c.setIdUser(rs2.getInt(2));
                        c.setTotal(rs2.getInt(3));
                        c.setCodePromo(rs2.getString(4));
                        c.setNomProd(rs2.getString(5));

                        commandes.add(c);
                }
                l.setListCommande(commandes);
                Livraisons.add(l);
            }

        }
        return Livraisons;
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        @Override
    public void modifier(Livraison l, String a) {
        try{
        String req ="UPDATE `livraison` SET `adresseLiv`= ? WHERE idLiv = ?";
        PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,a);
            ps.setInt(2,  l.getIdLiv());
            ps.executeUpdate();
            System.out.println("Delivery updated with success!");

    }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addList(Livraison l,Commande c){
        try {
            String req ="INSERT INTO `listecommande`(`idLiv`,`idCmd` ) VALUES (?,?) ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(2, c.getIdCmd());
            ps.setInt(1,  l.getIdLiv());
            ps.executeUpdate();
            System.out.println("ORDER added to list successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimer(int t) {
        try {
            String req ="DELETE FROM `livraison`  WHERE idLiv = ?";
            String req1 ="DELETE FROM `listecommande`  WHERE IdLiv = ?";

            PreparedStatement ps = connection.prepareStatement(req);
            PreparedStatement ps2 = connection.prepareStatement(req1);

            ps.setInt(1, t);
            ps2.setInt(1, t);

            ps.executeUpdate();
            ps2.executeUpdate();

            System.out.println("Delivery Deleted Successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }



    public List<Livraison> rechercheLivraison(int idLiv) {
        List<Livraison> Livraisons = new ArrayList<>();
        try {

            String req = "SELECT * FROM livraison WHERE idLiv LIKE CONCAT(?, '%')";
            String req1 = "SELECT * FROM listeCommande WHERE idLiv = ?";
            String req2 = "SELECT * FROM commande WHERE idCmd = ?";

            //Creating prepared Statement
            PreparedStatement ps = connection.prepareStatement(req1);
            PreparedStatement ex = connection.prepareStatement(req2);
            PreparedStatement st = connection.prepareStatement(req);

            st.setInt(1, idLiv);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Livraison l = new Livraison();
                l.setIdLiv(rs.getInt(1));
                l.setDateLiv(rs.getDate(4).toLocalDate()); // Convert java.sql.Date to LocalDate
                l.setAdresseLiv(rs.getString(3));
                l.setMethodePay(rs.getString(4));
                l.setPhone(rs.getInt(5));
                ps.setInt(1,l.getIdLiv());

                ResultSet rs1 = ps.executeQuery();

                List<Commande> commandes = new ArrayList<>();
                while (rs1.next()) {
                    int a = rs1.getInt(2);
                    ex.setInt(1, a);
                    ResultSet rs2 = ex.executeQuery();
                    while (rs2.next()) {
                        Commande c = new Commande();
                        c.setIdCmd(rs.getInt(1));
                        c.setIdUser(rs.getInt(2));
                        c.setTotal(rs.getInt(3));
                        c.setCodePromo(rs.getString(4));
                        c.setNomProd(rs.getString(5));

                        commandes.add(c);
                    }
                }
                l.setListCommande(commandes);
                Livraisons.add(l);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Livraisons;
    }




    public List<Livraison> filtreLivraison(String adressLiv) {
        List<Livraison> Livraisons = new ArrayList<>();
        try {

            String req = "SELECT * FROM livraison WHERE adressLiv = ?";
            String req1 = "SELECT * FROM listecommande WHERE idLiv = ?";
            String req2 = "SELECT * FROM Commande WHERE idCmd = ?";


            PreparedStatement ps = connection.prepareStatement(req1);
            PreparedStatement ex = connection.prepareStatement(req2);
            PreparedStatement st = connection.prepareStatement(req);

            st.setString(1,adressLiv);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Livraison l = new Livraison();
                l.setIdLiv(rs.getInt(1));
                l.setMethodePay(rs.getString(2));
                l.setAdresseLiv(rs.getString(3));
                l.setDateLiv(rs.getDate(4).toLocalDate()); // Convert java.sql.Date to LocalDate
                l.setPhone(rs.getInt(5));
                ps.setInt(1,l.getIdLiv());

                ResultSet rs1 = ps.executeQuery();



                List<Commande> commandes = new ArrayList<>();
                while (rs1.next()) {
                    int j = rs1.getInt(2);
                    ex.setInt(1, j);
                    ResultSet rs2 = ex.executeQuery();
                    while (rs2.next()) {
                        Commande c = new Commande();
                        c.setIdCmd(rs.getInt(1));
                        c.setIdUser(rs.getInt(2));

                        c.setTotal(rs.getInt(3));
                        c.setCodePromo(rs.getString(4));
                        c.setNomProd(rs.getString(5));

                        commandes.add(c);
                    }
                }
                l.setListCommande(commandes);
                Livraisons.add(l);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Livraisons;
    }
}
