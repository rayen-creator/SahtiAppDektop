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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import sahti.entities.Produit;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ProduitController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
    
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(Prod);
    }
    
    private MyListener myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     
    private Produit Prod;
     public void setData(Produit Prod, MyListener myListener){
        try {
            this.Prod = Prod;
            this.myListener = myListener;
            nameLabel.setText(Prod.getNom());
            priceLable.setText(String.valueOf(Prod.getPrix()));
            Image image = new Image(new FileInputStream(Prod.getImage()));
            img.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
             
    
}
