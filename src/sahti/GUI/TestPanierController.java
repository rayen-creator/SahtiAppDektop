/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import sahti.entities.Produit;
import sahti.services.PanierCRUD;

/**
 * FXML Controller class
 *
 * @author Akrimi
 */
public class TestPanierController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private AnchorPane anchorpane;
    
    private List<Produit> produit = new ArrayList<>();
    private List<Produit> mylistprod = new ArrayList<>();
    @FXML
    private Button btnPasserCommande;    
    @FXML
    private Button btnAddtoCart;
    
    private AnchorPane anchorpane1;
    
    
     public void setList(List<Produit> mylistprod) {
        this.mylistprod = mylistprod;
    }
    
    List<Produit> myProds=new ArrayList<>();
    ObservableList<Produit> Produits = FXCollections.observableArrayList();
    public int column = 0;
        public int row = 0;
    
    
    
    @FXML
   public ObservableList<Produit> getData() {               
        System.out.println(mylistprod);
        ObservableList<Produit> list = FXCollections.observableArrayList(mylistprod);
        return list;
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            int column = 0;
            int row = 1;
            for (int i = 0; i <= mylistprod.size() - 1; i++) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("detailProduitPanier.fxml"));
                anchorpane1 = fxmlLoader.load();

                DetailProduitPanierController pcc = fxmlLoader.getController();
                pcc.setData(mylistprod.get(i));
                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorpane1, column, row++); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorpane1, new Insets(1));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
    @FXML
    private void commander(ActionEvent event) {
        System.out.println("Working");
        //produits.add("1");
        //qteCmd.add(String.valueOf(qte));
        
//        CommandeCRUD cr = new CommandeCRUD();
//        Commande c = new Commande("commentaire");
//        cr.passerCommande(prixUnit,  produits, qteCmd, c);
//        produits.clear();
//        qteCmd.clear();
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommandes.fxml"));
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
        }*/
    }

    
}
