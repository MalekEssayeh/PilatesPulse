package PilatesPulse.Controller;

import PilatesPulse.Models.Exercice;
import PilatesPulse.Services.ExerciceService;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSlider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class VideoReader implements Initializable {

    private  int ID  ;
    @FXML
    private MFXButton min5;

    @FXML
    private MFXButton plus5;

    @FXML
    private MFXButton reset;

    @FXML
    private MFXButton resume;

    @FXML
    private MediaView Video;

    @FXML
    private MFXSlider VolumeSlider;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    private Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ExerciceService exp = new ExerciceService();
        Exercice e1=new Exercice();
        for (Exercice e: exp.rechercheExercice(ID)) {
             e1= e;
        }

        file = new File(e1.getVideo());
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        Video.setMediaPlayer(mediaPlayer);
        Video.setPreserveRatio(false);

        VolumeSlider.setValue(mediaPlayer.getVolume() * 100);
        VolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> mediaPlayer.setVolume(newValue.doubleValue() / 100));

    }
    @FXML

    void plus5(ActionEvent event) {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(5.0)));
    }

    @FXML
    void min5(ActionEvent event) {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(5.0)));
    }

    @FXML
    void resetmedia(ActionEvent event) {
        if (mediaPlayer.getStatus() != MediaPlayer.Status.READY) {
            mediaPlayer.seek(Duration.seconds(0.0));
        }
    }

    @FXML
    void resumemedia(ActionEvent event) {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.play();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void setPassedId(int ID) {
        this.ID = ID;
    }

}
