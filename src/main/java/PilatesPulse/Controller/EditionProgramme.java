package PilatesPulse.Controller;

import PilatesPulse.Models.Exercice;
import PilatesPulse.Models.Programme;
import PilatesPulse.Services.ExerciceService;
import PilatesPulse.Services.ProgrammeService;
import io.github.palexdev.materialfx.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EditionProgramme implements Initializable {
    private  int ID ;
    List<Exercice>lsEX=new ArrayList<>();
    List<Exercice>lsEXnon=new ArrayList<>();
    List<Exercice>lex=new ArrayList<>();

    @FXML
    private MFXButton Ajoutpr;
    @FXML
    private MFXButton Modifier;

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

    private ExerciceService exerciceService=new ExerciceService();
    private ProgrammeService exp= new ProgrammeService();
    private Stage primaryStage;

    public void setPassedId(int ID) {
        this.ID = ID;
    }
    public void Modifier(javafx.event.ActionEvent actionEvent) {
        exp.Edit(ID,NomProgramme.getText(),(int)Duree.getValue(),lsEXnon,lsEX);
            Stage stage = (Stage) Modifier.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/gothicb.ttf"), 72);
        Font customFont2 = Font.loadFont(getClass().getResourceAsStream("/gothicb.ttf"), 18);

        label1.setFont(customFont);
        label11.setFont(customFont2);
        Programme p1=new Programme();
        for (Programme p: exp.rechercheProgramme(ID)) {
            p1 = p;
        }

        Duree.setValue(p1.getDureeProgramme());
        NomProgramme.setText(p1.getNomProgramme());
        List<Exercice> exercises = exerciceService.fetch();
        for (Exercice exercise : exercises) {
            listajout.getItems().add(exercise);
        }
        for (Exercice exercise : p1.getListExercice()) {
            listsuppr.getItems().add(exercise);
            lsEX.add(exercise);
        }
        lsEXnon.addAll(lsEX);
        lex.addAll(lsEX);
    }
    public void setPrimaryStage(Stage primaryStage) {

        this.primaryStage = primaryStage;
    }
    @FXML
    void adding(ActionEvent event) {
        Exercice selectedExercise = listajout.getSelectionModel().getSelectedValue();

        if (selectedExercise != null) {
            List<Exercice> lista=new ArrayList<>();
            lista.addAll(listsuppr.getItems());

            if(!lista.contains(selectedExercise)) {
                    listsuppr.getItems().add(selectedExercise);
                    lsEX.add(selectedExercise);
            }
            listajout.getItems().remove(selectedExercise);
            lex.add(selectedExercise);
        }
    }

    @FXML
    void deleting(ActionEvent event) {
        Exercice selectedExercise = listsuppr.getSelectionModel().getSelectedValue();
        if (selectedExercise != null) {
            List<Exercice> lista=new ArrayList<>();
            lista.addAll(listajout.getItems());

            listsuppr.getItems().remove(selectedExercise);
            if(!lista.contains(selectedExercise)) {
                listajout.getItems().add(selectedExercise);
            }
            lsEX.remove(selectedExercise);
        }

    }

}
