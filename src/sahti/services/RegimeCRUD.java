/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;

import sahti.entities.Regime;
import sahti.entities.reg_repas;
import sahti.interfaces.InterfaceRegime;
import sahti.utils.MyConnection;
import java.sql.Connection;
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



/**
 *
 * @author user
 */
public class RegimeCRUD implements InterfaceRegime {

    Connection connexion;
    Login login = new Login();
    AccountModif am = new AccountModif();


    public RegimeCRUD() {
        connexion = MyConnection.getInstance().getConnexion(); 
    }

    /**
     * @param r
     */
    public void AjouterRegime(Regime r) {
        int id = 0;
        id = getConnectUser();
        if (id != 0) {

            try {
                Statement stm = connexion.createStatement();
                String query = "INSERT INTO `regime`(`id_regime`,id_specialiste, `objectif`, `date_debut`, `duree`, `max_calories`, `nb_repas`, `image`)"
                        + " VALUES (" + r.getId_regime() + ",'" + id + "','" + r.getObjectif() + "','" + r.getDate_debut() + "'," + r.getDuree() + "," + r.getMax_calories() + ","
                        + r.getNb_repas() + ",'" + r.getImage() + "')";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(RegimeCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Regime> AfficherRegime() {
        List<Regime> myList = new ArrayList<>();
        int id = 0;
        id = getConnectUser();
        if (id != 0) {
            try {
                String requete1 = "SELECT * FROM `regime` where id_specialiste=" + id;
                Statement stm = connexion.createStatement();
                ResultSet res = stm.executeQuery(requete1);
                while (res.next()) {
                    Regime r = new Regime();
                    r.setId_regime(res.getInt("id_regime"));
                    r.setId_specialiste(res.getInt("id_specialiste"));
                    r.setObjectif(res.getString("objectif"));
                    r.setDate_debut(res.getString("date_debut"));
                    r.setDuree(res.getInt("duree"));
                    r.setMax_calories(res.getInt("max_calories"));
                    r.setNb_repas(res.getInt("nb_repas"));
                    r.setId_specialiste(res.getInt("id_specialiste"));
                    r.setImage(res.getString("image"));

                    myList.add(r);
                }
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return myList;
    }

    public void SupprimerRegime(Regime r) {
        
        try {
            String requete = "DELETE FROM `regime` WHERE id_regime=" + r.getId_regime();
            //Statement stm = connexion.createStatement();
            PreparedStatement pst = connexion.prepareStatement(requete);
            pst.executeUpdate(requete);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());;
        }

    }

    public ObservableList<Regime> getRegimesList() {
        ObservableList<Regime> RegimesList = FXCollections.observableArrayList();
        int id = 0;
        id = getConnectUser();
        if (id != 0) {
            String requete = "SELECT * from regime where id_specialiste="+id;
            Statement stm;
            ResultSet rst;
            try {
                stm = connexion.createStatement();
                rst = stm.executeQuery(requete);
                Regime regimes;

                while (rst.next()) {
                    Regime regime = new Regime();
                    regime.setId_regime(rst.getInt("id_regime"));
                    regime.setId_specialiste(rst.getInt("id_specialiste"));
                    regime.setObjectif(rst.getString("objectif"));
                    regime.setDate_debut((rst.getDate("date_debut").toString()));
                    regime.setDuree(rst.getInt("duree"));
                    regime.setMax_calories(rst.getInt("max_calories"));
                    regime.setNb_repas(rst.getInt("nb_repas"));
                    regime.setId_specialiste(rst.getInt("id_specialiste"));
                    regime.setImage(rst.getString("image"));
                    RegimesList.add(regime);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return RegimesList;

    }

    public void ModifierRegime(Regime r) {
        try {
            Statement stm = connexion.createStatement();
            String requete = "UPDATE `regime` SET `objectif`='" + r.getObjectif() + "',`date_debut`='"
                    + r.getDate_debut() + "',`duree`='" + r.getDuree() + "',`max_calories`='"
                    + r.getMax_calories()
                    + "',`nb_repas`='" + r.getNb_repas() + "',`id_specialiste`='" + r.getId_specialiste()
                    + "',`image`='" + r.getImage() + "'WHERE id_regime =" + r.getId_regime() + ";";

            stm.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(RegimeCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void ajouterREGIME(reg_repas re) {

        try {
            String requete = "INSERT INTO reg_repas (id_regime,id_repas) VALUES (" + re.getId_regime() + "," + re.getId_repas() + ")";
            PreparedStatement pst = connexion.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Repas Ajoutée !");

        } catch (SQLException ex) {
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