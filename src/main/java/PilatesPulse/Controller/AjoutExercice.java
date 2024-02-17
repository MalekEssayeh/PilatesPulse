package PilatesPulse.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import PilatesPulse.Services.ExerciceService;
import PilatesPulse.Models.Exercice;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class AjoutExercice implements Initializable {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] t1 = {"Abdominaux", "PlancherPelvin", "Dos", "Fessiers", "Cuisses", "Epaules", "Bras", "StabilisateurEpaule"};
        String[] t2 = {"Facile", "Moyenne", "Difficile"};
        Integer[] t3 = {1, 2, 3, 4, 5};
        Muscle.getItems().addAll(t1);
        Evaluation.getItems().addAll(t3);
        Difficulte.getItems().addAll(t2);
    }


    public void AjoutEx(javafx.event.ActionEvent actionEvent) {
        exp.add(new Exercice(1299,Evaluation.getValue(),Difficulte.getValue(),NomExercice.getText(),Muscle.getValue(),Demonstration.getText()));

    }
}
