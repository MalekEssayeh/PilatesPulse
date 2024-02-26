package user.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.Models.Mailing;
import user.Models.user;
import user.Services.userService;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import java.io.IOException;
import java.util.Random;

public class ResetPwd {
    @FXML
    private Button backBT;

    @FXML
    private TextField mailTF;

    @FXML
    private Button resetBT;

    private final userService userService= new userService();
    private final Mailing mailing = new Mailing();



    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void back(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }

    public void Reset(ActionEvent actionEvent) throws IOException {
        String email = mailTF.getText();
        // Check if the email address exists in the database
        if (userService.emailExists(email)) {
            // Generate a verification code
            String verificationCode = generateVerificationCode();
            // Send the recovery email
            mailing.sendRecoveryCode(email, verificationCode);
            // Notify the user that the recovery email has been sent
            showAlert(Alert.AlertType.INFORMATION, "Success", "Recovery email sent. Check your inbox.");

            // Load the verification code interface
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/VerifCode.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Email address not found.");
        }
    }


    private String generateVerificationCode() {
        // Generate a random 6-digit verification code
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
