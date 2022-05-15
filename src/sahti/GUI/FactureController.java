/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class FactureController implements Initializable {

    @FXML
    private WebView viewFacture;
    @FXML
    private Button btnEnregisterFacture;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveFacture(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Enregistrer facture");
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment enregistrer la facture!");
        alert.showAndWait();
    }   
}
