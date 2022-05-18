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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import sahti.entities.Avis;
import sahti.services.AvisCRUD;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class AjouterAvisController implements Initializable {

    @FXML
    private Rating rtProduit;
    @FXML
    private ImageView imgProduit;
    @FXML
    private TextArea txtaComment;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnAjouter;
    @FXML
    private Label lblNom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*try {
            Image image = new Image(new FileInputStream("Image\\coach.jpg"));
              
        imgProduit.setImage(image);
        } catch (FileNotFoundException ex) {
           System.out.println(ex.getMessage());
        }*/
    }    

    @FXML
    private void addAvis(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Champ obligatoire");
        // Header Text: null
        alert.setHeaderText(null);
        
        String comment = txtaComment.getText();
        int rating = (int) rtProduit.getRating();
        if (rating == 0){
            alert.setContentText("Le rating ne doit pas être vide!");
            alert.showAndWait();            
        }else
            if(comment.isEmpty()){
                alert.setContentText("Le champ commentaire ne doit pas être vide!");
                alert.showAndWait();
            }
        Avis a = new Avis(comment, rating);
        AvisCRUD ac = new AvisCRUD();
        ac.ajouterAvis(a); 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherAvisCoach.fxml"));
         try {
            Stage primaryStage = new Stage();
            Parent root;

            root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("Liste des avis");
            primaryStage.setScene(scene);
            primaryStage.show();

           
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
}
