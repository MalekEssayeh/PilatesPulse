package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import io.github.palexdev.mfxcore.controls.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.services.ProductService;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayShop implements Initializable {

    @FXML
    private MFXButton addToCartButton;

    @FXML
    private MFXButton addToWishListButton;

    @FXML
    private MFXButton cartButton;

    @FXML
    private MFXButton categorynameButton;

    @FXML
    private MFXTextField categorynameFilter;

    @FXML
    private Label categorynameLabel;

    @FXML
    private MFXButton coacheButton;

    @FXML
    private MFXButton eventsButton;

    @FXML
    private MFXButton exercicesButton;

    @FXML
    private MFXButton homeButton;

    @FXML
    private ImageView logoImg;

    @FXML
    private Label logoLabel;

    @FXML
    private Label priceRangeLabe;

    @FXML
    private MFXSlider priceRangeSlider;

    @FXML
    private MFXLegacyListView<Product> productList;

    @FXML
    private MFXButton programsButton;

    @FXML
    private MFXButton searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private AnchorPane selectedItemPane;

    @FXML
    private MFXButton shopButton;

    @FXML
    private MFXButton shophomeButton;

    @FXML
    private AnchorPane topBar;

    @FXML
    private MFXButton wishlistButton;

    @FXML
    void addToCart(ActionEvent event) {

    }

    @FXML
    void addTowishList(ActionEvent event) {

    }

    @FXML
    void coachList(ActionEvent event) {

    }

    @FXML
    void displayCart(ActionEvent event) {

    }

    @FXML
    void displayWishList(ActionEvent event) {

    }

    @FXML
    void goBackToHome(ActionEvent event) {

    }

    private Stage primaryStage;
    private ProductService productService = new ProductService();
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }




    @Override
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



                    Text nameData = new Text(product.getNameProduct() + "\n");
                    //nameData.setStyle(nameStyle);

                    Text descriptionText = new Text("Description: ");
                    //evaluationText.setStyle(labelStyle);
                    Text descriptionData = new Text(product.getProductDescription() + "\n");
                    //evaluationData.setStyle(dataStyle);

                    Text priceText = new Text("Price: ");
                    //difficultyText.setStyle(labelStyle);
                    Text priceData = new Text(product.getPriceProduct() + "\n");
                    //difficultyData.setStyle(dataStyle);

                    Text stockText = new Text("quantity available: ");
                   // muscleText.setStyle(labelStyle);
                    Text stockData = new Text(product.getStock() + "\n");
                   // muscleData.setStyle(dataStyle);

                    Text categoryText = new Text("category reference: ");
                    // muscleText.setStyle(labelStyle);
                    Text categoryData = new Text(product.getIdCategory() + "\n");
                    // muscleData.setStyle(dataStyle);

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
    void Cart(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    public void clothesButtonFunction(ActionEvent actionEvent){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClothesList.fxml"));

            Parent root = loader.load();


            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();

            ClothesList clothesList = loader.getController();
            clothesList.setPrimaryStage(primaryStage);

            //Stage stage = new Stage();
          //  stage.setScene(new Scene(root));

           // stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

