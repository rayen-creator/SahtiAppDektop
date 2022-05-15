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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sahti.entities.Avis;
import sahti.entities.Client;
import sahti.entities.Entraineur;
import sahti.entities.Nutritioniste;
import sahti.utils.MyConnection;

/**
 *
 * @author Akrimi
 */
public class AvisCRUD {
    public int priorite;
    Connection cnx;
    
    
    Avis a = new Avis();
    
    Login login = new Login();
    Nutritioniste nutritionniste = new Nutritioniste();
    Entraineur entraineur = new Entraineur();
    AccountModif am = new AccountModif();

    public AvisCRUD() {
        cnx = MyConnection.getInstance().getConnexion(); 
    }
        
    public int analyserAvis(){return priorite;}
    
    
    public void ajouterAvis(Avis a){
        //récupérer iduser connecter
        Date sqlDate = new Date(a.getDateAvis().getTime());
        int id=0;
        try {
            id = getConnectedUser();
        } catch (SQLException ex) {
            Logger.getLogger(AvisCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (login.loginClient(Login.emailUser, Login.pwduser)){
            
        
        try{            
            String requete2 = "insert into avis(commentaire, rating,dateAvis,idClient)values(?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, a.getCommentaire());
            pst.setInt(2, a.getRating());             
            pst.setDate(3, sqlDate);
            pst.setInt(4,id);
                        
            pst.executeUpdate();
            
            System.out.println("Avis est ajoutée");
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        }
        
    }
    public void modifierAvis(Avis a){
        int id=0;
        try {
            id = getConnectedUser();
        } catch (SQLException ex) {
            Logger.getLogger(AvisCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (login.loginClient(Login.emailUser, Login.pwduser))
        try{            
            PreparedStatement pst = cnx.prepareStatement("update table avis SET commentaire = ? and rating=? where id=? and idClient=?");                
            pst.setString(1, a.getCommentaire());
            pst.setInt(2, a.getRating());
            pst.setInt(3, a.getId());
            pst.setInt(4, a.getIdClient());
            
            pst.executeUpdate();
            
            System.out.println("Avis modifé");
            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }            
    }
    
    
    
    public void supprimerAvis(Avis a){
        try{            
            PreparedStatement pst = cnx.prepareStatement("Delete from Avis where id=?");  
            pst.setInt(1,a.getId());
            pst.executeUpdate();
            System.out.println("Avis supprimé");            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }            
    }
        
    public ObservableList<Avis> afficherAvis(String attr){
        List<Avis> listAvis = new ArrayList();
        String query;
         int id=0;
        try {
            id = getConnectedUser();
        } catch (SQLException ex) {
            Logger.getLogger(AvisCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
           
            query = "select * from avis where idClient="+id;
            
            Statement st = cnx.createStatement();
            
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Avis avis = new Avis();
                avis.setId(res.getInt(1));
                avis.setCommentaire(res.getString("commentaire"));
                avis.setRating(res.getInt("rating"));
                             
                listAvis.add(avis);
            }
        
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        } 
       ObservableList<Avis> observableList = FXCollections.observableList(listAvis);
        return observableList;
    }
    //rating seulement
    public List<Avis> afficherRating(){
        List<Avis> listAvis = new ArrayList();
        
        try{
            String query = "Select id,rating from avis";
            Statement st = cnx.createStatement();                    
            ResultSet res = st.executeQuery(query);
            System.out.println("Ratings");
            while(res.next()){
                Avis avis = new Avis();
                avis.setId(res.getInt(1));
                avis.setRating(res.getInt("rating"));
                
                listAvis.add(avis);
            }
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return listAvis;
    }   
    public int getConnectedUser() throws SQLException{
    int id=0;
        if (login.loginClient(Login.emailUser, Login.pwduser))
            id=am.searchIDClient(Login.emailUser);
        
       return id;
    }
}
