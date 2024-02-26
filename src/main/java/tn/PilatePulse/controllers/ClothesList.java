package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import io.github.palexdev.mfxcore.controls.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ClothesList {

    @FXML
    private MFXButton accessoriesButton;

    @FXML
    private MFXButton cartButton;

    @FXML
    private MFXButton clothesButton;

    @FXML
    private MFXLegacyListView<?> clothesList;

    @FXML
    private MFXButton coacheButton;

    @FXML
    private MFXButton eventsButton;

    @FXML
    private MFXButton exercicesButton;

    @FXML
    private MFXButton homeButton;

    @FXML
    private ImageView logoImg;

    @FXML
    private Label logoLabel;

    @FXML
    private MFXButton programsButton;

    @FXML
    private MFXButton searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private MFXButton shopButton;

    @FXML
    private MFXButton shophomeButton;

    @FXML
    private AnchorPane topBar;

    @FXML
    private MFXButton wishlistButton;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    void Cart(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

}