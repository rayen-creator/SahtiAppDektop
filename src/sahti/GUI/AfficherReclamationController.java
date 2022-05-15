/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sahti.entities.Reclamation;
import sahti.services.ReclamationCRUD;


/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class AfficherReclamationController implements Initializable {

    @FXML
    private Label lblNumReclamation;
    @FXML
    private Label lblType;
    @FXML
    private Button btnValider;
    @FXML
    private Button btnQuitter;
    @FXML
    private Label lblDateReclamation;
    @FXML
    private ImageView viewPiecejointe;
    @FXML
    private Button btnRenvoyer;
    @FXML
    private Button btnAjouterCommentaire;
    @FXML
    private TextArea txtaMessage;
    @FXML
    private Label lblTitre;
    @FXML
    private TextField txtComment;
    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
      
    }
    public void setlblNumReclamation(String message){
        this.lblNumReclamation.setText(message);
    }
    public void setlblTitre(String message){
        this.lblTitre.setText(message);
    }
    public void setlblType(String message){
        this.lblType.setText(message);
    }
    public void setlblDateReclamation(Date date){
        this.lblDateReclamation.setText(date.toString());
    }
    public void settxtMessage(String message){
        this.txtaMessage.setText(message);
    }
    public void setImageReclamation(String filename){
        try {
            Image image = new Image(new FileInputStream("C:\\Users\\Akrimi\\Documents\\NetBeansProjects\\applicationSahti-Finalmain\\Image\\"+filename));
              
        viewPiecejointe.setImage(image);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    //public void setId

    @FXML
    private void AjouterCommentaire(ActionEvent event) {
        ReclamationCRUD rc = new ReclamationCRUD();
        String message = "";
        String user = rc.getUser();
        String numReclamation = lblNumReclamation.getText();
        String t = txtaMessage.getText();
        String comment = txtComment.getText();
        List<String> aList = new ArrayList<>();
        aList.add(t);
        aList.add("\n"+user+":\n"+comment);
        System.out.println(aList);
        StringBuilder listText = new StringBuilder();
        for (String s : aList) {
            message += s;
            txtaMessage.setText(message);

        }
        
        rc.envoyerCommentaire(numReclamation, comment,message);
        txtComment.setText("");
    }

    @FXML
    private void updateReclamtion(MouseEvent event) {
    }

    @FXML
    private void cloturerReclamation(ActionEvent event) {
        Optional<ButtonType> modification;
        confirmation.setTitle("Cloturer reclamation");
        // Header Text: null
        confirmation.setHeaderText(null);
        confirmation.setContentText("Voulez vous vraiment cloturer la reclamation "+lblNumReclamation.getText()+"!");        
        Reclamation r= new Reclamation(lblNumReclamation.getText(),true);
        modification = confirmation.showAndWait();
         if (modification.get() == ButtonType.OK) {
            ReclamationCRUD rc = new ReclamationCRUD();
            rc.cloturerReclamation(r);
         }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamations.fxml"));
        try {
            Stage primaryStage = new Stage();
            Parent root;

            root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("Liste des réclamations");
            primaryStage.setScene(scene);
            primaryStage.show();

            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        
    }
}
