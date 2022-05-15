/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sahti.entities.Commande;
import sahti.entities.Livraison;
import sahti.utils.MyConnection;

/**
 *
 * @author Akrimi
 */
public class LivraisonCRUD {
    Connection cnx;

    public LivraisonCRUD() {
          cnx = MyConnection.getInstance().getConnexion(); 
    }
    
    public ObservableList<Livraison> afficherListeLivraison(){
        List<Livraison> listLivraison = new ArrayList<>();
        try {
            
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery("select * from livraison l, commandes c where c.id=l.idCommande");
            while(rst.next()){
                Livraison l = new Livraison();
                
                l.setId(rst.getInt(1));
                l.setIdCommande(rst.getInt("idCommande"));
                l.setEtatLivraison(rst.getString("etatLivraison"));
                l.setDateLivraison(rst.getDate("DateLivraison"));
                
                listLivraison.add(l);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        ObservableList<Livraison> observablelist = FXCollections.observableArrayList(listLivraison);
        return observablelist;
}
    public List<String> getLivraison(int id){
        
        List<Livraison> listLivraison = new ArrayList<>();
        List<String> list = new ArrayList<>();
        
        
        try {
            String query = "select c.*,l.* from Commandes c, livraison l where c.id=l.idCommande and l.id="+id;
            Statement st = cnx.createStatement();
            ResultSet rst = st.executeQuery(query);
            while(rst.next()){
                
                //rst.getInt("id"));
                list.add(rst.getString("etat"));
                list.add(rst.getString("numCmd"));
                list.add(rst.getDate("dateCommande").toString());
                
               
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //ObservableList<Livraison> list = FXCollections.observableList(listLivraison);
        return list;
}
    public void livrer(Livraison l,Commande c){
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        try {

            PreparedStatement pst = cnx.prepareStatement("insert into livraison(idCommande, etatLivraison, DateLivraison) values(?,?,?)");
            pst.setInt(1, c.getId());
            pst.setString(2, l.getEtatLivraison());
            pst.setDate(3, date_sql);

            pst.executeUpdate();
            System.out.println("Commande livrée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
    //API
    public void trackerLivrasion(){}
    
}
