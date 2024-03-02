package PilatesPulse.Controller;

import PilatesPulse.Models.Exercice;
import PilatesPulse.Models.Programme;
import PilatesPulse.Services.ProgrammeService;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class RecTable implements Initializable {
    @FXML
    private ListView<Programme> reclist;
    private String Line;

    private Stage primaryStage;

    public void setPassedLine(String Line) {
        this.Line = Line;
    }

    ProgrammeService ps = new ProgrammeService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(Line);
List<Programme> programmes=ps.fetch() ;
        ObservableList<Programme> observableExerciceList = FXCollections.observableArrayList(filterAndSortPrograms(programmes,Line));

        reclist.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Programme Programme, boolean empty) {
                super.updateItem(Programme, empty);

                if (empty || Programme == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    TextFlow textFlow = new TextFlow();
                    TextFlow textFlow2 = new TextFlow();

                    HBox container = new HBox();


                    String labelStyle = "-fx-fill: #9b8385;  -fx-font-size: 27  ;";
                    String nameStyle = "-fx-fill: #8b7080;  -fx-font-size: 40;";

                    String dataStyle = "-fx-fill: #9b8385; -fx-font-size: 20;";

                    Font josefinBoldFont = Font.loadFont(getClass().getResource("/JosefinSans-Bold.ttf").toExternalForm(), 24);

                    Text nameData = new Text("\t     "+Programme.getNomProgramme() + "\n");
                    nameData.setStyle(nameStyle);



                    Text evaluationText = new Text("\nEvaluation: ");
                    evaluationText.setStyle(labelStyle);
                    Text evaluationData = new Text( Programme.getEvaluationProgramme() + "\n");
                    evaluationData.setStyle(dataStyle);

                    Text DuréeTEXT = new Text("Durée: ");
                    DuréeTEXT.setStyle(labelStyle);
                    Text DuréeData = new Text(Programme.getDureeProgramme() + "\n");
                    DuréeData.setStyle(dataStyle);

                    Text difficultyText = new Text("Difficulté: ");
                    difficultyText.setStyle(labelStyle);
                    Text difficultyData = new Text(Programme.getDifficulteProgramme() + "\n");
                    difficultyData.setStyle(dataStyle);



                    Text demonstrationText = new Text("Exercices: ");
                    demonstrationText.setStyle(labelStyle);
                    Text demonstrationData = new Text(Programme.getListExercice() + "\n");
                    demonstrationData.setStyle(dataStyle);

                    textFlow.getChildren().addAll(nameData,evaluationText,evaluationData ,DuréeTEXT,DuréeData,difficultyText,difficultyData,demonstrationText, demonstrationData);
                    setGraphic(textFlow);


                }
            }
        });
        reclist.setItems(observableExerciceList);
    }
    public void setPrimaryStage(Stage primaryStage) {

        this.primaryStage = primaryStage;
    }
    public static List<Programme> filterAndSortPrograms(List<Programme> programs, String targetMuscle) {
        List<Programme> filteredPrograms = new ArrayList<>();
        for (Programme program : programs) {
            for (Exercice exercise : program.getListExercice()) {
                if (exercise.getMuscle().equals(targetMuscle)) {
                    filteredPrograms.add(program);
                    break;
                }
            }
        }

        Collections.sort(filteredPrograms, Comparator.comparingInt(program -> {
            int count = 0;
            for (Exercice exercise : program.getListExercice()) {
                if (exercise.getMuscle().equals(targetMuscle)) {
                    count++;
                }
            }
            return -count;
        }));

        // Limit the result to the specified number
        return filteredPrograms.subList(0, Math.min(3, filteredPrograms.size()));
    }
}