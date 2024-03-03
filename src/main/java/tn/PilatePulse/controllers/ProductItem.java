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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.model.RatingModel;
import tn.PilatePulse.services.RatingService;
import tn.PilatePulse.services.ShoppingCartService;
import tn.PilatePulse.services.WishListService;


import org.controlsfx.control.Rating;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductItem implements Initializable {

    @FXML
    private Button cartButton;
    @FXML
    private Button wishListButton;
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
    @FXML
    private Label productDescriptionLabel;
    @FXML
    private Button addToWishListButton;
    @FXML
    private Button submitButton;
    @FXML
    private Rating ratingStarsId;



    private Product product;
    ShoppingCartService shoppingCartService = new ShoppingCartService();
    WishListService wishListService = new WishListService();





    void setPassedProduct(Product product){
        this.product=product;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idProductLabel.setText("REF: "+product.getIdProduct()+"");
        nameProductLabel.setText(product.getNameProduct());
        priceLabel.setText(product.getPriceProduct()+""+" TND");
        productStockLabel.setText("Rest in stock: "+product.getStock()+"");
        productDescriptionLabel.setText(product.getProductDescription());

        String demonstrationPath = product.getImage();
        Image demonstrationImage = new Image(new File(demonstrationPath).toURI().toString());
        productImage.setImage(demonstrationImage);

        if (ratingStarsId != null) {
            // You can safely invoke methods on ratingStarsId here
            ratingStarsId.setPartialRating(true);
        } else {
            System.out.println("Rating control is null.");
        }

    }





    @FXML
    void addToCart(ActionEvent event) {
        int quantity;
        try {
            quantity = Integer.parseInt(quantityField.getText());
            int currentStock = product.getStock();
            if (quantity > currentStock) {
                // Quantity exceeds stock, show alert
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("The quantity exceeds the available stock of the product (" + currentStock + "). Please enter a valid quantity.");
                alert.showAndWait();
                return;
            }
            shoppingCartService.add(product, quantity);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid numeric quantity.");
            alert.showAndWait();
        }
    }

    @FXML
    void addTowishList(ActionEvent event) {
        wishListService.add(product);
    }



    @FXML
    void returnToShop(ActionEvent event) {
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
    void displayCart(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShoppingCart.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void displayWishList(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayWishList.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void submitRating(ActionEvent event) {
        double selectedStars = ratingStarsId.getRating();
        RatingModel rating = new RatingModel(product.getIdProduct(),1,selectedStars);
        RatingService ratingService = new RatingService();
        ratingService.addRating(rating);
        System.out.println(selectedStars);

    }

    @FXML
    void search(ActionEvent event) {

    }


}
