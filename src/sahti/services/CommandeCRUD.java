/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sahti.services;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sahti.entities.Client;
import sahti.entities.Commande;
import sahti.entities.Entraineur;
import sahti.entities.Nutritioniste;

import sahti.utils.MyConnection;



/**
 *
 * @author Akrimi
 */
public class CommandeCRUD {
    Connection cnx;
    Login login = new Login();
    String query;
//Client
    Client client = new Client();
    Nutritioniste nutritionniste = new Nutritioniste();
    Entraineur entraineur = new Entraineur();
    AccountModif am = new AccountModif();

    public CommandeCRUD() {
        cnx = MyConnection.getInstance().getConnexion(); 
    }
    
    //Validation date format
    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
        }
        return date!=null;
    }
    public ObservableList<Commande> rechercherCommande(String mot) throws ParseException{
        List<Commande> listCmd = new ArrayList<>();
        String query="";
        boolean rDate=false;
        try {
            Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(mot);
            
            if (isValidFormat("dd-MM-yyyy", mot) || isValidFormat("dd/MM/yyyy", mot)){
                String fdc = new SimpleDateFormat("yyyy-MM-dd").format(date1);
                query = "select * from commandes where date_commande="+fdc;
                rDate = true;
            }
            else
                query = "Select * from Commandes";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                Commande cmd = new Commande();
                if(mot.equals(rs.getString("num_cmd"))&&!rDate){
                    cmd.setId(rs.getInt("id"));
                    cmd.setNumCmd(rs.getString("num_cmd"));
                    cmd.setMontantCmd(rs.getInt("montant_cmd"));
                    
                    listCmd.add(cmd);
                } else if (rDate) {
                    cmd.setId(rs.getInt("id"));
                    cmd.setNumCmd(rs.getString("num_cmd"));
                    cmd.setMontantCmd(rs.getInt("montant_cmd"));

                    listCmd.add(cmd);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        ObservableList<Commande> listCommande = FXCollections.observableList(listCmd);
        return listCommande;
    }
    //récupérer les produit à partir du panier
    public void passerCommande(double total, List<String> produits, List<String> qteCommandee, Commande c){
        int id=0;
        try {
            id = getConnectedUser();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (login.loginClient(Login.emailUser, Login.pwduser)) {
            query = "SELECT * FROM commandes where id_client="+id;        
        }
            
        float montant = 0;

        String numCmd="2022-";//Get year
        int count = 0;
        String idProduit="";
        String qte = "";
        idProduit = produits.stream().map((elem) -> elem+",").reduce(idProduit, String::concat);
        qte = qteCommandee.stream().map((e) -> e+",").reduce(qte, String::concat);
        idProduit = idProduit.substring(0, idProduit.length()-1);
        qte = qte.substring(0, qte.length()-1);
        
        System.out.println(idProduit);
        try {   
            String query1 = "Select count(*) as total from Commandes";
            Statement st = cnx.createStatement();
            ResultSet res1 = st.executeQuery(query1);
            while (res1.next()) {
                count = res1.getInt(1)+1;
                System.out.println(count);
            }
            if (count >= 0) {
                if(count<10){
                    numCmd+="0"+count++;
                    System.out.println(count);
                }
                else
                    numCmd +=  count++;
            }
            java.util.Date date_util = new java.util.Date();
            java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
           
            //ajouter les id du produit
            ///////////////////////////////////// tooooo cheeeeeeeeeeeeeeecccccccccckkkkkkkkkkkkkkkkkkkkkkkk//////////
            //String query = "insert into Commandes(num_cmd,idProduit, idClient,qteCommandee, montantCmd, commentaire,dateCommande) values(?,?,?,?,?,?,?)";
            
            String query = "insert into Commandes(num_cmd, id_client, qtecmd, montant_cmd, commentaire,date_commande) values(?,?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, numCmd);
            //pst.setString(2, idProduit);
            pst.setInt(2, id);
            pst.setString(3, qte);
            pst.setDouble(4, total);
            pst.setString(5, c.getCommentaireCmd());
            pst.setDate(6, date_sql);

            pst.executeUpdate();
            System.out.println("Commande effectuée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        
    }
    
    public ObservableList<Commande> consulterCommandes() {
        List<Commande> listCommandes = new ArrayList<>();
        String query="";
        int id=0;
        try {
            id = getConnectedUser();
            System.out.println(id);
        } catch (SQLException ex) {
            Logger.getLogger(CommandeCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (login.loginClient(Login.emailUser, Login.pwduser)) {
            query = "SELECT * FROM commandes where id_client="+id;
        }else if (login.loginAdmin(Login.emailUser, Login.pwduser)){
            query ="select * from commandes";
        }
        if (query !=""){
        try {

            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Commande c = new Commande();

                c.setId(res.getInt(1));
                c.setNumCmd(res.getString("num_cmd"));
                c.setMontantCmd(res.getDouble("montant_cmd"));
                c.setModePay(res.getString("mode_pay"));
                c.setCommentaireCmd(res.getString("commentaire"));
                c.setEtat(res.getString("etat"));
                c.setDateCommande(res.getDate("date_commande"));
                c.setDateModif(res.getDate("date_modif"));

                listCommandes.add(c);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        }
        ObservableList<Commande> observableList = FXCollections.observableList(listCommandes);
        return observableList;
    }
    public List<Commande> consulterCommande(int id) {
        List<Commande> listCommandes = new ArrayList<>();
        String query;
        try {

            query = "select * from commandes where id="+id;

            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(query);

            while (res.next()) {
                Commande cmd = new Commande();

                cmd.setId(res.getInt(1));
                cmd.setNumCmd(res.getString("num_cmd"));
                cmd.setMontantCmd(res.getDouble("montant_cmd"));                
                cmd.setDateCommande(res.getDate("date_commande"));

                listCommandes.add(cmd);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listCommandes;
    }
    public void modifierEtatCommande(Commande c){
         java.util.Date date_util = new java.util.Date();
         java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        try{
            PreparedStatement pst = cnx.prepareStatement("update commandes SET etat=?, date_modif=? where id=?");
            pst.setString(1, "Payée");
            pst.setDate(2, date_sql);
            pst.setInt(3, c.getId());
            pst.executeUpdate();
            System.out.println("Etat commande modifé");            
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }      
    }
    public List<Commande> consulterEtatCommande(){
        List<Commande> etatCommande = new ArrayList<>();
        int id=0;
        String query = "";
        try {
            id = getConnectedUser();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (login.loginAdmin(Login.emailUser, Login.pwduser)){
            query =  "select * from commande";
        }
        if (login.loginClient(Login.emailUser, Login.pwduser)) {
            query = "SELECT * FROM commandes where id_client="+id;        
        }
        if (query!=""){
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery("select id,etat,date_commande from commandes");
            System.out.println("Etat commandes");
            while (res.next()) {
                Commande c = new Commande();
                c.setId(res.getInt(1));
                c.setEtat(res.getString("etat"));
                c.setDateCommande(res.getDate("date_commande"));

                etatCommande.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
        return etatCommande;
    }
    public int getConnectedUser() throws SQLException{
    int id=0;
        if (login.loginClient(Login.emailUser, Login.pwduser))
            id=am.searchIDClient(Login.emailUser);
        
       return id;
    }
}
