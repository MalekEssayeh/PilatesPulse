package PilatesPulse.Controller;

import PilatesPulse.Models.Programme;
import PilatesPulse.Services.ProgrammeService;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RecTable implements Initializable {
    @FXML
    private MFXLegacyListView<Programme> reclist;
    private String Line;

    private Stage primaryStage;

    public void setPassedLine(String Line) {
        this.Line = Line;
    }

    ProgrammeService ps = new ProgrammeService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(Line);
List<Programme> exerciceList = ps.fetch().stream()
                .sorted((program, program2) -> program.getExerciseCountForMuscle(Line) - program2.getExerciseCountForMuscle(Line))
                .limit(3)
                .collect(Collectors.toList());
        ObservableList<Programme> observableExerciceList = FXCollections.observableArrayList(exerciceList);
        reclist.setItems(observableExerciceList);
    }
    public void setPrimaryStage(Stage primaryStage) {

        this.primaryStage = primaryStage;
    }

}