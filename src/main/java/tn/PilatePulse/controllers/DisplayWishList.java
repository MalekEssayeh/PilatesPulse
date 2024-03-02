package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.model.ShoppingCartModel;
import tn.PilatePulse.model.WishList;
import tn.PilatePulse.services.ShoppingCartService;
import tn.PilatePulse.services.WishListService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DisplayWishList implements Initializable {

    @FXML
    private Button addToCartButton;

    @FXML
    private MFXButton eventsButton;

    @FXML
    private MFXButton exercicesButton;

    @FXML
    private MFXButton homeButton;

    @FXML
    private ImageView logoImg;

    @FXML
    private AnchorPane navBar;

    @FXML
    private MFXLegacyListView<WishList> productList;

    @FXML
    private MFXButton programsButton;

    @FXML
    private Button removeButton;

    @FXML
    private MFXButton searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button shopButton;


    WishListService wishListService = new WishListService();
    ShoppingCartService shoppingCartService = new ShoppingCartService();




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productList.setCellFactory(param -> new ListCell<>() {

            @Override
            protected void updateItem(WishList product, boolean empty) {
                super.updateItem(product, empty);

                if (empty || product == null) {
                    setText(null);
                    setGraphic(null);
                } else {


                    GridPane container = new GridPane();

                    TextFlow textFlow = new TextFlow();


                    String labelStyle = "-fx-fill: #9b8385;  -fx-font-size: 27  ;";
                    String nameStyle = "-fx-fill: #8b7080;  -fx-font-size: 40;";

                    String dataStyle = "-fx-fill: #9b8385; -fx-font-size: 20;";


                    Text nameData = new Text(product.getNameProduct() + "\n");
                    nameData.setStyle(nameStyle);

                    Text descriptionText = new Text("Description: ");
                    descriptionText.setStyle(labelStyle);
                    Text descriptionData = new Text(product.getProductDescription() + "\n");
                    descriptionData.setStyle(dataStyle);

                    Text priceText = new Text("Price: ");
                    priceText.setStyle(labelStyle);
                    Text priceData = new Text(product.getPriceProduct() + "\n");
                    priceData.setStyle(dataStyle);


                    String imagePath = product.getImage();
                    Image productImage = new Image(new File(imagePath).toURI().toString());
                    ImageView imageView = new ImageView(productImage);
                    imageView.setFitHeight(200);
                    imageView.setFitWidth(200);
                    nameData.setWrappingWidth(200);
                    descriptionData.setWrappingWidth(200);
                    descriptionText.setWrappingWidth(200);
                    priceText.setWrappingWidth(200);
                    priceData.setWrappingWidth(200);
                    ColumnConstraints col1 = new ColumnConstraints(200);
                    ColumnConstraints col2 = new ColumnConstraints(450);
                    container.getColumnConstraints().addAll(col1, col2);


                    textFlow.getChildren().addAll(nameData, descriptionText, descriptionData, priceText, priceData);
                    container.add(textFlow, 1, 0);
                    container.add(imageView, 0, 0);
                    ColumnConstraints columnConstraints = new ColumnConstraints();
                    columnConstraints.setHgrow(Priority.ALWAYS);
                    container.getColumnConstraints().addAll(columnConstraints, columnConstraints);

                    container.setHgap(30);

                    setGraphic(container);


                }
            }
        });

        productList.getItems().addAll(wishListService.fetchProducts());
    }



    @FXML
    void DeleteProductSelected(ActionEvent event) {
        WishList selectedProduct = productList.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product to delete.");
            alert.showAndWait();
        } else {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Deletion");
            confirmAlert.setHeaderText("Are you sure you want to delete this item?");
            confirmAlert.setContentText("Click OK to confirm or Cancel");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                int id = selectedProduct.getIdProduct();
                wishListService.remove(id);
                productList.getItems().remove(selectedProduct);
            }
        }

    }

    /*@FXML
    void addToCart(ActionEvent event) {
        WishList selectedProduct = productList.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            shoppingCartService.addToWishList(selectedProduct);
            wishListService.remove(selectedProduct.getIdProduct());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Item added to cart successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a product to add to cart.");
            alert.showAndWait();
        }
    }*/

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
    void search(ActionEvent event) {

    }


}
