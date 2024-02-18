package PilatesPulse.Controller;

import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import PilatesPulse.Services.ExerciceService;
import PilatesPulse.Models.Exercice;
import javafx.scene.text.Font;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class AjoutExercice implements Initializable {
    @FXML
    private MFXComboBox<String> Muscle;
    @FXML
    private Label label1;
    @FXML
    private Label label11;
    @FXML
    private MFXSlider Evaluation;
    @FXML
    private MFXComboBox<String> Difficulte;
    @FXML
    private MFXTextField NomExercice;
    @FXML
    private MFXTextField Demonstration;

    private ExerciceService exp= new ExerciceService();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] t1 = {"Abdominaux", "PlancherPelvin", "Dos", "Fessiers", "Cuisses", "Epaules", "Bras", "StabilisateurEpaule"};
        String[] t2 = {"Facile", "Moyenne", "Difficile"};
        Muscle.getItems().addAll(t1);
        Difficulte.getItems().addAll(t2);
            // Load your desired font
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/gothicb.ttf"), 72);
        Font customFont2 = Font.loadFont(getClass().getResourceAsStream("/gothicb.ttf"), 18);

            // Set the font to the labels
            label1.setFont(customFont);
            label11.setFont(customFont2);


    }


    public void AjoutEx(javafx.event.ActionEvent actionEvent) {
        exp.add(new Exercice(1299, (int) Evaluation.getValue(),Difficulte.getValue(),NomExercice.getText(),Muscle.getValue(),Demonstration.getText()));

    }
}
