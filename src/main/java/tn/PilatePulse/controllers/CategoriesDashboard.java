package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CategoriesDashboard {

    @FXML
    private Button addID;

    @FXML
    private MFXButton categoriesButton;

    @FXML
    private MFXLegacyListView<?> categoriesList;

    @FXML
    private Button deleteid;

    @FXML
    private MFXButton homeButton;

    @FXML
    private ImageView logoImg;

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
    private Button updateid;

    @FXML
    void UpdateButton(ActionEvent event) {

    }

    @FXML
    void addButton(ActionEvent event) {

    }

    @FXML
    void categoriesDashBoard(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CategoriesDashboard.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void shopDashboard(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayShop.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void productsDashBoard(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayProduct.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteButton(ActionEvent event) {

    }



    @FXML
    void search(ActionEvent event) {

    }



}
