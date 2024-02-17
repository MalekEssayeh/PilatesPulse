package PilatesPulse.Services;
import PilatesPulse.Interfaces.crudInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import PilatesPulse.Models.Exercice;
import PilatesPulse.Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExerciceService implements crudInterface<Exercice> {
    Connection cnx = Connexion.getInstance().getCnx();



    @Override
    public void add(Exercice p) {
        try {

            String req = "INSERT INTO `Exercice`(`nomExercice`, `idCoach`, `DifficulteExercice`, `EvaluationExercice`, `Muscle`, `Demonstration`,`IDexercice`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, p.getNomExercice());
            ps.setInt(   2, p.getIdCoach());
            ps.setString(3, p.getDifficulteExercice());
            ps.setInt(   4, p.getEvaluationExercice());
            ps.setString(5, p.getMuscle());
            ps.setString(6, p.getDemonstration());
            ps.setInt(7, p.getIdExercice());



            ps.executeUpdate();
            System.out.println("Exercice Added Successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }




    @Override
    public ObservableList<Exercice> fetch() {
        ObservableList<Exercice> Exercices = FXCollections.observableArrayList();
        try {

            String req = "SELECT * FROM Exercice";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Exercice p = new Exercice();
                p.setIdExercice(rs.getInt(1));
                p.setNomExercice(rs.getString(2));
                p.setIdCoach(rs.getInt(3));
                p.setDifficulteExercice(rs.getString(4));
                p.setEvaluationExercice(rs.getInt(5));
                p.setMuscle(rs.getString(6));
                p.setDemonstration(rs.getString(7));

                Exercices.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Exercices;
    }

    @Override
    public void update(Exercice p,String a) {
        try {
            String req ="UPDATE `Exercice` SET `Demonstration`= ? WHERE nomExercice = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, a);
            ps.setString(2,  p.getNomExercice());
            ps.executeUpdate();
            System.out.println("Exercice updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    @Override
    public void delete(int p) {
        try {
            String req ="DELETE FROM `Exercice`  WHERE IdExercice = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p);
            ps.executeUpdate();
            System.out.println("Exercice Deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public List<Exercice> rechercheExercice(int id) {
        List<Exercice> Exercices = new ArrayList<>();
        try {

            String req = "SELECT * FROM Exercice WHERE IDexercice LIKE CONCAT(?, '%')";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Exercice p = new Exercice();
                p.setIdExercice(rs.getInt(1));
                p.setNomExercice(rs.getString(2));
                p.setIdCoach(rs.getInt(3));
                p.setDifficulteExercice(rs.getString(4));
                p.setEvaluationExercice(rs.getInt(5));
                p.setMuscle(rs.getString(6));
                p.setDemonstration(rs.getString(7));

                Exercices.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Exercices;
    }

    public List<Exercice> filtreExercice(String Muscle) {
        List<Exercice> Exercices = new ArrayList<>();
        try {

            String req = "SELECT * FROM Exercice WHERE Muscle = ?";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Exercice p = new Exercice();
                p.setIdExercice(rs.getInt(1));
                p.setNomExercice(rs.getString(2));
                p.setIdCoach(rs.getInt(3));
                p.setDifficulteExercice(rs.getString(4));
                p.setEvaluationExercice(rs.getInt(5));
                p.setMuscle(rs.getString(6));
                p.setDemonstration(rs.getString(7));

                Exercices.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Exercices;}

    public void Edit(int e,String nomExercice, int idCoach, String difficulteExercice, int evaluationExercice, String muscle, String demonstration) {
        try {
            String req = "UPDATE `Exercice` SET `NomExercice`=?, `IdCoach`=?, `DifficulteExercice`=?, `EvaluationExercice`=?, `Muscle`=?, `Demonstration`=? WHERE `IDExercice`=?";
            PreparedStatement ps = cnx.prepareStatement(req);

            // Set values for each parameter
            ps.setString(1, nomExercice);
            ps.setInt(2, idCoach);
            ps.setString(3, difficulteExercice);
            ps.setInt(4, evaluationExercice);
            ps.setString(5, muscle);
            ps.setString(6, demonstration);
            ps.setInt(7, e);

            // Execute the update
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Exercice updated successfully!");
            } else {
                System.out.println("Failed to update Exercice. No matching record found.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }}