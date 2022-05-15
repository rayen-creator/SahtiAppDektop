/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;


import sahti.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sahti.entities.Categorie;
import sahti.entities.Produit;

/**
 *
 * @author HP
 */
public class ProduitCRUD {

    Connection cnx2;

    public ProduitCRUD() {
        cnx2 = MyConnection.getInstance().getConnexion();
    }

    public void ajouterProduit(Produit P) {
        try {
            String query = "create table IF NOT EXISTS produit (id_prod int auto_increment key,"
                    + "nom varchar(255) , "
                    + "prix double , "
                    + "image varchar(255),"
                    + "quantite int,"
                    + "descProd varchar(255),"
                    + "id_cat int,"
                    + "FOREIGN KEY(id_cat) REFERENCES categorie (id_cat))";

            Statement st = cnx2.createStatement();
            st.execute(query);

            String requete2 = "INSERT INTO produit (nom,prix,image,quantite,descProd,id_cat) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setString(1, P.getNom());
            pst.setDouble(2, P.getPrix());
            pst.setString(3, P.getImage());
            pst.setInt(4, P.getQuantite());
            pst.setString(5, P.getDescProd());
            pst.setInt(6, P.getId_cat());

            int ok = pst.executeUpdate();

            if (ok == -1) {
                System.out.println("Insertion produit failed");
            } else {
                System.out.println("Insertion produit succes ! ");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    public void supprimerProduit(Produit p) {
        try {
            String requete3 = "DELETE FROM produit where nom=? ";
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setString(1, p.getNom());
            int ok = pst.executeUpdate();

            if (ok == -1) {
                System.out.println("suppretion produit failed");
            } else {
                System.out.println("suppretion produit succes ! ");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifierProduit(Produit p) {
        try {
            String requete4 = "update produit set nom=? ,prix=?,image=?,quantite=?,descProd=?,id_cat=? where id_prod=? ";

            PreparedStatement pst = cnx2.prepareStatement(requete4);
            pst.setString(1, p.getNom());
            pst.setDouble(2, p.getPrix());
            pst.setString(3, p.getImage());
            pst.setInt(4, p.getQuantite());
            pst.setString(5, p.getDescProd());
            pst.setInt(6, p.getId_cat());
            pst.setInt(7, p.getId_prod());

            int ok = pst.executeUpdate();

            if (ok == -1) {
                System.out.println("Modifier produit failed");
            } else {
                System.out.println("Modifier produit succes ! ");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public double Reduction(double proc, Produit p) {

        double ancienprix = p.getPrix();
        double NewPrix = ancienprix * (proc / 100d);
        return NewPrix;
    }

    public void modifierPrixProduit(Produit p, double test) {
        try {
            String requete4 = "update produit set prix=? where id_prod=?";

            PreparedStatement pst = cnx2.prepareStatement(requete4);

            pst.setDouble(1, test);
            pst.setInt(2, p.getId_prod());

            int ok = pst.executeUpdate();

            if (ok == -1) {
                System.out.println("update prix produit failed");
            } else {
                System.out.println("update prix succes ! ");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public ObservableList<Produit> afficheProduit() {
        ObservableList<Produit> myList = FXCollections.observableArrayList();

        try {
            String requete5 = "SELECT * FROM produit";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete5);
            while (rs.next()) {
                Produit p = new Produit();
                p.setId_prod(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrix(rs.getDouble("prix"));
                p.setImage(rs.getString("image"));
                p.setQuantite(rs.getInt("quantite"));
                p.setDescProd(rs.getString("descProd"));
                p.setId_cat(rs.getInt("id_cat"));
                myList.add(p);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }

    public ObservableList<Produit> rechercheParCat(int id_cat) {
        ObservableList<Produit> listProduit = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM produit where id_cat=" + id_cat;
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Produit p = new Produit();
                p.setId_prod(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrix(rs.getDouble("prix"));
                p.setImage(rs.getString("image"));
                p.setQuantite(rs.getInt("quantite"));
                p.setDescProd(rs.getString("descProd"));
                p.setId_cat(rs.getInt("id_cat"));
                listProduit.add(p);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return listProduit;
    }

    public Categorie findcategorie(Produit p) {
        Categorie c = new Categorie();
        try {
            String query = "SELECT * FROM categorie where id_cat=?";
            PreparedStatement pst = cnx2.prepareStatement(query);
            pst.setInt(1, p.getId_cat());
            ResultSet rs = pst.executeQuery();
            rs.first();
            c.setId_cat(rs.getInt(1));
            c.setTitre(rs.getString("titre"));
            c.setImg_cat(rs.getString("img_cat"));

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return c;
    }

    public Produit FindProd(int test) {
        Produit p = new Produit();
        try {
            String query = "SELECT * FROM produit where id_prod=" + test;
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                p.setId_prod(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setImage(rs.getString("image"));
                p.setPrix(rs.getDouble("prix"));
                p.setQuantite(rs.getInt("quantite"));
                p.setDescProd(rs.getString("descProd"));
                p.setId_cat(rs.getInt("id_cat"));

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return p;
    }

    public ObservableList<Produit> rechercheParNom(String test) {
        ObservableList<Produit> listProduit = FXCollections.observableArrayList();
        try {
            String requete3 = "SELECT * FROM produit WHERE nom=? ";
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setString(1, test);
            ResultSet rs = pst.executeQuery();
            Produit p = new Produit();
            rs.first();
            p.setId_prod(rs.getInt(1));
            p.setNom(rs.getString("nom"));
            p.setPrix(rs.getDouble("prix"));
            p.setImage(rs.getString("image"));
            p.setQuantite(rs.getInt("quantite"));
            p.setDescProd(rs.getString("descProd"));
            p.setId_cat(rs.getInt("id_cat"));
            listProduit.add(p);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return listProduit;
    }

//    public Produit afficheUnProduit() {
//        Produit row = null;
//        
//        try {
//            String requete6 = "SELECT * FROM produit where idProd=?";
//            Statement st = cnx2.createStatement();
//            ResultSet rs = st.executeQuery(requete6);
//            
//            while (rs.next()) {
//                row = new Produit();
//                row.setIdProd(rs.getInt("idProd"));
//                row.setNom(rs.getString("nom"));
//                row.setCateg(rs.getString("categorie"));
//                row.setDescProd(rs.getString("descProd"));
//                row.setPrix(rs.getDouble("prix"));
//                row.setImage(rs.getString("image"));
//                
//            }
//            
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        return row;
//        
//    }
    public ObservableList<Produit> trier() {
        ObservableList<Produit> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM produit ";
            PreparedStatement pst = cnx2.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Produit(rs.getInt("id_prod"), rs.getString("nom"), rs.getDouble("prix"), rs.getString("image"), rs.getInt("quantite"), rs.getString("descProd"), rs.getInt("id_cat")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //TRI
        Collections.sort(list, new MyComparatorProduit());

        return list;
    }

    class MyComparatorProduit implements Comparator<Produit> {

        @Override
        public int compare(Produit o1, Produit o2) {
            return o1.getNom().compareTo(o2.getNom());

        }

    }
}
