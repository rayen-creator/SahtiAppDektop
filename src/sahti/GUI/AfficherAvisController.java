/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class AfficherAvisController implements Initializable {

    @FXML
    private Rating rating;
    @FXML
    private ImageView imgViewer;
    @FXML
    private Label lblCommentaire;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            Image image = new Image(new FileInputStream("Image\\coach.jpg"));

            imgViewer.setImage(image);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void setRating1(int message){
        this.rating.setRating(message);
    }
    public void setlblCommentaire(String message){
        this.lblCommentaire.setText(message);
    }
    public void setlblNom(String message){
        this.lblNom.setText(message);
    }
    public void setlblUser(String message){
        this.lblUser.setText(message);
    }
    
    
}
