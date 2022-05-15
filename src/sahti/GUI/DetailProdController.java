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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DetailProdController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label prodnom;
    @FXML
    private Label prixprod;
    @FXML
    private Label lblqte;
    @FXML
    private Label descprod;
    @FXML
    private ScrollPane scroll;
    @FXML
    private Button btnmoin;
    @FXML
    private Button btnplus;
    
    private int qteprod;
    @FXML
    private Button btnajoutpanier;
    @FXML
    private Button topanier;
    @FXML
    private Button goback;
    @FXML
    private AnchorPane avis_;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            Node node;
            node = (Node) FXMLLoader.load(getClass().getResource("AjouterAvis.fxml"));
            avis_.getChildren().setAll(node);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }   
     public void setQte(int qte) {
        this.lblqte.setText(String.valueOf(qte));
    }
     public void setPrix(double prix) {
        this.prixprod.setText(String.valueOf(prix));
    }
     public void setName(String nom) {
        this.prodnom.setText(nom);
    }
     public void setDesc(String desc) {
        this.descprod.setText(desc);
    }
    
     public void setQteProd(int qteprod) {
        this.qteprod = qteprod;
    }
    
     public void setImg (String path) {
        try {
            String imgpath = path;
            Image image = new Image(new FileInputStream(imgpath));
            img.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailProdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void decrementQte(ActionEvent event) {
      
        String qtes = lblqte.getText();

        int qtefinal = Integer.parseInt(qtes);

        if (qtefinal > 1) {
            btnplus.setVisible(true);
            qtefinal--;//selon le nombre dipo dans le stock;

        } else {
            btnmoin.setVisible(false);

        }

        lblqte.setText(String.valueOf(qtefinal));
    }

    @FXML
    private void incrementQte(ActionEvent event) { 
        String qtes = lblqte.getText();

        int qte = Integer.parseInt(qtes);

        if (qte < this.qteprod) {
            qte++;//selon le nombre dipo dans le stock;

            btnmoin.setVisible(true);
        }
        if (qte == this.qteprod) {
            btnplus.setVisible(false);
        }

        lblqte.setText(String.valueOf(qte));
        

        
    }

    @FXML
    private void ajoutpanier(ActionEvent event) {
    }

    @FXML
    private void topanier(ActionEvent event) {
    }

    @FXML
    private void goback(ActionEvent event) {
    }
    
}
