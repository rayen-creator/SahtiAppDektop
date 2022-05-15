/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sahti.entities.Avis;
import sahti.services.AvisCRUD;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class AfficherAvisProduitController implements Initializable {

    @FXML
    private TextField txtRecherche;
    @FXML
    private Button btnRecherche;
    @FXML
    private TableColumn<Avis, String> colId;
    @FXML
    private TableColumn<Avis, String> colClient;
    @FXML
    private TableColumn<Avis, String> colProduit;
    @FXML
    private TableColumn<Avis, String> colCommentaire;
    @FXML
    private Button btnSupprimer;
    @FXML
    TableView<Avis> table;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AvisCRUD ac = new AvisCRUD();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClient.setCellValueFactory(new PropertyValueFactory<>("client"));
        colProduit.setCellValueFactory(new PropertyValueFactory<>("produit"));
        colCommentaire.setCellValueFactory(new PropertyValueFactory<>("Commentaire"));
           // Display row data
        ObservableList<Avis> list = ac.afficherAvis("idProduit");
        table.setItems(list);
    }    
@FXML
    private void getSelectedAvis(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Avis a = table.getSelectionModel().getSelectedItem();
            //Ajouter new scene avec une bouton close 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAvis.fxml"));

            try {
                Parent root = loader.load();

                AfficherAvisController avc = loader.getController();
                avc.setRating1(a.getRating());
                avc.setlblCommentaire(a.getCommentaire());
                avc.setlblNom("Akrimi Iheb");
                avc.setlblUser("Produit");
               

                txtRecherche.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    @FXML
    private void deleteAvisClient(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Supprimer avis Produit");
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment supprimer l'avis sur le produit!");
        alert.showAndWait();
    }
    
}
