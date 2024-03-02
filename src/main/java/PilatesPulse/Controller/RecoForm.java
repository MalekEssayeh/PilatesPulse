package PilatesPulse.Controller;

import PilatesPulse.Models.Programme;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXSlider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

public class RecoForm implements Initializable {
    @FXML
    private MFXSlider Age;

    @FXML
    private MFXSlider Height;

    @FXML
    private MFXComboBox<String> Genre;

    @FXML
    private MFXSlider weight;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] t2 = {"Femme", "Homme"};
        Genre.getItems().addAll(t2);
    }
    private AfficherProgramme afficherProgrammeController;

    public void setAfficherProgrammeController(AfficherProgramme afficherProgrammeController) {
        this.afficherProgrammeController = afficherProgrammeController;
    }
    public void Confirm(ActionEvent actionEvent) {
        try {
            double ageValue = Age.getValue();
            double heightValue = Height.getValue();
            double weightValue = weight.getValue();
            String genreValue = Genre.getValue();
            String ageString = String.valueOf((int) ageValue);
            String heightString = String.valueOf((int) heightValue);
            String weightString = String.valueOf((int) weightValue);
            // Construct the command array
            String[] command = {"python", "C:\\java\\GestionPhyAct\\Library\\RecomIA.py", genreValue, ageString, heightString, weightString};

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            System.out.println(line);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecTable.fxml"));
            Parent root = loader.load();

            RecTable recTableController = loader.getController();
            recTableController.setPassedLine(line);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();

            int exitCode = process.waitFor();
            System.out.println("Exited with error code " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
