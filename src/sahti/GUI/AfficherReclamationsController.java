/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sahti.entities.Reclamation;
import sahti.services.ReclamationCRUD;
import java.util.Timer;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class AfficherReclamationsController implements Initializable {

    @FXML
    private TextField txtRecherche;
    @FXML
    private Button btnRecherche;
    @FXML
    private TableColumn<Reclamation, String> colId;
    @FXML
    private TableColumn<Reclamation, String> colNumReclamation;
    @FXML
    private TableColumn<Reclamation, String> colTitre;
    @FXML
    private TableColumn<Reclamation, String> colType;
    @FXML
    private TableColumn<Reclamation, String> colDate;
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private Button btnSupprimer;
    ReclamationCRUD reclamation= new ReclamationCRUD();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getListReclamation();
       
    }
    public void getListReclamation(){
    ReclamationCRUD rc = new ReclamationCRUD();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNumReclamation.setCellValueFactory(new PropertyValueFactory<>("numReclamation"));
        colTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateReclamation"));
        // Display row data
        ObservableList<Reclamation> list = rc.afficherReclamation();
        table.setItems(list);
         
    }

    @FXML
    private void deleteReclamation(ActionEvent event) {
        Reclamation tr = table.getSelectionModel().getSelectedItem();
        
        Optional<ButtonType> delete;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supprimer reclamation");
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment supprimer la reclamation!");
        delete = alert.showAndWait();
        if (delete.get() == ButtonType.OK) {
            Reclamation r = new Reclamation(tr.getId());
            ReclamationCRUD rc = new ReclamationCRUD();
            rc.supprimerReclamation(r);
            getListReclamation();
        }
    }

    @FXML
    private void getSelectedReclamation(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Reclamation r = table.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
            
                
               
            try {
                
            Stage primaryStage = new Stage();
                Parent root = loader.load();
                Scene scene = new Scene(root);
                primaryStage.setTitle("Details reclamation");
                primaryStage.setScene(scene);
                

                AfficherReclamationController rc = loader.getController();
                rc.setlblNumReclamation(r.getNumReclamation());
                rc.setlblTitre(r.getTitre());
                rc.setlblType(r.getType());
                rc.settxtMessage(r.getMessage());
                rc.setlblDateReclamation(r.getDateReclamation());
                rc.setImageReclamation(r.getImage());
                primaryStage.show();

                 

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    private void updateReclamation(ActionEvent event) {

        Reclamation r = table.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierReclamation.fxml"));

        try {
            Parent root = loader.load();
            ModifierReclamationController rc = loader.getController();
            rc.settxtNumReclamation(r.getNumReclamation());
            rc.settxtTitre(r.getTitre());
            rc.settxtMessage(r.getMessage());
            rc.settxtType(r.getType());

            txtRecherche.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onMouseEntered(MouseEvent event) {
        
            new java.util.Timer().schedule(
                new java.util.TimerTask() {
            @Override
            public void run() {
                try {
                    reclamation.getReclamationParEmail();
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherReclamationsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                getListReclamation();
            }
        },
        3000
        );
            

    }

}
