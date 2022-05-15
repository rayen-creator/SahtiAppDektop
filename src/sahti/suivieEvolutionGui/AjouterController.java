/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.suivieEvolutionGui;



import entities.SuivieEvolution;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import services.SuivieEvolutionCRUD;


/**
 * FXML Controller class
 *
 * @author abdou
 */
public class AjouterController implements Initializable {

    @FXML
    private TextField in_id_user;
    @FXML
    private TextField in_poids;
    @FXML
    private TextField in_date_programme;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_valider;
    @FXML
    private TextField in_evolution;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher.fxml"));
            try {
                Parent root = loader.load();
                in_date_programme.getScene().setRoot(root);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void valider(ActionEvent event) {
        
        if(in_id_user.getText().isEmpty() || in_evolution.getText().isEmpty() || in_date_programme.getText().isEmpty() || in_poids.getText().isEmpty() )
        {
            JOptionPane.showMessageDialog(null, "vous devez remplir tous les champs !");
        }
        else {
        SuivieEvolutionCRUD sec = new SuivieEvolutionCRUD();
            SuivieEvolution se = new SuivieEvolution( Integer.parseInt(in_id_user.getText()),Integer.parseInt(in_poids.getText()), Date.valueOf(in_date_programme.getText()),Date.valueOf(in_evolution.getText()));
            sec.ajouterSuivieEvolution(se);
            JOptionPane.showMessageDialog(null, "suivi evolution ajouté!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher.fxml"));
            try {
                Parent root = loader.load();
                in_date_programme.getScene().setRoot(root);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        
        
    }
    

    
}
