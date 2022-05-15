/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sahti.entities.Client;
import sahti.entities.Entraineur;
import sahti.entities.Nutritioniste;
import sahti.utils.MyConnection;

/**
 *
 * @author Rayen
 */
public class AdminPannel extends ListUser {

    public AdminPannel() {

    }

    public void List() {

        AdminPannel a = new AdminPannel();
        a.GetClient();
        a.GetEntraineur();
        a.GetNutritioniste();
    }

    public void DeleteClient(Client c) {

        try {

            String query = "  DELETE FROM client WHERE id=? ";
            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setInt(1, c.getId());

            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("Client deleted successfully ! ");
            }
            else {
                System.out.println("something went wrong");
            }

        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void DeleteEntraineur(Entraineur e) {

        try {

            String query = "DELETE FROM entraineur WHERE id=? ";
            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setInt(1, e.getId());

            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("entraineur deleted successfully ! ");
            }
            else {
                System.out.println("something went wrong");
            }
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void DeleteNutritioniste(Nutritioniste n) {

        try {

            String query = "DELETE FROM nutritioniste WHERE id=? ";
            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setInt(1, n.getId());

            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("Nutritioniste deleted successfully ! ");
            }
            else {
                System.out.println("something went wrong");
            }

        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    //*************Blocking client********************
    public void blockClient(Client c) {

        try {
            String query = "update client set IsBlocked=1 where id=? ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);

            pst.setInt(1, c.getId());

            int ok = pst.executeUpdate();
            if (ok != -1) {
                System.out.println("Blocked !");
            }
            else {
                System.out.println("somthing went wrong");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean returnBlockedClient(String mail) {
        Boolean state = false;
        try {
            String query = "select IsBlocked from client where email=?  ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, mail);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                state = res.getBoolean("IsBlocked");

                System.out.println("search state succes");
            }
            else {
                System.out.println("search state failed ");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return state;
    }

    //*************Blocking nutri********************
    public void blockNutri(Nutritioniste c) {

        try {
            String query = "update nutritioniste set IsBlocked=1 where id=? ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);

            pst.setInt(1, c.getId());

            int ok = pst.executeUpdate();
            if (ok != -1) {
                System.out.println("Blocked !");
            }
            else {
                System.out.println("somthing went wrong");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean returnBlockedNutri(String mail) {
        Boolean state = false;
        try {
            String query = "select IsBlocked from nutritioniste where email=?  ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, mail);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                state = res.getBoolean("IsBlocked");

                System.out.println("search state succes");
            }
            else {
                System.out.println("search state failed ");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return state;
    }

    //*************Blocking coach********************
    public void blockCoach(Entraineur e) {

        try {
            String query = "update entraineur set IsBlocked=1 where id=? ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);

            pst.setInt(1, e.getId());

            int ok = pst.executeUpdate();
            if (ok != -1) {
                System.out.println("Blocked !");
            }
            else {
                System.out.println("somthing went wrong");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean returnBlockedCoach(String mail) {
        Boolean state = false;
        try {
            String query = "select IsBlocked from entraineur where email=?  ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, mail);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                state = res.getBoolean("IsBlocked");

                System.out.println("search state succes");
            }
            else {
                System.out.println("search state failed ");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return state;
    }
    //*************unbloick ********************

    public void unblockClient(Client c) {

        try {
            String query = "update client set IsBlocked=0 where id=? ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);

            pst.setInt(1, c.getId());

            int ok = pst.executeUpdate();
            if (ok != -1) {
                System.out.println("unBlocked !");
            }
            else {
                System.out.println("somthing went wrong");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void unblockCoach(Entraineur e) {

        try {
            String query = "update entraineur set IsBlocked=0 where id=? ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);

            pst.setInt(1, e.getId());

            int ok = pst.executeUpdate();
            if (ok != -1) {
                System.out.println("unBlocked !");
            }
            else {
                System.out.println("somthing went wrong");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void unblockNutri(Nutritioniste c) {

        try {
            String query = "update nutritioniste set IsBlocked=0 where id=? ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);

            pst.setInt(1, c.getId());

            int ok = pst.executeUpdate();
            if (ok != -1) {
                System.out.println("unBlocked !");
            }
            else {
                System.out.println("somthing went wrong");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //****************link client with coach & nutri*********

    public void linkNutriClient(Nutritioniste n) {

        try {
            String query = "update client set id_nutri=? where email=? ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);

            pst.setInt(1, n.getId());
            pst.setString(2, Login.emailUser);

            int ok = pst.executeUpdate();
            if (ok != -1) {
                System.out.println("Nutri linked with client !");
            }
            else {
                System.out.println("somthing went wrong");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void linkCoachClient(Entraineur e) {

        try {
            String query = "update client set id_coach=? where email=? ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);

            pst.setInt(1, e.getId());
            pst.setString(2, Login.emailUser);

            int ok = pst.executeUpdate();
            if (ok != -1) {
                System.out.println("Coach linked with client !");
            }
            else {
                System.out.println("somthing went wrong");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
