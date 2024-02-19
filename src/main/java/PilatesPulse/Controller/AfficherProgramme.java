package PilatesPulse.Controller;

import PilatesPulse.Models.Exercice;
import PilatesPulse.Models.Programme;
import PilatesPulse.Services.ExerciceService;
import PilatesPulse.Services.ProgrammeService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AfficherProgramme implements Initializable {
    @FXML
    private ListView<Programme> ListProgramme;
    ProgrammeService programmeService  = new ProgrammeService();
    private Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Load Poppins font
        Font.loadFont(getClass().getResource("/gothicb.ttf").toExternalForm(), 40);

        ListProgramme.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Programme Programme, boolean empty) {
                super.updateItem(Programme, empty);

                if (empty || Programme == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    TextFlow textFlow = new TextFlow();


                    String labelStyle = "-fx-fill: #4B0070; -fx-font-family: 'Century Gothic'; -fx-font-weight: bold; -fx-font-size: 24;";


                    String dataStyle = "-fx-fill: #403060; -fx-font-family: 'Tw Cen MT Condensed Extra Bold'; -fx-font-weight: bold; -fx-font-size: 24;";

                    Text nameText = new Text("Name: ");
                    nameText.setStyle(labelStyle);
                    Text nameData = new Text(Programme.getNomProgramme() + "\n");
                    nameData.setStyle(dataStyle);

                    Text idText = new Text("ID: ");
                    idText.setStyle(labelStyle);
                    Text idData = new Text(Programme.getIdProgramme() + "\n");
                    idData.setStyle(dataStyle);

                    Text coachText = new Text("Coach ID: ");
                    coachText.setStyle(labelStyle);
                    Text coachData = new Text(Programme.getIdCoachp() + "\n");
                    coachData.setStyle(dataStyle);

                    Text evaluationText = new Text("Evaluation: ");
                    evaluationText.setStyle(labelStyle);
                    Text evaluationData = new Text(Programme.getEvaluationProgramme() + "\n");
                    evaluationData.setStyle(dataStyle);

                    Text difficultyText = new Text("Difficulty: ");
                    difficultyText.setStyle(labelStyle);
                    Text difficultyData = new Text(Programme.getDifficulteProgramme() + "\n");
                    difficultyData.setStyle(dataStyle);



                    Text demonstrationText = new Text("ListExercice: ");
                    demonstrationText.setStyle(labelStyle);
                    Text demonstrationData = new Text(Programme.getListExercice() + "\n");
                    demonstrationData.setStyle(dataStyle);

                    textFlow.getChildren().addAll( nameText, nameData,idText, idData, coachText, coachData, evaluationText, evaluationData,
                            difficultyText, difficultyData,demonstrationText,demonstrationData);

                    setGraphic(textFlow);

                }
            }
        });

        ListProgramme.getItems().addAll(programmeService.fetch());
    }
   @FXML
    private void deleteSelectedProgramme() {
        Programme selectedProgramme = ListProgramme.getSelectionModel().getSelectedItem();

        if (selectedProgramme != null) {
            int id = selectedProgramme.getIdProgramme();
            programmeService.delete(id);
            ListProgramme.getItems().remove(selectedProgramme);
        }}



    public void Editer(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditionProgramme.fxml"));
            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == EditionProgramme.class) {
                    EditionProgramme editionProgrammeController = new EditionProgramme();
                    Programme selectedProgramme = ListProgramme.getSelectionModel().getSelectedItem();
                    int id = selectedProgramme.getIdProgramme();
                    editionProgrammeController.setPassedId(id);
                    return editionProgrammeController;
                } else {
                    return new EditionProgramme();
                }
            });

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.setOnHidden(event -> {
                updateListView();
            });

            stage.show();

            EditionProgramme editionProgrammeController = loader.getController();
            editionProgrammeController.setPrimaryStage(primaryStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateListView() {
        ListProgramme.getItems().setAll(programmeService.fetch());
    }
}
