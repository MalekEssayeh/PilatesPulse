package user.Controllers;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import user.Models.Promo;
import user.Models.user;
import user.Services.PromoService;
import user.Services.userService;
import user.Utils.UserSession;

import java.io.FileOutputStream;
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
    private Label numTelL;
    @FXML
    private Label codesL;

    @FXML
    private ListView<Integer> codesLV;
    private static Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    private static Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);

    private final userService userService = new userService();
    private final PromoService promoService = new PromoService();
    public void initProfile() {
        // Set user's information
        if (UserSession.getId() != 0) {
            nomL.setText(UserSession.getNom());
            prenomL.setText(UserSession.getPrenom());
            mailL.setText(UserSession.getMail());
            numTelL.setText(String.valueOf(UserSession.getNumTel()));


            // Retrieve and display code promos associated with the user's ID
            List<Promo> userPromos = promoService.searchByUserId(UserSession.getId());
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


    public void UpdateProfile(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateProfile.fxml"));
            Parent root = loader.load();
            UpdateProfile editProfileController = loader.getController();
            // Pass current user information to the edit profile controller
            editProfileController.initData(new user( UserSession.getNom(), UserSession.getPrenom(), UserSession.getMail(), UserSession.getRole(), UserSession.getMdp(), UserSession.getNumTel()));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log it)
        }
    }

    public void exportPromoToPDF(ActionEvent actionEvent) {
        Promo selectedPromo = getSelectedPromo();
        if (selectedPromo != null) {
            try {
                String fileName = "DiscountCode_info_" + selectedPromo.getCode() + ".pdf";
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(fileName));

                // Create custom colors for purple and babypink
                BaseColor purpleColor = new BaseColor(128, 0, 128); // Purple color
                BaseColor babypinkColor = new BaseColor(244, 194, 194); // Babypink color

                // Open the document
                document.open();


                // Add promo information to PDF
                Paragraph title = new Paragraph("Discount Code Details", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                title.setSpacingAfter(20f); // Add spacing after the title
                document.add(title);

                // Set font color to purple for the details
                Font purpleFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, purpleColor);

                // Add client information
                document.add(new Paragraph("Client: " + UserSession.getNom() + " " + UserSession.getPrenom(), purpleFont));
                document.add(new Paragraph("Discount Code: " + selectedPromo.getCode(), purpleFont));
                document.add(new Paragraph("Off Percentage: " + selectedPromo.getPourcentage(), purpleFont));
                document.add(new Paragraph("Expiration Date: " + selectedPromo.getValidite(), purpleFont));
                document.add(new Paragraph("Days until Expiration: " + promoService.daysUntilExpiration(selectedPromo), purpleFont));

                // Close the document
                document.close();

                showAlert(Alert.AlertType.INFORMATION, "PDF Created", "Promo details exported to PDF successfully!");
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to export promo details to PDF.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a promo code.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public Promo getSelectedPromo() {
        Integer selectedPromoCode = codesLV.getSelectionModel().getSelectedItem();
        if (selectedPromoCode != null) {
            // Retrieve the Promo object associated with the selected promo code
            return promoService.getPromoByCode(selectedPromoCode);
        } else {
            return null;
        }
    }
}
