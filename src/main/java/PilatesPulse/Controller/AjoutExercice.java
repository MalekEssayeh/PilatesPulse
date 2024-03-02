package PilatesPulse.Controller;

import PilatesPulse.Models.Exercice;
import PilatesPulse.Services.ExerciceService;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import user.Utils.UserSession;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.ResourceBundle;




public class AjoutExercice implements Initializable {


    @FXML
    private Pane captchaLabel;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField inputField;

    private String captchaValue;


    @FXML
    private MFXButton AjoutEx;
    @FXML
    private MFXComboBox<String> Muscle;
    @FXML
    private Label label1;
    @FXML
    private Label label11;
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

    private ExerciceService exp = new ExerciceService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String[] t1 = {"Abdominaux", "PlancherPelvin", "Dos", "Fessiers", "Cuisses", "Epaules", "Bras", "StabilisateurEpaule"};
        String[] t2 = {"Facile", "Moyenne", "Difficile"};
        Muscle.getItems().addAll(t1);
        Difficulte.getItems().addAll(t2);
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/gothicb.ttf"), 72);
        Font customFont2 = Font.loadFont(getClass().getResourceAsStream("/gothicb.ttf"), 18);

        label1.setFont(customFont);
        label11.setFont(customFont2);
        generateCaptcha();
        setCaptcha();

    }


    public void AjoutEx(javafx.event.ActionEvent actionEvent) {
String t1=Video.getText().replace("%20", " ");
       String t=Demonstration.getText().replace("%20", " ");
        String input = inputField.getText().trim();

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
        else if (Demonstration.getText()==null||(!Demonstration.getText().endsWith("jpeg")&&!Demonstration.getText().endsWith("png")&&!Demonstration.getText().endsWith("jpg"))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur s'est produite");
            alert.setContentText("Ajouter un Image Demonstratif(png,jpg,jpeg).");
            alert.showAndWait();
        } else if (!input.equals(captchaValue.replace(" ",""))) {
                errorMessage.setText("The word you entered does not match the generated word.");


        } else
        {               t1=t1.replace("/", "\\").replace("file:\\", "");
            t=t.replace("/", "\\").replace("file:\\", "");
            exp.add(new Exercice(UserSession.getId(), (int) Evaluation.getValue(), Difficulte.getValue(), NomExercice.getText(), Muscle.getValue(), t,t1));
            File selectedFile = new File(t);
            String targetFolder = "C:\\java\\GestionPhyAct\\src\\main\\java\\PilatesPulse\\Gui";
            String fileName = selectedFile.getName();
            Path targetPath = Paths.get(targetFolder, fileName);
            File selectedFile1 = new File(t1);
            String targetFolder1 = "C:\\java\\GestionPhyAct\\src\\main\\java\\PilatesPulse\\Gui";
            String fileName1 = selectedFile1.getName();
            Path targetPath1 = Paths.get(targetFolder1, fileName1);

            try {
                Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(selectedFile1.toPath(), targetPath1, StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/AffichageExercice.fxml"));
                Parent root = loader.load();

                Scene currentScene = ((Node) actionEvent.getSource()).getScene();

                currentScene.setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }}
    }

    public void Browse(ActionEvent actionEvent) {
        Stage primaryStage = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            String fileUrl = selectedFile.toURI().toString();
            Demonstration.setText(fileUrl);
        }
    }
    public void Browse1(ActionEvent actionEvent) {
        Stage primaryStage = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            String fileUrl = selectedFile.toURI().toString();
            Video.setText(fileUrl);
        }
    }


    private void generateCaptcha() {
        StringBuilder valueBuilder = new StringBuilder();
        Random random = new Random();

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        for (int i = 0; i < 6; i++) {
            char charValue = characters.charAt(random.nextInt(characters.length()));
            valueBuilder.append(charValue).append(" ");
        }

        captchaValue = valueBuilder.toString().trim();
    }
    private void setCaptcha() {
        captchaLabel.getChildren().clear();

        double xPos = 0;

        String[] fonts = {"cursive", "sans-serif", "serif", "monospace"};

        for (char charValue : captchaValue.toCharArray()) {
            Text text = new Text(String.valueOf(charValue));
            int rotate = -20 + new Random().nextInt(30);
            int padding = new Random().nextInt(10) + 5;

            String randomColor = String.format("#%06X", new Random().nextInt(0xFFFFFF));

            // Randomly select a font from the 'fonts' array
            String randomFont = fonts[new Random().nextInt(fonts.length)];

            text.setStyle("-fx-rotate: " + rotate + "; -fx-font-family: '" + randomFont + "'; -fx-font-size: 26; -fx-fill: " + randomColor + ";");

            text.setTranslateX(xPos);
            text.setTranslateY(0);

            captchaLabel.getChildren().add(text);

            xPos += text.getBoundsInLocal().getWidth() + padding;
        }
    }
    @FXML
    public void refreshCaptcha() {
        generateCaptcha();
        setCaptcha();
    }
}

