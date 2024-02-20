package user.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import user.Models.Promo;
import user.Models.user;
import user.Services.PromoService;
import user.Services.userService;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class UpdatePromo {

    @FXML
    private ChoiceBox<Boolean> isActiveCB;

    @FXML
    private TextField percentageTF;

    @FXML
    private Button updateBT;

    //@FXML
    //private ChoiceBox<?> userCB;

    @FXML
    private DatePicker validiteDP;

    private Promo promoToUpdate;
    private final PromoService promoService = new PromoService();
    private final userService userService = new userService();

    public void initialize() {
        ObservableList<Boolean> options = FXCollections.observableArrayList(true, false);
        isActiveCB.setItems(options);
    }

    public void initData(Promo promo) {
        promoToUpdate = promo;
        percentageTF.setText(String.valueOf(promo.getPourcentage()));

        // No pre-selected value for isActive
        isActiveCB.setValue(null);

        // Convert Date to LocalDate
        Date validiteDate = promo.getValidite();
        if (validiteDate != null) {
            LocalDate validiteLocalDate = validiteDate.toLocalDate();
            validiteDP.setValue(validiteLocalDate);
        }
    }

    @FXML
    void update(ActionEvent event) {
        float newPourcentage = Float.parseFloat(percentageTF.getText());
        Boolean newIsActive = isActiveCB.getValue();
        LocalDate newValidite = validiteDP.getValue();

        promoToUpdate.setPourcentage(newPourcentage);
        promoToUpdate.setActive(newIsActive);

        // Convert LocalDate to Date
        java.sql.Date newValiditeDate = java.sql.Date.valueOf(newValidite);
        promoToUpdate.setValidite(newValiditeDate);

        try {
            promoService.update(promoToUpdate);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Promo updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update promo: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}





