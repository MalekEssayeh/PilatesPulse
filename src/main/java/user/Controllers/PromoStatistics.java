package user.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class PromoStatistics implements Initializable {

    @FXML
    private BarChart<String, Integer> promoChart;

    @FXML
    private CategoryAxis promoAxis;

    @FXML
    private NumberAxis countAxis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the chart
        promoAxis.setLabel("Percentage Range");
        countAxis.setLabel("Number of Promo Codes");
        promoChart.setStyle("-fx-bar-fill: purple;");
        promoAxis.setStyle("-fx-text-fill: purple;");
        countAxis.setStyle("-fx-text-fill: purple;");

    }

    // Method to update the chart with promo statistics
    public void updatePromoStatistics() {
        // Your logic to fetch promo statistics and populate the chart
        // For example:
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("0-25%", 10));
        series.getData().add(new XYChart.Data<>("26-50%", 15));
        series.getData().add(new XYChart.Data<>("51-75%", 8));
        series.getData().add(new XYChart.Data<>("76-100%", 5));
        promoChart.getData().add(series);
    }
}
