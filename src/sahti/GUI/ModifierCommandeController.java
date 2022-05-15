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
import javafx.stage.Stage;
import sahti.entities.Commande;
import sahti.entities.Livraison;
import sahti.services.CommandeCRUD;
import sahti.services.LivraisonCRUD;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class ModifierCommandeController implements Initializable {

    @FXML
    private Button btnEtat;
    @FXML
    private Button btnLivrer;
    private int id;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void etatPay(ActionEvent event) {
        CommandeCRUD cr = new CommandeCRUD();
        Commande c = new Commande(id);
        cr.modifierEtatCommande(c);
        Stage stage = (Stage) btnEtat.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void updateCmdLivrer(ActionEvent event) {        
        Commande c = new Commande(id);
        Livraison l = new Livraison("Livrée");
        LivraisonCRUD lc = new LivraisonCRUD();
        lc.livrer(l,c);
        Stage stage = (Stage) btnEtat.getScene().getWindow();
        stage.close();
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
