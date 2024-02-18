package PilatesPulse.Controller;

import PilatesPulse.Models.Exercice;
import PilatesPulse.Services.ExerciceService;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditionExercice implements Initializable {
    @FXML
    private MFXButton Modifier;
    private int ID ;
    @FXML
    private MFXComboBox<String> Muscle;
    @FXML
    private MFXSlider Evaluation;
    @FXML
    private MFXComboBox<String> Difficulte;
    @FXML
    private MFXTextField NomExercice;
    @FXML
    private MFXTextField Demonstration;
    @FXML
    private Label label1;
    @FXML
    private Label label11;

    private ExerciceService exp= new ExerciceService();
    private Stage primaryStage;

    public void setPassedId(int ID) {
        this.ID = ID;
    }
    public void Modifier(javafx.event.ActionEvent actionEvent) {
        exp.Edit(ID,NomExercice.getText(),1299,Difficulte.getValue(), (int) Evaluation.getValue(),Muscle.getValue(),Demonstration.getText());
        Stage stage = (Stage) Modifier.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/gothicb.ttf"), 72);
        Font customFont2 = Font.loadFont(getClass().getResourceAsStream("/gothicb.ttf"), 18);

        label1.setFont(customFont);
        label11.setFont(customFont2);
        Exercice e1=new Exercice();
 for (Exercice e: exp.rechercheExercice(ID)) {
     e1 = e;
 }

        String[] t1 = {"Abdominaux", "PlancherPelvin", "Dos", "Fessiers", "Cuisses", "Epaules", "Bras", "StabilisateurEpaule"};
        String[] t2 = {"Facile", "Moyenne", "Difficile"};
        Muscle.getItems().addAll(t1);
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
