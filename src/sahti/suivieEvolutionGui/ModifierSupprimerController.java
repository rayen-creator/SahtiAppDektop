/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.suivieEvolutionGui;


import entities.EncapsulationSuivieEvolution;
import entities.SuivieEvolution;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
public class ModifierSupprimerController implements Initializable {

    @FXML
    private TextField in_user;
    @FXML
    private TextField in_poids;
    @FXML
    private TextField in_date_programme;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    @FXML
    private TextField in_date_evolution;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        in_poids.setText(Integer.toString(EncapsulationSuivieEvolution.getPoids()));
        in_user.setText(Integer.toString(EncapsulationSuivieEvolution.getIdUser()));
        in_date_programme.setText(EncapsulationSuivieEvolution.getDateDebutProgramme().toString());
        in_date_evolution.setText(EncapsulationSuivieEvolution.getDateEvolution().toString());
    }    

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher.fxml"));
        try {
            Parent root = loader.load();
            in_date_evolution.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
         SuivieEvolution se = new SuivieEvolution(EncapsulationSuivieEvolution.getId());
        SuivieEvolutionCRUD sec = new SuivieEvolutionCRUD();
        sec.supprimerSuivieEvolution(se);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher.fxml"));
        try {
            Parent root = loader.load();
            btn_retour.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        
            SuivieEvolution se = new SuivieEvolution(EncapsulationSuivieEvolution.getId(), Integer.parseInt(in_user.getText()), Integer.parseInt(in_poids.getText()), Date.valueOf(in_date_programme.getText()),Date.valueOf(in_date_evolution.getText()));
            SuivieEvolutionCRUD sec = new SuivieEvolutionCRUD();
            sec.modifierSuivieEvolution(se);
            JOptionPane.showMessageDialog(null, "Suivie Evolution modifiée!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher.fxml"));
            try {
                Parent root = loader.load();
                in_date_evolution.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        
    }
    
}
