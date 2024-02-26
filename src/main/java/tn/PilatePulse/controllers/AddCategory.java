package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.mfxcore.controls.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tn.PilatePulse.model.Category;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.services.CategoryService;

import java.util.HashSet;
import java.util.Set;

public class AddCategory {

    @FXML
    private Button addButtonid;

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
    private Label nameCatLabel;

    @FXML
    private MFXTextField nameFieldCat;

    @FXML
    private MFXButton programsButton;

    @FXML
    private MFXButton shopButton;

    @FXML
    private Label titleLabe;
    @FXML
    private MFXTextField idField;

    @FXML
    private Label idLabel;

    @FXML
    private AnchorPane topBar;
    CategoryService categoryService = new CategoryService();
    private Set<Integer> usedIds = new HashSet<>();

   /* @FXML
    void addCategory(ActionEvent event) {
        if (nameFieldCat == null || nameFieldCat.getText() == null || idField == null || idField.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("There's an error");
            alert.setContentText("Please enter category name and id");
            alert.showAndWait();}
        else {
            try {
                int categoryId = Integer.parseInt(idField.getText());
                String categoryName = nameFieldCat.getText();
                categoryService.add(new Category(categoryId, categoryName));

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Category ID");
                alert.setContentText("Please enter a valid integer for the category ID");
                alert.showAndWait();
            }
        }


    }*/
   @FXML
    void addCategory(ActionEvent event) {
        if (nameFieldCat != null && idField != null && idField.getText() != null) {
            String categoryName = nameFieldCat.getText();
            String categoryIdText = idField.getText();

            if (!categoryName.isEmpty() && !categoryIdText.isEmpty()) {
                try {
                    int categoryId = Integer.parseInt(categoryIdText);
                    categoryService.add(new Category(categoryId, categoryName));
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Category ID");
                    alert.setContentText("Please enter a valid integer for the category ID");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Empty Fields");
                alert.setContentText("Please enter category name and id");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Null Fields");
            alert.setContentText("Please make sure all fields are initialized");
            alert.showAndWait();
        }
    }


}
