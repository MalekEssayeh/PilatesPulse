package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import io.github.palexdev.mfxcore.controls.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import tn.PilatePulse.model.Category;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.model.ShoppingCartModel;
import tn.PilatePulse.services.CategoryService;
import tn.PilatePulse.services.ProductService;
import tn.PilatePulse.services.ShoppingCartService;
import tn.PilatePulse.services.WishListService;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import user.Utils.UserSession;


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
private MFXButton Home;
    @FXML
    private AnchorPane topBar;

    @FXML
    private MFXButton wishlistButton;
    @FXML
    private MFXButton filterButton;
    @FXML
    private MFXComboBox<String> categoryComboBox;


    ShoppingCartService shoppingCartService = new ShoppingCartService();
    WishListService wishListService = new WishListService();
    Category category  =new Category();



    private Stage primaryStage;
    private ProductService productService = new ProductService();

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!UserSession.getRole().equals("admin"))
        Home.setVisible(false);
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
                    BufferedImage qrCodeImage = createQRImage(
                                    nameData.getText() + " : \n" +
                                    descriptionText.getText() + " \n" +
                                    descriptionData.getText() + " \n" +
                                    priceText.getText() + " \n" +
                                    priceData.getText() + " \n"+
                                    stockText.getText()+ " \n"+
                                    stockData.getText()+ " \n"+
                                    categoryText.getText()+ " \n"+
                                    categoryData.getText()
                                    , 125);
                    Image qrCodeImageFX = SwingFXUtils.toFXImage(qrCodeImage, null);
                    ImageView Qr = new ImageView(qrCodeImageFX);

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
                    ColumnConstraints col3 = new ColumnConstraints(200);
                    container.getColumnConstraints().addAll(col1, col2, col3);


                    textFlow.getChildren().addAll(nameData, descriptionText, descriptionData, priceText, priceData, stockText, stockData, categoryText, categoryData );
                    container.add(textFlow, 1, 0);
                    container.add(imageView, 0, 0);
                    container.add(Qr, 2, 0);
                    ColumnConstraints columnConstraints = new ColumnConstraints();
                    columnConstraints.setHgrow(Priority.ALWAYS);
                    container.getColumnConstraints().addAll(columnConstraints, columnConstraints,columnConstraints);

                    container.setHgap(30);

                    setGraphic(container);




                    categoryComboBox.getSelectionModel().selectFirst();





                }
            }
        });

        productList.getItems().addAll(productService.fetchProduct());
        List<String> categoryNames = productService.getAllCategoryNames();
        categoryComboBox.getItems().addAll(categoryNames);

        categoryComboBox.setOnAction(event -> {
            String selectedCategory = categoryComboBox.getValue();


            List<Product> filteredProducts = productService.filterProductsByCategoryName2(selectedCategory);


            filteredProducts.forEach(System.out::println);

        });
        categoryComboBox.getSelectionModel().selectFirst();


        productList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                 Product selectedItem = productList.getSelectionModel().getSelectedItem();
                // Perform the action you want with the selected item
                System.out.println("Double-clicked on: " + selectedItem);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProductItem.fxml"));
                loader.setControllerFactory(controllerClass -> {
                    if (controllerClass == ProductItem.class) {
                        ProductItem productItemController = new ProductItem();
                        Product productSelected = productList.getSelectionModel().getSelectedItem();
                        productItemController.setPassedProduct(productSelected);
                        return productItemController;
                    } else {
                        return new ProductItem();
                    }
                });

                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Scene scene = new Scene(root);
                stage.setScene(scene);

                stage.show();


            }
        });


    }
    public BufferedImage createQRImage( String qrCodeText, int size)
    {
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = null;
        try {
            byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        return image;
    }


    @FXML
    void filterByPrice(ActionEvent event) {
        float minPrice = 0;
        float maxPrice = (float) priceRangeSlider.getValue();

        List<Product> filteredProducts = productService.filterProductsByPriceRange(minPrice, maxPrice);

        productList.getItems().clear();
        productList.getItems().addAll(filteredProducts);
    }


    @FXML
    void Cart(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {
        String searchTerm = searchTextField.getText().trim(); // Trim whitespace

        List<Product> searchResult;
        if (searchTerm.isEmpty()) {
            searchResult = productService.fetchProduct(); // Retrieve all products
        } else {
            searchResult = productService.rechercheParNom(searchTerm);
        }

        if (searchResult.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Search Result", "No Product Found", "No product matching the search term was found.");
        } else {
            productList.getItems().clear();
            productList.getItems().addAll(searchResult);
        }
    }



    @FXML
    void filterProductByCategory(ActionEvent event) {
        List<String> categoryNames = productService.getAllCategoryNames();
        System.out.println(categoryNames);
        //categoryComboBox.getItems().addAll(categoryNames);
    }



    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    @FXML
    void addTowishList(ActionEvent event) {
        Product selectedProduct = productList.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            wishListService.add(selectedProduct);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Item added to wish list successfully!");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a product to add to wish list.");
            alert.showAndWait();
        }
    }
   /* @FXML
    void addToCart(ActionEvent event) {
        Product selectedProduct = productList.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            shoppingCartService.add(selectedProduct);

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
    void coachList(ActionEvent event) {

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
    void goBackToHome(ActionEvent event) {

    }


    public void Home(ActionEvent event) {
        if(UserSession.getRole().equals("admin"))
        {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminDashboard.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }}

    }
    public void Home1(ActionEvent event) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
                Parent root = loader.load();

                Scene currentScene = ((Node) event.getSource()).getScene();

                currentScene.setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }}


}

