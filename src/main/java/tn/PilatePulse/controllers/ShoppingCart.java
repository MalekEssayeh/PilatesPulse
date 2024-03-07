package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.model.ShoppingCartModel;
import tn.PilatePulse.services.ShoppingCartService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ShoppingCart implements Initializable {

    @FXML
    private Button checkoutButton;

    @FXML
    private MFXButton eventsButton;

    @FXML
    private MFXButton exercicesButton;

    @FXML
    private MFXButton homeButton;

    @FXML
    private MFXLegacyListView<ShoppingCartModel> productList;

    @FXML
    private ImageView logoImg;

    @FXML
    private MFXButton programsButton;

    @FXML
    private MFXButton searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button shopButton;

    @FXML
    private AnchorPane navBar;

    @FXML
    private Button removeButton;

    @FXML
    private Label totalLabel;

    ShoppingCartService shoppingCartService = new ShoppingCartService();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productList.setCellFactory(param -> new ListCell<>() {

            @Override
            protected void updateItem(ShoppingCartModel product, boolean empty) {
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

                    Text quantityText = new Text("Quantiy: ");
                    quantityText.setStyle(labelStyle);
                    Text quantityData = new Text(product.getQuantity() + "\n");
                    quantityData.setStyle(dataStyle);


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
                    quantityText.setWrappingWidth(200);
                    quantityData.setWrappingWidth(200);
                    ColumnConstraints col1 = new ColumnConstraints(200);
                    ColumnConstraints col2 = new ColumnConstraints(450);
                    container.getColumnConstraints().addAll(col1, col2);


                    textFlow.getChildren().addAll(nameData, descriptionText, descriptionData, priceText, priceData, quantityText, quantityData);
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

        productList.getItems().addAll(shoppingCartService.fetchProducts());
        updateTotalLabel();
    }

    @FXML
    void DeleteProductSelected(ActionEvent event) {
        ShoppingCartModel selectedProduct = productList.getSelectionModel().getSelectedItem();

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
                shoppingCartService.remove(id);
                productList.getItems().remove(selectedProduct);
                updateTotalLabel();
            }
        }
    }

    private void updateTotalLabel() {
        float total = shoppingCartService.calculateTotal();
        totalLabel.setText(String.format("%.2f", total));

        System.out.println(total);
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
    void search(ActionEvent event) {

    }

    @FXML
    void checkOutFunction(ActionEvent event) {

    }


}
