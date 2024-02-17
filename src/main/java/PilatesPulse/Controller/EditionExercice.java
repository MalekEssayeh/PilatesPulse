package PilatesPulse.Controller;

import PilatesPulse.Models.Exercice;
import PilatesPulse.Services.ExerciceService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditionExercice implements Initializable {
    @FXML
    private Button Modifier;
    private int ID ;
    @FXML
    private ComboBox<String> Muscle;
    @FXML
    private ComboBox<Integer> Evaluation;
    @FXML
    private ComboBox<String> Difficulte;
    @FXML
    private TextField NomExercice;
    @FXML
    private TextField Demonstration;

    private ExerciceService exp= new ExerciceService();
    private Stage primaryStage;

    public void setPassedId(int ID) {
        this.ID = ID;
    }
    public void Modifier(javafx.event.ActionEvent actionEvent) {
        exp.Edit(ID,NomExercice.getText(),1299,Difficulte.getValue(),Evaluation.getValue(),Muscle.getValue(),Demonstration.getText());
        Stage stage = (Stage) Modifier.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Exercice e1=new Exercice();
 for (Exercice e: exp.rechercheExercice(ID)) {
     e1 = e;
 }

        String[] t1 = {"Abdominaux", "PlancherPelvin", "Dos", "Fessiers", "Cuisses", "Epaules", "Bras", "StabilisateurEpaule"};
        String[] t2 = {"Facile", "Moyenne", "Difficile"};
        Integer[] t3 = {1, 2, 3, 4, 5};
        Muscle.getItems().addAll(t1);
        Evaluation.getItems().addAll(t3);
        Difficulte.getItems().addAll(t2);
        Muscle.setValue(e1.getMuscle());
        Evaluation.setValue(e1.getEvaluationExercice());
        Difficulte.setValue(e1.getDifficulteExercice());
        NomExercice.setText(e1.getNomExercice());
        Demonstration.setText(e1.getDemonstration());
    }
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
