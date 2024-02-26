package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.PilatePulse.model.Category;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.services.ProductService;
import tn.PilatePulse.util.MaConnexion;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AddProduct {


    @FXML
    private Button addButtonid;

    @FXML
    private MFXTextField descriptionID;

    @FXML
    private MFXButton eventsButton;

    @FXML
    private MFXButton exercicesButton;

    @FXML
    private MFXButton homeButton;

    @FXML
    private MFXTextField idCategoryID;

    @FXML
    private MFXTextField imgURLId;

    @FXML
    private ImageView logoImg;

    @FXML
    private Label logoLabel;

    @FXML
    private MFXTextField nomProductiD;

    @FXML
    private MFXTextField prixProductID;

    @FXML
    private MFXButton programsButton;

    @FXML
    private MFXButton shopButton;

    @FXML
    private MFXTextField stockID;

    @FXML
    private Label titleLabe;

    @FXML
    private AnchorPane topBar;

    @FXML
    private Button uploadButton;
    private final ProductService productService = new ProductService();




    public void add(javafx.event.ActionEvent actionEvent) {
        productService.add(new Product(
                nomProductiD.getText(),
                descriptionID.getText(),
                imgURLId.getText(),
                Float.parseFloat(prixProductID.getText()),
                Integer.parseInt(stockID.getText()),
                Integer.parseInt((idCategoryID.getText()))
                ));
        //Category category = categoryComboBox.getValue();
    }

    @FXML
    void browse(ActionEvent event) {
        Stage primaryStage = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a File");

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            String fileUrl = selectedFile.toURI().toString();
            imgURLId.setText(fileUrl);
        }
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