/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;

import sahti.entities.Repas;
import sahti.entities.reg_repas;
import sahti.interfaces.InterfaceRepas;
import sahti.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public class RepasCRUD implements InterfaceRepas {

    Connection connexion;

    public RepasCRUD() {

        connexion = MyConnection.getInstance().getConnexion();
    }

    @Override
    public void AjouterRepas(Repas r) {
        
            try {
                Statement stm = connexion.createStatement();
                String query = "INSERT INTO `repas`(`id_repas`, `nom_rep`, `nb_cal`, `quantite`)"
                        + " VALUES (" + r.getId_repas() + ",'" + r.getNom_rep() + "','"
                        + r.getNb_cal() + "','" + r.getQuantite() + "')";
                stm.executeUpdate(query);
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        
    }

    @Override
    public void ModifierRepas(Repas r) {
        try {
            Statement stm = connexion.createStatement();
            String query = "UPDATE `repas` SET `nom_rep`='" + r.getNom_rep()
                    + "', `nb_cal`='" + r.getNb_cal() + "',`quantite`='" + r.getQuantite()
                    + "' WHERE id_repas =" + r.getId_repas() + ";";
            stm.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ObservableList<Repas> getRepasList() {
        ObservableList<Repas> RepasList = FXCollections.observableArrayList();
        String query = "SELECT * from repas";
        Statement stm;
        ResultSet rst;
        try {
            stm = connexion.createStatement();
            rst = stm.executeQuery(query);
            Repas repas;

            while (rst.next()) {
                Repas repa = new Repas();
                repa.setId_repas(rst.getInt("id_repas"));
                repa.setNom_rep(rst.getString("nom_rep"));
                repa.setNb_cal(rst.getInt("nb_cal"));
                repa.setQuantite(rst.getInt("quantite"));
                RepasList.add(repa);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return RepasList;

    }

    @Override
    public void SupprimerRepas(Repas r) {
        try {
            //Statement stm = connexion.createStatement();

            String query = "DELETE FROM `repas` WHERE id_repas=" + r.getId_repas();
            PreparedStatement pst = connexion.prepareStatement(query);

            pst.executeUpdate(query);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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

    @Override
    public ObservableList<Repas> AfficherRepas() throws SQLException {
        Statement stm = connexion.createStatement();
        String query = "SELECT * FROM `repas`";
        ResultSet rst = stm.executeQuery(query);
        ObservableList<Repas> repass;
        repass = FXCollections.observableArrayList();
        while (rst.next()) {
            Repas repa = new Repas();
            repa.setId_repas(rst.getInt("id_repas"));
            repa.setNom_rep(rst.getString("nom_rep"));
            repa.setNb_cal(rst.getInt("nb_cal"));
            repa.setQuantite(rst.getInt("quantite"));

        }
        return repass;
    }

    

}
