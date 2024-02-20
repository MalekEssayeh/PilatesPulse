package org.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import org.esprit.models.Commande;
import org.esprit.services.CommandeService;

import java.util.List;

public class AfficherOrder {

    @FXML
    private ListView<Commande> listeProd;

    @FXML
    private ListView<Commande> listeCodePromo;

    @FXML
    private ListView<Commande> listeTotal;


    private final CommandeService rc = new CommandeService();

    @FXML
    void initialize() {

            List<Commande> commandeList = rc.fetch();

            // Set the items for all the list views
            ObservableList<Commande> observableList = FXCollections.observableList(commandeList);
            listeProd.setItems(observableList);
            listeCodePromo.setItems(observableList);
            listeTotal.setItems(observableList);


            // Customize cell appearance for each ListView
            listeProd.setCellFactory(param -> new ListCell<Commande>() {
                @Override
                protected void updateItem(Commande item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getNomProd());
                    }
                }
            });

            listeCodePromo.setCellFactory(param -> new ListCell<Commande>() {
                @Override
                protected void updateItem(Commande item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        // Assuming getImage() returns Image object
                        ImageView imageView = new ImageView(item.getCodePromo());
                        imageView.setFitWidth(80);
                        imageView.setFitHeight(80);
                        setGraphic(imageView);
                    }
                }
            });

            listeTotal.setCellFactory(param -> new ListCell<Commande>() {
                @Override
                protected void updateItem(Commande item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        try {
                            int totalValue = Integer.parseInt(String.valueOf(item.getTotal()));
                            setText(String.valueOf(totalValue));
                        } catch (NumberFormatException e) {
                            // Handle the case where parsing fails
                            setText("Invalid Total");
                        }
                    }
                }
            });


        }

    public void Delete(ActionEvent actionEvent) {
        Commande selectCommande = listeProd.getSelectionModel().getSelectedItem();
        if (selectCommande != null) {
            listeProd.getItems().remove(selectCommande);
            rc.supprimer(selectCommande.getIdCmd());
        }else {
            System.out.println("Choose an order to delete");
        }

    }

    public void Update(ActionEvent actionEvent) {

        Commande selectedCommande = listeProd.getSelectionModel().getSelectedItem();
        if (selectedCommande != null) {
            System.out.println("Vous pouvez mettre à jour la commande sélectionnée : " + selectedCommande.getNomProd());
        } else {
            System.out.println("Veuillez sélectionner une commande à mettre à jour.");
        }
    }
}


