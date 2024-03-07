package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.model.RatingModel;
import tn.PilatePulse.services.RatingService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayRatings implements Initializable {

    @FXML
    private Button chartButton;

    @FXML
    private MFXButton homeButton;

    @FXML
    private ImageView logoImg;

    @FXML
    private AnchorPane navBar;

    @FXML
    private MFXLegacyListView<RatingModel> ratingList;

    @FXML
    private Label titleLabe;


    RatingService ratingService = new RatingService();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ratingList.setCellFactory(param -> new ListCell<>() {

            @Override
            protected void updateItem(RatingModel rating, boolean empty) {
                super.updateItem(rating, empty);

                if (empty || rating == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    GridPane container = new GridPane();

                    TextFlow textFlow = new TextFlow();


                    String labelStyle = "-fx-fill: #9b8385;  -fx-font-size: 27  ;";
                    String nameStyle = "-fx-fill: #8b7080;  -fx-font-size: 40;";

                    String dataStyle = "-fx-fill: #9b8385; -fx-font-size: 20;";




                    Text idUserText = new Text("idUser:  ");
                    idUserText.setStyle(labelStyle);
                    Text idUserData = new Text(rating.getIdUser() + "\n");
                    idUserData.setStyle(dataStyle);

                    Text idProductText = new Text("Product refrence:  ");
                    idProductText.setStyle(labelStyle);
                    Text idProductData = new Text(rating.getIdProduct() + "\n");
                    idProductData.setStyle(dataStyle);

                    Text nbStarsText = new Text("Product rating:  ");
                    nbStarsText.setStyle(labelStyle);
                    Text nbStarsData = new Text(rating.getNbStars() + "\n");
                    nbStarsData.setStyle(dataStyle);


                    textFlow.getChildren().addAll(idUserText, idUserData, idProductText, idProductData, nbStarsText, nbStarsData );
                    container.add(textFlow, 1, 0);
                    ColumnConstraints columnConstraints = new ColumnConstraints();
                    columnConstraints.setHgrow(Priority.ALWAYS);
                    container.getColumnConstraints().addAll(columnConstraints, columnConstraints);

                    //container.setHgap(30);

                    setGraphic(container);

                }
            }
        });

        ratingList.getItems().addAll(ratingService.fetchRating());

    }


    @FXML
    void displayChart(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayChart.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void displayDashboard(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminDashBoard.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
