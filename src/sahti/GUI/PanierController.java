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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sahti.entities.Commande;
import sahti.entities.Produit;
import sahti.services.CommandeCRUD;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class PanierController implements Initializable {

    @FXML
    private ImageView imgView;
    @FXML
    private Label lblNomProduit;
    @FXML
    private Label lblQte;
    @FXML
    private Label lblPrix;
    @FXML
    private Button btnPasserCommande;
    @FXML
    private Text lblDescProduit;
    @FXML
    private Button plusOne;
    @FXML
    private Button minesOne;
    @FXML
    private Button btnDelete;
    @FXML
    private Label lblVide;
    @FXML
    private Label lblDT;
    public float total=0.00f;
    public List<String> produits = new ArrayList<>();
    public List<String> qteCmd = new ArrayList<>();
    public int qte;
    public double prixUnit=50;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setlblNomProduit("Whey");
        settxtDescription("Conçu pour les amateurs de fitness \n "
                + "sérieux et les athlètes de performance\n"
                + " axés sur la maximisation de la force et\n des résultats");
        setlblQte(1);
        setlblPrix(50.0);
        setlblImgView("protein.jpg");
        if (lblQte.getText().equals("1")) {
            minesOne.setVisible(false);
        }
        lblVide.setVisible(false);
        qte = Integer.parseInt(lblQte.getText());

    }
     

    @FXML
    private void commander(ActionEvent event) {
        produits.add("1");
        qteCmd.add(String.valueOf(qte));
        
        CommandeCRUD cr = new CommandeCRUD();
        Commande c = new Commande("commentaire");
        cr.passerCommande(prixUnit,  produits, qteCmd, c);
        produits.clear();
        qteCmd.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommandes.fxml"));
            try {
                Stage primaryStage = new Stage();
                Parent root = loader.load();                              
                Scene scene = new Scene(root);
                primaryStage.setTitle("Commandes");
                primaryStage.setMaximized(true);
                primaryStage.setScene(scene);
                primaryStage.show();
                
                
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    public void setlblImgView(String path){
       try {
            Image image = new Image(new FileInputStream("Image\\"+path));
              
        imgView.setImage(image);
        } catch (FileNotFoundException ex) {
           System.out.println(ex.getMessage());
        }
    }
    public void setlblNomProduit(String nomProd){
        this.lblNomProduit.setText(nomProd);
    }
    public void settxtDescription(String description){
        this.lblDescProduit.setText(description);
    }
    public void setlblQte(int qte){
        this.lblQte.setText(String.valueOf(qte));
    }
    public void setlblPrix(Double prix){
        this.lblPrix.setText(String.valueOf(prix));
    }

    @FXML
    private void incrementQte(ActionEvent event) {
        String qtes = lblQte.getText();
        String prix = lblPrix.getText();
        qte = Integer.parseInt(qtes);
        prixUnit=50;
        if (qte < 10) {
            qte++;//selon le nombre dipo dans le stock;
            prixUnit *= qte;
            
            minesOne.setVisible(true);
        }
        if (qte == 10) {
            plusOne.setVisible(false);
        }
        total+=prixUnit;
        lblQte.setText(String.valueOf(qte));
        lblPrix.setText(String.valueOf(prixUnit));
    }

    @FXML
    private void decrementQte(ActionEvent event) {
        String qtes = lblQte.getText();
        String prix = lblPrix.getText();
        prixUnit = 50;
        qte = Integer.parseInt(qtes);
        
        if (qte > 1) {
            plusOne.setVisible(true);
            qte--;//selon le nombre dipo dans le stock;
            prixUnit *= qte;
            
        }
        else{
            minesOne.setVisible(false);
            plusOne.setVisible(true);
        }
        total-=prixUnit;
        ////////////////////////////////////////ADD Alert////////////////////////////////////////
        lblQte.setText(String.valueOf(qte));
        lblPrix.setText(String.valueOf(prixUnit));
    }

    @FXML
    private void deleteProd(ActionEvent event) {
        imgView.setImage(null);
        lblNomProduit.setText("");
        lblDescProduit.setText("");
        lblPrix.setText("");
        lblQte.setText("");
        btnDelete.setVisible(false);
        btnPasserCommande.setVisible(false);
        plusOne.setVisible(false);
        minesOne.setVisible(false);
        lblDT.setVisible(false);
        lblVide.setVisible(true);
    }

    @FXML
    private void addCart(ActionEvent event) {
        
    } 
}