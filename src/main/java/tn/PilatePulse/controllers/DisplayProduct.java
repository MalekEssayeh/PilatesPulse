package tn.PilatePulse.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.services.ProductService;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplayProduct implements Initializable {
    @FXML
    private ListView<Product> listProducts;
    @FXML
    private Label label1;
    @FXML
    private Button deleteid;
    @FXML
    private Button updateid;

    ProductService productService = new ProductService();
    private Stage primaryStage;

    public void initialize(URL url, ResourceBundle resourceBundle) {


        listProducts.setCellFactory(pram -> new ListCell<>() {

            protected void updateItem(Product product, boolean empty) {
                super.updateItem(product, empty);
                if (empty || product == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    TextFlow textFlow = new TextFlow();

                    Text nameText = new Text("Name: ");

                    Text nameData = new Text(product.getNameProduct() + "\n");


                    Text idText = new Text("ID: ");

                    Text idData = new Text(product.getIdProduct() + "\n");


                    Text categoryText = new Text("Category ID: ");

                    Text categoryData = new Text(product.getIdCategory() + "\n");


                    textFlow.getChildren().addAll(nameText, nameData, idText, idData, categoryText, categoryData);

                    setGraphic(textFlow);
                }
            }

        });
        listProducts.getItems().addAll(productService.fetchProduct());
    }


    public void deleteButton(ActionEvent actionEvent) {
        Product selectedExercice = listProducts.getSelectionModel().getSelectedItem();

        if (selectedExercice != null) {
            int id = selectedExercice.getIdProduct();
            productService.remove(id);
            listProducts.getItems().remove(selectedExercice);
        }
    }

    public void UpdateButton(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateProduct.fxml"));
            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == UpdateProduct.class) {
                    UpdateProduct updateProduct = new UpdateProduct();
                    Product selectedProduct = listProducts.getSelectionModel().getSelectedItem();
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
        listProducts.getItems().setAll(productService.fetchProduct());
    }
}

