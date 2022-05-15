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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class ItemsController implements Initializable {

    @FXML
    private ImageView imgView;
    @FXML
    private Label lblNomProduit;
    @FXML
    private Text lblDescProduit;
    @FXML
    private Label lblQte;
    @FXML
    private Label lblPrix;
    @FXML
    private Button plusOne;
    @FXML
    private Button minesOne;
    @FXML
    private Button btnDelete;
    @FXML
    private Label lblDT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void incrementQte(ActionEvent event) {
    }

    @FXML
    private void decrementQte(ActionEvent event) {
    }

    @FXML
    private void deleteProd(ActionEvent event) {
    }
    public void setlblNomProduit(String nomProd){
        this.lblNomProduit.setText(nomProd);
    }
    public void settxtDescription(String description){
        this.lblDescProduit.setText(description);
    }
    public void setlblQte(int qte){
        this.lblQte.setText(String.valueOf(qte));
    }
    public void setlblPrix(Double prix){
        this.lblPrix.setText(String.valueOf(prix));
    }
    
}
