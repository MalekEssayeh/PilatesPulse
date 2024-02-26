package user.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import user.Services.userService;
import user.Models.user;
import user.Models.Promo;
import user.Services.PromoService;

import java.io.IOException;
import java.util.List;

public class Profile {
    @FXML
    private Label mailL;

    @FXML
    private Label nomL;

    @FXML
    private Label prenomL;
    @FXML
    private Label codesL;

    @FXML
    private ListView<Integer> codesLV;

    private final userService userService = new userService();
    private final PromoService promoService = new PromoService();

    private user loggedInUser;
    public void setLoggedInUser(user user) {
        this.loggedInUser = user;
        initProfile();
    }
    private void initProfile() {
        // Set user's information
        if (loggedInUser != null) {
            nomL.setText(loggedInUser.getNom());
            prenomL.setText(loggedInUser.getPrenom());
            mailL.setText(loggedInUser.getMail());

            // Retrieve and display code promos associated with the user's ID
            List<Promo> userPromos = promoService.searchByUserId(loggedInUser.getId());
            for (Promo promo : userPromos) {
                codesLV.getItems().add(promo.getCode());
            }
        } else {
            System.out.println("No logged-in user found.");
        }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Home.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }


}
