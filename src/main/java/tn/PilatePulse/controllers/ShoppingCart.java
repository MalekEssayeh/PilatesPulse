package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.model.ShoppingCartModel;
import tn.PilatePulse.services.ShoppingCartService;

import java.io.File;
import java.net.URL;
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

        productList.getItems().addAll(shoppingCartService.fetchProducts());

    }




    @FXML
    void checkOutFunction(ActionEvent event) {

    }

    @FXML
    void returnToShop(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }


}
