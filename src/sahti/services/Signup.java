/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;


import java.io.File;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import sahti.entities.Client;
import sahti.entities.Entraineur;
import sahti.entities.Nutritioniste;
import sahti.utils.MyConnection;

/**
 *
 * @author Rayen
 */
public class Signup {

    //id should be generated auto
    //relation SQL ??
    public static String UploadIMG(String f) {
        return f;
    }

    public boolean signupClient(Client c) {
        boolean usercreated = false;
        try {
            String query = "create table IF NOT EXISTS client (id int auto_increment key,"
                    + "nom varchar(30) , "
                    + "prenom varchar(30) , "
                    + "email varchar(30) , "
                    + "passwd varchar(100),"
                    + " adresse varchar(30),"
                    + "datenaiss varchar(30),"
                    + "img varchar(100) , "
                    + "IsBlocked boolean DEFAULT 0, id_coach int , id_nutri int ,"
                    + "FOREIGN KEY(id_coach) REFERENCES entraineur (id),"
                    + "FOREIGN KEY(id_nutri) REFERENCES nutritioniste (id),"
                    + " UNIQUE (email) )";
            Statement st = MyConnection.getInstance().getConnexion().createStatement();

            st.execute(query);
            String requete = "INSERT INTO client (nom,prenom,email,passwd,adresse,datenaiss,img) "
                    + "VALUES (?,?,?,md5(?),?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(requete);  //Statement.RETURN_GENERATED_KEYS

//            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    c.setId( generatedKeys.getInt(1));
//                }
//                else {
//                    throw new SQLException("Creating user failed, no ID obtained.");
//                }
//            }
            pst.setString(1, c.getNom());
            pst.setString(2, c.getPrenom());
            pst.setString(3, c.getEmail());
            pst.setString(4, c.getPasswd());
            pst.setString(5, c.getAdresse());
            pst.setString(6, c.getDateNaiss());
            pst.setString(7, c.getImg());
//            pst.setBoolean(8, c.getIsBlocked());

//            pst.executeUpdate();
            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println(" Client Signed up successfully !");
                usercreated = true;
            }
            else {
                System.out.println(" Client Signed up failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return usercreated;
    }

    public boolean signupNutritioniste(Nutritioniste n) {
        boolean usercreated = false;

        try {
            String query = "create table IF NOT EXISTS nutritioniste (id int auto_increment key"
                    + ",nom varchar(30) , "
                    + "prenom varchar(30) ,"
                    + " email varchar(30) , "
                    + "passwd varchar(100), "
                    + "adresse varchar(30),"
                    + "bio varchar(30),"
                    + "certification varchar(30) ,"
                    + "img varchar(100) ,IsBlocked boolean DEFAULT 0,"
                    + " UNIQUE (email) )";
            Statement st = MyConnection.getInstance().getConnexion().createStatement();
            st.execute(query);
            String requete = "INSERT INTO nutritioniste (nom,prenom,email,passwd,adresse,bio,certification,img) "
                    + "VALUES (?"
                    + ",?"
                    + ",?"
                    + ",md5(?)"
                    + ",?"
                    + ",?,"
                    + "?,"
                    + "?)";
            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(requete);

            pst.setString(1, n.getNom());
            pst.setString(2, n.getPrenom());
            pst.setString(3, n.getEmail());
            pst.setString(4, n.getPasswd());
            pst.setString(5, n.getAdresse());
            pst.setString(6, n.getBio());
            pst.setString(7, n.getCertification());
            pst.setString(8, n.getImg());

            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println(" Nutritioniste Signed up successfully !");
                usercreated = true;
            }
            else {
                System.out.println(" Nutritioniste Signed up failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return usercreated;

    }

    public boolean signupEntraineur(Entraineur e) {
        boolean usercreated = false;
        try {
            String query = "create table IF NOT EXISTS entraineur (id int auto_increment key"
                    + ",nom varchar(30) , "
                    + "prenom varchar(30) ,"
                    + " email varchar(30) , "
                    + "passwd varchar(50), "
                    + "adresse varchar(30),"
                    + "bio varchar(30),"
                    + "certification varchar(30) ,"
                    + "img varchar(100) ,IsBlocked boolean DEFAULT 0,"
                    + "UNIQUE (email) )";

            Statement st = MyConnection.getInstance().getConnexion().createStatement();
            st.execute(query);
            String requete = "INSERT INTO entraineur (nom,prenom,email,passwd,adresse,bio,certification,img) "
                    + "VALUES (?,?,?,md5(?),?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(requete);

            pst.setString(1, e.getNom());
            pst.setString(2, e.getPrenom());
            pst.setString(3, e.getEmail());
            pst.setString(4, e.getPasswd());
            pst.setString(5, e.getAdresse());
            pst.setString(6, e.getBio());
            pst.setString(7, e.getCertification());
            pst.setString(8, e.getImg());

            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println(" Entraineur Signed up successfully !");
                usercreated = true;
            }
            else {
                System.out.println(" Entraineur Signed up failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return usercreated;
    }

    public boolean signupAdmin() {
        boolean usercreated = false;
        try {
            String query = "create table IF NOT EXISTS Admin (id int auto_increment key"
                    + ",nom varchar(30) , "
                    + "prenom varchar(30) ,"
                    + " email varchar(30) , "
                    + "passwd varchar(100), "
                    + "UNIQUE (email) )";

            Statement st = MyConnection.getInstance().getConnexion().createStatement();
            st.execute(query);
            String requete = "INSERT INTO Admin (nom,prenom,email,passwd) VALUES ('Admin' , 'Admin' , ? , md5(?) )";
            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(requete);
            pst.setString(1, "oueslati.rayen@esprit.tn");
            pst.setString(2, "pidev2122");
            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println(" Admin Signed up successfully !");
                usercreated = true;
            }
            else {
                System.out.println(" Admin Signed up failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return usercreated;
    }
}
