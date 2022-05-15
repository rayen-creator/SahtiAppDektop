/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sahti.utils.MyConnection;

/**
 *
 * @author Rayen
 */
public class Login {

    public static String emailUser = "";//static variable  
    public static String pwduser = "";//static variable  
    public static String img = "";//static variable  

    
    
    public boolean loginClient(String email, String pwd) {
        boolean userexist = false;

        try {
            //**************loginClient**********************************
            String query1 = "SELECT   *  FROM client where email=? and passwd=md5(?) ";
            PreparedStatement pst1 = MyConnection.getInstance().getConnexion().prepareStatement(query1);

            pst1.setString(1, email);
            pst1.setString(2, pwd);

            ResultSet result = pst1.executeQuery();
            if (result.next()) {
                System.out.println(" Client Login Success !");
                //getting username here 
                emailUser = email;
                pwduser=pwd;
                userexist = true;
            }
            else {
                System.out.println("Client Login Failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return userexist;
    }

    public boolean loginEntraineur(String email, String pwd) {
        boolean userexist = false;
        try {
            String query = "SELECT  email , passwd FROM entraineur where email=? and passwd=md5(?)  ";
            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, pwd);
            ResultSet result = pst.executeQuery();

            if (result.next()) {
                System.out.println(" Entraineur Login Success !");
                userexist = true;
                emailUser = email;
                pwduser=pwd;

            }
            else {
                System.out.println("Entraineur Login Failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return userexist;

    }

    public boolean loginNutritioniste(String email, String pwd) {
        boolean userexist = false;

        try {
            String query = "SELECT  email , passwd FROM nutritioniste where email=? and passwd=md5(?)  ";
            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);

            pst.setString(1, email);
            pst.setString(2, pwd);
            ResultSet result = pst.executeQuery();

            if (result.next()) {
                System.out.println("Nutritioniste Login Success !");
                userexist = true;
                emailUser = email;
 		pwduser=pwd;

            }
            else {
                System.out.println("Nutritioniste Login Failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return userexist;

    }

    public boolean loginAdmin(String email, String pwd) {
       boolean userexist = false;

        try {
            String query1 = "SELECT   *  FROM admin where email=? and passwd=md5(?) ";
            PreparedStatement pst1 = MyConnection.getInstance().getConnexion().prepareStatement(query1);

            pst1.setString(1, email);
            pst1.setString(2, pwd);

            ResultSet result = pst1.executeQuery();
            if (result.next()) {
                System.out.println(" Admin Login Success !");
                //getting username here 
                emailUser = email;
                pwduser=pwd;
                userexist = true;
            }
            else {
                System.out.println("Admin Login Failed !");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return userexist;

    }
}
