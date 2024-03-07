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
import javafx.stage.Stage;
import tn.PilatePulse.model.Category;
import tn.PilatePulse.model.Product;
import tn.PilatePulse.services.CategoryService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CategoriesDashboard implements Initializable {

    @FXML
    private Button addID;

    @FXML
    private MFXButton categoriesButton;

    @FXML
    private MFXLegacyListView<Category> categoriesList;

    @FXML
    private Button deleteid;

    @FXML
    private MFXButton homeButton;

    @FXML
    private ImageView logoImg;

    @FXML
    private AnchorPane navBar;

    @FXML
    private MFXButton productButton;

    @FXML
    private MFXButton searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private MFXButton shopButton;

    @FXML
    private Label titleLabe;

    @FXML
    private Button updateid;

    CategoryService categoryService = new CategoryService();
    private Stage primaryStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoriesList.setCellFactory(param -> new ListCell<>() {

            @Override
            protected void updateItem(Category category, boolean empty) {
                super.updateItem(category, empty);

                if (empty || category == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    GridPane container = new GridPane();

                    TextFlow textFlow = new TextFlow();


                    String labelStyle = "-fx-fill: #9b8385;  -fx-font-size: 27  ;";
                    String nameStyle = "-fx-fill: #8b7080;  -fx-font-size: 40;";

                    String dataStyle = "-fx-fill: #9b8385; -fx-font-size: 20;";


                    Text nameData = new Text(category.getNameCategory() + "\n");
                    nameData.setStyle(nameStyle);

                    Text categoryIdText = new Text("Reference: ");
                    categoryIdText.setStyle(labelStyle);
                    Text categoryIdData = new Text(category.getIdCategory() + "\n");
                    categoryIdData.setStyle(dataStyle);



                    textFlow.getChildren().addAll(nameData, categoryIdText, categoryIdData);
                    container.add(textFlow, 1, 0);
                    ColumnConstraints columnConstraints = new ColumnConstraints();
                    columnConstraints.setHgrow(Priority.ALWAYS);
                    container.getColumnConstraints().addAll(columnConstraints, columnConstraints);

                    //container.setHgap(30);

                    setGraphic(container);

                }
            }
        });

        categoriesList.getItems().addAll(categoryService.fetchCategory());

    }
    @FXML
    void shopDashboard(ActionEvent event) {
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
    void productsDashBoard(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayProduct.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void displayDashboard(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminDashBoard.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void categoriesDashBoard(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CategoriesDashboard.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void UpdateButton(ActionEvent event) {
        Category selectedCategory = categoriesList.getSelectionModel().getSelectedItem();

        if (selectedCategory == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Category Selected");
            alert.setContentText("Please select a category to update.");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateCategory.fxml"));
                loader.setControllerFactory(controllerClass -> {
                    if (controllerClass == UpdateCategory.class) {
                        UpdateCategory updateCategory = new UpdateCategory();
                        int id = selectedCategory.getIdCategory();
                        updateCategory.setPassedId(id);
                        return updateCategory;
                    } else {
                        return new UpdateCategory();
                    }
                });

                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.setOnHidden(event1 -> {
                    updateListView();
                });

                stage.show();

                UpdateCategory updateCategory = loader.getController();
                updateCategory.setPrimaryStage(primaryStage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void updateListView() {
        categoriesList.getItems().setAll(categoryService.fetchCategory());
    }



    @FXML
    void addButton(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddCategory.fxml"));
            Parent root = loader.load();

            Scene currentScene = ((Node) event.getSource()).getScene();

            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void deleteButton(ActionEvent event) {
        Category selectedCategory = categoriesList.getSelectionModel().getSelectedItem();

        if (selectedCategory == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Category Selected");
            alert.setContentText("Please select a category to delete.");
            alert.showAndWait();
        } else {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmAlert.setTitle("Confirm Deletion");
            confirmAlert.setHeaderText("Are you sure you want to delete this category?");
            confirmAlert.setContentText("Click OK to confirm or Cancel");

            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                int id = selectedCategory.getIdCategory();
                categoryService.remove(id);
                categoriesList.getItems().remove(selectedCategory);
            }
        }
    }




    @FXML
    void search(ActionEvent event) {

    }



}
