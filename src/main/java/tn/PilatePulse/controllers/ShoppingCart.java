package tn.PilatePulse.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import tn.PilatePulse.model.Product;

public class ShoppingCart {

    @FXML
    private Button checkoutButton;

    @FXML
    private MFXButton eventsButton;

    @FXML
    private MFXButton exercicesButton;

    @FXML
    private MFXButton homeButton;

    @FXML
    private MFXLegacyListView<?> listOfProducts;

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
    private AnchorPane topBar;

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
