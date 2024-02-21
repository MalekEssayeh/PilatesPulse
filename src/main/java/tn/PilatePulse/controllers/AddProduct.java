package tn.PilatePulse.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.PilatePulse.services.ProductService;

import java.sql.SQLException;

public class AddProduct {

    @FXML
    private TextField idCategoryID;

    @FXML
    private TextField nomProductiD;

    @FXML
    private TextField prixProductID;
    private final ProductService productService = new ProductService();

    @FXML
    void ajouter(ActionEvent event) {

        productService.add2(nomProductiD.getText(),
                Float.parseFloat(prixProductID.getText()),
                Integer.parseInt(idCategoryID.getText()));

        /*try {
        productService.add2(nomProductiD.getText(),
                Float.parseFloat(prixProductID.getText()),
                Integer.parseInt(idCategoryID.getText()));

    } catch (SQLException e) {
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle("error");
        alert.setContentText(e.getMessage());
        alert.showAndWait();

    }
}*/
    }

    public void add(javafx.event.ActionEvent actionEvent) {
        productService.add2(nomProductiD.getText(),
                Float.parseFloat(prixProductID.getText()),
                Integer.parseInt(idCategoryID.getText()));
    }
}