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


    import java.awt.event.ActionEvent;
    import java.net.URL;
    import java.util.ResourceBundle;

    public class AffichageExercice implements Initializable {
        @FXML
        private ListView<Exercice> ListExercice;
        ExerciceService exerciceService = new ExerciceService();
        private Stage primaryStage;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            // Load Poppins font
            Font.loadFont(getClass().getResource("/Poppins-Bold.ttf").toExternalForm(), 40);

            ListExercice.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Exercice exercice, boolean empty) {
                    super.updateItem(exercice, empty);

                    if (empty || exercice == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        TextFlow textFlow = new TextFlow();

                        // Create Text objects for different parts of the information
                        Text idText = new Text("ID: " + exercice.getIdExercice() + "\n");
                        idText.setFill(Color.web("#4B0082"));
                        idText.setFont(Font.font("Poppins", FontWeight.BOLD, 12)); // Set font to Poppins and make it bold

                        Text coachText = new Text("Coach ID: " + exercice.getIdCoach() + "\n");
                        coachText.setFill(Color.web("#4B0082"));
                        coachText.setFont(Font.font("Poppins", FontWeight.BOLD, 12));

                        Text evaluationText = new Text("Evaluation: " + exercice.getEvaluationExercice() + "\n");
                        evaluationText.setFill(Color.web("#4B0082"));
                        evaluationText.setFont(Font.font("Poppins", FontWeight.BOLD, 12));

                        Text difficultyText = new Text("Difficulty: " + exercice.getDifficulteExercice() + "\n");
                        difficultyText.setFill(Color.web("#4B0082"));
                        difficultyText.setFont(Font.font("Poppins", FontWeight.BOLD, 12));

                        Text nameText = new Text("Name: " + exercice.getNomExercice() + "\n");
                        nameText.setFill(Color.web("#4B0082"));
                        nameText.setFont(Font.font("Poppins", FontWeight.BOLD, 12));

                        Text muscleText = new Text("Muscle: " + exercice.getMuscle() + "\n");
                        muscleText.setFill(Color.web("#4B0082"));
                        muscleText.setFont(Font.font("Poppins", FontWeight.BOLD, 12));

                        Text demonstrationText = new Text("Demonstration: " + exercice.getDemonstration() + "\n");
                        demonstrationText.setFill(Color.web("#4B0082"));
                        demonstrationText.setFont(Font.font("Poppins", FontWeight.BOLD, 12));

                        textFlow.getChildren().addAll(idText, coachText, evaluationText, difficultyText, nameText, muscleText, demonstrationText);

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
