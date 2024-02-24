package tn.PilatePulse.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import tn.PilatePulse.model.Category;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.services.ProductService;
import tn.PilatePulse.util.MaConnexion;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddProduct {

    @FXML
    private TextField idCategoryID;

    @FXML
    private TextField nomProductiD;

    @FXML
    private TextField prixProductID;
    @FXML
    private TextField stockID;
    @FXML
    private TextArea descriptionID;
    @FXML
    private ComboBox<Category> categoryComboBox;
    private final ProductService productService = new ProductService();





    public void add(javafx.event.ActionEvent actionEvent) {
        productService.add(new Product(
                nomProductiD.getText(),
                descriptionID.getText(),
                Float.parseFloat(prixProductID.getText()),
                Integer.parseInt(stockID.getText()),
                Integer.parseInt((idCategoryID.getText()))
                ));
        Category category = categoryComboBox.getValue();
    }

   /* @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Category> categories = new ArrayList<>();
        categoryComboBox.getItems().addAll(categories);
    }*/




     /* @FXML
    void ajouter(ActionEvent event) {

        productService.add2(nomProductiD.getText(),
                Float.parseFloat(prixProductID.getText()),
                Integer.parseInt(idCategoryID.getText()));

        try {
        productService.add2(nomProductiD.getText(),
                Float.parseFloat(prixProductID.getText()),
                Integer.parseInt(idCategoryID.getText()));

    } catch (SQLException e) {
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle("error");
        alert.setContentText(e.getMessage());
        alert.showAndWait();

    }
}
    }*/
     /*   public void initialize() {
        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM category");
            List<Category> categories = new ArrayList<>();
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt("idCategory"), resultSet.getString("nameCategory")));
            }
            ObservableList<Category> observableCategories = FXCollections.observableArrayList(categories);


            categoryComboBox.setItems((ObservableList<Category>) categories.stream().collect(Collectors.toUnmodifiableList()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

}