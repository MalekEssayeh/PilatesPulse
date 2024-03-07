package tn.PilatePulse.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tn.PilatePulse.services.RatingService;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class DisplayChart implements Initializable {

    @FXML
    private BarChart<String, Double> prodyuctsBarChart;

    @FXML
    private Button ratingsButton;

    @FXML
    private Label titleLabe;

    CategoryAxis xAxis = new CategoryAxis();
    CategoryAxis yAxis = new CategoryAxis();

    public CategoryAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(CategoryAxis xAxis) {
        this.xAxis = xAxis;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RatingService ratingService = new RatingService();

        Map<String, Double> productRatings = ratingService.fetchAverageProductRatings();

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Product Name");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Rating");


        String chartStyle = "-fx-bar-width: 10; -fx-bar-fill: #CBC3E3;";



        prodyuctsBarChart.setTitle("Product Ratings");
        prodyuctsBarChart.setStyle(chartStyle);




        XYChart.Series<String, Double> series = new XYChart.Series<>();
        for (Map.Entry<String, Double> entry : productRatings.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }



        prodyuctsBarChart.getData().add(series);


    }


    @FXML
    void displayRatings(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayRatings.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
