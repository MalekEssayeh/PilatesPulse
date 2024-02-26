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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;



import javafx.stage.Stage;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.services.ProductService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayProduct implements Initializable {
    @FXML
    private Button addID;

    @FXML
    private Button deleteid;

    @FXML
    private MFXButton eventsButton;

    @FXML
    private MFXButton homeButton;

    @FXML
    private ImageView logoImg;

    @FXML
    private MFXLegacyListView<Product> productList;

    @FXML
    private MFXButton programsButton;

    @FXML
    private MFXButton searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private MFXButton shopButton;

    @FXML
    private Label titleLabe;

    @FXML
    private AnchorPane navBar;

    @FXML
    private Button updateid;




    ProductService productService = new ProductService();
    private Stage primaryStage;

    public void initialize(URL url, ResourceBundle resourceBundle) {


        productList.setCellFactory(param -> new ListCell<>() {

            @Override
            protected void updateItem(Product product, boolean empty) {
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

                    Text stockText = new Text("quantity available: ");
                    stockText.setStyle(labelStyle);
                    Text stockData = new Text(product.getStock() + "\n");
                    stockData.setStyle(dataStyle);

                    Text categoryText = new Text("category reference: ");
                    categoryText.setStyle(labelStyle);
                    Text categoryData = new Text(product.getIdCategory() + "\n");
                    categoryData.setStyle(dataStyle);

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
                    stockText.setWrappingWidth(200);
                    stockData.setWrappingWidth(200);
                    categoryText.setWrappingWidth(200);
                    categoryData.setWrappingWidth(200);
                    ColumnConstraints col1 = new ColumnConstraints(200);
                    ColumnConstraints col2 = new ColumnConstraints(450);
                    container.getColumnConstraints().addAll(col1, col2);


                    textFlow.getChildren().addAll(nameData, descriptionText, descriptionData, priceText, priceData, stockText, stockData, categoryText, categoryData );
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

        productList.getItems().addAll(productService.fetchProduct());

    }

    @FXML
    void addButton(ActionEvent event) {
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddProduct.fxml"));
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


    public void deleteButton(ActionEvent actionEvent) {
        Product selectedExercice = productList.getSelectionModel().getSelectedItem();

        if (selectedExercice != null) {
            int id = selectedExercice.getIdProduct();
            productService.remove(id);
            productList.getItems().remove(selectedExercice);
        }
    }

    public void UpdateButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateProduct.fxml"));
            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == UpdateProduct.class) {
                    UpdateProduct updateProduct = new UpdateProduct();
                    Product selectedProduct = productList.getSelectionModel().getSelectedItem();
                    int id = selectedProduct.getIdProduct();
                    updateProduct.setPassedId(id);
                    return updateProduct;
                } else {
                    return new UpdateProduct();
                }
            });

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.setOnHidden(event -> {
                updateListView();
            });

            stage.show();

            UpdateProduct updateProduct = loader.getController();
            updateProduct.setPrimaryStage(primaryStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateListView() {
        productList.getItems().setAll(productService.fetchProduct());
    }
}

