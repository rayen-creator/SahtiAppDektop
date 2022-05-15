/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.SuivieEvolution;
import interfaces.ISuivieEvolution;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sahti.utils.MyConnection;

/**
 *
 * @author abdou
 */
public class SuivieEvolutionCRUD implements ISuivieEvolution<SuivieEvolution>{
    
    
    
    
    public static boolean existe(int id)
    {
        boolean resultat=false;
        try {
            String requete = "SELECT EXISTS(SELECT * FROM suivieEvolution WHERE id='"+id+"')";
            Statement st = MyConnection.getInstance().getConnexion().createStatement();
            ResultSet rs = st.executeQuery(requete);
            int n = 0;
            if ( rs.next() ) {
                n = rs.getInt(1);
            }
            if (n!=0)
            {
                resultat= true;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultat;
    }
    
    


    @Override
    public void ajouterSuivieEvolution(SuivieEvolution t) {
       try {
            String requete  = "INSERT INTO `suivieEvolution` ( `idUser` , `poids`, `dateDebutProgramme`, `dateEvolution`) VALUES ( '"+t.getIdUser()+"' , '"+t.getPoids()+"', '"+t.getDateDebutProgramme()+"', '"+t.getDateEvolution()+"') ";
            Statement st = MyConnection.getInstance().getConnexion().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("SuiveEvolution ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerSuivieEvolution(SuivieEvolution t) {
        try {
            String requete = "DELETE FROM suivieEvolution WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("suivieEvoluution supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierSuivieEvolution(SuivieEvolution t) {
        try {
            String requete = " UPDATE suivieEvolution SET idUser=? , poids=?, dateDebutProgramme=?, dateEvolution=? WHERE id=?" ;
            PreparedStatement pst= MyConnection.getInstance().getConnexion().prepareStatement(requete);
            pst.setInt(1,t.getIdUser());
            pst.setInt(2,t.getPoids());
            pst.setDate(3,t.getDateDebutProgramme());
            pst.setDate(4,t.getDateEvolution());
            pst.setInt(5, t.getId());
            pst.executeUpdate();
            System.out.println("suivieEvolution modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<SuivieEvolution> SuivieEvolutionsList() {
         List<SuivieEvolution> myList = new ArrayList<>();
        try {

            String requete = "SELECT * FROM suivieEvolution";
            Statement st = MyConnection.getInstance().getConnexion().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                SuivieEvolution se = new SuivieEvolution();
                se.setId(rs.getInt("id"));
                se.setIdUser(rs.getInt("idUser"));
                se.setPoids(rs.getInt("poids"));
                se.setDateDebutProgramme(rs.getDate("dateDebutProgramme"));
                se.setDateEvolution(rs.getDate("dateEvolution"));
                myList.add(se);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
   
    public List<SuivieEvolution> SuivieEvolutionsTri() {
         List<SuivieEvolution> myList = new ArrayList<>();
        try {

            String requete = "SELECT * FROM suivieEvolution ORDER BY poids";
            Statement st = MyConnection.getInstance().getConnexion().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                SuivieEvolution se = new SuivieEvolution();
                se.setId(rs.getInt("id"));
                se.setIdUser(rs.getInt("idUser"));
                se.setPoids(rs.getInt("poids"));
                se.setDateDebutProgramme(rs.getDate("dateDebutProgramme"));
                se.setDateEvolution(rs.getDate("dateEvolution"));
                myList.add(se);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
}
