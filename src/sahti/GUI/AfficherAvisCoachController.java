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
import sahti.entities.Reclamation;
import sahti.services.AvisCRUD;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class AfficherAvisCoachController implements Initializable {

    @FXML
    private TextField txtRecherche;
    @FXML
    private Button btnRecherche;
    @FXML
    private TableColumn<Avis, String> colId;
    @FXML
    private TableColumn<Avis, String> colClient;
    @FXML
    private TableColumn<Avis, String> colCoach;
    @FXML
    private TableColumn<Avis, String> colCommentaire;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TableView<Avis> table;    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AvisCRUD ac = new AvisCRUD();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClient.setCellValueFactory(new PropertyValueFactory<>("client"));
        colCoach.setCellValueFactory(new PropertyValueFactory<>("coach"));
        colCommentaire.setCellValueFactory(new PropertyValueFactory<>("Commentaire"));
           // Display row data
        ObservableList<Avis> list = ac.afficherAvis("idCoach");
        
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
                avc.setlblUser("Coach");
               

                txtRecherche.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    @FXML
    private void deleteAvisCoach(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Supprimer avis coach");
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment supprimer l'avis sur le coach!");
        alert.showAndWait();
    }
    
    
}
