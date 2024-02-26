package PilatesPulse.Controller;

import PilatesPulse.Models.Exercice;
import PilatesPulse.Services.ExerciceService;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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
    private MFXTextField Video;
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

        String t=Demonstration.getText().replace("%20", " ");
        String t1=Video.getText().replace("%20", " ");

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
        else
        {
        exp.Edit(ID,NomExercice.getText(),1299,Difficulte.getValue(), (int) Evaluation.getValue(),Muscle.getValue(),t.replace("/", "\\").replace("file:\\",""),t1.replace("/", "\\").replace("file:\\",""));
        Stage stage = (Stage) Modifier.getScene().getWindow();
        stage.close();}
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
        Video.setText(e1.getVideo());

    }
    public void setPrimaryStage(Stage primaryStage) {

        this.primaryStage = primaryStage;
    }
    public void Browse(ActionEvent actionEvent) {
        Stage primaryStagee = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");

        File selectedFile = fileChooser.showOpenDialog(primaryStagee);

        if (selectedFile != null) {
            String fileUrl = selectedFile.toURI().toString();
            Demonstration.setText(fileUrl);
        }
    }
    public void Browse1(ActionEvent actionEvent) {
        Stage primaryStagee = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");

        File selectedFile = fileChooser.showOpenDialog(primaryStagee);

        if (selectedFile != null) {
            String fileUrl = selectedFile.toURI().toString();
            Video.setText(fileUrl);
        }
    }
}
