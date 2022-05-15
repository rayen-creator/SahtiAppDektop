/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.ProgrammeEntraineurGui;

import entities.ProgrammeEntraineur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
public class AjouterController implements Initializable {

    @FXML
    private Button btn_retour;
    @FXML
    private Button btn_valider;
    @FXML
    private TextArea in_idExercice;
    @FXML
    private TextField in_nomPack;
    @FXML
    private TextField in_type;
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
                in_idExercice.getScene().setRoot(root);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    private void valider(ActionEvent event) {
        
        if(in_idExercice.getText().isEmpty() || in_nomPack.getText().isEmpty() || in_type.getText().isEmpty() )
        {
            JOptionPane.showMessageDialog(null, "vous devez remplir tous les champs !");
        }
        else
        {
            ProgrammeEntraineurCRUD pec = new ProgrammeEntraineurCRUD();
            ProgrammeEntraineur pe = new ProgrammeEntraineur((in_idExercice.getText()), in_nomPack.getText(), in_type.getText());
            pec.ajouterProgrammeEntraineur(pe);
            JOptionPane.showMessageDialog(null, "ProgrammeEntraineur ajouté!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficher.fxml"));
            try {
                Parent root = loader.load();
                in_idExercice.getScene().setRoot(root);
                

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
            
    }   
}
