/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;

import entities.ProgrammeEntraineur;
import entities.SuivieEvolution;
import interfaces.IProgrammeEntraineur;
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
public class ProgrammeEntraineurCRUD implements IProgrammeEntraineur<ProgrammeEntraineur>{
    
    public static boolean existe(int id)
    {
        boolean resultat=false;
        try {
            String requete = "SELECT EXISTS(SELECT * FROM programmeEntraineur WHERE id='"+id+"')";
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
    public void ajouterProgrammeEntraineur(ProgrammeEntraineur t) {
        try {
            String requete  = "INSERT INTO `programmeEntraineur` ( `idExercice` , `nomPack`, `type`) VALUES ( '"+t.getIdExercice()+"' , '"+t.getNomPack()+"', '"+t.getType()+"') ";
            Statement st = MyConnection.getInstance().getConnexion().createStatement() ;
            st.executeUpdate(requete);
            System.out.println("progammeEntraineur ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerProgrammeEntraineur(ProgrammeEntraineur t) {
        try {
            String requete = "DELETE FROM programmeEntraineur WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getConnexion().prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("programmeEntraineur supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierProgrammeEntraineur(ProgrammeEntraineur t) {
        try {
            System.out.println(t);
            String requete = " UPDATE programmeEntraineur SET idExercice=? , nomPack=?, type=? WHERE id=?" ;
            PreparedStatement pst= MyConnection.getInstance().getConnexion().prepareStatement(requete);
            pst.setString(1,t.getIdExercice());
            pst.setString(2,t.getNomPack());
            pst.setString(3,t.getType());
            pst.setInt(4, t.getId());
            pst.executeUpdate();
            System.out.println("programmeEntraineur modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<ProgrammeEntraineur> ProgrammeEntraineursList() {
        List<ProgrammeEntraineur> myList = new ArrayList<>();
        try {

            String requete = "SELECT * FROM programmeEntraineur";
            Statement st = MyConnection.getInstance().getConnexion().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                ProgrammeEntraineur pe = new ProgrammeEntraineur();
                pe.setId(rs.getInt("id"));
                pe.setIdExercice(rs.getString("idExercice"));
                pe.setNomPack(rs.getString("nomPack"));
                pe.setType(rs.getString("type"));
                myList.add(pe);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    public List<ProgrammeEntraineur> ProgrammeEntraineursTri() {
        List<ProgrammeEntraineur> myList = new ArrayList<>();
        try {

            String requete = "SELECT * FROM programmeEntraineur ORDER BY nomPack";
            Statement st = MyConnection.getInstance().getConnexion().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                ProgrammeEntraineur pe = new ProgrammeEntraineur();
                pe.setId(rs.getInt("id"));
                pe.setIdExercice(rs.getString("idExercice"));
                pe.setNomPack(rs.getString("nomPack"));
                pe.setType(rs.getString("type"));
                myList.add(pe);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
}
