package PilatesPulse.Controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import PilatesPulse.Services.ExerciceService;
import PilatesPulse.Models.Exercice;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.stage.Stage;


public class AjoutExercice implements Initializable {
    @FXML
    private MFXButton AjoutEx;
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

    private ExerciceService exp = new ExerciceService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] t1 = {"Abdominaux", "PlancherPelvin", "Dos", "Fessiers", "Cuisses", "Epaules", "Bras", "StabilisateurEpaule"};
        String[] t2 = {"Facile", "Moyenne", "Difficile"};
        Muscle.getItems().addAll(t1);
        Difficulte.getItems().addAll(t2);
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/gothicb.ttf"), 72);
        Font customFont2 = Font.loadFont(getClass().getResourceAsStream("/gothicb.ttf"), 18);

        label1.setFont(customFont);
        label11.setFont(customFont2);


    }


    public void AjoutEx(javafx.event.ActionEvent actionEvent) {

       String t=Demonstration.getText().replace("%20", " ");
        if (Evaluation.getValue()==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite");
            alert.setContentText("Ajouter une Evaluation.");
            alert.showAndWait(); 
        } else if (NomExercice.getText()==null||NomExercice.getText().length()<2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite");
            alert.setContentText("longueur du nom d'exercice doit etre >2.");
            alert.showAndWait();
        }
        else if (Muscle.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite");
            alert.setContentText("Ajouter un Muscle solicité.");
            alert.showAndWait();
        }
        else if (Demonstration.getText()==null||!Demonstration.getText().endsWith("jpeg")||!Demonstration.getText().endsWith("png")||!Demonstration.getText().endsWith("jpg")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite");
            alert.setContentText("Ajouter un Image Demonstratif(png,jpg,jpeg).");
            alert.showAndWait();
        }
        else
        {
            exp.add(new Exercice(1299, (int) Evaluation.getValue(), Difficulte.getValue(), NomExercice.getText(), Muscle.getValue(), t.replace("/", "\\").replace("file:\\", "")));
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AffichageExercice.fxml"));
                Parent root = loader.load();

                Scene currentScene = ((Node) actionEvent.getSource()).getScene();

                currentScene.setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }}
    }

    public void Browse(ActionEvent actionEvent) {
        Stage primaryStage = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            String fileUrl = selectedFile.toURI().toString();
            Demonstration.setText(fileUrl);
        }
    }
}