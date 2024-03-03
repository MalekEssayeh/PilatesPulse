package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
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
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.services.ProductService;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateProduct implements Initializable {

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
    private Button updateButton;

    @FXML
    private Button uploadButton;





    private Stage  primaryStage;
    private int id;
    private ProductService productService = new ProductService();
    private Label nomProductID;




    @FXML
    void adminDashBoard(ActionEvent event) {

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
    void search(ActionEvent event) {

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




    @FXML
    void update(ActionEvent event) {
        String t1=imgURLId.getText().replace("%20", " ");
        t1=t1.replace("/", "\\").replace("file:\\", "");

        if (nomProductiD.getText().isEmpty() || descriptionID.getText().isEmpty() ||
                imgURLId.getText().isEmpty() || prixProductID.getText().isEmpty() ||
                stockID.getText().isEmpty() || idCategoryID.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("Please make sure all fields are filled in");
            alert.showAndWait();
        } else {
            try {
                productService.update(new Product(id,
                        nomProductiD.getText(),
                        descriptionID.getText(),
                        t1,
                        Float.parseFloat(prixProductID.getText()),
                        Integer.parseInt(stockID.getText()),
                        Integer.parseInt(idCategoryID.getText())
                ));
                Stage stage = (Stage) updateButton.getScene().getWindow();
                stage.close();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please enter valid numerical values for price, stock, and category ID");
                alert.showAndWait();
            }
        }
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
        descriptionID.setText(p1.getProductDescription());
        prixProductID.setText(String.valueOf(p1.getPriceProduct()));
        stockID.setText(String.valueOf(p1.getStock()));
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



