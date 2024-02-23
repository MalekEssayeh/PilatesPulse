    package PilatesPulse.Controller;

    import PilatesPulse.Models.Exercice;
    import PilatesPulse.Services.ExerciceService;
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
    import javafx.scene.control.Label;
    import javafx.scene.control.ListCell;
    import javafx.scene.control.ListView;
    import javafx.scene.layout.HBox;
    import javafx.scene.layout.VBox;
    import javafx.scene.paint.Color;
    import javafx.scene.text.Font;
    import javafx.scene.text.FontWeight;
    import javafx.scene.text.Text;
    import javafx.scene.text.TextFlow;
    import javafx.stage.Stage;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import java.io.File;
    import java.io.IOException;
    import java.net.URL;

    import java.util.Comparator;
    import java.util.List;
    import java.util.ResourceBundle;
    import java.util.stream.Collectors;

    public class AffichageExercice implements Initializable {
        @FXML
        private MFXComboBox<String> Tri;
       @FXML
       private MFXTextField recherche;
        @FXML
        private ListView<Exercice> ListExercice;
        @FXML
        private Label label1;

        ExerciceService exerciceService = new ExerciceService();
        private Stage primaryStage;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            Font customFont = Font.loadFont(getClass().getResourceAsStream("/gothicb.ttf"), 72);
            label1.setFont(customFont);

            Font josefinBoldFont = Font.loadFont(getClass().getResource("/JosefinSans-Bold.ttf").toExternalForm(), 24);
            Font josefinRegularFont = Font.loadFont(getClass().getResource("/JosefinSans-ThinItalic.ttf").toExternalForm(), 24);

            ListExercice.setCellFactory(param -> new ListCell<>() {

                @Override
                protected void updateItem(Exercice exercice, boolean empty) {
                    super.updateItem(exercice, empty);

                    if (empty || exercice == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        HBox container = new HBox();

                        TextFlow textFlow = new TextFlow();


                        String labelStyle = "-fx-fill: #9b8385;  -fx-font-size: 27  ;";
                        String nameStyle = "-fx-fill: #8b7080;  -fx-font-size: 40;";

                        String dataStyle = "-fx-fill: #9b8385; -fx-font-size: 20;";


                        Text nameData = new Text("\t"+exercice.getNomExercice() + "\n");
                        nameData.setStyle(nameStyle);

                        Text evaluationText = new Text("Evaluation: ");
                        evaluationText.setStyle(labelStyle);

                        Text evaluationData = new Text(exercice.getEvaluationExercice() + "\n");
                        evaluationData.setStyle(dataStyle);

                        Text difficultyText = new Text("Difficulty: ");
                        difficultyText.setStyle(labelStyle);
                        Text difficultyData = new Text(exercice.getDifficulteExercice() + "\n");
                        difficultyData.setStyle(dataStyle);

                        Text muscleText = new Text("Muscle: ");
                        muscleText.setStyle(labelStyle);
                        Text muscleData = new Text(exercice.getMuscle() + "\n");
                        muscleData.setStyle(dataStyle);



                        String demonstrationPath = exercice.getDemonstration();
                        Image demonstrationImage = new Image(new File(demonstrationPath).toURI().toString());
                        ImageView imageView = new ImageView(demonstrationImage);
                        imageView.setFitHeight(200);
                        imageView.setFitWidth(200);
                        textFlow.getChildren().addAll(nameData, evaluationText, evaluationData, difficultyText, difficultyData, muscleText, muscleData );
                        container.getChildren().addAll(imageView,textFlow);
                        container.setSpacing(30);
                        setGraphic(container);

                    }
                }
            });

            ListExercice.getItems().addAll(exerciceService.fetch());
            String[] trier = { "Nom","Difficulté", "Evaluation"};
            Tri.getItems().addAll(trier);
        }
        @FXML
        private void deleteSelectedExercice() {
            Exercice selectedExercice = ListExercice.getSelectionModel().getSelectedItem();

            if (selectedExercice != null) {
                int id = selectedExercice.getIdExercice();
                exerciceService.delete(id);
                ListExercice.getItems().remove(selectedExercice);
        }}



        public void Editer(javafx.event.ActionEvent actionEvent) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditionExercice.fxml"));
                loader.setControllerFactory(controllerClass -> {
                    if (controllerClass == EditionExercice.class) {
                        EditionExercice editionExerciceController = new EditionExercice();
                        Exercice selectedExercice = ListExercice.getSelectionModel().getSelectedItem();
                        int id = selectedExercice.getIdExercice();
                        editionExerciceController.setPassedId(id);
                        return editionExerciceController;
                    } else {
                        return new EditionExercice();
                    }
                });

                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    updateListView();
                });

                stage.show();

                EditionExercice editionExerciceController = loader.getController();
                editionExerciceController.setPrimaryStage(primaryStage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public void updateListView() {
            ListExercice.getItems().setAll(exerciceService.fetch());
        }

        public void ok(ActionEvent actionEvent) {
            List<Exercice> exerciceList =  ListExercice.getItems().stream().filter(exercice -> exercice.getNomExercice().contains(recherche.getText())).toList();
            ObservableList<Exercice> observableExerciceList = FXCollections.observableArrayList(exerciceList);
            ListExercice.setItems(observableExerciceList);
        }

        public void tri(ActionEvent actionEvent) {
            if(Tri.getValue().equals("Nom")) {
                List<Exercice> exerciceList =  ListExercice.getItems().stream().sorted(Comparator.comparing(Exercice::getNomExercice)).toList();
                ObservableList<Exercice> observableExerciceList = FXCollections.observableArrayList(exerciceList);
                ListExercice.setItems(observableExerciceList);
            }
            if(Tri.getValue().equals("Difficulté")) {
                List<Exercice> exerciceList =  ListExercice.getItems().stream().sorted(Comparator.comparing(Exercice::getDifficulteExercice)).toList();
                ObservableList<Exercice> observableExerciceList = FXCollections.observableArrayList(exerciceList);
                ListExercice.setItems(observableExerciceList);
            }
            if(Tri.getValue().equals("Evaluation")) {
                List<Exercice> exerciceList =  ListExercice.getItems().stream().sorted(Comparator.comparing(Exercice::getEvaluationExercice)).toList();
                ObservableList<Exercice> observableExerciceList = FXCollections.observableArrayList(exerciceList);
                ListExercice.setItems(observableExerciceList);
            }

        }

        public void refrech(ActionEvent actionEvent) {

            Font josefinBoldFont = Font.loadFont(getClass().getResource("/JosefinSans-Bold.ttf").toExternalForm(), 24);
            Font josefinRegularFont = Font.loadFont(getClass().getResource("/JosefinSans-ThinItalic.ttf").toExternalForm(), 24);

            ListExercice.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Exercice exercice, boolean empty) {
                    super.updateItem(exercice, empty);

                    if (empty || exercice == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        HBox container = new HBox();

                        TextFlow textFlow = new TextFlow();


                        String labelStyle = "-fx-fill: #9b8385;  -fx-font-size: 27  ;";
                        String nameStyle = "-fx-fill: #8b7080;  -fx-font-size: 40;";

                        String dataStyle = "-fx-fill: #9b8385; -fx-font-size: 20;";


                        Text nameData = new Text("\t"+exercice.getNomExercice() + "\n");
                        nameData.setStyle(nameStyle);
                        nameData.setFont(josefinBoldFont);

                        Text evaluationText = new Text("Evaluation: ");
                        evaluationText.setStyle(labelStyle);
                        evaluationText.setFont(josefinBoldFont);

                        Text evaluationData = new Text(exercice.getEvaluationExercice() + "\n");
                        evaluationData.setStyle(dataStyle);

                        Text difficultyText = new Text("Difficulty: ");
                        difficultyText.setStyle(labelStyle);
                        difficultyText. setFont(josefinBoldFont);
                        Text difficultyData = new Text(exercice.getDifficulteExercice() + "\n");
                        difficultyData.setStyle(dataStyle);

                        Text muscleText = new Text("Muscle: ");
                        muscleText.setStyle(labelStyle);
                        Text muscleData = new Text(exercice.getMuscle() + "\n");
                        muscleData.setStyle(dataStyle);



                        String demonstrationPath = exercice.getDemonstration();
                        Image demonstrationImage = new Image(new File(demonstrationPath).toURI().toString());
                        ImageView imageView = new ImageView(demonstrationImage);
                        imageView.setFitHeight(200);
                        imageView.setFitWidth(200);
                        textFlow.getChildren().addAll(nameData, evaluationText, evaluationData, difficultyText, difficultyData, muscleText, muscleData );
                        container.getChildren().addAll(imageView,textFlow);
                        container.setSpacing(30);
                        setGraphic(container);

                    }
                }
            });

            ObservableList<Exercice> observableExerciceList = FXCollections.observableArrayList(exerciceService.fetch());
            ListExercice.setItems(observableExerciceList);
        }

        public void pass(ActionEvent actionEvent) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherProgramme.fxml"));
                Parent root = loader.load();

                Scene currentScene = ((Node) actionEvent.getSource()).getScene();

                currentScene.setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void ajoutpass(ActionEvent actionEvent) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjoutExercice.fxml"));
                Parent root = loader.load();

                Scene currentScene = ((Node) actionEvent.getSource()).getScene();

                currentScene.setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
