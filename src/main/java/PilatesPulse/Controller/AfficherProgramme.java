package PilatesPulse.Controller;

import PilatesPulse.Models.Exercice;
import PilatesPulse.Models.Programme;
import PilatesPulse.Services.ExerciceService;
import PilatesPulse.Services.ProgrammeService;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import user.Utils.UserSession;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AfficherProgramme implements Initializable {
    @FXML
    private AnchorPane prog;
    @FXML
    private ListView<Programme> ListProgramme;
    ProgrammeService programmeService  = new ProgrammeService();
    private Stage primaryStage;
    @FXML
    private MFXComboBox<String> Tri;
    @FXML
    private MFXTextField recherche;
    @FXML
    private String Line;
    @FXML
    private MFXButton Ajoutp;

    @FXML
    private MFXButton Delete;

    @FXML
    private MFXButton Editer;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] trier = { "Nom","Difficulté", "Evaluation"};
        Tri.getItems().addAll(trier);
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
if(UserSession.getRole().equals("client")) {
    Ajoutp.setDisable(true);
    Editer.setDisable(true);
    Delete.setDisable(true);
    Ajoutp.setVisible(false);
    Editer.setVisible(false);
    Delete.setVisible(false);
}

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

    public void tri(ActionEvent actionEvent) {
        if(Tri.getValue().equals("Nom")) {
            List<Programme> exerciceList =  ListProgramme.getItems().stream().sorted(Comparator.comparing(Programme::getNomProgramme)).toList();
            ObservableList<Programme> observableExerciceList = FXCollections.observableArrayList(exerciceList);
            ListProgramme.setItems(observableExerciceList);
        }
        if(Tri.getValue().equals("Difficulté")) {
            List<Programme> exerciceList =  ListProgramme.getItems().stream().sorted(Comparator.comparing(Programme::getDifficulteProgramme)).toList();
            ObservableList<Programme> observableExerciceList = FXCollections.observableArrayList(exerciceList);
            ListProgramme.setItems(observableExerciceList);
        }
        if(Tri.getValue().equals("Evaluation")) {
            List<Programme> exerciceList =  ListProgramme.getItems().stream().sorted(Comparator.comparing(Programme::getEvaluationProgramme)).toList();
            ObservableList<Programme> observableExerciceList = FXCollections.observableArrayList(exerciceList);
            ListProgramme.setItems(observableExerciceList);
        }

    }

    public void refrech(ActionEvent actionEvent) {
        Font josefinBoldFont = Font.loadFont(getClass().getResource("/JosefinSans-Bold.ttf").toExternalForm(), 24);
        Font josefinRegularFont = Font.loadFont(getClass().getResource("/JosefinSans-ThinItalic.ttf").toExternalForm(), 24);
        ListProgramme.setCellFactory(param -> new ListCell<>() {
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
                    nameData.setFont(josefinBoldFont);



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

        ObservableList<Programme> observableExerciceList = FXCollections.observableArrayList(programmeService.fetch());
        ListProgramme.setItems(observableExerciceList);
    }
    public void ok(ActionEvent actionEvent) {
        List<Programme> exerciceList =  ListProgramme.getItems().stream().filter(exercice -> exercice.getNomProgramme().contains(recherche.getText())).toList();
        ObservableList<Programme> observableExerciceList = FXCollections.observableArrayList(exerciceList);
        ListProgramme.setItems(observableExerciceList);
    }

    public void passing(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AffichageExercice.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) actionEvent.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ajoutpass(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjoutProgramme.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) actionEvent.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Rec(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecoForm.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void handleCellClick(javafx.scene.input.MouseEvent mouseEvent) {

            if(ListProgramme.getSelectionModel().getSelectedItem().getIdCoachp()!=UserSession.getId())
            {
                Editer.setDisable(true);
                Delete.setDisable(true);
            }
            else{
                Editer.setDisable(false);
                Delete.setDisable(false);
            }


    }
}
