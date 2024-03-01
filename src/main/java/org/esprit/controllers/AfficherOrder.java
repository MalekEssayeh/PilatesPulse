package org.esprit.controllers;


import com.google.zxing.WriterException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.nio.file.Paths;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.esprit.models.Commande;
import org.esprit.services.CommandeService;

import java.io.IOException;
import java.util.*;


public class AfficherOrder {

    @FXML
    private ListView<Commande> listOrders;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button playGameButton;
    @FXML
    private Button updateButton;


    private ObservableList<Commande> orders;
    CommandeService cs = new CommandeService();
    @FXML
    public void initialize() {
        // Fetch the last added order from the database
        Commande lastAddedOrder = cs.fetchLastAddedOrder();

        // Check if there is a last added order
        if (lastAddedOrder != null) {
            // Populate the product name and total from the last added order
            String lastProductName = lastAddedOrder.getNomProd();
            int lastTotal = lastAddedOrder.getTotal();
        }

        // Add the fetched orders to the listOrders
        orders = FXCollections.observableArrayList(lastAddedOrder);
        listOrders.setItems(orders);

        // Show the welcome notification
        showWelcomeNotification();
    }

    private void showWelcomeNotification() {
        String message = "Thank you for trusting us, Pilates Pulse is GRATEFUL FOR YOU <3";
        Notifications.create()
                .title("Welcome!")
                .text(message)
                .darkStyle()
                .graphic(null)
                .showInformation(); // Utilisez showInformation() pour une notification rose
    }

    @FXML
    public void Delete() {
        // Implement the functionality for the delete button action
        // You can use listOrders.getSelectionModel().getSelectedItem() to get the selected item
        System.out.println("Delete Button clicked");
        Commande selectedOrder = listOrders.getSelectionModel().getSelectedItem();
        System.out.println("Deleting order: " + selectedOrder);
        orders.remove(selectedOrder);
    }

    @FXML
    public void Update() {
        System.out.println("Update Button clicked");
        Commande selectedOrder = listOrders.getSelectionModel().getSelectedItem();
        System.out.println("Updating order: " + selectedOrder);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdatePromoCode.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            UpdatePromoCode updatePromoCode = loader.getController();
            updatePromoCode.initData(selectedOrder);

            // Set a listener to refresh the ListView after the update
            stage.setOnHidden(e -> {
                String newPromoCode = updatePromoCode.getNewPromoCode();
                List<String> allowedPromoCodes = Arrays.asList("pilatespulse12", "soulmindT", "therapy22");

                if (allowedPromoCodes.contains(newPromoCode)) {
                    // Only proceed if the new promo code is in the allowed list
                    if (!newPromoCode.equals(selectedOrder.getCodePromo())) {
                        // Only proceed if the new promo code is different from the actual promo code
                        refreshListView();
                        showUpdateNotification(selectedOrder.getCodePromo(), newPromoCode);
                    } else {
                        // Display an error message if the new promo code is the same as the actual promo code
                        showErrorNotification("New promo code must be different from the actual promo code.");
                    }
                } else {
                    // Display an error message if the new promo code is not in the allowed list
                    showErrorNotification("Invalid promo code. Please enter a valid promo code.");
                }
            });

            // Use showAndWait to block processing until the window is closed
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showErrorNotification(String errorMessage) {
        Notifications.create()
                .title("Error")
                .text(errorMessage)
                .darkStyle()
                .graphic(null)
                .showError(); // Use showError() for an error notification in red
    }
    private void refreshListView() {
        // Fetch data from the database using your CommandeService
        CommandeService cs = new CommandeService();
        List<Commande> fetchedOrders = cs.fetch();

        // Clear and re-populate the ListView with fetched data
        orders.clear();
        orders.addAll(fetchedOrders);
    }
    private void showUpdateNotification(String oldPromoCode, String newPromoCode) {
        String message = String.format("You updated your promo code from %s to %s", oldPromoCode, newPromoCode);
        Notifications.create()
                .title("Promo Code Updated")
                .text(message)
                .showInformation();
    }




    public void playGame(ActionEvent actionEvent) {
        // Get the selected order
        Commande selectedOrder = listOrders.getSelectionModel().getSelectedItem();

        // Check if the total is greater than 100
        if (selectedOrder != null && selectedOrder.getTotal() > 100) {
            // Call a method to start the game
            startYogaGame();
        } else {
            // Display a message that the order does not qualify for the game
            showGameQualificationMessage();
        }

    }

    private void showGameQualificationMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Qualification");
        alert.setHeaderText(null);
        alert.setContentText("To play the game, the order total must be greater than 100.");
        alert.showAndWait();
    }


    private void startYogaGame() {
        // Define the yoga questions and their options
        List<Question> yogaQuestions = new ArrayList<>();
        yogaQuestions.add(new Question("What is the meaning of the word “Yoga”?", "C) Union", "A) Harmony", "B) Exercise", "C) Union"));
        yogaQuestions.add(new Question("What is the main focus of Hatha Yoga?", "A) Physical postures", "B) Meditation", "C) Breath control", "A) Physical postures"));
        yogaQuestions.add(new Question("In which ancient text are the Yoga Sutras compiled?", "C) Patanjali’s Yoga Sutras", "A) Rigveda", "B) Bhagavad Gita", "C) Patanjali’s Yoga Sutras"));
        yogaQuestions.add(new Question("What is the purpose of Pranayama in yoga?", "B) Breathing control", "A) Body warm-up", "C) Physical relaxation", "B) Breathing control"));
        yogaQuestions.add(new Question("Which of the following is not one of the eight limbs of yoga?", "C) Mudra", "A) Asana", "B) Niyama", "C) Mudra"));
        yogaQuestions.add(new Question("What is the warrior pose called in Sanskrit?", "A) Virabhadrasana", "B) Adho Mukha Svanasana", "C) Trikonasana", "A) Virabhadrasana"));
        yogaQuestions.add(new Question("How many vinyasas are there in a traditional Sun Salutation?", "B) 12", "A) 10", "C) 18", "B) 12"));
        yogaQuestions.add(new Question("What does Yama mean in the context of yoga philosophy?", "A) Moral observances", "B) Meditation", "C) Movement", "A) Moral observances"));
        yogaQuestions.add(new Question("Which of the following is not a branch of yoga?", "C) Ayurveda", "A) Kundalini", "B) Raja", "C) Ayurveda"));
        yogaQuestions.add(new Question("What is the translation of Savasana in English?", "B) Corpse Pose", "A) Downward-Facing Dog", "C) Tree Pose", "B) Corpse Pose"));
        yogaQuestions.add(new Question("Which pose represents the animal Cobra in yoga?", "A) Bhujangasana", "B) Tadasana", "C) Vrikshasana", "A) Bhujangasana"));
        yogaQuestions.add(new Question("What is the goal of Raja Yoga?", "B) Self-realization", "A) Physical strength", "C) Flexibility", "B) Self-realization"));
        yogaQuestions.add(new Question("What is the name of the goddess who resides at the base of the spine in Kundalini Yoga?", "C) Kundalini Shakti", "A) Saraswati", "B) Durga", "C) Kundalini Shakti"));
        yogaQuestions.add(new Question("What is the name of the yoga performed in a heated room?", "B) Bikram Yoga", "A) Ashtanga Yoga", "C) Vinyasa Yoga", "B) Bikram Yoga"));

        // Shuffle the questions to present them randomly
        Collections.shuffle(yogaQuestions);

        // Present three random questions to the user
        int correctAnswers = 0;
        for (int i = 0; i < 3; i++) {
            Question currentQuestion = yogaQuestions.get(i);
            String userAnswer = presentQuestion(currentQuestion);

            if (userAnswer != null && userAnswer.equals(currentQuestion.getCorrectOption())) {
                correctAnswers++;
            } else {
                break; // If the user answers one question wrong, exit the loop
            }
        }

        // Check if the user answered all three questions correctly
        if (correctAnswers == 3) {
            showCongratulationsMessage();
            assignPromoCode();
        } else {
            showTryAgainMessage();
        }
    }

    private String presentQuestion(Question question) {
        // You can customize the way you present the question to the user
        // For simplicity, let's use an Alert dialog with multiple-choice options

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Question");
        alert.setHeaderText(question.getQuestion());

        // Set the options as buttons
        alert.getButtonTypes().setAll(
                new ButtonType(question.getOptionA()),
                new ButtonType(question.getOptionB()),
                new ButtonType(question.getOptionC()),
                ButtonType.CANCEL
        );

        // Show the dialog and wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();

        // Check which button the user clicked and return the corresponding option
        if (result.isPresent() && result.get() != ButtonType.CANCEL) {
            return result.get().getText();
        } else {
            return null; // User canceled the dialog
        }}

    private void showTryAgainMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Try Again");
        alert.setHeaderText(null);
        alert.setContentText("You need to answer all three questions correctly to win the promo code. Please try again!");
        alert.showAndWait();
    }
    private Optional<String> showGameDialog(String tunisianWord) {
        // Implement the logic to show a dialog with multiple-choice options
        // For simplicity, we'll use a basic dialog, and you can customize it
        return new GameDialog(tunisianWord).showAndWait();
    }

    private void showCongratulationsMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText(null);
        alert.setContentText("Thank you for playing! You won a promo code for this order.\nEnjoy your discount!");
        alert.showAndWait();
    }

    private void assignPromoCode() {
        // Assuming total is a field in your Commande class
        Commande selectedOrder = listOrders.getSelectionModel().getSelectedItem();
        double total = selectedOrder.getTotal();

        // Calculate the discounted total (90% of the original total)
        double discountedTotal = total * 0.9;

        // Display a message with the discounted total
        Alert promoCodeAlert = new Alert(Alert.AlertType.INFORMATION);
        promoCodeAlert.setTitle("Discount Applied");
        promoCodeAlert.setHeaderText(null);
        promoCodeAlert.setContentText(String.format("A 10%% discount has been applied. Your new total is: %.2f", discountedTotal));
        promoCodeAlert.showAndWait();
    }


    @FXML
    void genererCodesQR(ActionEvent event) {
        CommandeService commandeService = new CommandeService();
        List<Commande> commandes = listOrders.getItems();
        for (Commande commande : commandes) {
            String commandeData = "ID: " + commande.getIdCmd() + ", Total: " + commande.getTotal() + ", codepromo: " + commande.getCodePromo() + ", NomProduit: " + commande.getNomProd();

            // Construct the file path using java.nio.file.Path
            String filePath = "C:/Users/ASUS/Desktop/qrcode/QRcode.png";
            filePath = filePath + java.nio.file.FileSystems.getDefault().getSeparator() + commande.getIdCmd() + ".png";

            try {
                QRCodeGenerator.generateQRCode(commandeData, filePath);
                System.out.println("Code QR généré pour la commande avec l'ID : " + commande.getIdCmd());
            } catch (WriterException | IOException e) {
                System.err.println("Erreur lors de la génération du code QR pour la commande avec l'ID : " + commande.getIdCmd());
                e.printStackTrace();
            }
        }
        showAlert("Success", "Codes QR générés avec succès.");

    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    @FXML
    void QRcodeButton(ActionEvent event) {
        // Get the selected order
        Commande selectedOrder = listOrders.getSelectionModel().getSelectedItem();

        if (selectedOrder != null) {
            // Construct the personalized content for the QR code
            String personalizedContent = "ID: " + selectedOrder.getIdCmd() +
                    ", Total: " + selectedOrder.getTotal() +
                    ", codepromo: " + selectedOrder.getCodePromo() +
                    ", NomProduit: " + selectedOrder.getNomProd();

            // Call the genererCodesQR method with the personalized content
            genererCodesQR(personalizedContent);

            // Show a success message or perform additional actions if needed
            showAlert("Success", "QR code generated successfully.");
        } else {
            // Show an error message if no order is selected
            showAlert("Error", "Please select an order before generating a QR code.");
        }
    }

    @FXML
    void genererCodesQR(String personalizedContent) {
        // Specify the file path for the generated QR code
        String filePath = "C:/Users/ASUS/Desktop/qrcode/QRcode.png";

        try {
            // Generate the QR code with the personalized content
            QRCodeGenerator.generateQRCode(personalizedContent, filePath);
            System.out.println("QR code generated successfully.");

            // Show a success message or perform additional actions if needed
            showAlert("Success", "QR code generated successfully.");
        } catch (WriterException | IOException e) {
            System.err.println("Error generating QR code.");
            e.printStackTrace();

            // Show an error message or perform additional error-handling actions if needed
            showAlert("Error", "Failed to generate QR code.");
        }


}


//    @FXML
//    void search(ActionEvent event) {
//        String keyword = searchTextField.getText();
//        List<Commande> searchResults = cs.search(keyword);
//        ObservableList<user> observableSearchResults = FXCollections.observableArrayList(searchResults);
//        usersLV.setItems(observableSearchResults);
//    }
}
