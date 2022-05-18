/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sahti.entities.Promotion;
import sahti.utils.MyConnection;

/**
 *
 * @author HP
 */
public class PromotionCRUD {

    Connection cnx2;

    public PromotionCRUD() {
        cnx2 = MyConnection.getInstance().getConnexion();
    }

    public void ajouterPromotion(Promotion P) {
        try {

            String query = "create table IF NOT EXISTS promotion (id_prom int auto_increment key,"
                    + "titre varchar(255) , "
                    + "porcentage double,"
                    + "ancienPrix double,"
                    + "image varchar(255),"
                    + "descProm varchar(255),"
                    + "id_prod int,"
                    + "FOREIGN KEY(id_prod) REFERENCES produit (id_prod))";

            Statement st = cnx2.createStatement();
            st.execute(query);

            String requete2 = "INSERT INTO promotion (titre,porcentage,ancienPrix,image,descProm,id_prod)"
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, P.getTitre());
            pst.setDouble(2, P.getPorcentage());
            pst.setDouble(3, P.getAncienPrix());
            pst.setString(4, P.getImage());
            pst.setString(5, P.getDescProm());
            pst.setInt(6, P.getId_prod());
            int ok = pst.executeUpdate();

            if (ok == -1) {
                System.out.println("Insertion failed");
            }
            else {
                System.out.println("Insertion succes ! ");
            }

        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    public void supprimerPromotion(Promotion p) {
        try {
            String requete = "DELETE FROM promotion  WHERE id_prom=? ";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setInt(1, p.getId_prom());
//            pst.setInt(1, id);

            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("suppression succes ! ");
            }
            else {
                System.out.println("suppression failed");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifierPromotion(Promotion p) {
        try {
//            (titre,porcentage,ancienPrix,image,descProm,id_prod)
            String requete = "update promotion set titre=? ,porcentage=?,"
                    + "ancienPrix=? ,image=?,descProm=?,id_prod=? where id_prom=?";

            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, p.getTitre());
            pst.setDouble(2, p.getPorcentage());
            pst.setDouble(3, p.getAncienPrix());
            pst.setString(4, p.getImage());
            pst.setString(5, p.getDescProm());
            pst.setInt(6, p.getId_prod());
            pst.setInt(7, p.getId_prom());

            int ok = pst.executeUpdate();

            if (ok == -1) {
                System.out.println("update failed");
            }
            else {
                System.out.println("update succes ! ");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ObservableList<Promotion> affichePromotion() {
        ObservableList<Promotion> myList = FXCollections.observableArrayList();
//(titre,porcentage,ancienPrix,image,descProm,id_prod)
        try {
            String requete3 = "SELECT * FROM promotion";
            PreparedStatement st = cnx2.prepareStatement(requete3);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Promotion p = new Promotion();
                p.setId_prom(rs.getInt(1));
                p.setTitre(rs.getString("titre"));
                p.setPorcentage(rs.getInt("porcentage"));
                p.setAncienPrix(rs.getInt("ancienPrix"));
                p.setImage(rs.getString("image"));
                p.setDescProm(rs.getString("descProm"));
                p.setId_prod(rs.getInt("id_prod"));
                myList.add(p);

            }

        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public Promotion rechercheUnePromotion(int id) {
        Promotion p = new Promotion();

        try {
            String query = "SELECT * FROM promotion WHERE id_prod=? ";
            PreparedStatement pst = cnx2.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            rs.first();
            p.setId_prom(rs.getInt(1));
            p.setTitre(rs.getString("titre"));
            p.setPorcentage(rs.getInt("porcentage"));
            p.setAncienPrix(rs.getInt("ancienPrix"));
            p.setImage(rs.getString("image"));
            p.setDescProm(rs.getString("descProm"));
            p.setId_prod(rs.getInt("id_prod"));

        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return p;
    }

    public ObservableList<Promotion> recherchePromotion(String test) {
        ObservableList<Promotion> listPromotion = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM promotion WHERE titre=?";
            PreparedStatement pst = cnx2.prepareStatement(query);
            pst.setString(1, test);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Promotion p = new Promotion();
                p.setId_prom(rs.getInt(1));
                p.setTitre(rs.getString("titre"));
                p.setPorcentage(rs.getInt("porcentage"));
                p.setAncienPrix(rs.getInt("ancienPrix"));
                p.setImage(rs.getString("image"));
                p.setDescProm(rs.getString("descProm"));
                p.setId_prod(rs.getInt("id_prod"));
                listPromotion.add(p);

            }

        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return listPromotion;
    }
}
