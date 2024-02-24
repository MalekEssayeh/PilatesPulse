package user.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import user.Services.userService;
import user.Models.user;

public class Profile {
    @FXML
    private Label mailL;

    @FXML
    private Label nomL;

    @FXML
    private Label prenomL;

    public void initData(user user) {
        nomL.setText(user.getNom());
        prenomL.setText(user.getPrenom());
        mailL.setText(user.getMail());

    }
}
