/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import sahti.utils.MyConnection;

/**
 *
 * @author Rayen
 */
public class ResetPassword {

    public boolean ClientPwd(String email, String pwd) {
        boolean userexiste = false;

        try {
            String query = "update client set passwd=md5(?) where email=?";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, pwd);
            pst.setString(2, email);

            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("Password Client successfully updated ! ");
                userexiste = true;
            }
            else {
                System.out.println("something went wrong");
            }

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userexiste;
    }

    public boolean NutritionistePwd(String email, String pwd) {
        boolean userexiste = false;

        try {
            String query = "update nutritioniste set passwd=md5(?) where email=?";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, pwd);
            pst.setString(2, email);

            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("Password Nutritioniste successfully updated ! ");
                userexiste = true;
            }
            else {
                System.out.println("something went wrong");
            }
            return userexiste;

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userexiste;
    }

    public boolean EntraineurPwd(String email, String pwd) {
        boolean userexiste = false;

        try {
            String query = "update entraineur set passwd=md5(?) where email=?";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, pwd);
            pst.setString(2, email);

            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("Password Entraineur successfully updated ! ");
                userexiste = true;
            }
            else {
                System.out.println("something went wrong");
            }

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userexiste;

    }

}
