    package PilatesPulse.Controller;

    import PilatesPulse.Models.Exercice;
    import PilatesPulse.Services.ExerciceService;
    import io.github.palexdev.materialfx.controls.MFXButton;
    import io.github.palexdev.materialfx.controls.MFXComboBox;
    import io.github.palexdev.materialfx.controls.MFXTextField;
    import io.github.palexdev.materialfx.utils.SwingFXUtils;
    import javafx.collections.FXCollections;
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
    import javafx.scene.control.ListCell;
    import javafx.scene.control.ListView;
    import javafx.scene.layout.ColumnConstraints;
    import javafx.scene.layout.GridPane;
    import javafx.scene.layout.HBox;
    import javafx.scene.layout.Priority;
    import javafx.scene.text.Font;
    import javafx.scene.text.Text;
    import javafx.scene.text.TextFlow;
    import javafx.stage.Stage;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;

    import java.awt.*;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.IOException;
    import java.net.URL;
    import java.util.Comparator;
    import java.util.Hashtable;
    import java.util.List;
    import java.util.ResourceBundle;
    import com.google.zxing.BarcodeFormat;
    import com.google.zxing.EncodeHintType;
    import com.google.zxing.WriterException;
    import com.google.zxing.common.BitMatrix;
    import com.google.zxing.qrcode.QRCodeWriter;
    import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
    import user.Utils.UserSession;


    public class AffichageExercice implements Initializable {
        @FXML
        private MFXComboBox<String> Tri;
        @FXML

       private MFXTextField recherche;
        @FXML
        private ListView<Exercice> ListExercice;
        @FXML
        private Label label1;
        @FXML
        private MFXButton Delete;
        @FXML
        private MFXButton Editer;
        @FXML
        private MFXButton Ajoutp;
        ExerciceService exerciceService = new ExerciceService();
        private Stage primaryStage;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            int size = 125;
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
                        GridPane container = new GridPane();

                        TextFlow textFlow = new TextFlow();


                        String labelStyle = "-fx-fill: #9b8385;  -fx-font-size: 27  ;";
                        String nameStyle = "-fx-fill: #8b7080;  -fx-font-size: 40;";

                        String dataStyle = "-fx-fill: #9b8385; -fx-font-size: 20;";


                        Text nameData = new Text(exercice.getNomExercice() + "\n");
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
                        BufferedImage qrCodeImage = createQRImage(nameData.getText() + " \n" +
                                        evaluationText.getText() + " \n" +
                                        evaluationData.getText() + " \n" +
                                        difficultyText.getText() + " \n" +
                                        difficultyData.getText() + " \n" +
                                        muscleText.getText() + " \n" +
                                        muscleData.getText()
                                , size);
                        Image qrCodeImageFX = SwingFXUtils.toFXImage(qrCodeImage, null);
                        ImageView Qr = new ImageView(qrCodeImageFX);
                        imageView.setFitHeight(200);
                        imageView.setFitWidth(200);
                        nameData.setWrappingWidth(200);
                        evaluationData.setWrappingWidth(200);
                        evaluationText.setWrappingWidth(200);
                        difficultyText.setWrappingWidth(200);
                        difficultyData.setWrappingWidth(200);
                        muscleText.setWrappingWidth(200);
                        muscleData.setWrappingWidth(200);
                        ColumnConstraints col1 = new ColumnConstraints(200);
                        ColumnConstraints col2 = new ColumnConstraints(450);
                        ColumnConstraints col3 = new ColumnConstraints(200);
                        container.getColumnConstraints().addAll(col1, col2, col3);


                        textFlow.getChildren().addAll(nameData, evaluationText, evaluationData, difficultyText, difficultyData, muscleText, muscleData );
                        container.add(textFlow, 1, 0);  // Add textFlow to column 0
                        container.add(imageView, 0, 0); // Add imageView to column 1
                        container.add(Qr, 2, 0);         // Add Qr to column 2
                        ColumnConstraints columnConstraints = new ColumnConstraints();
                        columnConstraints.setHgrow(Priority.ALWAYS);
                        container.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints);

                        container.setHgap(30);

                        setGraphic(container);

                    }
                }
            });

            ListExercice.getItems().addAll(exerciceService.fetch());
            String[] trier = { "Nom","Difficulté", "Evaluation"};
            Tri.getItems().addAll(trier);
            if(UserSession.getRole().equals("client")) {
                Ajoutp.setDisable(true);
                Editer.setDisable(true);
                Delete.setDisable(true);
                Ajoutp.setVisible(false);
                Editer.setVisible(false);
                Delete.setVisible(false);
            }
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
        public void Vid(javafx.event.ActionEvent actionEvent) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/VideoReader.fxml"));
                loader.setControllerFactory(controllerClass -> {
                    if (controllerClass == VideoReader.class) {
                        VideoReader VideoReaderController = new VideoReader();
                        Exercice selectedExercice = ListExercice.getSelectionModel().getSelectedItem();
                        int id = selectedExercice.getIdExercice();
                        VideoReaderController.setPassedId(id);
                        return VideoReaderController;
                    } else {
                        return new VideoReader();
                    }
                });

                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.setOnHidden(event -> {
                    updateListView();
                });

                stage.show();

                VideoReader VideoReaderController = loader.getController();
                VideoReaderController.setPrimaryStage(primaryStage);

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
                        GridPane container = new GridPane();

                        TextFlow textFlow = new TextFlow();


                        String labelStyle = "-fx-fill: #9b8385;  -fx-font-size: 27  ;";
                        String nameStyle = "-fx-fill: #8b7080;  -fx-font-size: 40;";

                        String dataStyle = "-fx-fill: #9b8385; -fx-font-size: 20;";


                        Text nameData = new Text(exercice.getNomExercice() + "\n");
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
                        BufferedImage qrCodeImage = createQRImage(nameData.getText() + " \n" +
                                        evaluationText.getText() + " \n" +
                                        evaluationData.getText() + " \n" +
                                        difficultyText.getText() + " \n" +
                                        difficultyData.getText() + " \n" +
                                        muscleText.getText() + " \n" +
                                        muscleData.getText()
                                , 125);
                        Image qrCodeImageFX = SwingFXUtils.toFXImage(qrCodeImage, null);
                        ImageView Qr = new ImageView(qrCodeImageFX);
                        imageView.setFitHeight(200);
                        imageView.setFitWidth(200);
                        nameData.setWrappingWidth(200);
                        evaluationData.setWrappingWidth(200);
                        evaluationText.setWrappingWidth(200);
                        difficultyText.setWrappingWidth(200);
                        difficultyData.setWrappingWidth(200);
                        muscleText.setWrappingWidth(200);
                        muscleData.setWrappingWidth(200);
                        ColumnConstraints col1 = new ColumnConstraints(200);
                        ColumnConstraints col2 = new ColumnConstraints(450);
                        ColumnConstraints col3 = new ColumnConstraints(200);
                        container.getColumnConstraints().addAll(col1, col2, col3);


                        textFlow.getChildren().addAll(nameData, evaluationText, evaluationData, difficultyText, difficultyData, muscleText, muscleData );
                        container.add(textFlow, 1, 0);  // Add textFlow to column 0
                        container.add(imageView, 0, 0); // Add imageView to column 1
                        container.add(Qr, 2, 0);         // Add Qr to column 2
                        ColumnConstraints columnConstraints = new ColumnConstraints();
                        columnConstraints.setHgrow(Priority.ALWAYS);
                        container.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints);

                        container.setHgap(30);

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
        public BufferedImage createQRImage( String qrCodeText, int size)
                 {
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
                     BitMatrix byteMatrix = null;
                     try {
                         byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
                     } catch (WriterException e) {
                         throw new RuntimeException(e);
                     }
                     int matrixWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, matrixWidth, matrixWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < matrixWidth; i++) {
                for (int j = 0; j < matrixWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            return image;
        }
        public void handleCellClick(javafx.scene.input.MouseEvent mouseEvent) {

            if(ListExercice.getSelectionModel().getSelectedItem().getIdCoach()!= UserSession.getId())
            {
                Editer.setDisable(true);
                Delete.setDisable(true);
            }
            else{
                Editer.setDisable(false);
                Delete.setDisable(false);
            }


        }

        public void Home(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
                Parent root = loader.load();

                Scene currentScene = ((Node) event.getSource()).getScene();

                currentScene.setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


