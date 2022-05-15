/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;

import sahti.entities.Aliment;
import sahti.entities.ali_repas;
import sahti.interfaces.InterfaceAliment;
import sahti.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class AlimentCRUD implements InterfaceAliment {

    Connection connexion;
    Login login = new Login();
    AccountModif am = new AccountModif();

    

    public AlimentCRUD() {
        connexion = MyConnection.getInstance().getConnexion();
    }

      public void AjouterAliment(Aliment a) {
        int id = 0;
        id = getConnectUser();
        if (id != 0) {

            try {
                Statement stm = connexion.createStatement();
                String query = "INSERT INTO `aliment`(id_aliment,`nom`, `type`, `image`, `calories`, `description`) VALUES (" + a.getId_aliment() + ",'" + a.getNom() + "','" + a.getType() + "','" + a.getImage() + "'," + a.getCalories() + ",'" + a.getDescription() + "')";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(RegimeCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    public void ModifierAliment(Aliment a) {
        String query = "UPDATE `aliment` SET `nom`='" + a.getNom() + "', `type`=',`calories`='" + a.getCalories()
                + "',`description`='" + a.getDescription() + "' WHERE id_aliment =" + a.getId_aliment() + ";";

        try {
            Statement stm = connexion.createStatement();
            System.out.println("Aliment modifier avec succée!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void SupprimerAliment(Aliment a) {

        try {
            String query = "DELETE FROM `aliment` WHERE id_aliment=" + a.getId_aliment();
          PreparedStatement pst = connexion.prepareStatement(query);
            pst.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());;
        }

    }

    public ObservableList<Aliment> AfficherAliment() throws SQLException {
        Statement stm = connexion.createStatement();
        String query = "SELECT * FROM `aliment`";
        ResultSet rst = stm.executeQuery(query);
        ObservableList<Aliment> aliments;
        aliments = FXCollections.observableArrayList();
        while (rst.next()) {
            Aliment aliment = new Aliment();
            aliment.setId_aliment(rst.getInt("id_aliment"));
            aliment.setNom(rst.getString("nom"));
            aliment.setType(rst.getString("type"));
            aliment.setImage(rst.getString("image"));
            aliment.setCalories(rst.getInt("calories"));
            aliment.setDescription(rst.getString("description"));

        }
        return aliments;

    }

    public ObservableList<Aliment> getAlimentList() {
        ObservableList<Aliment> AlimentsList = FXCollections.observableArrayList();
        String query = "SELECT * from aliment";
        Statement stm;
        ResultSet rst;
        try {
            stm = connexion.createStatement();
            rst = stm.executeQuery(query);
            Aliment aliments;

            while (rst.next()) {
                Aliment aliment = new Aliment();
                aliment.setId_aliment(rst.getInt("id_aliment"));
                aliment.setNom(rst.getString("nom"));
                aliment.setType(rst.getString("type"));
                aliment.setImage(rst.getString("image"));
                aliment.setCalories(rst.getInt("calories"));
                aliment.setDescription(rst.getString("description"));

                AlimentsList.add(aliment);

            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return AlimentsList;
    }
      public ObservableList<Aliment> getEventsList()
   {
       ObservableList<Aliment> EventsList = FXCollections.observableArrayList();
       String query = "SELECT * from aliment";
       Statement stm ;
       ResultSet rst;
       try{
           stm = connexion.createStatement();
           rst = stm.executeQuery(query);
           Aliment aliments;
           
       
           while (rst.next())
            {
                Aliment aliment = new Aliment();
                aliment.setId_aliment(rst.getInt("id_aliment"));
                aliment.setNom(rst.getString("nom"));
                aliment.setDescription(rst.getString("description"));
                aliment.setImage(rst.getString("image"));
                aliment.setCalories(rst.getInt("calories"));
                aliment.setType(rst.getString("type"));
                EventsList.add(aliment);
           
       }
       }catch(Exception ex)
       {
           ex.printStackTrace();
       }
        return EventsList;
       
   }
public void ajouterREPAS(ali_repas l){
        try {
            String requete = "INSERT INTO ali_repas (id_aliment,id_repas) VALUES ("+l.getId_aliment()+","+l.getId_repas()+")";
            PreparedStatement pst = connexion.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Aliment Ajoutée !");
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public int getConnectUser() {
        int id = 0;
        if (login.loginNutritioniste(Login.emailUser, Login.pwduser)) {
            id = am.searchIDNutri(Login.emailUser);
        }
        return id;
    }
}
                 
