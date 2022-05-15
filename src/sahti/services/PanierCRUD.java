/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sahti.entities.Produit;
import sahti.utils.MyConnection;

/**
 *
 * @author Akrimi
 */
public class PanierCRUD {
    Connection cnx;

    public PanierCRUD() {
        cnx = MyConnection.getInstance().getConnexion(); 
    }
    
/* public ObservableList<Panier> afficherListeLivraison(){
        List<Panier> listPanier = new ArrayList<>();
        try {
            
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery("select * from livraison");
            while(rst.next()){
                Livraison l = new Livraison();
                
                l.setId(rst.getInt(1));
                l.setIdCommande(rst.getInt("idCommande"));
                l.setEtatLivraison(rst.getString("etatLivraison"));
                l.setDateLivraison(rst.getDate("DateLivraison"));
                
                listLivraison.add(l);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        ObservableList<Livraison> observablelist = FXCollections.observableArrayList(listLivraison);
        return observablelist;
}    */
    
    public List<Produit> ajouterPanier(Produit p){
                
        List<Produit> prod = new ArrayList<>();
        prod.add(p);               
        ObservableList<Produit> list = FXCollections.observableArrayList(prod);
        return prod;          
    }
}
