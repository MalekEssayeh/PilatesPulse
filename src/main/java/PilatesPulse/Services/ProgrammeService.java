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
import PilatesPulse.Models.Programme;
import PilatesPulse.Utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProgrammeService implements crudInterface<Programme>{

        Connection cnx = Connexion.getInstance().getCnx();



        @Override
        public void add(Programme p) {
            try {
                String req = "INSERT INTO `programme`(`idCoachp`, `DureeProgramme`, `EvaluationProgramme`, `DifficulteProgramme`,`NomProgramme`) VALUES (?,?,?,?,?)";
                String req2 = "SELECT idprogramme FROM programme ORDER BY idprogramme DESC LIMIT 1";
                String req1 = "INSERT INTO `ListExercice`(`idProg`,`IDex` ) VALUES (?,?)";

                // Adding to prog table
                try (PreparedStatement ps = cnx.prepareStatement(req)) {
                    ps.setInt(1, p.getIdCoachp());
                    ps.setInt(2, p.getDureeProgramme());
                    ps.setInt(3, p.getEvaluationProgramme());
                    ps.setString(4, p.getDifficulteProgramme());
                    ps.setString(5, p.getNomProgramme());

                    ps.executeUpdate();
                }

                // Getting prog id
                try (PreparedStatement ps2 = cnx.prepareStatement(req2)) {
                    try (ResultSet rs = ps2.executeQuery()) {
                        if (rs.next()) {
                            p.setIdProgramme(rs.getInt(1));
                        }
                    }
                }

                // Adding ex list
                try (PreparedStatement ps1 = cnx.prepareStatement(req1)) {
                    for (Exercice e : p.getListExercice()) {
                        ps1.setInt(1, p.getIdProgramme());
                        ps1.setInt(2, e.getIdExercice());
                        ps1.executeUpdate();
                    }
                }

                System.out.println("Programme Added Successfully!");

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    public ObservableList<Programme> fetch() {
        ObservableList<Programme> Programmes = FXCollections.observableArrayList() ;
        try {
            String req = "SELECT * FROM Programme";
            String req1 = "SELECT * FROM listExercice WHERE idProg = ?";
            String req2 = "SELECT * FROM Exercice WHERE idExercice = ?";

            // Creating prepared statements
            PreparedStatement ps = cnx.prepareStatement(req1);
            PreparedStatement ex = cnx.prepareStatement(req2);

            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Programme p = new Programme();
                p.setIdProgramme(rs.getInt(1));
                p.setNomProgramme(rs.getString(2));
                p.setIdCoachp(rs.getInt(3));
                p.setDureeProgramme(rs.getInt(4));
                p.setEvaluationProgramme(rs.getInt(5));
                p.setDifficulteProgramme(rs.getString(6));
                ps.setInt(1, p.getIdProgramme());

                ResultSet rs1 = ps.executeQuery();

                List<Exercice> exercices = new ArrayList<>();
                while (rs1.next()) {
                    int a = rs1.getInt(2);
                    ex.setInt(1, a);
                    ResultSet rs2 = ex.executeQuery();
                    while (rs2.next()) {
                        Exercice exer = new Exercice();
                        exer.setIdExercice(rs2.getInt(1));
                        exer.setNomExercice(rs2.getString(2));
                        exer.setIdCoach(rs2.getInt(3));
                        exer.setDifficulteExercice(rs2.getString(4));
                        exer.setEvaluationExercice(rs2.getInt(5));
                        exer.setMuscle(rs2.getString(6));
                        exer.setDemonstration(rs2.getString(7));
                        exercices.add(exer);
                    }
                }
                p.setListExercice(exercices);
                Programmes.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Programmes;
    }


    @Override
    public void update(Programme p, String a) {
        try {
            String req ="UPDATE `Programme` SET `DifficulteProgramme`= ? WHERE nomProgramme = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, a);
            ps.setString(2,  p.getNomProgramme());
            ps.executeUpdate();
            System.out.println("Programme updated successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void Edit(int id,String NomProgramme,int DureeProgramme,List<Exercice> ex,List<Exercice> exup) {
        System.out.println(ex);
        System.out.println(exup);

    try {
            ExerciceService es=new ExerciceService();
            String req  = "UPDATE `Programme` SET `DureeProgramme`=?, `EvaluationProgramme`=?, `DifficulteProgramme`=?, `NomProgramme`=? WHERE idProgramme=?";
            String req1 ="INSERT INTO `ListExercice`(`idProg`,`IDex` ) VALUES (?,?)";
            String req2 ="DELETE FROM listexercice WHERE idprog = ? AND idex = ?";

        for(Exercice e:exup) {
            if(!ex.contains(e)){
                PreparedStatement preps2 = cnx.prepareStatement(req1);
                preps2.setInt(1, id);
                preps2.setInt(2, e.getIdExercice());
                preps2.executeUpdate();
            }
        }

            for(Exercice e:ex) {
                if(!exup.contains(e)){
                    PreparedStatement preps2 = cnx.prepareStatement(req2);
                    preps2.setInt(1, id);
                    preps2.setInt(2, e.getIdExercice());
                    preps2.executeUpdate();
                }
            }

            Programme pr=new Programme(id,255,NomProgramme,DureeProgramme,exup);
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, DureeProgramme);
            ps.setInt(2, pr.getEvaluationProgramme());
            ps.setString(3, pr.getDifficulteProgramme());
            ps.setString(4, NomProgramme);
            ps.setInt(5, id);
            ps.executeUpdate();
            System.out.println("Programme updated successfully!");
        } catch (SQLException exep) {
            exep.printStackTrace();
        }
    }
public void addList(Programme p,Exercice a){
    try {
        String req ="INSERT INTO `ListExercice`(`idProg`,`IDex` ) VALUES (?,?) ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(2, a.getIdExercice());
        ps.setInt(1,  p.getIdProgramme());
        ps.executeUpdate();
        System.out.println("Exercice added to list successfully!");
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    @Override
    public void delete(int p) {
        try {
            String req ="DELETE FROM `Programme`  WHERE IDprogramme = ?";
            String req1 ="DELETE FROM `ListExercice`  WHERE IDprog = ?";

            PreparedStatement ps = cnx.prepareStatement(req);
            PreparedStatement ps2 = cnx.prepareStatement(req1);

            ps.setInt(1, p);
            ps2.setInt(1, p);

            ps.executeUpdate();
            ps2.executeUpdate();

            System.out.println("Programme Deleted successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public List<Programme> rechercheProgramme(int id){
        List<Programme> Programmes = new ArrayList<>();
        try {
            String req = "SELECT * FROM Programme WHERE IdProgramme LIKE CONCAT(?, '%')";
            String req1 = "SELECT * FROM listExercice WHERE idProg = ?";
            String req2 = "SELECT * FROM Exercice WHERE idExercice = ?";

            // Creating prepared statements
            PreparedStatement ps = cnx.prepareStatement(req1);
            PreparedStatement ex = cnx.prepareStatement(req2);

            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Programme p = new Programme();
                p.setIdProgramme(rs.getInt(1));
                p.setNomProgramme(rs.getString(2));
                p.setIdCoachp(rs.getInt(3));
                p.setDureeProgramme(rs.getInt(4));
                p.setEvaluationProgramme(rs.getInt(5));
                p.setDifficulteProgramme(rs.getString(6));
                ps.setInt(1, p.getIdProgramme());

                ResultSet rs1 = ps.executeQuery();

                List<Exercice> exercices = new ArrayList<>();
                while (rs1.next()) {
                    int a = rs1.getInt(2);
                    ex.setInt(1, a);
                    ResultSet rs2 = ex.executeQuery();
                    while (rs2.next()) {
                        Exercice exer = new Exercice();
                        exer.setIdExercice(rs2.getInt(1));
                        exer.setNomExercice(rs2.getString(2));
                        exer.setIdCoach(rs2.getInt(3));
                        exer.setDifficulteExercice(rs2.getString(4));
                        exer.setEvaluationExercice(rs2.getInt(5));
                        exer.setMuscle(rs2.getString(6));
                        exer.setDemonstration(rs2.getString(7));
                        exercices.add(exer);
                    }
                }
                p.setListExercice(exercices);
                Programmes.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Programmes;
    }
    public List<Programme> filtreProgramme(int a,int b ) {
        List<Programme> Programmes = new ArrayList<>();
        try {

            String req = "SELECT * FROM Programme WHERE DureeProgramme BETWEEN ? AND ?";
            String req1 = "SELECT * FROM listExercice WHERE idProg = ?";
            String req2 = "SELECT * FROM Exercice WHERE idExercice = ?";

            // Creating prepared statements
            PreparedStatement ps = cnx.prepareStatement(req1);
            PreparedStatement ex = cnx.prepareStatement(req2);

            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, a);
            st.setInt(2, b);
             ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Programme p = new Programme();
                p.setIdProgramme(rs.getInt(1));
                p.setNomProgramme(rs.getString(2));
                p.setIdCoachp(rs.getInt(3));
                p.setDureeProgramme(rs.getInt(4));
                p.setEvaluationProgramme(rs.getInt(5));
                p.setDifficulteProgramme(rs.getString(6));
                ps.setInt(1, p.getIdProgramme());

                ResultSet rs1 = ps.executeQuery();

                List<Exercice> exercices = new ArrayList<>();
                while (rs1.next()) {
                    int j = rs1.getInt(2);
                    ex.setInt(1, j);
                    ResultSet rs2 = ex.executeQuery();
                    while (rs2.next()) {
                        Exercice exer = new Exercice();
                        exer.setIdExercice(rs2.getInt(1));
                        exer.setNomExercice(rs2.getString(2));
                        exer.setIdCoach(rs2.getInt(3));
                        exer.setDifficulteExercice(rs2.getString(4));
                        exer.setEvaluationExercice(rs2.getInt(5));
                        exer.setMuscle(rs2.getString(6));
                        exer.setDemonstration(rs2.getString(7));
                        exercices.add(exer);
                    }
                }
                p.setListExercice(exercices);
                Programmes.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Programmes;
        }
}
