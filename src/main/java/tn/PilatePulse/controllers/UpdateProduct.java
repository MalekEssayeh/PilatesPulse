package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private AnchorPane navBar;

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
    private Button updateButton;

    @FXML
    private Button uploadButton;


    private Stage  primaryStage;
    private int id;
    private ProductService productService = new ProductService();
    private Label nomProductID;




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
        System.out.println(id);
        productService.update(new Product(id,
                nomProductiD.getText(),
                descriptionID.getText(),
                imgURLId.getText(),
                Float.parseFloat(prixProductID.getText()),
                Integer.parseInt(stockID.getText()),
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



