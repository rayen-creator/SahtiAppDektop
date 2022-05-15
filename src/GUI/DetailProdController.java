/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class DetailProdController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label prodnom;
    @FXML
    private Label prixprod;
    @FXML
    private Button btnmoin;
    @FXML
    private Label lblqte;
    @FXML
    private Button btnplus;
    @FXML
    private Label descprod;
    @FXML
    private Button btnajoutpanier;
    @FXML
    private Button topanier;
    @FXML
    private Button btncom;
    @FXML
    private TextArea txtcom;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button goback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void decrementQte(ActionEvent event) {
    }

    @FXML
    private void incrementQte(ActionEvent event) {
    }

    @FXML
    private void ajoutpanier(ActionEvent event) {
    }

    @FXML
    private void topanier(ActionEvent event) {
    }

    @FXML
    private void goback(ActionEvent event) {
    }
    
}
