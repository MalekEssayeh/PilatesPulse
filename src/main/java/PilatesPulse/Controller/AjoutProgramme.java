package PilatesPulse.Controller;

import PilatesPulse.Models.Exercice;
import PilatesPulse.Models.Programme;
import PilatesPulse.Services.ExerciceService;
import PilatesPulse.Services.ProgrammeService;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class AjoutProgramme implements Initializable {

    @FXML
    private MFXButton Ajoutpr;

    @FXML
    private MFXSlider Duree;

    @FXML
    private MFXTextField NomProgramme;

    @FXML
    private MFXButton add;

    @FXML
    private MFXButton delete;

    @FXML
    private Label label1;

    @FXML
    private Label label11;

    @FXML
    private MFXListView<Exercice> listajout;

    @FXML
    private MFXListView<Exercice> listsuppr;

    private ExerciceService exerciceService;
    private ProgrammeService programmeService;

    private List<Exercice> lsEX = new ArrayList<>();

    @FXML
    void Ajoutpr(javafx.event.ActionEvent event) {
        if (Duree.getValue()==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite");
            alert.setContentText("Ajouter une Duree.");
            alert.showAndWait();
        } else if (NomProgramme.getText()==null||NomProgramme.getText().length()<2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite");
            alert.setContentText("longueur du nom d'Programme doit etre >2.");
            alert.showAndWait();
        }

        else
        {
        programmeService.add(new Programme(ThreadLocalRandom.current().nextInt(1000, 2852),255,NomProgramme.getText(), (int) Duree.getValue(),lsEX));
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherProgramme.fxml"));
                Parent root = loader.load();

                Scene currentScene = ((Node) event.getSource()).getScene();

                currentScene.setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }}
    }

    @FXML
    void adding(ActionEvent event) {
        Exercice selectedExercise = listajout.getSelectionModel().getSelectedValue();

        if (selectedExercise != null) {
            listsuppr.getItems().add(selectedExercise);
            listajout.getItems().remove(selectedExercise);
            lsEX.add(selectedExercise);
        }
    }

    @FXML
    void deleting(ActionEvent event) {
        Exercice selectedExercise = listsuppr.getSelectionModel().getSelectedValue();
        if (selectedExercise != null) {

            listsuppr.getItems().remove(selectedExercise);
        listajout.getItems().add(selectedExercise);
        lsEX.remove(selectedExercise);}

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exerciceService = new ExerciceService();
        programmeService = new ProgrammeService();
        List<Exercice> exercises = exerciceService.fetch();
        for (Exercice exercise : exercises) {
            listajout.getItems().add(exercise);
        }
    }
}
