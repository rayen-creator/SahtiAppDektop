/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.ProgrammeEntraineurGui;

import entities.EncapsulationProgrammeEntraineur;
import entities.ProgrammeEntraineur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;



import javafx.scene.control.TextArea;
import sahti.services.ProgrammeEntraineurCRUD;


/**
 * FXML Controller class
 *
 * @author masso
 */
public class ModifierSupprimerController implements Initializable {

    @FXML
    private Label l_user;
    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    @FXML
    private TextArea in_idExercice;
    @FXML
    private TextField in_pack;
    @FXML
    private TextField in_type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        in_idExercice.setText((EncapsulationProgrammeEntraineur.getIdExercice()));
        
        in_type.setText(EncapsulationProgrammeEntraineur.getType());
        in_pack.setText(EncapsulationProgrammeEntraineur.getNomPack());
        

    }

    @FXML
    private void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher.fxml"));
        try {
            Parent root = loader.load();
            in_idExercice.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        ProgrammeEntraineur pe = new ProgrammeEntraineur(EncapsulationProgrammeEntraineur.getId(), (in_idExercice.getText()), in_pack.getText(), in_type.getText());
         
        ProgrammeEntraineurCRUD pec = new ProgrammeEntraineurCRUD();
        pec.modifierProgrammeEntraineur(pe);
        JOptionPane.showMessageDialog(null, "ProgrammeEntraineur modifié!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher.fxml"));
        try {
            Parent root = loader.load();
            in_idExercice.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    private void supprimer(ActionEvent event) {
         
            ProgrammeEntraineur pe = new ProgrammeEntraineur(EncapsulationProgrammeEntraineur.getId());
            ProgrammeEntraineurCRUD pec = new ProgrammeEntraineurCRUD();
            pec.supprimerProgrammeEntraineur(pe);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher.fxml"));
            try {
                Parent root = loader.load();
                btn_retour.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        

    }

}
