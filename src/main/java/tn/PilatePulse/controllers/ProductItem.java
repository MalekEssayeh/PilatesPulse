package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tn.PilatePulse.model.Product;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductItem implements Initializable {

    @FXML
    private Button addToCartButton;

    @FXML
    private MFXButton eventsButton;

    @FXML
    private MFXButton exercicesButton;

    @FXML
    private MFXButton homeButton;

    @FXML
    private Label idProductLabel;

    @FXML
    private ImageView logoImg;

    @FXML
    private Label nameProductLabel;

    @FXML
    private AnchorPane navBar;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView productImage;

    @FXML
    private MFXButton programsButton;

    @FXML
    private MFXButton quantityButton;

    @FXML
    private MFXTextField quantityField;

    @FXML
    private MFXButton searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button shopButton;
    @FXML
    private Label productStockLabel;


    private Product product;



    void setPassedProduct(Product product){
        this.product=product;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idProductLabel.setText("REF: "+product.getIdProduct()+"");
        nameProductLabel.setText(product.getNameProduct());
        priceLabel.setText(product.getPriceProduct()+""+"TND");
        productStockLabel.setText("Rest in stock: "+product.getStock()+"");

        String demonstrationPath = product.getImage();
        Image demonstrationImage = new Image(new File(demonstrationPath).toURI().toString());
        productImage.setImage(demonstrationImage);

        System.out.println(product);
    }



    @FXML
    void addToCart(ActionEvent event) {

    }

    @FXML
    void getWantedQuantity(ActionEvent event) {

    }

    @FXML
    void returnToShop(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }


}
