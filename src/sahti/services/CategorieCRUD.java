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
import static java.util.Collections.list;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sahti.entities.Categorie;
import sahti.utils.MyConnection;
/**
 *
 * @author HP
 */
public class CategorieCRUD {

    Connection cnx2;

    public CategorieCRUD() {
        cnx2 = MyConnection.getInstance().getConnexion();

    }

    public void ajouterCategorie(Categorie C) {

        try {

            String query = "create table IF NOT EXISTS categorie (id_cat int auto_increment key,"
                    + "titre varchar(30) , "
                    + "img_cat varchar(255))";

            Statement st = cnx2.createStatement();
            st.execute(query);

            String requete1 = "INSERT INTO categorie (titre,img_cat)"
                    + "VALUES (?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete1);
            pst.setString(1, C.getTitre());
            pst.setString(2, C.getImg_cat());

            int ok = pst.executeUpdate();

            if (ok == -1) {
                System.out.println("Insertion failed");
            } else {
                System.out.println("Insertion succes ! ");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    public void supprimerCategorie(Categorie cat) {
        try {
            String req = "DELETE FROM categorie where titre=?";
            PreparedStatement pst = cnx2.prepareStatement(req);
            pst.setString(1, cat.getTitre());
            pst.executeUpdate();
            System.out.println("Categorie suprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierCategorie(Categorie C) {
        try {
            String requete = "update categorie set titre=? , img_cat=? where id_cat=?";

            PreparedStatement pst = cnx2.prepareStatement(requete);
            pst.setString(1, C.getTitre());
            pst.setString(2, C.getImg_cat());
            pst.setInt(3, C.getId_cat());

            int ok = pst.executeUpdate();

            if (ok == -1) {
                System.out.println("update failed");
            } else {
                System.out.println("update succes ! ");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ObservableList<Categorie> afficheCategorie() {
        ObservableList<Categorie> myList = FXCollections.observableArrayList();

        try {
            String requete3 = "SELECT * FROM categorie";
            PreparedStatement st = cnx2.prepareStatement(requete3);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Categorie p = new Categorie();
                p.setId_cat(rs.getInt(1));
                p.setTitre(rs.getString("titre"));
                p.setImg_cat(rs.getString("img_cat"));

                myList.add(p);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public List AllCatNames() {
        List<String> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT titre FROM categorie";
            PreparedStatement st = cnx2.prepareStatement(requete3);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String names = rs.getString("titre");
                myList.add(names);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
   

    public int rechercheParCat(String test) {
        int idcat = 0;
        try {
            String query = "SELECT id_cat FROM categorie WHERE titre=? ";
            PreparedStatement pst = cnx2.prepareStatement(query);
            pst.setString(1, test);
            ResultSet rs = pst.executeQuery();
            rs.first();
            idcat = rs.getInt(1);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return idcat;
    }

    public ObservableList rechercheCategorie(String test) {
        ObservableList<Categorie> listCategorie = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM categorie WHERE titre=? ";
            PreparedStatement pst = cnx2.prepareStatement(query);
            pst.setString(1, test);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId_cat(rs.getInt(1));
                c.setTitre(rs.getString("titre"));
                c.setImg_cat(rs.getString("img_cat"));
                listCategorie.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return listCategorie;
    }

}
