package user.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
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
    private TextField percentageTF;

    @FXML
    private Button updateBT;

    @FXML
    private DatePicker validiteDP;
    private int code;
    private int id;
    private boolean isActive;

    private Promo promoToUpdate;
    private final PromoService promoService = new PromoService();
    private final userService userService = new userService();

    public void initialize() {

    }

    public void initData(Promo promo) {
        this.promoToUpdate = promo;
       // code = promoToUpdate.getCode();
       // id = promoToUpdate.getId();
       // isActive = promoToUpdate.isActive();
        percentageTF.setText(String.valueOf(promo.getPourcentage()));
        // No pre-selected value for isActive
        // Controle de saisie calendrier
        validiteDP.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                // Disable past dates
                setDisable(date.isBefore(LocalDate.now()));
            }
        });
        // Convert Date to LocalDate
        Date validiteDate = promo.getValidite();
        if (validiteDate != null) {
            LocalDate validiteLocalDate = validiteDate.toLocalDate();
            validiteDP.setValue(validiteLocalDate);
        }
        //setpromoToUpdate(promoToUpdate);
    }



    @FXML
    void update(ActionEvent event) {
        try {
            float newPourcentage = Float.parseFloat(percentageTF.getText());
            LocalDate newValidite = validiteDP.getValue();

            // Update the Promo object with the new values
            promoToUpdate.setPourcentage(newPourcentage);

            // Convert LocalDate to Date
            java.sql.Date newValiditeDate = java.sql.Date.valueOf(newValidite);
            promoToUpdate.setValidite(newValiditeDate);

            // Set the code attribute
            //promoToUpdate.setCode(code);
           // promoToUpdate.setId(id);
            promoService.update(promoToUpdate);


            showAlert(Alert.AlertType.INFORMATION, "Success", "Promo updated successfully.");
            // Close the current window upon successful update
            Stage stage = (Stage) updateBT.getScene().getWindow();
            stage.close();
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





