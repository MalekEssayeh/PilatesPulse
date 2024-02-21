package tn.PilatePulse.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.services.ProductService;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateProduct implements Initializable {
    @FXML
    private TextField idCategoryID;

    @FXML
    private TextField nomProductiD;

    @FXML
    private TextField prixProductID;

    @FXML
    private Button updateButton;
    @FXML
    private ListView<Product> listProducts;
    private Stage  primaryStage;
    private int id;
    private ProductService productService = new ProductService();
    private Label nomProductID;


    @FXML
    void update(ActionEvent event) {
        System.out.println(id);
        productService.update(new Product(id,
                nomProductiD.getText(),
                Float.parseFloat(prixProductID.getText()),
                Integer.parseInt(idCategoryID.getText())
                ));
        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.close();
    }

    public void setPassedId(int id) {
        this.id=id;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Product p1 = new Product();
        for (Product product : productService.rechercheParId(id)) {
            p1 = product;
        }

        nomProductiD.setText(p1.getNameProduct());
        prixProductID.setText(String.valueOf(p1.getPriceProduct()));
        idCategoryID.setText(String.valueOf(p1.getIdCategory()));
    }

    public void setPrimaryStage(Stage primaryStage) {

        this.primaryStage = primaryStage;
    }
    public void Browse(ActionEvent actionEvent) {
        Stage primaryStagee = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");

        File selectedFile = fileChooser.showOpenDialog(primaryStagee);

    }

}



