/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sahti.utils.MyConnection;

/**
 *
 * @author Rayen
 */
public class AccountModif {

    public void AdminModifUserName(String newn, String newp, String email) {

        try {
            String query = "update admin set nom=? , prenom=?  where email=? ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);

            pst.setString(1, newn);
            pst.setString(2, newp);
            pst.setString(3, Login.emailUser);
//            pst.setInt(3, c.getId());

            int ok = pst.executeUpdate();
            if (ok != -1) {
                System.out.println("updated !");
            } else {
                System.out.println("somthing went wrong");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void AdminModifPwd(String pwdn, String pwdo, String email) {

        try {
            String query = "update admin set passwd=md5(?)  where email=? and passwd=md5(?) ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, pwdn);
            pst.setString(2, Login.emailUser);
            pst.setString(3, pwdo);

            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("updated !");
            } else {
                System.out.println("somthing went wrong");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //**********************************************************************************************
    public void ClientModifUserName(String newn, String newp, String img, String email) {

        try {
            String query = "update client set nom=? , prenom=?  , img=? where email=? ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);

            pst.setString(1, newn);
            pst.setString(2, newp);
            pst.setString(3, img);
            pst.setString(4, Login.emailUser);
//            pst.setInt(3, c.getId());

            int ok = pst.executeUpdate();
            if (ok != -1) {
                System.out.println("updated !");
            } else {
                System.out.println("somthing went wrong");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void ClientModifPwd(String pwdn, String pwdo, String email) {

        try {
            String query = "update client set passwd=md5(?)  where email=? and passwd=md5(?) ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, pwdn);
            pst.setString(2, Login.emailUser);
            pst.setString(3, pwdo);

            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("updated !");
            } else {
                System.out.println("somthing went wrong");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    //**********************************************************************************************

    public void CoachModifPwd(String pwdn, String pwdo, String email) {

        try {
            String query = "update entraineur set passwd=md5(?)  where email=? and passwd=md5(?) ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, pwdn);
            pst.setString(2, Login.emailUser);
            pst.setString(3, pwdo);

            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("updated !");
            } else {
                System.out.println("somthing went wrong");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void EntraineurModifUserName(String newn, String newp, String bio, String img, String email) {

        try {
            String query = "update entraineur set nom=? , prenom=? , bio=? , img=? where email=? ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);

            pst.setString(1, newn);
            pst.setString(2, newp);
            pst.setString(3, bio);
            pst.setString(4, img);
            pst.setString(5, Login.emailUser);

            int ok = pst.executeUpdate();
            if (ok != -1) {
                System.out.println("updated !");
            } else {
                System.out.println("somthing went wrong");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//***********************************************************************************************

    public void NutriModifPwd(String pwdn, String pwdo, String email) {

        try {
            String query = "update nutritioniste set passwd=md5(?)  where email=? and passwd=md5(?) ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, pwdn);
            pst.setString(2, Login.emailUser);
            pst.setString(3, pwdo);

            int ok = pst.executeUpdate();

            if (ok != -1) {
                System.out.println("updated !");
            } else {
                System.out.println("somthing went wrong");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void NutritionisteModifUserName(String newn, String newp, String bio, String img, String email) {
        try {
            String query = "update nutritioniste set nom=? , prenom=?  , bio=? where email=? ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);

            pst.setString(1, newn);
            pst.setString(2, newp);
            pst.setString(3, bio);
            pst.setString(4, img);
            pst.setString(5, Login.emailUser);

            int ok = pst.executeUpdate();
            if (ok != -1) {
                System.out.println("updated !");
            } else {
                System.out.println("somthing went wrong");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
//************************************Search for user'sname**************************************************************

    public String searchclientbyemail(String mail) {
        String username = "";
        try {
            String query = "select nom ,prenom from client where email=?  ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, mail);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");
                username = nom + " " + prenom;
                System.out.println("search succes");
            } else {
                System.out.println("search failed ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return username;
    }

    public String searchcoachbyemail(String mail) {
        String username = "";
        try {
            String query = "select nom ,prenom from entraineur where email=?  ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, mail);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");
                username = nom + " " + prenom;
                System.out.println("search succes");
            } else {
                System.out.println("search failed ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return username;
    }

    public String searchnutritionistebyemail(String mail) {
        String username = "";
        try {
            String query = "select nom ,prenom from nutritioniste where email=?  ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, mail);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");
                username = nom + " " + prenom;
                System.out.println("search succes");
            } else {
                System.out.println("search failed ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return username;
    }

    public String searchadminbyemail(String mail) {
        String username = "";
        try {
            String query = "select nom ,prenom from admin where email=?  ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, mail);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                String nom = res.getString("nom");
                String prenom = res.getString("prenom");
                username = nom + " " + prenom;
                System.out.println("search succes");
            } else {
                System.out.println("search failed ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return username;
    }

    //************************************Search for user's img**************************************************************
    public String searchImgClient(String mail) {
        String img = "";
        try {
            String query = "select img from client where email=?  ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, mail);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                String imgFound = res.getString("img");
                img = imgFound;
                System.out.println("search img succes");
            } else {
                System.out.println("search failed ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return img;
    }

    public String searchImgCoach(String mail) {
        String img = "";
        try {
            String query = "select img from entraineur where email=?  ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, mail);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                String imgFound = res.getString("img");
                img = imgFound;
                System.out.println("search img succes");
            } else {
                System.out.println("search failed ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return img;
    }

    public String searchImgNutri(String mail) {
        String img = "";
        try {
            String query = "select img from nutritioniste where email=?  ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, mail);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                String imgFound = res.getString("img");
                img = imgFound;
                System.out.println("search img succes");
            } else {
                System.out.println("search failed ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return img;
    }
//************************************Search for user's id**************************************************************

    public int searchIDClient(String mail) {
        int idClient = 0;
        try {
            String query = "select id from client where email=?  ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, mail);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                int id = res.getInt("id");
                idClient = id;
                System.out.println("search img succes");
            } else {
                System.out.println("search failed ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return idClient;
    }

    public int searchIDCoach(String mail) {
        int idCoach = 0;
        try {
            String query = "select id from entraineur where email=?  ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, mail);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                int id = res.getInt("id");
                idCoach = id;
                System.out.println("search id succes");
            } else {
                System.out.println("search failed ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return idCoach;
    }

    public int searchIDNutri(String mail) {
        int idNutri = 0;
        try {
            String query = "select id from nutritioniste where email=?  ";

            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(query);
            pst.setString(1, mail);

            ResultSet res = pst.executeQuery();
            if (res.next()) {
                int id = res.getInt("id");
                idNutri = id;
                System.out.println("search id succes");
            } else {
                System.out.println("search failed ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return idNutri;
    }
}
