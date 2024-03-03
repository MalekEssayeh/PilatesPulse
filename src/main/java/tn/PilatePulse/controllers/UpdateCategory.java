package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.PilatePulse.model.Category;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.services.CategoryService;

import java.io.IOException;

public class UpdateCategory {

    @FXML
    private MFXButton categoriesButton;

    @FXML
    private MFXButton homeButton1;

    @FXML
    private MFXTextField idField;

    @FXML
    private Label idLabel;

    @FXML
    private ImageView logoImg;

    @FXML
    private Label logoLabel;

    @FXML
    private Label nameCatLabel;

    @FXML
    private MFXTextField nameFieldCat;

    @FXML
    private AnchorPane navBar;

    @FXML
    private MFXButton productButton;

    @FXML
    private MFXButton searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private MFXButton shopButton;

    @FXML
    private Label titleLabe;

    @FXML
    private Button updateButtonId;
    private int id;
    private Stage  primaryStage;
    CategoryService categoryService = new CategoryService();



    @FXML
    void adminDashboard(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminDashBoard.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void UpdateButton(ActionEvent event) {
        if (id == 0 || nameFieldCat.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete Information");
            alert.setContentText("Please make sure all fields are filled in");
            alert.showAndWait();
        } else {
            categoryService.update(new Category(id, nameFieldCat.getText()));
            Stage stage = (Stage) updateButtonId.getScene().getWindow();
            stage.close();
        }
    }






    public void setPassedId(int id) {
        this.id = id;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
