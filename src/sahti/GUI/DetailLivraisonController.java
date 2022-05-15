/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class DetailLivraisonController implements Initializable {

    @FXML
    private Text lblNumCmd;
    @FXML
    private Text lblDateCmd;
    @FXML
    private Text lblClient;
    @FXML
    private Text lblAdresse;
    @FXML
    private Text lblNumTel;
    @FXML
    private Text lblEtatLivraison;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setlblNumCmd(String message){
        this.lblNumCmd.setText(message);
    }
    public void setlblDaetCmd(String message){
        this.lblDateCmd.setText(message);        
    }
    public void setlblAdresse(String message){
        this.lblAdresse.setText(message);                
    }
    public void setlblNumTel(String message){
        this.lblNumTel.setText(message);
    }
    public void setlblEtatLivraison(String message){
        this.lblEtatLivraison.setText(message);
    }
}
