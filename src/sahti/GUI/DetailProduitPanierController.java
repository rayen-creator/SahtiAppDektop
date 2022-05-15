/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sahti.entities.Produit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class DetailProduitPanierController implements Initializable {

    @FXML
    private ImageView imgView;
    @FXML
    private Label lblNomProduit;
    @FXML
    private Label lblQte;
    @FXML
    private Label lblPrix;
    @FXML
    private Text lblDescProduit;
    @FXML
    private Label lblDT;
    @FXML
    private Button plusOne;
    @FXML
    private Button minesOne;
    @FXML
    private Button btnDelete;

    public Produit produit;
    public float total = 0.00f;
    public int qte;
    public double prixUnit = 50;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Produit produit) {
        try {
            this.produit = produit;
            lblNomProduit.setText(produit.getNom());
            lblQte.setText(String.valueOf(produit.getQuantite()));
            lblDescProduit.setText(produit.getDescProd());
            lblPrix.setText(String.valueOf(produit.getPrix()));
            Image image = new Image(new FileInputStream(produit.getImage()));
            imgView.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailProduitPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void incrementQte(ActionEvent event) {
        String qtes = lblQte.getText();
        String prix = lblPrix.getText();
        qte = Integer.parseInt(qtes);
        prixUnit = 50;
        if (qte < 10) {
            qte++;//selon le nombre dipo dans le stock;
            prixUnit *= qte;

            minesOne.setVisible(true);
        }
        if (qte == 10) {
            plusOne.setVisible(false);
        }
        total += prixUnit;
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

        } else {
            minesOne.setVisible(false);
            plusOne.setVisible(true);
        }
        total -= prixUnit;
        ////////////////////////////////////////ADD Alert////////////////////////////////////////
        lblQte.setText(String.valueOf(qte));
        lblPrix.setText(String.valueOf(prixUnit));
    }

    @FXML
    public void deleteProd(ActionEvent event) {

    }
}
