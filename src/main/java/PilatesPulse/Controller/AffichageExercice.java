    package PilatesPulse.Controller;

    import PilatesPulse.Models.Exercice;
    import PilatesPulse.Services.ExerciceService;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.ListCell;
    import javafx.scene.control.ListView;
    import javafx.scene.paint.Color;
    import javafx.scene.text.Font;
    import javafx.scene.text.FontWeight;
    import javafx.scene.text.Text;
    import javafx.scene.text.TextFlow;
    import javafx.stage.Stage;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import java.io.File;
    import java.net.URL;

    import java.awt.event.ActionEvent;
    import java.util.ResourceBundle;

    public class AffichageExercice implements Initializable {
        @FXML
        private ListView<Exercice> ListExercice;
        ExerciceService exerciceService = new ExerciceService();
        private Stage primaryStage;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            // Load Poppins font
            Font.loadFont(getClass().getResource("/gothicb.ttf").toExternalForm(), 40);

            ListExercice.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Exercice exercice, boolean empty) {
                    super.updateItem(exercice, empty);

                    if (empty || exercice == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        TextFlow textFlow = new TextFlow();


                        String labelStyle = "-fx-fill: #4B0070; -fx-font-family: 'Century Gothic'; -fx-font-weight: bold; -fx-font-size: 24;";


                        String dataStyle = "-fx-fill: #403060; -fx-font-family: 'Tw Cen MT Condensed Extra Bold'; -fx-font-weight: bold; -fx-font-size: 24;";

                        Text nameText = new Text("Name: ");
                        nameText.setStyle(labelStyle);
                        Text nameData = new Text(exercice.getNomExercice() + "\n");
                        nameData.setStyle(dataStyle);

                        Text idText = new Text("ID: ");
                        idText.setStyle(labelStyle);
                        Text idData = new Text(exercice.getIdExercice() + "\n");
                        idData.setStyle(dataStyle);

                        Text coachText = new Text("Coach ID: ");
                        coachText.setStyle(labelStyle);
                        Text coachData = new Text(exercice.getIdCoach() + "\n");
                        coachData.setStyle(dataStyle);

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

                        Text demonstrationText = new Text("Demonstration: ");
                        demonstrationText.setStyle(labelStyle);

                        String demonstrationPath = exercice.getDemonstration();
                        Image demonstrationImage = new Image(new File(demonstrationPath).toURI().toString());
                        ImageView imageView = new ImageView(demonstrationImage);
                        imageView.setFitHeight(200);
                        imageView.setFitWidth(200);
                        textFlow.getChildren().addAll(nameText, nameData, idText, idData, coachText, coachData, evaluationText, evaluationData,
                                difficultyText, difficultyData, muscleText, muscleData, demonstrationText);
                        textFlow.getChildren().add(imageView);
                        setGraphic(textFlow);

                    }
                }
            });

            ListExercice.getItems().addAll(exerciceService.fetch());
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
    }
