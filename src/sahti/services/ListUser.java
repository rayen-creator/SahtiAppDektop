/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sahti.entities.Client;
import sahti.entities.Entraineur;
import sahti.entities.Nutritioniste;
import sahti.utils.MyConnection;

/**
 *
 * @author Rayen
 */
public class ListUser {

    public ObservableList<Client> GetClient() {
        ObservableList<Client> myList = FXCollections.observableArrayList();
        try {
            String query = "SELECT id,nom , prenom , adresse, datenaiss FROM client";
            Statement st = MyConnection.getInstance().getConnexion().createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Client c = new Client();
                c.setId(res.getInt("id"));
                c.setNom(res.getString("nom"));
                c.setPrenom(res.getString("prenom"));
                c.setAdresse(res.getString("adresse"));
                c.setDateNaiss(res.getString("datenaiss"));

                myList.add(c);
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public ObservableList<Nutritioniste> GetNutritioniste() {
        ObservableList<Nutritioniste> myList = FXCollections.observableArrayList();
        try {
            String query = "SELECT id,nom , prenom , adresse , certification FROM nutritioniste";
            Statement st = MyConnection.getInstance().getConnexion().createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Nutritioniste n = new Nutritioniste();
                n.setId(res.getInt("id"));
                n.setNom(res.getString("nom"));
                n.setPrenom(res.getString("prenom"));
                n.setAdresse(res.getString("adresse"));
                n.setCertification(res.getString("certification"));

                myList.add(n);
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public ObservableList<Entraineur> GetEntraineur() {
        ObservableList<Entraineur> myList = FXCollections.observableArrayList();
        try {
            String query = "SELECT id,nom , prenom ,adresse , certification FROM entraineur";
            Statement st = MyConnection.getInstance().getConnexion().createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Entraineur e = new Entraineur();
                e.setId(res.getInt("id"));
                e.setNom(res.getString("nom"));
                e.setPrenom(res.getString("prenom"));
                e.setAdresse(res.getString("adresse"));
                e.setCertification(res.getString("certification"));

                myList.add(e);
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

}
