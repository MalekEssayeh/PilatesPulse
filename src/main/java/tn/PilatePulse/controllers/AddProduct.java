package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import java.io.IOException;
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
    private MFXButton categoriesButton;

    @FXML
    private MFXTextField descriptionID;

    @FXML
    private MFXButton homeButton1;

    @FXML
    private MFXTextField idCategoryID;

    @FXML
    private MFXTextField imgURLId;

    @FXML
    private ImageView logoImg;

    @FXML
    private Label logoLabel;

    @FXML
    private AnchorPane navBar;

    @FXML
    private MFXTextField nomProductiD;

    @FXML
    private MFXTextField prixProductID;

    @FXML
    private MFXButton productButton;

    @FXML
    private MFXButton searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private MFXButton shopButton;

    @FXML
    private MFXTextField stockID;

    @FXML
    private Label titleLabe;

    @FXML
    private Button uploadButton;



    private final ProductService productService = new ProductService();


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
    void adminDashBoard(ActionEvent event) {
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
    void search(ActionEvent event) {

    }



  public void add(javafx.event.ActionEvent actionEvent) {
      String t1=imgURLId.getText().replace("%20", " ");
      t1=t1.replace("/", "\\").replace("file:\\", "");
      if (nomProductiD != null && descriptionID != null && imgURLId != null &&
              prixProductID != null && stockID != null && idCategoryID != null &&
              !nomProductiD.getText().isEmpty() && !descriptionID.getText().isEmpty() &&
              !imgURLId.getText().isEmpty() && !prixProductID.getText().isEmpty() &&
              !stockID.getText().isEmpty() && !idCategoryID.getText().isEmpty()) {

          try {
              Product productToAdd = new Product(
                      nomProductiD.getText(),
                      descriptionID.getText(),
                      t1,
                      Float.parseFloat(prixProductID.getText()),
                      Integer.parseInt(stockID.getText()),
                      Integer.parseInt(idCategoryID.getText())
              );

              productService.add(productToAdd);
          } catch (NumberFormatException e) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Error");
              alert.setHeaderText("Invalid Input");
              alert.setContentText("Please enter valid numerical values for price, stock, and category ID");
              alert.showAndWait();
          }
      } else {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Error");
          alert.setHeaderText("Empty Fields");
          alert.setContentText("Please make sure all fields are filled in");
          alert.showAndWait();
      }
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